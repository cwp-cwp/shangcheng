package com.taotao.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;

/**
 * 用户管理前端控制器
 * 
 * @author 叔公
 * 
 */
@Controller
@RequestMapping("/user")
public class UserController {

	/**
	 * 自动注入用户管理接口的动态代理实现类对象
	 */
	@Autowired
	private UserService userService;

	/**
	 * 校验用户数据
	 * 
	 * URL: http://127.0.0.1:8084/user/check/zhangsan/1
	 * http://127.0.0.1:8084/user/check/13488888888/2
	 * http://127.0.0.1:8084/user/check/aa@a/3
	 * 
	 * http://127.0.0.1:8084/user/check/aa@a/3?callback=callbackfunc
	 * 
	 * @param param
	 *            用户内容(username、phone、email)
	 * @param type
	 *            1、2、3分别代表username、phone、email
	 * @param callback
	 *            可选参数callback：如果有此参数表示此方法为jsonp请求，需要支持jsonp
	 * @return
	 */
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public Object checkData(@PathVariable String param,
			@PathVariable Integer type, String callback) {

		TaotaoResult result = null;
		// 参数有效性校验
		if (StringUtils.isBlank(param)) {
			result = TaotaoResult.build(400, "校验内容不能为空");
		}
		if (type == null) {
			result = TaotaoResult.build(400, "校验内容类型不能为空");
		}
		if (type != 1 && type != 2 && type != 3) {
			result = TaotaoResult.build(400, "校验内容类型错误");
		}
		// 校验出错
		if (null != result) {
			// 判断 jsonp 的回调
			if (null != callback) {
				MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(
						result);
				mappingJacksonValue.setJsonpFunction(callback);
				return mappingJacksonValue;
			} else {
				return result;
			}
		}
		// 调用服务
		try {
			result = userService.checkData(param, type);
		} catch (Exception e) {
			result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		// 判断 jsonp 的回调
		if (null != callback) {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(
					result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		} else {
			return result;
		}
	}

	/**
	 * 接收页面传递的参数，注册用户。 接收提交的数据用户名、密码、电话、邮件。使用pojo接收
	 * 
	 * @param user
	 *            用户信息
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	// 返回 json 格式数据
	public TaotaoResult createUser(TbUser user) {
		try {
			TaotaoResult result = userService.createUser(user);
			return result;
		} catch (Exception e) {
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

	/**
	 * 用户登陆， 接收表单，包含用户、密码。调用Service进行登录返回TaotaoResult
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult userLogin(String username, String password,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			TaotaoResult result = userService.userLogin(username, password,
					request, response);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

	/**
	 * 接收token调用Service返回用户信息，使用TaotaoResult包装。
	 * 请求的url：http://sso.taotao.com/user/token/{token}
	 * 
	 * @param token
	 *            存储在 redis 中的用户登陆信息
	 * @param callback
	 *            jsonp 调用
	 * @return
	 */
	@RequestMapping("/token/{token}")
	@ResponseBody
	public Object getUserByToken(@PathVariable String token, String callback) {
		TaotaoResult result = null;
		try {
			result = userService.getUserByToken(token);
		} catch (Exception e) {
			e.printStackTrace();
			result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		// 判断是否为jsonp调用
		if (StringUtils.isBlank(callback)) {
			return result;
		} else {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(
					result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
	}

}
