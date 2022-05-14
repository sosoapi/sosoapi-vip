package com.dev.doc.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;

import com.dev.base.constant.AppConstants;
import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.InterNodeType;
import com.dev.base.json.JsonUtils;
import com.dev.base.utils.FormatUtils;
import com.dev.base.utils.MapUtils;
import com.dev.base.utils.ResourceUtils;
import com.dev.base.vo.TreeNodeInfo;
import com.dev.doc.entity.ApiErrorCode;
import com.dev.doc.entity.Inter;
import com.dev.doc.entity.Module;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * 
		* <p>Title: 接口管理树工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年4月14日下午2:34:17</p>
 */
public class InterTreeUtil {
	/**
	 * 模块层级
	 */
	public static int TREE_NODE_MODULE_LEVEL;
	
	/**
	 * 接口层级
	 */
	public static int TREE_NODE_INTER_LEVEL;
	
	/**
	 * 错误码层级
	 */
	public static int TREE_NODE_CODE_LEVEL;
	
	/**
	 * 分类，接口
	 */
	public static Long TREE_CATA_NODE_ID_INTER;
	
	/**
	 * 分类，数据结构
	 */
	public static Long TREE_CATA_NODE_ID_SCHEMA;
	
	/**
	 * 分类，错误码
	 */
	public static Long TREE_CATA_NODE_ID_CODE;
	
	/**
	 * 模块，所有节点id
	 */
	public static Long TREE_MODULE_NODE_ID_DEF;
	
	/**
	 * 模块，回收站节点id
	 */
	public static Long TREE_MODULE_NODE_ID_RECYCLE; 
	
	/**
	 * 模块，未分组节点id
	 */
	public static Long TREE_MODULE_NODE_ID_NO_GROUP;
	
	/**
	 * 静态根节点
	 */
	public static Map<String, TreeNodeInfo> staticNodeMap = MapUtils.newMap();
	
	/**
	 * 分类节点列表
	 */
	public static List<TreeNodeInfo> baseCataNodeList = new ArrayList<TreeNodeInfo>();
	
	/**
	 * 系统模块接口列表
	 */
	public static List<TreeNodeInfo> baseModuleNodeList = new ArrayList<TreeNodeInfo>();
	
