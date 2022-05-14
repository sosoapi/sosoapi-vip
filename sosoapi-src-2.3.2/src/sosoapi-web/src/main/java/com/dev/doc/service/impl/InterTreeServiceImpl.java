package com.dev.doc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dev.base.enums.InterNodeType;
import com.dev.base.enums.NodeMoveType;
import com.dev.base.util.Pager;
import com.dev.base.utils.FormatUtils;
import com.dev.base.utils.MapUtils;
import com.dev.base.vo.TreeNodeInfo;
import com.dev.doc.entity.ApiErrorCode;
import com.dev.doc.entity.Inter;
import com.dev.doc.entity.Module;
import com.dev.doc.service.ApiErrorCodeService;
import com.dev.doc.service.InterService;
import com.dev.doc.service.InterTreeService;
import com.dev.doc.service.ModuleService;
import com.dev.doc.util.InterTreeUtil;

/**
 * 
		* <p>Title: 接口树管理</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年3月23日下午4:12:37</p>
 */
@Service
public class InterTreeServiceImpl implements InterTreeService{
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private InterService interService;
	
	@Autowired
	private ApiErrorCodeService apiErrorCodeService;
	
	@Override
	public List<TreeNodeInfo> loadTreeData(Long docId,InterNodeType nodeType,String parentNodeId,Long parentDataId) {
		List<TreeNodeInfo> result = new ArrayList<TreeNodeInfo>();
		if (parentDataId == null) {
			result.addAll(loadCataTreeData(docId));
			result.addAll(loadInterModuleTreeData(docId));
			return result;
		}
		else{
			switch (nodeType) {
				case cata:
					if (FormatUtils.isEqual(InterTreeUtil.TREE_CATA_NODE_ID_CODE, parentDataId)) {//加载错误码
						result = loadErrorCodeTreeData(docId, null);
//						result = loadErrorCodeModuleTreeData(docId);
					}
					else if (FormatUtils.isEqual(InterTreeUtil.TREE_CATA_NODE_ID_INTER, parentDataId)) {//加载接口列表
						result = loadInterModuleTreeData(docId);
					}
					else if (FormatUtils.isEqual(InterTreeUtil.TREE_CATA_NODE_ID_SCHEMA, parentDataId)) {//加载数据结构列表

					}
					break;
					
				case module:
					//解析模块对应的分类id
					Long moduleCataId = parseModuleCataId(parentNodeId);
					if (FormatUtils.isEqual(InterTreeUtil.TREE_CATA_NODE_ID_INTER,moduleCataId)) {
						result = loadInterTreeData(docId,parentDataId);
					}
					else if (FormatUtils.isEqual(InterTreeUtil.TREE_CATA_NODE_ID_CODE, moduleCataId)) {
						result = loadErrorCodeTreeData(docId, parentDataId);
					}
					
					break;
					
				default:
					break;
			}
		}
		
		return result;
	}
	
	//解析模块id对应的分类id
	private Long parseModuleCataId(String moduleNodeId){
		if (StringUtils.isEmpty(moduleNodeId)) {
			return null;
		}
		
		String[] infoArray = moduleNodeId.split("_");
		if (infoArray.length != 3) {
			return null;
		}
		
		return Long.parseLong(infoArray[1]);
	}
	
	@Override
	public List<TreeNodeInfo> loadCataTreeData(Long docId){
		return InterTreeUtil.buildSysCataTreeData();
	}
	
	@Override
	public List<TreeNodeInfo> loadInterModuleTreeData(Long docId){
		List<TreeNodeInfo> result = InterTreeUtil.buildSysModuleTreeData(InterTreeUtil.TREE_CATA_NODE_ID_INTER);
		//系统模块
		for (TreeNodeInfo treeNodeInfo : result) {
			treeNodeInfo.setChildrenCount(interService.countByModuleId(docId,treeNodeInfo.getDataId()));
		}
		
		//自定义模块
		List<Module> moduleList = moduleService.listAllByDocId(docId);
		Map<Long, Integer> interCountMap = interService.countByModule(docId);
		Integer interCount = 0;
		for (Module module : moduleList) {
			interCount = interCountMap.get(module.getId());
			if (interCount == null) {
				interCount = 0;
			}
			
			result.add(InterTreeUtil.buildTreeNodeInfo(InterTreeUtil.TREE_CATA_NODE_ID_INTER,module,false,interCount));
		}
		
		return result;
	}
	
