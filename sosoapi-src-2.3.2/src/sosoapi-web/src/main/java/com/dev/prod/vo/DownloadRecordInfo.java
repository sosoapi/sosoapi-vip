package com.dev.prod.vo;

import java.io.Serializable;
import java.util.Date;

import com.dev.base.enums.ProdType;

/**
 * 
		* <p>Title: 下载记录</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年3月3日下午6:06:40</p>
 */
public class DownloadRecordInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 记录id*/
	private Long id;
	
	/** 用户id */
	private Long userId;	
	
	/** 用户邮箱*/
	private String userEmail;
	
	/** 商品id */
	private Long goodsId;		
	
	/** 商品名称 */
	private String goodsName;		
	
	/** 商品描述 */
	private String goodsDesc;		
	
	/** 类型 */
	private ProdType goodsType;		
	
	/** 下载ip */
	private String downloadIp;		
	
	/** 文件名 */
	private String fileName;		
	
	/** 下载日期*/
	private Date downloadDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getDownloadIp() {
		return downloadIp;
	}

	public void setDownloadIp(String downloadIp) {
		this.downloadIp = downloadIp;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getDownloadDate() {
		return downloadDate;
	}

	public void setDownloadDate(Date downloadDate) {
		this.downloadDate = downloadDate;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public ProdType getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(ProdType goodsType) {
		this.goodsType = goodsType;
	}
}
