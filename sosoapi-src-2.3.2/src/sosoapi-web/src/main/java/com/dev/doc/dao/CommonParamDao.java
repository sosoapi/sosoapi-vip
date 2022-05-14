package com.dev.doc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.doc.entity.InterParam;

/**
 * 
		* <p>Title: 接口参数</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:28:07</p>
 */
public interface CommonParamDao extends BaseMybatisDao<InterParam,Long> {
	/**
	 * 
			*@name 查询指定文档下的公共参数 
			*@Description  
			*@CreateDate 2017年4月13日上午11:27:25
	 */
	List<InterParam> listByDocId(@Param("docId")Long docId);
	
	/**
	 * 
			*@name 删除指定文档下的公共参数
			*@Description  
			*@CreateDate 2017年4月13日上午11:28:28
	 */
	void batchDelByDocId(@Param("docId")Long docId);
	
	/**
	 * 
			*@name 复制
			*@Description  
			*@CreateDate 2017年8月31日下午3:25:49
	 */
	void cloneByDocId(@Param("srcDocId")Long srcDocId,@Param("cloneDocId")Long cloneDocId);
}
