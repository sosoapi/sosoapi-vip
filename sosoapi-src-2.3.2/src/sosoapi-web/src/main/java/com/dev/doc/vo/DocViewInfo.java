package com.dev.doc.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
		* <p>Title: api文档视图</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年7月19日上午10:19:12</p>
 */
public class DocViewInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 主键*/
	private Long id;
	
	/** 项目id */
	private Long projId;	
	
	/** 项目编码*/
	private String projCode;
	
	/** 项目名称 */
	private String projName;
	
	/** api文档id */
	private Long docId;		
	
	/** 标题 */
	private String title;		
	
	/** 描述信息 */
	private String description;		
	
	/** 版本 */
	private String version;
	
	/** 是否分享*/
	private boolean share;
	
	/** 创建时间*/
	private Date createDate;

	public Long getProjId() {
		return projId;
	}

	public void setProjId(Long projId) {
		this.projId = projId;
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

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isShare() {
		return share;
	}

	public void setShare(boolean share) {
		this.share = share;
	}		
}
