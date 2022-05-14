package com.dev.doc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;
import com.dev.doc.entity.DocArchive;

/**
 * 
		* <p>Title: 文档归档</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年9月26日上午10:46:26</p>
 */
public interface DocArchiveDao extends BaseMybatisDao<DocArchive,Long> {
	/**
	 * 
			*@name 查询文档归档信息
			*@Description  
			*@CreateDate 2017年7月20日下午5:19:10
	 */
	DocArchive getByDocId(@Param("archiveId")Long archiveId,@Param("docId")Long docId);
	
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
	void delByDocId(@Param("archiveId")Long archiveId,@Param("docId")Long docId);
	
	/**
	 * 
			*@name 根据文档id查询相关的文档归档列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<DocArchive> listByDocId(@Param("docId")Long docId,@Param("title")String title,
							@Param("description")String description,@Param("label")String label,@Param("pager")Pager pager);
	
	/**
	 * 
			*@name 根据文档id查询相关的文档归档总数
			*@Description  
			*@CreateDate 2015年8月19日下午3:26:29
	 */
	int countByDocId(@Param("docId")Long docId,@Param("title")String title,
						@Param("description")String description,@Param("label")String label);
}
