package com.dev.doc.entity;

import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: api错误码</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年4月16日上午8:52:36</p>
 */
public class ApiErrorCode extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 文档id */
	private Long docId;		
	
	/** 模块id */
	private Long moduleId;		
	
	/** 错误码 */
	private String code;		
	
	/** 错误信息 */
	private String msg;		
	
	/** 备注 */
	private String description;		
	
	/** 排序权重 */
	private int sortWeight;		
	

	public void setDocId(Long docId){
		this.docId = docId;
	}
	
	public Long getDocId(){
		return docId;
	}
	
	public void setModuleId(Long moduleId){
		this.moduleId = moduleId;
	}
	
	public Long getModuleId(){
		return moduleId;
	}
	
	public void setCode(String code){
		this.code = code;
	}
	
	public String getCode(){
		return code;
	}
	
	public void setMsg(String msg){
		this.msg = msg;
	}
	
	public String getMsg(){
		return msg;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setSortWeight(int sortWeight){
		this.sortWeight = sortWeight;
	}
	
	public int getSortWeight(){
		return sortWeight;
	}
	
}
