package com.taotao.rest.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.rest.JedisDao.JedisClient;
import com.taotao.rest.service.RedisService;

/**
 * 缓存同步服务
 * @author 叔公
 *
 */
@Service
public class RedisServiceImpl implements RedisService {

	/**
	 * 自动注入 JedisClient 接口的动态代理实现类对象
	 */
	@Autowired
	private JedisClient jedisClient;
	
	/**
	 * 读取配置文件中的属性值(首页内容信息在redis中保存的key)
	 */
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	
	/**
	 * 接收内容分类id，调用dao删除redis中对应的hash中key为分类id的项
	 */
	@Override
	public TaotaoResult syncContent(long contentCid) {
		try {
			jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, contentCid + "");
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok();
	}

}
