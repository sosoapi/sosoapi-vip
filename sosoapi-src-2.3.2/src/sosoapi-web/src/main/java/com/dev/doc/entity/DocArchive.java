package com.dev.doc.entity;

import com.dev.base.enums.DocArchiveType;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 文档归档</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年9月26日上午10:46:26</p>
 */
public class DocArchive extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 项目id */
	private Long projId;		
	
	/** 文档id */
	private Long docId;		
	
	/** 标题 */
	private String title;		
	
	/** 描述 */
	private String description;		
	
	/** 自定义标签 */
	private String label;		
	
	/** 创建者 */
	private Long userId;		
	
	/** 是否开启分享 */
	private boolean share;		
	
	/** 分享密码 */
	private String sharePassword;		
	
	/** 分享url */
	private String shareUrl;		
	
	/** 归档方式 */
	private DocArchiveType type;		
	
	/** 归档资源url */
	private String archiveUrl;		
	

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
	
	public void setLabel(String label){
		this.label = label;
	}
	
	public String getLabel(){
		return label;
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return userId;
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
	
	public void setArchiveUrl(String archiveUrl){
		this.archiveUrl = archiveUrl;
	}
	
	public String getArchiveUrl(){
		return archiveUrl;
	}

	public boolean isShare() {
		return share;
	}

	public void setShare(boolean share) {
		this.share = share;
	}

	public DocArchiveType getType() {
		return type;
	}

	public void setType(DocArchiveType type) {
		this.type = type;
	}
}
