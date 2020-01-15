package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转的控制器
 * @author 叔公
 *
 */
@Controller//前端控制器
public class PageController {
	
	/**
	 * 打开首页
	 * @return
	 */
	@RequestMapping("/") // 127.0.0.1:8080
	public String showIndex(){
		return "index";
	}
	
	/**
	 * 打开其他页面
	 * @param page
	 * @return
	 */
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page){
		return page;
	}
}
