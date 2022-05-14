package com.dev.base.constant;

/**
 * 
		* <p>Title: 应用相关常量</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午2:11:31</p>
 */
public class AppConstants {
	/**
	 * 保存到session中的用户key
	 */
	public static final String SESSION_KEY_USER = "userInfo";
	
	/**
	 * 保存到session中的文档key
	 */
	public static final String SESSION_KEY_SHARE_INFO_MAP = "shareInfoMap";
	
	/**
	 * 保存到session中的文档视图key
	 */
	public static final String SESSION_KEY_SHARE_VIEW_INFO_MAP = "shareViewInfoMap";
	
	/**
	 * 保存到session中的接口归档key
	 */
	public static final String SESSION_KEY_SHARE_ARCHIVE_INFO_MAP = "shareArchiveInfoMap";
	
	/**
	 * 登录后回跳地址
	 */
	public final static String SESSION_KEY_REDIRECT_URL = "redirectUrl";
	
	/**
	 * 当前用户权限列表
	 */
	public final static String SESSION_KEY_CUR_PRIV_LIST = "curPrivList";
	
	/**
	 * 应用级属性，用于存储公共项目角色列表
	 */
	public final static String APP_KEY_PROJ_ROLE_LIST = "appProjRoleList";
	
	/**
	 * 应用级属性，用于存储项目角色关联的属性信息
	 */
	public final static String APP_KEY_PROJ_ROLE_PRIV_MAP = "appProjRolePrivMap";
	
	/**
	 * 请求对应的权限信息
	 */
	public final static String REQUEST_KEY_PRIV_INFO = "reqPrivInfo";
	
	/**
	 * 父菜单列表
	 */
	public final static String SESSION_KEY_SYS_PRIV_PARENT_LIST = "sysPrivParentList";
	
	/**
	 * 角色列表
	 */
	public final static String SESSION_KEY_SYS_ROLE_LIST = "sysRoleList";
	
	/**
	 * 系统配置分组列表
	 */
	public final static String SESSION_KEY_SYS_CFG_CATE_LIST = "sysCfgCateList";
	
	/**
	 * 注册相关验证码
	 */
	public static final String CAPTCHA_REGIST = "regist";
	
	/**
	 * 登陆相关验证码
	 */
	public static final String CAPTCHA_LOGIN = "login";
	
	/**
	 * 默认编码
	 */
	public final static String DEF_CHARSET = "UTF-8";
	
	/**
	 * 树根节点id
	 */
	public final static Long TREE_ROOT_PARENT_ID = 0L;
	
	/**
	 * 权限根节点的父菜单id
	 */
	public final static Long PRIV_ROOT_PARENT_ID = TREE_ROOT_PARENT_ID;
	
	/**
	 * 权限根节点名称
	 */
	public final static String PRIV_ROOT_PARENT_NAME = "所有权限";
	
	/**
	 * postman导出接口基路径
	 */
	public final static String POSTMAN_BASE_URL = "{{baseUrl}}";
}