	//初始化设置
	static {
		Map<String, Object> settingInfo = MapUtils.newMap();
		try {
			String interTreeSetting = IOUtils.toString(ResourceUtils.loadSingle("classpath:interTreeSetting.json").getInputStream(),AppConstants.DEF_CHARSET);
			settingInfo = JsonUtils.toObject(interTreeSetting, HashMap.class);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		//初始化固定id
		TREE_NODE_MODULE_LEVEL = FormatUtils.parseInt(settingInfo.get("moduleLevel"));
		TREE_NODE_INTER_LEVEL = FormatUtils.parseInt(settingInfo.get("interLevel"));
		TREE_NODE_CODE_LEVEL = FormatUtils.parseInt(settingInfo.get("codeLevel"));
		TREE_CATA_NODE_ID_CODE = FormatUtils.parseLong(settingInfo.get("cataNodeIdCode"));
		TREE_CATA_NODE_ID_SCHEMA = FormatUtils.parseLong(settingInfo.get("cataNodeIdSchema"));
		TREE_CATA_NODE_ID_INTER = FormatUtils.parseLong(settingInfo.get("cataNodeIdInter"));
		TREE_MODULE_NODE_ID_DEF = FormatUtils.parseLong(settingInfo.get("moduleNodeIdDef"));
		TREE_MODULE_NODE_ID_RECYCLE = FormatUtils.parseLong(settingInfo.get("moduleNodeIdRecycle"));
		TREE_MODULE_NODE_ID_NO_GROUP = FormatUtils.parseLong(settingInfo.get("moduleNodeIdNoGroup"));
		
		//初始化所有固定节点
		List<Map> nodeInfoList = new ArrayList<Map>(); 
		nodeInfoList.addAll((List<Map>)settingInfo.get("cataNodeList"));
		nodeInfoList.addAll((List<Map>)settingInfo.get("moduleNodeList"));
		String nodeInfoListJson = JsonUtils.toJson(nodeInfoList);
		List<TreeNodeInfo> nodeList = JsonUtils.toObject(nodeInfoListJson, new TypeReference<List<TreeNodeInfo>>(){});
		String cataTypeName = InterNodeType.cata.name();
		String moduleTypeName = InterNodeType.module.name();
		for (TreeNodeInfo treeNodeInfo : nodeList) {
			staticNodeMap.put(treeNodeInfo.getId(), treeNodeInfo);
			
			if (treeNodeInfo.getStatus() == EnableStatus.on) {
				if (cataTypeName.equals(treeNodeInfo.getType())) {
					baseCataNodeList.add(treeNodeInfo);
				}
				else if (moduleTypeName.equals(treeNodeInfo.getType())) {
					baseModuleNodeList.add(treeNodeInfo);
				}
			}
		}
	}
	
	/**
	 * 
			*@name 创建模块节点
			*@Description  
			*@CreateDate 2017年4月16日上午9:21:44
	 */
	public static TreeNodeInfo buildTreeNodeInfo(Long cataId,Module module,boolean open,int childrenCount){
		TreeNodeInfo nodeInfo = new TreeNodeInfo();
		nodeInfo.setId(buildModuleNodeId(cataId,module.getId()));
		nodeInfo.setDataId(module.getId());
		nodeInfo.setParentId(buildCataNodeId(cataId));
		nodeInfo.setParentDataId(cataId);
		nodeInfo.setName(module.getName());
		nodeInfo.setTitle(module.getName());
		nodeInfo.setOpen(open);
		nodeInfo.setIsParent(true);
		nodeInfo.setDrag(true);
		nodeInfo.setMinLevel(TREE_NODE_MODULE_LEVEL);
		nodeInfo.setMaxLevel(TREE_NODE_MODULE_LEVEL);
		nodeInfo.setEnableAdd(true);
		nodeInfo.setEnableDel(true);
		nodeInfo.setEnableEdit(true);
		nodeInfo.setChildrenCount(childrenCount);
		nodeInfo.setType(InterNodeType.module.name());
		nodeInfo.setParentType(InterNodeType.cata.name());
		
		return nodeInfo;
	}
	
	/**
	 * 
			*@name 创建接口节点
			*@Description  
			*@CreateDate 2017年4月16日上午9:22:55
	 */
	public static TreeNodeInfo buildTreeNodeInfo(Inter inter){
		TreeNodeInfo nodeInfo = new TreeNodeInfo();
		nodeInfo.setId(buildInterNodeId(inter.getId()));
		nodeInfo.setDataId(inter.getId());
		nodeInfo.setParentDataId(inter.getModuleId());
		nodeInfo.setName(inter.getName());
		nodeInfo.setTitle(inter.getName());
		nodeInfo.setOpen(false);
		nodeInfo.setIsParent(false);
		nodeInfo.setDrag(true);
		nodeInfo.setMinLevel(TREE_NODE_INTER_LEVEL);
		nodeInfo.setMaxLevel(TREE_NODE_INTER_LEVEL);
		nodeInfo.setEnableAdd(false);
		nodeInfo.setEnableDel(true);
		nodeInfo.setEnableEdit(true);
		nodeInfo.setType(InterNodeType.inter.name());
		nodeInfo.setParentType(InterNodeType.module.name());
		if (inter.getModuleId() == null) {//默认分组
			nodeInfo.setParentId(buildModuleNodeId(TREE_CATA_NODE_ID_INTER,TREE_MODULE_NODE_ID_NO_GROUP));
		}
		else{
			nodeInfo.setParentId(buildModuleNodeId(TREE_CATA_NODE_ID_INTER,inter.getModuleId()));	
		}
		
		//弃用接口节点名称样式
		if (inter.isDeprecated()) {
			nodeInfo.setFontClass(TreeNodeInfo.FONT_CLASS_DEPRECATED);
		}
		
		return nodeInfo;
	}

	/**
	 * 
			*@name 创建错误码节点
			*@Description  
			*@CreateDate 2017年4月16日上午9:24:05
	 */
	public static TreeNodeInfo buildTreeNodeInfo(ApiErrorCode apiErrorCode) {
		TreeNodeInfo nodeInfo = new TreeNodeInfo();
		nodeInfo.setId(buildErrorCodeNodeId(apiErrorCode.getId()));
		nodeInfo.setParentId(buildCataNodeId(TREE_CATA_NODE_ID_CODE));
		nodeInfo.setDataId(apiErrorCode.getId());
		nodeInfo.setParentDataId(TREE_CATA_NODE_ID_CODE);
		nodeInfo.setName(apiErrorCode.getCode());
		nodeInfo.setTitle(apiErrorCode.getCode());
		nodeInfo.setOpen(false);
		nodeInfo.setIsParent(false);
		nodeInfo.setDrag(true);
		nodeInfo.setMinLevel(TREE_NODE_CODE_LEVEL);
		nodeInfo.setMaxLevel(TREE_NODE_CODE_LEVEL);
		nodeInfo.setEnableAdd(false);
		nodeInfo.setEnableDel(true);
		nodeInfo.setEnableEdit(true);
		nodeInfo.setType(InterNodeType.code.name());
		nodeInfo.setParentType(InterNodeType.cata.name());
		
		return nodeInfo;
	}
	
	/**
	 * 
			*@name 创建系统分类节点树
			*@Description  
			*@CreateDate 2017年9月28日下午2:22:04
	 */
	public static List<TreeNodeInfo> buildSysCataTreeData(){
		List<TreeNodeInfo> result = new ArrayList<TreeNodeInfo>();
		TreeNodeInfo nodeInfo = null;
		//深度拷贝
		for (TreeNodeInfo treeNodeInfo : baseCataNodeList) {
			nodeInfo = new TreeNodeInfo();
			BeanUtils.copyProperties(treeNodeInfo, nodeInfo);
			result.add(nodeInfo);
		}
		return result;
	}
	
	/**
	 * 
			*@name 创建系统模块节点树
			*@Description  
			*@CreateDate 2017年9月28日下午2:22:04
	 */
	public static List<TreeNodeInfo> buildSysModuleTreeData(Long cataId){
		List<TreeNodeInfo> result = new ArrayList<TreeNodeInfo>();
		TreeNodeInfo nodeInfo = null;
		//深度拷贝
		for (TreeNodeInfo treeNodeInfo : baseModuleNodeList) {
			nodeInfo = new TreeNodeInfo();
			BeanUtils.copyProperties(treeNodeInfo, nodeInfo);
			nodeInfo.setId(buildModuleNodeId(cataId, nodeInfo.getDataId()));
			result.add(nodeInfo);
		}
		return result;
	}
	
	/**
	 * 
			*@name 创建展示未分组接口节点
			*@Description  
			*@CreateDate 2017年4月14日下午3:04:53
	 */
	public static TreeNodeInfo buildModuleNoGroupNode(Long cataId,boolean open,int childrenCount){
		return buildStaticNode(buildModuleNodeId(cataId,TREE_MODULE_NODE_ID_NO_GROUP), open, childrenCount);
	}

	//创建静态节点
	private static TreeNodeInfo buildStaticNode(String staticNodeId,boolean open,Integer childrenCount){
		TreeNodeInfo srcNode = staticNodeMap.get(staticNodeId);
		if (srcNode == null || srcNode.getStatus() == EnableStatus.off) {
			return null;
		}
		
		TreeNodeInfo nodeInfo = new TreeNodeInfo();
		BeanUtils.copyProperties(srcNode, nodeInfo);
		nodeInfo.setOpen(open);
		
		if (childrenCount != null) {
			nodeInfo.setChildrenCount(childrenCount);
		}
		
		return nodeInfo;
	}
	
	//组装分类节点id
	private static String buildCataNodeId(Long cataId){
		return "cata_" + cataId;
	}
	
	//组装模块节点id
	private static String buildModuleNodeId(Long cataId,Long moduleId){
		return "module_" + cataId + "_" + moduleId;
	}
	
	//组装接口节点id
	private static String buildInterNodeId(Long interId){
		return "inter_" + interId;
	}
	
	//组装错误码节点id
	private static String buildErrorCodeNodeId(Long codeId){
		return "code_" + codeId;
	}
}
