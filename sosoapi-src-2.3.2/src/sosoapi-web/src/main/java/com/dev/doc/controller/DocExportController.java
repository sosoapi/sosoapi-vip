package com.dev.doc.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.base.constant.AppConstants;
import com.dev.base.controller.BaseController;
import com.dev.base.enums.DocExportType;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.FreeMarkerUtil;
import com.dev.base.util.WebUtil;
import com.dev.base.utils.ValidateUtils;
import com.dev.doc.entity.ApiDoc;
import com.dev.doc.entity.DocArchive;
import com.dev.doc.entity.DocView;
import com.dev.doc.exports.json.filter.OperationFilter;
import com.dev.doc.exports.json.filter.ResponseFilter;
import com.dev.doc.exports.json.filter.SwaggerFilter;
import com.dev.doc.exports.json.filter.TagFilter;
import com.dev.doc.exports.service.BaseDocExportService;
import com.dev.doc.exports.vo.DocDataInfo;
import com.dev.doc.service.ApiDocService;
import com.dev.doc.service.DocArchiveService;
import com.dev.doc.service.DocViewService;
import com.dev.doc.vo.PostmanCollection;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.swagger.models.Operation;
import io.swagger.models.Response;
import io.swagger.models.Swagger;
import io.swagger.models.Tag;

/**
 * 
		* <p>Title: 导出文档</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年8月16日上午6:28:09</p>
 */
@Controller
public class DocExportController extends BaseController{
	@Resource(name = "swaggerJsonDocExportServiceImpl")
	private BaseDocExportService<Swagger> swaggerJsonDocExportService;
	
	@Resource(name = "htmlDocExportServiceImpl")
	private BaseDocExportService<DocDataInfo> htmlDocExportService;
	
	@Resource(name = "wordDocExportServiceImpl")
	private BaseDocExportService<DocDataInfo> wordDocExportService;
	
	@Resource(name = "postmanDocExportServiceImpl")
	private BaseDocExportService<PostmanCollection> postmanDocExportService;
	
	@Autowired
	private ApiDocService apiDocService;
	
	@Autowired
	private DocViewService docViewService;
	
	@Autowired
	private DocArchiveService docArchiveService;
	
