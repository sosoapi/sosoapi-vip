package com.dev.book.entity;

import com.dev.base.enums.EnableStatus;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 书籍设置信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年11月20日上午11:51:03</p>
 */
public class Book extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 用户id */
	private Long userId;		
	
	/** 项目id */
	private Long projId;		
	
	/** 标题 */
	private String title;		
	
	/** 简介 */
	private String brief;		
	
	/** 标签 */
	private String tag;		
	
	/** 是否公开 */
	private boolean open;		
	
	/** 是否开启分享 */
	private boolean share;		
	
	/** 分享密码 */
	private String sharePassword;		
	
	/** 分享url */
	private String shareUrl;		
	
	/** 封面url */
	private String coverUrl;		
	
	/** 阅读数 */
	private int readCount;		
	
	/** 排序权重 */
	private int sortWeight;		
	
	/** 发布状态 */
	private EnableStatus pubStatus;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProjId() {
		return projId;
	}

	public void setProjId(Long projId) {
		this.projId = projId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
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

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getSortWeight() {
		return sortWeight;
	}

	public void setSortWeight(int sortWeight) {
		this.sortWeight = sortWeight;
	}

	public EnableStatus getPubStatus() {
		return pubStatus;
	}

	public void setPubStatus(EnableStatus pubStatus) {
		this.pubStatus = pubStatus;
	}		
}
