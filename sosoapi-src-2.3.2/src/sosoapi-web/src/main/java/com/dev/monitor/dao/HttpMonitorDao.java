package com.dev.monitor.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.MonitorStatus;
import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;
import com.dev.monitor.entity.HttpMonitor;

/**
 * 
		* <p>Title: http监控</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年5月10日下午10:57:30</p>
 */
public interface HttpMonitorDao extends BaseMybatisDao<HttpMonitor,Long> {
	/**
	 * 
			*@name 根据用户id查询相关的监控列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<HttpMonitor> listByUserId(@Param("userId")Long userId,@Param("name")String name,
									@Param("sub")String sub,@Param("subStatus")MonitorStatus subStatus,
									@Param("status")EnableStatus status,@Param("pager")Pager pager);

	/**
	 * 
			*@name 查询用户相关的监控记录总数
			*@Description  
			*@CreateDate 2015年8月6日下午5:40:59
	 */
	int countByUserId(@Param("userId")Long userId,@Param("name")String name,
						@Param("sub")String sub,@Param("subStatus")MonitorStatus subStatus,
						@Param("status")EnableStatus status);
	
	/**
	 * 
			*@name 查询所有监控列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<Map> listAll(@Param("name")String name,@Param("email")String email,
						@Param("sub")String sub,@Param("subStatus")MonitorStatus subStatus,
						@Param("status")EnableStatus status,@Param("dateStart")Date dateStart,@Param("dateEnd")Date dateEnd,@Param("pager")Pager pager);

	/**
	 * 
			*@name 查询所有监控总数
			*@Description  
			*@CreateDate 2015年8月6日下午5:40:59
	 */
	int countAll(@Param("name")String name,@Param("email")String email,
					@Param("sub")String sub,@Param("subStatus")MonitorStatus subStatus,
					@Param("status")EnableStatus status,@Param("dateStart")Date dateStart,@Param("dateEnd")Date dateEnd);
	
	/**
	 * 
			*@name 获取监控详情
			*@Description  
			*@CreateDate 2017年5月11日下午6:11:01
	 */
	HttpMonitor getByUserId(@Param("userId")Long userId,@Param("monitorId")Long monitorId);
	
	/**
	 * 
			*@name 删除监控
			*@Description  
			*@CreateDate 2017年5月11日下午6:01:58
	 */
	void delByUserId(@Param("userId")Long userId,@Param("monitorId")Long monitorId);
	
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
	List<HttpMonitor> listByRate(@Param("rate")int rate,@Param("status")EnableStatus status,@Param("size")int size);
	
	/**
	 * 
			*@name 更新状态
			*@Description  
			*@CreateDate 2017年11月30日下午5:42:10
	 */
	void updateStatusForMgr(@Param("monitorId")Long monitorId,@Param("status")EnableStatus status);
}
