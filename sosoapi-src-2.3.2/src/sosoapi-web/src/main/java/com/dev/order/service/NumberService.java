package com.dev.order.service;

/**
 * 
		* <p>Title: 编号生成服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2016年9月2日上午11:49:05</p>
 */
public interface NumberService {
	/**
	 * 
			*@name 生成订单号
			*@Description  
			*@CreateDate 2016年9月5日上午10:06:25
	 */
	String createOrderNo(Long userId,Long subId);
	
	/**
	 * 
			*@name 生成交易号
			*@Description  
			*@CreateDate 2016年9月5日上午10:06:25
	 */
	String createTradeNo(Long userId,Long subId);
	
	/**
	 * 
			*@name 生成退单号
			*@Description  
			*@CreateDate 2016年9月5日上午10:06:38
	 */
	String createRefundNo(Long userId,Long subId);
	
	/**
	 * 
			*@name 生成流水号
			*@Description  
			*@CreateDate 2016年9月5日上午10:06:54
	 */
	String createReceiptNo(Long userId,Long subId);
}
