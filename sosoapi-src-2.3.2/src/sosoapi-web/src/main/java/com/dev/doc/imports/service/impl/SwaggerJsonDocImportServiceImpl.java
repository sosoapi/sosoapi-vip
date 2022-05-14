package com.dev.doc.imports.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.dev.base.constant.CfgConstants;
import com.dev.base.enums.DevStatus;
import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.ParamPosition;
import com.dev.base.enums.ReqMethod;
import com.dev.base.enums.ReqScheme;
import com.dev.base.enums.SchemaType;
import com.dev.base.json.JsonUtils;
import com.dev.base.utils.FormatUtils;
import com.dev.base.utils.MapUtils;
import com.dev.doc.entity.ApiDoc;
import com.dev.doc.entity.ApiEnv;
import com.dev.doc.entity.ApiErrorCode;
import com.dev.doc.entity.Inter;
import com.dev.doc.entity.InterParam;
import com.dev.doc.entity.InterResp;
import com.dev.doc.entity.Module;
import com.dev.doc.entity.RespSchema;
import com.dev.doc.imports.service.BaseDocImportServiceAbstract;
import com.dev.doc.service.ApiEnvService;
import com.dev.doc.service.ApiErrorCodeService;
import com.dev.doc.service.CommonParamService;
import com.dev.doc.service.InterParamService;
import com.dev.doc.service.InterRespService;
import com.dev.doc.service.InterService;
import com.dev.doc.service.ModuleService;
import com.dev.doc.service.RespSchemaService;
import com.dev.doc.util.DocBuildUtil;
import com.dev.doc.vo.SchemaNodeInfo;

/**
 * 
		* <p>Title: Swagger Json导入</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年8月15日下午2:50:06</p>
 */
@Service
public class SwaggerJsonDocImportServiceImpl extends BaseDocImportServiceAbstract<Map<String,Object>>{
	@Autowired
	private RespSchemaService respSchemaService;
	
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private ApiEnvService apiEnvService;
	
	@Autowired
	private InterService interService;
	
	@Autowired
	private InterParamService interParamService;
	
	@Autowired
	private InterRespService interRespService;
	
	@Autowired
	private ApiErrorCodeService apiErrorCodeService;
	
	@Autowired
	private CommonParamService commonParamService;
	
	@Override
	public ApiDoc importDoc(Long userId, Map<String, Object> docInfo) {
		//保存文档信息
		ApiDoc apiDoc = saveApiDoc(userId, docInfo);
		Long docId = apiDoc.getId();
		
		//保存模块信息
		saveModule(docId, docInfo);
		Map<String, Long> moduleInfo = getModuleInfo(docId);
		
		//保存公共数据结构信息
		Map<String, Long> respSchemaInfo = saveRespSchema(docId, docInfo);
		
		//保存接口
		saveInter(docId, docInfo, moduleInfo, respSchemaInfo);
		
		//保存环境基路径
		saveApiEnv(docId, docInfo);
		
		//保存错误码
		saveApiErrorCode(docId, docInfo);
				
		//保存公共请求参数
		saveCommonParam(docId, docInfo, respSchemaInfo);
		
		return apiDoc;
	}

	//保存文档信息
	private ApiDoc saveApiDoc(Long userId, Map<String, Object> docInfo) {
		ApiDoc apiDoc = new ApiDoc();
		apiDoc.setOpen(true);
		apiDoc.setPub(false);
		apiDoc.setHost((String)docInfo.get("host"));
		apiDoc.setBasePath((String)docInfo.get("basePath"));
		apiDoc.setScheme(parseList((List<String>)docInfo.get("schemes"),true));
		apiDoc.setConsume(parseList((List<String>)docInfo.get("consumes"),false));
		apiDoc.setProduce(parseList((List<String>)docInfo.get("produces"),false));
		
		Map<String, Object> infoMap = (Map<String,Object>)docInfo.get("info");
		if (!CollectionUtils.isEmpty(infoMap)) {
			apiDoc.setDescription((String)infoMap.get("description"));
			apiDoc.setTitle((String)infoMap.get("title"));
			//设置默认标题
			if (StringUtils.isEmpty(apiDoc.getTitle())) {
				apiDoc.setTitle(CfgConstants.SWAGGER_IMPORT_DEF_TITLE);
			}
			
			apiDoc.setVersion((String)infoMap.get("version"));
		}
		
		return saveApiDoc(userId, apiDoc);
	}

