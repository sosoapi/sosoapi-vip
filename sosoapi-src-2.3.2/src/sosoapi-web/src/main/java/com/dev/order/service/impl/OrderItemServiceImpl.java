package com.dev.order.service.impl;

import org.springframework.stereotype.Service;

import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.order.dao.OrderItemDao;
import com.dev.order.entity.OrderItem;
import com.dev.order.service.OrderItemService;

/**
 * 
		* <p>Title: 订单明细相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年1月22日下午2:49:06</p>
 */
@Service
public class OrderItemServiceImpl extends BaseMybatisServiceImpl<OrderItem,Long,OrderItemDao>
										implements OrderItemService{

	@Override
	public void delByOrderId(Long orderId) {
		getMybatisDao().delByOrderId(orderId);
	}
}
