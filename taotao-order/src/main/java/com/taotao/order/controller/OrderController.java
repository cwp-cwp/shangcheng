package com.taotao.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.order.pojo.Order;
import com.taotao.order.service.OrderService;

/**
 * 订单管理前端控制器
 * 
 * @author 叔公
 * 
 */
@Controller
public class OrderController {

	/**
	 * 自动注入订单管理接口的动态代理实现类对象
	 */
	@Autowired
	private OrderService orderService;

	/**
	 * 处理页面传递过来的创建订单的请求
	 * 
	 * 请求URL: http://127.0.0.1:8085/order/create
	 * 
	 * @param order
	 *            订单信息对象
	 * @RequestBody Order order 接收页面传递的 json 格式的数据
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	// 返回 json 格式数据
	public TaotaoResult createOrder(@RequestBody Order order) {
		try {
			TaotaoResult result = orderService.createOrder(order,
					order.getOrderItems(), order.getOrderShipping());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

}
