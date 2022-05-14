package com.dev.base.shiro.authc.credential;

import org.apache.shiro.authc.credential.PasswordService;
import org.springframework.util.StringUtils;

import com.dev.base.utils.Sha1Utils;

/**
 * 
		* <p>Title: 自定义加解密算法</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年6月14日下午5:39:23</p>
 */
public class CustPasswordService implements PasswordService{

	@Override
	public String encryptPassword(Object plaintextPassword) throws IllegalArgumentException {
		String password = parsePassword(plaintextPassword);
		return StringUtils.isEmpty(password) ? password : Sha1Utils.sha1Crypt(password);
	}

	@Override
	public boolean passwordsMatch(Object submittedPlaintext, String encrypted) {
		String password = parsePassword(submittedPlaintext);
		if (StringUtils.isEmpty(password) || StringUtils.isEmpty(encrypted)) {
			return false;
		}
		
		return encryptPassword(password).equals(encrypted);
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
}
