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

import com.dev.admin.entity.FilterChain;
import com.dev.admin.entity.Privilege;
import com.dev.admin.service.PrivilegeService;
import com.dev.admin.vo.PrivilegeInfo;
import com.dev.base.constant.AppConstants;
import com.dev.base.controller.BaseController;
import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.PrivilegeType;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.Pager;
import com.dev.base.util.WebPaginate;
import com.dev.base.util.WebUtil;
import com.dev.base.utils.ValidateUtils;
import com.dev.base.vo.SelectInfo;
import com.dev.base.vo.TreeNodeInfo;

/**
 * 
		* <p>Title: 权限管理</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月6日下午4:19:22</p>
 */
@Controller
@RequestMapping("/admin/priv")
public class PrivMgrController extends BaseController{
	@Autowired
	private PrivilegeService privilegeService;
	
	/**
	 * 
			*@name 查询列表
			*@Description  
			*@CreateDate 2017年6月30日上午11:06:17
	 */
	@RequestMapping("/list.htm")
	public String list(HttpServletRequest request,Model model,
							String name,String url,String type,String status,
							String accessVerify,Long parentId,
							Integer pageNumber,Integer pageSize){
		EnableStatus enableStatus = StringUtils.isEmpty(status) ? null : EnableStatus.valueOf(status);
		PrivilegeType privType = StringUtils.isEmpty(type) ? null : PrivilegeType.valueOf(type);
		Boolean verify = StringUtils.isEmpty(accessVerify) ? null : Boolean.valueOf(accessVerify);
		
		Pager pager = new Pager(pageNumber, pageSize);
		List<PrivilegeInfo> list = privilegeService.list(name, url, privType, enableStatus, verify,parentId,null, pager);
		int count = privilegeService.count(name, url, privType, enableStatus, verify,parentId,null);
		pager.setTotalCount(count);
		pager.setList(list);
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		//加载父菜单列表
		loadParent(request, false);
		
		return "admin/priv/privList";
	}
	
	//加载根菜单，并保持到session中
	private void loadParent(HttpServletRequest request,boolean force){
		if (!force && WebUtil.getSessionAttr(request, AppConstants.SESSION_KEY_SYS_PRIV_PARENT_LIST) != null) {
			return ;
		}
		
		List<SelectInfo> selectList = privilegeService.listParentPriv(null);
		WebUtil.setSessionAttr(request, AppConstants.SESSION_KEY_SYS_PRIV_PARENT_LIST, selectList);
	}
	
	//重新加载
	private void reloadParent(HttpServletRequest request,Privilege privilege){
		if (privilege.getType() == PrivilegeType.cate) {
			loadParent(request,true);
		}
	}
	
	/**
	 * 
			*@name 新增
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/add.htm",method = RequestMethod.POST)
	public @ResponseBody Map add(HttpServletRequest request,Privilege privilege){
		ValidateUtils.notNull(privilege.getName(), ErrorCode.SYS_001,"名称不能为空");
		ValidateUtils.notNull(privilege.getUrl(), ErrorCode.SYS_001,"url不能为空");
		
		privilegeService.add(privilege);
		//重新加载
		reloadParent(request,privilege);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 获取详情
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/info.htm",method = RequestMethod.GET)
	public @ResponseBody Map getInfo(FilterChain filterChain,Long privId){
		ValidateUtils.notNull(privId, ErrorCode.SYS_001,"权限id不能为空");
		Privilege privilege = privilegeService.getById(privId);
		return JsonUtils.createSuccess(privilege);
	}
	
	/**
	 * 
			*@name 编辑
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/update.htm",method = RequestMethod.POST)
	public @ResponseBody Map update(HttpServletRequest request,Privilege privilege,Long privId){
		ValidateUtils.notNull(privId, ErrorCode.SYS_001,"权限id不能为空");
		ValidateUtils.notNull(privilege.getName(), ErrorCode.SYS_001,"名称不能为空");
		ValidateUtils.notNull(privilege.getUrl(), ErrorCode.SYS_001,"url不能为空");
		
		privilege.setId(privId);
		privilegeService.update(privilege);
		//重新加载
		reloadParent(request,privilege);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 删除
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/del.htm",method = RequestMethod.POST)
	public @ResponseBody Map del(Long privId){
		ValidateUtils.notNull(privId, ErrorCode.SYS_001,"权限id不能为空");
		privilegeService.deleteById(privId);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 查询角色相关权限树
			*@Description  用于角色权限设置
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/listTree.htm")
	public @ResponseBody List<TreeNodeInfo> listTreeByRoleId(Long roleId){
		ValidateUtils.notNull(roleId, ErrorCode.SYS_001,"角色id不能为空");
		return privilegeService.listTreeByRoleId(roleId);
	}
}
