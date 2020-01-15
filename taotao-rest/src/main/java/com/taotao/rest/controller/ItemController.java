package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.service.ItemService;

/**
 * 商品基本信息前端控制器
 * @author 叔公
 *
 */
@Controller
@RequestMapping("/item")
public class ItemController {

	/**
	 * 自动注入商品信息服务接口的动态代理实现类对象
	 */
	@Autowired
	private ItemService itemService;
	
	/**
	 * 获取商品基本信息
	 * 接收商品id调用Service查询商品信息，返回商品对象，使用TaotaoResult包装
	 * 
	 * Url：http://127.0.0.1:8081/rest/item/info/{itemId}
	 * @param itemId 商品id
	 * @return
	 */
	@RequestMapping("/info/{itemId}")
	@ResponseBody // 返回 json 字符串
	public TaotaoResult getItemBaseInfo(@PathVariable Long itemId) {
		TaotaoResult result = itemService.getItemBaseInfo(itemId);
		return result;
	}
	
	/**
	 * 接收商品id参数，调用Service查询商品描述。返回TaotaoResult.
	 * 
	 * Url：http://127.0.0.1:8081/rest/item/desc/{itemId}
	 * @param itemId 商品 id
	 * @return
	 */
	@RequestMapping("/desc/{itemId}")
	@ResponseBody //返回 json 字符串
	public TaotaoResult getItemDesc(@PathVariable Long itemId) {
		TaotaoResult result = itemService.getItemDesc(itemId);
		return result;
	}

	/**
	 * 接收商品id参数，调用Service查询商品规格参数。返回TaotaoResult.
	 * 
	 * Url：http://127.0.0.1:8081/rest/item/param/{itemId}
	 * @param itemId 商品 id
	 * @return
	 */
	@RequestMapping("/param/{itemId}")
	@ResponseBody //返回 json 字符串
	public TaotaoResult getItemParam(@PathVariable Long itemId) {
		TaotaoResult result = itemService.getItemParam(itemId);
		return result;
	}
}
