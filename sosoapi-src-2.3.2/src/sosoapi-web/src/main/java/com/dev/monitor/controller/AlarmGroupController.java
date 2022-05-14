package com.dev.monitor.controller;

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
import com.dev.base.vo.SelectInfo;
import com.dev.monitor.entity.AlarmGroup;
import com.dev.monitor.service.AlarmGroupService;
import com.dev.monitor.service.AlarmReceiverService;

/**
 * 
		* <p>Title: 警报接收组</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年5月10日下午10:48:46</p>
 */
@Controller
@RequestMapping("/auth/monitor/alarm/group")
public class AlarmGroupController extends BaseController{
	@Autowired
	private AlarmGroupService groupService;
	
	@Autowired
	private AlarmReceiverService receiverService;
	
	/**
	 * 
			*@name 接收组列表
			*@Description  
			*@CreateDate 2017年5月11日下午4:12:08
	 */
	@RequestMapping("/list.htm")
	public String list(HttpServletRequest request,Model model,String name,
						String status,Integer pageNumber,Integer pageSize){
		Long userId = getUserId(request);
		
		EnableStatus groupStatus = null;
		if (!StringUtils.isEmpty(status)) {
			groupStatus = EnableStatus.valueOf(status);
		}
		
		Pager pager = new Pager(pageNumber, pageSize);
		List<AlarmGroup> list = groupService.listByUserId(userId,name,groupStatus, pager);
		int count = groupService.countByUserId(userId,name, groupStatus);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "monitor/alarmGroupList";
	}
	
	/**
	 * 
			*@name 编辑接收组
			*@Description  
			*@CreateDate 2017年5月11日下午5:33:11
	 */
	@RequestMapping("/update.htm")
	public String update(HttpServletRequest request,AlarmGroup alarmGroup,Long groupId,String receiverIdList){
		ValidateUtils.notNull(groupId, ErrorCode.SYS_001,"接收组id不能为空");
		ValidateUtils.notNull(alarmGroup.getName(), ErrorCode.SYS_001,"名称不能为空");

		alarmGroup.setId(groupId);
		alarmGroup.setUserId(getUserId(request));
		groupService.updateByUserId(alarmGroup,WebUtil.parseLongList(receiverIdList));
		
		return WebUtil.getRedirectUrl("/auth/monitor/alarm/group/list.htm");
	}
	
	/**
	 * 
			*@name 跳转新增页面
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping("/forwardAdd.htm")
	public String forwardAdd(HttpServletRequest request,Model model){
		model.addAttribute("action", "auth/monitor/alarm/group/add.htm");
		model.addAttribute("operType", "add");
		
		List<SelectInfo> receiverList = receiverService.listReceiver(getUserId(request), -1L);
		model.addAttribute("receiverList", receiverList);
		
		return "monitor/alarmGroup";
	}
	
	/**
	 * 
			*@name 跳转修改页面
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping("/forwardUpdate.htm")
	public String forwardUpdate(HttpServletRequest request,Model model,Long groupId){
		ValidateUtils.notNull(groupId, ErrorCode.SYS_001,"接收组id不能为空");
		model.addAttribute("action", "auth/monitor/alarm/group/update.htm");
		model.addAttribute("operType", "update");
		
		Long userId = getUserId(request);
		AlarmGroup alarmGroup = groupService.getByUserId(userId, groupId);
		model.addAttribute("groupInfo", alarmGroup);
		
		List<SelectInfo> receiverList = receiverService.listReceiver(userId, groupId);
		model.addAttribute("receiverList", receiverList);
		
		return "monitor/alarmGroup";
	}
	
	/**
	 * 
			*@name 新增接收组
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/add.htm",method = RequestMethod.POST)
	public String add(HttpServletRequest request,AlarmGroup alarmGroup,String receiverIdList){
		ValidateUtils.notNull(alarmGroup.getName(), ErrorCode.SYS_001,"名称不能为空");
		
		alarmGroup.setUserId(getUserId(request));
		groupService.add(alarmGroup);
		
		return WebUtil.getRedirectUrl("/auth/monitor/alarm/group/list.htm");
	}
	
	/**
	 * 
			*@name 删除接收组
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/del.htm")
	public @ResponseBody Map del(HttpServletRequest request,Long groupId){
		ValidateUtils.notNull(groupId, ErrorCode.SYS_001,"接收组id不能为空");
		Long userId = getUserId(request);
		groupService.delByUserId(userId, groupId);
		return JsonUtils.createSuccess();
	}
}
