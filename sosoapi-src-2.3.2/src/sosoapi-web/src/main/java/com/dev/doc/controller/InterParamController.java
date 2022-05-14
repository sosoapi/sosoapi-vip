package com.dev.doc.controller;

import java.util.List;
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
import com.dev.doc.entity.InterParam;
import com.dev.doc.service.InterParamService;

/**
 * 
		* <p>Title: 接口参数相关</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午5:39:29</p>
 */
@Controller
@RequestMapping("/auth/doc/inter/param")
public class InterParamController extends BaseController{
	@Autowired
	private InterParamService interParamService;
	
	/**
	 * 
			*@name 查询接口参数列表
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/list.htm")
	public @ResponseBody Map list(HttpServletRequest request,Long docId,Long interId){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(interId, ErrorCode.SYS_001,"接口id不能为空");
		
		List<InterParam> interParamList = interParamService.listAllByInterId(docId,interId);
		return JsonUtils.createSuccess(interParamList);
	}
	
	/**
	 * 
			*@name 新增接口参数
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping(value = "/json/add.htm",method = RequestMethod.POST)
	public @ResponseBody Map add(HttpServletRequest request,Long docId,Long interId,String reqParam){
		ValidateUtils.notNull(interId, ErrorCode.SYS_001,"接口id不能为空");
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		
		List<InterParam> interParamList = interParamService.buildParamList(docId, interId, reqParam);
		interParamService.batchAdd(docId,interId, interParamList);
		return JsonUtils.createSuccess();
	}
}
