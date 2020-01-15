package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;

/**
 * 商品规格参数模板管理接口
 * @author 叔公
 *
 */
public interface ItemParamService {
	
	/**
	 * 查询商品规格参数表
	 * @param cid
	 * @return
	 */
	TaotaoResult getItemParamByCid(long cid);
	
	/**
	 * 插入商品规格参数
	 * @param itemParam
	 * @return
	 */
	TaotaoResult insertItemParam(TbItemParam itemParam);
}
