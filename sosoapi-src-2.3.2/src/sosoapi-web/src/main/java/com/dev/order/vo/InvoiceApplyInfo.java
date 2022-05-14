package com.dev.order.vo;

import java.io.Serializable;
import java.util.Date;

import com.dev.base.enums.DealStatus;
import com.dev.base.enums.InvoiceType;

/**
 * 
		* <p>Title: 发票申请信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年11月20日上午11:22:44</p>
 */
public class InvoiceApplyInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 记录id*/
	private Long id;
	
	/** 用户id */
	private Long userId;	
	
	/** 用户邮箱*/
	private String userEmail;
	
	/** 发票类型 */
	private InvoiceType type;		
	
	/** 产品名称 */
	private String productName;		
	
	/** 总金额 */
	private int totalAmount;		
	
	/** 公司名称 */
	private String companyName;		
	
	/** 处理状态 */
	private DealStatus dealStatus;		
	
	/** 处理时间 */
	private Date dealDate;
	
	/** 申请时间*/
	private Date applyDate;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public InvoiceType getType() {
		return type;
	}

	public void setType(InvoiceType type) {
		this.type = type;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public DealStatus getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(DealStatus dealStatus) {
		this.dealStatus = dealStatus;
	}

	public Date getDealDate() {
		return dealDate;
	}

	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
}
