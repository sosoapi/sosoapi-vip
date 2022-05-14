package com.dev.doc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.base.controller.BaseController;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.utils.ValidateUtils;
import com.dev.doc.entity.ApiErrorCode;
import com.dev.doc.service.ApiErrorCodeService;
import com.dev.doc.util.InterTreeUtil;

/**
 * 
		* <p>Title 返回码相关</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午5:39:29</p>
 */
@Controller
@RequestMapping("/auth/doc/code")
public class ApiErrorCodeController extends BaseController{
	@Autowired
	private ApiErrorCodeService apiErrorCodeService;
	
	/**
	 * 
			*@name 新增返回码
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/add.htm",method = RequestMethod.POST)
	public @ResponseBody Map add(HttpServletRequest request,ApiErrorCode apiErrorCode){
		ValidateUtils.notNull(apiErrorCode.getDocId(), ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(apiErrorCode.getCode(), ErrorCode.SYS_001,"返回码不能为空");
		
		apiErrorCodeService.add(apiErrorCode);
		return JsonUtils.createSuccess(InterTreeUtil.buildTreeNodeInfo(apiErrorCode));
	}
	
	/**
	 * 
			*@name 编辑返回码信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/update.htm",method = RequestMethod.POST)
	public @ResponseBody Map update(HttpServletRequest request,ApiErrorCode apiErrorCode,Long codeId){
		ValidateUtils.notNull(apiErrorCode.getDocId(), ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(codeId, ErrorCode.SYS_001,"返回码id不能为空");
		ValidateUtils.notNull(apiErrorCode.getCode(), ErrorCode.SYS_001,"返回码不能为空");
		
		apiErrorCode.setId(codeId);
		apiErrorCodeService.updateByDocId(apiErrorCode);
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 删除返回码信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/del.htm")
	public @ResponseBody Map del(HttpServletRequest request,Long docId,Long codeId){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(codeId, ErrorCode.SYS_001,"返回码id不能为空");
		
		apiErrorCodeService.deleteByDocId(docId, codeId);
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 返回码基本信息
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/info.htm")
	public @ResponseBody Map getInfo(Long docId,Long codeId){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(codeId, ErrorCode.SYS_001,"返回码id不能为空");

		ApiErrorCode apiErrorCode = apiErrorCodeService.getByDocId(docId, codeId);
		return JsonUtils.createSuccess(apiErrorCode);
	}
}