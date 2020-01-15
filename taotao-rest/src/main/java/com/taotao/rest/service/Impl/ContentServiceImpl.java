package com.taotao.rest.service.Impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.rest.JedisDao.JedisClient;
import com.taotao.rest.service.ContentService;

/**
 * 内容管理
 * 
 * @author 叔公
 * 
 */
@Service
public class ContentServiceImpl implements ContentService {

	/**
	 * 自动注入 TbContentMapper 接口的动态代理实现类对象
	 */
	@Autowired
	private TbContentMapper contentMapper;

	/**
	 * 自动注入 JedisClient 接口的动态代理实现类对象
	 */
	@Autowired
	private JedisClient jedisClient;

	/**
	 * 读取配置文件中的属性值(首页内容信息在redis中保存的key)
	 */
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;

	/**
	 * 接收内容分类id，根据分类id查询分类列表
	 */
	@Override
	public List<TbContent> getContentList(long contentCid) {
		// 从缓存中取内容
		try {
			String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY,
					contentCid + "");
			if (!StringUtils.isBlank(result)) {
				// 把字符串转换成list
				List<TbContent> resultList = JsonUtils.jsonToList(result,
						TbContent.class);
				return resultList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 根据内容分类id查询内容列表
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCid);
		// 执行查询
		List<TbContent> list = contentMapper.selectByExample(example);

		// 向缓存中添加内容
		try {
			// 把list转换成字符串
			String cacheString = JsonUtils.objectToJson(list);
			jedisClient.hset(INDEX_CONTENT_REDIS_KEY, contentCid + "",
					cacheString);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
