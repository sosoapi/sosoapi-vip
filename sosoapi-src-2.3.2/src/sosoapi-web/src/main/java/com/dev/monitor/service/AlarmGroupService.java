package com.dev.monitor.service;

import java.util.List;

import com.dev.base.enums.EnableStatus;
import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;
import com.dev.base.vo.SelectInfo;
import com.dev.monitor.entity.AlarmGroup;

/**
 * 
		* <p>Title: 警报接收组</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年5月10日下午10:48:46</p>
 */
public interface AlarmGroupService extends BaseMybatisService<AlarmGroup, Long>{
	/**
	 * 
			*@name 根据用户id查询相关的接收组列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<AlarmGroup> listByUserId(Long userId,String name,EnableStatus status,Pager pager);

	/**
	 * 
			*@name 查询用户相关的接收组记录总数
			*@Description  
			*@CreateDate 2015年8月6日下午5:40:59
	 */
	int countByUserId(Long userId,String name,EnableStatus status);
	
	/**
	 * 
			*@name 获取接收组详情
			*@Description  
			*@CreateDate 2017年5月11日下午6:11:01
	 */
	AlarmGroup getByUserId(Long userId,Long groupId);
	
	/**
	 * 
			*@name 删除接收组
			*@Description  
			*@CreateDate 2017年5月11日下午6:01:58
	 */
	void delByUserId(Long userId,Long groupId);
	
	/**
	 * 
			*@name 更新接收组
			*@Description  
			*@CreateDate 2017年5月11日下午6:03:18
	 */
	void updateByUserId(AlarmGroup alarmGroup,List<Long> receiverIdList);
	
	/**
	 * 
			*@name 查询接收组列表
			*@Description  
			*@CreateDate 2017年5月13日上午9:02:35
	 */
	List<SelectInfo> listGroup(Long userId);
}