	//保存模块信息
	private void saveModule(Long docId, Map<String, Object> docInfo) {
		List<Module> moduleList = new ArrayList<Module>();
		
		//直接从定义中组装
		List<Map> tagList = (List<Map>)docInfo.get("tags");
		Set<String> tagNameSet = new HashSet<String>();
		int sortWeight = 1;
		if (!CollectionUtils.isEmpty(tagList)) {
			String tagName = "";
			Module module = null;
			for (Map tagInfo : tagList) {
				tagName = (String)tagInfo.get("name");
				if (!StringUtils.isEmpty(tagName) && !tagNameSet.contains(tagName)) {
					module = new Module();
					module.setSortWeight(sortWeight ++);
					module.setName(tagName);
					module.setDescription((String)tagInfo.get("description"));
					module.setDocId(docId);
					
					tagNameSet.add(tagName);
					moduleList.add(module);
				}
			}
		}
		
		//从path中组装
		Map<String, Object> pathMap = (Map<String, Object>)docInfo.get("paths");
		if (!CollectionUtils.isEmpty(pathMap)) {
			Set<String> pathKeySet = pathMap.keySet();
			Map<String, Object> methodMap = null;
			Set<String> methodKeySet = null;
			List<String> methodTagList = null;
			Module module = null;
			Map method = null;
			for (String pathKey : pathKeySet) {
				methodMap = (Map<String, Object>)pathMap.get(pathKey);
				methodKeySet = methodMap.keySet();
				for (String methodKey : methodKeySet) {
					method = (Map)methodMap.get(methodKey);
					if (CollectionUtils.isEmpty(method)) {
						continue ;
					}
					
					methodTagList = (List<String>)method.get("tags");
					if (!CollectionUtils.isEmpty(methodTagList)) {
						for (String methodTag : methodTagList) {
							if (!tagNameSet.contains(methodTag)) {
								module = new Module();
								module.setSortWeight(sortWeight ++);
								module.setName(methodTag);
								module.setDocId(docId);
								
								tagNameSet.add(methodTag);
								moduleList.add(module);
							}
						}
					}
				}
			}
		}
		
		moduleService.batchAdd(moduleList);
	}

	//保存环境基路径
	private void saveApiEnv(Long docId, Map<String, Object> docInfo) {
		ArrayList<ApiEnv> result = new ArrayList<ApiEnv>();
		ArrayList<Map> envList = (ArrayList<Map>)docInfo.get("apiEnvs");
		if (CollectionUtils.isEmpty(envList)) {
			return ;
		}
		
		ApiEnv apiEnv = null;
		int sortWeight = 1;
		for (Map temp : envList) {
			if ((Boolean)temp.get("mock") == Boolean.TRUE) {
				continue ;
			}
			
			apiEnv = new ApiEnv();
			apiEnv.setBaseUrl((String)temp.get("baseUrl"));
			apiEnv.setName((String)temp.get("name"));
			apiEnv.setSortWeight(sortWeight ++);
			apiEnv.setDocId(docId);
			apiEnv.setDescription((String)temp.get("description"));
			apiEnv.setStatus(EnableStatus.valueOf((String)temp.get("status"), EnableStatus.on));
			result.add(apiEnv);
		}
		
		apiEnvService.batchAdd(result);
	}
	
	//保存错误码
	private void saveApiErrorCode(Long docId, Map<String, Object> docInfo) {
		ArrayList<ApiErrorCode> result = new ArrayList<ApiErrorCode>();
		ArrayList<Map> errorCodeList = (ArrayList<Map>)docInfo.get("errorCodes");
		if (CollectionUtils.isEmpty(errorCodeList)) {
			return ;
		}
		
		ApiErrorCode apiErrorCode = null;
		int sortWeight = 1;
		for (Map temp : errorCodeList) {
			apiErrorCode = new ApiErrorCode();
			apiErrorCode.setDocId(docId);
			apiErrorCode.setSortWeight(sortWeight ++);
			apiErrorCode.setCode((String)temp.get("code"));
			apiErrorCode.setMsg((String)temp.get("msg"));
			apiErrorCode.setDescription((String)temp.get("description"));
			result.add(apiErrorCode);
		}
		
		apiErrorCodeService.batchAdd(result);
	}

