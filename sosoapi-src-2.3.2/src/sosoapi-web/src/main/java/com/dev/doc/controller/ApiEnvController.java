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
import com.dev.base.enums.EnableStatus;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.utils.ValidateUtils;
import com.dev.doc.entity.ApiEnv;
import com.dev.doc.service.ApiEnvService;

/**
 * 
		* <p>Title: 文档环境相关</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午5:39:29</p>
 */
@Controller
@RequestMapping("/auth/doc/env")
public class ApiEnvController extends BaseController{
	@Autowired
	private ApiEnvService apiEnvService;
	
	/**
	 * 
			*@name 查询环境列表
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/list.htm")
	public @ResponseBody Map list(HttpServletRequest request,Long docId){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		
		List<ApiEnv> envList = apiEnvService.listByDocId(docId,null);
		return JsonUtils.createSuccess(envList);
	}
	
	/**
	 * 
			*@name 新增环境
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping(value = "/json/add.htm",method = RequestMethod.POST)
	public @ResponseBody Map add(HttpServletRequest request,Long docId,String envParam){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		
		List<ApiEnv> envList = apiEnvService.buildEnvList(docId, envParam);
		apiEnvService.batchAdd(docId, envList);
		return JsonUtils.createSuccess();
	}
}
