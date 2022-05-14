package com.dev.monitor.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.dev.base.concurrent.TaskListProcess;
import com.dev.base.concurrent.TaskUtils;
import com.dev.base.constant.AppConstants;
import com.dev.base.constant.CfgConstants;
import com.dev.base.constant.MailConstants;
import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.MonitorErrorType;
import com.dev.base.enums.MonitorStatus;
import com.dev.base.enums.MonitorType;
import com.dev.base.enums.ParamPosition;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.MailUtil;
import com.dev.base.utils.DateUtil;
import com.dev.base.utils.HttpClientUtils;
import com.dev.base.utils.MapUtils;
import com.dev.base.utils.TemplateUtils;
import com.dev.doc.entity.InterParam;
import com.dev.monitor.entity.AlarmLog;
import com.dev.monitor.entity.AlarmReceiver;
import com.dev.monitor.entity.HttpMonitor;
import com.dev.monitor.entity.MonitorLog;
import com.dev.monitor.service.AlarmGroupService;
import com.dev.monitor.service.AlarmLogService;
import com.dev.monitor.service.AlarmReceiverService;
import com.dev.monitor.service.HttpMonitorService;
import com.dev.monitor.service.MonitorLogService;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * 
		* <p>Title: http监控处理</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年5月15日下午3:35:52</p>
 */
@Component
public class HttpMonitorHandler {
	private static Logger logger = LogManager.getLogger(HttpMonitorHandler.class);
	
	@Autowired
	private HttpMonitorService httpMonitorService;
	
	@Autowired
	private MonitorLogService monitorLogService;
	
	@Autowired
	private AlarmLogService alarmLogService;
	
	@Autowired
	private AlarmGroupService groupService;
	
	@Autowired
	private AlarmReceiverService receiverService;
	
	private int BATCH_COUNT = 50;
	
	private int THREAD_COUNT = 4;
	
	private int MAX_COUNT = 100;
	
	/**
	 * 
			*@name 执行同一频率的监控
			*@Description  
			*@CreateDate 2017年5月17日上午10:38:48
	 */
	public void doMonitorByRate(int rate){
		if (rate <= 0) {
			return ;
		}
		
		//一次性全部查询出来
		List<HttpMonitor> monitorList = httpMonitorService.listByRate(rate, EnableStatus.on, Integer.MAX_VALUE);
		if (CollectionUtils.isEmpty(monitorList)) {
			return ;
		}
		
		//超过指定值才需要线程池来处理
		if (monitorList.size() > MAX_COUNT) {
			TaskUtils.execList(monitorList, THREAD_COUNT, BATCH_COUNT, new TaskListProcess<HttpMonitor, Object>() {
				@Override
				public List<Object> exec(int batchIndex, List<HttpMonitor> taskList) {
					doMonitor(taskList);
					return null;
				}
			});
		}
		else {
			doMonitor(monitorList);
		}
	}
	
	//处理监控列表
	private void doMonitor(List<HttpMonitor> monitorList){
		if (CollectionUtils.isEmpty(monitorList)) {
			return ;
		}
		
		for (HttpMonitor httpMonitor : monitorList) {
			doMonitor(httpMonitor);
		}
	}
	
	/**
	 * 
			*@name 处理监控
			*@Description  
			*@CreateDate 2017年5月18日上午10:04:32
	 */
	public void doMonitor(HttpMonitor httpMonitor){
		List<InterParam> paramList = new ArrayList<InterParam>();
		//将请求参数分类
		Map<ParamPosition, Object> paramMap = MapUtils.newMap();
		if (!StringUtils.isEmpty(httpMonitor.getReqParam())) {
			paramList = JsonUtils.toObject(httpMonitor.getReqParam(), new TypeReference<List<InterParam>>(){});
			paramMap = buildParamMap(paramList);
		}
		
		//组装请求url，包含path和query的请求参数
		String reqUrl = buildReqUrl(httpMonitor.getSub(), paramMap);
		logger.debug("dealMonitor reqUrl:" + reqUrl);
		if (StringUtils.isEmpty(reqUrl)) {
			return ;
		}
		
		//请求开始时间
		long startTime = System.currentTimeMillis();
		String respContent = "";
		boolean isSuccess = true;
		String errorMsg = "";
		try{
			switch (httpMonitor.getReqMethod()) {
				case GET:
					respContent = doGet(reqUrl, paramMap);
					break;
					
				case POST:
					respContent = doPost(reqUrl, paramMap);
					break;
					
				default:
					break;
			}
		}
		catch(Exception exception){
			isSuccess = false;
			errorMsg = exception.getMessage();
			exception.printStackTrace();
		}
		//请求消耗时间
		long spendTime = System.currentTimeMillis() - startTime;
		logger.debug("dealMonitor \r\n reqUrl:" + reqUrl 
					+ "\r\n spend time:" + spendTime 
					+ "\r\n respContent:" + respContent);
		
		//处理响应
		dealResp(httpMonitor, isSuccess, errorMsg, respContent,spendTime);
	}
	
