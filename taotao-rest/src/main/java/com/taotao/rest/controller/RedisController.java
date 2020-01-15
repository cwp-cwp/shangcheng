package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.service.RedisService;

/**
 * 缓存同步前端控制器
 * 
 * 访问路径 http://127.0.0.1:8081/rest/cache/sync/content/89
 * @author 叔公
 *
 */
@Controller //前端控制器
@RequestMapping("/cache/sync")
public class RedisController {
	
	/**
	 * 自动注入缓存同步服务接口的动态代理实现类对象
	 */
	@Autowired
	private RedisService redisService;
	
	/**
	 * 接收内容分类id，调用service同步缓存
	 * @param contentCid 内容分类id
	 * @return
	 */
	@RequestMapping("/content/{contentCid}")
	@ResponseBody // 返回json 数据格式
	public TaotaoResult contentCacheSync(@PathVariable Long contentCid) {
		TaotaoResult result = redisService.syncContent(contentCid);
		return result;
	}

}
