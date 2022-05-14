package com.dev.order.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.dev.base.constant.CfgConstants;
import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.ProdType;
import com.dev.base.enums.TradeStatus;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;
import com.dev.base.utils.DateUtil;
import com.dev.base.utils.FormatUtils;
import com.dev.base.utils.ValidateUtils;
import com.dev.order.dao.OrderDao;
import com.dev.order.entity.Order;
import com.dev.order.entity.OrderItem;
import com.dev.order.entity.PayNotify;
import com.dev.order.service.NumberService;
import com.dev.order.service.OrderBizService;
import com.dev.order.service.OrderItemService;
import com.dev.order.service.OrderService;
import com.dev.order.service.PayNotifyService;
import com.dev.order.vo.OrderInfo;
import com.dev.prod.entity.Goods;
import com.dev.prod.service.GoodsService;

/**
 * 
		* <p>Title: 订单相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年1月22日下午2:48:54</p>
 */
@Service
public class OrderServiceImpl extends BaseMybatisServiceImpl<Order,Long,OrderDao>
										implements OrderService{
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private NumberService numberService;
	
	@Autowired
	private PayNotifyService payNotifyService;
	
	@Autowired
	private OrderBizService orderBizService;
	
	@Transactional
	@Override
	public Order add(Long userId, Long goodsId, int count) {
		Goods goods = goodsService.getById(goodsId);
		ValidateUtils.notNull(goods, ErrorCode.ORDER_001);
		ValidateUtils.isTrue(goods.getStatus() == EnableStatus.on, ErrorCode.ORDER_002);
		
		//创建订单
		Order order = new Order();
		order.setOrderName(goods.getName());
		order.setOrderDesc(goods.getDescription());
		order.setOrderNo(numberService.createOrderNo(userId, goodsId));
		order.setGoodsId(goodsId);
		order.setProdType(goods.getType());
		//交易号默认为订单号
		order.setTradeNo(order.getOrderNo());
		order.setTotalFee(goods.getPrice() * count);
		order.setTotalItemCount(1);
		order.setTradeStatus(TradeStatus.notpay);
		order.setUserId(userId);
		if (CfgConstants.ORDER_EXPIRE_MIN != 0) {
			order.setExpireDate(DateUtil.getNextDate(CfgConstants.ORDER_EXPIRE_MIN));
		}
		getMybatisDao().add(order);
		
		//创建订单详情
		OrderItem orderItem = new OrderItem();
		orderItem.setOrderId(order.getId());
		orderItem.setGoodsCount(count);
		orderItem.setGoodsId(goodsId + "");
		orderItem.setGoodsDesc(goods.getDescription());
		orderItem.setGoodsName(goods.getName());
		orderItem.setPrice(goods.getPrice());
		orderItem.setTotalFee(goods.getPrice() * count);
		orderItem.setTradeStatus(TradeStatus.notpay);
		orderItemService.add(orderItem);
		
		return order;
	}

	@Override
	public Order getForPay(Long userId, Long orderId) {
		Order order = getById(orderId);
		validPay(userId,order);
		return order;
	}

	@Override
	public void validPay(Long userId,Order order) {
		ValidateUtils.notNull(order, ErrorCode.ORDER_003);
		ValidateUtils.isTrue(FormatUtils.isEqual(order.getUserId(), userId), ErrorCode.ORDER_003);
		ValidateUtils.isTrue(order.getExpireDate() != null 
								&& FormatUtils.compareDate(order.getExpireDate(), DateUtil.getNow()), ErrorCode.ORDER_004);
		ValidateUtils.isTrue(order.getTradeStatus() == TradeStatus.notpay, ErrorCode.ORDER_005);
	}

	@Override
	public boolean dealPayNotify(PayNotify payNotify) {
		Order order = getByTradeNo(payNotify.getTradeNo());
		if (order == null) {//订单不存在
			return false;
		}
		
		//记录通知
		payNotify.setOrderId(order.getId());
		payNotifyService.add(payNotify);
		
		//支付金额被篡改，无效支付
		if (order.getTotalFee() != payNotify.getTotalFee()) {
			return false;
		}
		
		//未处理订单
		if (order.getTradeStatus() != TradeStatus.success) {
			//更新订单信息
			order.setOutTradeNo(payNotify.getOutTradeNo());
			order.setPayDate(payNotify.getPayDate() == null ? DateUtil.getNow() : payNotify.getPayDate());
			order.setTradePlatform(payNotify.getTradePlatform());
			order.setTradeStatus(TradeStatus.success);
			update(order);
			
			//处理业务
			orderBizService.doBiz(order);
		}
		
		return true;
	}

	@Override
	public Order getByTradeNo(String tradeNo) {
		return StringUtils.isEmpty(tradeNo) ? null : getMybatisDao().getByTradeNo(tradeNo);
	}

	@Override
	public List<OrderInfo> listAll(Date dateStart, Date dateEnd, TradeStatus tradeStatus, String email, Pager pager) {
		email = getLikeExpr(email);
		List<Map> infoList = getMybatisDao().listAll(dateStart, dateEnd, tradeStatus, email, pager);
		List<OrderInfo> result = new ArrayList<OrderInfo>();
		OrderInfo info = null;
		for (Map temp : infoList) {
			info = new OrderInfo();
			info.setOrderId((Long)temp.get("orderId"));
			info.setGoodsId((Long)temp.get("goodsId"));
			info.setProdType(ProdType.valueOf((String)temp.get("prodType")));
			info.setUserEmail((String)temp.get("userEmail"));
			info.setOrderNo((String)temp.get("orderNo"));
			info.setOrderDesc((String)temp.get("orderDesc"));
			info.setTradeNo((String)temp.get("tradeNo"));
			info.setOrderName((String)temp.get("orderName"));
			info.setExpireDate((Date)temp.get("expireDate"));
			info.setPayDate((Date)temp.get("payDate"));
			info.setUserId((Long)temp.get("userId"));
			info.setTotalFee((Integer)temp.get("totalFee"));
			info.setTradeStatus(TradeStatus.valueOf((String)temp.get("tradeStatus")));
			info.setCreateDate((Date)temp.get("createDate"));
			
			result.add(info);
		}
		
		return result;
	}

	@Override
	public int countAll(Date dateStart, Date dateEnd, TradeStatus tradeStatus, String email) {
		email = getLikeExpr(email);
		return getMybatisDao().countAll(dateStart, dateEnd, tradeStatus, email);
	}

	@Transactional
	@Override
	public void cascadeDelById(Long orderId) {
		getMybatisDao().deleteById(orderId);
		orderItemService.delByOrderId(orderId);
	}

	@Override
	public List<Order> listByUserId(Long userId, Date dateStart, Date dateEnd, ProdType prodType,
			TradeStatus tradeStatus, Pager pager) {
		return getMybatisDao().listByUserId(userId, dateStart, dateEnd, prodType, tradeStatus, pager);
	}

	@Override
	public int countByUserId(Long userId, Date dateStart, Date dateEnd, 
								ProdType prodType, TradeStatus tradeStatus) {
		return getMybatisDao().countByUserId(userId, dateStart, dateEnd, prodType, tradeStatus);
	}
}
