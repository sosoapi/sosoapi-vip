package com.dev.proj.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.dev.admin.vo.PrivilegeInfo;
import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.PrivilegeType;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;
import com.dev.base.vo.SelectInfo;
import com.dev.base.vo.TreeNodeInfo;
import com.dev.proj.dao.ProjectRoleDao;
import com.dev.proj.entity.ProjectRole;
import com.dev.proj.service.ProjectRoleService;

/**
 * 
		* <p>Title: 项目角色相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:38:27</p>
 */
@Service
public class ProjectRoleServiceImpl extends BaseMybatisServiceImpl<ProjectRole,Long,ProjectRoleDao>
										implements ProjectRoleService{
	@Override
	public ProjectRole add(ProjectRole role) {
		role.setDelDisabled(false);
		getMybatisDao().add(role);
		return role;
	}

	@Override
	public List<ProjectRole> list(Long projId,String name, String code, EnableStatus status, Pager pager) {
		name = getLikeExpr(name);
		code = getLikeExpr(code);
		return getMybatisDao().list(projId,name,code, status, pager);
	}

	@Override
	public int count(Long projId,String name, String code, EnableStatus status) {
		name = getLikeExpr(name);
		code = getLikeExpr(code);
		return getMybatisDao().count(projId,name,code, status);
	}

	@Override
	public List<SelectInfo> listRole(Long projId) {
		List<ProjectRole> roleList = getMybatisDao().listByProjId(projId);
		List<SelectInfo> result = new ArrayList<SelectInfo>();
		SelectInfo selectInfo = null;
		for (ProjectRole role : roleList) {
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

	@Override
	public List<TreeNodeInfo> listTreeByRoleId(Long roleId) {
		List<Map> infoList = getMybatisDao().listTreeByRoleId(roleId);
		List<TreeNodeInfo> result = new ArrayList<TreeNodeInfo>();
		TreeNodeInfo nodeInfo = null;
		for (Map map : infoList) {
			nodeInfo = new TreeNodeInfo();
			nodeInfo.setDataId((Long)map.get("id"));
			nodeInfo.setId(parseNodeId(nodeInfo.getDataId()));
			nodeInfo.setName((String)map.get("name"));
			nodeInfo.setTitle(nodeInfo.getName());
			nodeInfo.setParentDataId((Long)map.get("parentId"));
			nodeInfo.setParentId(parseNodeId(nodeInfo.getParentDataId()));
			nodeInfo.setOpen(true);
			nodeInfo.setType((String)map.get("type"));
			nodeInfo.setChecked(map.get("relationId") != null);
			
			result.add(nodeInfo);
		}
		
		return result;
	}
	
	//组装节点id
	private String parseNodeId(Long id){
		return "priv_" + id;
	}

	@Override
	public boolean isExist(Long projId, Long roleId) {
		return getMybatisDao().countByProjId(projId, roleId) > 0;
	}

	@Override
	public ProjectRole getByProjIdAndUserId(Long projId, Long userId) {
		return getMybatisDao().getByProjIdAndUserId(projId, userId);
	}

	@Override
	public List<PrivilegeInfo> listPriv(Long roleId,EnableStatus status,Boolean accessVerify) {
		List<PrivilegeInfo> result = new ArrayList<PrivilegeInfo>();
		List<Map> infoList = getMybatisDao().listPriv(roleId, status, accessVerify);
		PrivilegeInfo info = null;
		for (Map map : infoList) {
			info = new PrivilegeInfo();
			info.setId((Long)map.get("id"));
			info.setCode((String)map.get("code"));
			info.setName((String)map.get("name"));
			info.setUrl((String)map.get("url"));
			info.setType(PrivilegeType.valueOf((String)map.get("type")));
			info.setParentId((Long)map.get("parentId"));
			info.setParentName((String)map.get("parentName"));
			info.setParentCode((String)map.get("parentCode"));
			info.setPermission((String)map.get("permission"));
			info.setAccessVerify((Integer)map.get("accessVerify") == 1);
			info.setStatus(EnableStatus.valueOf((String)map.get("status")));
			info.setIconClass((String)map.get("iconClass"));
			info.setImgUrl((String)map.get("imgUrl"));
			
			result.add(info);
		}
		
		return result;
	}
}
