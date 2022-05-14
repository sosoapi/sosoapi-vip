package com.dev.base.enums;

/**
 * 
		* <p>Title: 退款状态</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月6日上午9:44:28</p>
 */
public enum RefundStatus {
	approving("审批中"),
	
	refuse("拒绝"),
	
	refunding("退款中"),
	
	success("已退款"),
	
	error("退款失败");
	
	private RefundStatus(String displayName){
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
