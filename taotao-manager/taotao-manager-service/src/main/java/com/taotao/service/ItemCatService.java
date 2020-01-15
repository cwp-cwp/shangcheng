package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.TreeNode;

/**
 * 商品分类管理
 * @author 叔公
 *
 */
public interface ItemCatService {
	
	/**
	 * 根据 parentId 查询商品分类
	 * @param parentId 商品的分类id
	 * @return 返回节点集合
	 */
	List<TreeNode> getItemCatList(long parentId);
}
