package com.dev.order.service;

import java.util.Date;
import java.util.List;

import com.dev.base.enums.ProdType;
import com.dev.base.enums.TradeStatus;
import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;
import com.dev.order.entity.Order;
import com.dev.order.entity.PayNotify;
import com.dev.order.vo.OrderInfo;

/**
 * 
		* <p>Title: 订单相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年1月22日下午2:49:53</p>
 */
public interface OrderService extends BaseMybatisService<Order, Long>{
	/**
	 * 
			*@name 创建订单
			*@Description  
			*@CreateDate 2017年1月23日上午11:23:41
	 */
	Order add(Long userId,Long goodsId,int count);
	
	/**
	 * 
			*@name 获取待支付订单信息
			*@Description  
			*@CreateDate 2017年1月23日下午5:01:38
	 */
	Order getForPay(Long userId,Long orderId);
	
	/**
	 * 
			*@name 支付验证
			*@Description  
			*@CreateDate 2017年1月23日下午5:10:58
	 */
	void validPay(Long userId,Order order);
	
	/**
	 * 
			*@name 处理支付通知
			*@Description  
			*@CreateDate 2017年2月7日下午2:15:27
	 */
	boolean dealPayNotify(PayNotify payNotify);
	
	/**
	 * 
			*@name 根据交易号查询订单信息
			*@Description  
			*@CreateDate 2017年2月7日下午2:44:56
	 */
	Order getByTradeNo(String tradeNo);
	
	/**
	 * 
			*@name 查询订单列表
			*@Description  
			*@CreateDate 2017年2月13日下午5:10:48
	 */
	List<OrderInfo> listAll(Date dateStart,Date dateEnd,
						TradeStatus tradeStatus,String email,Pager pager);
	
	/**
	 * 
			*@name 查询订单总数
			*@Description  
			*@CreateDate 2017年2月13日下午5:11:01
	 */
	int countAll(Date dateStart,Date dateEnd,
			TradeStatus tradeStatus,String email);
	
	/**
	 * 
			*@name 删除订单信息，包括订单明细
			*@Description  
			*@CreateDate 2017年2月17日下午2:17:28
	 */
	void cascadeDelById(Long orderId);
	
	/**
	 * 
			*@name 查询用户订单列表
			*@Description  
			*@CreateDate 2017年2月13日下午5:10:48
	 */
	List<Order> listByUserId(Long userId,Date dateStart,Date dateEnd,
							ProdType prodType,TradeStatus tradeStatus,Pager pager);
	
	/**
	 * 
			*@name 查询用户订单总数
			*@Description  
			*@CreateDate 2017年2月13日下午5:11:01
	 */
	int countByUserId(Long userId,Date dateStart,Date dateEnd,
						ProdType prodType,TradeStatus tradeStatus);
}
