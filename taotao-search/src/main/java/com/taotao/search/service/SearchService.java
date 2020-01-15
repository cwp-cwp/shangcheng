package com.taotao.search.service;

import com.taotao.search.pojo.SearchResult;

/**
 * solr 搜索服务层
 * @author 叔公
 *
 */
public interface SearchService {
	
	/**
	 * solr 搜索
	 * @param queryString 搜索条件
	 * @param page 当前页数
	 * @param rows 每页的记录数
	 * @return
	 */
	SearchResult search(String queryString, int page, int rows) throws Exception;
}