	//处理响应
	private void dealResp(HttpMonitor httpMonitor,boolean isSuccess,
							String errorMsg,String respContent,long spendTime){
		//获取错误类型,如果为null,则表明执行成功
		MonitorErrorType errorType = getErrorType(httpMonitor, isSuccess, errorMsg, respContent, spendTime);
		//添加监控运行记录
		Long monitorLogId = addMonitorLog(httpMonitor, errorMsg, respContent, spendTime,errorType);
		//是否需要发送通知
		boolean isNotice = false;
		//是否异常预警
		boolean isAlarm = false;
		//处理监控异常
		if (errorType != null) {
			//增加连续错误次数
			httpMonitor.setErrorCount(httpMonitor.getErrorCount() + 1);
			//连续错误次数达到发出预警条件并且还没达到最大警报次数或最大预警次数为0，则发出预警警报
			if (httpMonitor.getErrorCount() >= httpMonitor.getMaxErrorCount()
					&& (httpMonitor.getMaxAlarmCount() == 0 || httpMonitor.getAlarmCount() + 1 <= httpMonitor.getMaxAlarmCount())) {
				//处理预警
				isAlarm = true;
				//发送通知
				isNotice = true;
				httpMonitor.setAlarmCount(httpMonitor.getAlarmCount() + 1);
			}
			httpMonitor.setSubStatus(MonitorStatus.error);
			httpMonitorService.update(httpMonitor);
		}
		//处理监控正常
		else {
			if (httpMonitor.getSubStatus() == MonitorStatus.error) {//异常转正常
				//发送通知
				isNotice = true;
				//只要一次返回成功则重置错误次数和预警次数
				httpMonitor.setAlarmCount(0);
				httpMonitor.setErrorCount(0);
				httpMonitor.setSubStatus(MonitorStatus.normal);
				httpMonitorService.update(httpMonitor);
			}
			else{//第一次监控，更新状态
				httpMonitor.setSubStatus(MonitorStatus.normal);
				httpMonitorService.update(httpMonitor);
			}
		}
		
		//添加预警记录
		if (isAlarm) {
			addAlarmLog(httpMonitor, monitorLogId, errorMsg, respContent, spendTime,errorType);
		}
		
		//处理通知
		if (isNotice) {
			dealNotice(httpMonitor, errorMsg, respContent, spendTime, isAlarm,errorType);
		}
	}
	
	//处理通知
	private void dealNotice(HttpMonitor httpMonitor,String errorMsg,
								String respContent,long spendTime,boolean isAlarm,MonitorErrorType errorType){
		Long userId = httpMonitor.getUserId();
		Long groupId = httpMonitor.getAlarmGroupId();
		//未配置预警分组则直接返回
		if (groupId == null) {
			return ;
		}
				
		//创建预警的邮箱和手机列表
		List<AlarmReceiver> receiverList = receiverService.listByGroupId(userId, groupId);
		List<String> emailList = new ArrayList<String>();
		List<String> phoneList = new ArrayList<String>();
		for (AlarmReceiver receiver : receiverList) {
			if (!StringUtils.isEmpty(receiver.getEmail())
					&& receiver.isEmailValid()) {//验证通过才发送
				emailList.add(receiver.getEmail());
			}
			
			if (!StringUtils.isEmpty(receiver.getPhone())
					&& receiver.isPhoneValid()) {//验证通过才发送
				phoneList.add(receiver.getPhone());
			}
		}
		
		//邮件通知
		noticeByEmail(httpMonitor, errorMsg, respContent, spendTime, isAlarm,errorType, emailList);
		
		//短信通知
		noticeBySms(httpMonitor, errorMsg, respContent, spendTime, isAlarm,errorType, phoneList);
	}
	
