package com.dev.base.shiro.authc.credential;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.subject.PrincipalCollection;

import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: 自定义密码匹配</p>
		* <p>Description: 该实现同SimpleCredentialsMatcher</p>
		* <p>CreateDate: 2017年6月14日下午5:46:32</p>
 */
public class CustCredentialsMatcher implements CredentialsMatcher{
	//注入指定加密算法
	private PasswordService passwordService;
	
	/**
	 * @name 身份验证 
	 * @param token 登录时传递进来的登录凭证信息
	 * @param info realm中doGetAuthenticationInfo拼装的数据库身份授权信息
	 * @CreateDate 2017年6月14日下午6:54:13
	 */
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String password = parsePassword(token.getCredentials()) + parseUserId(info);
		return passwordService.passwordsMatch(password, (String)info.getCredentials());
	}

	public PasswordService getPasswordService() {
		return passwordService;
	}

	public void setPasswordService(PasswordService passwordService) {
		this.passwordService = passwordService;
	}
	
	//转换密码字符串
	private String parsePassword(Object plaintextPassword){
		String password = "";
		if (plaintextPassword instanceof String) {
			password = (String)plaintextPassword;
		}
		else{
			password = new String((char[])plaintextPassword);
		}
		
		return password;
	}
	
	//获取用户id
	private Long parseUserId(AuthenticationInfo info){
		PrincipalCollection collection = info.getPrincipals();
		return ((UserInfo)collection.getPrimaryPrincipal()).getUserId();
	}
}
