package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;

/**
 * 购物车前端控制器
 * 
 * @author 叔公
 * 
 */
@Controller
@RequestMapping("/cart")
public class CartController {

	/**
	 * 自动注入购物车管理接口的动态代理实现类对象
	 */
	@Autowired
	private CartService cartService;

	/**
	 * 添加购物车
	 * 
	 * 访问URL: http://localhost:8082/cart/add/154849675425163.html
	 * 
	 * @param itemId
	 *            商品id
	 * @param num
	 *            商品数量
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/add/{itemId}")
	public String addCartItem(@PathVariable Long itemId,
			@RequestParam(defaultValue = "1") Integer num,
			HttpServletRequest request, HttpServletResponse response) {
		TaotaoResult result = cartService.addCartItem(itemId, num, request,
				response);
		return "redirect:/cart/success.html";
	}

	@RequestMapping("success")
	public String showSuccess() {
		return "cartSuccess";
	}

	/**
	 * 调用Service取购物车商品列表，把购物车商品传递给jsp。在购物车页面展示商品列表
	 * 请求的url：http://localhost:8082/cart/cart.html
	 * 
	 * @param request
	 * @param response
	 * @param model
	 *            传递数据给页面的model
	 * @return
	 */
	@RequestMapping("/cart")
	public String showCart(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<CartItem> list = cartService.getCartItemList(request, response);
		model.addAttribute("cartList", list);
		return "cart";
	}

	/**
	 * 删除购物车商品， 接收商品id，调用Service删除购物车商品，返回购物车页面。
	 * 
	 * 请求的url：/cart/delete/${cart.id}.html
	 * 
	 * @param itemId
	 *            商品id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/delete/{itemId}")
	public String deleteCartItem(@PathVariable Long itemId,
			HttpServletRequest request, HttpServletResponse response) {
		cartService.deleteCartItem(itemId, request, response);
		return "redirect:/cart/cart.html";
	}

}
