package com.dev.order.entity;

import com.dev.base.enums.TradeStatus;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 订单明细</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年1月22日下午2:58:52</p>
 */
public class OrderItem extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 订单号 */
	private Long orderId;		
	
	/** 商品编号 */
	private String goodsId;		
	
	/** 商品名称 */
	private String goodsName;		
	
	/** 商品描述 */
	private String goodsDesc;		
	
	/** 商品数量 */
	private int goodsCount;		
	
	/** 单价 */
	private int price;		
	
	/** 总价格 */
	private int totalFee;		
	
	/** 退款金额 */
	private int refundFee;		
	
	/** 退款备注 */
	private String refundRemark;		
	
	/** 交易状态 */
	private TradeStatus tradeStatus;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public int getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
	}

	public int getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(int refundFee) {
		this.refundFee = refundFee;
	}

	public String getRefundRemark() {
		return refundRemark;
	}

	public void setRefundRemark(String refundRemark) {
		this.refundRemark = refundRemark;
	}

	public TradeStatus getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(TradeStatus tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
}
