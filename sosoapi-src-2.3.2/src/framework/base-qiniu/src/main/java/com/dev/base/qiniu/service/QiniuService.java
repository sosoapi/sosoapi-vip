package com.dev.base.qiniu.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * 
		* <p>Title: 七牛云相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年12月31日下午5:03:35</p>
 */
public interface QiniuService {
	/**
	 * 
			*@name 获取上传token
			*@Description  
			*@CreateDate 2015年12月31日下午5:03:42
	 */
	String getUploadToken(String bucket);
	
	/**
	 * 
			*@name 批量上传指定文件夹中的文件
			*@Description  
			*@param filePath
			*@param filterArrayStr 文件后缀，逗号分隔，如"jpg,png"
			*@param bucket
			*@param prefix
			*@CreateDate 2015年12月31日下午5:03:51
	 */
	void upload(String filePath,String filterArrayStr,String bucket,String prefix);
	
	/**
	 * 
			*@name 上传文件
			*@Description  
			*@CreateDate 2015年12月31日下午5:04:10
	 */
	void upload(String bucket,String key,File file);
	
	/**
	 * 
			*@name 上传文件
			*@Description  
			*@CreateDate 2015年12月31日下午5:04:16
	 */
	void upload(String bucket,String uploadToken,String key,File file);
	
	/**
	 * 
			*@name 上传二进制数据
			*@Description  
			*@CreateDate 2017年9月14日下午5:16:01
	 */
	void upload(String bucket,byte[] data, String key);
	
	/**
	 * 
			*@name 上传
			*@Description  
			*@CreateDate 2017年9月14日下午5:16:03
	 */
	void upload(String bucket,InputStream stream, String key);
	
	/**
	 * 
			*@name 根据前缀批量删除文件
			*@Description  
			*@CreateDate 2015年12月31日下午5:04:25
	 */
	void batchDel(String bucket,String prefix);
	
	/**
	 * 
			*@name 删除资源
			*@Description  
			*@CreateDate 2017年9月14日下午5:07:01
	 */
	void del(String bucket,String key);
	
	/**
	 * 
			*@name 根据前缀获取文件列表
			*@Description 相关说明 最多只能获取1000张
			*@CreateDate 2015年12月31日下午5:04:30
	 */
	List<String> list(String bucket,String prefix);
	
	/**
	 * 
			*@name 获取私有空间地址
			*@Description  
			*@CreateDate 2017年9月14日下午4:46:55
	 */
	String getPrivDownUrl(String pubDownUrl,long expireInSeconds);
}
