package com.dev.user.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.admin.service.PrivilegeService;
import com.dev.admin.vo.PrivilegeInfo;
import com.dev.base.constant.AppConstants;
import com.dev.base.constant.CfgConstants;
import com.dev.base.controller.BaseController;
import com.dev.base.enums.LoginType;
import com.dev.base.exception.TipException;
import com.dev.base.exception.ValidateException;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.shiro.util.ShiroUtil;
import com.dev.base.util.WebUtil;
import com.dev.base.utils.RegexUtil;
import com.dev.base.utils.SpringContextUtils;
import com.dev.base.utils.ValidateUtils;
import com.dev.user.service.LoginService;
import com.dev.user.vo.LoginParamInfo;
import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: 登陆退出处理</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午2:04:09</p>
 */
@Controller
@RequestMapping("")
public class LoginController extends BaseController{
	@Autowired
	private LoginService loginService;
	
	//因WebUtil.java中在用户保存密码登录时会调用dealLoginSuccess，直接autowired可能导致null
	private static PrivilegeService privilegeService = SpringContextUtils.getBean(PrivilegeService.class);
	
	/**
	 * 
			*@name 跳转到网站首页
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("")
	public String forwardIndex(HttpServletRequest request){
		//初始化自动登录
		UserInfo userInfo = WebUtil.getUserInfo(request, loginService);
		
		String index = "index";
		if (CfgConstants.PROD_FLAG) {
			if (userInfo != null) {//当前用户已登录
				index = WebUtil.getRedirectUrl(userInfo.getHomeUrl());
			}
			else{//用户未登录
				index = "forward:/forwardLogin.htm";
			}
		}
		
		return index;
	}
	
	/**
	 * 
			*@name 跳转到登陆页面
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/forwardLogin.htm")
	public String forwardLogin(Model model){
		model.addAttribute("isLogin", true);
		return "user/login";
	}
	
	/**
	 * 
			*@name 登陆处理
			*@param name		登陆名
			*@param password	登陆密码
			*@param code		验证码
			*@param	smsCode 	手机验证码
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/login.htm")
	public String login(HttpServletRequest request,HttpServletResponse response,
							String loginName,String passwd,
								String validCode,boolean autoLogin,Model model){
		ValidateUtils.notNull(loginName,ErrorCode.SYS_001, "登陆名不能为空");
		ValidateUtils.notNull(passwd,ErrorCode.SYS_001, "登陆密码不能为空");
		
		//目前默认使用邮箱登陆
		LoginParamInfo paramInfo = new LoginParamInfo();
		paramInfo.setEmail(loginName);
		paramInfo.setPassword(passwd);
		paramInfo.setLoginIp(WebUtil.getClientIp(request));
		paramInfo.setLoginType(LoginType.email);
		paramInfo.setAutoLogin(autoLogin);
		
		UserInfo userInfo = null;
		try {
			ValidateUtils.isTrue(validCode.equals(WebUtil.getSessionAttr(request, AppConstants.CAPTCHA_LOGIN)), ErrorCode.LOGIN_003);
			userInfo = loginService.loginByEmail(paramInfo);
		} 
		catch (ValidateException e) {
			throw new TipException("forward:/forwardLogin.htm", e);
		}

		dealLoginSuccess(request,userInfo);
				
		return getLoginSuccessUrl(request,userInfo);
	}
	
	/**
	 * 
			*@name 处理登录成功
			*@Description  
			*@CreateDate 2017年7月10日下午6:21:41
	 */
	public static void dealLoginSuccess(HttpServletRequest request,UserInfo userInfo){
		if (userInfo == null) {
			return ;
		}
		
		//保存登陆用户信息
		WebUtil.setSessionAttr(request, AppConstants.SESSION_KEY_USER, userInfo);
		
		//创建菜单树
		List<PrivilegeInfo> menuTree = privilegeService.buildMenuTree(userInfo.getRoleId());
		WebUtil.setSessionAttr(request, AppConstants.SESSION_KEY_CUR_PRIV_LIST, menuTree);
		
		//处理token，保存到cookie中
		//切换shiro的自动登录
		/*if (autoLogin) {
			WebUtil.addCookie(response, CfgConstants.COOKIE_TOKEN_NAME, 
								userInfo.getToken(), CfgConstants.COOKIE_TOKEN_EXPIRE);
		}*/
	}
	
	//获取登录后跳转的url
	private String getLoginSuccessUrl(HttpServletRequest request,UserInfo userInfo){
		String redirectUrl = (String)WebUtil.getSessionAttr(request, AppConstants.SESSION_KEY_REDIRECT_URL);
		if (!StringUtils.isEmpty(redirectUrl)) {
			WebUtil.removeSessionAttr(request, AppConstants.SESSION_KEY_REDIRECT_URL);
		}
		else {
			redirectUrl = userInfo.getHomeUrl();
		}
		
		return WebUtil.getRedirectUrl(redirectUrl);
	}
	
	/**
	 * 
			*@name 退出
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/logout.htm")
	public String logout(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("isLogin", true);
		
		/*UserInfo userInfo = getUserInfo(request);
		if (userInfo != null) {
			//更新数据库信息
			UserToken userToken = userTokenService.getByUserId(userInfo.getUserId());
			if (userToken != null) {
				//不能设置为空，索引异常
				if (!userToken.getToken().startsWith("invalid:")) {
					userToken.setToken("invalid:" + userToken.getToken());
					userTokenService.update(userToken);
				}
				
				//删除cookie
				WebUtil.delCookie(response, CfgConstants.COOKIE_TOKEN_NAME);
			}
		}*/
		
		//如果使用了shiro框架，这样做会报错：...Session already invalidated。
		//原因是shiro对HttpSession进行了一层包装，你直接让原生的session失效，
		//没有通知shiro，shiro再去使用session时就会报错了。
		//解决方法，不要使用原生的失效方法，而是使用shiro自己提供的api方法：SecurityUtils.getSubject().logout();
		//request.getSession().invalidate();
		ShiroUtil.logout();
		
		return "user/login";
	}
	
	/**
	 * 
			*@name 发送重置密码授权码
			*@Description  
			*@CreateDate 2015年8月22日下午2:58:59
	 */
	@RequestMapping("/json/sendResetCode.htm")
	public @ResponseBody Map sendResetCode(HttpServletRequest request,String email){
		ValidateUtils.isTrue(RegexUtil.isEmail(email), ErrorCode.SYS_001,"邮箱格式不正确");
		
		loginService.sendResetCode(email);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 跳转到重置密码页面
			*@Description  
			*@CreateDate 2015年8月22日下午2:58:59
	 */
	@RequestMapping("/forwardReset.htm")
	public String resetPasswd(HttpServletRequest request){
		return "user/reset";
	}
	
	/**
	 * 
			*@name 重置密码
			*@Description  
			*@CreateDate 2015年8月22日下午2:58:59
	 */
	@RequestMapping("/resetPasswd.htm")
	public String resetPasswd(HttpServletRequest request,String code,String passwd){
		ValidateUtils.notNull(code, ErrorCode.SYS_001,"授权码不能为空");
		
		loginService.resetPasswd(code, passwd);
		
		return WebUtil.getRedirectUrl("/resetSuccess.htm");
	}
	
	/**
	 * 
			*@name 跳转到重置密码成功页面
			*@Description  
			*@CreateDate 2015年8月22日下午2:58:59
	 */
	@RequestMapping("/resetSuccess.htm")
	public String resetSuccess(HttpServletRequest request){
		return "user/resetSuccess";
	}
}
