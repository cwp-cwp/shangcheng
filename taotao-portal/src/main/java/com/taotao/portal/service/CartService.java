package com.taotao.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;

/**
 * 购物车管理接口
 * 
 * @author 叔公
 * 
 */
public interface CartService {

	/**
	 * 添加购物车商品
	 * 
	 * @param itemId
	 *            商品id
	 * @param num
	 *            商品数量
	 * @param request
	 * @param response
	 * @return
	 */
	TaotaoResult addCartItem(long itemId, int num, HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * 从cookie中把购物车列表取出来
	 * 
	 * @param request
	 * @param response
	 * @return 返回购物车中的商品列表。
	 */
	List<CartItem> getCartItemList(HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * 删除购物车商品
	 * @param itemId 商品id
	 * @param request
	 * @param response
	 * @return
	 */
	TaotaoResult deleteCartItem(long itemId, HttpServletRequest request,
			HttpServletResponse response);
}
