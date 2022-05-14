package com.dev.doc.service;

import java.util.List;

import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.doc.entity.InterParam;

/**
 * 
		* <p>Title: 接口参数相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:38:02</p>
 */
public interface CommonParamService extends BaseMybatisService<InterParam, Long>{
	/**
	 * 
			*@name 查询指定文档下的公共参数 
			*@Description  
			*@CreateDate 2017年4月13日上午11:27:25
	 */
	List<InterParam> listByDocId(Long docId);
	
	/**
	 * 
			*@name 删除指定文档下的公共参数
			*@Description  
			*@CreateDate 2017年4月13日上午11:28:28
	 */
	void batchDelByDocId(Long docId);
	
	/**
	 * 
			*@name 新增接口公共参数
			*@Description  
			*@CreateDate 2015年8月6日下午3:37:00
	 */
	void batchAdd(Long docId,List<InterParam> list);
	
	/**
	 * 
			*@name 复制
			*@Description  
			*@CreateDate 2017年8月31日下午3:25:49
	 */
	void cloneByDocId(Long srcDocId,Long cloneDocId);
}
