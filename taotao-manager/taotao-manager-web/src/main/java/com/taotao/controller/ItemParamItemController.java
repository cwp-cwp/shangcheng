package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.service.ItemParamItemService;

/**
 * 展示商品规格参数的前端控制器
 * @author 叔公
 *
 */
@Controller //前端控制器
public class ItemParamItemController {
	
	/**
	 * 自动注入展示商品规格参数接口的动态代理实现类对象
	 */
	@Autowired
	private ItemParamItemService itemParamItemService;
	
	/**
	 * 展示商品规格参数
	 * @param itemId 商品 id
	 * @param model 模型数据
	 * @return
	 */
	@RequestMapping("/showitem/{itemId}") // 127.0.0.1:8080/showitem/154865206551974
	public String showItemParam(@PathVariable Long itemId, Model model) {
		String itemParams = itemParamItemService.getItemParamByItemId(itemId);
		model.addAttribute("itemParam", itemParams);
		return "item";
	}

}
