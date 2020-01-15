package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDateGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;

/**
 * 商品管理 Service
 * @author 叔公
 *
 */
@Service // 表明Service层
public class ItemServiceImpl implements ItemService {

	/**
	 * 注入 TbItemMapper(商品信息) 代理实现类对象
	 */
	@Autowired
	private TbItemMapper itemMapper;
	
	/**
	 * 自动注入 TbItemDescMapper(商品描述) 代理实现类对象
	 */
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	/**
	 * 自动注入 TbItemParamItemMapper(商品规格参数) 动态代理实现类对象
	 */
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	/**
	 * 根据商品id查询商品信息
	 */
	@Override
	public TbItem getItemById(long itemId) {
		//添加查询条件
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		//查询
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			TbItem item = list.get(0);
			return item;
		}
		return null;
	}

	/**
	 * 商品列表查询(分页处理)
	 */
	@Override
	public EasyUIDateGridResult getItemList(int page, int rows) {
		//查询商品列表
		TbItemExample example = new TbItemExample();
		//分页处理
		PageHelper.startPage(page, rows);
		//条件查询商品
		List<TbItem> items = itemMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDateGridResult result = new EasyUIDateGridResult();
		result.setRows(items);
		//取记录总条数
		PageInfo<TbItem> pageInfo = new PageInfo<>(items);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	/**
	 * 添加商品
	 * @throws Exception 
	 */
	@Override
	public TaotaoResult createItem(TbItem item, String desc, String itemParam) throws Exception {
		//item 信息补全
		//生成商品id
		long itemId = IDUtils.genItemId();
		item.setId(itemId);
		//商品状态    1-正常  2-下架  3-删除
		item.setStatus((byte) 1);
		item.setCreated(new Date());//创建时间
		item.setUpdated(new Date());//更新时间
		//插入数据库
		itemMapper.insert(item);
		//添加商品描述
		if(this.insertItemDesc(itemId, desc).getStatus() != 200){
			throw new Exception();
		}
		//添加商品规格参数
		if(this.insertItemParamItem(itemId, itemParam).getStatus() != 200){
			throw new Exception();
		}
		return TaotaoResult.ok();
	}
	/**
	 * 商品描述添加方法
	 * @param itemId 商品id
	 * @param desc 商品描述
	 * @return
	 */
	private TaotaoResult insertItemDesc(Long itemId, String desc) {
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);
		return TaotaoResult.ok();
	}
	
	/**
	 * 商品规格参数添加方法
	 * @param itemId 商品id
	 * @param itemParam 商品参数
	 * @return
	 */
	private TaotaoResult insertItemParamItem(Long itemId, String itemParam) {
		//创建一个pojo
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		//向表中插入数据
		itemParamItemMapper.insert(itemParamItem);
		
		return TaotaoResult.ok();
		
	}


}
