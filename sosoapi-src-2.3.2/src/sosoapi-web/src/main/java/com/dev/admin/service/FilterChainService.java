package com.dev.admin.service;

import java.util.List;

import com.dev.admin.entity.FilterChain;
import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.FilterChainPosition;
import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;

/**
 * 
		* <p>Title: shiro过滤链</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年6月21日下午9:36:28</p>
 */
public interface FilterChainService extends BaseMybatisService<FilterChain, Long>{
	/**
	 * 
			*@name 查询过滤链列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<FilterChain> list(String name,String url,String filter,EnableStatus status,Pager pager);

	/**
	 * 
			*@name 查询过滤链总数
			*@Description  
			*@CreateDate 2015年8月6日下午5:40:59
	 */
	int count(String name,String url,String filter,EnableStatus status);
	
	/**
	 * 
			*@name 根据位置获取过滤链列表
			*@Description  
			*@CreateDate 2017年7月10日下午5:58:46
	 */
	List<FilterChain> listByPostion(EnableStatus status,FilterChainPosition position);
}
