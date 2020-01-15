package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.pojo.TreeNode;
import com.taotao.service.ContentCategoryService;

/**
 * 内容分类管理前端控制器
 * 
 * @author 叔公
 * 
 */
@Controller // 前端控制器
@RequestMapping("/content/category")
public class ContentCategoryController {

	/**
	 * 自动注入内容分类管理接口的动态代理实现类对象
	 */
	@Autowired
	private ContentCategoryService contentCategoryService;

	/**
	 * 获取内容分类
	 * 
	 * @param parentId
	 *            页面传递过来的分类的父节点id
	 * @return 返回 EasyUI 异步 Tree 的json 格式数据
	 */
	@RequestMapping("/list")
	@ResponseBody
	// 返回 json 格式数据
	public List<TreeNode> getContentCatList(
			@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		List<TreeNode> list = contentCategoryService.getCategoryList(parentId);
		return list;
	}

	/**
	 * 添加内容分类节点
	 * @param parentId 父节点id
	 * @param name 当前节点的名称
	 * @return
	 */
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult createContentCategory(Long parentId, String name) {
		TaotaoResult result = contentCategoryService.insertContentCategory(
				parentId, name);
		return result;
	}

}
