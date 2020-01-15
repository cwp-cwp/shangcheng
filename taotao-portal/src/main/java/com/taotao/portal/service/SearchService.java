package com.taotao.portal.service;

import com.taotao.portal.pojo.SearchResult;

/**
 * 查询 search 服务层的接口
 * @author 叔公
 *
 */
public interface SearchService {

	/**
	 * 查询 search 服务
	 * @param queryString 查询条件
	 * @param page 查询的页码
	 * @return 返回封装的查询结果实体类
	 */
	SearchResult search(String queryString, int page);
}
