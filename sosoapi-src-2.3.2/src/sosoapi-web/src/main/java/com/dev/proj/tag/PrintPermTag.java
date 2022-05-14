package com.dev.proj.tag;

import java.util.List;

import com.dev.admin.vo.PrivilegeInfo;
import com.dev.base.enums.PrivilegeType;
import com.dev.proj.vo.ProjectRoleInfo;

/**
 * 
		* <p>Title: 打印指定类型的权限permission</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年9月5日下午6:05:28</p>
 */
public class PrintPermTag extends ProjTag {
	private static final long serialVersionUID = 1L;

	/** 权限类型*/
	private PrivilegeType privType;
	
	@Override
	protected boolean showTagBody() {
		ProjectRoleInfo roleInfo = getProjRoleInfo(getProjId(), getDocId());
		if (roleInfo == null) {
			return false;
		}
		
		StringBuilder permBuilder = new StringBuilder();
		List<PrivilegeInfo> privList = roleInfo.getPrivList();
		for (PrivilegeInfo privilegeInfo : privList) {
			if (privType != null && privType != privilegeInfo.getType()) {
				continue ;
			}
			
			permBuilder.append(privilegeInfo.getPermission()).append(",");
			
		}
		printVal(permBuilder.toString());
		
		return false;
	}

	public PrivilegeType getPrivType() {
		return privType;
	}

	public void setPrivType(PrivilegeType privType) {
		this.privType = privType;
	}
}
