package com.dev.order.service.impl;

import org.springframework.stereotype.Service;

import com.dev.base.utils.DateUtil;
import com.dev.base.utils.RandomUtils;
import com.dev.order.service.NumberService;

/**
 * 
		* <p>Title: 编号生成服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2016年9月2日上午11:49:05</p>
 */
@Service
public class NumberServiceImpl implements NumberService{
	/** 日期格式*/
	private String dateFormat = "yyMMddHHmmss";
	
	@Override
	public String createOrderNo(Long userId, Long subId) {
		return createNo(userId, subId);
	}
	
	@Override
	public String createTradeNo(Long userId, Long subId) {
		return createNo(userId, subId);
	}

	@Override
	public String createRefundNo(Long userId, Long subId) {
		return createNo(userId, subId);
	}

	@Override
	public String createReceiptNo(Long userId, Long subId) {
		return createNo(userId, subId);
	}
	
	//创建编号
	private String createNo(Long userId,Long subId){
		return DateUtil.format(dateFormat, DateUtil.getNow()) + getPostfix(userId, subId) + RandomUtils.genRandomNum(10, 99);
	}
	
	//拼装后缀
	private String getPostfix(Long userId,Long actId){
		int postFixLength = 4;
		long temp = userId + actId + RandomUtils.genRandomNum(100, 999);
		String result = temp > 10000 ? "" + temp % 10000 : "" + temp;
		
		int length = result.length();
		if (length < postFixLength) {
			for (int i = 0; i < postFixLength - length; i++) {
				result = "0" + result;
			}
		}
		
		return result;
	}
}