	//邮件通知
	private void noticeByEmail(HttpMonitor httpMonitor,String errorMsg,String respContent,
								long spendTime,boolean isAlarm,MonitorErrorType errorType,List<String> emailList){
		if (CollectionUtils.isEmpty(emailList)) {
			return ;
		}
		
		Map<String, Object> model = MapUtils.newMap();
		model.put("webName", CfgConstants.WEB_NAME);
		model.put("webEmail", CfgConstants.WEB_SERVICE_EMAIL);
		model.put("sendDate", DateUtil.formatNowByLong());
		model.put("monitorName", httpMonitor.getName());
		model.put("sub", httpMonitor.getSub());
		if (errorType != null) {
			model.put("errorBrief", errorType.getDisplayName());
		}
		model.put("errorDetail", errorMsg);
		model.put("spendTime", spendTime);
		model.put("createDate", DateUtil.formatNowByLong());
		String subject = "";
		String content = "";
		if (isAlarm) {
			subject = MailConstants.SUB_EMAIL_MONITOR_ALARM + " - " + httpMonitor.getName();
			content = TemplateUtils.process(MailConstants.TMPL_EMAIL_MONITOR_ALARM, model);
		}
		else{
			subject = MailConstants.SUB_EMAIL_MONITOR_RECOVER + " - " + httpMonitor.getName();
			content = TemplateUtils.process(MailConstants.TMPL_EMAIL_MONITOR_RECOVER, model);
		}
		
		MailUtil.sendMonitor(emailList, subject, content);
	}
	
	//短信通知
	private void noticeBySms(HttpMonitor httpMonitor,String errorMsg,String respContent,
								long spendTime,boolean isAlarm,MonitorErrorType errorType,List<String> phoneList){
		//TO-DO
		
	}
	
	//获取失败类型
	private MonitorErrorType getErrorType(HttpMonitor httpMonitor,boolean isSuccess,
			String errorMsg,String respContent,long spendTime){
		//执行异常
		if (!isSuccess) {
			return MonitorErrorType.execFail;
		}
		
		//调用超时
		if (httpMonitor.getMaxSpendTime() > 0 && spendTime >= httpMonitor.getMaxSpendTime()) {
			return MonitorErrorType.timeout;
		}
		
		//响应结果不匹配
		if (httpMonitor.isValidResp() 
				&& !StringUtils.isEmpty(httpMonitor.getRespContent())) {
			if (!matchResp(httpMonitor, respContent)) {
				return MonitorErrorType.respValidFail;
			}
		}
		
		return null;
	}
	
	//验证响应
	private boolean matchResp(HttpMonitor httpMonitor,String respContent){
		boolean result = false;
		if (StringUtils.isEmpty(httpMonitor.getRespContent())) {
			return result;
		}
		
		switch (httpMonitor.getRespContentType()) {
			case APPLICATION_JSON:
				result = matchRespByJson(httpMonitor.getRespContent(), respContent);
				break;
				
			case APPLICATION_XML:
				break;
				
			default:
				break;
		}
		
		return result;
	}
	
	//验证返回响应是json格式
	private boolean matchRespByJson(String srcJson,String targetJson){
		try{
			return JsonUtils.isEqual(srcJson, targetJson);
		}
		catch(Exception exception){
			logger.error("matchRespByJson error:"
							+ "\r\n srcJson:" + srcJson
							+ "\r\n targetJson:" + targetJson);
		}
		
		return false;
	}
	
	//添加监控运行记录
	private Long addMonitorLog(HttpMonitor httpMonitor,
			String errorMsg,String respContent,long spendTime,MonitorErrorType errorType){
		MonitorLog monitorLog = new MonitorLog();
		monitorLog.setUserId(httpMonitor.getUserId());
		monitorLog.setMonitorId(httpMonitor.getId());
		monitorLog.setMonitorType(MonitorType.http);
		monitorLog.setSub(httpMonitor.getSub());
		if (errorType != null) {
			monitorLog.setSubStatus(MonitorStatus.error);
			monitorLog.setErrorBrief(errorType.getDisplayName());
		}
		else{
			monitorLog.setSubStatus(MonitorStatus.normal);
		}
		monitorLog.setErrorDetail(errorMsg);
		monitorLog.setRespContent(respContent);
		monitorLog.setSpendTime(spendTime);
		monitorLogService.add(monitorLog);
		return monitorLog.getId();
	}
	
