package com.dev.doc.entity;

import com.dev.base.enums.DevStatus;
import com.dev.base.enums.ReqMethod;
import com.dev.base.enums.ReqScheme;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 接口信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:30:31</p>
 */
public class Inter extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** api文档id */
	private Long docId;		
	
	/** 模块id */
	private Long moduleId;		
	
	/** 接口方法名称*/
	private String name;
	
	/** 请求url */
	private String path;
	
	/** 请求url正则表达式*/
	private String pathRegex;
	
	/** 请求方法 */
	private ReqMethod method;		
	
	/** 请求协议 */
	private ReqScheme scheme;		
	
	/** 概要信息 */
	private String summary;		
	
	/** 描述信息 */
	private String description;		
	
	/** 请求格式 */
	private String consume;		
	
	/** 响应格式 */
	private String produce;		
	
	/** 是否弃用 */
	private boolean deprecated;	
	
	/** 删除标志*/
	private boolean delFlag;
	
	/** 排序权重，值越小，排序越靠前，默认步长为50*/
	private int sortWeight;
	
	/** 开发状态*/
	private DevStatus devStatus;
	
	/** 自定义标签*/
	private String label;
	
	/** 责任人*/
	private String developer;
	
	/** 接口标识*/
	private String operationId;
	
	/** 是否过滤公共参数*/
	private boolean skipCommonParam;
	
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
	
	public void setPath(String path){
		this.path = path;
	}
	
	public String getPath(){
		return path;
	}
	
	public ReqMethod getMethod() {
		return method;
	}

	public void setMethod(ReqMethod method) {
		this.method = method;
	}
	
	public ReqScheme getScheme() {
		return scheme;
	}

	public void setScheme(ReqScheme scheme) {
		this.scheme = scheme;
	}

	public void setSummary(String summary){
		this.summary = summary;
	}
	
	public String getSummary(){
		return summary;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setConsume(String consume){
		this.consume = consume;
	}
	
	public String getConsume(){
		return consume;
	}
	
	public void setProduce(String produce){
		this.produce = produce;
	}
	
	public String getProduce(){
		return produce;
	}

	public boolean isDeprecated() {
		return deprecated;
	}

	public void setDeprecated(boolean deprecated) {
		this.deprecated = deprecated;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSortWeight() {
		return sortWeight;
	}

	public void setSortWeight(int sortWeight) {
		this.sortWeight = sortWeight;
	}

	public String getPathRegex() {
		return pathRegex;
	}

	public void setPathRegex(String pathRegex) {
		this.pathRegex = pathRegex;
	}

	public boolean isDelFlag() {
		return delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}

	public DevStatus getDevStatus() {
		return devStatus;
	}

	public void setDevStatus(DevStatus devStatus) {
		this.devStatus = devStatus;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getOperationId() {
		return operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	public boolean isSkipCommonParam() {
		return skipCommonParam;
	}

	public void setSkipCommonParam(boolean skipCommonParam) {
		this.skipCommonParam = skipCommonParam;
	}
}
