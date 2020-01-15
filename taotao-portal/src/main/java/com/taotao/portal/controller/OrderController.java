package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.pojo.TbUser;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.service.CartService;
import com.taotao.portal.service.OrderService;

/**
 * 订单管理前端控制器
 * 
 * @author 叔公
 * 
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	/**
	 * 自动注入购物车管理接口的动态代理实现类对象
	 */
	@Autowired
	private CartService cartService;

	/**
	 * 自动注入提交订单的接口
	 */
	@Autowired
	private OrderService orderService;

	/**
	 * 处理请求，从购物车中获取商品列表，跳转至订单确认界面
	 * 
	 * url：/order/order-cart.html
	 * 
	 * @param request
	 * @param response
	 * @param model
	 *            传递数据到界面的model
	 * @return
	 */
	@RequestMapping("/order-cart")
	public String showOrderCart(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 取购物车商品列表
		List<CartItem> list = cartService.getCartItemList(request, response);
		// 传递给页面
		model.addAttribute("cartList", list);

		return "order-cart";
	}

	/**
	 * 提交订单
	 * 
	 * @param order
	 *            接收页面提交的订单信息的对象
	 * @param model
	 *            传递数据给页面的model
	 * @return
	 */
	@RequestMapping("/create")
	public String createOrder(Order order, Model model, HttpServletRequest request) {
		try {
			//从 request 中获取用户信息
			TbUser user = (TbUser) request.getAttribute("user");
			//在 order 中补全用户信息
			order.setUserId(user.getId());
			order.setBuyerNick(user.getUsername());
			//调用 Service 层
			String orderId = orderService.createOrder(order);
			model.addAttribute("orderId", orderId);
			model.addAttribute("payment", order.getPayment());
			model.addAttribute("date",
					new DateTime().plusDays(3).toString("yyyy-MM-dd"));
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "订单确认出错，请稍后再试...");
			return "error/exception";
		}
	}

}
