package com.dev.base.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.dev.admin.entity.Role;
import com.dev.admin.service.PrivilegeService;
import com.dev.admin.service.RoleService;
import com.dev.base.enums.EnableStatus;
import com.dev.base.shiro.util.ShiroUtil;
import com.dev.user.entity.UserBasic;
import com.dev.user.service.UserBasicService;
import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: 自定义数据库realm</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年6月14日下午6:03:59</p>
 */
public class CustJdbcRealm extends AuthorizingRealm{
	private final static Logger logger = Logger.getLogger(CustJdbcRealm.class);  
	
	@Autowired
	private UserBasicService userBasicService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PrivilegeService privilegeService;
	
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.debug("doGetAuthorizationInfo...");
		
		UserBasic userBasic = (UserBasic)ShiroUtil.getAttribute(ShiroUtil.SESSION_KEY_USER_BASIC);
		if (userBasic == null) {
			UserInfo userInfo = (UserInfo)principals.getPrimaryPrincipal();
			//不直接通过userId获取，减少风险
			userBasic = userBasicService.getByEmail(userInfo.getEmail());
		}
		
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(parseRoleSet(userBasic));
        authorizationInfo.setStringPermissions(parsePermissionSet(userBasic));
        return authorizationInfo;
	}

	//拼装角色列表
	private Set<String> parseRoleSet(UserBasic userBasic){
		Set<String> roleSet = new HashSet<String>();
		Role role = roleService.getById(userBasic.getRoleId());
		if (role != null && role.getStatus() == EnableStatus.on) {
			roleSet.add(role.getCode());
		}
		
		return roleSet;
	}
	
	//拼装权限列表
	private Set<String> parsePermissionSet(UserBasic userBasic){
		/*List<Privilege> privList = privilegeService.listByRoleId(userBasic.getRoleId(), EnableStatus.on, true);
		Set<String> permissionSet = new HashSet<String>();
		String permission = "";
		for (Privilege privilege : privList) {
			permission = privilege.getPermission();
			if (StringUtils.isEmpty(permission)) {
				continue ;
			}
			
			permissionSet.add(permission.trim());
		}
		
		return permissionSet;*/
		return privilegeService.listPermByRoleId(userBasic.getRoleId());
	}
	
	//拼装登录用户对应的数据库身份授权信息
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UserBasic userBasic = userBasicService.getByEmail((String)token.getPrincipal());
		loginValid(userBasic);
		
		//用于登录时获取，减少数据库查询
		ShiroUtil.setAttribute(ShiroUtil.SESSION_KEY_USER_BASIC, userBasic);
		
		//作为登录凭证
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(userBasic.getId());
		userInfo.setEmail(userBasic.getEmail());
		
		//交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
		return new SimpleAuthenticationInfo(userInfo, userBasic.getPassword(), getName());
	}
	
	//登录验证
	private void loginValid(UserBasic userBasic){
		if (userBasic == null) {
			throw new UnknownAccountException();
		}
		
		if (userBasic.isLocked()) {
			throw new LockedAccountException();
		}
		
		if (!userBasic.isValid()) {
			throw new DisabledAccountException();
		}
	}
	
	@Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
    	clearCachedAuthorizationInfo(principals);
    	clearCachedAuthenticationInfo(principals);
    }
    
    /**
     * 
    		*@name 删除当前用户的权限缓存信息，重新加载
    		*@Description  
    		*@CreateDate 2017年6月16日下午6:53:20
     */
    public void clearCachedAuthorizationInfo(){
    	Subject subject = SecurityUtils.getSubject();
    	if (subject.isAuthenticated() || subject.isRemembered()) {
    		clearCachedAuthorizationInfo(subject.getPrincipals());
		}
    }
}
