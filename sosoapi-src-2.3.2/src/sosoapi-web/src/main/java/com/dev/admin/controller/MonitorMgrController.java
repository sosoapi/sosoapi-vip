package com.dev.admin.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.base.controller.BaseController;
import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.MonitorStatus;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.Pager;
import com.dev.base.util.WebPaginate;
import com.dev.base.utils.DateUtil;
import com.dev.base.utils.ValidateUtils;
import com.dev.monitor.service.AlarmLogService;
import com.dev.monitor.service.HttpMonitorService;
import com.dev.monitor.service.MonitorLogService;
import com.dev.monitor.vo.AlarmLogInfo;
import com.dev.monitor.vo.HttpMonitorInfo;
import com.dev.monitor.vo.MonitorLogInfo;

/**
 * 
		* <p>Title: 监控</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月6日下午4:19:22</p>
 */
@Controller
@RequestMapping("/admin/monitor")
public class MonitorMgrController extends BaseController{
	@Autowired
	private HttpMonitorService httpMonitorService;
	
	@Autowired
	private MonitorLogService monitorLogService;
	
	@Autowired
	private AlarmLogService alarmLogService;
	
	/**
	 * 
			*@name http监控列表
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/http/list.htm")
	public String listHttpMonitor(HttpServletRequest request,Model model,String name,
									String email,String sub,String subStatus,
									String status,String dateStart,String dateEnd,
									Integer pageNumber,Integer pageSize){
		Date start = StringUtils.isEmpty(dateStart) ? null : DateUtil.parseByLong(dateStart + " 00:00:00");
		Date end = StringUtils.isEmpty(dateEnd) ? null : DateUtil.parseByLong(dateEnd + " 23:59:59");
		
		MonitorStatus monitorStatus = null;
		if (!StringUtils.isEmpty(subStatus)) {
			monitorStatus = MonitorStatus.valueOf(subStatus);
		}
		
		EnableStatus enableStatus = null;
		if (!StringUtils.isEmpty(status)) {
			enableStatus = EnableStatus.valueOf(status);
		}
		
		Pager pager = new Pager(pageNumber, pageSize);
		List<HttpMonitorInfo> list = httpMonitorService.listAll(name, email, 
						sub, monitorStatus, enableStatus, start, end, pager);
		int count = httpMonitorService.countAll(name, email, sub, monitorStatus, 
												enableStatus, start, end);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "admin/monitor/httpMonitorList";
	}
	
	/**
	 * 
			*@name 更新状态
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/http/json/updateStatus.htm")
	public @ResponseBody Map updateStatus(HttpServletRequest request,Long monitorId,EnableStatus status){
		ValidateUtils.notNull(monitorId, ErrorCode.SYS_001,"监控id不能为空");
		ValidateUtils.notNull(status, ErrorCode.SYS_001,"启用状态不能为空");
		
		httpMonitorService.updateStatusForMgr(monitorId, status);
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 监控运行日志列表
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/log/list.htm")
	public String listMonitorLog(HttpServletRequest request,Model model,String monitorName,
									String sub,String subStatus,
									String dateStart,String dateEnd,
									Integer pageNumber,Integer pageSize){
		Date start = StringUtils.isEmpty(dateStart) ? null : DateUtil.parseByLong(dateStart + " 00:00:00");
		Date end = StringUtils.isEmpty(dateEnd) ? null : DateUtil.parseByLong(dateEnd + " 23:59:59");
		
		MonitorStatus monitorStatus = null;
		if (!StringUtils.isEmpty(subStatus)) {
			monitorStatus = MonitorStatus.valueOf(subStatus);
		}
		
		Pager pager = new Pager(pageNumber, pageSize);
		List<MonitorLogInfo> list = monitorLogService.listAll(monitorName, null, 
				sub, monitorStatus, start, end, pager);
		int count = monitorLogService.countAll(monitorName, null, 
										sub, monitorStatus, start, end);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "admin/monitor/monitorLogList";
	}
	
	/**
	 * 
			*@name 监控预警日志列表
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/alarm/log/list.htm")
	public String listAlarmLog(HttpServletRequest request,Model model,
								String monitorName,String sub,String dateStart,String dateEnd,
								Integer pageNumber,Integer pageSize){
		Date start = StringUtils.isEmpty(dateStart) ? null : DateUtil.parseByLong(dateStart + " 00:00:00");
		Date end = StringUtils.isEmpty(dateEnd) ? null : DateUtil.parseByLong(dateEnd + " 23:59:59");
		
		Pager pager = new Pager(pageNumber, pageSize);
		List<AlarmLogInfo> list = alarmLogService.listAll(monitorName, null, sub, start, end, pager);
		int count = alarmLogService.countAll(monitorName, null, sub, start, end);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "admin/monitor/alarmLogList";
	}
}
