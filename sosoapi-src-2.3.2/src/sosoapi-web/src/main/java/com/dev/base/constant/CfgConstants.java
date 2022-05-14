package com.dev.base.constant;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.springframework.util.StringUtils;

import com.dev.admin.entity.SysCfg;
import com.dev.admin.service.SysCfgService;
import com.dev.base.enums.DocArchiveType;
import com.dev.base.enums.SysCfgType;
import com.dev.base.utils.CryptUtil;
import com.dev.base.utils.MapUtils;
import com.dev.base.utils.PropertiesUtils;
import com.dev.base.utils.RandomUtils;

/**
 * 
		* <p>Title: 配置信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午1:45:42</p>
 */
public class CfgConstants {
	// 配置信息
	private final static Properties cfgProperties = PropertiesUtils.getProperties("cfg.properties");

	// 数据库配置信息
	private static Map<String, String> cfgDbInfo = null;
	
	//数据库加载
	/**
	 * 分页展示页码数
	 */
	//public static final int SHOW_PAGE_NUMBER = Integer.parseInt(cfgProperties.getProperty("show.page.number"));
	public static int SHOW_PAGE_NUMBER;
	
	/**
	 * 每页显示记录数
	 */
	//public static final int SHOW_PAGE_SIZE = Integer.parseInt(cfgProperties.getProperty("show.page.size"));
	public static int SHOW_PAGE_SIZE;
	
	/**
	 * 一天最多登陆失败次数，超过账号锁定
	 */
	//public static final int LIMIT_LOGIN_FAIL_COUNT = Integer.parseInt(cfgProperties.getProperty("limit.login.fail.count"));
	public static int LIMIT_LOGIN_FAIL_COUNT;
	
	/**
	 * 每个用户可创建的监控数目
	 */
	//public static final int LIMIT_MONITOR_COUNT = Integer.parseInt(cfgProperties.getProperty("limit.monitor.count"));
	public static int LIMIT_MONITOR_COUNT;
	
	/**
	 * 网站名称
	 */
	//public static final String WEB_NAME = cfgProperties.getProperty("web.name");
	public static String WEB_NAME;
	
	/**
	 * 网站url
	 */
	//public static final String WEB_SITE = cfgProperties.getProperty("web.site");
	public static String WEB_SITE;
	
	/**
	 * 网站联系人邮箱
	 */
	//public static final String WEB_SERVICE_EMAIL = cfgProperties.getProperty("web.service.email");
	public static String WEB_SERVICE_EMAIL;
	
	/**
	 * 网站联系人电话
	 */
	//public static final String WEB_SERVICE_TEL = cfgProperties.getProperty("web.service.tel");
	public static String WEB_SERVICE_TEL;
	
	/**
	 * 网页tittle后缀
	 */
	//public static final String WEB_PAGE_TITLE_POSTFIX = cfgProperties.getProperty("web.page.title.postfix");
	public static String WEB_PAGE_TITLE_POSTFIX;
	
	/**
	 * 网页description
	 */
	//public static final String WEB_PAGE_META_DESCRIPTION = cfgProperties.getProperty("web.page.meta.description");
	public static String WEB_PAGE_META_DESCRIPTION;
	
	/**
	 * 网页keywords
	 */
	//public static final String WEB_PAGE_META_KEYWORDS = cfgProperties.getProperty("web.page.meta.keywords");
	public static String WEB_PAGE_META_KEYWORDS;
	
	/**
	 * 是否启用cdn
	 */
	//public static final boolean WEB_CDN_ENABLE = Boolean.parseBoolean(cfgProperties.getProperty("web.cdn.enable"));
	public static boolean WEB_CDN_ENABLE;
	
	/**
	 * 是否启用
	 */
	//public static final boolean ENABLE_REGIST = Boolean.parseBoolean(cfgProperties.getProperty("enable.regist"));
	public static boolean ENABLE_REGIST;
	
	/**
	 * 注册用户是否需要验证邮箱
	 */
	public static boolean REGIST_EMAIL_VALID;
	
