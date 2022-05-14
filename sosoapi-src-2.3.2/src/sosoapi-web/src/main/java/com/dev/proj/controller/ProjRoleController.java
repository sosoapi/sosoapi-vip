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

import com.dev.base.controller.BaseController;
import com.dev.base.enums.EnableStatus;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.Pager;
import com.dev.base.util.WebPaginate;
import com.dev.base.util.WebUtil;
import com.dev.base.utils.ValidateUtils;
import com.dev.base.vo.TreeNodeInfo;
import com.dev.proj.entity.ProjectRole;
import com.dev.proj.service.ProjectRoleService;

/**
 * 
		* <p>Title: 项目角色管理</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月6日下午4:19:22</p>
 */
@Controller
@RequestMapping("/auth/proj/role")
public class ProjRoleController extends BaseController{
	@Autowired
	private ProjectRoleService projectRoleService;
	
	/**
	 * 
			*@name 查询列表
			*@Description  
			*@CreateDate 2017年6月30日上午11:06:17
	 */
	@RequestMapping("/list.htm")
	public String list(HttpServletRequest request,Model model,Long projId,
							String name,String code,String status,
							Integer pageNumber,Integer pageSize){
		ValidateUtils.notNull(projId, ErrorCode.SYS_001,"项目id不能为空");
		EnableStatus enableStatus = StringUtils.isEmpty(status) ? null : EnableStatus.valueOf(status);
		Pager pager = new Pager(pageNumber, pageSize);
		List<ProjectRole> list = projectRoleService.list(projId,name,code, enableStatus, pager);
		int count = projectRoleService.count(projId,name,code, enableStatus);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "project/projRoleList";
	}
	
	/**
	 * 
			*@name 新增
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/add.htm",method = RequestMethod.POST)
	public @ResponseBody Map add(HttpServletRequest request,ProjectRole role){
		ValidateUtils.notNull(role.getName(), ErrorCode.SYS_001,"名称不能为空");
		ValidateUtils.notNull(role.getProjId(), ErrorCode.SYS_001,"项目id不能为空");
		projectRoleService.add(role);
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 获取详情
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/info.htm",method = RequestMethod.GET)
	public @ResponseBody Map getInfo(Long projId,Long roleId){
		ValidateUtils.notNull(roleId, ErrorCode.SYS_001,"角色id不能为空");
		ValidateUtils.notNull(projId, ErrorCode.SYS_001,"项目id不能为空");
		ValidateUtils.isTrue(projectRoleService.isExist(projId, roleId), ErrorCode.SYS_006);
		
		ProjectRole role = projectRoleService.getById(roleId);
		return JsonUtils.createSuccess(role);
	}
	
	/**
	 * 
			*@name 编辑
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/update.htm",method = RequestMethod.POST)
	public @ResponseBody Map update(HttpServletRequest request,ProjectRole role,Long roleId){
		ValidateUtils.notNull(roleId, ErrorCode.SYS_001,"角色id不能为空");
		ValidateUtils.notNull(role.getProjId(), ErrorCode.SYS_001,"项目id不能为空");
		ValidateUtils.notNull(role.getName(), ErrorCode.SYS_001,"名称不能为空");
		ValidateUtils.isTrue(projectRoleService.isExist(role.getProjId(), roleId), ErrorCode.SYS_006);
		
		role.setId(roleId);
		projectRoleService.update(role);
		
		//更新权限缓存
		clearProjRolePrivMapCache(request,roleId);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 删除
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/del.htm",method = RequestMethod.POST)
	public @ResponseBody Map del(HttpServletRequest request,Long projId,Long roleId){
		ValidateUtils.notNull(roleId, ErrorCode.SYS_001,"角色id不能为空");
		ValidateUtils.isTrue(projectRoleService.isExist(projId, roleId), ErrorCode.SYS_006);
		
		projectRoleService.deleteById(roleId);
		
		//更新权限缓存
		clearProjRolePrivMapCache(request,roleId);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 权限设置
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/forwardPrivSet.htm",method = RequestMethod.GET)
	public String forwardPrivSet(HttpServletRequest request){
		return "project/projRolePrivSet";
	}
	
	/**
	 * 
			*@name 查询角色相关权限树
			*@Description  用于角色权限设置
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/listTree.htm")
	public @ResponseBody List<TreeNodeInfo> listTreeByRoleId(Long projId,Long roleId){
		ValidateUtils.notNull(roleId, ErrorCode.SYS_001,"角色id不能为空");
		ValidateUtils.notNull(projId, ErrorCode.SYS_001,"项目id不能为空");
		ValidateUtils.isTrue(projectRoleService.isExist(projId, roleId), ErrorCode.SYS_006);
		
		return projectRoleService.listTreeByRoleId(roleId);
	}
	
	/**
	 * 
			*@name 更新角色权限
			*@Description  
			*@CreateDate 2017年7月3日下午2:39:11
	 */
	@RequestMapping(value = "/json/updatePriv.htm",method = RequestMethod.POST)
	public @ResponseBody Map updatePriv(HttpServletRequest request,Long projId,Long roleId,String privIdList){
		ValidateUtils.notNull(roleId, ErrorCode.SYS_001,"角色id不能为空");
		ValidateUtils.notNull(projId, ErrorCode.SYS_001,"项目id不能为空");
		ValidateUtils.isTrue(projectRoleService.isExist(projId, roleId), ErrorCode.SYS_006);
		
		List<Long> idList = WebUtil.parseLongList(privIdList);
		projectRoleService.updatePriv(roleId, idList);
		//更新权限缓存
		clearProjRolePrivMapCache(request,roleId);
		
		return JsonUtils.createSuccess();
	} 
}
