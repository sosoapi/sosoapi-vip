package com.dev.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.dev.admin.dao.RoleDao;
import com.dev.admin.entity.Role;
import com.dev.admin.service.RoleService;
import com.dev.base.enums.EnableStatus;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;
import com.dev.base.vo.SelectInfo;

/**
 * 
		* <p>Title: 角色服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年6月21日下午9:37:25</p>
 */
@Service
public class RoleServiceImpl extends BaseMybatisServiceImpl<Role,Long,RoleDao>
										implements RoleService{

	@Override
	public Role getByUserId(Long userId) {
		return getMybatisDao().getByUserId(userId);
	}

	@Override
	public List<Role> list(String name,String code, EnableStatus status, Pager pager) {
		name = getLikeExpr(name);
		return getMybatisDao().list(name,code, status, pager);
	}

	@Override
	public int count(String name,String code, EnableStatus status) {
		name = getLikeExpr(name);
		return getMybatisDao().count(name,code, status);
	}

	@Override
	public List<SelectInfo> listRole(EnableStatus status) {
		List<Role> roleList = list(null,null, status, null);
		List<SelectInfo> result = new ArrayList<SelectInfo>();
		SelectInfo selectInfo = null;
		for (Role role : roleList) {
			selectInfo = new SelectInfo(role.getName(), "" + role.getId());
			result.add(selectInfo);
		}
		
		return result;
	}

	@Transactional
	@Override
	public void updatePriv(Long roleId, List<Long> privIdList) {
		getMybatisDao().batchDelPriv(roleId);
		if (!CollectionUtils.isEmpty(privIdList)) {
			getMybatisDao().batchAddPriv(roleId, privIdList);
		}
	}
}
