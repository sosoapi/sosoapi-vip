package com.dev.order.entity;

import java.util.Date;

import com.dev.base.enums.ProdType;
import com.dev.base.enums.TradePlatform;
import com.dev.base.enums.TradeStatus;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 订单信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年1月22日下午2:53:13</p>
 */
public class Order extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 订单号 */
	private String orderNo;		
	
	/** 订单名称 */
	private String orderName;		
	
	/** 订单描述 */
	private String orderDesc;	
	
	/** 商品类型*/
	private ProdType prodType;
	
	/** 商品id*/
	private Long goodsId;
	
	/** 预支付id */
	private String prepayId;		
	
	/** 支付二维码 */
	private String qrCodeUrl;		
	
	/** 交易号 */
	private String tradeNo;		
	
	/** 支付平台交易号 */
	private String outTradeNo;		
	
	/** 总金额 */
	private int totalFee;		
	
	/** 应结订单金额 */
	private int settlementTotalFee;		
	
	/** 订单项数目 */
	private int totalItemCount;		
	
	/** 订单失效时间 */
	private Date expireDate;		
	
	/** 终端设备号 */
	private String deviceInfo;		
	
	/** 支付时间 */
	private Date payDate;		
	
	/** 用户id */
	private Long userId;		
	
	/** 交易状态 */
	private TradeStatus tradeStatus;		
	
	/** 交易状态描述 */
	private String tradeStatusDesc;		
	
	/** 订单支付ip */
	private String tradeIp;		
	
	/** 交易方式 */
	private String tradeType;		
	
	/** 交易平台 */
	private TradePlatform tradePlatform;		
	
	/** 退款金额 */
	private int refundFee;		
	
	/** 退款订单项 */
	private int refundItemCount;		
	
	/** 取消时间 */
	private Date cancelDate;		
	
	/** 取消原因 */
	private String cancelRemark;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public String getQrCodeUrl() {
		return qrCodeUrl;
	}

	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
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

	public int getTotalItemCount() {
		return totalItemCount;
	}

	public void setTotalItemCount(int totalItemCount) {
		this.totalItemCount = totalItemCount;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public TradeStatus getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(TradeStatus tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getTradeStatusDesc() {
		return tradeStatusDesc;
	}

	public void setTradeStatusDesc(String tradeStatusDesc) {
		this.tradeStatusDesc = tradeStatusDesc;
	}

	public String getTradeIp() {
		return tradeIp;
	}

	public void setTradeIp(String tradeIp) {
		this.tradeIp = tradeIp;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public TradePlatform getTradePlatform() {
		return tradePlatform;
	}

	public void setTradePlatform(TradePlatform tradePlatform) {
		this.tradePlatform = tradePlatform;
	}

	public int getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(int refundFee) {
		this.refundFee = refundFee;
	}

	public int getRefundItemCount() {
		return refundItemCount;
	}

	public void setRefundItemCount(int refundItemCount) {
		this.refundItemCount = refundItemCount;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getCancelRemark() {
		return cancelRemark;
	}

	public void setCancelRemark(String cancelRemark) {
		this.cancelRemark = cancelRemark;
	}

	public ProdType getProdType() {
		return prodType;
	}

	public void setProdType(ProdType prodType) {
		this.prodType = prodType;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
}
