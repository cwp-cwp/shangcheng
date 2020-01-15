package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.pojo.TreeNode;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;

/**
 * 内容分类管理
 * 
 * @author 叔公
 * 
 */
@Service // service 层
public class ContentCategoryServiceImpl implements ContentCategoryService {

	/**
	 * 自动注入内容分类管理接口的动态代理实现类对象
	 */
	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;

	/**
	 * 获取内容分类的节点数据
	 */
	@Override
	public List<TreeNode> getCategoryList(long parentId) {
		// 根据parentid查询节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbContentCategory> list = contentCategoryMapper
				.selectByExample(example);
		// 创建 EasyUI 的异步 Tree 节点数据集合
		List<TreeNode> resultList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			// 创建一个节点
			TreeNode node = new TreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
			// 添加节点到集合中
			resultList.add(node);
		}
		return resultList;
	}

	/**
	 * 添加内容分类节点
	 */
	@Override
	public TaotaoResult insertContentCategory(long parentId, String name) {
		// 创建一个pojo
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setIsParent(false);
		// '状态。可选值:1(正常),2(删除)',
		contentCategory.setStatus(1);
		contentCategory.setParentId(parentId);
		contentCategory.setSortOrder(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		// 添加记录
		contentCategoryMapper.insert(contentCategory);
		// 查看父节点的isParent列是否为true，如果不是true改成true
		TbContentCategory parentCat = contentCategoryMapper
				.selectByPrimaryKey(parentId);
		// 判断是否为true
		if (!parentCat.getIsParent()) {
			parentCat.setIsParent(true);
			// 更新父节点
			contentCategoryMapper.updateByPrimaryKey(parentCat);
		}
		// 返回结果
		return TaotaoResult.ok(contentCategory);
	}

}
