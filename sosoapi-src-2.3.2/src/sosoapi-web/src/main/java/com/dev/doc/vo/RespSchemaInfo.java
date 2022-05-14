package com.dev.doc.vo;

import java.io.Serializable;
import java.util.Date;

import com.dev.base.enums.SchemaType;

/**
 * 
		* <p>Title: api文档公用响应结构信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:34:50</p>
 */
public class RespSchemaInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 记录id*/
	private Long id;
	
	/** api文档id */
	private Long docId;		
	
	/** 模块id */
	private Long moduleId;	
	
	/** 模块名称*/
	private String moduleName;
	
	/** 编码 */
	private String code;		
	
	/** 名称 */
	private String name;		
	
	/** 描述 */
	private String description;		
	
	/** 类型 */
	private SchemaType type;		
	
	/** 创建时间*/
	private Date createDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public SchemaType getType() {
		return type;
	}

	public void setType(SchemaType type) {
		this.type = type;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
