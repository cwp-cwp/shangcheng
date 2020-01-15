package com.taotao.service;

/**
 * 展示商品规格参数的接口
 * @author 叔公
 *
 */
public interface ItemParamItemService {
	
	/**
	 * 根据商品id展示商品规格参数
	 * @param itemId 商品id
	 * @return 返回参数字符串
	 */
	String getItemParamByItemId(Long itemId);
}
