package com.dev.doc.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dev.base.constant.AppConstants;
import com.dev.base.constant.CfgConstants;
import com.dev.base.controller.BaseController;
import com.dev.base.exception.ValidateException;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.WebUtil;
import com.dev.base.utils.ValidateUtils;
import com.dev.doc.entity.ApiDoc;
import com.dev.doc.imports.service.BaseDocImportService;

/**
 * 
		* <p>Title: 导入文档</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年8月16日上午6:27:54</p>
 */
@Controller
public class DocImportController extends BaseController{
	@Resource(name = "swaggerJsonDocImportServiceImpl")
	private BaseDocImportService<Map<String, Object>> swaggerJsonDocImportService;
	
	/**
	 * 
	 * 		 @name 导入swagger json格式
			*@Description  文件导入
			*@CreateDate 2016年1月14日下午4:22:38
	 */
	@RequestMapping(value = "/auth/apidoc/importByFile.htm", method = RequestMethod.POST)
	public String importByFile(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "jsonFile") MultipartFile file) throws Exception{
		ValidateUtils.notNull(file, ErrorCode.SYS_001,"api json文档不能为空");
		return importDoc(request, file.getInputStream());
	}
	
	/**
	 * 
	 * 		 @name 导入swagger json格式
			*@Description  url导入
			*@CreateDate 2016年1月14日下午4:22:38
	 */
	@RequestMapping(value = "/auth/apidoc/importByUrl.htm", method = RequestMethod.POST)
	public String importByUrl(HttpServletRequest request,HttpServletResponse response,String jsonUrl) throws Exception{
		ValidateUtils.notNull(jsonUrl, ErrorCode.SYS_001,"json url不能为空");
		if (!jsonUrl.toLowerCase().startsWith("http")) {
			throw new ValidateException(ErrorCode.SYS_001, "url格式错误");
		}
		
		org.springframework.core.io.Resource resource = new UrlResource(jsonUrl);
		return importDoc(request, resource.getInputStream());
	}
	
	//导入文档
	private String importDoc(HttpServletRequest request,InputStream inputStream) throws Exception{
		Long userId = getUserId(request);
		String content = IOUtils.toString(inputStream,AppConstants.DEF_CHARSET);
		Map<String, Object> docInfo = JsonUtils.toObject(content, HashMap.class);
		ApiDoc apiDoc = swaggerJsonDocImportService.importDoc(userId, docInfo);
		
		//更新缓存权限
		updateUserProjRole(request, apiDoc.getProjId(), apiDoc.getId(), CfgConstants.PROJ_ROLE_ID_ADMIN);
		
		return WebUtil.getRedirectUrl("/auth/proj/list.htm");
	}
}
