package com.dev.doc.exports.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.dev.base.constant.AppConstants;
import com.dev.base.enums.SchemaType;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.utils.DateUtil;
import com.dev.base.utils.ValidateUtils;
import com.dev.doc.entity.ApiDoc;
import com.dev.doc.entity.DocView;
import com.dev.doc.entity.Inter;
import com.dev.doc.entity.InterParam;
import com.dev.doc.exports.service.BaseDocExportServiceAbstract;
import com.dev.doc.service.ApiDocService;
import com.dev.doc.service.CommonParamService;
import com.dev.doc.service.DocViewService;
import com.dev.doc.service.InterService;
import com.dev.doc.vo.PostmanCollection;
import com.dev.doc.vo.PostmanReq;
import com.dev.doc.vo.PostmanReqParam;

/**
 * 
		* <p>Title: postman导出</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年8月15日下午2:50:06</p>
 */
@Service
public class PostmanDocExportServiceImpl extends BaseDocExportServiceAbstract<PostmanCollection>{

	/** 文档id前缀*/
	private static String PREFIX_DOC_ID = "sosoapi-doc-id-";
	
	/** 接口id前缀*/
	private static String PREFIX_INTER_ID = "sosoapi-inter-id-";
	
	/** Postman版本号*/
	private static int POSTMAN_VERSINO = 2;
	
	@Autowired
	private ApiDocService apiDocService;
	
	@Autowired
	private InterService interService;
	
	@Autowired
	private CommonParamService commonParamService;
	
	@Autowired
	private DocViewService docViewService;
	
	@Override
	public PostmanCollection exportDoc(Long userId, Long docId, Long moduleId, String condition) {
		ApiDoc apiDoc = apiDocService.getById(docId);
		ValidateUtils.notNull(apiDoc, ErrorCode.SYS_007);
		
		PostmanCollection collection = new PostmanCollection();
		List<Inter> interList = interService.listAllByDocId(docId,null, false, condition);
		
		//创建集合基础信息
		createCollectionInfo(collection,apiDoc);
		
		//创建文档接口顺序
		createReqOrder(collection, interList);
		
		//创建文档接口信息
		createReqList(apiDoc, collection, interList);
		
		return collection;
	}

	@Override
	public PostmanCollection exportDocView(Long userId,Long viewId,Long docId,Long moduleId,String condition) {
		ApiDoc apiDoc = apiDocService.getById(docId);
		DocView docView = docViewService.getByDocId(viewId, docId);
		ValidateUtils.isTrue(apiDoc != null && docView != null, ErrorCode.SYS_007);
		
		PostmanCollection collection = new PostmanCollection();
		List<Inter> interList = interService.listByViewId(viewId,null);
		
		//创建集合基础信息
		createCollectionInfo(collection,apiDoc);
		
		//创建文档接口顺序
		createReqOrder(collection, interList);
		
		//创建文档接口信息
		createReqList(apiDoc, collection, interList);
		
		return collection;
	}
	
	//创建集合基础信息
	private void createCollectionInfo(PostmanCollection collection,ApiDoc apiDoc){
		collection.setId(parseCollectionId(apiDoc.getId()));
		collection.setName(apiDoc.getTitle());
		collection.setTimestamp(DateUtil.getNow());
	}
	
	//创建文档接口顺序
	private void createReqOrder(PostmanCollection collection,List<Inter> interList){
		List<String> reqOrder = new ArrayList<String>();
		for (Inter inter : interList) {
			reqOrder.add(parseInterId(inter.getId()));
		}
		
		collection.setReqOrder(reqOrder);
	}
	
	//创建文档接口信息
	private void createReqList(ApiDoc apiDoc,PostmanCollection collection,List<Inter> interList){
		Map<Long, List<InterParam>> interParamInfo = getInterParamInfo(apiDoc.getId());
		//公共请求参数
		List<InterParam> commonParamList = commonParamService.listByDocId(apiDoc.getId());
		boolean isCommonParamEmpty = CollectionUtils.isEmpty(commonParamList);
		List<PostmanReq> reqList = new ArrayList<PostmanReq>();
		String collectionId = collection.getId();
//			String baseUrl = parseBaseUrl(apiDoc);
		String baseUrl = AppConstants.POSTMAN_BASE_URL;
		List<InterParam> interParamList = null;
		for (Inter inter : interList) {
			interParamList = interParamInfo.get(inter.getId());
			
			//处理公共请求参数
			if (!isCommonParamEmpty && !inter.isSkipCommonParam()) {
				if (CollectionUtils.isEmpty(interParamList)) {
					interParamList = commonParamList;
				}
				else{
					interParamList.addAll(0, commonParamList);
				}
			}
			
			reqList.add(parseReq(baseUrl,collectionId,inter,interParamList));
		}
		
		collection.setReqList(reqList);
	}
	
