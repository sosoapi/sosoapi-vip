package com.dev.doc.vo;

import java.io.Serializable;

import org.aspectj.weaver.AjAttribute.PrivilegedAttribute;

import com.dev.doc.entity.ApiDoc;
import com.dev.doc.entity.DocArchive;
import com.dev.doc.entity.DocView;

/**
 * 
		* <p>Title: 分享信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2016年11月17日上午11:46:12</p>
 */
public class DocShareInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 保存key*/
	private String key;
	
	/** 文档id*/
	private Long docId;
	
	/** 文档对象*/
	private ApiDoc apiDoc;
	
	/** 文档视图*/
	private DocView docView;
	
	/** 接口归档*/
	private DocArchive docArchive;
	
	/** 是否已授权*/
	private boolean auth;
	
	public DocShareInfo() {
		
	}
	
	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public ApiDoc getApiDoc() {
		return apiDoc;
	}

	public void setApiDoc(ApiDoc apiDoc) {
		this.apiDoc = apiDoc;
	}

	public boolean isAuth() {
		return auth;
	}

	public void setAuth(boolean auth) {
		this.auth = auth;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public DocView getDocView() {
		return docView;
	}

	public void setDocView(DocView docView) {
		this.docView = docView;
	}

	public DocArchive getDocArchive() {
		return docArchive;
	}

	public void setDocArchive(DocArchive docArchive) {
		this.docArchive = docArchive;
	}
}
