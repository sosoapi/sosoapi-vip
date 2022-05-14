package com.dev.doc.exports.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.dev.base.enums.SchemaType;
import com.dev.base.json.JsonUtils;
import com.dev.base.utils.MapUtils;
import com.dev.doc.entity.ApiDoc;
import com.dev.doc.entity.DocView;
import com.dev.doc.entity.Inter;
import com.dev.doc.entity.InterParam;
import com.dev.doc.entity.InterResp;
import com.dev.doc.entity.Module;
import com.dev.doc.entity.RespSchema;
import com.dev.doc.exports.service.BaseDocExportServiceAbstract;
import com.dev.doc.exports.vo.DocDataInfo;
import com.dev.doc.exports.vo.InterDataInfo;
import com.dev.doc.exports.vo.ModuleDataInfo;
import com.dev.doc.exports.vo.ParamDataInfo;
import com.dev.doc.exports.vo.RespDataInfo;
import com.dev.doc.service.ApiDocService;
import com.dev.doc.service.ApiEnvService;
import com.dev.doc.service.ApiErrorCodeService;
import com.dev.doc.service.CommonParamService;
import com.dev.doc.service.DocViewService;
import com.dev.doc.service.InterService;
import com.dev.doc.service.ModuleService;
import com.dev.doc.service.RespSchemaService;
import com.dev.doc.util.DocBuildUtil;
import com.dev.doc.vo.SchemaNodeInfo;

/**
 * 
		* <p>Title: html导出</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年8月15日下午2:50:06</p>
 */
@Service
public class HtmlDocExportServiceImpl extends BaseDocExportServiceAbstract<DocDataInfo>{
	private Logger logger = LogManager.getLogger(HtmlDocExportServiceImpl.class);
	
	//结构树层级空格
	private static String SCHEMA_LEVEL_SPACE = "&nbsp;&nbsp;";
		
	@Autowired
	private ApiDocService apiDocService;
	
	@Autowired
	private ApiEnvService apiEnvService;
	
	@Autowired
	private ApiErrorCodeService apiErrorCodeService;
	
	@Autowired
	private CommonParamService commonParamService;
	
	@Autowired
	private InterService interService;
	
	@Autowired
	private RespSchemaService respSchemaService;
	
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private DocViewService docViewService;
	
	@Override
	public DocDataInfo exportDoc(Long userId, Long docId, Long moduleId, String condition) {
		DocDataInfo dataInfo = new DocDataInfo();
		ApiDoc apiDoc = apiDocService.getById(docId);
		if (apiDoc == null) {
			return dataInfo;
		}
		
		dataInfo.setApiDoc(apiDoc);
		dataInfo.setEnvList(apiEnvService.listByDocId(docId, null));
		dataInfo.setErrorCodeList(apiErrorCodeService.listInfoByDocId(docId, moduleId, condition));
		dataInfo.setCommonParamList(buildCommonParamDataInfoList(commonParamService.listByDocId(docId)));
		dataInfo.setModuleList(buildDocModuleDataList(docId));
		return dataInfo;
	}

	@Override
	public DocDataInfo exportDocView(Long userId, Long viewId, Long docId, Long moduleId, String condition) {
		DocDataInfo dataInfo = new DocDataInfo();
		ApiDoc apiDoc = apiDocService.getById(docId);
		DocView docView = docViewService.getByDocId(viewId, docId);
		if (docView == null || apiDoc == null) {
			return dataInfo;
		}
		
		dataInfo.setApiDoc(apiDoc);
		dataInfo.setEnvList(apiEnvService.listByDocId(docId, null));
		dataInfo.setErrorCodeList(apiErrorCodeService.listInfoByViewId(viewId));
		dataInfo.setCommonParamList(buildCommonParamDataInfoList(commonParamService.listByDocId(docId)));
		dataInfo.setModuleList(buildDocViewModuleDataList(viewId, docId));
		return dataInfo;
	}

	//组装文档模块相关信息
	private List<ModuleDataInfo> buildDocModuleDataList(Long docId){
		//模块信息列表
		List<Module> moduleList = moduleService.listAllByDocId(docId);
		
		//接口列表
		List<Inter> interList = interService.listAllByDocId(docId,null,null,null);
		
		return buildModuleDataList(docId,moduleList, interList);
	}
	
	//组装文档视图模块相关信息
	private List<ModuleDataInfo> buildDocViewModuleDataList(Long viewId,Long docId){
		//模块信息列表
		List<Module> moduleList = moduleService.listByViewId(viewId);
		
		//接口列表
		List<Inter> interList = interService.listByViewId(viewId,null);
		
		return buildModuleDataList(docId,moduleList, interList);
	}
	
