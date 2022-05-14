package com.dev.monitor.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dev.base.constant.CfgConstants;
import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.MonitorStatus;
import com.dev.base.enums.RespContentType;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;
import com.dev.base.utils.ValidateUtils;
import com.dev.monitor.dao.HttpMonitorDao;
import com.dev.monitor.entity.HttpMonitor;
import com.dev.monitor.service.HttpMonitorService;
import com.dev.monitor.vo.HttpMonitorInfo;

/**
 * 
		* <p>Title: http监控</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年5月10日下午10:57:30</p>
 */
@Service
public class HttpMonitorServiceImpl extends BaseMybatisServiceImpl<HttpMonitor,Long,HttpMonitorDao>
										implements HttpMonitorService{

	@Override
	public List<HttpMonitor> listByUserId(Long userId, String name, String sub, 
				MonitorStatus subStatus,EnableStatus status, Pager pager) {
		name = getLikeExpr(name);
		sub = getLikeExpr(sub);
		
		return getMybatisDao().listByUserId(userId, name, sub, subStatus, status, pager);
	}

	@Override
	public int countByUserId(Long userId, String name, String sub, 
								MonitorStatus subStatus, EnableStatus status) {
		name = getLikeExpr(name);
		sub = getLikeExpr(sub);
		
		return getMybatisDao().countByUserId(userId, name, sub, subStatus, status);
	}

	@Override
	public HttpMonitor getByUserId(Long userId, Long monitorId) {
		return getMybatisDao().getByUserId(userId, monitorId);
	}

	@Override
	public void delByUserId(Long userId, Long monitorId) {
		getMybatisDao().delByUserId(userId, monitorId);
	}

	@Override
	public void updateByUserId(HttpMonitor HttpMonitor) {
		getMybatisDao().updateByUserId(HttpMonitor);
	}

	@Override
	public List<HttpMonitor> listByRate(int rate, EnableStatus status, int size) {
		return getMybatisDao().listByRate(rate, status, size);
	}

	@Override
	public boolean isAddLimit(Long userId) {
		if (CfgConstants.LIMIT_MONITOR_COUNT == 0) {
			return false;
		}
		
		return getMybatisDao().countByUserId(userId, null, null, null, null) >= CfgConstants.LIMIT_MONITOR_COUNT;
	}

	@Override
	public List<HttpMonitorInfo> listAll(String name, String email, String sub, MonitorStatus subStatus,
			EnableStatus status, Date dateStart, Date dateEnd, Pager pager) {
		name = getLikeExpr(name);
		email = getLikeExpr(email);
		sub = getLikeExpr(sub);
		
		List<HttpMonitorInfo> result = new ArrayList<HttpMonitorInfo>();
		List<Map> infoList = getMybatisDao().listAll(name, email, sub, subStatus, status, dateStart, dateEnd, pager);
		HttpMonitorInfo monitorInfo = null;
		for (Map info : infoList) {
			monitorInfo = new HttpMonitorInfo();
			monitorInfo.setId((Long)info.get("id"));
			monitorInfo.setUserId((Long)info.get("userId"));
			monitorInfo.setUserEmail((String)info.get("email"));
			monitorInfo.setStatus(EnableStatus.valueOf((String)info.get("status")));
			monitorInfo.setName((String)info.get("name"));
			monitorInfo.setSub((String)info.get("sub"));
			monitorInfo.setSubStatus(MonitorStatus.valueOf((String)info.get("subStatus")));
			monitorInfo.setRate((Integer)info.get("rate"));
			monitorInfo.setCreateDate((Date)info.get("createDate"));
			result.add(monitorInfo);
		}
		
		return result;
	}

	@Override
	public int countAll(String name, String email, String sub, MonitorStatus subStatus, 
							EnableStatus status,Date dateStart, Date dateEnd) {
		name = getLikeExpr(name);
		email = getLikeExpr(email);
		sub = getLikeExpr(sub);
		
		return getMybatisDao().countAll(name, email, sub, subStatus, status, dateStart, dateEnd);
	}

	@Override
	public HttpMonitor add(HttpMonitor httpMonitor) {
		ValidateUtils.isTrue(!isAddLimit(httpMonitor.getUserId()), ErrorCode.MONITOR_001);
		
		httpMonitor.setSubStatus(MonitorStatus.none);
		if (httpMonitor.getRespContentType() == null) {
			httpMonitor.setRespContentType(RespContentType.APPLICATION_JSON);
		}
		
		getMybatisDao().add(httpMonitor);
		return httpMonitor;
	}

	@Override
	public void updateStatusForMgr(Long monitorId, EnableStatus status) {
		getMybatisDao().updateStatusForMgr(monitorId, status);
	}
}
