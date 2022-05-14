package com.dev.doc.entity;

import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: api文档视图</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年7月19日上午10:19:12</p>
 */
public class DocView extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 项目id */
	private Long projId;		
	
	/** api文档id */
	private Long docId;		
	
	/** 标题 */
	private String title;		
	
	/** 描述信息 */
	private String description;		
	
	/** 版本 */
	private String version;		
	
	/** 是否发布 */
	private boolean pub;		
	
	/** 是否开启分享 */
	private boolean share;		
	
	/** 分享密码 */
	private String sharePassword;		
	
	/** 分享url */
	private String shareUrl;		
	

	public void setProjId(Long projId){
		this.projId = projId;
	}
	
	public Long getProjId(){
		return projId;
	}
	
	public void setDocId(Long docId){
		this.docId = docId;
	}
	
	public Long getDocId(){
		return docId;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setVersion(String version){
		this.version = version;
	}
	
	public String getVersion(){
		return version;
	}
	
	public void setSharePassword(String sharePassword){
		this.sharePassword = sharePassword;
	}
	
	public String getSharePassword(){
		return sharePassword;
	}
	
	public void setShareUrl(String shareUrl){
		this.shareUrl = shareUrl;
	}
	
	public String getShareUrl(){
		return shareUrl;
	}

	public boolean isPub() {
		return pub;
	}

	public void setPub(boolean pub) {
		this.pub = pub;
	}

	public boolean isShare() {
		return share;
	}

	public void setShare(boolean share) {
		this.share = share;
	}
}
