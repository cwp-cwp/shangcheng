package com.taotao.portal.service.Impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;

/**
 * 商品基本信息 Service
 * 
 * @author 叔公
 * 
 */
@Service
public class ItemServiceImpl implements ItemService {

	/**
	 * 读取配置文件的属性值 http://127.0.0.1:8081/rest
	 */
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;

	/**
	 * 读取配置文件的属性值 (商品基本信息url) /item/info/
	 */
	@Value("${ITME_INFO_URL}")
	private String ITME_INFO_URL;

	/**
	 * 读取配置文件的属性值 (商品描述url) /item/desc/
	 */
	@Value("${ITEM_DESC_URL}")
	private String ITEM_DESC_URL;

	/**
	 * 获取配置文件属性值 (商品规格参数 url) /item/param/
	 */
	@Value("${ITEM_PARAM_URL}")
	private String ITEM_PARAM_URL;

	/**
	 * 获取商品基本信息
	 */
	@Override
	public ItemInfo getItemById(Long itemId) {
		try {
			// 调用rest的服务查询商品基本信息
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITME_INFO_URL
					+ itemId);
			if (!StringUtils.isBlank(json)) {
				TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json,
						ItemInfo.class);
				if (taotaoResult.getStatus() == 200) {
					ItemInfo item = (ItemInfo) taotaoResult.getData();
					return item;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取商品描述
	 */
	@Override
	public String getItemDescById(Long itemId) {
		try {
			// 查询商品描述
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_DESC_URL
					+ itemId);
			// 转换成java对象
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json,
					TbItemDesc.class);
			if (taotaoResult.getStatus() == 200) {
				TbItemDesc itemDesc = (TbItemDesc) taotaoResult.getData();
				// 取商品描述信息
				String result = itemDesc.getItemDesc();
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 获取规格参数
	 */
	@Override
	public String getItemParam(Long itemId) {
		try {
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_PARAM_URL
					+ itemId);
			// 把json转换成java对象
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json,
					TbItemParamItem.class);
			if (taotaoResult.getStatus() == 200) {
				TbItemParamItem itemParamItem = (TbItemParamItem) taotaoResult
						.getData();
				String paramData = itemParamItem.getParamData();
				// 生成html
				// 把规格参数json数据转换成java对象
				List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
				StringBuffer sb = new StringBuffer();
				sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
				sb.append("    <tbody>\n");
				for (Map m1 : jsonList) {
					sb.append("        <tr>\n");
					sb.append("            <th class=\"tdTitle\" colspan=\"2\">"
							+ m1.get("group") + "</th>\n");
					sb.append("        </tr>\n");
					List<Map> list2 = (List<Map>) m1.get("params");
					for (Map m2 : list2) {
						sb.append("        <tr>\n");
						sb.append("            <td class=\"tdTitle\">"
								+ m2.get("k") + "</td>\n");
						sb.append("            <td>" + m2.get("v") + "</td>\n");
						sb.append("        </tr>\n");
					}
				}
				sb.append("    </tbody>\n");
				sb.append("</table>");
				// 返回html片段
				return sb.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
