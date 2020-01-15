package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TreeNode;
import com.taotao.service.ItemCatService;

/**
 * 商品分类管理的前端控制器
 * @author 叔公
 *
 */
@Controller //前端控制器
@RequestMapping("/item/cat")
public class ItemCatController {

	/**
	 * 自动注入商品分类管理接口的动态代理实现类对象
	 */
	@Autowired
	private ItemCatService itemCatService;
	
	/**
	 * 获取商品分类集合
	 * @param parentId 商品分类的父id
	 * @return 返回商品集合
	 */
	@RequestMapping("/list")
	@ResponseBody //返回 json 数据
	public List<TreeNode> getItemCatList(@RequestParam(value="id", defaultValue="0")Long parentId) {
		List<TreeNode> list = itemCatService.getItemCatList(parentId);
		return list;
	}

}
