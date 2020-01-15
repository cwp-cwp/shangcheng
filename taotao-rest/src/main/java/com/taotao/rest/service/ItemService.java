package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * 商品信息服务接口
 * 
 * @author 叔公
 * 
 */
public interface ItemService {

	/**
	 * 根据商品 id 查询商品信息
	 * 
	 * @param itemId
	 *            商品id
	 * @return 返回商品信息实体类对象
	 */
	TaotaoResult getItemBaseInfo(long itemId);

	/**
	 * 根据商品id查询商品描述
	 * 
	 * @param itemId
	 *            商品id
	 * @return 返回商品描述的pojo
	 */
	TaotaoResult getItemDesc(long itemId);

	/**
	 * 根据商品id查询商品规格参数
	 * 
	 * @param itemId
	 *            商品id
	 * @return 返回商品规格参数的pojo
	 */
	TaotaoResult getItemParam(long itemId);
}
