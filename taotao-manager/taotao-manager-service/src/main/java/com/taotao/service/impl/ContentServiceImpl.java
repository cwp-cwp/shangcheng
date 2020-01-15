package com.taotao.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;

/**
 * 内容管理
 * @author 叔公
 *
 */
@Service // service 层
public class ContentServiceImpl implements ContentService {

	/**
	 * 自动注入 TbContentMapper 接口的动态代理实现类对象
	 */
	@Autowired
	private TbContentMapper contentMapper;
	
	/**
	 * 读取配置文件中的值,服务层的基础 url
	 */
	@Value("${REST_BASE_URL}")// http://127.0.0.1:8081/rest
	private String REST_BASE_URL;
	@Value("${REST_CONTENT_SYNC_URL}") // /cache/sync/content/89
	private String REST_CONTENT_SYNC_URL;
	
	/**
	 * 向 tb_content 表中插入内容 
	 */
	@Override
	public TaotaoResult insertContent(TbContent content) {
		//补全pojo内容
		content.setCreated(new Date());
		content.setUpdated(new Date());
		//插入
		contentMapper.insert(content);
		
		//添加缓存同步
		try {
			HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return TaotaoResult.ok();
	}

}
