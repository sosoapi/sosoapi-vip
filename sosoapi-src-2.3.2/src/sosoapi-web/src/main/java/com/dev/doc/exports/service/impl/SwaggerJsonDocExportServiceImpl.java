package com.dev.doc.exports.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.dev.base.constant.CfgConstants;
import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.MockType;
import com.dev.base.utils.MapUtils;
import com.dev.doc.entity.ApiDoc;
import com.dev.doc.entity.ApiEnv;
import com.dev.doc.entity.DocView;
import com.dev.doc.entity.Inter;
import com.dev.doc.entity.InterParam;
import com.dev.doc.entity.InterResp;
import com.dev.doc.entity.Module;
import com.dev.doc.entity.RespSchema;
import com.dev.doc.exports.service.BaseDocExportServiceAbstract;
import com.dev.doc.service.ApiDocService;
import com.dev.doc.service.ApiEnvService;
import com.dev.doc.service.ApiErrorCodeService;
import com.dev.doc.service.CommonParamService;
import com.dev.doc.service.DocViewService;
import com.dev.doc.service.InterService;
import com.dev.doc.service.MockService;
import com.dev.doc.service.ModuleService;
import com.dev.doc.service.RespSchemaService;
import com.dev.doc.util.DocBuildUtil;
import com.dev.doc.vo.ApiEnvInfo;
import com.dev.doc.vo.ApiErrorCodeInfo;

import io.swagger.models.Info;
import io.swagger.models.Model;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import io.swagger.models.Tag;

/**
 * 
		* <p>Title: Swagger Json导出</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年8月15日下午2:50:06</p>
 */
@Service
public class SwaggerJsonDocExportServiceImpl extends BaseDocExportServiceAbstract<Swagger>{
	@Autowired
	private ApiDocService apiDocService;
	
	@Autowired
	private CommonParamService commonParamService;
	
	@Autowired
	private ApiErrorCodeService apiErrorCodeService;
	
	@Autowired
	private ApiEnvService apiEnvService;
	
	@Autowired
	private InterService interService;
	
	@Autowired
	private RespSchemaService respSchemaService;
	
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private MockService mockService;
	
	@Autowired
	private DocViewService docViewService;
	
	@Override
	public Swagger exportDoc(Long userId, Long docId, Long moduleId, String condition) {
		Swagger swagger = new Swagger();
		ApiDoc apiDoc = apiDocService.getById(docId);
		if (apiDoc == null) {
			return swagger;
		}
		
		//公共请求参数
		List<InterParam> commonParamList = commonParamService.listByDocId(docId);
		
		//公共响应信息
		Map<Long, String> refSchemaMap = respSchemaService.getAllByDocId(docId);
		
		//查询所有的模块信息列表
		List<Module> moduleList = moduleService.listAllByDocId(docId);
		//模块信息
		Map<Long, String> moduleMap = getModuleMap(moduleList);
		
		//创建api环境
		createApiEnv(swagger,docId);
		
		//创建api错误码
		createApiErrorCode(swagger,apiErrorCodeService.listInfoByDocId(docId, null, null));
		
		//创建公共请求参数
		createCommonParam(swagger,commonParamList,refSchemaMap);
				
		//创建文档基本信息
		createBasicInfo(swagger, apiDoc);
		
		//创建公用数据结构
		createDefinition(swagger,docId,refSchemaMap);
		
		//创建文档标签信息
		createTags(swagger,moduleList);
		
		//创建方法
		createPath(swagger, docId, interService.listAllByDocId(docId,moduleId,null,condition), 
					refSchemaMap, moduleMap,commonParamList);
		
		return swagger;
	}
	
	@Override
	public Swagger exportDocView(Long userId,Long viewId,Long docId,Long moduleId,String condition) {
		Swagger swagger = new Swagger();
		DocView docView = docViewService.getByDocId(viewId, docId);
		ApiDoc apiDoc = apiDocService.getById(docId);
		if (docView == null || apiDoc == null) {
			return swagger;
		}
		
		//公共请求参数
		List<InterParam> commonParamList = commonParamService.listByDocId(docId);
				
		//公共响应信息
		Map<Long, String> refSchemaMap = respSchemaService.getAllByDocId(docId);
		
		//查询所有的模块信息列表
		List<Module> moduleList = moduleService.listByViewId(viewId);
		//模块信息
		Map<Long, String> moduleMap = getModuleMap(moduleList);
		
		//创建api环境
		createApiEnv(swagger,docId);
		
		//创建api错误码
		createApiErrorCode(swagger,apiErrorCodeService.listInfoByViewId(viewId));
		
		//创建公共请求参数
		createCommonParam(swagger,commonParamList,refSchemaMap);
		
		//创建文档基本信息
		createBasicInfo(swagger, apiDoc);
		
		//创建公用数据结构
		createDefinition(swagger,docId,refSchemaMap);
		
		//创建文档标签信息
		createTags(swagger,moduleList);
		
		//创建方法
		createPath(swagger, docId, interService.listByViewId(viewId,condition),
					refSchemaMap, moduleMap,commonParamList);
		
		return swagger;
	}
	
