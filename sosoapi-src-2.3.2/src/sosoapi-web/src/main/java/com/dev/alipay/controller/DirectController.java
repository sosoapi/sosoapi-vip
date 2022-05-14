package com.dev.alipay.controller;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.alipay.constant.DirectCfg;
import com.dev.alipay.util.DirectUtil;
import com.dev.base.controller.BaseController;
import com.dev.base.enums.TradePlatform;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.WebUtil;
import com.dev.base.utils.DateUtil;
import com.dev.base.utils.FormatUtils;
import com.dev.base.utils.ValidateUtils;
import com.dev.order.entity.Order;
import com.dev.order.entity.PayNotify;
import com.dev.order.service.OrderService;

/**
 * 
		* <p>Title: 即时到账</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年1月21日下午2:07:56</p>
 */
@Controller
public class DirectController extends BaseController{
	@Autowired
	private OrderService orderService;
	
	/**
	 * 
			*@name 组装支付表单
			*@Description  
			*@param tradeNo		交易单号，商户网站订单系统中唯一订单号，必填
			*@param subName		商品名称，必填
			*@param subDesc		商品描述，选填
			*@param totalFee	付款金额，必填
			*@CreateDate 2017年1月21日下午2:13:47
	 */
	@RequestMapping("/auth/pay/ali/direct/buildReq.htm")
	public void buildRequest(HttpServletRequest request,Long orderId,HttpServletResponse response){
		ValidateUtils.notNull(orderId, ErrorCode.SYS_001,"订单id不能为空");
		Order order = orderService.getForPay(getUserId(request), orderId);
		
		//请求方法
		String reqMethod = "get";
		String buttonName = "确认";
		//请求表单
        StringBuffer reqBuilder = new StringBuffer();
        reqBuilder.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"")
        		  .append(DirectCfg.GATEWAY)
        		  .append("_input_charset=")
        		  .append(DirectCfg.CHARSET)
        		  .append("\" method=\"")
        		  .append(reqMethod)
        		  .append("\">");
        
		//请求参数数组
        Map<String, String> paramMap = DirectUtil.buildReqParam(order.getTradeNo(), 
        		order.getOrderName(), order.getOrderDesc(), "" + FormatUtils.formatDouble((double)order.getTotalFee()/100,2));
        Set<String> keySet = paramMap.keySet();
        for (String key : keySet) {
			reqBuilder.append("<input type=\"hidden\" name=\"")
					  .append(key)
					  .append("\" value=\"")
					  .append(paramMap.get(key))
					  .append("\"/>");
		}

        //submit按钮控件请不要含有name属性
        reqBuilder.append("<input type=\"submit\" value=\"")
        		  .append(buttonName)
        		  .append("\" style=\"display:none;\"></form>");
        reqBuilder.append("<script>document.forms['alipaysubmit'].submit();</script>");

        DirectUtil.print(response, reqBuilder.toString());
	}

	/**
	 * 
			*@name 同步通知，支付成功后支付宝自动跳转到该请求，对应return_url
			*@Description  
			*@CreateDate 2017年1月21日下午3:15:03
	 */
	@RequestMapping("/auth/pay/ali/synchNotice.htm")
	public String synchNotice(HttpServletRequest request){
		boolean result = dealNotice(request);
		if (result) {
			return "order/paySuccess";
		}
		else{
			return "order/payError";
		}
	}

	/**
	 * 
			*@name 异步通知，支付宝订单状态变更时异步调用该请求，对应notify_url
			*@Description  
			*@CreateDate 2017年1月21日下午3:15:40
	 */
	@RequestMapping("/pass/pay/ali/asynchNotice.htm")
	public void asynchNotice(HttpServletRequest request,HttpServletResponse response){
		boolean result = dealNotice(request);
		if (result) {
			DirectUtil.print(response, "success");
		}
		else{
			DirectUtil.print(response, "fail");
		}
	}
	
	//处理通知
	private boolean dealNotice(HttpServletRequest request){
		boolean result = false;
		//获取支付宝GET过来反馈信息
		Map<String,String> paramMap = DirectUtil.parseNoticeParam(request.getParameterMap());
		//计算得出通知验证结果
		boolean verifyResult = DirectUtil.verify(paramMap);
		if(verifyResult){//验证成功
			//交易状态
			String tradeStatus = request.getParameter("trade_status");
			if(tradeStatus.equals("TRADE_FINISHED") || tradeStatus.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
				//如果有做过处理，不执行商户的业务程序
				result = orderService.dealPayNotify(buildNotify(request));
			}
		}
		
		return result;
	}
	
	//组装通知信息
	private PayNotify buildNotify(HttpServletRequest request){
		PayNotify notify = new PayNotify();
		//支付宝的外部交易号即系统内部交易号，反正亦然
		notify.setOutTradeNo(request.getParameter("trade_no"));
		notify.setTradeNo(request.getParameter("out_trade_no"));
		notify.setTradeStatus(request.getParameter("trade_status"));
		notify.setPayDate(DateUtil.parseByLong(request.getParameter("gmt_payment")));
		notify.setTotalFee(FormatUtils.formatInt(Double.parseDouble(request.getParameter("total_fee"))*100));
		notify.setRespContent(JsonUtils.toJson(request.getParameterMap()));
		notify.setNotifyIp(WebUtil.getClientIp(request));
		notify.setTradePlatform(TradePlatform.alipay);
		
		return notify;
	}
}
