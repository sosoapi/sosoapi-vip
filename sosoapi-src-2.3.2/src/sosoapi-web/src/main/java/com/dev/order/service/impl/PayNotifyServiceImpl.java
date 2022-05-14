package com.dev.order.service.impl;

import org.springframework.stereotype.Service;

import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.order.dao.PayNotifyDao;
import com.dev.order.entity.PayNotify;
import com.dev.order.service.PayNotifyService;

/**
 * 
		* <p>Title: 支付通知记录相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年1月22日下午2:48:33</p>
 */
@Service
public class PayNotifyServiceImpl extends BaseMybatisServiceImpl<PayNotify,Long,PayNotifyDao>
										implements PayNotifyService{
	
}
