package com.dev.order.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.base.controller.BaseController;
import com.dev.base.enums.ProdType;
import com.dev.base.enums.TradePlatform;
import com.dev.base.enums.TradeStatus;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.util.Pager;
import com.dev.base.util.WebPaginate;
import com.dev.base.utils.DateUtil;
import com.dev.base.utils.ValidateUtils;
import com.dev.order.entity.Order;
import com.dev.order.service.OrderService;


/**
 * 
		* <p>Title: 支付相关</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2016年3月28日上午10:24:02</p>
 */
@Controller
@RequestMapping("/auth/order")
public class OrderController extends BaseController{
	@Autowired
	private OrderService orderService;
	
	/**
	 * 
			*@name 订单查询
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/list.htm")
	public String list(HttpServletRequest request,Model model,
			String dateStart,String dateEnd,String prodType,String tradeStatus,
			Integer pageNumber,Integer pageSize){
		ProdType type = StringUtils.isEmpty(prodType) ? null : ProdType.valueOf(prodType);
		TradeStatus status = StringUtils.isEmpty(tradeStatus) ? null : TradeStatus.valueOf(tradeStatus);
		Date start = StringUtils.isEmpty(dateStart) ? null : DateUtil.parseByLong(dateStart + " 00:00:00");
		Date end = StringUtils.isEmpty(dateEnd) ? null : DateUtil.parseByLong(dateEnd + " 23:59:59");
		
		Long userId = getUserId(request);
		Pager pager = new Pager(pageNumber, pageSize);
		List<Order> list = orderService.listByUserId(userId, start, end, type, status, pager);
		int count = orderService.countByUserId(userId, start, end, type, status);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "order/orderList";
	}
	
	/**
	 * 
			*@name 提交订单
			*@Description  
			*@CreateDate 2017年1月21日下午3:15:03
	 */
	@RequestMapping("/add.htm")
	public String add(HttpServletRequest request,Long goodsId,Model model){
		ValidateUtils.notNull(goodsId, ErrorCode.SYS_001,"商品id不能为空");
		Order order = orderService.add(getUserId(request), goodsId, 1);
		return "forward:/auth/pay/ali/direct/buildReq.htm?orderId=" + order.getId();
	}
	
	/**
	 * 
			*@name 支付订单
			*@Description  
			*@CreateDate 2017年1月21日下午3:15:03
	 */
	@RequestMapping("/pay.htm")
	public String pay(HttpServletRequest request,Long orderId,TradePlatform platform){
		ValidateUtils.notNull(orderId, ErrorCode.SYS_001,"订单id不能为空");
		
		if (platform == null) {
			platform = TradePlatform.alipay;
		}
		
		String forwardUrl = "";
		switch (platform) {
			case alipay:
				forwardUrl = "forward:/auth/pay/ali/direct/buildReq.htm?orderId=" + orderId;
				break;
				
			case weixin:
				break;
				
			case unionpay:
				break;
	
			default:
				break;
		}
		
		return forwardUrl;
	}
}
