package com.dev.monitor.entity;

import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.MonitorStatus;
import com.dev.base.enums.ReqMethod;
import com.dev.base.enums.RespContentType;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: http监控</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年5月10日下午10:57:30</p>
 */
public class HttpMonitor extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 用户id */
	private Long userId;		
	
	/** 描述 */
	private String description;		
	
	/** 状态 */
	private EnableStatus status;		
	
	/** 名称 */
	private String name;		
	
	/** 监控url */
	private String sub;		
	
	/** 监控对象状态 */
	private MonitorStatus subStatus;	
	
	/** 监控频率，单位分 */
	private int rate;		
	
	/** 请求方法 */
	private ReqMethod reqMethod;		
	
	/** 验证内容 */
	private String respContent;		
	
	/** 是否验证响应 */
	private boolean validResp;
	
	/** 请求参数 */
	private String reqParam;
	
	/** 最大响应时间，毫秒 */
	/** 0:表示不关注*/
	private long maxSpendTime;		
	
	/** 最大错误次数 */
	private int maxErrorCount;		
	
	/** 最大预警次数 */
	private int maxAlarmCount;		
	
	/** 警报组 */
	private Long alarmGroupId;		
	
	/** 已发出预警次数 */
	private int alarmCount;		
	
	/** 已错误次数 */
	private int errorCount;
	
	/** 响应内容类型*/
	private RespContentType respContentType;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public EnableStatus getStatus() {
		return status;
	}

	public void setStatus(EnableStatus status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public MonitorStatus getSubStatus() {
		return subStatus;
	}

	public void setSubStatus(MonitorStatus subStatus) {
		this.subStatus = subStatus;
	}

	public long getMaxSpendTime() {
		return maxSpendTime;
	}

	public void setMaxSpendTime(long maxSpendTime) {
		this.maxSpendTime = maxSpendTime;
	}

	public int getMaxErrorCount() {
		return maxErrorCount;
	}

	public void setMaxErrorCount(int maxErrorCount) {
		this.maxErrorCount = maxErrorCount;
	}

	public int getMaxAlarmCount() {
		return maxAlarmCount;
	}

	public void setMaxAlarmCount(int maxAlarmCount) {
		this.maxAlarmCount = maxAlarmCount;
	}

	public Long getAlarmGroupId() {
		return alarmGroupId;
	}

	public void setAlarmGroupId(Long alarmGroupId) {
		this.alarmGroupId = alarmGroupId;
	}

	public int getAlarmCount() {
		return alarmCount;
	}

	public void setAlarmCount(int alarmCount) {
		this.alarmCount = alarmCount;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

	public String getReqParam() {
		return reqParam;
	}

	public void setReqParam(String reqParam) {
		this.reqParam = reqParam;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public ReqMethod getReqMethod() {
		return reqMethod;
	}

	public void setReqMethod(ReqMethod reqMethod) {
		this.reqMethod = reqMethod;
	}

	public String getRespContent() {
		return respContent;
	}

	public void setRespContent(String respContent) {
		this.respContent = respContent;
	}

	public boolean isValidResp() {
		return validResp;
	}

	public void setValidResp(boolean validResp) {
		this.validResp = validResp;
	}

	public RespContentType getRespContentType() {
		return respContentType;
	}

	public void setRespContentType(RespContentType respContentType) {
		this.respContentType = respContentType;
	}		
}
