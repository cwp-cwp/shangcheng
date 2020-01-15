package com.taotao.portal.service.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.service.OrderService;

/**
 * 提交订单 Service
 * 
 * @author 叔公
 * 
 */
@Service
public class OrderServiceImpl implements OrderService {

	/**
	 * 订单服务系统的基础 url, http://127.0.0.1:8085/order
	 */
	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	
	/**
	 * 创建订单服务的 url, /create
	 */
	@Value("${ORDER_CREATE_URL}")
	private String ORDER_CREATE_URL;

	/**
	 * 提交订单
	 */
	@Override
	public String createOrder(Order order) {
		// 调用taotao-order的服务提交订单。
		String json = HttpClientUtil.doPostJson(ORDER_BASE_URL
				+ ORDER_CREATE_URL, JsonUtils.objectToJson(order));
		// 把json转换成taotaoResult
		TaotaoResult taotaoResult = TaotaoResult.format(json);
		if (taotaoResult.getStatus() == 200) {
			//Object orderId = (Long) taotaoResult.getData();
			//return orderId.toString();
			return taotaoResult.getData().toString();
		}
		return "";
	}

}
