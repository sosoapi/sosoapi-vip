package com.dev.doc.entity;

import com.dev.base.enums.InterNodeType;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: api文档视图资源</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年7月19日上午10:23:10</p>
 */
public class DocViewRes extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 文档视图id */
	private Long viewId;		
	
	/** api文档id */
	private Long docId;		
	
	/** 资源类型 */
	private InterNodeType type;		
	
	/** 资源id */
	private Long resourceId;		
	

	public void setViewId(Long viewId){
		this.viewId = viewId;
	}
	
	public Long getViewId(){
		return viewId;
	}
	
	public void setDocId(Long docId){
		this.docId = docId;
	}
	
	public Long getDocId(){
		return docId;
	}

	public InterNodeType getType() {
		return type;
	}

	public void setType(InterNodeType type) {
		this.type = type;
	}

	public void setResourceId(Long resourceId){
		this.resourceId = resourceId;
	}
	
	public Long getResourceId(){
		return resourceId;
	}
	
}
