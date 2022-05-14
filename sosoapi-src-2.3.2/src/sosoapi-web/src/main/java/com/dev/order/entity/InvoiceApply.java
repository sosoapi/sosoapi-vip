package com.dev.order.entity;

import java.util.Date;

import com.dev.base.enums.DealStatus;
import com.dev.base.enums.InvoiceType;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 发票申请信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年11月20日上午11:22:44</p>
 */
public class InvoiceApply extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 用户id */
	private Long userId;		
	
	/** 发票类型 */
	private InvoiceType type;		
	
	/** 产品名称 */
	private String productName;		
	
	/** 总金额 */
	private int totalAmount;		
	
	/** 公司名称 */
	private String companyName;		
	
	/** 公司税号 */
	private String companyTaxNo;		
	
	/** 公司地址 */
	private String companyAddr;		
	
	/** 公司电话 */
	private String companyTel;		
	
	/** 公司开户银行 */
	private String companyBankName;		
	
	/** 公司开户银行账号 */
	private String companyBankAccount;		
	
	/** 邮寄地址 */
	private String mailingAddr;		
	
	/** 收件人姓名 */
	private String receiverName;		
	
	/** 收件人电话 */
	private String receiverTel;		
	
	/** 申请备注信息 */
	private String applyRemark;		
	
	/** 处理状态 */
	private DealStatus dealStatus;		
	
	/** 处理时间 */
	private Date dealDate;		
	
	/** 处理用户id */
	private Long dealUserId;		
	
	/** 快递商名称 */
	private String mailingServName;		
	
	/** 快递单号 */
	private String mailingNo;		
	
	/** 寄送时间 */
	private Date mailingDate;		
	
	/** 备注 */
	private String remark;

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

	public String getCompanyTaxNo() {
		return companyTaxNo;
	}

	public void setCompanyTaxNo(String companyTaxNo) {
		this.companyTaxNo = companyTaxNo;
	}

	public String getCompanyAddr() {
		return companyAddr;
	}

	public void setCompanyAddr(String companyAddr) {
		this.companyAddr = companyAddr;
	}

	public String getCompanyTel() {
		return companyTel;
	}

	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}

	public String getCompanyBankName() {
		return companyBankName;
	}

	public void setCompanyBankName(String companyBankName) {
		this.companyBankName = companyBankName;
	}

	public String getCompanyBankAccount() {
		return companyBankAccount;
	}

	public void setCompanyBankAccount(String companyBankAccount) {
		this.companyBankAccount = companyBankAccount;
	}

	public String getMailingAddr() {
		return mailingAddr;
	}

	public void setMailingAddr(String mailingAddr) {
		this.mailingAddr = mailingAddr;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverTel() {
		return receiverTel;
	}

	public void setReceiverTel(String receiverTel) {
		this.receiverTel = receiverTel;
	}

	public String getApplyRemark() {
		return applyRemark;
	}

	public void setApplyRemark(String applyRemark) {
		this.applyRemark = applyRemark;
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

	public Long getDealUserId() {
		return dealUserId;
	}

	public void setDealUserId(Long dealUserId) {
		this.dealUserId = dealUserId;
	}

	public String getMailingServName() {
		return mailingServName;
	}

	public void setMailingServName(String mailingServName) {
		this.mailingServName = mailingServName;
	}

	public String getMailingNo() {
		return mailingNo;
	}

	public void setMailingNo(String mailingNo) {
		this.mailingNo = mailingNo;
	}

	public Date getMailingDate() {
		return mailingDate;
	}

	public void setMailingDate(Date mailingDate) {
		this.mailingDate = mailingDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}		
}