	/**
	 * 是否启用接口列表
	 */
	//public static final boolean ENABLE_INTER_LIST = Boolean.parseBoolean(cfgProperties.getProperty("enable.inter.list"));
	public static boolean ENABLE_INTER_LIST;

	/**
	 * 允许邀请注册用户为成员
	 */
	public static boolean ENABLE_PROJ_MEM_ALL;
	
	//配置文件加载
	/**
	 * 项目contextPath,启动后在CfgInitListener.java中完成初始化
	 * /sosoapi-web
	 */
	public static String WEB_CONTEXT_PATH = "";
	
	/**
	 * 网站版权
	 */
	public static final String WEB_COPYRIGHT = cfgProperties.getProperty("web.copyright");
	
	/**
	 * 页面基路径
	 */
	public static String WEB_BASE_URL = cfgProperties.getProperty("web.base.url");
	
	/**
	 * cookie域名
	 */
	public static final String COOKIE_DOMAIN = cfgProperties.getProperty("cookie.domain");
	
	/**
	 * 客户端保存token名称
	 */
	public static final String COOKIE_TOKEN_NAME = "token";
	
	/**
	 * 客户端保存token有效期，单位秒
	 */
	public static final int COOKIE_TOKEN_EXPIRE = Integer.parseInt(cfgProperties.getProperty("cookie.token.expire"));
	
	/**
	 * 图片域名
	 */
	public static final String IMG_DOMAIN = cfgProperties.getProperty("img.domain");
	
	/**
	 * swagger ui下载url
	 */
	public static final String SWAGGER_UI_DOWNLOAD_URL = cfgProperties.getProperty("swagger.ui.download.url");
	
	/**
	 * swagger导入默认项目标题
	 */
	public static final String SWAGGER_IMPORT_DEF_TITLE = cfgProperties.getProperty("swagger.import.def.title");
	
	/**
	 * 线上编辑说明url
	 */
	public static final String ONLINE_HELP_URL = cfgProperties.getProperty("online.help.url");
	
	/**
	 * 线下部署说明url
	 */
	public static final String OFFLINE_HELP_URL = cfgProperties.getProperty("offline.help.url");
	
	/**
	 * 复制标志
	 */
	public static final String COPY_FLAG = cfgProperties.getProperty("copy.flag");
	
	/**
	 * 产品标识
	 */
	//public static final boolean PROD_FLAG = StringUtils.isEmpty(cfgProperties.getProperty("prod.flag")) ? true : Boolean.valueOf(cfgProperties.getProperty("prod.flag"));
	public static final boolean PROD_FLAG = !"false".equals(cfgProperties.getProperty("prod.flag"));
	
	/**
	 * 减少httpclient请求爬数据
	 */
	public static final String SYS_REQ_TOKEN = "" + RandomUtils.genRandomNum(1000, 1000000);
	
	/**
	 * 安全密钥
	 */
	public static final byte[] SECURITY_SECRET_KEY = CryptUtil.buildSecretKey((String)cfgProperties.getProperty("security.secret.key"));
	
	/**
	 * 订单失效时间
	 */
	public static final int ORDER_EXPIRE_MIN = Integer.parseInt(cfgProperties.getProperty("order.expire.min"));
	
	/**
	 * 商品下载基路径
	 */
	public static final String GOODS_DOWNLOAD_BASE_URL = cfgProperties.getProperty("goods.download.base.url");
	
	/**
	 * 商品下载最新版
	 */
	public static final String GOODS_LATEST_VERSION = cfgProperties.getProperty("goods.latest.version");
	
	/**
	 * 是否启用对demo项目的权限控制
	 */
	public static final boolean ENABLE_DEMO_AUTH = Boolean.parseBoolean(cfgProperties.getProperty("enable.demo.auth"));
	
	/**
	 * demo 项目id
	 */
	public static final Long DEMO_PROJ_ID = Long.parseLong(cfgProperties.getProperty("demo.proj.id"));
	
	/**
	 * demo 文档id
	 */
	public static final Long DEMO_DOC_ID = Long.parseLong(cfgProperties.getProperty("demo.doc.id"));
	
