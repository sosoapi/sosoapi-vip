package com.dev.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.base.controller.BaseController;

/**
 * 
		* <p>Title: 页面跳转</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月14日下午5:39:07</p>
 */
@Controller
public class PageController extends BaseController{
	/**
	 * 
			*@name 用户手册
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24

	 */
	@RequestMapping("/pass/help/{page}.htm")
	public String forwardHelp(@PathVariable("page")String page){
		return "help/" + page;
	}
	
	/**
	 * 
			*@name 用户手册
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24

	 */
	@RequestMapping("/pass/error/{error}.htm")
	public String forwardError(@PathVariable("error")String error){
		return "error/" + error;
	}
}
