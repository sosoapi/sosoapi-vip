package com.dev.doc.exports.service;

/**
 * 
		* <p>Title: 导出基类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年8月15日下午2:48:57</p>
 */
public interface BaseDocExportService<T> {
	/**
	 * 
			*@name 导出文档
			*@Description  
			*@CreateDate 2017年8月15日下午6:03:19
	 */
	T exportDoc(Long userId, Long docId,Long moduleId,String condition);
	
	/**
	 * 
			*@name 导出文档视图
			*@Description  
			*@CreateDate 2016年12月1日下午1:25:05
	 */
	T exportDocView(Long userId,Long viewId,Long docId,Long moduleId,String condition);
}
