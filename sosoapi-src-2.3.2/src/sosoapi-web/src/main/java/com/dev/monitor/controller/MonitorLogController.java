package com.dev.monitor.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.base.controller.BaseController;
import com.dev.base.enums.MonitorStatus;
import com.dev.base.enums.MonitorType;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.util.Pager;
import com.dev.base.util.WebPaginate;
import com.dev.base.utils.DateUtil;
import com.dev.base.utils.ValidateUtils;
import com.dev.monitor.service.MonitorLogService;
import com.dev.monitor.vo.MonitorLogInfo;

/**
 * 
		* <p>Title: 监控日志</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年5月10日下午10:59:36</p>
 */
@Controller
@RequestMapping("/auth/monitor/log")
public class MonitorLogController extends BaseController{
	@Autowired
	private MonitorLogService monitorLogService;
	
	/**
	 * 
			*@name 监控列表
			*@Description  
			*@CreateDate 2017年5月11日下午4:12:08
	 */
	@RequestMapping("/list.htm")
	public String list(HttpServletRequest request,Model model,String name,
						String sub,String subStatus,
						String dateStart,String dateEnd,
						String monitorType,
						Integer pageNumber,Integer pageSize){
		Long userId = getUserId(request);
		
		MonitorStatus monitorStatus = null;
		if (!StringUtils.isEmpty(subStatus)) {
			monitorStatus = MonitorStatus.valueOf(subStatus);
		}
		
		MonitorType type = null;
		if (!StringUtils.isEmpty(monitorType)) {
			type = MonitorType.valueOf(monitorType);
		}
		
		Date start = StringUtils.isEmpty(dateStart) ? null : DateUtil.parseByLong(dateStart + " 00:00:00");
		Date end = StringUtils.isEmpty(dateEnd) ? null : DateUtil.parseByLong(dateEnd + " 23:59:59");
		
		Pager pager = new Pager(pageNumber, pageSize);
		List<MonitorLogInfo> list = monitorLogService.listByUserId(userId, name, sub, 
									monitorStatus, start, end,type, pager);
		int count = monitorLogService.countByUserId(userId, name, sub, 
									monitorStatus, start, end,type);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "monitor/monitorLogList";
	}
	
	/**
	 * 
			*@name 监控日志详情
			*@Description  
			*@CreateDate 2017年5月11日下午4:12:08
	 */
	@RequestMapping("/info.htm")
	public String getInfo(HttpServletRequest request,Model model,Long logId){
		ValidateUtils.notNull(logId, ErrorCode.SYS_001,"监控日志id不能为空");
		
		MonitorLogInfo logInfo = monitorLogService.getByUserId(getUserId(request), logId);
		model.addAttribute("logInfo", logInfo);
		
		return "monitor/monitorLog";
	}
}
