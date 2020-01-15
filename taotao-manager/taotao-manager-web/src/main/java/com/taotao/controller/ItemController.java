package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDateGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
 
/**
 * 商品信息请求处理器
 * @author 叔公
 *
 */
@Controller //表明请求处理器(控制器)
public class ItemController {
	
	/**
	 * 注入 ItemService 的代理实现类对象
	 */
	@Autowired 
	private ItemService itemService;
	
	/**
	 * 根据请求的 itemId 返回一个商品信息的 json 字符串
	 * @param itemId
	 * @return
	 */
	@RequestMapping("item/{itemId}")//127.0.0.1:8080/item/536563
	@ResponseBody //直接返回一个json数据
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}

	/**
	 * 接收页面传递过来的参数page、rows。返回json格式的数据
	 * @param page 页码
	 * @param rows 每页行数
	 * @return 返回一个 EasyUIDateGrid 支持的数据格式
	 */
	@RequestMapping("/item/list")  
	@ResponseBody //返回一个json数据
	public EasyUIDateGridResult getItemList(Integer page, Integer rows){
		EasyUIDateGridResult dateGridResult = itemService.getItemList(page, rows);
		return dateGridResult;
	}
	
	/**
	 * 添加商品
	 * @param item 商品信息
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody //返回 json 数据
	public TaotaoResult createItem(TbItem item, String desc, String itemParams) throws Exception{
		return itemService.createItem(item, desc, itemParams);
	}
}
