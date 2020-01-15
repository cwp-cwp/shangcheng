package com.taotao.order.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.order.JedisDao.JedisClient;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;

/**
 * 订单管理 Service
 * 
 * @author 叔公
 * 
 */
@Service
public class OrderServiceImpl implements OrderService {

	/**
	 * 自动注入 TbOrderMapper 接口的动态代理实现类对象
	 */
	@Autowired
	private TbOrderMapper orderMapper;

	/**
	 * 自动注入 TbOrderItemMapper 接口的动态代理实现类对象
	 */
	@Autowired
	private TbOrderItemMapper orderItemMapper;

	/**
	 * 自动注入 TbOrderShippingMapper 接口的动态代理实现类对象
	 */
	@Autowired
	private TbOrderShippingMapper orderShippingMapper;

	/**
	 * 自动注入 JedisClient 接口的动态代理实现类对象
	 */
	@Autowired
	private JedisClient jedisClient;

	/**
	 * redis 中订单号生成的 key
	 */
	@Value("${ORDER_GEN_KEY}")
	private String ORDER_GEN_KEY;

	/**
	 * 初始化订单号: 100544
	 */
	@Value("${ORDER_INIT_ID}")
	private String ORDER_INIT_ID;

	/**
	 * redis 中订单明细生成的 key
	 */
	@Value("${ORDER_DETAIL_GEN_KEY}")
	private String ORDER_DETAIL_GEN_KEY;

	/**
	 * 创建订单
	 */
	@Override
	public TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList,
			TbOrderShipping orderShipping) {
		// 向订单表中插入记录
		// 获得订单号
		String string = jedisClient.get(ORDER_GEN_KEY);
		if (StringUtils.isBlank(string)) {
			jedisClient.set(ORDER_GEN_KEY, ORDER_INIT_ID);
		}
		long orderId = jedisClient.incr(ORDER_GEN_KEY);
		// 补全pojo的属性
		order.setOrderId(orderId + "");
		// 状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
		order.setStatus(1);
		Date date = new Date();
		order.setCreateTime(date);
		order.setUpdateTime(date);
		// 0：未评价 1：已评价
		order.setBuyerRate(0);
		// 向订单表插入数据
		orderMapper.insert(order);
		// 插入订单明细
		for (TbOrderItem tbOrderItem : itemList) {
			// 补全订单明细
			// 取订单明细id
			long orderDetailId = jedisClient.incr(ORDER_DETAIL_GEN_KEY);
			tbOrderItem.setId(orderDetailId + "");
			tbOrderItem.setOrderId(orderId + "");
			// 向订单明细插入记录
			orderItemMapper.insert(tbOrderItem);
		}
		// 插入物流表
		// 补全物流表的属性
		orderShipping.setOrderId(orderId + "");
		orderShipping.setCreated(date);
		orderShipping.setUpdated(date);
		orderShippingMapper.insert(orderShipping);

		return TaotaoResult.ok(orderId);
	}

}
