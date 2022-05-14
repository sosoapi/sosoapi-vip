package com.dev.base.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.dev.base.constant.CfgConstants;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
		* <p>Title: 模板工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年10月25日下午10:21:16</p>
 */
public class FreeMarkerUtil {
	//配置信息
	private static Configuration config = initFtlCfg();
	
	/**
	 * word模板名称
	 */
	public static String TMPL_NAME_DOC_WORD= "doc.ftl";
	
	/**
	 * word模板
	 */
	public static Template TMPL_DOC_WORD= getTmpl(TMPL_NAME_DOC_WORD);
		
	/**
	 * html模板名称
	 */
	public static String TMPL_NAME_DOC_HTML = "html.ftl";
	
	/**
	 * html模板
	 */
	public static Template TMPL_DOC_HTML = getTmpl(TMPL_NAME_DOC_HTML);
	
	//初始化配置信息
	private static Configuration initFtlCfg(){
		//编码
		String encoding = CfgConstants.FREEMARKER_ENCODING;
		//模板读取目录
		String ftlDir = CfgConstants.FREEMARKER_FTL_DIR;
		Resource resource = new ClassPathResource(ftlDir);
		
		config = new Configuration(Configuration.VERSION_2_3_22);
		config.setDefaultEncoding(encoding);
		config.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");  
		config.setDateFormat("yyyy-MM-dd");  
		config.setTimeFormat("HH:mm:ss"); 
		config.setOutputEncoding(encoding);
		config.setClassicCompatible(true);
		config.setNumberFormat("#");
		try {
			config.setDirectoryForTemplateLoading(resource.getFile());
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return config;
	}
	
	/**
	 * 
			*@name 获取模板
			*@Description  
			*@CreateDate 2017年8月16日上午7:11:26
	 */
	public static Template getTmpl(String tmplName){
		Template template = null;
		try {
			template = config.getTemplate(tmplName);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return template;
	}
	
	/**
	 * 
			*@name 返回渲染内容
			*@Description  
			*@CreateDate 2017年8月21日上午11:31:55
	 */
	public static String getProcessContent(String tmplName,Object dataModel){
		Template tmpl = getTmpl(tmplName);
		Writer out = new StringWriter();  
		try {
			tmpl.process(dataModel, out);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}  
        return out.toString();  
	}
	
	/**
	 * 
			*@name 渲染模板 
			*@Description  
			*@CreateDate 2015年10月25日下午10:50:06
	 */
	public static void process(String tmplName,Object dataModel,Writer output){
		process(getTmpl(tmplName), dataModel, output);
	}
	
	/**
	 * 
			*@name 渲染模板 
			*@Description  
			*@CreateDate 2015年10月25日下午10:50:06
	 */
	public static void process(Template tmpl,Object dataModel,Writer output){
		try {
			tmpl.process(dataModel, output);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
