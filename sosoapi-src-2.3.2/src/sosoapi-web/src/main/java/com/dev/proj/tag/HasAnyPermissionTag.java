package com.dev.proj.tag;

import org.springframework.util.StringUtils;

import com.dev.proj.vo.ProjectRoleInfo;

/**
 * 
		* <p>Title: 判断是否包含任一指定权限</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年9月5日下午6:04:36</p>
 */
public class HasAnyPermissionTag extends ProjTag {
	private static final long serialVersionUID = 1L;

	@Override
	protected boolean showTagBody() {
		ProjectRoleInfo roleInfo = getProjRoleInfo(getProjId(), getDocId());
		if (roleInfo == null || StringUtils.isEmpty(getName())) {
			return false;
		}
		
		boolean result = false;
		String[] permArray = getName().split(",");
		for (String perm : permArray) {
			if (roleInfo.getPrivPermSet().contains(perm)) {
				result = true;
				break;
			}
		}
		
		return result;
	}
}
