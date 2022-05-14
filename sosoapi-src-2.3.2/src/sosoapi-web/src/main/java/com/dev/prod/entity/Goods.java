package com.dev.prod.entity;

import java.util.Date;

import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.ProdType;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 商品信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年1月23日上午10:38:01</p>
 */
public class Goods extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 产品类型 */
	private ProdType type;		
	
	/** 名称 */
	private String name;		
	
	/** 描述 */
	private String description;		
	
	/** 单价，单位分 */
	private int price;		
	
	/** 封面 */
	private String coverUrl;		
	
	/** 详情 */
	private String detailInfo;		
	
	/** 折扣价格，单位分 */
	private int discount;		
	
	/** 启用状态 */
	private EnableStatus status;		
	
	/** 已售数量 */
	private int sellCount;		
	
	/** 总数量 */
	private int totalCount;		
	
	/** 下载地址 */
	private String downloadUrl;
	
	/** 文件名*/
	private String fileName;
	
	/** 发布时间*/
	private Date pubDate;
	
	/** 发布状态 */
	private EnableStatus pubStatus;	

	public ProdType getType() {
		return type;
	}

	public void setType(ProdType type) {
		this.type = type;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public String getDetailInfo() {
		return detailInfo;
	}

	public void setDetailInfo(String detailInfo) {
		this.detailInfo = detailInfo;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public EnableStatus getStatus() {
		return status;
	}

	public void setStatus(EnableStatus status) {
		this.status = status;
	}

	public int getSellCount() {
		return sellCount;
	}

	public void setSellCount(int sellCount) {
		this.sellCount = sellCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public EnableStatus getPubStatus() {
		return pubStatus;
	}

	public void setPubStatus(EnableStatus pubStatus) {
		this.pubStatus = pubStatus;
	}		
}
