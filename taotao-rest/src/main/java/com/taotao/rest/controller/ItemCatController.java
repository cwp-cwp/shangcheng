package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.JsonUtils;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

/**
 * 商品分类前端控制器
 * URL: http://127.0.0.1:8081/rest/itemcat/list
 * @author 叔公
 * 
 */
@Controller // 前端控制器
public class ItemCatController {

	/**
	 * 自动注入 ItemCatService 接口的动态代理实现类对象
	 */
	@Autowired
	private ItemCatService itemCatService;

	/**
	 * 接收页面传递过来的参数。参数就是方法的名称。
	 * 返回一个json数据，需要把json数据包装成一句js代码。返回一个字符串
	 * 
	 * @param callback 页面传递过来的参数(就是js的方法名)
	 * @return 返回一句 js 代码(js代码包装了一个json数据)
	 */
	@RequestMapping(value = "/itemcat/list", produces = MediaType.APPLICATION_JSON_VALUE
			+ ";charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback) {
		CatResult catResult = itemCatService.getItemCatList();
		// 把pojo转换成字符串
		String json = JsonUtils.objectToJson(catResult);
		// 拼装返回值
		String result = callback + "(" + json + ");";
		return result;
	}

	/**
	 * 方式 二 (spring 4.1之后的版本)
	 *  接收页面传递过来的参数。参数就是方法的名称。
	 * 返回一个json数据，需要把json数据包装成一句js代码。返回一个字符串
	 * 
	 * @param callback 页面传递过来的参数(就是js的方法名)
	 * @return 返回一句 js 代码(js代码包装了一个json数据)
	 */
//	@RequestMapping("/itemcat/list")
//	@ResponseBody
//	public Object getItemCatList2(String callback) {
//		CatResult catResult = itemCatService.getItemCatList();
//		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
//		mappingJacksonValue.setJsonpFunction(callback);
//		return mappingJacksonValue;
//	}

}
