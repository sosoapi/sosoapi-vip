package com.dev.proj.vo;

import java.io.Serializable;
import java.util.Date;

import com.dev.base.enums.ProjectStatus;

/**
 * 
		* <p>Title: 项目信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:33:39</p>
 */
public class ProjectInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 项目id*/
	private Long projId;
	
	/** 文档id*/
	private Long docId;
	
	/** 编码*/
	private String code;
	
	/** 名称 */
	private String name;		
	
	/** 描述 */
	private String description;		
	
	/** 项目状态 */
	private ProjectStatus status;

	/** 项目角色*/
	private Long projRoleId;
	
	/** 项目角色名称*/
	private String projRoleName;
	
	/** 创建者邮箱*/
	private String createrEmail;
	
	/** 成员数目*/
	private long memCount;
	
	/** 项目创建时间*/
	private Date createDate;
	
	/** mock基地址*/
	private String mockBaseUrl;
	
	/** mock静态基地址*/
	private String mockStaticBaseUrl;
	
	/** mock动态基地址*/
	private String mockDynamicBaseUrl;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getProjRoleId() {
		return projRoleId;
	}

	public void setProjRoleId(Long projRoleId) {
		this.projRoleId = projRoleId;
	}

	public String getProjRoleName() {
		return projRoleName;
	}

	public void setProjRoleName(String projRoleName) {
		this.projRoleName = projRoleName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreaterEmail() {
		return createrEmail;
	}

	public void setCreaterEmail(String createrEmail) {
		this.createrEmail = createrEmail;
	}

	public long getMemCount() {
		return memCount;
	}

	public void setMemCount(long memCount) {
		this.memCount = memCount;
	}

	public String getMockBaseUrl() {
		return mockBaseUrl;
	}

	public void setMockBaseUrl(String mockBaseUrl) {
		this.mockBaseUrl = mockBaseUrl;
	}

	public String getMockStaticBaseUrl() {
		return mockStaticBaseUrl;
	}

	public void setMockStaticBaseUrl(String mockStaticBaseUrl) {
		this.mockStaticBaseUrl = mockStaticBaseUrl;
	}

	public String getMockDynamicBaseUrl() {
		return mockDynamicBaseUrl;
	}

	public void setMockDynamicBaseUrl(String mockDynamicBaseUrl) {
		this.mockDynamicBaseUrl = mockDynamicBaseUrl;
	}
}
