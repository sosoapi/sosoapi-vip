package com.dev.doc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.base.controller.BaseController;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.Pager;
import com.dev.base.util.WebPaginate;
import com.dev.base.util.WebUtil;
import com.dev.base.utils.DateUtil;
import com.dev.base.utils.MapUtils;
import com.dev.base.utils.ValidateUtils;
import com.dev.base.vo.SelectInfo;
import com.dev.doc.entity.Inter;
import com.dev.doc.entity.InterParam;
import com.dev.doc.entity.InterResp;
import com.dev.doc.service.CopyService;
import com.dev.doc.service.InterParamService;
import com.dev.doc.service.InterRespService;
import com.dev.doc.service.InterService;
import com.dev.doc.service.ModuleService;
import com.dev.doc.service.RespSchemaService;
import com.dev.doc.util.InterTreeUtil;
import com.dev.doc.vo.InterDetailInfo;
import com.dev.doc.vo.InterInfo;
import com.dev.proj.service.ProjectService;
import com.dev.proj.vo.ProjectInfo;

/**
 * 
		* <p>Title: 项目接口相关</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午5:39:29</p>
 */
@Controller
@RequestMapping("/auth/doc/inter")
public class InterController extends BaseController{
	@Autowired
	private InterService interService;
	
	@Autowired
	private InterParamService interParamService;
	
	@Autowired
	private InterRespService interRespService;
	
	@Autowired
	private RespSchemaService respSchemaService;
	
	@Autowired
	private CopyService copyService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ModuleService moduleService;
	
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

