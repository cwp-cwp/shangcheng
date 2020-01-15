package com.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.service.ItemService;

/**
 * solr 索引库维护前端控制器
 * @author 叔公
 * 
 * URL: http://127.0.0.1:8083/search/manager/importall
 */
@Controller
@RequestMapping("/manager")
public class ItemController {
	
	/**
	 * 自动注入 ItemService 接口的动态代理实现类对象
	 */
	@Autowired
	private ItemService itemService;

	/**
	 * 导入商品数据到索引库
	 */
	@RequestMapping("/importall")
	@ResponseBody // 返回 json 数据
	public TaotaoResult importAllItems() {
		TaotaoResult result = itemService.importAllItems();
		return result;
	}

}
