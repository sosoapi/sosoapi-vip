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
import com.dev.base.enums.MonitorStatus;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.Pager;
import com.dev.base.util.WebPaginate;
import com.dev.base.util.WebUtil;
import com.dev.base.utils.ValidateUtils;
import com.dev.base.vo.SelectInfo;
import com.dev.doc.entity.InterParam;
import com.dev.doc.service.InterParamService;
import com.dev.monitor.entity.HttpMonitor;
import com.dev.monitor.service.AlarmGroupService;
import com.dev.monitor.service.HttpMonitorService;

/**
 * 
		* <p>Title: http监控</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年5月10日下午10:57:30</p>
 */
@Controller
@RequestMapping("/auth/monitor/http")
public class HttpMonitorController extends BaseController{
	@Autowired
	private HttpMonitorService httpMonitorService;
	
	@Autowired
	private AlarmGroupService alarmGroupService;
	
	@Autowired
	private InterParamService interParamService;
	
	/**
	 * 
			*@name 监控列表
			*@Description  
			*@CreateDate 2017年5月11日下午4:12:08
	 */
	@RequestMapping("/list.htm")
	public String list(HttpServletRequest request,Model model,String name,
						String sub,String subStatus,String status,
						Integer pageNumber,Integer pageSize){
		Long userId = getUserId(request);
		
		MonitorStatus monitorStatus = null;
		if (!StringUtils.isEmpty(subStatus)) {
			monitorStatus = MonitorStatus.valueOf(subStatus);
		}
		
		EnableStatus enableStatus = null;
		if (!StringUtils.isEmpty(status)) {
			enableStatus = EnableStatus.valueOf(status);
		}
		
		Pager pager = new Pager(pageNumber, pageSize);
		List<HttpMonitor> list = httpMonitorService.listByUserId(userId, name, 
										sub, monitorStatus, enableStatus, pager);
		int count = httpMonitorService.countByUserId(userId, name, 
										sub, monitorStatus, enableStatus);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "monitor/httpMonitorList";
	}
	
	/**
	 * 
			*@name 编辑监控
			*@Description  
			*@CreateDate 2017年5月11日下午5:33:11
	 */
	@RequestMapping("/update.htm")
	public String update(HttpServletRequest request,HttpMonitor httpMonitor,Long monitorId){
		ValidateUtils.notNull(monitorId, ErrorCode.SYS_001,"监控id不能为空");
		validMonitor(httpMonitor);
		
		dealReqParam(httpMonitor);
		httpMonitor.setId(monitorId);
		httpMonitor.setUserId(getUserId(request));
		httpMonitorService.updateByUserId(httpMonitor);
		
		return WebUtil.getRedirectUrl("/auth/monitor/http/list.htm");
	}
	
	//验证必输项
	private void validMonitor(HttpMonitor httpMonitor){
		ValidateUtils.notNull(httpMonitor.getName(), ErrorCode.SYS_001,"名称不能为空");
		ValidateUtils.notNull(httpMonitor.getSub(), ErrorCode.SYS_001,"监控url不能为空");
		ValidateUtils.isTrue(httpMonitor.getRate() > 0, ErrorCode.SYS_001,"监控频率必须大于0");
		ValidateUtils.isTrue(httpMonitor.getMaxSpendTime() >= 0, ErrorCode.SYS_001,"最大响应时间必须大于或等于0");
		ValidateUtils.isTrue(httpMonitor.getMaxErrorCount() >= 0, ErrorCode.SYS_001,"最大错误次数必须大于或等于0");
		ValidateUtils.isTrue(httpMonitor.getMaxAlarmCount() >= 0, ErrorCode.SYS_001,"最大预警次数必须大于或等于0");
		if (httpMonitor.isValidResp()) {
			ValidateUtils.notNull(httpMonitor.getRespContent(), ErrorCode.SYS_001,"验证内容不能为空");
		}
	}
	
	/**
	 * 
			*@name 跳转新增页面
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping("/forwardAdd.htm")
	public String forwardAdd(HttpServletRequest request,Model model){
		model.addAttribute("action", "auth/monitor/http/add.htm");
		model.addAttribute("operType", "add");
		
		List<SelectInfo> alarmGoupList = alarmGroupService.listGroup(getUserId(request));
		model.addAttribute("alarmGroupList", alarmGoupList);
		
		return "monitor/httpMonitor";
	}
	
	/**
	 * 
			*@name 跳转修改页面
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping("/forwardUpdate.htm")
	public String forwardUpdate(HttpServletRequest request,Model model,Long monitorId){
		ValidateUtils.notNull(monitorId, ErrorCode.SYS_001,"监控id不能为空");
		model.addAttribute("action", "auth/monitor/http/update.htm");
		model.addAttribute("operType", "update");
		
		Long userId = getUserId(request);
		HttpMonitor httpMonitor = httpMonitorService.getByUserId(userId, monitorId);
		model.addAttribute("monitorInfo", httpMonitor);
		
		List<SelectInfo> alarmGoupList = alarmGroupService.listGroup(getUserId(request));
		model.addAttribute("alarmGroupList", alarmGoupList);
		
		return "monitor/httpMonitor";
	}
	
	/**
	 * 
			*@name 新增监控
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/add.htm",method = RequestMethod.POST)
	public String add(HttpServletRequest request,HttpMonitor httpMonitor){
		validMonitor(httpMonitor);
		
		dealReqParam(httpMonitor);
		httpMonitor.setUserId(getUserId(request));
		httpMonitorService.add(httpMonitor);
		
		return WebUtil.getRedirectUrl("/auth/monitor/http/list.htm");
	}
	
	//处理请求参数
	private void dealReqParam(HttpMonitor httpMonitor){
		if(StringUtils.isEmpty(httpMonitor.getReqParam())){
			return ;
		}
		
		List<InterParam> paramList = interParamService.buildParamList(null, null, httpMonitor.getReqParam());
		httpMonitor.setReqParam(JsonUtils.toJson(paramList));
	}
	
	/**
	 * 
			*@name 删除监控
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/del.htm")
	public @ResponseBody Map del(HttpServletRequest request,Long monitorId){
		ValidateUtils.notNull(monitorId, ErrorCode.SYS_001,"监控id不能为空");
		Long userId = getUserId(request);
		httpMonitorService.delByUserId(userId, monitorId);
		return JsonUtils.createSuccess();
	}
}
