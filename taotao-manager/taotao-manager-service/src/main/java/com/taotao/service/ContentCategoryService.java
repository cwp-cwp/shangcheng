package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.pojo.TreeNode;

/**
 * 内容分类管理接口
 * @author 叔公
 *
 */
public interface ContentCategoryService {

	/**
	 * 获取内容分类
	 * @param parentId 分类的父节点id
	 * @return 返回 EasyUI 的异步 Tree 的数据
	 */
	List<TreeNode> getCategoryList(long parentId);
	
	/**
	 * 添加内容分类节点
	 * @param parentId 父节点id
	 * @param name 当前节点的名称
	 * @return 
	 */
	TaotaoResult insertContentCategory(long parentId, String name);
}
