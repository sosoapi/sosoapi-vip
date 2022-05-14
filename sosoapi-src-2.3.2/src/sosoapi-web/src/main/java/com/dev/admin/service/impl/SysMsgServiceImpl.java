package com.dev.admin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dev.admin.dao.SysMsgDao;
import com.dev.admin.entity.SysMsg;
import com.dev.admin.service.SysMsgService;
import com.dev.admin.vo.SysMsgInfo;
import com.dev.base.enums.MsgType;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;

/**
 * 
		* <p>Title: 系统消息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月30日下午3:29:59</p>
 */
@Service
public class SysMsgServiceImpl extends BaseMybatisServiceImpl<SysMsg,Long,SysMsgDao>
										implements SysMsgService{

	@Override
	public List<SysMsgInfo> listAll(String title, MsgType msgType,
			Long roleId, Pager pager) {
		title = getLikeExpr(title);
		
		List<Map> infoList = getMybatisDao().listAll(title, msgType, roleId, pager);
		List<SysMsgInfo> result = new ArrayList<SysMsgInfo>();
		SysMsgInfo info = null;
		for (Map map : infoList) {
			info = new SysMsgInfo();
			info.setId((Long)map.get("id"));
			info.setPubDate((Date)map.get("pubDate"));
			info.setTitle((String)map.get("title"));
			info.setContent((String)map.get("content"));
			info.setMsgType(MsgType.valueOf((String)map.get("msgType")));
			info.setRoleId((Long)map.get("roleId"));
			info.setRoleName((String)map.get("roleName"));
			result.add(info);
		}
		
		return result;
	}

	@Override
	public int countAll(String title, MsgType msgType,Long roleId) {
		return getMybatisDao().countAll(title, msgType, roleId);
	}
}