	//保存公共请求参数
	private void saveCommonParam(Long docId,Map<String,Object> docInfo,Map<String,Long> defMap){
		List<InterParam> result = new ArrayList<InterParam>();
		ArrayList<Map> paramList = (ArrayList<Map>)docInfo.get("commonParams");
		if (CollectionUtils.isEmpty(paramList)) {
			return ;
		}
		
		result = parseInterParam(docId, null, paramList, defMap);
		commonParamService.batchAdd(result);
	}
	
	//保存公共数据结构
	private Map<String,Long> saveRespSchema(Long docId, Map<String, Object> docInfo){
		Map<String, Object> defMap = (Map)docInfo.get("definitions");
		if (CollectionUtils.isEmpty(defMap)) {
			return new HashMap<String,Long>();
		}
		
		Map<String, Long> defDealedMap = saveRespSchemaCode(docId,docInfo);
		Set<String> defCodeSet = defMap.keySet();
		RespSchema respSchema = null;
		Map defInfo = null;
		for (String defCode : defCodeSet) {
			defInfo = (Map)defMap.get(defCode);
			defInfo.put("defCode", defCode);
			
			respSchema = parseRespSchema(docId, (Map)defMap.get(defCode), defDealedMap);
			//之前已保存过，当前直接更新
			respSchemaService.updateByDocId(respSchema);
		}
		
		return defDealedMap;
	}
	
	//保存接口
	private void saveInter(Long docId,Map<String,Object> docInfo,
								Map<String,Long> moduleMap,Map<String,Long> defMap){
		Map<String,Object> pathsMap = (Map)docInfo.get("paths");
		if (CollectionUtils.isEmpty(pathsMap)) {
			return ;
		}
		
		Set<String> pathSet = pathsMap.keySet();
		Map<String,Object> methodsMap = null;
		Map<String,Object> methodMap = null;
		Set<String> methodSet = null;
		Inter inter = null;
		Long interId = null;
		List<InterParam> paramList = null;
		List<InterResp> respList = null;
		//不同path
		for (String path : pathSet) {
			methodsMap = (Map)pathsMap.get(path);
			methodSet = methodsMap.keySet();
			//同一path下的不同请求
			for (String method : methodSet) {
				methodMap = (Map)methodsMap.get(method);
				if (CollectionUtils.isEmpty(methodMap)) {
					continue ;
				}
				
				//保存接口
				inter = parseInter(docId, path, 
						ReqMethod.valueOf(method.toUpperCase()), methodMap, moduleMap);
				interService.add(inter);
				
				//保存接口参数
				interId = inter.getId();
				paramList = parseInterParam(docId, interId, methodMap, defMap);
				interParamService.batchAdd(paramList);
				
				//保存接口响应
				respList = parseInterResp(docId, interId, methodMap, defMap);
				interRespService.batchAdd(respList);
			}
		}
	}
	
