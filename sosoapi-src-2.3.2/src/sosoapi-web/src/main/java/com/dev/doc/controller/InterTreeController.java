package com.dev.doc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.base.controller.BaseController;
import com.dev.base.enums.NodeMoveType;
import com.dev.base.enums.InterNodeType;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.WebUtil;
import com.dev.base.utils.MapUtils;
import com.dev.base.utils.ValidateUtils;
import com.dev.base.vo.SelectInfo;
import com.dev.base.vo.TreeNodeInfo;
import com.dev.doc.service.InterTreeService;
import com.dev.doc.service.RespSchemaService;
import com.dev.proj.service.ProjectService;
import com.dev.proj.vo.ProjectInfo;

/**
 * 
		* <p>Title: 项目接口相关</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午5:39:29</p>
 */
@Controller
@RequestMapping("/auth/doc/inter/tree")
public class InterTreeController extends BaseController{
	@Autowired
	private InterTreeService interTreeService;
	
	@Autowired
	private RespSchemaService respSchemaService;
	
	@Autowired
	private ProjectService projectService;
	
	/**
	 * 
			*@name 接口列表
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/list.htm")
	public String list(HttpServletRequest request,Model model,Long projId,
			Long docId,Long moduleId,String name,String path,String description,
			Integer pageNumber,Integer pageSize){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(projId, ErrorCode.SYS_001,"项目id不能为空");
		List<SelectInfo> refSchemaList = respSchemaService.listRespSchema(docId);
		model.addAttribute("refSchemaList", refSchemaList);
		
		//设置项目缓存信息，用于展示项目名称
		initProjCache(request,getUserId(request),projId);
				
		return "apidoc/interTree";
	}
	
	//设置项目缓存信息，用于展示项目名称
	private void initProjCache(HttpServletRequest request,Long userId,Long projId){
		if (projId == null) {
			return ;
		}
		
		Map<String, ProjectInfo> sessionMap = (Map<String, ProjectInfo>)WebUtil.getSessionAttr(request, "projTempMap");
		if (CollectionUtils.isEmpty(sessionMap)) {
			sessionMap = MapUtils.newMap();
		}
		
		String key = "" + projId;
		if (!sessionMap.containsKey(key)) {
			ProjectInfo projectInfo = projectService.getInfo(getUserId(request), projId);
			sessionMap.put(key, projectInfo);
			WebUtil.setSessionAttr(request, "projTempMap", sessionMap);
		}
	}
		
	/**
	 * 
			*@name 获取接口树结构
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/load.htm")
	public @ResponseBody List<TreeNodeInfo> loadTreeData(HttpServletRequest request,Long docId,
				InterNodeType nodeType,String parentNodeId,Long parentDataId,String condition){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		return StringUtils.isEmpty(condition) ? interTreeService.loadTreeData(docId,nodeType, parentNodeId, parentDataId)
													: interTreeService.search(docId, condition);
		
	}
	
	/**
	 * 
			*@name 节点排序
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/sort.htm")
	public @ResponseBody Map sortTreeData(Long docId,String srcNodeInfo,String targetNodeInfo,NodeMoveType moveType){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.isTrue(!StringUtils.isEmpty(srcNodeInfo), ErrorCode.SYS_001,"源节点信息不能为空");
		ValidateUtils.isTrue(!StringUtils.isEmpty(targetNodeInfo), ErrorCode.SYS_001,"目标节点信息不能为空");
		
		TreeNodeInfo srcNode = JsonUtils.toObject(srcNodeInfo, TreeNodeInfo.class);
		TreeNodeInfo targetNode = JsonUtils.toObject(targetNodeInfo, TreeNodeInfo.class);
		
		interTreeService.sortTreeData(docId,srcNode, targetNode, moveType);
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 删除节点
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/del.htm")
	public @ResponseBody Map del(Long docId,Long dataId,InterNodeType type){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(dataId, ErrorCode.SYS_001,"节点dataId不能为空");
		interTreeService.del(docId, dataId, type);
		
		return JsonUtils.createSuccess();
	}
}
