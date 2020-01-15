package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * 缓存同步服务接口
 * @author 叔公
 *
 */
public interface RedisService {
	
	/**
	 * 接收内容分类id，调用dao删除redis中对应的hash中key为分类id的项
	 * @param contentCid 内容分类id
	 * @return TaotaoResult
	 */
	TaotaoResult syncContent(long contentCid);
}