	//组装接口信息
	private Inter parseInter(Long docId,String path,ReqMethod reqMethod,
							Map<String,Object> interInfo,Map<String,Long> moduleMap){
		Inter inter = new Inter();
		inter.setDocId(docId);
		inter.setName((String)interInfo.get("summary"));
		if (StringUtils.isEmpty(inter.getName())) {
			inter.setName(reqMethod.name() + "_" + path);
		}
		
		inter.setConsume(DocBuildUtil.parseStr((List<String>)interInfo.get("consumes")));
		inter.setProduce(DocBuildUtil.parseStr((List<String>)interInfo.get("produces")));
		inter.setDeprecated(parseDeprecated(interInfo));
		inter.setSkipCommonParam(parseSkipCommonParam(interInfo));
		inter.setDescription((String)interInfo.get("description"));
		inter.setMethod(reqMethod);
		inter.setPath(path);
		inter.setDevStatus(parseDevStatus(interInfo));
		inter.setOperationId((String)interInfo.get("operationId"));
		inter.setLabel((String)interInfo.get("label"));
		inter.setDeveloper((String)interInfo.get("developer"));
		
		if (interInfo.containsKey("sortWeight")) {
			inter.setSortWeight((Integer)interInfo.get("sortWeight"));
		}
		
		List<String> schemeList = (List<String>)interInfo.get("schemes");
		if (!CollectionUtils.isEmpty(schemeList)) {
			inter.setScheme(ReqScheme.valueOf(schemeList.get(0).toUpperCase()));
		}
		else{
			inter.setScheme(ReqScheme.HTTP);
		}
		
		List<String> tagList = (List<String>)interInfo.get("tags");
		if (!CollectionUtils.isEmpty(tagList)) {
			//默认只处理第一个
			inter.setModuleId(moduleMap.get(tagList.get(0)));
		}
		
		return inter;
	}
	
	//解析开发状态
	private DevStatus parseDevStatus(Map<String,Object> interInfo){
		if (CollectionUtils.isEmpty(interInfo) || !interInfo.containsKey("devStatus")) {
			return DevStatus.none;
		}
		
		return DevStatus.valueOf((String)interInfo.get("devStatus"));
	}
	
	//解析是否弃用
	private boolean parseDeprecated(Map<String,Object> interInfo){
		if (CollectionUtils.isEmpty(interInfo) || !interInfo.containsKey("deprecated")) {
			return false;
		}
		
		Object deprecated = interInfo.get("deprecated");
		return deprecated == null ? false : Boolean.valueOf(deprecated.toString());
	}
	
	//解析是否过滤公共请求参数
	private boolean parseSkipCommonParam(Map<String,Object> interInfo){
		if (CollectionUtils.isEmpty(interInfo) || !interInfo.containsKey("skipCommonParam")) {
			return false;
		}
		
		return (Boolean)interInfo.get("skipCommonParam");
	}
		
	//组装接口请求参数
	private List<InterParam> parseInterParam(Long docId,Long interId,Map<String,Object> methodMap,Map<String,Long> defMap){
		List<InterParam> result = new ArrayList<InterParam>();
		List<Map> paramList = (List<Map>)methodMap.get("parameters");
		if (CollectionUtils.isEmpty(paramList)) {
			return result;
		}
		
		return parseInterParam(docId, interId, paramList, defMap);
	}
	
	//将swagger请求参数格式转换为数据库结构
	private List<InterParam> parseInterParam(Long docId,Long interId,List<Map> paramList,Map<String,Long> defMap){
		List<InterParam> result = new ArrayList<InterParam>();
		if (CollectionUtils.isEmpty(paramList)) {
			return result;
		}
		
		InterParam interParam = null;
		SchemaType type = null;
		for (Map param : paramList) {
			type = parseSchemaType(param);
			
			interParam = new InterParam();
			interParam.setPosition(ParamPosition.valueOf((String)param.get("in")));
			interParam.setCode((String)param.get("name"));
			interParam.setDescription((String)param.get("description"));
			interParam.setType(type);
			if (param.containsKey("default")) {
				interParam.setDefValue(param.get("default").toString());
			}
			interParam.setDocId(docId);
			interParam.setFormat(interParam.getType().getFormat());
			interParam.setInterId(interId);
			if (param.containsKey("required")) {
				interParam.setRequired((Boolean)param.get("required"));
			}
			else{
				interParam.setRequired(false);
			}
			
			switch (type) {
				case sys_ref :
					interParam.setRefSchemaId(defMap.get(parseRefName(type, param)));
					break;

				case sys_object:
				case sys_array:
					//interParam.setCustSchema((String)param.get("custSchema"));
					break;
				
				case cust_json:
					interParam.setExtSchema(parseExtSchema(param));
					break;
					
				default :
					break;
			}
			
			result.add(interParam);
		}
		
		return result;
	}
	