	/**
	 * 项目管理员角色id
	 */
	public static final Long PROJ_ROLE_ID_ADMIN = Long.parseLong(cfgProperties.getProperty("proj.role.id.admin"));
	
	/**
	 * 项目访客角色id
	 */
	public static final Long PROJ_ROLE_ID_GUEST = Long.parseLong(cfgProperties.getProperty("proj.role.id.guest"));
	
	/**
	 * mock环境名称
	 */
	public static final String MOCK_ENV_NAME = cfgProperties.getProperty("mock.env.name");
	
	/**
	 * 管理员角色id
	 */
	public static final Long ROLE_ID_ADMIN = Long.parseLong(cfgProperties.getProperty("role.id.admin"));
	
	/**
	 * vip用户角色id
	 */
	public static final Long ROLE_ID_VIP = Long.parseLong(cfgProperties.getProperty("role.id.vip"));
	
	/**
	 * 默认注册用户角色id
	 */
	public static final Long ROLE_ID_DEF = Long.parseLong(cfgProperties.getProperty("role.id.def"));
	
	/**
	 * 默认角色主页url
	 */
	public static final String ROLE_HOME_URL_DEF = cfgProperties.getProperty("role.home.url.def");
	
	/**
	 * 文档归档-存放方式
	 */
	public static final DocArchiveType DOC_ARCHIVE_TYPE = DocArchiveType.valueOf(cfgProperties.getProperty("doc.archive.type"));
	
	/**
	 * 文档归档-本地访问根目录
	 */
	public static final String DOC_ARCHIVE_LOCAL_BASE_URL = cfgProperties.getProperty("doc.archive.local.base.url");
	
	/**
	 * 文档归档-远程存储访问根目录
	 */
	public static final String DOC_ARCHIVE_REMOTE_BASE_URL = cfgProperties.getProperty("doc.archive.remote.base.url");
	
	/**
	 * 文档归档-根目录前缀
	 */
	public static final String DOC_ARCHIVE_URL_PREFIX = cfgProperties.getProperty("doc.archive.url.prefix");
	
	/**
	 * freemarker-模板文件位置
	 */
	public static final String FREEMARKER_FTL_DIR = cfgProperties.getProperty("freemarker.ftl.dir");
	
	/**
	 * freemarker-编码
	 */
	public static final String FREEMARKER_ENCODING = cfgProperties.getProperty("freemarker.encoding");
	
	/**
	 * mock跨域自定义header参数
	 */
	public static String MOCK_CORS_HEADER_CUST;
	
	/**
	 * 
			*@name 加载数据库配置信息
			*@Description  
			*@CreateDate 2017年7月18日下午4:15:32
	 */
	public static void loadDbCfg(SysCfgService service,boolean refresh){
		//加载数据库配置信息
		loadDbCfgInfo(service,refresh);
		
		SHOW_PAGE_NUMBER = Integer.parseInt(cfgDbInfo.get("show.page.number"));
		SHOW_PAGE_SIZE = Integer.parseInt(cfgDbInfo.get("show.page.size"));
		LIMIT_LOGIN_FAIL_COUNT = Integer.parseInt(cfgDbInfo.get("limit.login.fail.count"));
		LIMIT_MONITOR_COUNT = Integer.parseInt(cfgDbInfo.get("limit.monitor.count"));
		WEB_NAME = cfgDbInfo.get("web.name");
		WEB_SITE = cfgDbInfo.get("web.site");
		WEB_SERVICE_EMAIL = cfgDbInfo.get("web.service.email");
		WEB_SERVICE_TEL = cfgDbInfo.get("web.service.tel");
		WEB_PAGE_TITLE_POSTFIX = cfgDbInfo.get("web.page.title.postfix");
		WEB_PAGE_META_DESCRIPTION = cfgDbInfo.get("web.page.meta.description");
		WEB_PAGE_META_KEYWORDS = cfgDbInfo.get("web.page.meta.keywords");
		WEB_CDN_ENABLE = Boolean.parseBoolean(cfgDbInfo.get("web.cdn.enable"));
		ENABLE_REGIST = Boolean.parseBoolean(cfgDbInfo.get("enable.regist"));
		REGIST_EMAIL_VALID = Boolean.parseBoolean(cfgDbInfo.get("regist.email.valid"));
		ENABLE_INTER_LIST = Boolean.parseBoolean(cfgDbInfo.get("enable.inter.list"));
		ENABLE_PROJ_MEM_ALL = Boolean.parseBoolean(cfgDbInfo.get("enable.proj.mem.all"));
		MOCK_CORS_HEADER_CUST = (String)cfgDbInfo.get("mock.cors.header.cust");
	}
	
