package com.dev.prod.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.ProdType;
import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;
import com.dev.prod.entity.Goods;

/**
 * 
		* <p>Title: 商品信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年1月23日上午10:45:29</p>
 */
public interface GoodsDao extends BaseMybatisDao<Goods,Long> {
	/**
	 * 
			*@name 查询商品列表
			*@Description  
			*@CreateDate 2015年8月6日下午3:05:46
	 */
	List<Goods> list(@Param("type")ProdType type,@Param("name")String name,
			@Param("status")EnableStatus status,@Param("pubStatus")EnableStatus pubStatus,
			@Param("pager")Pager pager);
	
	/**
	 * 
			*@name 查询商品总数
			*@Description  
			*@CreateDate 2017年1月23日下午2:29:13
	 */
	int count(@Param("type")ProdType type,@Param("name")String name,@Param("status")EnableStatus status,@Param("pubStatus")EnableStatus pubStatus);
}
