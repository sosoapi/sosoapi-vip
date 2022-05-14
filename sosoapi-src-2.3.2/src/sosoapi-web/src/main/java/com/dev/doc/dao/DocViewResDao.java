package com.dev.doc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.doc.entity.DocViewRes;

/**
 * 
		* <p>Title: api文档视图资源</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年7月19日上午10:23:10</p>
 */
public interface DocViewResDao extends BaseMybatisDao<DocViewRes,Long> {
	/**
	 * 
			*@name 删除全部资源
			*@Description  
			*@CreateDate 2017年7月3日下午2:42:07
	 */
	void batchDel(@Param("viewId")Long viewId,@Param("docId")Long docId);
	
	/**
	 * 
			*@name 查询视图相关所有资源
			*@Description  
			*@CreateDate 2017年7月24日下午3:20:37
	 */
	List<DocViewRes> listAll(@Param("viewId")Long viewId,@Param("docId")Long docId);
}
