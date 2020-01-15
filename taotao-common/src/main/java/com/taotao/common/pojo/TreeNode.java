package com.taotao.common.pojo;

/**
 * 保存商品分类树节点的实体类
 * 该树节点的数据类型是 json 类型
 * [{    
 *   "id": 1,    //当前节点的id
 *   "text": "Node 1",    //节点显示的名称
 *   "state": "closed"    //节点的状态，如果是closed就是一个文件夹形式，
 *                // 当打开时还会 做一次请求。如果是open就显示为叶子节点。
 * },{    
 *   "id": 2,    
 *   "text": "Node 2",    
 *   "state": "closed"   
 * }]
 * @author 叔公
 *
 */
public class TreeNode {

	/**
	 * 当前节点的id
	 */
	private long id;
	/**
	 * 节点显示的名称
	 */
	private String text;
	/**
	 * 节点的状态，如果是closed就是一个文件夹形式,open就显示为叶子节点。
	 */
	private String state;
	
	public TreeNode() {
		super();
	}
	public TreeNode(long id, String text, String state) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