	@Override
	public List<TreeNodeInfo> loadErrorCodeModuleTreeData(Long docId) {
		List<TreeNodeInfo> result = InterTreeUtil.buildSysModuleTreeData(InterTreeUtil.TREE_CATA_NODE_ID_CODE);
		//系统模块
		for (TreeNodeInfo treeNodeInfo : result) {
			treeNodeInfo.setChildrenCount(apiErrorCodeService.countByModuleId(docId,treeNodeInfo.getDataId()));
		}
		
		//自定义模块
		List<Module> moduleList = moduleService.listAllByDocId(docId);
		Map<Long, Integer> errorCodeCountMap = apiErrorCodeService.countByModule(docId);
		Integer errorCodeCount = 0;
		for (Module module : moduleList) {
			errorCodeCount = errorCodeCountMap.get(module.getId());
			if (errorCodeCount == null) {
				errorCodeCount = 0;
			}
			
			result.add(InterTreeUtil.buildTreeNodeInfo(InterTreeUtil.TREE_CATA_NODE_ID_CODE,module,false,errorCodeCount));
		}
		
		return result;
	}
	
	@Override
	public List<TreeNodeInfo> loadErrorCodeTreeData(Long docId,Long moduleId){
		List<TreeNodeInfo> result = new ArrayList<TreeNodeInfo>();
		List<ApiErrorCode> codeList = apiErrorCodeService.listByModuleId(docId, moduleId, Pager.getMaxSize());
		for (ApiErrorCode apiErrorCode : codeList) {
			result.add(InterTreeUtil.buildTreeNodeInfo(apiErrorCode));
		}
		
		return result;
	}
	
	//加载接口树结构数据
	private List<TreeNodeInfo> loadInterTreeData(Long docId,Long moduleId){
		List<TreeNodeInfo> result = new ArrayList<TreeNodeInfo>();
		List<Inter> interList = interService.listByModuleId(docId, moduleId, Pager.getMaxSize());
		for (Inter inter : interList) {
			result.add(InterTreeUtil.buildTreeNodeInfo(inter));
		}
		
		return result;
	}
	
	@Override
	public void sortTreeData(Long docId,TreeNodeInfo srcNode, TreeNodeInfo targetNode, NodeMoveType moveType) {
		InterNodeType nodeType = InterNodeType.valueOf(srcNode.getType());
		switch (nodeType) {
			case module :
				sortModuleTreeData(docId,srcNode, targetNode, moveType);
				break;
			
			case inter:
				sortInterTreeData(docId,srcNode, targetNode, moveType);
				break;
			
			case code:
				sortErrorCodeTreeData(docId,srcNode, targetNode, moveType);
				break;
				
			default :
				break;
		}
	}
	
