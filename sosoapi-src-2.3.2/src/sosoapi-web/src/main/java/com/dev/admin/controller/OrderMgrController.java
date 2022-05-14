package com.dev.admin.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.base.controller.BaseController;
import com.dev.base.enums.TradeStatus;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.Pager;
import com.dev.base.util.WebPaginate;
import com.dev.base.utils.DateUtil;
import com.dev.base.utils.ValidateUtils;
import com.dev.order.service.OrderService;
import com.dev.order.vo.OrderInfo;

/**
 * 
		* <p>Title: 订单管理</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年2月13日下午4:59:27</p>
 */
@Controller
@RequestMapping("/admin/order")
public class OrderMgrController extends BaseController{
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
			String dateStart,String dateEnd,String email,String tradeStatus,
			Integer pageNumber,Integer pageSize){
		TradeStatus status = StringUtils.isEmpty(tradeStatus) ? null : TradeStatus.valueOf(tradeStatus);
		Date start = StringUtils.isEmpty(dateStart) ? null : DateUtil.parseByLong(dateStart + " 00:00:00");
		Date end = StringUtils.isEmpty(dateEnd) ? null : DateUtil.parseByLong(dateEnd + " 23:59:59");
		
		Pager pager = new Pager(pageNumber, pageSize);
		List<OrderInfo> list = orderService.listAll(start, end, status, email, pager);
		int count = orderService.countAll(start, end, status, email);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "admin/orderList";
	}
	
	/**
	 * 
			*@name 删除订单
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/del.htm")
	public @ResponseBody Map del(Long orderId){
		ValidateUtils.notNull(orderId, ErrorCode.SYS_001,"订单id不能为空");
		orderService.cascadeDelById(orderId);
		
		return JsonUtils.createSuccess();
	}
}
