package com.dev.doc.service;

import java.util.List;

import com.dev.base.enums.InterNodeType;
import com.dev.base.enums.NodeMoveType;
import com.dev.base.vo.TreeNodeInfo;

/**
 * 
		* <p>Title: 接口树管理</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年3月23日下午4:12:37</p>
 */
public interface InterTreeService {
	/**
	 * 
			*@name 查询树结构数据
			*@Description  
			*@CreateDate 2017年3月15日下午6:01:41
	 */
	List<TreeNodeInfo> loadTreeData(Long docId,InterNodeType nodeType,String parentNodeId,Long parentDataId);
	
	/**
	 * 
			*@name 搜索
			*@Description  
			*@CreateDate 2017年3月15日下午6:01:41
	 */
	List<TreeNodeInfo> search(Long docId,String condition);
	
	/**
	 * 
			*@name 排序树结构
			*@Description  
			*@CreateDate 2017年3月23日下午4:03:57
	 */
	void sortTreeData(Long docId,TreeNodeInfo srcNode,TreeNodeInfo targetNode,NodeMoveType moveType);
	
	/**
	 * 
			*@name 更新名称
			*@Description  
			*@CreateDate 2017年3月26日下午4:31:09
	 */
	void updateName(Long docId,Long dataId,InterNodeType type,String name);
	
	/**
	 * 
			*@name 删除节点
			*@Description  
			*@CreateDate 2017年3月26日下午4:37:31
	 */
	void del(Long docId,Long dataId,InterNodeType type);
	
	/**
	 * 
			*@name 加载分类节点
			*@Description  
			*@CreateDate 2017年7月21日下午4:19:32
	 */
	List<TreeNodeInfo> loadCataTreeData(Long docId);
	
	/**
	 * 
			*@name 加载接口模块树结构数据
			*@Description  包括静态模块和自定义模块，附带接口数目
			*@CreateDate 2017年7月21日下午4:20:48
	 */
	List<TreeNodeInfo> loadInterModuleTreeData(Long docId);
	
	/**
	 * 
			*@name 加载接口模块树结构数据
			*@Description  包括静态模块和自定义模块，附带接口数目
			*@CreateDate 2017年7月21日下午4:20:48
	 */
	List<TreeNodeInfo> loadErrorCodeModuleTreeData(Long docId);
	
	/**
	 * 
			*@name 加载错误码树结构数据
			*@Description  
			*@CreateDate 2017年7月21日下午4:21:26
	 */
	List<TreeNodeInfo> loadErrorCodeTreeData(Long docId,Long moduleId);
}