	//组装接口响应
	private List<InterResp> parseInterResp(Long docId,Long interId,Map<String,Object> methodMap,Map<String,Long> defMap){
		List<InterResp> result = new ArrayList<InterResp>();
		Map<String,Object> respsMap = (Map)methodMap.get("responses");
		if (CollectionUtils.isEmpty(respsMap)) {
			return result;
		}
		
		Set<String> respsKeySet = respsMap.keySet();
		Map<String,Object> respMap = null;
		InterResp resp = null;
		SchemaType type = null;
		for (String respKey : respsKeySet) {
			respMap = (Map)respsMap.get(respKey);
			type = parseSchemaType(respMap);
			
			resp = new InterResp();
			resp.setCode(respKey);
			resp.setDocId(docId);
			resp.setInterId(interId);
			resp.setDescription((String)respMap.get("description"));
			resp.setType(type);
			if (respMap.containsKey("sortWeight")) {
				resp.setSortWeight((Integer)respMap.get("sortWeight"));
			}
			
			switch (type) {
				case sys_ref :
					resp.setRefSchemaId(defMap.get(parseRefName(type,respMap)));
					break;

				case cust_json:
					resp.setExtSchema(parseExtSchema(respMap));
					break;
					
				case sys_array:
				case sys_object:
					resp.setCustSchema(parseSchema(type,respKey, (Map)respMap.get("schema"),defMap));
					break;
					
				default :
					break;
			}
			
			result.add(resp);
		}
		
		return result;
	}
		
	//组装公共数据
	private RespSchema parseRespSchema(Long docId,Map<String,Object> defInfo,Map<String,Long> defDealedMap){
		SchemaType defType = parseSchemaType(defInfo);
		String defCode = (String)defInfo.get("defCode");
		
		RespSchema respSchema = new RespSchema();
		respSchema.setId(defDealedMap.get(defCode));
		respSchema.setCode(defCode);
		respSchema.setType(defType);
		respSchema.setDocId(docId);
		respSchema.setDescription((String)defInfo.get("description"));
		
		switch (defType) {
			case sys_ref :
				respSchema.setRefSchemaId(defDealedMap.get(parseRefName(defType,defInfo)));
				break;

			case cust_json:
				break;
				
			case sys_object:
			case sys_array:
				respSchema.setCustSchema(parseSchema(defType,defCode, defInfo,defDealedMap));
				break;
				
			default :
				break;
		}
		return respSchema;
	}
	
	//组装schema
	private String parseSchema(SchemaType type,String key,Map<String, Object> value,Map<String,Long> defDealedMap){
		if (CollectionUtils.isEmpty(value)) {
			return "";
		}
		value = preDealRequired(value);
		
		SchemaNodeInfo nodeInfo = new SchemaNodeInfo();
		nodeInfo.setType(type);
		nodeInfo.setNodeId("");
		nodeInfo.setCode(key);
		nodeInfo.setDescription((String)value.get("description"));
		nodeInfo.setRequired(parseRequired(value));
		parseSchemaNode(nodeInfo, value,defDealedMap);
		
		//递归获取所有子节点
		List<SchemaNodeInfo> result = new ArrayList<SchemaNodeInfo>();
		parseChildList(result,nodeInfo);
		return JsonUtils.toJson(result);
	}
	
	//递归获取子节点
	private void parseChildList(List<SchemaNodeInfo> result,SchemaNodeInfo nodeInfo){
		List<SchemaNodeInfo> childList = nodeInfo.getChildList();
		if (CollectionUtils.isEmpty(childList)) {
			return ;
		}
		
		for (SchemaNodeInfo schemaNodeInfo : childList) {
			result.add(schemaNodeInfo);
			if (!CollectionUtils.isEmpty(schemaNodeInfo.getChildList())) {
				parseChildList(result, schemaNodeInfo);
			}
		}
		
		nodeInfo.setChildList(Collections.EMPTY_LIST);
	}
		
	//组装对象schema
	private void parseSchemaNode(SchemaNodeInfo nodeInfo,Map<String, Object> properties,Map<String,Long> defDealedMap){
		switch (nodeInfo.getType()) {
			case sys_object:
				parseObjectNode(nodeInfo,properties,defDealedMap);
				break;
				
			case sys_array:
				parseArrayNode(nodeInfo,properties,defDealedMap);
				break;
			
			case sys_ref:
				parseRefNode(nodeInfo, properties, defDealedMap);
				break;
				
			case cust_json:
				parseExtNode(nodeInfo,properties,defDealedMap);
				break;
				
			default:
				break;
		}
	}
	
