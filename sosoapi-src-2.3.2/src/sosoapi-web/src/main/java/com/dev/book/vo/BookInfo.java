package com.dev.book.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
		* <p>Title: 书籍相关信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年12月6日下午1:47:53</p>
 */
public class BookInfo implements Serializable{
	private static final long serialVersionUID = 1L;

	/** 主键标识*/
	private Long id;
	
	/** 创建时间*/
	private Date createDate;
	
	/** 修改时间*/
	private Date modifyDate;
	
	/** 标题*/
	private String title;
	
	/** 封面url */
	private String coverUrl;
	
	/** 简介*/
	private String brief;
	
	/** 标签 */
	private String tag;	
	
	/** 项目id*/
	private Long projId;
	
	/** 创建者id*/
	private Long userId;
	
	/** 角色id*/
	private Long roleId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Long getProjId() {
		return projId;
	}

	public void setProjId(Long projId) {
		this.projId = projId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
