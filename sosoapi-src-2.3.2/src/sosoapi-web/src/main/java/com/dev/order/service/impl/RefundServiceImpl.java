package com.dev.order.service.impl;

import org.springframework.stereotype.Service;

import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.order.dao.RefundDao;
import com.dev.order.entity.Refund;
import com.dev.order.service.RefundService;

/**
 * 
		* <p>Title: 退款相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年1月22日下午2:48:07</p>
 */
@Service
public class RefundServiceImpl extends BaseMybatisServiceImpl<Refund,Long,RefundDao>
										implements RefundService{
	
}
