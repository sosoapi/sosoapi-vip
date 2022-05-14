package com.dev.doc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.base.constant.CfgConstants;
import com.dev.base.controller.BaseController;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.FreeMarkerUtil;
import com.dev.base.utils.CryptUtil;
import com.dev.base.utils.FormatUtils;
import com.dev.base.utils.ValidateUtils;
import com.dev.doc.exports.service.BaseDocExportService;
import com.dev.doc.exports.vo.DocDataInfo;

/**
 * 
		* <p>Title: html 预览 </p>
		* <p>Description: freemarker方式</p>
		* <p>CreateDate: 2017年8月21日上午10:27:21</p>
 */
@Controller
public class HtmlPreviewController extends BaseController {
	@Resource(name = "htmlDocExportServiceImpl")
	private BaseDocExportService<DocDataInfo> htmlDocExportService;
	
	/**
	 * 
			*@name 文档预览
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	/*@RequestMapping("/auth/apidoc/html/preview.htm")
	public String previewDoc(HttpServletRequest request,Model model,Long docId){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		
		Long userId = getUserId(request);
		DocDataInfo docDataInfo = htmlDocExportService.exportDoc(userId, docId, null, null);
		model.addAttribute("ftlContent", FreeMarkerUtil.getProcessContent(FreeMarkerUtil.TMPL_NAME_DOC_HTML, docDataInfo));
		return "ftl";
	}*/
	
	/**
	 * 
			*@name 文档预览
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/pass/apidoc/html/preview.htm")
	public String previewDoc(HttpServletRequest request,Model model,String authKey){
		ValidateUtils.notNull(authKey, ErrorCode.SYS_001,"授权信息不能为空");
		Map<String, Object> reqInfo = decrypt(authKey);
		ValidateUtils.notNull(CollectionUtils.isEmpty(reqInfo), ErrorCode.SYS_006);
		
		Long userId = FormatUtils.parseLong(reqInfo.get("userId"));
		Long docId = FormatUtils.parseLong(reqInfo.get("docId"));
		DocDataInfo docDataInfo = htmlDocExportService.exportDoc(userId, docId, null, null);
		model.addAttribute("ftlContent", FreeMarkerUtil.getProcessContent(FreeMarkerUtil.TMPL_NAME_DOC_HTML, docDataInfo));
		return "ftl";
	}
	
	/**
	 * 
			*@name 文档视图预览
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	/*@RequestMapping("/auth/doc/view/html/preview.htm")
	public String previewDocView(HttpServletRequest request,Model model,Long viewId,Long docId){
		ValidateUtils.notNull(viewId, ErrorCode.SYS_001,"视图id不能为空");
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		
		Long userId = getUserId(request);
		DocDataInfo docDataInfo = htmlDocExportService.exportDocView(userId, viewId, docId, null, null);
		model.addAttribute("ftlContent", FreeMarkerUtil.getProcessContent(FreeMarkerUtil.TMPL_NAME_DOC_HTML, docDataInfo));
		return "ftl";
	}*/
	
	/**
	 * 
			*@name 文档视图预览
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/pass/doc/view/html/preview.htm")
	public String previewDocView(HttpServletRequest request,Model model,String authKey){
		ValidateUtils.notNull(authKey, ErrorCode.SYS_001,"授权信息不能为空");
		Map<String, Object> reqInfo = decrypt(authKey);
		ValidateUtils.notNull(CollectionUtils.isEmpty(reqInfo), ErrorCode.SYS_006);
		
		Long userId = FormatUtils.parseLong(reqInfo.get("userId"));
		Long docId = FormatUtils.parseLong(reqInfo.get("docId"));
		Long viewId = FormatUtils.parseLong(reqInfo.get("viewId"));
		DocDataInfo docDataInfo = htmlDocExportService.exportDocView(userId, viewId, docId, null, null);
		model.addAttribute("ftlContent", FreeMarkerUtil.getProcessContent(FreeMarkerUtil.TMPL_NAME_DOC_HTML, docDataInfo));
		return "ftl";
	}
	
	//解密授权信息
	private Map<String,Object> decrypt(String authKey){
		if (StringUtils.isEmpty(authKey)) {
			return null;
		}
		
		return JsonUtils.toObject(CryptUtil.decryptAES(authKey,CfgConstants.SECURITY_SECRET_KEY), HashMap.class);
	}
}
