package com.taotao.portal.service;

import com.taotao.portal.pojo.Order;

/**
 * 提交订单的接口
 * 
 * @author 叔公
 * 
 */
public interface OrderService {

	/**
	 * 提交订单
	 * 
	 * @param order
	 *            接收的订单信息对象
	 * @return 返回订单号
	 */
	String createOrder(Order order);
}
