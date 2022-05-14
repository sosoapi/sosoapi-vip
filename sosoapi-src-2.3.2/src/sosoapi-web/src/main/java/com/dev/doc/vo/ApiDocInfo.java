package com.dev.doc.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
		* <p>Title: 项目文档信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月19日下午3:07:48</p>
 */
public class ApiDocInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 项目id*/
	private Long projId;
	
	/** 文档id*/
	private Long docId;
	
	/** 项目编码*/
	private String projCode;
	
	/** 项目名称*/
	private String projName;
	
	/** 文档标题*/
	private String docTitle;
	
	/** 文档版本*/
	private String version;
	
	/** 创建时间*/
	private Date createDate;
	
	/** 是否开启分享*/
	private boolean share;
	
	/** 分享访问密码*/
	private String sharePassword;
	
	/** 分享访问url*/
	private String shareUrl;

	public Long getProjId() {
		return projId;
	}

	public void setProjId(Long projId) {
		this.projId = projId;
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public String getProjCode() {
		return projCode;
	}

	public void setProjCode(String projCode) {
		this.projCode = projCode;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getDocTitle() {
		return docTitle;
	}

	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public boolean isShare() {
		return share;
	}

	public void setShare(boolean share) {
		this.share = share;
	}

	public String getSharePassword() {
		return sharePassword;
	}

	public void setSharePassword(String sharePassword) {
		this.sharePassword = sharePassword;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}
}