		Pager pager = new Pager(pageNumber, pageSize);
		List<Inter> list = interService.listByDocId(docId, moduleId,name,path,description,pager);
		int count = interService.countByDocId(docId, moduleId,name,path,description);
		pager.setTotalCount(count);
		pager.setList(list);

		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));

		//设置项目缓存信息，用于展示项目名称
		initProjCache(request,getUserId(request),projId);
		
		return "apidoc/interList";
	}
	
	/**
	 * 
			*@name 加载接口列表
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/load.htm")
	public @ResponseBody Pager load(HttpServletRequest request,Long projId,
			Long docId,Long moduleId,String name,String path,String description,
			Integer pageNumber,Integer pageSize){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");

		Pager pager = new Pager(pageNumber, pageSize);
		List<Inter> list = interService.listByDocId(docId, moduleId,name,path,description,pager);
		Map<Long, String> moduleInfo = moduleService.getByDocId(docId);
		List<InterInfo> infoList = new ArrayList<InterInfo>();
		InterInfo info = null;
		for (Inter inter : list) {
			info = new InterInfo();
			info.setInterId(inter.getId());
			info.setDocId(inter.getDocId());
			info.setName(inter.getName());
			info.setPath(inter.getPath());
			info.setCreateDate(DateUtil.formatByLong(inter.getCreateDate()));
			info.setModifyDate(DateUtil.formatByLong(inter.getModifyDate()));
			info.setDescription(inter.getDescription());
			if (inter.getModuleId() != null) {
				info.setModuleId(inter.getModuleId());
				info.setModuleName(moduleInfo.get(inter.getModuleId()));
			}
			
			infoList.add(info);
		}
		
		int count = interService.countByDocId(docId, moduleId,name,path,description);
		pager.setTotalCount(count);
		pager.setList(infoList);
		
		return pager;
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
			*@name 复制接口
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/copy.htm",method = RequestMethod.POST)
	public @ResponseBody Map copy(HttpServletRequest request,Long docId,Long interId){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(interId, ErrorCode.SYS_001,"接口id不能为空");
		
		Long userId = getUserId(request);
		copyService.copyInter(userId, docId, interId);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 跳转到接口基本信息
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/forwardInfo.htm")
	public String forwardInfo(HttpServletRequest request,Long docId,Long interId,Model model){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		InterInfo interInfo = interService.getInfoByDocId(docId,interId);
		model.addAttribute("interInfo", interInfo);
		
		List<SelectInfo> refSchemaList = respSchemaService.listRespSchema(docId);
		model.addAttribute("refSchemaList", refSchemaList);
		
		return "apidoc/interInfo";
	}
	
	/**
	 * 
			*@name 新增接口
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/add.htm")
	public @ResponseBody Map add(HttpServletRequest request,Inter inter,Model model){
		ValidateUtils.notNull(inter.getDocId(), ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(inter.getName(), ErrorCode.SYS_001,"接口名称不能为空");
		ValidateUtils.notNull(inter.getPath(), ErrorCode.SYS_001,"接口url不能为空");
		
		interService.add(inter);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 新增接口详情，包括接口信息，请求参数，请求响应
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/addDetail.htm")
	public @ResponseBody Map addDetail(HttpServletRequest request,InterInfo interInfo){
		ValidateUtils.notNull(interInfo.getDocId(), ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(interInfo.getName(), ErrorCode.SYS_001,"接口名称不能为空");
		ValidateUtils.notNull(interInfo.getPath(), ErrorCode.SYS_001,"接口url不能为空");
		Inter inter = interService.addDetailByDocId(interInfo.getDocId(), interInfo);
		
		return JsonUtils.createSuccess(InterTreeUtil.buildTreeNodeInfo(inter));
	}
	
	/**
	 * 
			*@name 更新项目信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/update.htm",method = RequestMethod.POST)
	public @ResponseBody Map update(HttpServletRequest request,Inter inter,Long interId){
		ValidateUtils.notNull(interId, ErrorCode.SYS_001,"接口id不能为空");
		ValidateUtils.notNull(inter.getDocId(), ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(inter.getName(), ErrorCode.SYS_001,"接口名称不能为空");
		ValidateUtils.notNull(inter.getPath(), ErrorCode.SYS_001,"接口url不能为空");
		
		inter.setId(interId);
		interService.updateByDocId(inter);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 更新项目信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/updateDetail.htm",method = RequestMethod.POST)
	public @ResponseBody Map updateDetail(HttpServletRequest request,InterInfo interInfo){
		ValidateUtils.notNull(interInfo.getInterId(), ErrorCode.SYS_001,"接口id不能为空");
		ValidateUtils.notNull(interInfo.getDocId(), ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(interInfo.getName(), ErrorCode.SYS_001,"接口名称不能为空");
		ValidateUtils.notNull(interInfo.getPath(), ErrorCode.SYS_001,"接口url不能为空");
		interService.updateDetailByDocId(interInfo.getDocId(),interInfo.getInterId(), interInfo);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 删除
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/del.htm")
	public @ResponseBody Map del(HttpServletRequest request,Long docId,Long interId){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(interId, ErrorCode.SYS_001,"接口id不能为空");
		
		interService.deleteByDocId(docId,interId);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 接口基本信息
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/info.htm")
	public @ResponseBody Map getInfo(HttpServletRequest request,Long docId,Long interId,Model model){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		InterInfo interInfo = interService.getInfoByDocId(docId,interId);
		return JsonUtils.createSuccess(interInfo);
	}
	
	/**
	 * 
			*@name 接口详情，包括基本信息，请求和响应
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/detailInfo.htm")
	public @ResponseBody Map getDetailInfo(HttpServletRequest request,Long docId,Long interId,Model model){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		InterInfo basicInfo = interService.getInfoByDocId(docId,interId);
		List<InterParam> paramList = interParamService.listAllByInterId(docId, interId);
		List<InterResp> respList = interRespService.listAllByInterId(docId,interId);
		
		InterDetailInfo detailInfo = new InterDetailInfo();
		detailInfo.setBasicInfo(basicInfo);
		detailInfo.setParamList(paramList);
		detailInfo.setRespList(respList);
		
		return JsonUtils.createSuccess(detailInfo);
	}
}
