package com.taotao.portal.service.Impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;

/**
 * 查询 search 服务
 * 
 * @author 叔公
 * 
 */
@Service
public class SearchServiceImpl implements SearchService {

	/**
	 * 读取配置文件中的属性值，search 服务层的基础 url
	 * http://127.0.0.1:8083/search/query
	 */
	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;

	/**
	 * 调用taotao-search的服务查询
	 */
	@Override
	public SearchResult search(String queryString, int page) {
		// 调用taotao-search的服务
		// 查询参数
		Map<String, String> param = new HashMap<>();
		param.put("q", queryString);
		param.put("page", page + "");
		try {
			// 调用服务
			String json = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
			// 把字符串转换成java对象
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json,
					SearchResult.class);
			if (taotaoResult.getStatus() == 200) {
				SearchResult result = (SearchResult) taotaoResult.getData();
				return result;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
