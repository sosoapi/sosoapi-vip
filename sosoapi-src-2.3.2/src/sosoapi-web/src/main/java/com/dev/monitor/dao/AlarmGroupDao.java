package com.dev.monitor.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dev.base.enums.EnableStatus;
import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;
import com.dev.monitor.entity.AlarmGroup;

/**
 * 
		* <p>Title: 警报接收组</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年5月10日下午10:48:46</p>
 */
public interface AlarmGroupDao extends BaseMybatisDao<AlarmGroup,Long> {
	/**
	 * 
			*@name 根据用户id查询相关的接收组列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<AlarmGroup> listByUserId(@Param("userId")Long userId,@Param("name")String name,
								@Param("status")EnableStatus status,@Param("pager")Pager pager);

	/**
	 * 
			*@name 查询用户相关的接收组记录总数
			*@Description  
			*@CreateDate 2015年8月6日下午5:40:59
	 */
	int countByUserId(@Param("userId")Long userId,@Param("name")String name,@Param("status")EnableStatus status);
	
	/**
	 * 
			*@name 获取接收组详情
			*@Description  
			*@CreateDate 2017年5月11日下午6:11:01
	 */
	AlarmGroup getByUserId(@Param("userId")Long userId,@Param("groupId")Long groupId);
	
	/**
	 * 
			*@name 删除接收组
			*@Description  
			*@CreateDate 2017年5月11日下午6:01:58
	 */
	void delByUserId(@Param("userId")Long userId,@Param("groupId")Long groupId);
	
	/**
	 * 
			*@name 更新接收组
			*@Description  
			*@CreateDate 2017年5月11日下午6:03:18
	 */
	void updateByUserId(AlarmGroup alarmGroup);
	
	/**
	 * 
			*@name 删除组下的全部接收者
			*@Description  
			*@CreateDate 2017年5月12日下午5:31:39
	 */
	void batchDelReceiver(@Param("userId")Long userId,@Param("groupId")Long groupId);
	
	/**
	 * 
			*@name 批量新增接收者
			*@Description  
			*@CreateDate 2017年5月12日下午5:33:42
	 */
	void batchAddReceiver(@Param("userId")Long userId,@Param("groupId")Long groupId,
								@Param("receiverList")List<Long> receiverList);
}
