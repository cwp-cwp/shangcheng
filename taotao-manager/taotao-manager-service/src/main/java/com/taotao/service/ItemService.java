package com.taotao.service;

import com.taotao.common.pojo.EasyUIDateGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

/**
 * 商品管理的 Service 接口
 * @author 叔公
 *
 */
public interface ItemService {
	
	/**
	 * 根据商品id查询商品信息
	 * @param itemId 商品id
	 * @return 返回商品信息
	 */
	TbItem getItemById(long itemId);
	
	/**
	 * 商品列表查询
	 * @param page 页码
	 * @param rows 每页的行数
	 * @return 返回一个 EasyUIDateGrid 支持的数据格式
	 */
	EasyUIDateGridResult getItemList(int page, int rows);
	
	/**
	 * 添加商品
	 * @param item 商品实体
	 * @param desc 商品描述
	 * @param itemParam 商品规格参数
	 * @return 返回自定义的结果
	 * @throws Exception 
	 */
	TaotaoResult createItem(TbItem item, String desc, String itemParam) throws Exception;
}
