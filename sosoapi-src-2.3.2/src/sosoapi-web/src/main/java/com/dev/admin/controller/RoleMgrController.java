package com.dev.admin.controller;

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

import com.dev.admin.entity.Role;
import com.dev.admin.service.RoleService;
import com.dev.base.controller.BaseController;
import com.dev.base.enums.EnableStatus;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.Pager;
import com.dev.base.util.WebPaginate;
import com.dev.base.util.WebUtil;
import com.dev.base.utils.ValidateUtils;

/**
 * 
		* <p>Title: 角色管理</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月6日下午4:19:22</p>
 */
@Controller
@RequestMapping("/admin/role")
public class RoleMgrController extends BaseController{
	@Autowired
	private RoleService roleService;
	
	/**
	 * 
			*@name 查询列表
			*@Description  
			*@CreateDate 2017年6月30日上午11:06:17
	 */
	@RequestMapping("/list.htm")
	public String list(HttpServletRequest request,Model model,
							String name,String code,String status,
							Integer pageNumber,Integer pageSize){
		EnableStatus enableStatus = StringUtils.isEmpty(status) ? null : EnableStatus.valueOf(status);
		Pager pager = new Pager(pageNumber, pageSize);
		List<Role> list = roleService.list(name,code, enableStatus, pager);
		int count = roleService.count(name,code, enableStatus);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "admin/priv/roleList";
	}
	
	/**
	 * 
			*@name 新增
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/add.htm",method = RequestMethod.POST)
	public @ResponseBody Map add(HttpServletRequest request,Role role){
		ValidateUtils.notNull(role.getName(), ErrorCode.SYS_001,"名称不能为空");
		
		roleService.add(role);
		loadRole(request, true);
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 获取详情
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/info.htm",method = RequestMethod.GET)
	public @ResponseBody Map getInfo(Role role,Long roleId){
		ValidateUtils.notNull(roleId, ErrorCode.SYS_001,"角色id不能为空");
		Role chain = roleService.getById(roleId);
		return JsonUtils.createSuccess(chain);
	}
	
	/**
	 * 
			*@name 编辑
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/update.htm",method = RequestMethod.POST)
	public @ResponseBody Map update(HttpServletRequest request,Role role,Long roleId){
		ValidateUtils.notNull(roleId, ErrorCode.SYS_001,"角色id不能为空");
		ValidateUtils.notNull(role.getName(), ErrorCode.SYS_001,"名称不能为空");
		
		role.setId(roleId);
		roleService.update(role);
		loadRole(request, true);
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 删除
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/del.htm",method = RequestMethod.POST)
	public @ResponseBody Map del(HttpServletRequest request,Long roleId){
		ValidateUtils.notNull(roleId, ErrorCode.SYS_001,"角色id不能为空");
		roleService.deleteById(roleId);
		loadRole(request, true);
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 权限设置
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/forwardPrivSet.htm",method = RequestMethod.GET)
	public String forwardPrivSet(HttpServletRequest request,Model model,Long roleId){
		return "admin/priv/rolePrivSet";
	}
	
	/**
	 * 
			*@name 更新角色权限
			*@Description  
			*@CreateDate 2017年7月3日下午2:39:11
	 */
	@RequestMapping(value = "/json/updatePriv.htm",method = RequestMethod.POST)
	public @ResponseBody Map updatePriv(Long roleId,String privIdList){
		ValidateUtils.notNull(roleId, ErrorCode.SYS_001,"角色id不能为空");
		List<Long> idList = WebUtil.parseLongList(privIdList);
		roleService.updatePriv(roleId, idList);
		
		return JsonUtils.createSuccess();
	} 
}
