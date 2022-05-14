package com.dev.doc.service;

import com.dev.base.enums.MockType;
import com.dev.base.enums.ReqMethod;

/**
 * 
		* <p>Title: mock相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2016年10月25日下午3:57:38</p>
 */
public interface MockService {
	/**
	 * 
			*@name 获取mock数据
			*@Description  
			*@CreateDate 2017年2月27日下午6:59:24
	 */
	String getMockData(Long docId,Long interId,String path,
			ReqMethod method,String respCode,MockType mockType);
	
	/**
	 * 
			*@name 获取mock静态数据
			*@Description  
			*@CreateDate 2016年10月25日下午4:07:41
	 */
	String getStaticMockData(Long docId,Long interId,String path,ReqMethod method,String respCode);
	
	/**
	 * 
			*@name 获取mock动态数据
			*@Description  
			*@CreateDate 2016年10月25日下午4:07:41
	 */
	String getDynamicMockData(Long docId,Long interId,String path,ReqMethod method,String respCode);
	
	/**
	 * 
			*@name 获取mock url
			*@Description  
			*@CreateDate 2017年5月13日上午8:44:54
	 */
	String getMockUrl(Long docId,MockType mockType);
}
