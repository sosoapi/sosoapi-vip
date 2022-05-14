package com.dev.order.service.impl;

import org.springframework.stereotype.Service;

import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.order.dao.RefundItemDao;
import com.dev.order.entity.RefundItem;
import com.dev.order.service.RefundItemService;

/**
 * 
		* <p>Title: 退款相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年1月22日下午2:48:20</p>
 */
@Service
public class RefundItemServiceImpl extends BaseMybatisServiceImpl<RefundItem,Long,RefundItemDao>
										implements RefundItemService{
	
}
