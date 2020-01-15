package com.taotao.sso.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;
import com.taotao.sso.JedisDao.JedisClient;
import com.taotao.sso.service.UserService;

/**
 * 用户管理 Service
 * 
 * @author 叔公
 * 
 */
@Service
public class UserServiceImpl implements UserService {

	/**
	 * 自动注入 TbUserMapper 接口的动态代理实现类对象
	 */
	@Autowired
	private TbUserMapper userMapper;

	/**
	 * 自动注入 JedisClient 接口的动态代理实现类对象
	 */
	@Autowired
	private JedisClient jedisClient;

	/**
	 * 读取配置文件中的值(用户 session 在 redis 中保存的 key)
	 */
	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;

	/**
	 * 读取配置文件中的属性值(session 的过期时间30分钟)
	 */
	@Value("${SSO_SESSION_EXPIRE}")
	private Integer SSO_SESSION_EXPIRE;

	/**
	 * 用户校验 
	 * 接收两个参数：内容、内容类型。根据内容类型查询tb_user表返回Taotaoresult对象。
	 *  Data属性值：返回数据，
	 * 		true：数据可用 
	 * 		false：数据不可用
	 */
	@Override
	public TaotaoResult checkData(String content, Integer type) {
		// 创建查询条件
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		// 对数据进行校验：1、2、3分别代表username、phone、email
		// 用户名校验
		if (1 == type) {
			criteria.andUsernameEqualTo(content);
			// 电话校验
		} else if (2 == type) {
			criteria.andPhoneEqualTo(content);
			// email校验
		} else {
			criteria.andEmailEqualTo(content);
		}
		// 执行查询
		List<TbUser> list = userMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return TaotaoResult.ok(true);
		}
		return TaotaoResult.ok(false);
	}

	/**
	 * 用户注册, 接收TbUser对象，补全user的属性。向tb_user表插入记录。返回taoTaoResult
	 */
	@Override
	public TaotaoResult createUser(TbUser user) {
		user.setUpdated(new Date());
		user.setCreated(new Date());
		// md5加密
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword()
				.getBytes()));
		userMapper.insert(user);
		return TaotaoResult.ok();

	}

	/**
	 * 用户登陆 接收两个参数: 用户名、密码。调用dao层查询用户信息。生成token.
	 * 把用户信息写入redis。返回token。使用TaotaoResult包装
	 */
	@Override
	public TaotaoResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
		// 查询用户信息
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> list = userMapper.selectByExample(example);
		// 如果没有此用户名
		if (null == list || list.size() == 0) {
			return TaotaoResult.build(400, "用户名或密码错误");
		}
		TbUser user = list.get(0);
		// 比对密码
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(
				user.getPassword())) {
			return TaotaoResult.build(400, "用户名或密码错误");
		}
		// 生成token
		String token = UUID.randomUUID().toString();
		// 保存用户之前，把用户对象中的密码清空。
		user.setPassword(null);
		// 把用户信息写入redis
		jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token,
				JsonUtils.objectToJson(user));
		// 设置session的过期时间
		jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token,
				SSO_SESSION_EXPIRE);
		// 添加写入 cookie 的逻辑，cookie的有效期时关闭浏览器就失效
		CookieUtils.setCookie(request, response, "TT_TOKEN", token);
		// 返回token
		return TaotaoResult.ok(token);

	}

	/**
	 * 接收token，调用dao，到redis中查询token对应的用户信息。返回用户信息并更新过期时间
	 */
	@Override
	public TaotaoResult getUserByToken(String token) {
		// 根据token从redis中查询用户信息
		String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
		// 判断是否为空
		if (StringUtils.isBlank(json)) {
			return TaotaoResult.build(400, "此session已经过期，请重新登录");
		}
		// 更新过期时间
		jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token,
				SSO_SESSION_EXPIRE);
		// 返回用户信息
		return TaotaoResult.ok(JsonUtils.jsonToPojo(json, TbUser.class));

	}

}
