package com.dev.doc.imports.service;

import com.dev.doc.entity.ApiDoc;

/**
 * 
		* <p>Title: 导入基类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年8月15日下午2:48:57</p>
 */
public interface BaseDocImportService<T> {
	/**
	 * 
			*@name 导入文档
			*@Description  
			*@CreateDate 2017年8月15日下午3:14:58
	 */
	ApiDoc importDoc(Long userId,T docInfo);
}
