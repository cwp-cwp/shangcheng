package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;

/**
 * 商品分类业务处理
 * 
 * @author 叔公
 * 
 */
@Service // 表明service层
public class ItemCatServiceImpl implements ItemCatService {

	/**
	 * 自动注入商品分类接口的动态代理实现类对象
	 */
	@Autowired
	private TbItemCatMapper itemCatMapper;

	/**
	 * 根据 parentId 查询商品分类
	 */
	@Override
	public List<TreeNode> getItemCatList(long parentId) {
		// 根据parentId查询分类列表
		TbItemCatExample example = new TbItemCatExample();
		// 设置查询条件
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		// 分类列表转换成TreeNode的列表
		List<TreeNode> resultList = new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			// 创建一个TreeNode对象
			TreeNode node = new TreeNode(tbItemCat.getId(),
					tbItemCat.getName(), tbItemCat.getIsParent() ? "closed"
							: "open");
			resultList.add(node);
		}
		//返回节点集合
		return resultList;
	}

}
