package com.dev.doc.exports.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.dev.base.utils.MapUtils;
import com.dev.doc.entity.ApiDoc;
import com.dev.doc.entity.Inter;
import com.dev.doc.entity.InterParam;
import com.dev.doc.entity.InterResp;
import com.dev.doc.entity.Module;
import com.dev.doc.entity.RespSchema;
import com.dev.doc.service.InterParamService;
import com.dev.doc.service.InterRespService;

/**
 * 
		* <p>Title: 导出基类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年8月15日下午2:49:27</p>
 */
public abstract class BaseDocExportServiceAbstract<T> implements BaseDocExportService<T>{
	/**
	 * 默认模块id
	 */
	protected Long DEF_MODULE_ID = -1L;
	
	/**
	 * 默认模块名称
	 */
	protected String DEF_MODULE_NAME = "未分组";
	
	@Autowired
	private InterParamService interParamService;
	
	@Autowired
	private InterRespService interRespService;
	
	/**
	 * 
			*@name 查询文档相关的所有接口请求参数，接口id为key
			*@Description  
			*@CreateDate 2017年8月15日下午6:24:48
	 */
	protected Map<Long, List<InterParam>> getInterParamInfo(Long docId){
		List<InterParam> paramList = interParamService.listAllByDocId(docId);
		Map<Long, List<InterParam>> result = MapUtils.newMap();
		Long interId = -1L;
		for (InterParam interParam : paramList) {
			interId = interParam.getInterId();
			if (result.containsKey(interId)) {
				result.get(interId).add(interParam);
			}
			else{
				List<InterParam> temp = new ArrayList<InterParam>();
				temp.add(interParam);
				result.put(interId, temp);
			}
		}
		
		return result;
	}
	
	/**
	 * 
			*@name 查询文档相关的所有接口请求响应，接口id为key
			*@Description  
			*@CreateDate 2017年8月15日下午6:24:58
	 */
	protected Map<Long, List<InterResp>> getInterRespInfo(Long docId){
		List<InterResp> respList = interRespService.listAllByDocId(docId);
		Map<Long, List<InterResp>> result = MapUtils.newMap();
		Long interId = -1L;
		for (InterResp interResp : respList) {
			interId = interResp.getInterId();
			if (result.containsKey(interId)) {
				result.get(interId).add(interResp);
			}
			else{
				List<InterResp> temp = new ArrayList<InterResp>();
				temp.add(interResp);
				result.put(interId, temp);
			}
		}
		
		return result;
	}
	
	/**
	 * 
			*@name 将接口按模块分类
			*@Description  
			*@CreateDate 2017年8月16日下午4:51:32
	 */
	protected Map<Long, List<Inter>> getModuleInterInfo(List<Inter> interList){
		Map<Long, List<Inter>> result = MapUtils.newMap();
		List<Inter> tempList = null;
		Long moduleId = null;
		for (Inter inter : interList) {
			moduleId = inter.getModuleId() == null ? DEF_MODULE_ID : inter.getModuleId();
			tempList = result.get(moduleId);
			if (tempList == null) {
				tempList = new ArrayList<Inter>();
				tempList.add(inter);
				result.put(moduleId, tempList);
			}
			else{
				tempList.add(inter);
			}
		}
		
		return result;
	}
	
	/**
	 * 
			*@name 将公共数据结构按模块分类
			*@Description  
			*@CreateDate 2017年8月16日下午4:51:32
	 */
	protected Map<Long, List<RespSchema>> getModuleRespSchemaInfo(List<RespSchema> schemaList){
		Map<Long, List<RespSchema>> result = MapUtils.newMap();
		List<RespSchema> tempList = null;
		Long moduleId = null;
		for (RespSchema respSchema : schemaList) {
			moduleId = respSchema.getModuleId() == null ? DEF_MODULE_ID : respSchema.getModuleId();
			tempList = result.get(moduleId);
			if (tempList == null) {
				tempList = new ArrayList<RespSchema>();
				tempList.add(respSchema);
				result.put(moduleId, tempList);
			}
			else{
				tempList.add(respSchema);
			}
		}
		
		return result;
	}
	
	/**
	 * 
			*@name 组装公共数据结构信息,key:id,value:code
			*@Description  
			*@CreateDate 2017年8月16日下午4:51:32
	 */
	protected Map<Long, String> getRespSchemaCodeInfo(List<RespSchema> schemaList){
		Map<Long, String> result = MapUtils.newMap();
		for (RespSchema respSchema : schemaList) {
			result.put(respSchema.getId(), respSchema.getCode());
		}
		
		return result;
	}
		
	/**
	 * 
			*@name 创建未分组模块
			*@Description  
			*@CreateDate 2017年8月16日下午4:56:40
	 */
	protected Module buildDefModule() {
		Module module = new Module();
		module.setId(DEF_MODULE_ID);
		module.setName(DEF_MODULE_NAME);
		return module;
	}
	
	/**
	 * 
			*@name 组装模块信息,key:id,value:name
			*@Description  
			*@CreateDate 2017年8月15日下午6:35:50
	 */
	protected Map<Long, String> getModuleMap(List<Module> moduleList){
		Map<Long, String> moduleMap = MapUtils.newMap();
		for (Module module : moduleList) {
			moduleMap.put(module.getId(), module.getName());
		}
		
		return moduleMap;
	}
	
	/**
	 * 
			*@name 组装基路径
			*@Description  
			*@CreateDate 2017年8月15日下午6:38:35
	 */
	protected String parseBaseUrl(ApiDoc apiDoc){
		String scheme = StringUtils.isEmpty(apiDoc.getScheme()) ? "http" : apiDoc.getScheme().toLowerCase();
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(scheme)
				  .append("://")
				  .append(apiDoc.getHost())
				  .append(apiDoc.getBasePath());
		
		return urlBuilder.toString();
	}
}
