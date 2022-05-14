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
import com.dev.doc.service.CommonParamService;
import com.dev.doc.service.InterParamService;

/**
 * 
		* <p>Title: 接口公共参数相关</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午5:39:29</p>
 */
@Controller
@RequestMapping("/auth/doc/common/param")
public class CommonParamController extends BaseController{
	@Autowired
	private CommonParamService commonParamService;
	
	@Autowired
	private InterParamService interParamService;
	
	/**
	 * 
			*@name 查询接口公共参数列表
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/list.htm")
	public @ResponseBody Map list(HttpServletRequest request,Long docId){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		
		List<InterParam> interParamList = commonParamService.listByDocId(docId);
		return JsonUtils.createSuccess(interParamList);
	}
	
	/**
	 * 
			*@name 新增接口公共参数
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping(value = "/json/add.htm",method = RequestMethod.POST)
	public @ResponseBody Map add(HttpServletRequest request,Long docId,String commonParam){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		
		List<InterParam> interParamList = interParamService.buildParamList(docId, null, commonParam);
		commonParamService.batchAdd(docId,interParamList);
		return JsonUtils.createSuccess();
	}
}
