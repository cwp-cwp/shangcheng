package com.taotao.rest.SolrJ;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
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
		document.addField("item_title", "测试商品2223332");
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

	/**
	 * 测试查询 solr
	 * @throws Exception
	 */
	@Test
	public void queryDocument() throws Exception {
		SolrServer solrServer = new HttpSolrServer("http://192.168.245.131:8080/solr");
		//创建一个查询对象
		SolrQuery query = new SolrQuery();
		//设置查询条件
		query.setQuery("*:*");
		query.setStart(20);
		query.setRows(50);
		//执行查询
		QueryResponse response = solrServer.query(query);
		//取查询结果
		SolrDocumentList solrDocumentList = response.getResults();
		System.out.println("共查询到记录：" + solrDocumentList.getNumFound());
		for (SolrDocument solrDocument : solrDocumentList) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("item_price"));
			System.out.println(solrDocument.get("item_image"));
			
		}
	}

}
