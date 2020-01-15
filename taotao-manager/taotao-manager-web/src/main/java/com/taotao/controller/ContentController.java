package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;

/**
 * 内容管理前端控制器
 * @author 叔公
 *
 */
@Controller // 前端控制器
@RequestMapping("/content")
public class ContentController {

	/**
	 * 自动注入内容管理接口的动态代理实现类对象
	 */
	@Autowired
	private ContentService contentService;
	
	/**
	 * 接收表单中的内容，使用pojo接收。要求pojo的属性要和表单中的name一致
	 * @param content 表单提交的内容
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody // 返回 json 格式的数据
	public TaotaoResult insertContent(TbContent content) {
		TaotaoResult result = contentService.insertContent(content);
		return result;
	}

}
