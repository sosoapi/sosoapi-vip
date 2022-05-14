package com.dev.admin.controller;

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
import com.dev.base.enums.DealStatus;
import com.dev.base.enums.InvoiceType;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.Pager;
import com.dev.base.util.WebPaginate;
import com.dev.base.utils.DateUtil;
import com.dev.base.utils.ValidateUtils;
import com.dev.order.entity.InvoiceApply;
import com.dev.order.service.InvoiceApplyService;
import com.dev.order.vo.InvoiceApplyInfo;
import com.dev.user.entity.UserBasic;
import com.dev.user.service.UserBasicService;

/**
 * 
		* <p>Title: 发票申请管理</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月6日下午4:19:22</p>
 */
@Controller
@RequestMapping("/admin/invoice/apply")
public class InvoiceApplyMgrController extends BaseController{
	@Autowired
	private InvoiceApplyService invoiceApplyService;
	
	@Autowired
	private UserBasicService userBasicService;
	
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
		
		Pager pager = new Pager(pageNumber, pageSize);
		List<InvoiceApplyInfo> list = invoiceApplyService.listByUserId(null, email, companyName, dealStatusObj, typeObj, pager);
		int count = invoiceApplyService.countByUserId(null, email, companyName, dealStatusObj, typeObj);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "admin/invoiceApplyList";
	}
	
	/**
	 * 
			*@name 编辑申请信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping("/json/update.htm")
	public @ResponseBody Map update(HttpServletRequest request,InvoiceApply apply,String email,Long applyId,boolean submitFlag){
		ValidateUtils.notNull(applyId, ErrorCode.SYS_001,"申请id不能为空");
		ValidateUtils.notNull(email, ErrorCode.SYS_001,"邮箱不能为空");
		UserBasic userBasic = userBasicService.getByEmail(email);
		ValidateUtils.notNull(userBasic, ErrorCode.SYS_001,"申请用户不存在");
		
		if (submitFlag) {
			dealForSubmit(apply);
		}
		apply.setId(applyId);
		apply.setUserId(userBasic.getId());
		invoiceApplyService.update(apply);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 新增申请信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping("/json/add.htm")
	public @ResponseBody Map add(HttpServletRequest request,InvoiceApply invoiceApply,String email,boolean submitFlag){
		ValidateUtils.notNull(email, ErrorCode.SYS_001,"邮箱不能为空");
		UserBasic userBasic = userBasicService.getByEmail(email);
		ValidateUtils.notNull(userBasic, ErrorCode.SYS_001,"申请用户不存在");
		
		if (submitFlag) {
			dealForSubmit(invoiceApply);
		}
		invoiceApplyService.addByUserId(userBasic.getId(),invoiceApply);
		
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
	public @ResponseBody Map getInfo(Long applyId){
		ValidateUtils.notNull(applyId, ErrorCode.SYS_001,"申请id不能为空");
		InvoiceApply invoiceApply = invoiceApplyService.getByUserId(applyId, null);
		return JsonUtils.createSuccess(invoiceApply);
	}
	
	/**
	 * 
			*@name 删除申请信息
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/del.htm")
	public @ResponseBody Map del(Long applyId){
		ValidateUtils.notNull(applyId, ErrorCode.SYS_001,"申请id不能为空");
		invoiceApplyService.delByUserId(applyId, null);
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
		
		invoiceApplyService.submitByUserId(applyId, null);
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 审批
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping("/json/approve.htm")
	public @ResponseBody Map approve(HttpServletRequest request,InvoiceApply apply,Long applyId){
		ValidateUtils.notNull(applyId, ErrorCode.SYS_001,"申请id不能为空");
		
		apply.setId(applyId);
		invoiceApplyService.approve(getUserId(request), apply);
		return JsonUtils.createSuccess();
	}
}