	//判断是否是复合结构
	private boolean isComplex(SchemaType type){
		return (SchemaType.sys_object == type 
				|| SchemaType.sys_array == type
				|| SchemaType.sys_ref == type
				|| SchemaType.cust_json == type);
	}
	
	//组装对象schema
	private void parseObjectNode(SchemaNodeInfo nodeInfo,Map<String, Object> map,Map<String,Long> defDealedMap){
		Map<String, Object> properties = (Map)map.get("properties");
		if (CollectionUtils.isEmpty(properties)) {
			return ;
		}
		
		int childId = FormatUtils.parseInt(nodeInfo.getNodeId(),0) * 100 + 10;
		Set<String> keySet = properties.keySet();
		SchemaNodeInfo temp = null;
		Map<String, Object> info = null;
		SchemaType type = null;
		List<SchemaNodeInfo> childList = new ArrayList<SchemaNodeInfo>();
		for (String key : keySet) {
			info = (Map)properties.get(key);
			type = parseSchemaType(info);
			
			temp = new SchemaNodeInfo();
			temp.setNodeId(childId + "");
			temp.setCode(key);
			temp.setDescription((String)info.get("description"));
			temp.setType(type);
			temp.setParentId(nodeInfo.getNodeId());
			temp.setRequired(parseRequired(info));
			
			if (isComplex(type)) {
				parseSchemaNode(temp,info,defDealedMap);
			}
			
			childList.add(temp);
			childId ++;
		}
		
		nodeInfo.setChildList(childList);
	}
	
	//组装数组schema
	private void parseArrayNode(SchemaNodeInfo nodeInfo,Map<String, Object> map,Map<String,Long> defDealedMap){
		Map<String,Object> items = (Map)map.get("items");
		//处理复合对象
		Map<String, Object> properties = (Map)items.get("properties");
		if (!CollectionUtils.isEmpty(properties)) {
			parseObjectNode(nodeInfo, items, defDealedMap);
		}
		
		//处理数组引用
		String refInfo = (String)items.get("$ref");
		if (!StringUtils.isEmpty(refInfo)) {
			List<SchemaNodeInfo> childList = new ArrayList<SchemaNodeInfo>();
			int childId = FormatUtils.parseInt(nodeInfo.getNodeId(),0) * 100 + 10;
			String refName = parseRefName(SchemaType.sys_ref, items);
			SchemaNodeInfo temp = new SchemaNodeInfo();
			temp.setCode(refName);
			temp.setNodeId(childId + "");
			temp.setDescription((String)map.get("description"));
			temp.setType(SchemaType.sys_ref);
			temp.setRefSchemaId(defDealedMap.get(refName));
			temp.setParentId(nodeInfo.getNodeId());
			temp.setRequired(parseRequired(map));
			childList.add(temp);
			
			nodeInfo.setChildList(childList);
		}
	}
	
	//组装扩展的schema
	private void parseExtNode(SchemaNodeInfo nodeInfo,Map<String, Object> map,Map<String,Long> defDealedMap){
		nodeInfo.setExtSchema(parseExtSchema(map));
	}
	
	//组装引用schema
	private void parseRefNode(SchemaNodeInfo nodeInfo,Map<String, Object> map,Map<String,Long> defDealedMap){
		String refName = parseRefName(SchemaType.sys_ref,map);
		nodeInfo.setRefSchemaId(defDealedMap.get(refName));
	}
	
	//解析extSchema
	private String parseExtSchema(Map<String,Object> info){
		if (CollectionUtils.isEmpty(info)) {
			return null;
		}
		
		Map<String, Object> schema = (Map)info.get("schema");
		return CollectionUtils.isEmpty(schema) ? null : (String)schema.get("content");
	}
		
	//解析是否非空字段
	private boolean parseRequired(Map info){
		if (CollectionUtils.isEmpty(info) || !info.containsKey("required")) {
			return false;
		}
		
		return (Boolean)info.get("required");
	}
		
