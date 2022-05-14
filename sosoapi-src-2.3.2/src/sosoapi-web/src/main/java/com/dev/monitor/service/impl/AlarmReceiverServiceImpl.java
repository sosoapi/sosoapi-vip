package com.dev.monitor.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dev.base.enums.EnableStatus;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;
import com.dev.base.vo.SelectInfo;
import com.dev.monitor.dao.AlarmReceiverDao;
import com.dev.monitor.entity.AlarmReceiver;
import com.dev.monitor.service.AlarmReceiverService;

/**
 * 
		* <p>Title: 警报接收者</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年5月10日下午10:56:37</p>
 */
@Service
public class AlarmReceiverServiceImpl extends BaseMybatisServiceImpl<AlarmReceiver,Long,AlarmReceiverDao>
										implements AlarmReceiverService{

	@Override
	public List<AlarmReceiver> listByUserId(Long userId,String name, String phone, 
											String email, EnableStatus status, Pager pager) {
		name = getLikeExpr(name);
		phone = getLikeExpr(phone);
		email = getLikeExpr(email);
		
		return getMybatisDao().listByUserId(userId,name, phone, email, status, pager);
	}

	@Override
	public int countByUserId(Long userId,String name, String phone, 
								String email, EnableStatus status) {
		name = getLikeExpr(name);
		phone = getLikeExpr(phone);
		email = getLikeExpr(email);
		
		return getMybatisDao().countByUserId(userId,name, phone, email, status);
	}

	@Override
	public AlarmReceiver getByUserId(Long userId, Long receiverId) {
		return getMybatisDao().getByUserId(userId, receiverId);
	}

	@Override
	public void delByUserId(Long userId, Long receiverId) {
		getMybatisDao().delByUserId(userId, receiverId);
	}

	@Override
	public void updateByUserId(AlarmReceiver receiver) {
		getMybatisDao().updateByUserId(receiver);
	}

	@Override
	public boolean isEmailExist(Long userId, String email) {
		return getMybatisDao().countByUserId(userId,null, null, email, null) > 0;
	}

	@Override
	public List<AlarmReceiver> listByGroupId(Long userId, Long groupId) {
		return getMybatisDao().listByGroupId(userId, groupId);
	}
	
	@Override
	public List<SelectInfo> listReceiver(Long userId, Long groupId) {
		List<Map> infoList = getMybatisDao().listReceiver(userId, groupId);
		List<SelectInfo> result = new ArrayList<SelectInfo>();
		SelectInfo info = null;
		for (Map temp : infoList) {
			info = new SelectInfo();
			info.setName((String)temp.get("name"));
			info.setCode("" + (Long)temp.get("id"));
			info.setSelected((Integer)temp.get("existFlag") == 1);
			result.add(info);
		}
		
		return result;
	}
}
