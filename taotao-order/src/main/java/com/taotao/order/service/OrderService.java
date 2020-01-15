package com.taotao.order.service;

import java.util.List;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;

/**
 * 订单管理接口
 * 
 * @author 叔公
 * 
 */
public interface OrderService {

	/**
	 * 创建订单
	 * 
	 * @param order
	 *            对应的订单表
	 * @param itemList
	 *            订单明细表对应的商品列表
	 * @param orderShipping
	 *            对应的物流表
	 * @return
	 */
	TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList,
			TbOrderShipping orderShipping);
}