	//预处理非空属性，object类型时，required为数组
	private Map<String, Object> preDealRequired(Map<String, Object> value){
		if (CollectionUtils.isEmpty(value) || !value.containsKey("required")) {
			return value;
		}

		Object list = value.get("required");
		Map<String, Object> properties = (Map<String, Object>)value.get("properties");
		
		if (list instanceof ArrayList && !CollectionUtils.isEmpty(properties)) {
			ArrayList<String> tempList = (ArrayList<String>)list;
			Map<String, Object> info = null;
			for (String temp : tempList) {
				info = (Map<String, Object>)properties.get(temp);
				if (!CollectionUtils.isEmpty(info)) {
					info.put("required", true);
				}
			}
			
			value.remove("required");
		}
		
		return value;
	}
		
	//解析类型
	private SchemaType parseSchemaType(Map<String,Object> info){
		if (info.containsKey("type")) {
			return SchemaType.getByType((String)info.get("type"),(String)info.get("format"));
		}
		
		SchemaType type = SchemaType.sys_string;
		if (info.containsKey("$ref")) {
			type = SchemaType.sys_ref;
		}
		else if (info.get("schema") != null) {
			Map<String,Object> schema = (Map)info.get("schema");
			if (schema.containsKey("type")) {
				type = SchemaType.getByType((String)schema.get("type"),(String)schema.get("format"));
			}
			else if (schema.containsKey("$ref")) {
				type = SchemaType.sys_ref;
			}
		}
		else if (info.containsKey("properties")) {
			type = SchemaType.sys_object;
		}
		
		return type;
	}
	
	//获取引用的公共结构名称
	private String parseRefName(SchemaType type,Map<String,Object> info){
		String refInfo = "";
		switch (type) {
			case sys_ref :
				Map<String,Object> schema = (Map)info.get("schema");
				if (schema != null) {
					refInfo = (String)schema.get("$ref");
				}
				else{
					refInfo = (String)info.get("$ref");
				}
				break;
			case sys_array :
				refInfo = (String)((Map)info.get("items")).get("$ref");
				break;
				
			default :
				break;
		}
		
		return DocBuildUtil.parseSingleDefRef(refInfo);
	}
		
		
	//将所有的数据结构只设置code和docId后直接保存，然后查询组装code和id的关系
	//从而解决按顺序保存可能出现环的情况
	private Map<String, Long> saveRespSchemaCode(Long docId,Map<String, Object> docInfo){
		Map<String, Long> defDealedMap = MapUtils.newMap();
		RespSchema schema = null;
		List<RespSchema> batchList = new ArrayList<RespSchema>();
		Set<String> respSchemaCodeSet = parseRespSchemaCode(docInfo);
		if (CollectionUtils.isEmpty(respSchemaCodeSet)) {
			return defDealedMap;
		}
		
		for (String code : respSchemaCodeSet) {
			schema = new RespSchema();
			schema.setCode(code);
			schema.setDocId(docId);
			batchList.add(schema);
		}
		respSchemaService.batchAdd(batchList);
		
		//先保存再查下并组装code和id的映射关系
		List<RespSchema> schemaList = respSchemaService.listAllByDocId(docId);
		for (RespSchema respSchema : schemaList) {
			defDealedMap.put(respSchema.getCode(), respSchema.getId());
		}
		
		return defDealedMap;
	}
	
	//组装当前的所有公共数据结构响应编码
	private Set<String> parseRespSchemaCode(Map<String, Object> docInfo){
		Set<String> defNameSet = new HashSet<String>();
		Map<String, Object> defMap = (Map)docInfo.get("definitions");
		if (CollectionUtils.isEmpty(defMap)) {
			return defNameSet;
		}
		
		Set<String> defKeySet = defMap.keySet();
		for (String defKey : defKeySet) {
			if (StringUtils.isEmpty(defKey)) {
				continue ;
			}
			defNameSet.add(defKey);
		}
		//添加引用中的公共数据结构
		defNameSet.addAll(DocBuildUtil.parseDefRef(JsonUtils.toJson(defMap)));
		
		return defNameSet;
	}
}
