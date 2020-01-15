package com.taotao.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.search.mapper.ItemMapper;
import com.taotao.search.pojo.Item;
import com.taotao.search.service.ItemService;

/**
 * 导入数据到 solr 索引库
 * 
 * @author 叔公
 * 
 */
@Service
public class ItemServiceImpl implements ItemService {

	/**
	 * 自动注入 ItemMapper 接口的动态代理实现类对象
	 */
	@Autowired
	private ItemMapper itemMapper;

	/**
	 * 自动注入 SolrServer 接口的动态代理实现类对象
	 */
	@Autowired
	private SolrServer solrServer;

	/**
	 * 导入数据到 solr 索引库
	 */
	@Override
	public TaotaoResult importAllItems() {
		try {
			// 查询商品列表
			List<Item> list = itemMapper.getItemList();
			// 把商品信息写入索引库
			for (Item item : list) {
				// 创建一个SolrInputDocument对象
				SolrInputDocument document = new SolrInputDocument();
				document.setField("id", item.getId());
				document.setField("item_title", item.getTitle());
				document.setField("item_sell_point", item.getSell_point());
				document.setField("item_price", item.getPrice());
				document.setField("item_image", item.getImage());
				document.setField("item_category_name", item.getCategory_name());
				document.setField("item_desc", item.getItem_des());
				// 写入索引库
				solrServer.add(document);
			}
			// 提交修改
			solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok();
	}

}