	//创建api环境
	private void createApiEnv(Swagger swagger,Long docId){
		List<ApiEnvInfo> result = new ArrayList<ApiEnvInfo>();
		List<ApiEnv> envList = apiEnvService.listByDocId(docId,null);
		ApiEnvInfo envInfo = null;
		for (ApiEnv apiEnv : envList) {
			envInfo = new ApiEnvInfo();
			envInfo.setName(apiEnv.getName());
			envInfo.setBaseUrl(apiEnv.getBaseUrl());
			envInfo.setSortWeight(apiEnv.getSortWeight());
			envInfo.setStatus(apiEnv.getStatus());
			envInfo.setMock(false);
			
			result.add(envInfo);
		}
		
		ApiEnvInfo mockEnv = new ApiEnvInfo();
		mockEnv.setName(CfgConstants.MOCK_ENV_NAME);
		mockEnv.setBaseUrl(mockService.getMockUrl(docId, MockType.auto));
		mockEnv.setSortWeight(Integer.MAX_VALUE);
		mockEnv.setMock(true);
		mockEnv.setStatus(EnableStatus.on);
		result.add(mockEnv);
		
		swagger.setApiEnvs(result);
	}
	
	//创建api错误码
	private void createApiErrorCode(Swagger swagger,List<ApiErrorCodeInfo> codeList){
		swagger.setErrorCodes(codeList);
	}
	
	//创建公共请求参数
	private void createCommonParam(Swagger swagger,List<InterParam> commonParamList,Map<Long, String> refSchemaMap){
		swagger.setCommonParams(DocBuildUtil.parseParameter(commonParamList, refSchemaMap));
	}
	
	//创建文档标签信息
	private void createTags(Swagger swagger,List<Module> moduleList){
		List<Tag> tagList = new ArrayList<Tag>();
		Tag tag = null;
		for (Module module : moduleList) {
			tag = new Tag();
			tag.setName(module.getName());
			tag.setDescription(module.getDescription());
			tag.setSortWeight(module.getSortWeight());
			tagList.add(tag);
		}
		
		swagger.setTags(tagList);
	}
	
	//创建文档基本信息
	private void createBasicInfo(Swagger swagger,ApiDoc apiDoc){
		swagger.setHost(apiDoc.getHost());
		swagger.setBasePath(apiDoc.getBasePath());
		swagger.setSchemes(DocBuildUtil.buildSchemeList(apiDoc.getScheme()));
		swagger.setConsumes(DocBuildUtil.buildConsumeList(apiDoc.getConsume()));
		swagger.setProduces(DocBuildUtil.buildProduceList(apiDoc.getProduce()));
		
		Info info = new Info();
		info.setDescription(apiDoc.getDescription());
		info.setTitle(apiDoc.getTitle());
		info.setVersion(apiDoc.getVersion());
		swagger.setInfo(info);
	}
	
	//创建公用数据结构
	private void createDefinition(Swagger swagger,Long docId,Map<Long, String> refSchemaMap){
		List<RespSchema> schemaList = respSchemaService.listAllByDocId(docId);
		Map<String, Model> definitionMap = MapUtils.newMap();
		Model model = null;
		for (RespSchema respSchema : schemaList) {
			model = DocBuildUtil.buildModel(respSchema,refSchemaMap);
			definitionMap.put(respSchema.getCode(), model);
		}

		swagger.setDefinitions(definitionMap);
	}
	
	//创建方法
	private void createPath(Swagger swagger,Long docId,List<Inter> interList,Map<Long, String> refSchemaMap,
								Map<Long, String> moduleMap,List<InterParam> commonParamList){
		//获取文档所有的接口请求参数信息
		Map<Long, List<InterParam>> paramInfoMap = getInterParamInfo(docId);
		//获取文档所有的接口请求响应参数
		Map<Long, List<InterResp>> respInfoMap = getInterRespInfo(docId);
		
		boolean isCommonParamEmpty = CollectionUtils.isEmpty(commonParamList);
		LinkedHashMap<String,Path> pathMap = new LinkedHashMap<String,Path>();
		List<InterParam> reqParamList = null;
		List<InterResp> respList = null;
		Long interId = null;
		Path path = null;
		Operation operation = null;
		for (Inter inter : interList) {
			interId = inter.getId();
			reqParamList = paramInfoMap.containsKey(interId) ? paramInfoMap.get(interId) : Collections.EMPTY_LIST;
			
			//处理公共请求参数
			//由swagger ui直接渲染
//			if (!isCommonParamEmpty) {
//				if (CollectionUtils.isEmpty(reqParamList)) {
//					reqParamList = commonParamList;
//				}
//				else{
//					reqParamList.addAll(0, commonParamList);
//				}
//			}
			
			respList = respInfoMap.containsKey(interId) ? respInfoMap.get(interId) : Collections.EMPTY_LIST;
			operation = DocBuildUtil.buildOperation(inter, reqParamList, respList, moduleMap,refSchemaMap);
			path = DocBuildUtil.buildPath(pathMap.get(inter.getPath()),inter.getMethod(),operation);

			pathMap.put(inter.getPath(), path);
		}

		swagger.setPaths(pathMap);
	}
}
