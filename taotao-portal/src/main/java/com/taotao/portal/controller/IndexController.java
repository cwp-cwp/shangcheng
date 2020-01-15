package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.portal.service.ContentService;

/**
 * 首页展示的前端控制器
 * @author 叔公
 *
 */
@Controller //前端控制器
public class IndexController {

	/**
	 * 自动注入 ContentService 接口的动态代理实现类对象
	 */
	@Autowired
	private ContentService contentService;
	
	/**
	 * 首页展示
	 * @return
	 */
	@RequestMapping("/index")
	public String showIndex(Model model) {
		String adJson = contentService.getContentList();
		model.addAttribute("ad1", adJson);
		
		return "index";
	}

}