	//组装模块列表相关信息
	private List<ModuleDataInfo> buildModuleDataList(Long docId,List<Module> moduleList,List<Inter> interList){
		//公共数据列表
		List<RespSchema> respSchemaList = respSchemaService.listAllByDocId(docId);
		//公共数据结构id和code集合
		Map<Long, String> respSchamaCodeMap = getRespSchemaCodeInfo(respSchemaList);
		
		//请求信息列表
		Map<Long, List<InterParam>> interParamInfoMap = getInterParamInfo(docId);
		
		//响应信息列表
		Map<Long, List<InterResp>> interRespInfoMap = getInterRespInfo(docId);
				
		//添加默认分组
		moduleList.add(0, buildDefModule());
		
		//将接口按模块分类
		Map<Long, List<Inter>> interInfoMap = getModuleInterInfo(interList);
		//将公共数据结构按模块分类
		Map<Long,List<RespDataInfo>> moduleRespDataInfoMap = getModuleRespDataInfo(respSchemaList, respSchamaCodeMap);
		
		//将模块列表转换为ModuelDataInfo列表
		List<ModuleDataInfo> result = new ArrayList<ModuleDataInfo>();
		Long moduleId = null;
		for (Module module : moduleList) {
			moduleId = module.getId();
			result.add(buildModuleData(module, interInfoMap.get(moduleId), 
					moduleRespDataInfoMap.get(moduleId), respSchamaCodeMap,
					interParamInfoMap, interRespInfoMap));
		}
		
		//未分组节点下若无接口和公共数据结构则不展示
		ModuleDataInfo defModuleDataInfo = result.get(0);
		if (CollectionUtils.isEmpty(defModuleDataInfo.getInterList())
				&& CollectionUtils.isEmpty(defModuleDataInfo.getRespSchemaList())) {
			result.remove(0);
		}
		
		return result;
	}
	
	//组装模块数据信息
	private ModuleDataInfo buildModuleData(Module module,List<Inter> interList,
			List<RespDataInfo> respDataInfoList,Map<Long, String> respSchamaCodeMap,
			Map<Long, List<InterParam>> interParamInfoMap,Map<Long, List<InterResp>> interRespInfoMap){
		ModuleDataInfo moduleDataInfo = new ModuleDataInfo();
		moduleDataInfo.setModule(module);
		moduleDataInfo.setInterList(buildInterDataInfoList(module.getId(), interList, respSchamaCodeMap,
										interParamInfoMap, interRespInfoMap));
		moduleDataInfo.setRespSchemaList(respDataInfoList);
		return moduleDataInfo;
	}
	
	//组装接口列表数据信息
	private List<InterDataInfo> buildInterDataInfoList(Long moduleId,List<Inter> interList,
			Map<Long, String> respSchamaCodeMap,Map<Long, List<InterParam>> interParamInfoMap,
			Map<Long, List<InterResp>> interRespInfoMap){
		List<InterDataInfo> result = new ArrayList<InterDataInfo>();
		if (CollectionUtils.isEmpty(interList)) {
			return result;
		}
		
		Long interId = null;
		for (Inter inter : interList) {
			interId = inter.getId();
			result.add(buildInterDataInfo(inter, respSchamaCodeMap, 
					interParamInfoMap.get(interId), interRespInfoMap.get(interId)));
		}
		
		return result;
	}
	
	//组装接口数据信息
	private InterDataInfo buildInterDataInfo(Inter inter,Map<Long, String> respSchamaCodeMap,
							List<InterParam> interParamList,List<InterResp> interRespList){
		List<ParamDataInfo> paramList = buildInterParamDataInfoList(interParamList, respSchamaCodeMap);
		List<RespDataInfo> respList = buildInterRespDataInfoList(interRespList, respSchamaCodeMap);
		
		InterDataInfo interDataInfo = new InterDataInfo();
		interDataInfo.setInter(inter);
		interDataInfo.setParamList(paramList);
		interDataInfo.setRespList(respList);
		
		//处理接口请求参数的自定义json
		for (ParamDataInfo paramDataInfo : paramList) {
			if (paramDataInfo.getType() == SchemaType.cust_json) {
				interDataInfo.getParamExtSchemaMap().put(paramDataInfo.getCode(), paramDataInfo.getExtSchema());
			}
		}
		
		//处理接口响应中的自定义json和复合对象
		SchemaType respType = null;
		List<SchemaNodeInfo> nodeList = null;
		for (RespDataInfo respDataInfo : respList) {
			respType = respDataInfo.getType();
			if (respType == SchemaType.cust_json) {
				interDataInfo.getRespExtSchemaMap().put(respDataInfo.getCode(), respDataInfo.getExtSchema());
			}
			else if (respType == SchemaType.sys_object
					|| respType == SchemaType.sys_array) {
				nodeList = parseSchemaTree(respDataInfo.getCustSchemaTree(),respSchamaCodeMap);
				interDataInfo.getRespCustComplexSchemaMap().put(respDataInfo.getCode(), nodeList);
			}
		}
		
		return interDataInfo;
	}
	
