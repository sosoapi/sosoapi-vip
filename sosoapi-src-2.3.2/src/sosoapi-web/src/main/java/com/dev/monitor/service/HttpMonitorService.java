package com.dev.monitor.service;

import java.util.Date;
import java.util.List;

import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.MonitorStatus;
import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;
import com.dev.monitor.entity.HttpMonitor;
import com.dev.monitor.vo.HttpMonitorInfo;

/**
 * 
		* <p>Title: http监控</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年5月10日下午10:57:30</p>
 */
public interface HttpMonitorService extends BaseMybatisService<HttpMonitor, Long>{
	/**
	 * 
			*@name 根据用户id查询相关的监控列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<HttpMonitor> listByUserId(Long userId,String name,String sub,
									MonitorStatus subStatus,EnableStatus status,Pager pager);

	/**
	 * 
			*@name 查询用户相关的监控记录总数
			*@Description  
			*@CreateDate 2015年8月6日下午5:40:59
	 */
	int countByUserId(Long userId,String name,String sub,
						MonitorStatus subStatus,EnableStatus status);
	
	/**
	 * 
			*@name 获取监控详情
			*@Description  
			*@CreateDate 2017年5月11日下午6:11:01
	 */
	HttpMonitor getByUserId(Long userId,Long monitorId);
	
	/**
	 * 
			*@name 删除监控
			*@Description  
			*@CreateDate 2017年5月11日下午6:01:58
	 */
	void delByUserId(Long userId,Long monitorId);
	
	/**
	 * 
			*@name 更新监控
			*@Description  
			*@CreateDate 2017年5月11日下午6:03:18
	 */
	void updateByUserId(HttpMonitor HttpMonitor);
	
	/**
	 * 
			*@name 查询指定监控频率的监控列表
			*@Description  
			*@CreateDate 2017年5月15日下午5:30:40
	 */
	List<HttpMonitor> listByRate(int rate,EnableStatus status,int size);
	
	/**
	 * 
			*@name 碰到用户是否已被限制新增监控
			*@Description  
			*@CreateDate 2017年5月15日下午5:50:46
	 */
	boolean isAddLimit(Long userId);
	
	/**
	 * 
			*@name 查询所有监控列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<HttpMonitorInfo> listAll(String name,String email,String sub,MonitorStatus subStatus,
						EnableStatus status,Date dateStart,Date dateEnd,Pager pager);

	/**
	 * 
			*@name 查询用户相关的监控记录总数
			*@Description  
			*@CreateDate 2015年8月6日下午5:40:59
	 */
	int countAll(String name,String email,String sub,MonitorStatus subStatus,
			EnableStatus status,Date dateStart,Date dateEnd);
	
	/**
	 * 
			*@name 更新状态
			*@Description  
			*@CreateDate 2017年11月30日下午5:42:10
	 */
	void updateStatusForMgr(Long monitorId,EnableStatus status);
}
