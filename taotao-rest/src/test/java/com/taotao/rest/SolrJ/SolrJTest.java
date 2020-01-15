package com.taotao.rest.SolrJ;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * 测试 Solr 的增删改查
 * @author 叔公
 *
 */
public class SolrJTest {
	
	/**
	 * 添加索引库(id不变，再次添加就是覆盖原来的，即更新)
	 * @throws Exception
	 */
	@Test
	public void addDocument() throws Exception {
		//创建一连接
		SolrServer solrServer = new HttpSolrServer("http://192.168.245.131:8080/solr");
		//创建一个文档对象
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "test001");
		document.addField("item_title", "测试商品2222");
		document.addField("item_price", 54321);
		//把文档对象写入索引库
		solrServer.add(document);
		//提交
		solrServer.commit();
	}
	
	/**
	 * 删除索引库
	 * @throws Exception
	 */
	@Test
	public void deleteDocument() throws Exception {
		//创建一连接
		SolrServer solrServer = new HttpSolrServer("http://192.168.245.131:8080/solr");
		//根据id删除
		//solrServer.deleteById("test001");
		//根据查询条件删除
		solrServer.deleteByQuery("*:*");//删除全部
		solrServer.commit();
	}

}
