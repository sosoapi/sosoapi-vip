package com.dev.prod.service;

import java.util.List;

import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.ProdType;
import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;
import com.dev.prod.entity.Goods;

/**
 * 
		* <p>Title: 商品相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年1月23日上午10:45:40</p>
 */
public interface GoodsService extends BaseMybatisService<Goods, Long>{
	/**
	 * 
			*@name 查询商品列表
			*@Description  
			*@CreateDate 2015年8月6日下午3:05:46
	 */
	List<Goods> list(ProdType type,String name,EnableStatus status,EnableStatus pubStatus,Pager pager);
	
	/**
	 * 
			*@name 查询商品总数
			*@Description  
			*@CreateDate 2017年1月23日下午2:29:13
	 */
	int count(ProdType type,String name,EnableStatus status,EnableStatus pubStatus);
	
	/**
	 * 
			*@name 复制版本
			*@Description  
			*@CreateDate 2017年11月21日下午4:16:27
	 */
	void copy(Long goodsId);
}
