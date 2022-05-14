package com.dev.order.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.base.controller.BaseController;
import com.dev.base.enums.DealStatus;
import com.dev.base.enums.InvoiceType;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.Pager;
import com.dev.base.util.WebPaginate;
import com.dev.base.utils.DateUtil;
import com.dev.base.utils.MapUtils;
import com.dev.base.utils.ValidateUtils;
import com.dev.order.entity.InvoiceApply;
import com.dev.order.service.InvoiceApplyService;
import com.dev.order.vo.InvoiceApplyInfo;
import com.dev.user.entity.UserDetail;
import com.dev.user.service.UserDetailService;

/**
 * 
		* <p>Title: 发票申请管理</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月6日下午4:19:22</p>
 */
@Controller
@RequestMapping("/auth/invoice/apply")
public class InvoiceApplyController extends BaseController{
	@Autowired
	private InvoiceApplyService invoiceApplyService;
	
	@Autowired
	private UserDetailService userDetailService;
	
	/**
	 * 
			*@name 发票申请列表
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/list.htm")
	public String list(HttpServletRequest request,Model model,String email,String companyName,
						String dealStatus,String type,Integer pageNumber,Integer pageSize){
		DealStatus dealStatusObj = StringUtils.isEmpty(dealStatus) ? null : DealStatus.valueOf(dealStatus);
		InvoiceType typeObj = StringUtils.isEmpty(type) ? null : InvoiceType.valueOf(type);
		
		Long userId = getUserId(request);
		Pager pager = new Pager(pageNumber, pageSize);
		List<InvoiceApplyInfo> list = invoiceApplyService.listByUserId(userId, email, companyName, dealStatusObj, typeObj, pager);
		int count = invoiceApplyService.countByUserId(userId, email, companyName, dealStatusObj, typeObj);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "order/invoiceApplyList";
	}
	
	/**
	 * 
			*@name 编辑申请信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/update.htm",method = RequestMethod.POST)
	public @ResponseBody Map update(HttpServletRequest request,InvoiceApply apply,Long applyId,boolean submitFlag){
		ValidateUtils.notNull(applyId, ErrorCode.SYS_001,"申请id不能为空");
		
		if (submitFlag) {
			dealForSubmit(apply);
		}
		apply.setId(applyId);
		invoiceApplyService.updateByUserId(getUserId(request), apply);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 新增申请信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/add.htm",method = RequestMethod.POST)
	public @ResponseBody Map add(HttpServletRequest request,InvoiceApply invoiceApply,boolean submitFlag){
		if (submitFlag) {
			dealForSubmit(invoiceApply);
		}
		invoiceApplyService.addByUserId(getUserId(request),invoiceApply);
		
		return JsonUtils.createSuccess();
	}
	
	//处理提交相关信息
	private void dealForSubmit(InvoiceApply invoiceApply){
		invoiceApply.setDealStatus(DealStatus.dealing);
		invoiceApply.setModifyDate(DateUtil.getNow());
	}
	
	/**
	 * 
			*@name 查询申请信息
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/info.htm")
	public @ResponseBody Map getInfo(HttpServletRequest request,Long applyId){
		ValidateUtils.notNull(applyId, ErrorCode.SYS_001,"申请id不能为空");
		InvoiceApply invoiceApply = invoiceApplyService.getByUserId(applyId, getUserId(request));
		return JsonUtils.createSuccess(invoiceApply);
	}
	
	/**
	 * 
			*@name 删除申请信息
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/del.htm")
	public @ResponseBody Map del(HttpServletRequest request,Long applyId){
		ValidateUtils.notNull(applyId, ErrorCode.SYS_001,"申请id不能为空");
		invoiceApplyService.delByUserId(applyId, getUserId(request));
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 提交审核
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping("/json/submit.htm")
	public @ResponseBody Map submit(HttpServletRequest request,Long applyId){
		ValidateUtils.notNull(applyId, ErrorCode.SYS_001,"申请id不能为空");
		
		invoiceApplyService.submitByUserId(applyId, getUserId(request));
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 获取申请前汇总学习
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping("/json/getSumInfo.htm")
	public @ResponseBody Map getSumInfo(HttpServletRequest request){
		UserDetail userDetail = userDetailService.getByUserId(getUserId(request));
		Map<String, Object> result = MapUtils.newMap();
		result.put("feeAmount", userDetail.getFeeAmount());
		result.put("invoiceAmount", userDetail.getInvoiceAmount());
		result.put("availableInvoiceAmount", userDetail.getFeeAmount() - userDetail.getInvoiceAmount());
		
		return JsonUtils.createSuccess(result);
	}
}
