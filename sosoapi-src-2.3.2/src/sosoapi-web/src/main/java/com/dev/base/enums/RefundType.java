package com.dev.base.enums;

/**
 * 
		* <p>Title: 退款方式</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月6日上午9:44:28</p>
 */
public enum RefundType {
	userApply("自行申请"),
	
	operBatch("批量退款");
	
	private RefundType(String displayName){
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
