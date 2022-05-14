package com.dev.order.entity;

import java.util.Date;

import com.dev.base.enums.TradePlatform;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 支付通知记录</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年1月22日下午3:03:01</p>
 */
public class PayNotify extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 订单id */
	private Long orderId;		
	
	/** 商户订单号 */
	private String tradeNo;		
	
	/** 支付订单号 */
	private String outTradeNo;		
	
	/** 交易状态 */
	private String tradeStatus;		
	
	/** 交易平台 */
	private TradePlatform tradePlatform;		
	
	/** 支付时间 */
	private Date payDate;		
	
	/** 通知请求ip */
	private String notifyIp;		
	
	/** 返回码 */
	private String returnCode;		
	
	/** 返回信息 */
	private String returnMsg;		
	
	/** 业务结果 */
	private String resultCode;		
	
	/** 错误代码 */
	private String errCode;		
	
	/** 错误代码描述 */
	private String errCodeDes;		
	
	/** 订单金额 */
	private int totalFee;		
	
	/** 应结订单金额 */
	private int settlementTotalFee;		
	
	/** 通知内容 */
	private String respContent;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public TradePlatform getTradePlatform() {
		return tradePlatform;
	}

	public void setTradePlatform(TradePlatform tradePlatform) {
		this.tradePlatform = tradePlatform;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getNotifyIp() {
		return notifyIp;
	}

	public void setNotifyIp(String notifyIp) {
		this.notifyIp = notifyIp;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrCodeDes() {
		return errCodeDes;
	}

	public void setErrCodeDes(String errCodeDes) {
		this.errCodeDes = errCodeDes;
	}

	public int getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
	}

	public int getSettlementTotalFee() {
		return settlementTotalFee;
	}

	public void setSettlementTotalFee(int settlementTotalFee) {
		this.settlementTotalFee = settlementTotalFee;
	}

	public String getRespContent() {
		return respContent;
	}

	public void setRespContent(String respContent) {
		this.respContent = respContent;
	}
}
