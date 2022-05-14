package com.dev.monitor.service;

import java.util.Date;
import java.util.List;

import com.dev.base.enums.MonitorStatus;
import com.dev.base.enums.MonitorType;
import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;
import com.dev.monitor.entity.MonitorLog;
import com.dev.monitor.vo.MonitorLogInfo;

/**
 * 
		* <p>Title: 监控日志</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年5月10日下午10:59:36</p>
 */
public interface MonitorLogService extends BaseMybatisService<MonitorLog, Long>{
	/**
	 * 
			*@name 根据用户id查询相关的监控日志列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<MonitorLogInfo> listByUserId(Long userId,String name,
							String sub,MonitorStatus subStatus,
							Date dateStart,Date dateEnd,
							MonitorType monitorType,Pager pager);

	/**
	 * 
			*@name 查询用户相关的监控日志记录总数
			*@Description  
			*@CreateDate 2015年8月6日下午5:40:59
	 */
	int countByUserId(Long userId,String name,
						String sub,MonitorStatus subStatus,
						Date dateStart,Date dateEnd,
						MonitorType monitorType);
	
	/**
	 * 
			*@name 获取监控日志详情
			*@Description  
			*@CreateDate 2017年5月11日下午6:11:01
	 */
	MonitorLogInfo getByUserId(Long userId,Long logId);
	
	/**
	 * 
			*@name 查询所有监控列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<MonitorLogInfo> listAll(String monitorName,MonitorType monitorType,
						String sub,MonitorStatus subStatus,
						Date dateStart,Date dateEnd,Pager pager);

	/**
	 * 
			*@name 查询所有监控记录总数
			*@Description  
			*@CreateDate 2015年8月6日下午5:40:59
	 */
	int countAll(String monitorName,MonitorType monitorType,
					String sub,MonitorStatus subStatus,
					Date dateStart,Date dateEnd);
}
