package com.taotao.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.taotao.search.pojo.SearchResult;

/**
 * solr 搜索的 dao 接口
 * @author 叔公
 *
 */
public interface SearchDao {

	/**
	 * solr 搜索
	 * @param query solr搜索条件
	 * @return 返回搜索结果
	 */
	SearchResult search(SolrQuery query) throws Exception;
}
