package com.dev.sys.service.impl;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.admin.service.PrivilegeService;
import com.dev.admin.vo.PrivilegeInfo;
import com.dev.base.constant.AppConstants;
import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.PrivilegeType;
import com.dev.base.json.JsonUtils;
import com.dev.base.utils.FormatUtils;
import com.dev.sys.service.StaticDataService;

/**
 * 
		* <p>Title: 系统静态数据</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年7月4日上午11:31:39</p>
 */
@Service
public class StaticDataServiceImpl implements StaticDataService{
	private static Logger logger = LogManager.getLogger(StaticDataServiceImpl.class);
	
	@Autowired
	private PrivilegeService privilegeService;
	
	//系统权限列表
	private static List<PrivilegeInfo> privList = null;
	
	@Override
	public List<PrivilegeInfo> loadPrivList(boolean refresh) {
		if (privList == null || refresh) {
			privList = privilegeService.list(null, null, null, EnableStatus.on, true, null, null,null);
			
			//组装左侧菜单显示编码
			PrivilegeInfo parent = null;
			for (PrivilegeInfo privilegeInfo : privList) {
				//类型为menu或cate的显示当前权限编码
				if (privilegeInfo.getType() == PrivilegeType.menu
						|| privilegeInfo.getType() == PrivilegeType.cate) {
					privilegeInfo.setMenuCode(privilegeInfo.getCode());
					privilegeInfo.setMenuParentCode(privilegeInfo.getParentCode());
					continue ;
				}
				
				//类型不为menu或cate的显示最近父权限类型为menu或cate对应展示编码
				parent = getParentMenu(privilegeInfo);
				if (parent != null) {
					privilegeInfo.setMenuCode(parent.getCode());
					privilegeInfo.setMenuParentCode(parent.getParentCode());
				}
			}
			
			logger.debug(JsonUtils.toFormatJson(privList));
		}
		
		return privList;
	}
	
	//递归获取当前权限最近的菜单类型是menu或cate的父菜单
	private PrivilegeInfo getParentMenu(PrivilegeInfo privilegeInfo){
		//当前权限菜单类型为menu或cate则直接返回
		if (privilegeInfo.getType() == PrivilegeType.cate
				|| privilegeInfo.getType() == PrivilegeType.menu) {
			return privilegeInfo;
		}
		
		Long parentId = privilegeInfo.getParentId();
		//根节点
		if (parentId == null
				|| FormatUtils.isEqual(parentId, AppConstants.PRIV_ROOT_PARENT_ID)) {
			return null;
		}
				
		PrivilegeInfo parent = null;
		for (PrivilegeInfo info : privList) {
			if (FormatUtils.isEqual(parentId, info.getId())) {
				parent = getParentMenu(info);
				break;
			}
		}
		
		return parent;
	}
}
