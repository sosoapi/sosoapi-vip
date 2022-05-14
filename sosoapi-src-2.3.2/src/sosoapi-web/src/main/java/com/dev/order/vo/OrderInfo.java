package com.dev.order.vo;

import java.io.Serializable;
import java.util.Date;

import com.dev.base.enums.ProdType;
import com.dev.base.enums.TradeStatus;

/**
 * 
		* <p>Title: 订单信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年2月13日下午5:18:40</p>
 */
public class OrderInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 订单id*/
	private Long orderId;
	
	/** 商品id*/
	private Long goodsId;
	
	/** 产品类型*/
	private ProdType prodType;
	
	/** 下单用户id*/
	private Long userId;
	
	/** 下单用户邮箱*/
	private String userEmail;
	
	/** 订单号*/
	private String orderNo;
	
	/** 订单描述 */
	private String orderDesc;

	/** 交易号*/
	private String tradeNo;
	
	/** 订单名称*/
	private String orderName;
	
	/** 订单失效时间*/
	private Date expireDate;
	
	/** 订单支付时间*/
	private Date payDate;
	
	/** 订单金额*/
	private Integer totalFee;
	
	/** 交易状态*/
	private TradeStatus tradeStatus;
	
	/** 下单时间*/
	private Date createDate;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Integer getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}

	public TradeStatus getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(TradeStatus tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public ProdType getProdType() {
		return prodType;
	}

	public void setProdType(ProdType prodType) {
		this.prodType = prodType;
	}
}
