package com.dev.base.enums;

/**
 * 
		* <p>Title: 交易状态</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月6日上午9:44:28</p>
 */
public enum TradeStatus {
	none("待定"),
	
	success("支付成功"),
	
	refund("已退款"),
	
	notpay("未支付"),
	
	closed("已关闭"),
	
	payerror("支付失败(其他原因，如银行返回失败)"),
	
	refunding("退款中"),
	
	expire("付款超时"),
	
	cancel("取消"),
	
	refundRefuse("退款拒绝"),
	
	refundError("退款失败");
	
	private TradeStatus(String displayName){
		this.displayName = displayName;
	}
	
	/** 展示名称*/
	private String displayName;

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
