package com.dev.doc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.base.controller.BaseController;
import com.dev.base.enums.MockType;
import com.dev.base.enums.ReqMethod;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.MockUtils;
import com.dev.base.util.WebUtil;
import com.dev.base.utils.ValidateUtils;
import com.dev.doc.service.MockService;

/**
 * 
		* <p>Title: 项目模块相关</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午5:39:29</p>
 */
@Controller
@RequestMapping("/pass/mock")
public class MockController extends BaseController{
	@Autowired
	private MockService mockService;
	
	/**
	 * 
			*@name 获取mock数据，优先动态
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/{docId}/**")
	public @ResponseBody Object getMockInfo(HttpServletRequest request,@PathVariable("docId")Long docId,
												Long mockInterId,String mockRespCode,MockType mockType){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		ReqMethod reqMethod = ReqMethod.valueOf(request.getMethod());
		String reqUrl = getReqUrl(request, "/" + docId + "/");
		String mockData = mockService.getMockData(docId,mockInterId, reqUrl, reqMethod, mockRespCode,mockType);
		ValidateUtils.isTrue(!StringUtils.isEmpty(mockData), ErrorCode.DOC_003);
		
		return JsonUtils.toObject(mockData, Object.class);
	}
	
	/**
	 * 
			*@name 获取mock数据，静态
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/static/{docId}/**")
	public @ResponseBody Object getStaticMockInfo(HttpServletRequest request,@PathVariable("docId")Long docId,
												Long mockInterId,String mockRespCode){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		ReqMethod reqMethod = ReqMethod.valueOf(request.getMethod());
		String reqUrl = getReqUrl(request, "/" + docId + "/");
		String mockData = mockService.getStaticMockData(docId,mockInterId, reqUrl, reqMethod, mockRespCode);
		ValidateUtils.isTrue(!StringUtils.isEmpty(mockData), ErrorCode.DOC_003);
		
		return JsonUtils.toObject(mockData, Object.class);
	}
	
	/**
	 * 
			*@name 获取mock数据，动态
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/dynamic/{docId}/**")
	public @ResponseBody Object getDynamicMockInfo(HttpServletRequest request,@PathVariable("docId")Long docId,
												Long mockInterId,String mockRespCode){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		ReqMethod reqMethod = ReqMethod.valueOf(request.getMethod());
		String reqUrl = getReqUrl(request, "/" + docId + "/");
		String mockData = mockService.getDynamicMockData(docId,mockInterId, reqUrl, reqMethod, mockRespCode);
		ValidateUtils.isTrue(!StringUtils.isEmpty(mockData), ErrorCode.DOC_003);
		
		return JsonUtils.toObject(mockData, Object.class);
	}
	
	/**
	 * 
			*@name mock配置界面预览mock数据，动态
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/dynamic/json/preview.htm")
	public @ResponseBody Map previewMockInfo(HttpServletRequest request,String mockRule){
		ValidateUtils.notNull(mockRule, ErrorCode.SYS_001,"mock规则不能为空");
		String mockData = MockUtils.getMockData(mockRule);
		//return JsonUtils.createSuccess(JsonUtils.toObject(mockData, Object.class));
		return JsonUtils.createSuccess(mockData);
	}
	
	//查询请求url
	private String getReqUrl(HttpServletRequest request,String prefix){
		String reqUri = request.getRequestURI();
		int index = reqUri.indexOf(prefix);
		String url = reqUri.substring(index + prefix.length(), reqUri.length());
		return url + WebUtil.getParamStr(request);
	}
}