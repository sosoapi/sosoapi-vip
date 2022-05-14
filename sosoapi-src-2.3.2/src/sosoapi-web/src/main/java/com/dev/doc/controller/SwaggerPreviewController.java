package com.dev.doc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.base.constant.CfgConstants;
import com.dev.base.controller.BaseController;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.utils.ValidateUtils;
import com.dev.doc.exports.service.BaseDocExportService;
import com.dev.doc.service.DocArchiveService;

import io.swagger.models.Swagger;

/**
 * 
		* <p>Title: swagger 预览</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月13日下午2:20:08</p>
 */
@Controller
public class SwaggerPreviewController extends BaseController{
	@Resource(name = "swaggerJsonDocExportServiceImpl")
	private BaseDocExportService<Swagger> swaggerJsonDocExportService;
	
	@Autowired
	private DocArchiveService docArchiveService;
	
	/**
	 * 
			*@name 文档预览
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/auth/apidoc/preview.htm")
	public String previewDoc(HttpServletRequest request,Model model,Long docId,Long moduleId,String condition){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		StringBuilder docUrlBuilder = new StringBuilder();
		docUrlBuilder.append(CfgConstants.WEB_BASE_URL)
					 .append("auth/apidoc/json/build.htm?docId=").append(docId);
		if (moduleId != null) {
			docUrlBuilder.append("&moduleId=").append(moduleId);
		}
		
		if (!StringUtils.isEmpty(condition)) {
			docUrlBuilder.append("&condition=").append(condition);
		}
		
		model.addAttribute("docUrl", docUrlBuilder.toString());
		
		return "forward:/swagger/index.jsp";
	}
	
	/**
	 * 
			*@name 生成文档对应的swagger
			*@Description  
			*@CreateDate 2015年8月13日下午2:24:29
	 */
	@RequestMapping("/auth/apidoc/json/build.htm")
	public @ResponseBody Swagger buildDoc(HttpServletRequest request,Long docId,Long moduleId,String condition){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		
		Long userId = getUserId(request);
		return swaggerJsonDocExportService.exportDoc(userId, docId, moduleId, condition);
	}
	
	
	/**
	 * 
			*@name 文档视图预览
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/auth/doc/view/preview.htm")
	public String previewDocView(HttpServletRequest request,Model model,Long viewId,Long docId,String condition){
		ValidateUtils.notNull(viewId, ErrorCode.SYS_001,"视图id不能为空");
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		StringBuilder docUrlBuilder = new StringBuilder();
		docUrlBuilder.append(CfgConstants.WEB_BASE_URL)
					 .append("auth/doc/view/json/build.htm?docId=").append(docId)
					 .append("&viewId=").append(viewId);
		
		if (!StringUtils.isEmpty(condition)) {
			docUrlBuilder.append("&condition=").append(condition);
		}
		
		model.addAttribute("docUrl", docUrlBuilder.toString());
		
		return "forward:/swagger/index.jsp";
	}
	
	/**
	 * 
			*@name 生成文档视图对应的swagger
			*@Description  
			*@CreateDate 2015年8月13日下午2:24:29
	 */
	@RequestMapping("/auth/doc/view/json/build.htm")
	public @ResponseBody Swagger buildDocView(HttpServletRequest request,Long viewId,Long docId,String condition){
		ValidateUtils.notNull(viewId, ErrorCode.SYS_001,"视图id不能为空");
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		
		return swaggerJsonDocExportService.exportDocView(null, viewId, docId, null, condition);
	}
	
	/**
	 * 
			*@name 文档归档预览
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/auth/doc/archive/preview.htm")
	public String previewDocArchive(HttpServletRequest request,Model model,Long archiveId,Long docId,String condition){
		ValidateUtils.notNull(archiveId, ErrorCode.SYS_001,"归档id不能为空");
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		StringBuilder docUrlBuilder = new StringBuilder();
		docUrlBuilder.append(CfgConstants.WEB_BASE_URL)
					 .append("auth/doc/archive/json/build.htm?docId=").append(docId)
					 .append("&archiveId=").append(archiveId);
		
		if (!StringUtils.isEmpty(condition)) {
			docUrlBuilder.append("&condition=").append(condition);
		}
		
		model.addAttribute("docUrl", docUrlBuilder.toString());
		
		return "forward:/swagger/index.jsp";
	}
	
	/**
	 * 
			*@name 生成文档归档对应的swagger
			*@Description  
			*@CreateDate 2015年8月13日下午2:24:29
	 */
	@RequestMapping("/auth/doc/archive/json/build.htm")
	public @ResponseBody Map buildDocArchive(Long archiveId,Long docId,String condition){
		ValidateUtils.notNull(archiveId, ErrorCode.SYS_001,"归档id不能为空");
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		
		String jsonContent = docArchiveService.loadArchive(docId, archiveId);
		return JsonUtils.toObject(jsonContent, HashMap.class);
	}
	
	/**
	 * 
			*@name 预览demo
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/pass/apidoc/demo.htm")
	public String previewDemo(HttpServletRequest request,Model model){
		String docUrl = CfgConstants.WEB_BASE_URL + "pass/apidoc/demo/json/build.htm";
		model.addAttribute("docUrl", docUrl);
		
		return "forward:/swagger/index.jsp";
	}
	
	/**
	 * 
			*@name 生成demo对应的swagger
			*@Description  
			*@CreateDate 2015年8月13日下午2:24:29
	 */
	@RequestMapping("/pass/apidoc/demo/json/build.htm")
	public @ResponseBody Swagger buildDemo(HttpServletRequest request,String condition){
		return swaggerJsonDocExportService.exportDoc(null, 1L,null,condition);
	}
}
