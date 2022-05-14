package com.dev.doc.service;

import java.util.List;

import com.dev.base.enums.DocArchiveType;
import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;
import com.dev.doc.entity.DocArchive;

import io.swagger.models.Swagger;

/**
 * 
		* <p>Title: 文档归档</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年9月26日上午10:46:26</p>
 */
public interface DocArchiveService extends BaseMybatisService<DocArchive, Long>{
	/**
	 * 
			*@name 查询文档归档信息
			*@Description  
			*@CreateDate 2017年7月20日下午5:19:10
	 */
	DocArchive getByDocId(Long archiveId,Long docId);
	
	/**
	 * 
			*@name 更新归档信息
			*@Description  
			*@CreateDate 2017年7月21日上午10:18:37
	 */
	void updateByDocId(DocArchive docArchive);
	
	/**
	 * 
			*@name 删除文档归档
			*@Description  
			*@CreateDate 2017年7月21日上午10:18:58
	 */
	void delByDocId(Long archiveId,Long docId);
	
	/**
	 * 
			*@name 根据文档id查询相关的文档归档列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<DocArchive> listByDocId(Long docId,String title,
							String description,String label,Pager pager);
	
	/**
	 * 
			*@name 根据文档id查询相关的文档归档总数
			*@Description  
			*@CreateDate 2015年8月19日下午3:26:29
	 */
	int countByDocId(Long docId,String title,
						String description,String label);
	
	/**
	 * 
			*@name 加密分享信息
			*@Description  
			*@CreateDate 2017年7月21日下午2:45:08
	 */
	String encryShareInfo(Long archiveId,Long docId);
	
	/**
	 * 
			*@name 解密分享信息
			*@Description  
			*@CreateDate 2017年7月24日下午4:42:45
	 */
	DocArchive decryptShareInfo(String shareKey);
	
	/**
	 * 
			*@name 创建归档文档并返回url
			*@Description  
			*@CreateDate 2017年9月27日上午9:59:22
	 */
	String buildArchive(Long docId,String title,DocArchiveType type);
	
	/**
	 * 
			*@name 加载归档资源
			*@Description  
			*@CreateDate 2017年9月27日下午2:11:18
	 */
	String loadArchive(Long docId,Long archiveId);
}
