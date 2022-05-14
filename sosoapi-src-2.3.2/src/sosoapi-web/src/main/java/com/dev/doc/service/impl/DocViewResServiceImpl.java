package com.dev.doc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.dev.base.enums.InterNodeType;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.utils.FormatUtils;
import com.dev.base.utils.MapUtils;
import com.dev.base.vo.TreeNodeInfo;
import com.dev.doc.dao.DocViewResDao;
import com.dev.doc.entity.ApiErrorCode;
import com.dev.doc.entity.DocViewRes;
import com.dev.doc.entity.Inter;
import com.dev.doc.service.ApiErrorCodeService;
import com.dev.doc.service.DocViewResService;
import com.dev.doc.service.InterService;
import com.dev.doc.service.InterTreeService;
import com.dev.doc.util.InterTreeUtil;

/**
 * 
		* <p>Title: api文档视图资源</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年7月19日上午10:24:32</p>
 */
@Service
public class DocViewResServiceImpl extends BaseMybatisServiceImpl<DocViewRes,Long,DocViewResDao>
										implements DocViewResService{
	@Autowired
	private ApiErrorCodeService apiErrorCodeService;

	@Autowired
	private InterService interService;
	
	@Autowired
	private InterTreeService interTreeService;
	
	@Override
	public List<TreeNodeInfo> loadTreeData(Long docId, Long viewId) {
		//视图资源
		Map<InterNodeType, Map<String,String>> resInfo = buildViewResInfo(docId, viewId);
		
		List<TreeNodeInfo> result = new ArrayList<TreeNodeInfo>();
		result.addAll(buildCataStaticNode(docId,resInfo));
		result.addAll(buildModuleNode(docId,resInfo));
		result.addAll(buildApiErrorCodeNode(docId, resInfo));
		result.addAll(buildInterNode(docId, resInfo));
		
		return result;
	}
	
	//创建静态分类节点
	private List<TreeNodeInfo> buildCataStaticNode(Long docId,Map<InterNodeType, Map<String,String>> resInfo){
		Map<String,String> cataResInfo = resInfo.get(InterNodeType.cata);
		List<TreeNodeInfo> nodeList = interTreeService.loadCataTreeData(docId);
		for (TreeNodeInfo treeNodeInfo : nodeList) {
			if (cataResInfo.containsKey("" + treeNodeInfo.getDataId())) {
				treeNodeInfo.setChecked(true);
			}
		}
		
		return nodeList;
	}
	
	//组装模块节点
	private List<TreeNodeInfo> buildModuleNode(Long docId,Map<InterNodeType, Map<String,String>> resInfo){
		Map<String,String> moduleResInfo = resInfo.get(InterNodeType.module);
		List<TreeNodeInfo> moduleList = interTreeService.loadInterModuleTreeData(docId);
		List<TreeNodeInfo> result = new ArrayList<TreeNodeInfo>();
		for (TreeNodeInfo module : moduleList) {
			if (FormatUtils.isEqual(module.getDataId(), InterTreeUtil.TREE_MODULE_NODE_ID_DEF)) {
				continue ;
			}
			
			if (moduleResInfo.containsKey("" + module.getDataId())) {
				module.setChecked(true);
			}
			result.add(module);
		}
		
		return result;
	}
	
	//组装返回码节点
	private List<TreeNodeInfo> buildApiErrorCodeNode(Long docId,Map<InterNodeType, Map<String,String>> resInfo){
		Map<String,String> codeResInfo = resInfo.get(InterNodeType.code);
		List<ApiErrorCode> codeList = apiErrorCodeService.listByDocId(docId, null, null);
		List<TreeNodeInfo> result = new ArrayList<TreeNodeInfo>();
		TreeNodeInfo treeNodeInfo = null;
		for (ApiErrorCode apiErrorCode : codeList) {
			treeNodeInfo = InterTreeUtil.buildTreeNodeInfo(apiErrorCode);
			if (codeResInfo.containsKey("" + treeNodeInfo.getDataId())) {
				treeNodeInfo.setChecked(true);
			}
			result.add(treeNodeInfo);
		}
		
		return result;
	}
	
	//组装接口节点
	private List<TreeNodeInfo> buildInterNode(Long docId,Map<InterNodeType, Map<String,String>> resInfo){
		Map<String,String> interResInfo = resInfo.get(InterNodeType.inter);
		List<TreeNodeInfo> result = new ArrayList<TreeNodeInfo>();
		List<Inter> interList = interService.listAllByDocId(docId, null, null, null);
		TreeNodeInfo treeNodeInfo = null;
		for (Inter inter : interList) {
			treeNodeInfo = InterTreeUtil.buildTreeNodeInfo(inter);
			if (interResInfo.containsKey("" + treeNodeInfo.getDataId())) {
				treeNodeInfo.setChecked(true);
			}
			result.add(treeNodeInfo);
		}
		
		return result;
	}
		
	//组装视图相关资源信息
	private Map<InterNodeType, Map<String,String>> buildViewResInfo(Long docId,Long viewId){
		Map<InterNodeType, Map<String,String>> result = MapUtils.newMap();
		InterNodeType[] typeArray = InterNodeType.values();
		for (InterNodeType interNodeType : typeArray) {
			result.put(interNodeType, new HashMap<String,String>());
		}
		
		List<DocViewRes> resList = listAll(viewId, docId);
		for (DocViewRes docViewRes : resList) {
			result.get(docViewRes.getType()).put("" + docViewRes.getResourceId(), "");
		}
		
		return result;
	}

	@Override
	public void batchDel(Long viewId, Long docId) {
		getMybatisDao().batchDel(viewId, docId);
	}

	@Transactional
	@Override
	public void updateViewRes(Long viewId, Long docId, List<DocViewRes> resList) {
		batchDel(viewId, docId);
		if (!CollectionUtils.isEmpty(resList)) {
			batchAdd(resList);
		}
	}

	@Override
	public List<DocViewRes> listAll(Long viewId, Long docId) {
		return getMybatisDao().listAll(viewId, docId);
	}
}
