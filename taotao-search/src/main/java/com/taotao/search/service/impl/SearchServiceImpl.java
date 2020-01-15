package com.taotao.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;

/**
 * solr 搜索查询服务
 * 
 * @author 叔公
 * 
 */
@Service
public class SearchServiceImpl implements SearchService {

	/**
	 * 自动注入 solr 搜索的dao接口的动态代理实现类对象
	 */
	@Autowired
	private SearchDao searchDao;

	/**
	 * 接收查询条件，查询条件及分页条件（page、rows），指定查询条件、分页条件、默认搜索域、高亮显示。
	 * 调用dao层执行查询 得到查询结果计算总页数，返回SearchResult对象
	 */
	@Override
	public SearchResult search(String queryString, int page, int rows)
			throws Exception {
		// 创建查询对象
		SolrQuery query = new SolrQuery();
		// 设置查询条件
		query.setQuery(queryString);
		// 设置分页
		query.setStart((page - 1) * rows);
		query.setRows(rows);
		// 设置默认搜素域
		query.set("df", "item_keywords");
		// 设置高亮显示
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");
		// 执行查询
		SearchResult searchResult = searchDao.search(query);
		// 计算查询结果总页数
		long recordCount = searchResult.getRecordCount();
		long pageCount = recordCount / rows;
		if (recordCount % rows > 0) {
			pageCount++;
		}
		searchResult.setPageCount(pageCount);
		searchResult.setCurPage(page);

		return searchResult;
	}

}
