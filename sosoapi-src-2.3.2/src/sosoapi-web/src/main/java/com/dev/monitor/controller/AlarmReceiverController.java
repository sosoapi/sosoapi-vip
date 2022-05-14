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
import com.dev.base.utils.RegexUtil;
import com.dev.base.utils.ValidateUtils;
import com.dev.base.vo.ValidInfo;
import com.dev.monitor.entity.AlarmReceiver;
import com.dev.monitor.service.AlarmReceiverService;

/**
 * 
		* <p>Title: 警报接收者</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年5月10日下午10:56:37</p>
 */
@Controller
@RequestMapping("/auth/monitor/alarm/receiver")
public class AlarmReceiverController extends BaseController{
	@Autowired
	private AlarmReceiverService receiverService;
	
	/**
	 * 
			*@name 接收者列表
			*@Description  
			*@CreateDate 2017年5月11日下午4:12:08
	 */
	@RequestMapping("/list.htm")
	public String list(HttpServletRequest request,Model model,String name,String phone,String email,
						String status,Integer pageNumber,Integer pageSize){
		Long userId = getUserId(request);
		
		EnableStatus receiverStatus = null;
		if (!StringUtils.isEmpty(status)) {
			receiverStatus = EnableStatus.valueOf(status);
		}
		
		Pager pager = new Pager(pageNumber, pageSize);
		List<AlarmReceiver> list = receiverService.listByUserId(userId,name, phone, email, receiverStatus, pager);
		int count = receiverService.countByUserId(userId,name, phone, email, receiverStatus);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "monitor/alarmReceiverList";
	}
	
	/**
	 * 
			*@name 编辑接收者
			*@Description  
			*@CreateDate 2017年5月11日下午5:33:11
	 */
	@RequestMapping("/json/update.htm")
	public @ResponseBody Map update(HttpServletRequest request,AlarmReceiver receiver,Long receiverId){
		ValidateUtils.notNull(receiverId, ErrorCode.SYS_001,"接收者id不能为空");
		ValidateUtils.notNull(receiver.getName(), ErrorCode.SYS_001,"姓名不能为空");
		ValidateUtils.notNull(receiver.getEmail(), ErrorCode.SYS_001,"邮箱不能为空");
		
		receiver.setId(receiverId);
		receiver.setUserId(getUserId(request));
		receiverService.updateByUserId(receiver);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 新增接收者
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/add.htm",method = RequestMethod.POST)
	public @ResponseBody Map add(HttpServletRequest request,String name,String phone,String email,EnableStatus status,
										String description,String smsCode){
		ValidateUtils.notNull(name, ErrorCode.SYS_001,"姓名不能为空");
		ValidateUtils.notNull(email, ErrorCode.SYS_001,"邮箱不能为空");
		ValidateUtils.isTrue(RegexUtil.isEmail(email), "邮箱格式错误");
		
		AlarmReceiver receiver = new AlarmReceiver();
		receiver.setName(name);
		receiver.setEmail(email);
		receiver.setPhone(phone);
		receiver.setStatus(status);
		receiver.setDescription(description);
		receiver.setUserId(getUserId(request));
		//TO-DO 默认验证
		receiver.setEmailValid(true);
		receiverService.add(receiver);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 验证邮箱是否已存在
			*@Description  
			*@CreateDate 2017年5月11日下午6:23:57
	 */
	@RequestMapping(value = "/json/validEmail.htm")
	public @ResponseBody ValidInfo validEmail(HttpServletRequest request,String email){
		ValidateUtils.isTrue(RegexUtil.isEmail(email), "邮箱格式错误");
		Long userId = getUserId(request);
		ValidInfo result = new ValidInfo();
		result.setValid(!receiverService.isEmailExist(userId, email));
		
		return result;
	}
	
	/**
	 * 
			*@name 查询接收者
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/info.htm")
	public @ResponseBody Map getInfo(HttpServletRequest request,Long receiverId){
		ValidateUtils.notNull(receiverId, ErrorCode.SYS_001,"接收者id不能为空");
		Long userId = getUserId(request);
		AlarmReceiver receiver = receiverService.getByUserId(userId, receiverId);
		return JsonUtils.createSuccess(receiver);
	}
	
	/**
	 * 
			*@name 删除接收者
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/del.htm")
	public @ResponseBody Map del(HttpServletRequest request,Long receiverId){
		ValidateUtils.notNull(receiverId, ErrorCode.SYS_001,"接收者id不能为空");
		Long userId = getUserId(request);
		receiverService.delByUserId(userId, receiverId);
		return JsonUtils.createSuccess();
	}
}
