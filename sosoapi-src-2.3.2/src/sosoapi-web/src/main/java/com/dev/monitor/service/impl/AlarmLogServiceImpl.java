package com.dev.monitor.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.dev.base.enums.MonitorStatus;
import com.dev.base.enums.MonitorType;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;
import com.dev.base.utils.FormatUtils;
import com.dev.monitor.dao.AlarmLogDao;
import com.dev.monitor.entity.AlarmLog;
import com.dev.monitor.service.AlarmLogService;
import com.dev.monitor.vo.AlarmLogInfo;

/**
 * 
		* <p>Title: 警报日志</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年5月10日下午10:52:52</p>
 */
@Service
public class AlarmLogServiceImpl extends BaseMybatisServiceImpl<AlarmLog,Long,AlarmLogDao>
										implements AlarmLogService{
	@Override
	public List<AlarmLogInfo> listByUserId(Long userId, String name, 
								String sub, MonitorStatus subStatus,
								Date dateStart, Date dateEnd, MonitorType monitorType, Pager pager) {
		name = getLikeExpr(name);
		sub = getLikeExpr(sub);
		List<Map> infoList = getMybatisDao().listByUserId(userId, name, sub, 
								subStatus, dateStart, dateEnd, monitorType, pager);
		List<AlarmLogInfo> result = new ArrayList<AlarmLogInfo>();
		AlarmLogInfo logInfo = null;
		for (Map info : infoList) {
			logInfo = new AlarmLogInfo();
			logInfo.setId((Long)info.get("id"));
			logInfo.setUserId((Long)info.get("userId"));
			logInfo.setMonitorId((Long)info.get("monitorId"));
			logInfo.setMonitorName((String)info.get("monitorName"));
			logInfo.setMonitorType(MonitorType.valueOf((String)info.get("monitorType")));
			logInfo.setSub((String)info.get("sub"));
			logInfo.setSubStatus(MonitorStatus.valueOf((String)info.get("subStatus")));
			logInfo.setSpendTime(FormatUtils.parseLong(info.get("spendTime")));
			logInfo.setCreateDate((Date)info.get("createDate"));
			result.add(logInfo);
		}
		
		return result;
	}

	@Override
	public int countByUserId(Long userId, String name, String sub, 
								MonitorStatus subStatus, Date dateStart,
								Date dateEnd, MonitorType monitorType) {
		name = getLikeExpr(name);
		sub = getLikeExpr(sub);
		
		return getMybatisDao().countByUserId(userId, name, sub, 
						subStatus, dateStart, dateEnd, monitorType);
	}

	@Override
	public AlarmLogInfo getByUserId(Long userId, Long logId) {
		Map<String, Object> info = getMybatisDao().getByUserId(userId, logId);
		AlarmLogInfo result = new AlarmLogInfo();
		if (CollectionUtils.isEmpty(info)) {
			return result;
		}
		
		result.setId((Long)info.get("id"));
		result.setUserId((Long)info.get("userId"));
		result.setMonitorId((Long)info.get("monitorId"));
		result.setMonitorName((String)info.get("monitorName"));
		result.setMonitorType(MonitorType.valueOf((String)info.get("monitorType")));
		result.setSub((String)info.get("sub"));
		result.setSubStatus(MonitorStatus.valueOf((String)info.get("subStatus")));
		result.setErrorBrief((String)info.get("errorBrief"));
		result.setErrorDetail((String)info.get("errorDetail"));
		result.setRespContent((String)info.get("respContent"));
		result.setSpendTime(FormatUtils.parseLong(info.get("spendTime")));
		result.setCreateDate((Date)info.get("createDate"));
		return result;
	}

	@Override
	public List<AlarmLogInfo> listAll(String monitorName, MonitorType monitorType, String sub, Date dateStart,
			Date dateEnd, Pager pager) {
		monitorName = getLikeExpr(monitorName);
		sub = getLikeExpr(sub);
		
		List<AlarmLogInfo> result = new ArrayList<AlarmLogInfo>();
		List<Map> infoList = getMybatisDao().listAll(monitorName, monitorType, sub, dateStart, dateEnd, pager);
		AlarmLogInfo logInfo = null;
		for (Map info : infoList) {
			logInfo = new AlarmLogInfo();
			logInfo.setId((Long)info.get("id"));
			logInfo.setMonitorId((Long)info.get("monitorId"));
			logInfo.setMonitorName((String)info.get("monitorName"));
			logInfo.setSub((String)info.get("sub"));
			logInfo.setSpendTime(FormatUtils.parseLong(info.get("spendTime")));
			logInfo.setCreateDate((Date)info.get("createDate"));
			result.add(logInfo);
		}
		
		return result;
	}

	@Override
	public int countAll(String monitorName, MonitorType monitorType, String sub, Date dateStart, Date dateEnd) {
		monitorName = getLikeExpr(monitorName);
		sub = getLikeExpr(sub);
		
		return getMybatisDao().countAll(monitorName, monitorType, sub, dateStart, dateEnd);
	}
}
