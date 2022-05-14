package com.dev.monitor.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.MonitorStatus;
import com.dev.base.enums.MonitorType;
import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;
import com.dev.monitor.entity.MonitorLog;

/**
 * 
		* <p>Title: 监控日志</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年5月10日下午10:59:36</p>
 */
public interface MonitorLogDao extends BaseMybatisDao<MonitorLog,Long> {
	/**
	 * 
			*@name 根据用户id查询相关的监控日志列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<Map> listByUserId(@Param("userId")Long userId,@Param("name")String name,
							@Param("sub")String sub,@Param("subStatus")MonitorStatus subStatus,
							@Param("dateStart")Date dateStart,@Param("dateEnd")Date dateEnd,
							@Param("monitorType")MonitorType monitorType,@Param("pager")Pager pager);

	/**
	 * 
			*@name 查询用户相关的监控日志记录总数
			*@Description  
			*@CreateDate 2015年8月6日下午5:40:59
	 */
	int countByUserId(@Param("userId")Long userId,@Param("name")String name,
						@Param("sub")String sub,@Param("subStatus")MonitorStatus subStatus,
						@Param("dateStart")Date dateStart,@Param("dateEnd")Date dateEnd,
						@Param("monitorType")MonitorType monitorType);
	
	/**
	 * 
			*@name 获取监控日志详情
			*@Description  
			*@CreateDate 2017年5月11日下午6:11:01
	 */
	Map getByUserId(@Param("userId")Long userId,@Param("logId")Long logId);
	
	/**
	 * 
			*@name 查询所有监控列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<Map> listAll(@Param("monitorName")String monitorName,@Param("monitorType")MonitorType monitorType,
						@Param("sub")String sub,@Param("subStatus")MonitorStatus subStatus,
						@Param("dateStart")Date dateStart,@Param("dateEnd")Date dateEnd,@Param("pager")Pager pager);

	/**
	 * 
			*@name 查询所有监控记录总数
			*@Description  
			*@CreateDate 2015年8月6日下午5:40:59
	 */
	int countAll(@Param("monitorName")String monitorName,@Param("monitorType")MonitorType monitorType,
			@Param("sub")String sub,@Param("subStatus")MonitorStatus subStatus,
			@Param("dateStart")Date dateStart,@Param("dateEnd")Date dateEnd);
}
