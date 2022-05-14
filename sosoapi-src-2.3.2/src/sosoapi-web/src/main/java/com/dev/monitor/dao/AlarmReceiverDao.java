package com.dev.monitor.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dev.base.enums.EnableStatus;
import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;
import com.dev.monitor.entity.AlarmReceiver;

/**
 * 
		* <p>Title: 警报接收者</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年5月10日下午10:56:37</p>
 */
public interface AlarmReceiverDao extends BaseMybatisDao<AlarmReceiver,Long> {
	/**
	 * 
			*@name 根据用户id查询相关的接收者列表
			*@Description  
			*@CreateDate 2015年8月6日下午2:34:14
	 */
	List<AlarmReceiver> listByUserId(@Param("userId")Long userId,@Param("name")String name,
								@Param("phone")String phone,@Param("email")String email,
								@Param("status")EnableStatus status,@Param("pager")Pager pager);

	/**
	 * 
			*@name 查询用户相关的接收者记录总数
			*@Description  
			*@CreateDate 2015年8月6日下午5:40:59
	 */
	int countByUserId(@Param("userId")Long userId,@Param("name")String name,@Param("phone")String phone,
						@Param("email")String email,@Param("status")EnableStatus status);
	
	/**
	 * 
			*@name 获取接收者详情
			*@Description  
			*@CreateDate 2017年5月11日下午6:11:01
	 */
	AlarmReceiver getByUserId(@Param("userId")Long userId,@Param("receiverId")Long receiverId);
	
	/**
	 * 
			*@name 删除接收者
			*@Description  
			*@CreateDate 2017年5月11日下午6:01:58
	 */
	void delByUserId(@Param("userId")Long userId,@Param("receiverId")Long receiverId);
	
	/**
	 * 
			*@name 更新接收者
			*@Description  
			*@CreateDate 2017年5月11日下午6:03:18
	 */
	void updateByUserId(AlarmReceiver receiver);
	
	/**
	 * 
			*@name 获取预警组成员列表
			*@Description  
			*@CreateDate 2017年5月17日下午6:43:24
	 */
	List<AlarmReceiver> listByGroupId(@Param("userId")Long userId,@Param("groupId")Long groupId);
	
	/**
	 * 
			*@name 查询接收者列表，包含已设置和未设置
			*@Description  
			*@CreateDate 2017年5月11日下午11:32:19
	 */
	List<Map> listReceiver(@Param("userId")Long userId,@Param("groupId")Long groupId);
}
