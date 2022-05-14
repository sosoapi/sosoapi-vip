package com.dev.order.entity;

import java.util.Date;

import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.RefundStatus;
import com.dev.base.enums.RefundType;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 退款信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年1月22日下午3:00:00</p>
 */
public class Refund extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 订单id */
	private Long orderId;		
	
	/** 退款单号 */
	private String refundNo;		
	
	/** 外部退款单号 */
	private String outRefundNo;		
	
	/** 退款类型 */
	private RefundType refundType;		
	
	/** 退款申请金额 */
	private int refundFee;		
	
	/** 申请退款原因 */
	private String refundReason;		
	
	/** 退款金额 */
	private int settlementRefundFee;		
	
	/** 退款状态 */
	private RefundStatus refundStatus;		
	
	/** 退款状态描述 */
	private String refundStatusDesc;		
	
	/** 退款入账账户 */
	private String refundRecvAccout;		
	
	/** 申请人 */
	private Long userId;		
	
	/** 操作者id */
	private Long operId;		
	
	/** 审批状态 */
	private EnableStatus approveStatus;		
	
	/** 审批时间 */
	private Date approveDate;		
	
	/** 审批说明 */
	private String approveRemark;		
	
	/** 响应信息 */
	private String respContent;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getRefundNo() {
		return refundNo;
	}

	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}

	public String getOutRefundNo() {
		return outRefundNo;
	}

	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}

	public RefundType getRefundType() {
		return refundType;
	}

	public void setRefundType(RefundType refundType) {
		this.refundType = refundType;
	}

	public int getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(int refundFee) {
		this.refundFee = refundFee;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	public int getSettlementRefundFee() {
		return settlementRefundFee;
	}

	public void setSettlementRefundFee(int settlementRefundFee) {
		this.settlementRefundFee = settlementRefundFee;
	}

	public RefundStatus getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(RefundStatus refundStatus) {
		this.refundStatus = refundStatus;
	}

	public String getRefundStatusDesc() {
		return refundStatusDesc;
	}

	public void setRefundStatusDesc(String refundStatusDesc) {
		this.refundStatusDesc = refundStatusDesc;
	}

	public String getRefundRecvAccout() {
		return refundRecvAccout;
	}

	public void setRefundRecvAccout(String refundRecvAccout) {
		this.refundRecvAccout = refundRecvAccout;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOperId() {
		return operId;
	}

	public void setOperId(Long operId) {
		this.operId = operId;
	}

	public EnableStatus getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(EnableStatus approveStatus) {
		this.approveStatus = approveStatus;
	}

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	public String getApproveRemark() {
		return approveRemark;
	}

	public void setApproveRemark(String approveRemark) {
		this.approveRemark = approveRemark;
	}

	public String getRespContent() {
		return respContent;
	}

	public void setRespContent(String respContent) {
		this.respContent = respContent;
	}
}
