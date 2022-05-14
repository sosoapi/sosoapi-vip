package com.dev.order.dao;

import org.apache.ibatis.annotations.Param;

import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.order.entity.OrderItem;

/**
 * 
		* <p>Title: 订单明细</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年1月22日下午3:05:14</p>
 */
public interface OrderItemDao extends BaseMybatisDao<OrderItem,Long> {
	/**
	 * 
			*@name 删除指定订单的订单明细
			*@Description  
			*@CreateDate 2017年2月17日下午2:15:50
	 */
	void delByOrderId(@Param("orderId")Long orderId);
}
