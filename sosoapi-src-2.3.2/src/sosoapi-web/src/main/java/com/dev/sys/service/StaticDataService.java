package com.dev.sys.service;

import java.util.List;

import com.dev.admin.vo.PrivilegeInfo;

/**
 * 
		* <p>Title: 系统静态数据</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年7月4日上午11:31:21</p>
 */
public interface StaticDataService {
	/**
	 * 
			*@name 加载权限列表
			*@Description  
			*@CreateDate 2017年7月4日上午11:34:09
	 */
	List<PrivilegeInfo> loadPrivList(boolean refresh);
}