	//组装接口请求参数信息
	private List<ParamDataInfo> buildInterParamDataInfoList(List<InterParam> paramList,Map<Long, String> respSchamaCodeMap){
		List<ParamDataInfo> result = new ArrayList<ParamDataInfo>();
		if (CollectionUtils.isEmpty(paramList)) {
			return result;
		}
		//判断公共数据结构是否为空
		boolean isRefEmpty = CollectionUtils.isEmpty(respSchamaCodeMap);
		
		ParamDataInfo paramDataInfo = null;
		SchemaType type = null;
		for (InterParam interParam : paramList) {
			type = interParam.getType();
			paramDataInfo = new ParamDataInfo();
			paramDataInfo.setId(interParam.getId());
			paramDataInfo.setCode(interParam.getCode());
			paramDataInfo.setName(interParam.getName());
			paramDataInfo.setDescription(interParam.getDescription());
			paramDataInfo.setType(type);
			paramDataInfo.setPosition(interParam.getPosition());
			paramDataInfo.setRequired(interParam.isRequired());
			paramDataInfo.setDefValue(interParam.getDefValue());
			if (type == SchemaType.sys_ref && !isRefEmpty) {
				paramDataInfo.setRefSchemaId(interParam.getRefSchemaId());
				paramDataInfo.setRefSchemaCode(respSchamaCodeMap.get(interParam.getRefSchemaId()));
			}
			else if (type == SchemaType.cust_json) {
				paramDataInfo.setExtSchema(formatExtJsonSchema(interParam.getExtSchema()));
			}
			
			result.add(paramDataInfo);
		}
		
		return result;
	}
	
	//组装接口响应信息
	private List<RespDataInfo> buildInterRespDataInfoList(List<InterResp> interRespList,Map<Long, String> respSchamaCodeMap){
		List<RespDataInfo> result = new ArrayList<RespDataInfo>();
		if (CollectionUtils.isEmpty(interRespList)) {
			return result;
		}
		
		for (InterResp interResp : interRespList) {
			result.add(parseInterResp(interResp, respSchamaCodeMap));
		}
		
		return result;
	}
	
	//组装公共请求参数信息
	private List<ParamDataInfo> buildCommonParamDataInfoList(List<InterParam> commonParamList){
		return buildInterParamDataInfoList(commonParamList, null);
	}
	
	//将公共响应信息按模块分类
	private Map<Long,List<RespDataInfo>> getModuleRespDataInfo(List<RespSchema> respSchemaList,Map<Long, String> respSchamaCodeMap){
		Map<Long,List<RespDataInfo>> result = MapUtils.newMap();
		
		List<RespDataInfo> tempList = null;
		Long moduleId = null;
		for (RespSchema respSchema : respSchemaList) {
			moduleId = respSchema.getModuleId() == null ? DEF_MODULE_ID : respSchema.getModuleId();
			tempList = result.get(moduleId);
			if (tempList == null) {
				tempList = new ArrayList<RespDataInfo>();
				tempList.add(parseRespSchema(respSchema, respSchamaCodeMap));
				result.put(moduleId, tempList);
			}
			else{
				tempList.add(parseRespSchema(respSchema, respSchamaCodeMap));
			}
		}
		
		return result;
	}
	
	//将公共数据结构转换为respDataInfo
	private RespDataInfo parseRespSchema(RespSchema respSchema,Map<Long, String> respSchamaCodeMap){
		SchemaType type = respSchema.getType();
		RespDataInfo respDataInfo = new RespDataInfo();
		respDataInfo.setId(respSchema.getId());
		respDataInfo.setCode(respSchema.getCode());
		respDataInfo.setName(respSchema.getName());
		respDataInfo.setDescription(respSchema.getDescription());
		respDataInfo.setType(type);
		/*if (type == SchemaType.sys_ref) {//处理引用
			respDataInfo.setRefSchemaId(respSchema.getRefSchemaId());
			respDataInfo.setRefSchemaCode(respSchamaCodeMap.get(respSchema.getRefSchemaId()));
		}
		else if(type == SchemaType.cust_json){//处理自定义json，公共数据结构无该类型
			
		}
		else if (type == SchemaType.sys_array || type == SchemaType.sys_object) {//处理数组或对象
			SchemaNodeInfo rootNode = DocBuildUtil.buildTreeNode(respSchema.getCustSchema());
			respDataInfo.setChildList(parseSchemaTree(rootNode,respSchamaCodeMap));
		}*/
		
		if (type == SchemaType.sys_array || type == SchemaType.sys_object) {//处理数组或对象
			SchemaNodeInfo rootNode = DocBuildUtil.buildTreeNode(respSchema.getCustSchema());
			respDataInfo.setChildList(parseSchemaTree(rootNode,respSchamaCodeMap));
		}
		else {//处理简单类型，数据结构无自定义json格式，无需处理
			SchemaNodeInfo simpleNode = new SchemaNodeInfo();
			simpleNode.setCode(respSchema.getCode());
			simpleNode.setDescription(respSchema.getDescription());
			simpleNode.setType(respSchema.getType());
			if (type == SchemaType.sys_ref) {
				simpleNode.setRefSchemaId(respSchema.getRefSchemaId());
				simpleNode.setRefSchemaCode(respSchamaCodeMap.get(respSchema.getRefSchemaId()));
			}
			respDataInfo.getChildList().add(simpleNode);
		}
		
		return respDataInfo;
	}
	
