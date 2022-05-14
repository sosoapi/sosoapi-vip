package com.dev.admin.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.dev.admin.dao.PrivilegeDao;
import com.dev.admin.entity.Privilege;
import com.dev.admin.service.PrivilegeService;
import com.dev.admin.vo.PrivilegeInfo;
import com.dev.base.constant.AppConstants;
import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.MenuDisPosition;
import com.dev.base.enums.PrivScope;
import com.dev.base.enums.PrivilegeType;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;
import com.dev.base.utils.FormatUtils;
import com.dev.base.utils.MapUtils;
import com.dev.base.vo.SelectInfo;
import com.dev.base.vo.TreeNodeInfo;

/**
 * 
		* <p>Title: 权限服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年6月21日下午9:36:59</p>
 */
@Service
public class PrivilegeServiceImpl extends BaseMybatisServiceImpl<Privilege,Long,PrivilegeDao>
										implements PrivilegeService{
	
	@Override
	public Privilege add(Privilege privilege) {
		if (privilege.getDisPosition() == null) {
			privilege.setDisPosition(MenuDisPosition.none);
		}
		
		getMybatisDao().add(privilege);
		return privilege;
	}

	@Override
	public List<PrivilegeInfo> list(String name, String url, PrivilegeType type, 
								EnableStatus status, Boolean accessVerify,
								Long parentId,PrivScope scope,Pager pager) {
		name = getLikeExpr(name);
		url = getLikeExpr(url);
		
		List<Map> list = getMybatisDao().list(name, url, type, status, accessVerify,parentId,getScopeName(scope), pager);
		List<PrivilegeInfo> result = new ArrayList<PrivilegeInfo>();
		PrivilegeInfo privilegeInfo = null;
		for (Map info : list) {
			privilegeInfo = new PrivilegeInfo();
			privilegeInfo.setId((Long)info.get("id"));
			privilegeInfo.setCode((String)info.get("code"));
			privilegeInfo.setName((String)info.get("name"));
			privilegeInfo.setUrl((String)info.get("url"));
			privilegeInfo.setType(PrivilegeType.valueOf((String)info.get("type")));
			privilegeInfo.setParentId((Long)info.get("parentId"));
			privilegeInfo.setParentName((String)info.get("parentName"));
			privilegeInfo.setParentCode((String)info.get("parentCode"));
			privilegeInfo.setPermission((String)info.get("permission"));
			privilegeInfo.setAccessVerify((Integer)info.get("accessVerify") == 1);
			privilegeInfo.setStatus(EnableStatus.valueOf((String)info.get("status")));
			privilegeInfo.setSortWeight((Integer)info.get("sortWeight"));
			privilegeInfo.setCreateDate((Date)info.get("createDate"));
			privilegeInfo.setScope(PrivScope.valueOf((String)info.get("scope")));
			privilegeInfo.setImgUrl((String)info.get("imgUrl"));
			result.add(privilegeInfo);
		}
		
		return result;
	}

	@Override
	public List<Privilege> listAll(PrivilegeType type, EnableStatus status, 
									Boolean accessVerify, Long parentId,PrivScope scope) {
		return getMybatisDao().listAll(type, status, accessVerify, parentId,getScopeName(scope));
	}
	
	@Override
	public int count(String name, String url, PrivilegeType type, 
						EnableStatus status, Boolean accessVerify,Long parentId,PrivScope scope) {
		name = getLikeExpr(name);
		url = getLikeExpr(url);
		
		return getMybatisDao().count(name, url, type, status, accessVerify,parentId,getScopeName(scope));
	}

	@Override
	public List<PrivilegeInfo> listByRoleId(Long roleId, EnableStatus status, Boolean accessVerify) {
		List<PrivilegeInfo> result = new ArrayList<PrivilegeInfo>();
		List<Map> infoList = getMybatisDao().listByRoleId(roleId, status, accessVerify);
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

	@Override
	public List<Privilege> listParent(EnableStatus status,PrivScope scope) {
		return getMybatisDao().listParent(status,getScopeName(scope));
	}

	@Override
	public List<SelectInfo> listParentPriv(PrivScope scope) {
		List<Privilege> parentList = listParent(EnableStatus.on,scope);
		List<SelectInfo> selectList = new ArrayList<SelectInfo>();
		Map<Long, String> parentInfo = MapUtils.newMap();
		for (Privilege privilege : parentList) {
			parentInfo.put(privilege.getId(), privilege.getName());
			selectList.add(new SelectInfo(privilege.getName(), "" + privilege.getId()));
		}
		
		return selectList;
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
	public Set<String> listPermByRoleId(Long roleId) {
		return getMybatisDao().listPermByRoleId(roleId);
	}

	@Override
	public List<PrivilegeInfo> buildMenuTree(Long roleId) {
		List<PrivilegeInfo> privList = listByRoleId(roleId, EnableStatus.on, null);
		List<PrivilegeInfo> menuTree = new ArrayList<PrivilegeInfo>();
		Map<Long, PrivilegeInfo> privMap = new LinkedHashMap<Long,PrivilegeInfo>();
		PrivilegeInfo parent = null;
		for (PrivilegeInfo privInfo : privList) {
			//仅处理权限类型是菜单组或菜单的权限用于组装菜单树
			if (privInfo.getType() != PrivilegeType.cate 
					&& privInfo.getType() != PrivilegeType.menu) {
				continue ;
			}
			
			//保存子节点
			if (privInfo.getParentId() != null 
					&& !FormatUtils.isEqual(privInfo.getParentId(), AppConstants.PRIV_ROOT_PARENT_ID)) {
				parent = privMap.get(privInfo.getParentId());
				//如果父节点不存在，直接过滤，可能是设置不当或sql排序出差，
				//默认优先按父节点权重其次自身权重
				if (parent == null) {
					continue ;
				}
				
				parent.getChildList().add(privInfo);
			}
			//保存根节点
			else if (!privMap.containsKey(privInfo.getId())) {
				privMap.put(privInfo.getId(), privInfo);
			}
		}
		
		Collection<PrivilegeInfo> valus = privMap.values();
		for (PrivilegeInfo privilegeInfo : valus) {
			menuTree.add(privilegeInfo);
		}
		
		return menuTree;
	}
	
	private String getScopeName(PrivScope scope){
		return scope == null ? null : scope.name();
	}
}
