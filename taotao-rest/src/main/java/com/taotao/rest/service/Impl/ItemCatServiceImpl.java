package com.taotao.rest.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

/**
 * 商品分类服务
 * 
 * @author 叔公
 * 
 */
@Service // service层
public class ItemCatServiceImpl implements ItemCatService {

	/**
	 * 自动注入商品分类接口的动态代理实现类对象
	 */
	@Autowired
	private TbItemCatMapper itemCatMapper;

	/**
	 * 获取商品分类
	 */
	@Override
	public CatResult getItemCatList() {
		CatResult catResult = new CatResult();
		// 查询分类列表
		catResult.setData(getCatList(0));
		return catResult;
	}

	/**
	 * 查询分类列表
	 * @param parentId 父节点id
	 * @return
	 */
	private List<?> getCatList(long parentId) {
		// 创建查询条件
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		// 返回值list
		List resultList = new ArrayList<>();
		// 向list中添加节点
		int count = 0;
		for (TbItemCat tbItemCat : list) {
			// 判断是否为父节点
			if (tbItemCat.getIsParent()) {
				CatNode catNode = new CatNode();
				if (parentId == 0) {
					catNode.setName("<a href='/products/" + tbItemCat.getId()
							+ ".html'>" + tbItemCat.getName() + "</a>");
				} else {
					catNode.setName(tbItemCat.getName());
				}
				catNode.setUrl("/products/" + tbItemCat.getId() + ".html");
				catNode.setItem(getCatList(tbItemCat.getId()));

				resultList.add(catNode);
				
				//页面装不下了，只加载14个父节点
				count++;
				if(tbItemCat.getIsParent() && count >= 14){
					break;
				}
				// 如果是叶子节点
			} else {
				resultList.add("/products/" + tbItemCat.getId() + ".html|"
						+ tbItemCat.getName());
			}
		}
		return resultList;
	}

}
