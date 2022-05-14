package com.dev.doc.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.base.constant.AppConstants;
import com.dev.base.constant.CfgConstants;
import com.dev.base.controller.BaseController;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.util.WebUtil;
import com.dev.base.utils.MapUtils;
import com.dev.base.utils.ValidateUtils;
import com.dev.doc.entity.ApiDoc;
import com.dev.doc.exports.service.BaseDocExportService;
import com.dev.doc.service.ApiDocService;
import com.dev.doc.vo.DocShareInfo;

import io.swagger.models.Swagger;

/**
 * 
		* <p>Title: 查看分享的 swagger api文档</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月13日下午2:20:08</p>
 */
@Controller
@RequestMapping("/pass/apidoc/share")
public class DocShareController extends BaseController{
	@Resource(name = "swaggerJsonDocExportServiceImpl")
	private BaseDocExportService<Swagger> swaggerJsonDocExportService;
	
	@Autowired
	private ApiDocService apiDocService;
	
	/**
	 * 
			*@name 查看分享的api文档入口
			*@Description  
			*@CreateDate 2015年8月13日下午2:24:29
	 */
	@RequestMapping("/forward.htm")
	public String forward(HttpServletRequest request,String shareKey){
		ValidateUtils.notNull(shareKey, ErrorCode.SYS_001,"文档信息不能为空");
		Long docId = apiDocService.decryptDocId(shareKey);
		ApiDoc apiDoc = apiDocService.getById(docId);
		ValidateUtils.notNull(apiDoc, ErrorCode.SYS_007,"该文档不存在");
		ValidateUtils.isTrue(apiDoc.isShare(), ErrorCode.SYS_006,"该文档已关闭分享");
		
		setShareInfo(request, shareKey, apiDoc);
		
		String redirectUrl = WebUtil.getRedirectUrl("/pass/apidoc/share/show.htm?shareKey=" + shareKey);
		if (!StringUtils.isEmpty(apiDoc.getSharePassword())) {//需要密码验证
			redirectUrl = WebUtil.getRedirectUrl("/pass/apidoc/share/forwardAuth.htm?shareKey=" + shareKey);
		}
		
		return redirectUrl;
	}
	
	/**
	 * 
			*@name 分享密码验证页面
			*@Description  
			*@CreateDate 2015年8月13日下午2:24:29
	 */
	@RequestMapping("/forwardAuth.htm")
	public String forwardAuth(HttpServletRequest request,String shareKey,Model model){
		DocShareInfo shareInfo = getShareInfo(request, shareKey);
		ValidateUtils.notNull(shareInfo, ErrorCode.SYS_001);
		
		String title = shareInfo.getApiDoc().getTitle();
		model.addAttribute("title", title);
		model.addAttribute("shareKey",shareKey);
		model.addAttribute("formAction","pass/apidoc/share/auth.htm");
		
		return "apidoc/shareAuth";
	}
	
	/**
	 * 
			*@name 分享密码验证
			*@Description  
			*@CreateDate 2015年8月13日下午2:24:29
	 */
	@RequestMapping("/auth.htm")
	public String auth(HttpServletRequest request,String shareKey,String password,Model model){
		DocShareInfo shareInfo = getShareInfo(request, shareKey);
		ValidateUtils.notNull(shareInfo, ErrorCode.SYS_001);
		ValidateUtils.notNull(password, ErrorCode.SYS_001,"访问密码不能为空");
		
		if (shareInfo.getApiDoc().getSharePassword().equals(password)) {
			shareInfo.setAuth(true);
			updateShareInfo(request, shareInfo);
			return WebUtil.getRedirectUrl("/pass/apidoc/share/show.htm?shareKey=" + shareKey);
		}
		else{
			model.addAttribute("title", shareInfo.getApiDoc().getTitle());
			model.addAttribute("shareKey",shareKey);
			model.addAttribute("errorMsg","访问密码错误");
			return "apidoc/shareAuth";
		}
	}
	
	/**
	 * 
			*@name 分享页面
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/show.htm")
	public String show(HttpServletRequest request,String shareKey,Model model){
		DocShareInfo shareInfo = getShareInfo(request, shareKey);
		//直接访问，重定向到分享页面入口
		if (shareInfo == null) {
			return WebUtil.getRedirectUrl("/pass/apidoc/share/forward.htm?shareKey=" + shareKey);
		}
		
		String docUrl = CfgConstants.WEB_BASE_URL + "pass/apidoc/share/json/build.htm?shareKey=" + shareKey;
		model.addAttribute("docUrl", docUrl);
		
		return "forward:/swagger/index.jsp";
	}
	
	/**
	 * 
			*@name 查看分享相关api文档
			*@Description  
			*@CreateDate 2015年8月13日下午2:24:29
	 */
	@RequestMapping("/json/build.htm")
	public @ResponseBody Swagger build(HttpServletRequest request,String shareKey,String condition){
		DocShareInfo shareInfo = getShareInfo(request, shareKey);
		ValidateUtils.isTrue(shareInfo != null && shareInfo.isAuth(), ErrorCode.SYS_006);
		return swaggerJsonDocExportService.exportDoc(null, shareInfo.getDocId(), null, condition);
	}
	
	//获取分享信息
	private DocShareInfo getShareInfo(HttpServletRequest request,String shareKey){
		Map<String, DocShareInfo> shareInfoMap = (Map<String, DocShareInfo>)WebUtil.getSessionAttr(request, AppConstants.SESSION_KEY_SHARE_INFO_MAP);
		return shareInfoMap == null ? null : shareInfoMap.get(shareKey);
	}
	
	//设置分享信息
	private void setShareInfo(HttpServletRequest request,String shareKey,ApiDoc apiDoc){
		Map<String, DocShareInfo> shareInfoMap = (Map<String, DocShareInfo>)WebUtil.getSessionAttr(request, AppConstants.SESSION_KEY_SHARE_INFO_MAP);
		if (shareInfoMap == null) {
			shareInfoMap = MapUtils.newMap();
		}
		
		DocShareInfo shareInfo = new DocShareInfo();
		//有设置密码，则需要授权
		shareInfo.setAuth(StringUtils.isEmpty(apiDoc.getSharePassword()));
		shareInfo.setDocId(apiDoc.getId());
		shareInfo.setApiDoc(apiDoc);
		shareInfo.setKey(shareKey);
		shareInfoMap.put(shareKey, shareInfo);
		
		WebUtil.setSessionAttr(request, AppConstants.SESSION_KEY_SHARE_INFO_MAP, shareInfoMap);
	}
	
	//更新分享信息
	private void updateShareInfo(HttpServletRequest request,DocShareInfo shareInfo){
		Map<String, DocShareInfo> shareInfoMap = (Map<String, DocShareInfo>)WebUtil.getSessionAttr(request, AppConstants.SESSION_KEY_SHARE_INFO_MAP);
		if (shareInfoMap ==  null) {
			shareInfoMap = MapUtils.newMap();
		}
		
		shareInfoMap.put(shareInfo.getKey(), shareInfo);
		WebUtil.setSessionAttr(request, AppConstants.SESSION_KEY_SHARE_INFO_MAP, shareInfoMap);
	}
}

