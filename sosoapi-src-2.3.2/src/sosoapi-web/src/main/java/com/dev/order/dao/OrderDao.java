package com.dev.order.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dev.base.enums.ProdType;
import com.dev.base.enums.TradeStatus;
import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;
import com.dev.order.entity.Order;

/**
 * 
		* <p>Title: 订单信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年1月22日下午3:05:33</p>
 */
public interface OrderDao extends BaseMybatisDao<Order,Long> {
	/**
	 * 
			*@name 根据交易号查询订单信息
			*@Description  
			*@CreateDate 2017年2月7日下午2:44:56
	 */
	Order getByTradeNo(@Param("tradeNo")String tradeNo);
	
	/**
	 * 
			*@name 查询订单列表
			*@Description  
			*@CreateDate 2017年2月13日下午5:10:48
	 */
	List<Map> listAll(@Param("dateStart")Date dateStart,@Param("dateEnd")Date dateEnd,
						@Param("tradeStatus")TradeStatus tradeStatus,@Param("email")String email,
						@Param("pager")Pager pager);
	
	/**
	 * 
			*@name 查询订单总数
			*@Description  
			*@CreateDate 2017年2月13日下午5:11:01
	 */
	int countAll(@Param("dateStart")Date dateStart,@Param("dateEnd")Date dateEnd,
			@Param("tradeStatus")TradeStatus tradeStatus,@Param("email")String email);
	
	/**
	 * 
			*@name 查询用户订单列表
			*@Description  
			*@CreateDate 2017年2月13日下午5:10:48
	 */
	List<Order> listByUserId(@Param("userId")Long userId,@Param("dateStart")Date dateStart,@Param("dateEnd")Date dateEnd,
							@Param("prodType")ProdType prodType,@Param("tradeStatus")TradeStatus tradeStatus,
							@Param("pager")Pager pager);
	
	/**
	 * 
			*@name 查询用户订单总数
			*@Description  
			*@CreateDate 2017年2月13日下午5:11:01
	 */
	int countByUserId(@Param("userId")Long userId,@Param("dateStart")Date dateStart,@Param("dateEnd")Date dateEnd,
						@Param("prodType")ProdType prodType,@Param("tradeStatus")TradeStatus tradeStatus);
	
}
