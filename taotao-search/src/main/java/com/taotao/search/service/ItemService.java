package com.taotao.search.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * 导入数据到 solr 索引库的接口
 * @author 叔公
 *
 */
public interface ItemService {
	
	/**
	 * 导入所有数据
	 * @return
	 */
	TaotaoResult importAllItems();
}
