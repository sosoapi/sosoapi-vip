package com.dev.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dev.admin.entity.FilterChain;
import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.FilterChainPosition;
import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;

/**
 * 
		* <p>Title: shiro过滤链</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年6月21日下午9:36:28</p>
 */
public interface FilterChainDao extends BaseMybatisDao<FilterChain,Long> {
	/**
	 * 
			*@name 查询过滤链列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<FilterChain> list(@Param("name")String name,@Param("url")String url,@Param("filter")String filter,
							@Param("status")EnableStatus status,@Param("pager")Pager pager);

	/**
	 * 
			*@name 查询过滤链总数
			*@Description  
			*@CreateDate 2015年8月6日下午5:40:59
	 */
	int count(@Param("name")String name,@Param("url")String url,
				@Param("filter")String filter,@Param("status")EnableStatus status);
	
	/**
	 * 
			*@name 根据位置获取过滤链列表
			*@Description  
			*@CreateDate 2017年7月10日下午5:58:46
	 */
	List<FilterChain> listByPostion(@Param("status")EnableStatus status,@Param("position")FilterChainPosition position);
}
