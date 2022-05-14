package com.dev.monitor.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.base.enums.EnableStatus;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;
import com.dev.base.vo.SelectInfo;
import com.dev.monitor.dao.AlarmGroupDao;
import com.dev.monitor.entity.AlarmGroup;
import com.dev.monitor.service.AlarmGroupService;

/**
 * 
		* <p>Title: 警报接收组</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年5月10日下午10:48:46</p>
 */
@Service
public class AlarmGroupServiceImpl extends BaseMybatisServiceImpl<AlarmGroup,Long,AlarmGroupDao>
										implements AlarmGroupService{

	@Override
	public List<AlarmGroup> listByUserId(Long userId, String name, EnableStatus status, Pager pager) {
		name = getLikeExpr(name);
		return getMybatisDao().listByUserId(userId, name, status, pager);
	}

	@Override
	public int countByUserId(Long userId, String name, EnableStatus status) {
		name = getLikeExpr(name);
		return getMybatisDao().countByUserId(userId, name, status);
	}

	@Override
	public AlarmGroup getByUserId(Long userId, Long groupId) {
		return getMybatisDao().getByUserId(userId, groupId);
	}

	@Override
	public void delByUserId(Long userId, Long groupId) {
		getMybatisDao().delByUserId(userId, groupId);
	}

	@Transactional
	@Override
	public void updateByUserId(AlarmGroup alarmGroup,List<Long> receiverIdList) {
		getMybatisDao().updateByUserId(alarmGroup);
		
		Long userId = alarmGroup.getUserId();
		Long groupId = alarmGroup.getId();
		getMybatisDao().batchDelReceiver(userId, groupId);
		if (!receiverIdList.isEmpty()) {
			getMybatisDao().batchAddReceiver(userId, groupId, receiverIdList);
		}
	}

	@Override
	public List<SelectInfo> listGroup(Long userId) {
		List<AlarmGroup> groupList = listByUserId(userId, null, EnableStatus.on, null);
		List<SelectInfo> result = new ArrayList<SelectInfo>();
		SelectInfo info = null;
		for (AlarmGroup alarmGroup : groupList) {
			info = new SelectInfo();
			info.setName(alarmGroup.getName());
			info.setCode("" + alarmGroup.getId());
			result.add(info);
		}
		
		return result;
	}
}
