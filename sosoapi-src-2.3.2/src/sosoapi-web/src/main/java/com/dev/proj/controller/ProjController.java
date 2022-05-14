package com.dev.proj.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.base.constant.CfgConstants;
import com.dev.base.controller.BaseController;
import com.dev.base.enums.ProjectStatus;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.Pager;
import com.dev.base.util.WebPaginate;
import com.dev.base.util.WebUtil;
import com.dev.base.utils.MapUtils;
import com.dev.base.utils.ValidateUtils;
import com.dev.doc.service.CopyService;
import com.dev.proj.entity.Project;
import com.dev.proj.service.ProjectMemberService;
import com.dev.proj.service.ProjectService;
import com.dev.proj.vo.ProjectInfo;
import com.dev.proj.vo.ProjectRoleInfo;
import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: 项目相关</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午5:39:29</p>
 */
@Controller
@RequestMapping("/auth/proj")
public class ProjController extends BaseController{
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ProjectMemberService projectMemberService;
	
	@Autowired
	private CopyService copyService;
	
	/**
	 * 
			*@name 项目首页
			*@Description  
			*@CreateDate 2015年8月22日下午2:58:59
	 */
	@RequestMapping("/home.htm")
	public String home(HttpServletRequest request,Long projId,Long docId){
		Map<String, Object> paramMap = MapUtils.newMap();
		paramMap.put("projId", projId);
		paramMap.put("docId", docId);
		
		String homeUrl = "/auth/doc/inter/tree/list.htm";
		Long projRoleId = getUserInfo(request).getProjRoleMap().get("" + projId);
		if (projRoleId != null) {
			ProjectRoleInfo roleInfo = getProjRoleInfoCache(request,projRoleId);
			if (roleInfo != null && !StringUtils.isEmpty(roleInfo.getHomeUrl())) {
				homeUrl = roleInfo.getHomeUrl();
			}
		}
		
		return WebUtil.getRedirectUrl(homeUrl, paramMap);
	}
	
	/**
	 * 
			*@name 新增项目信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/add.htm",method = RequestMethod.POST)
	public @ResponseBody Map add(HttpServletRequest request,Project project){
		ValidateUtils.notNull(project.getName(), ErrorCode.SYS_001,"项目名称不能为空");
		
		Long userId = getUserId(request);
		projectService.add(userId,project);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 复制项目信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/copy.htm",method = RequestMethod.POST)
	public @ResponseBody Map copy(HttpServletRequest request,Long projId){
		ValidateUtils.notNull(projId, ErrorCode.SYS_001,"项目id不能为空");
		
		UserInfo userInfo = getUserInfo(request);
		ProjectInfo projectInfo = copyService.copyProj(userInfo.getUserId(), projId);
		//更新当前登陆用户缓存信息
		if (projectInfo != null) {
			updateUserProjRole(request, projectInfo.getProjId(), projectInfo.getDocId(), CfgConstants.PROJ_ROLE_ID_ADMIN);
		}
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 更新项目信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/update.htm",method = RequestMethod.POST)
	public @ResponseBody Map update(HttpServletRequest request,Project project,Long projId){
		ValidateUtils.notNull(projId, ErrorCode.SYS_001,"项目id不能为空");
		ValidateUtils.notNull(project.getName(), ErrorCode.SYS_001,"项目名称不能为空");
		
		project.setId(projId);
		projectService.update(project);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 删除项目信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/del.htm")
	public @ResponseBody Map del(HttpServletRequest request,Long projId){
		ValidateUtils.notNull(projId, ErrorCode.SYS_001,"项目id不能为空");
		
		projectService.deleteById(projId);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 退出项目
			*@Description  
			*@CreateDate 2015年8月22日下午2:07:39
	 */
	@RequestMapping("/json/quit.htm")
	public @ResponseBody Map quit(HttpServletRequest request,Long projId){
		ValidateUtils.notNull(projId, ErrorCode.SYS_001,"项目id不能为空");

		Long userId = getUserId(request);
		projectMemberService.quit(userId, projId);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 项目列表
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/list.htm")
	public String list(HttpServletRequest request,Model model,String code,String name,
						String status,Integer pageNumber,Integer pageSize){
		Long userId = getUserId(request);
		
		ProjectStatus projectStatus = null;
		if (!StringUtils.isEmpty(status)) {
			projectStatus = ProjectStatus.valueOf(status);
		}
		
		Pager pager = new Pager(pageNumber, pageSize);
		List<ProjectInfo> list = projectService.listByUserId(userId,code,name, projectStatus, pager);
		int count = projectService.countByUserId(userId,code,name, projectStatus);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "project/projList";
	}
	
	/**
	 * 
			*@name 项目基本信息
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/info.htm")
	public String getInfo(HttpServletRequest request,Long projId,Model model){
		ValidateUtils.notNull(projId, ErrorCode.SYS_001,"项目id不能为空");
		
		Long userId = getUserId(request);
		ProjectInfo projectInfo = projectService.getInfo(userId, projId);
		model.addAttribute("projInfo", projectInfo);
		
		return "project/projInfo";
	}
}
