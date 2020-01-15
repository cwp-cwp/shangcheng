package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;

/**
 * 添加商品规格参数的前端控制器
 * 
 * @author 叔公
 * 
 */
@Controller // 前端控制器
@RequestMapping("/item/param")
public class ItemParamController {

	/**
	 * 自动注入商品规格参数接口的动态代理实现类对象
	 */
	@Autowired
	private ItemParamService itemParamService;

	/**
	 * 获取商品规格参数
	 * 
	 * @param itemCatId
	 *            商品分类id
	 * @return
	 */
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody // 返回 json 数据
	public TaotaoResult getItemParamByCid(@PathVariable Long itemCatId) {
		TaotaoResult result = itemParamService.getItemParamByCid(itemCatId);
		return result;
	}

	/**
	 * 插入商品规格参数
	 * @param cid 商品规格id
	 * @param paramData 规格参数
	 * @return
	 */
	@RequestMapping("/save/{cid}")
	@ResponseBody // 返回 json 数据
	public TaotaoResult insertItemParam(@PathVariable Long cid, String paramData) {
		// 创建pojo对象
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		TaotaoResult result = itemParamService.insertItemParam(itemParam);
		return result;
	}

}
