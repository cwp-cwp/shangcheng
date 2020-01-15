package com.taotao.portal.service;

import com.taotao.portal.pojo.ItemInfo;

/**
 * 查询商品基本信息接口
 * 
 * @author 叔公
 * 
 */
public interface ItemService {

	/**
	 * 根据商品id查询商品基本信息
	 * 
	 * @param itemId
	 *            商品id
	 * @return
	 */
	ItemInfo getItemById(Long itemId);

	/**
	 * 根据商品id查询商品描述
	 * 
	 * @param itemId
	 *            商品id
	 * @return
	 */
	String getItemDescById(Long itemId);

	/**
	 * 根据商品 id 获取商品规格参数
	 * 
	 * @param itemId
	 *            商品id
	 * @return
	 */
	String getItemParam(Long itemId);
}
