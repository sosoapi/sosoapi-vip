package com.dev.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.base.controller.BaseController;
import com.dev.base.utils.MapUtils;
import com.dev.base.vo.TreeNodeInfo;

/**
 * 
		* <p>Title: ztree demo对应的接口</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午5:39:29</p>
 */
@Controller
@RequestMapping("/demo/tree")
public class TreeDemoController extends BaseController{
	private static Map<Long, List<TreeNodeInfo>> initData = getInitData();
	
	/**
	 * 
			*@name 获取异步数据
			*@Description  
			*@CreateDate 2017年3月9日上午10:35:26
	 */
	@RequestMapping("/loadData.htm")
	public @ResponseBody List<TreeNodeInfo> loadData(Long id,Integer level,Long projId,Long docId){
		System.out.println("id:" + id + ",level:" + level + ",projId:" + projId + ",docId:" + docId);
		return id == null ? getInitData().get(0L) : getInitData().get(id);
	}
	
	private static Map<Long, List<TreeNodeInfo>> getInitData(){
		Map<Long,List<TreeNodeInfo>> result = MapUtils.newMap();
		List<TreeNodeInfo> rootList = new ArrayList<TreeNodeInfo>();
		List<TreeNodeInfo> subList = null;
		TreeNodeInfo rootNode = null;
		TreeNodeInfo childNode = null;
		Long rootNodeId = null;
		
		for (int i = 1; i < 11; i ++) {
			rootNodeId = Long.parseLong(i + "");
			
			rootNode = new TreeNodeInfo();
			rootNode.setParentId("0");
			rootNode.setId("" + rootNodeId);
			rootNode.setIsParent(true);
			rootNode.setName("node_" + i);
			rootNode.setTitle("title_" + i);
			rootNode.setOpen(false);
			rootNode.setMaxLevel(0);
			rootNode.setMinLevel(0);
			rootNode.setEnableAdd(true);
			rootNode.setEnableDel(true);
			rootNode.setEnableEdit(true);
			rootList.add(rootNode);
			
			subList = new ArrayList<TreeNodeInfo>();
			for (int j = 1; j < 5; j++) {
				childNode = new TreeNodeInfo();
				childNode.setParentId("" + rootNodeId);
				childNode.setId("" + i + j);
				childNode.setIsParent(false);
				childNode.setName("sub_node_" + i + j);
				childNode.setTitle("sub_node_" + i + j);
				childNode.setOpen(false);
				childNode.setDropInner(false);
				childNode.setMinLevel(1);
				childNode.setMaxLevel(1);
				childNode.setEnableAdd(false);
				childNode.setEnableDel(true);
				childNode.setEnableEdit(true);
				subList.add(childNode);
			}
			result.put(rootNodeId, subList);
		}
		
		result.put(0L, rootList);
		return result;
	}
}