	//将公共数据结构转换为respDataInfo
	private RespDataInfo parseInterResp(InterResp interResp,Map<Long, String> respSchamaCodeMap){
		SchemaType type = interResp.getType();
		RespDataInfo respDataInfo = new RespDataInfo();
		respDataInfo.setId(interResp.getId());
		respDataInfo.setCode(interResp.getCode());
		respDataInfo.setName(interResp.getName());
		respDataInfo.setDescription(interResp.getDescription());
		respDataInfo.setType(type);
		if (type == SchemaType.sys_ref) {//处理引用
			respDataInfo.setRefSchemaId(interResp.getRefSchemaId());
			respDataInfo.setRefSchemaCode(respSchamaCodeMap.get(interResp.getRefSchemaId()));
		}
		else if(type == SchemaType.cust_json){//处理自定义json
			respDataInfo.setExtSchema(formatExtJsonSchema(interResp.getExtSchema()));
		}
		else if (type == SchemaType.sys_array || type == SchemaType.sys_object) {//处理数组或对象
			respDataInfo.setCustSchemaTree(DocBuildUtil.buildTreeNode(interResp.getCustSchema()));
		}
		
		return respDataInfo;
	}
	
	//将节点树转换为缩进的列表格式
	private List<SchemaNodeInfo> parseSchemaTree(SchemaNodeInfo rootNodeInfo,Map<Long, String> respSchamaCodeMap){
		List<SchemaNodeInfo> result = new ArrayList<SchemaNodeInfo>();
		//根据custSchema创建的节点树第一个节点为虚拟的根节点
		List<SchemaNodeInfo> childList = rootNodeInfo.getChildList();
		if (CollectionUtils.isEmpty(childList)) {
			return result;
		}
		
		for (SchemaNodeInfo nodeInfo : childList) {
			addSchemaNodeInfo(result, 0, nodeInfo,respSchamaCodeMap);
		}
		
		return result;
	}
	
	//添加树所有节点
	private void addSchemaNodeInfo(List<SchemaNodeInfo> result,int level,SchemaNodeInfo nodeInfo,Map<Long, String> respSchamaCodeMap){
		nodeInfo.setCode(formatTreeLevel(level, nodeInfo.getCode()));
		//处理引用
		if (nodeInfo.getType() == SchemaType.sys_ref) {
			nodeInfo.setRefSchemaCode(respSchamaCodeMap.get(nodeInfo.getRefSchemaId()));
		}
		result.add(nodeInfo);
		
		//只有数组或对象才有复合对象
		if (nodeInfo.getType() != SchemaType.sys_array 
				&& nodeInfo.getType() != SchemaType.sys_object) {
			return ;
		}
		
		List<SchemaNodeInfo> childList = nodeInfo.getChildList();
		if (!CollectionUtils.isEmpty(childList)) {//处理子节点
			level ++;//子节点层级+1
			for (SchemaNodeInfo child : childList) {
				addSchemaNodeInfo(result,level,child,respSchamaCodeMap);
			}
		}
	}
	
	//格式化层级关系，以空格缩进
	private String formatTreeLevel(int level,String content){
		if (StringUtils.isEmpty(content) || level <= 0) {
			return content;
		}
		
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < level; i++) {
			builder.append(SCHEMA_LEVEL_SPACE);
		}
		builder.append(content.trim());
		return builder.toString();
	}
	
	//格式化json
	private String formatExtJsonSchema(String jsonSchema){
		if (StringUtils.isEmpty(jsonSchema)) {
			return "{}";
		}
		
		String jsonFormat = JsonUtils.formatJson(jsonSchema);
		return jsonFormat.trim()
						 .replace(System.lineSeparator(), "<br/>")
						 .replace(" ", "&nbsp;");
	}
}
