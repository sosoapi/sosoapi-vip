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

import com.dev.admin.service.AdminService;
import com.dev.base.controller.BaseController;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.Pager;
import com.dev.base.util.WebPaginate;
import com.dev.base.utils.ValidateUtils;
import com.dev.user.service.UserDetailService;
import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: 管理员首页</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月6日下午4:19:22</p>
 */
@Controller
@RequestMapping("/admin/user")
public class UserMgrController extends BaseController{
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserDetailService userDetailService;
	
	/**
	 * 
			*@name 用户列表
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/list.htm")
	public String listUser(HttpServletRequest request,Model model,String email,String nickName,
						String valid,Long roleId,Integer pageNumber,Integer pageSize){
		Boolean isValid = StringUtils.isEmpty(valid) ? null : Boolean.valueOf(valid);
		
		Pager pager = new Pager(pageNumber, pageSize);
		List<UserInfo> list = userDetailService.listAll(email, nickName, isValid,roleId, pager);
		int count = userDetailService.countAll(email, nickName, isValid,roleId);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		//加载系统角色列表
		loadRole(request,false);
		
		return "admin/userList";
	}
	
	/**
	 * 
			*@name 编辑用户信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/update.htm",method = RequestMethod.POST)
	public @ResponseBody Map updateUser(HttpServletRequest request,UserInfo userInfo,Long userId){
		ValidateUtils.notNull(userId, ErrorCode.SYS_001,"用户id不能为空");
		
		userInfo.setUserId(userId);
		adminService.updateUser(userInfo);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 新增用户信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/add.htm",method = RequestMethod.POST)
	public @ResponseBody Map addUser(String nickName, String email,String passwd,
										boolean locked,boolean valid,Long roleId){
		ValidateUtils.notNull(nickName, ErrorCode.SYS_001,"昵称不能为空");
		ValidateUtils.notNull(email, ErrorCode.SYS_001,"邮箱不能为空");
		ValidateUtils.notNull(passwd, ErrorCode.SYS_001,"密码不能为空");
		ValidateUtils.notNull(roleId, ErrorCode.SYS_001,"用户角色不能为空");
		adminService.addUser(nickName, email, passwd, locked, valid, roleId);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 重置密码
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/resetPasswd.htm",method = RequestMethod.POST)
	public @ResponseBody Map resetPasswd(HttpServletRequest request,Long userId,String passwd){
		ValidateUtils.notNull(passwd, ErrorCode.SYS_001,"新密码不能为空");
		adminService.resetUserPasswd(userId, passwd);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 查询用户基本信息
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/info.htm")
	public @ResponseBody Map getUserInfo(Long userId){
		ValidateUtils.notNull(userId, ErrorCode.SYS_001,"用户id不能为空");
		UserInfo userInfo = userDetailService.getDetailByUserId(userId);
		return JsonUtils.createSuccess(userInfo);
	}
}
