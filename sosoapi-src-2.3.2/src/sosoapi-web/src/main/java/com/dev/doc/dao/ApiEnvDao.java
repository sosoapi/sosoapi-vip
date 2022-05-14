package com.dev.doc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dev.base.enums.EnableStatus;
import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.doc.entity.ApiEnv;

/**
 * 
		* <p>Title: api接口环境</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年4月13日下午5:44:56</p>
 */
public interface ApiEnvDao extends BaseMybatisDao<ApiEnv,Long> {
	/**
	 * 
			*@name 查询文档环境
			*@Description  
			*@CreateDate 2017年4月25日下午11:14:40
	 */
	List<ApiEnv> listByDocId(@Param("docId")Long docId,@Param("status")EnableStatus status);
	
	/**
	 * 
			*@name 批量删除
			*@Description  
			*@CreateDate 2017年4月25日下午11:15:12
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