	/**
	 * 
	 * 		 @name 导出文档
			*@Description  
			*@CreateDate 2016年1月14日下午4:22:38
	 */
	@RequestMapping("/auth/apidoc/export.htm")
	public void exportApiDoc(HttpServletRequest request,HttpServletResponse response,Long docId,DocExportType exportType) throws Exception{
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(exportType, ErrorCode.SYS_001,"导出文档类型不能为空");
		
		Long userId = getUserId(request);
		
		ApiDoc apiDoc = apiDocService.getById(docId);
		ValidateUtils.notNull(apiDoc, ErrorCode.SYS_007,"文档信息不存在");
		//下载文件名
		String fileName = buildFileName(apiDoc.getTitle(),apiDoc.getVersion(), exportType);
		
		response.setCharacterEncoding(AppConstants.DEF_CHARSET);
        response.setContentType("multipart/form-data;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, AppConstants.DEF_CHARSET));
        
        DocDataInfo docDataInfo = null;
        switch (exportType) {
			case swagger:
				Swagger swagger = swaggerJsonDocExportService.exportDoc(userId, docId, null, null);
				dealSwaggerFormat(response, swagger,false);
				break;
				
			case postman:
				PostmanCollection collection = postmanDocExportService.exportDoc(userId, docId, null, null);
				dealPostmanFormat(response, collection);
				break;
			
			case doc:
				docDataInfo = wordDocExportService.exportDoc(userId, docId, null, null);
				dealWordFormat(response, docDataInfo);
				break;
				
			case html:
				docDataInfo = htmlDocExportService.exportDoc(userId, docId, null, null);
				dealHtmlFormat(response, docDataInfo);
				break;
			
			case pdf:
				break;
		}
	}
	
	/**
	 * 
	 * 		 @name 导出文档视图
			*@Description  
			*@CreateDate 2016年1月14日下午4:22:38
	 */
	@RequestMapping("/auth/doc/view/export.htm")
	public void exportDocView(HttpServletRequest request,HttpServletResponse response,
								Long viewId,Long docId,DocExportType exportType) throws Exception{
		ValidateUtils.notNull(viewId, ErrorCode.SYS_001,"视图id不能为空");
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(exportType, ErrorCode.SYS_001,"导出文档类型不能为空");
		
		Long userId = getUserId(request);
		
		DocView docView = docViewService.getByDocId(viewId, docId);
		ValidateUtils.notNull(docView, ErrorCode.SYS_007,"文档视图信息不存在");
		//下载文件名
		String fileName = buildFileName(docView.getTitle(),docView.getVersion(), exportType);
		
		response.setCharacterEncoding(AppConstants.DEF_CHARSET);
        response.setContentType("multipart/form-data;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, AppConstants.DEF_CHARSET));
        
        DocDataInfo docDataInfo = null;
        switch (exportType) {
			case swagger:
				Swagger swagger = swaggerJsonDocExportService.exportDocView(null, viewId, docId, null, null);
				dealSwaggerFormat(response, swagger,false);
				break;
				
			case postman:
				PostmanCollection collection = postmanDocExportService.exportDocView(userId, viewId, docId, null, null);
				dealPostmanFormat(response, collection);
				break;
			
			case doc:
				docDataInfo = wordDocExportService.exportDocView(userId, viewId, docId, null, null);
				dealWordFormat(response, docDataInfo);
				break;
				
			case html:
				docDataInfo = htmlDocExportService.exportDocView(userId, viewId, docId, null, null);
				dealHtmlFormat(response, docDataInfo);
				break;
			
			case pdf:
				break;
		}
	}
	
	/**
	 * 
	 * 		 @name 导出文档归档
			*@Description  
			*@CreateDate 2016年1月14日下午4:22:38
	 */
	@RequestMapping("/auth/doc/archive/export.htm")
	public void exportDocArchive(HttpServletRequest request,HttpServletResponse response,
								Long archiveId,Long docId,DocExportType exportType) throws Exception{
		ValidateUtils.notNull(archiveId, ErrorCode.SYS_001,"归档id不能为空");
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(exportType, ErrorCode.SYS_001,"导出文档类型不能为空");
		
		DocArchive docArchive = docArchiveService.getByDocId(archiveId, docId);
		ValidateUtils.notNull(docArchive, ErrorCode.SYS_007,"归档信息不存在");
		//下载文件名
		String fileName = buildFileName(docArchive.getTitle(),null, exportType);
		
		response.setCharacterEncoding(AppConstants.DEF_CHARSET);
        response.setContentType("multipart/form-data;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, AppConstants.DEF_CHARSET));
        
        switch (exportType) {
			case swagger:
				String content = docArchiveService.loadArchive(docId, archiveId);
				WebUtil.printJson(response, JsonUtils.toObject(content, HashMap.class));
				break;
				
			case postman:
				break;
			
			case doc:
				break;
				
			case html:
				break;
			
			case pdf:
				break;
		}
	}
	
	//组装文件名
	private String buildFileName(String title,String version,DocExportType exportType){
		if (StringUtils.isEmpty(title)) {
			title = "apiDoc";
		}
		
		StringBuilder fileNameBuilder = new StringBuilder(title);
		if (!StringUtils.isEmpty(version)) {
			fileNameBuilder.append("_")
						   .append(version);
		}
		
		switch (exportType) {
			case swagger:
				fileNameBuilder.append("_swagger");
				break;
				
			case postman:
				fileNameBuilder.append("_postman");
				break;
				
			default:
				break;
		}
		
		fileNameBuilder.append("." + exportType.getFormat());
		return fileNameBuilder.toString();
	}
	
	//导出swagger格式
	private void dealSwaggerFormat(HttpServletResponse response,Swagger swagger,boolean standard){
		if (!standard) {
			WebUtil.printJson(response, swagger);
			return ;
		}
		
		//处理swagger官网格式
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//添加自定义属性过滤
		mapper.addMixInAnnotations(Swagger.class, SwaggerFilter.class);
		mapper.addMixInAnnotations(Operation.class, OperationFilter.class);
		mapper.addMixInAnnotations(Response.class, ResponseFilter.class);
		mapper.addMixInAnnotations(Tag.class, TagFilter.class);
		
		try {
            OutputStream outputStream = response.getOutputStream();
            byte[] content = mapper.writeValueAsString(swagger).getBytes(AppConstants.DEF_CHARSET);
            outputStream.write(content);
          
            outputStream.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	//导出postman格式
	private void dealPostmanFormat(HttpServletResponse response,PostmanCollection collection){
		WebUtil.printJson(response, collection);	
	}
	
	//导出word格式
	private void dealWordFormat(HttpServletResponse response,Object docDataInfo){
		try {
            Writer writer = new OutputStreamWriter(response.getOutputStream(),AppConstants.DEF_CHARSET);
            FreeMarkerUtil.process(FreeMarkerUtil.TMPL_DOC_WORD, docDataInfo, writer);
            
            writer.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	//导出html格式
	private void dealHtmlFormat(HttpServletResponse response,DocDataInfo docDataInfo){
		try {
            Writer writer = new OutputStreamWriter(response.getOutputStream(),AppConstants.DEF_CHARSET);
            FreeMarkerUtil.process(FreeMarkerUtil.TMPL_DOC_HTML, docDataInfo, writer);
            
            writer.close();
        } 
        catch (IOException e) {	
            e.printStackTrace();
        }
	}
}