	//获取数据库配置信息
	private static void loadDbCfgInfo(SysCfgService sysCfgService,boolean refresh){
		if (cfgDbInfo == null || refresh) {
			cfgDbInfo = MapUtils.newMap();
			List<SysCfg> cfgList = sysCfgService.listAll(SysCfgType.item, null, null);
			String cfgCode = "";
			for (SysCfg sysCfg : cfgList) {
				cfgCode = sysCfg.getCode();
				if (StringUtils.isEmpty(cfgCode)) {
					continue ;
				}
				cfgDbInfo.put(cfgCode,sysCfg.getValue());
			}
		}
	}
	
	/**
	 * 
			*@name 更新缓存
			*@Description  
			*@CreateDate 2017年7月18日下午5:52:00
	 */
	public static void updateApplication(ServletContext application){
		String flagVal = cfgProperties.getProperty("prod.flag");
		boolean flag = StringUtils.isEmpty(flagVal) ? true : Boolean.valueOf(flagVal);
		
		//初始化contextPath
		CfgConstants.WEB_CONTEXT_PATH = application.getContextPath();
		if (StringUtils.isEmpty(CfgConstants.WEB_BASE_URL)) {
			CfgConstants.WEB_BASE_URL = CfgConstants.WEB_CONTEXT_PATH + "/";
		}
		
		Map<String, Object> cfgMap = MapUtils.newMap();
		cfgMap.put("WEB_BASE_URL", CfgConstants.WEB_BASE_URL);
		cfgMap.put("WEB_PAGE_TITLE_POSTFIX", CfgConstants.WEB_PAGE_TITLE_POSTFIX);
		cfgMap.put("WEB_PAGE_META_DESCRIPTION", CfgConstants.WEB_PAGE_META_DESCRIPTION);
		cfgMap.put("WEB_PAGE_META_KEYWORDS", CfgConstants.WEB_PAGE_META_KEYWORDS);
		
		cfgMap.put("WEB_COPYRIGHT", CfgConstants.WEB_COPYRIGHT);
		cfgMap.put("WEB_CDN_ENABLE", CfgConstants.WEB_CDN_ENABLE);
		cfgMap.put("SWAGGER_UI_DOWNLOAD_URL", CfgConstants.SWAGGER_UI_DOWNLOAD_URL);
		cfgMap.put("IMG_DOMAIN", CfgConstants.IMG_DOMAIN);
		cfgMap.put("ONLINE_HELP_URL", CfgConstants.ONLINE_HELP_URL);
		cfgMap.put("OFFLINE_HELP_URL", CfgConstants.OFFLINE_HELP_URL);
		cfgMap.put("SYS_REQ_TOKEN", CfgConstants.SYS_REQ_TOKEN);
		cfgMap.put("COPY_FLAG", CfgConstants.COPY_FLAG);
		cfgMap.put("ENABLE_REGIST", CfgConstants.ENABLE_REGIST);
		cfgMap.put("PROD_FLAG", CfgConstants.PROD_FLAG);
		cfgMap.put("REGIST_EMAIL_VALID", CfgConstants.REGIST_EMAIL_VALID);
		cfgMap.put("ENABLE_INTER_LIST", CfgConstants.ENABLE_INTER_LIST);
		cfgMap.put("GOODS_LATEST_VERSION", CfgConstants.GOODS_LATEST_VERSION);
		
		application.setAttribute("Cfg", cfgMap);
	}
}
