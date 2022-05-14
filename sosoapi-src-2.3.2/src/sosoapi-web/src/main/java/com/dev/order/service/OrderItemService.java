package com.dev.order.service;

import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.order.entity.OrderItem;

/**
 * 
		* <p>Title: 订单明细相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年1月22日下午2:50:08</p>
 */
public interface OrderItemService extends BaseMybatisService<OrderItem, Long>{
	/**
	 * 
			*@name 删除指定订单的订单明细
			*@Description  
			*@CreateDate 2017年2月17日下午2:15:50
	 */
	void delByOrderId(Long orderId);
}
