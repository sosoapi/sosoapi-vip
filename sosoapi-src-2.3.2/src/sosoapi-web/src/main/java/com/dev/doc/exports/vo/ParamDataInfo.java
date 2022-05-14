package com.dev.doc.exports.vo;

import java.io.Serializable;

import com.dev.base.enums.ParamPosition;
import com.dev.base.enums.SchemaType;

/**
 * 
		* <p>Title: 参数信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年8月17日下午3:36:50</p>
 */
public class ParamDataInfo implements Serializable{
	private static final long serialVersionUID = 1L;

	/** 主键id*/
	private Long id;
	
	/** 编码*/
	private String code;
	
	/** 名称 */
	private String name;		
	
	/** 描述 */
	private String description;		
	
	/** 数据类型 */
	private SchemaType type;		
	
	/** 参数位置 */
	private ParamPosition position;		
	
	/** 是否必输项 */
	private boolean required;		
	
	/** 默认值 */
	private String defValue;		
	
	/** 扩展自定义结构体,仅当type为cust_开头*/
	private String extSchema;

	/** 响应数据结构id,仅当type为sys_ref */
	private Long refSchemaId;
	
	/** 响应数据结构code*/
	private String refSchemaCode;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public ParamPosition getPosition() {
		return position;
	}

	public void setPosition(ParamPosition position) {
		this.position = position;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getDefValue() {
		return defValue;
	}

	public void setDefValue(String defValue) {
		this.defValue = defValue;
	}

	public String getExtSchema() {
		return extSchema;
	}

	public void setExtSchema(String extSchema) {
		this.extSchema = extSchema;
	}

	public Long getRefSchemaId() {
		return refSchemaId;
	}

	public void setRefSchemaId(Long refSchemaId) {
		this.refSchemaId = refSchemaId;
	}

	public String getRefSchemaCode() {
		return refSchemaCode;
	}

	public void setRefSchemaCode(String refSchemaCode) {
		this.refSchemaCode = refSchemaCode;
	}
}
