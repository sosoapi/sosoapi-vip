package com.dev.order.entity;

import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 退款明细</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年1月22日下午2:50:27</p>
 */
public class RefundItem extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 订单id */
	private Long orderId;		
	
	/** 退款单id */
	private Long refundId;		
	
	/** 订单项id */
	private Long orderItemId;		
	
	/** 退款申请金额 */
	private int refundFee;		
	
	/** 退款金额 */
	private int settlementRefundFee;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getRefundId() {
		return refundId;
	}

	public void setRefundId(Long refundId) {
		this.refundId = refundId;
	}

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(int refundFee) {
		this.refundFee = refundFee;
	}

	public int getSettlementRefundFee() {
		return settlementRefundFee;
	}

	public void setSettlementRefundFee(int settlementRefundFee) {
		this.settlementRefundFee = settlementRefundFee;
	}		
}