	//模块排序
	private void sortModuleTreeData(Long docId,TreeNodeInfo srcNode, TreeNodeInfo targetNode, NodeMoveType moveType){
		Module srcModule = moduleService.getByDocId(docId, srcNode.getDataId());
		Module targetModule = moduleService.getByDocId(docId,targetNode.getDataId());
		if (srcModule == null || targetModule == null) {
			return ;
		}
		
		int srcSortWeight = srcModule.getSortWeight();
		int targetSortWeight = targetModule.getSortWeight();
		if (srcModule.getSortWeight() > targetModule.getSortWeight()) {//从后往前拖拽
			switch (moveType) {
				case prev://置于目标之前
					//目标及之后的权重值全部+1
					moduleService.buildSortWeight(docId, targetSortWeight, srcSortWeight - 1, 1);
					
					//源节点权重变更为目标权重
					moduleService.updateSortWeight(docId, srcModule.getId(), targetSortWeight);
					break;
				
				case next://置于目标之后
					//目标之后的权重值全部+1
					moduleService.buildSortWeight(docId, targetSortWeight + 1, srcSortWeight - 1, 1);
					
					//源节点权重变更为目标权重+1
					moduleService.updateSortWeight(docId, srcModule.getId(), targetSortWeight + 1);
					break;
					
				default:
					break;
			}
		}
		else {//从前往后拖拽
			switch (moveType) {
				case prev://置于目标之前
					//源节点之后的权重值全部-1
					moduleService.buildSortWeight(docId, srcSortWeight + 1, targetSortWeight - 1, -1);
					
					//源节点权重变更为目标权重-1
					moduleService.updateSortWeight(docId, srcModule.getId(), targetSortWeight - 1);
					break;
				
				case next://置于目标之后
					//目标之后的权重值全部-1
					moduleService.buildSortWeight(docId, srcSortWeight + 1, targetSortWeight, -1);
					
					//源节点权重变更为目标权重
					moduleService.updateSortWeight(docId, srcModule.getId(), targetSortWeight);
					break;
					
				default:
					break;
			}
		}
	}
	
	//错误码排序
	private void sortErrorCodeTreeData(Long docId,TreeNodeInfo srcNode, TreeNodeInfo targetNode, NodeMoveType moveType){
		ApiErrorCode srcCode = apiErrorCodeService.getByDocId(docId, srcNode.getDataId());
		ApiErrorCode targetCode = apiErrorCodeService.getByDocId(docId, targetNode.getDataId());
		if (srcCode == null || targetCode == null) {
			return ;
		}
		
		int srcSortWeight = srcCode.getSortWeight();
		int targetSortWeight = targetCode.getSortWeight();
		if (srcCode.getSortWeight() > targetCode.getSortWeight()) {//从后往前拖拽
			switch (moveType) {
				case prev://置于目标之前
					//目标及之后的权重值全部+1
					apiErrorCodeService.buildSortWeight(docId,null, targetSortWeight, srcSortWeight - 1, 1);
					
					//源节点权重变更为目标权重
					apiErrorCodeService.updateSortWeight(docId, srcCode.getId(), targetSortWeight);
					break;
				
				case next://置于目标之后
					//目标之后的权重值全部+1
					apiErrorCodeService.buildSortWeight(docId,null, targetSortWeight + 1, srcSortWeight - 1, 1);
					
					//源节点权重变更为目标权重+1
					apiErrorCodeService.updateSortWeight(docId, srcCode.getId(), targetSortWeight + 1);
					break;
					
				default:
					break;
			}
		}
		else {//从前往后拖拽
			switch (moveType) {
				case prev://置于目标之前
					//源节点之后的权重值全部-1
					apiErrorCodeService.buildSortWeight(docId,null, srcSortWeight + 1, targetSortWeight - 1, -1);
					
					//源节点权重变更为目标权重-1
					apiErrorCodeService.updateSortWeight(docId, srcCode.getId(), targetSortWeight - 1);
					break;
				
				case next://置于目标之后
					//目标之后的权重值全部-1
					apiErrorCodeService.buildSortWeight(docId,null, srcSortWeight + 1, targetSortWeight, -1);
					
					//源节点权重变更为目标权重
					apiErrorCodeService.updateSortWeight(docId, srcCode.getId(), targetSortWeight);
					break;
					
				default:
					break;
			}
		}
	}
		
