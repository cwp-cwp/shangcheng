package com.taotao.rest.service;

import com.taotao.rest.pojo.CatResult;

/**
 * 商品分类接口
 * @author 叔公
 *
 */
public interface ItemCatService {

	/**
	 * 获取分类 list
	 * @return 返回分类 list
	 */
	 CatResult getItemCatList();
}
