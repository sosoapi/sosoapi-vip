package com.dev.prod.entity;

import com.dev.base.enums.ProdType;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 下载记录</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年3月3日下午6:06:40</p>
 */
public class DownloadRecord extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 用户id */
	private Long userId;		
	
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
	

	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return userId;
	}
	
	public void setGoodsId(Long goodsId){
		this.goodsId = goodsId;
	}
	
	public Long getGoodsId(){
		return goodsId;
	}
	
	public void setGoodsName(String goodsName){
		this.goodsName = goodsName;
	}
	
	public String getGoodsName(){
		return goodsName;
	}
	
	public void setGoodsDesc(String goodsDesc){
		this.goodsDesc = goodsDesc;
	}
	
	public String getGoodsDesc(){
		return goodsDesc;
	}
	
	public ProdType getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(ProdType goodsType) {
		this.goodsType = goodsType;
	}

	public void setDownloadIp(String downloadIp){
		this.downloadIp = downloadIp;
	}
	
	public String getDownloadIp(){
		return downloadIp;
	}
	
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	
	public String getFileName(){
		return fileName;
	}
	
}
