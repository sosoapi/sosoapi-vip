package com.dev.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.base.constant.CfgConstants;
import com.dev.order.entity.Order;
import com.dev.order.service.OrderBizService;
import com.dev.user.service.UserBasicService;
import com.dev.user.service.UserDetailService;

/**
 * 
		* <p>Title: 订单业务服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年2月7日下午2:57:08</p>
 */
@Service
public class OrderBizServiceImpl implements OrderBizService{
	@Autowired
	private UserBasicService userBasicService;
	
	@Autowired
	private UserDetailService userDetailService;
	
	@Override
	public void doBiz(Order order) {
		userBasicService.updateRole(order.getUserId(), CfgConstants.ROLE_ID_VIP);
		//新增总费用金额
		userDetailService.updateAmountInfo(order.getUserId(), order.getTotalFee(), null);
	}
}