	//接口排序
	private void sortInterTreeData(Long docId,TreeNodeInfo srcNode, TreeNodeInfo targetNode, NodeMoveType moveType){
		Inter srcInter = interService.getByDocId(docId, srcNode.getDataId());
		if (InterNodeType.module.name().equals(targetNode.getType())) {//移动到不同模块内部
			Module targetModule = moduleService.getByDocId(docId, targetNode.getDataId());
			sortInterOutside(docId, srcInter, targetModule, moveType);
		}
		else{
			Inter targetInter = interService.getByDocId(docId, targetNode.getDataId());
			//同模块内部排序
			if ((srcInter.getModuleId() == null && targetInter.getModuleId() == null)
					|| FormatUtils.isEqual(srcInter.getModuleId(), targetInter.getModuleId())) {
				sortInterInside(docId, srcInter, targetInter, moveType);
			}
			else{//移动到不同模块下其他接口位置
				sortInterOutside(docId, srcInter, targetInter, moveType);
			}
		}
	}

	//同模块内部排序
	private void sortInterInside(Long docId,Inter srcInter,Inter targetInter,NodeMoveType moveType){
		if (srcInter == null || targetInter == null) {
			return ;
		}
		
		int srcSortWeight = srcInter.getSortWeight();
		int targetSortWeight = targetInter.getSortWeight();
		Long moduleId = srcInter.getModuleId();
		if (srcInter.getSortWeight() > targetInter.getSortWeight()) {//从后往前拖拽
			switch (moveType) {
				case prev://置于目标之前
					//目标及之后的权重值全部+1
					interService.buildSortWeight(docId,moduleId, targetSortWeight, srcSortWeight - 1, 1);
					
					//源节点权重变更为目标权重
					interService.updateSortWeight(docId, srcInter.getId(), targetSortWeight);
					break;
				
				case next://置于目标之后
					//目标之后的权重值全部+1
					interService.buildSortWeight(docId,moduleId, targetSortWeight + 1, srcSortWeight - 1, 1);
					
					//源节点权重变更为目标权重+1
					interService.updateSortWeight(docId, srcInter.getId(), targetSortWeight + 1);
					break;
					
				default:
					break;
			}
		}
		else {//从前往后拖拽
			switch (moveType) {
				case prev://置于目标之前
					//源节点之后的权重值全部-1
					interService.buildSortWeight(docId,moduleId, srcSortWeight + 1, targetSortWeight - 1, -1);
					
					//源节点权重变更为目标权重-1
					interService.updateSortWeight(docId, srcInter.getId(), targetSortWeight - 1);
					break;
				
				case next://置于目标之后
					//目标之后的权重值全部-1
					interService.buildSortWeight(docId,moduleId, srcSortWeight + 1, targetSortWeight, -1);
					
					//源节点权重变更为目标权重
					interService.updateSortWeight(docId, srcInter.getId(), targetSortWeight);
					break;
					
				default:
					break;
			}
		}
	}
	
	//移动到指定模块内部
	private void sortInterOutside(Long docId,Inter srcInter,Module module,NodeMoveType moveType){
		if (srcInter == null || module == null) {
			return ;
		}
		
		//将源节点权重变更为当前模块中的最大值
		Long srcModuleId = srcInter.getModuleId();
		Long targetModuleId = module.getId();
		int srcSortWeight = srcInter.getSortWeight();
		interService.updateSortWeight(docId, srcInter.getId(), interService.getMaxSortWeight(docId, targetModuleId) + 1);
		
		//更新模块信息
		srcInter.setModuleId(targetModuleId);
		interService.updateByDocId(srcInter);
				
		//将源节点所在模块中的当前节点之后的所有权重-1
		interService.buildSortWeight(docId, srcModuleId, srcSortWeight + 1, Integer.MAX_VALUE, -1);
	}
	
