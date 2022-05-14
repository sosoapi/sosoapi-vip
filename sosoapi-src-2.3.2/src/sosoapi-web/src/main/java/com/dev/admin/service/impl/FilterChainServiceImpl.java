package com.dev.admin.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.admin.dao.FilterChainDao;
import com.dev.admin.entity.FilterChain;
import com.dev.admin.service.FilterChainService;
import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.FilterChainPosition;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;

/**
 * 
		* <p>Title: shiro过滤链</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年6月21日下午9:36:28</p>
 */
@Service
public class FilterChainServiceImpl extends BaseMybatisServiceImpl<FilterChain,Long,FilterChainDao>
										implements FilterChainService{

	@Override
	public List<FilterChain> list(String name, String url,String filter, 
									EnableStatus status, Pager pager) {
		name = getLikeExpr(name);
		url = getLikeExpr(url);
		filter = getLikeExpr(filter);
		
		return getMybatisDao().list(name, url,filter, status, pager);
	}

	@Override
	public int count(String name, String url,String filter, EnableStatus status) {
		name = getLikeExpr(name);
		url = getLikeExpr(url);
		filter = getLikeExpr(filter);
		
		return getMybatisDao().count(name, url,filter, status);
	}

	@Override
	public List<FilterChain> listByPostion(EnableStatus status, FilterChainPosition position) {
		return getMybatisDao().listByPostion(status, position);
	}
}