	//组装接口信息
	private PostmanReq parseReq(String baseUrl,String collectionId,Inter inter,List<InterParam> paramList){
		//接口基本信息
		PostmanReq reqInfo = new PostmanReq();
		reqInfo.setCollectionId(collectionId);
		reqInfo.setId(parseInterId(inter.getId()));
		reqInfo.setName(inter.getName());
		reqInfo.setDescription(inter.getDescription());
		reqInfo.setMethod(inter.getMethod());
		reqInfo.setDataMode(PostmanReq.DATA_MODE_PARAMS);
		reqInfo.setVersion(POSTMAN_VERSINO);
		reqInfo.setTime(inter.getModifyDate());
		reqInfo.setTimestamp(inter.getModifyDate());

		//接口参数
		List<PostmanReqParam> reqParamList = new ArrayList<PostmanReqParam>();
		StringBuilder headerBuilder = new StringBuilder();
		StringBuilder urlBuilder = new StringBuilder();
		if (!CollectionUtils.isEmpty(paramList)) {
			for (InterParam interParam : paramList) {
				parseReqParam(interParam, urlBuilder, headerBuilder, reqParamList);
			}
		}
		reqInfo.setHeaders(headerBuilder.toString());
		reqInfo.setParamList(reqParamList);
		
		//接口请求url
		String url = parsePath(inter.getPath());
		if (urlBuilder.length() > 0) {
			//删除最后一个&
			urlBuilder.deleteCharAt(urlBuilder.length() - 1);
			if (url.indexOf("?") > 0) {
				url += "&" + urlBuilder.toString();
			}
			else {
				url += "?" + urlBuilder.toString();
			}
		}
		
		//如果以http开头，则无需加前缀
		if (!StringUtils.isEmpty(url) && url.toLowerCase().startsWith("http")) {
			reqInfo.setUrl(url);
		}
		else{
			reqInfo.setUrl(baseUrl + url);
		}
		
		return reqInfo;
	}
	
	//处理postman占位符
	private String parsePath(String path){
		return StringUtils.isEmpty(path) ? path : path.replaceAll("[{]", "{{")
				   									  .replaceAll("[}]", "}}");
	}
	
	//组装请求参数
	private void parseReqParam(InterParam interParam,StringBuilder urlBuilder,StringBuilder headerBuilder,List<PostmanReqParam> reqParamList){
		switch (interParam.getPosition()) {
			case header:
				headerBuilder.append(interParam.getCode() + ":" + parseDefValue(interParam.getDefValue()) + "\n");
				break;
				
			case query:
				urlBuilder.append(interParam.getCode() + "=" + parseDefValue(interParam.getDefValue()) + "&");
				break;
				
			case formData:
				parseFormDataReqParam(interParam, reqParamList);
				break;
				
			default:
				break;
		}
	}
	
	//处理表单请求参数
	private void parseFormDataReqParam(InterParam interParam,List<PostmanReqParam> reqParamList){
		PostmanReqParam reqParam = new PostmanReqParam();
		reqParam.setKey(interParam.getCode());
		
		//设置默认值
		//自定义复合结构类型，默认值为自定义结构
		if (interParam.getType() == SchemaType.cust_json) {
			reqParam.setValue(interParam.getExtSchema());
		}
		else{
			reqParam.setValue(parseDefValue(interParam.getDefValue()));
		}
		
		//设置参数类型
		if (interParam.getType() == SchemaType.sys_file) {
			reqParam.setType(PostmanReqParam.TYPE_FILE);
		}
		else{
			reqParam.setType(PostmanReqParam.TYPE_TEXT);
		}
		
		reqParamList.add(reqParam);
	}
	
	//组装文档id
	private String parseCollectionId(Long docId){
		return PREFIX_DOC_ID + docId;
	}
	
	//组装接口id
	private String parseInterId(Long interId){
		return PREFIX_INTER_ID + interId;
	}
	
	//组装默认值
	private String parseDefValue(String defValue){
		return StringUtils.isEmpty(defValue) ? "" : defValue;
	}
}