	//移动到不同模块指定接口位置
	private void sortInterOutside(Long docId,Inter srcInter,Inter targetInter,NodeMoveType moveType){
		if (srcInter == null || targetInter == null) {
			return ;
		}
		
		//将源节点权重变更为当前模块中的最大值
		Long srcModuleId = srcInter.getModuleId();
		int srcSortWeight = srcInter.getSortWeight();
		Long targetModuleId = targetInter.getModuleId();
		int targetSortWeight = targetInter.getSortWeight();
		switch (moveType) {
			case prev://置于目标之前
				//源节点之后的权重值全部+1
				interService.buildSortWeight(docId,targetModuleId, targetSortWeight, Integer.MAX_VALUE, 1);
				
				//源节点权重变更为目标权重
				interService.updateSortWeight(docId, srcInter.getId(), targetSortWeight);
				break;
			
			case next://置于目标之后
				//目标之后的权重值全部+1
				interService.buildSortWeight(docId,targetModuleId, targetSortWeight + 1, Integer.MAX_VALUE, 1);
				
				//源节点权重变更为目标权重+1
				interService.updateSortWeight(docId, srcInter.getId(), targetSortWeight + 1);
				break;
				
			default:
				break;
		}
		
		//更新模块信息
		srcInter.setModuleId(targetModuleId);
		interService.updateByDocId(srcInter);
				
		//将源节点所在模块中的当前节点之后的所有权重-1
		interService.buildSortWeight(docId, srcModuleId, srcSortWeight + 1, Integer.MAX_VALUE, -1);
	}

	@Override
	public void updateName(Long docId, Long dataId, InterNodeType type,String name) {
		switch (type) {
			case module :
				moduleService.updateName(docId, dataId, name);
				break;
				
			case inter:
				interService.updateName(docId, dataId, name);
				break;
				
			default :
				break;
		}
	}

	@Override
	public void del(Long docId, Long dataId, InterNodeType type) {
		switch (type) {
			case module :
				moduleService.deleteByDocId(docId, dataId);
				interService.updateForDelModule(docId, dataId);
				break;
			
			case inter:
				interService.deleteByDocId(docId, dataId);
				break;
				
			case code:
				apiErrorCodeService.deleteByDocId(docId, dataId);
				break;
				
			default :
				break;
		}
	}

	@Override
	public List<TreeNodeInfo> search(Long docId, String condition) {
		List<Inter> interList = interService.listAllByDocId(docId,null, null, condition);
		List<TreeNodeInfo> result = parseModuleList(docId, interList);
		for (Inter inter : interList) {
			result.add(InterTreeUtil.buildTreeNodeInfo(inter));
		}
		
		return result;
	}
	
	//组装接口所在模块的节点列表
	private List<TreeNodeInfo> parseModuleList(Long docId,List<Inter> interList){
		List<TreeNodeInfo> result = new ArrayList<TreeNodeInfo>();

		Long moduleId = null;
		Integer interCount = 0;
		//统计各个模块下的接口总数
		Map<Long, Integer> interCountMap = MapUtils.newMap();
		for (Inter inter : interList) {
			moduleId = inter.getModuleId() == null ? InterTreeUtil.TREE_MODULE_NODE_ID_NO_GROUP : inter.getModuleId();
			interCount = interCountMap.get(moduleId);
			if (interCount == null) {//不存在
				interCount = 0;
			}
			interCountMap.put(moduleId, interCount + 1);
		}
		
		//优先添加未分组节点
		Integer noGroupInterCount = interCountMap.get(InterTreeUtil.TREE_MODULE_NODE_ID_NO_GROUP);
		if (noGroupInterCount != null) {
			result.add(InterTreeUtil.buildModuleNoGroupNode(InterTreeUtil.TREE_CATA_NODE_ID_INTER,true,noGroupInterCount));
		}
		
		//组建查询关联的接口所在模块列表
		List<Module> moduleList = moduleService.listByIdSet(docId, interCountMap.keySet());
		Integer interCountTemp = null;
		for (Module module : moduleList) {
			interCountTemp = interCountMap.get(module.getId());
			if (interCountTemp == null) {
				interCountTemp = 0;
			}
			result.add(InterTreeUtil.buildTreeNodeInfo(InterTreeUtil.TREE_CATA_NODE_ID_INTER,module,true,interCountTemp));
		}
		
		return result;
	}
}