	//添加预警记录
	private Long addAlarmLog(HttpMonitor httpMonitor,Long monitorLogId,String errorMsg,
								String respContent,long spendTime,MonitorErrorType errorType){
		AlarmLog alarmLog = new AlarmLog();
		alarmLog.setUserId(httpMonitor.getUserId());
		alarmLog.setMonitorId(httpMonitor.getId());
		alarmLog.setMonitorType(MonitorType.http);
		alarmLog.setSub(httpMonitor.getSub());
		alarmLog.setSubStatus(MonitorStatus.error);
		if (errorType != null) {
			alarmLog.setErrorBrief(errorType.getDisplayName());
		}
		alarmLog.setErrorDetail(errorMsg);
		alarmLog.setRespContent(respContent);
		alarmLog.setSpendTime(spendTime);
		alarmLog.setAlarmGroupId(httpMonitor.getAlarmGroupId());
		alarmLog.setMonitorLogId(monitorLogId);
		alarmLogService.add(alarmLog);
		return alarmLog.getId();
	}
	
	//组装请求参数
	private Map<ParamPosition, Object> buildParamMap(List<InterParam> paramList){
		Map<ParamPosition, Object> result = MapUtils.newMap();
		Map<String, Object> formDataParam = MapUtils.newMap();
		Map<String, Object> pathParam = MapUtils.newMap();
		Map<String, Object> queryParam = MapUtils.newMap();
		Map<String, String> headerParam = MapUtils.newMap();
		
		for (InterParam interParam : paramList) {
			switch (interParam.getPosition()) {
				case formData:
					formDataParam.put(interParam.getCode(), interParam.getDefValue());
					break;
				
				case path:
					pathParam.put(interParam.getCode(), interParam.getDefValue());
					break;
					
				case query:
					queryParam.put(interParam.getCode(), interParam.getDefValue());
					break;
					
				case header:
					headerParam.put(interParam.getCode(), interParam.getDefValue());
					break;
					
				default:
					break;
			}
		}
		
		result.put(ParamPosition.formData, formDataParam);
		result.put(ParamPosition.path, pathParam);
		result.put(ParamPosition.query, queryParam);
		result.put(ParamPosition.header, headerParam);
		return result;
	}
	
	//处理参数位置是path和query
	private String buildReqUrl(String reqUrl,Map<ParamPosition, Object> paramMap){
		//处理http和https请求
		if (StringUtils.isEmpty(reqUrl) 
				|| (!reqUrl.toLowerCase().startsWith("http") && reqUrl.toLowerCase().startsWith("https"))) {
			return null;
		}
		
		//无请求参数
		if (CollectionUtils.isEmpty(paramMap)) {
			return reqUrl;
		}
		
		Map<String, Object> pathParam = (Map<String, Object>)paramMap.get(ParamPosition.path);
		Map<String, Object> queryParam = (Map<String, Object>)paramMap.get(ParamPosition.query);
		
		StringBuilder reqUrlBuilder = new StringBuilder();
		//处理path参数
		if (!CollectionUtils.isEmpty(pathParam)) {
			reqUrlBuilder.append(TemplateUtils.process(reqUrl, pathParam));
		}
		else{
			reqUrlBuilder.append(reqUrl);
		}
		
		//处理query参数
		if (!CollectionUtils.isEmpty(queryParam)) {
			if (reqUrlBuilder.indexOf("?") < 0) {
				reqUrlBuilder.append("?");
			}
			
			Set<String> keySet = queryParam.keySet();
			for (String key : keySet) {
				reqUrlBuilder.append(key)
							 .append("=")
							 .append(queryParam.get(key))
							 .append("&");
			}
			
			reqUrlBuilder.deleteCharAt(reqUrlBuilder.length() - 1);
		}
		
		return reqUrlBuilder.toString();
	}
	
	//处理get
	private String doGet(String reqUrl,Map<ParamPosition, Object> paramMap){
		Map<String, String> headerMap = (Map<String, String>)paramMap.get(ParamPosition.header);
		return HttpClientUtils.doGet(reqUrl, null, headerMap, AppConstants.DEF_CHARSET);
	}
	
	//处理post
	private String doPost(String reqUrl,Map<ParamPosition, Object> paramMap){
		Map<String, String> headerMap = (Map<String, String>)paramMap.get(ParamPosition.header);
		Map<String, Object> formDataMap = (Map<String, Object>)paramMap.get(ParamPosition.formData);
		return HttpClientUtils.doPost(reqUrl, null, headerMap, AppConstants.DEF_CHARSET);
	}
}
