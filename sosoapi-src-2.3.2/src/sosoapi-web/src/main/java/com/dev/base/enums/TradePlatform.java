package com.dev.base.enums;

/**
 * 
		* <p>Title: 支付平台</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月6日上午9:44:28</p>
 */
public enum TradePlatform {
	weixin("微信"),
	
	alipay("支付宝"),
	
	unionpay("银联支付");
	
	private TradePlatform(String displayName){
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
