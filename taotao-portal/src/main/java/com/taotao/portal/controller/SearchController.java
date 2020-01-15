package com.taotao.portal.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;

/**
 * 搜索前端控制器
 *  接收请求的参数查询条件和页码。调用Service查询商品列表得到SearchResult对象。
 *   需要把 
 *   Query：回显的查询条件
 *   totalPages：总页数 
 *   itemList：商品列表 
 *   Page：当前页码 
 *   传递到页面。返回一个逻辑视图search字符串
 * 
 * @author 叔公
 * 
 */
@Controller
public class SearchController {
	
	/**
	 * 自动注入 SearchService 接口的动态代理实现类对象
	 */
	@Autowired
	private SearchService searchService;

	/**
	 * 调用服务层搜索
	 * @param queryString 查询条件
	 * @param page 当前页码
	 * @param model 向页面传递数据的 modle
	 * url: http://localhost:8082/search.html?q=冰箱
	 * @return
	 */
	@RequestMapping("/search")
	public String search(@RequestParam("q") String queryString,
			@RequestParam(defaultValue = "1") Integer page, Model model) {
		//解决乱码
		if (queryString != null) {
			try {
				queryString = new String(queryString.getBytes("iso8859-1"),
						"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		//查询
		SearchResult searchResult = searchService.search(queryString, page);
		// 向页面传递参数
		model.addAttribute("query", queryString);
		model.addAttribute("totalPages", searchResult.getPageCount());
		model.addAttribute("itemList", searchResult.getItemList());
		model.addAttribute("page", page);

		return "search";

	}

}
