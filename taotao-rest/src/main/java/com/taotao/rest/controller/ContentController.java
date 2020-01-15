package com.taotao.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.pojo.TbContent;
import com.taotao.rest.service.ContentService;

/**
 * 内容管理
 * 
 * @author 叔公
 * 
 */
@Controller
@RequestMapping("/content")
public class ContentController {

	/**
	 * 自动注入内容管理接口的动态代理实现类对象
	 */
	@Autowired
	private ContentService contentService;

	/**
	 * 接收内容分类 id，根据分类 id 获取分类列表
	 * @param contentCategoryId 内容分类id
	 * @return
	 */
	@RequestMapping("/list/{contentCategoryId}")
	@ResponseBody // 返回 json 格式数据
	public TaotaoResult getContentList(@PathVariable Long contentCategoryId) {
		try {
			List<TbContent> list = contentService
					.getContentList(contentCategoryId);
			return TaotaoResult.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

}
