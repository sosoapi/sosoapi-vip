package com.dev.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 
		* <p>Title: redirect验证</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年12月20日上午9:25:10</p>
 */
@Controller 
@RequestMapping(value = "/demo/redirect")
public class RedirectDemoController {
	@RequestMapping("/test1")
	public ModelAndView test1(ModelAndView view) {
	    view.setViewName("redirect:index");
	    return view;
	}

	@RequestMapping("/test2")
	public ModelAndView test2(ModelAndView view) {
	    view.setViewName("redirect:login");
	    return view;
	}

	@RequestMapping("/test3")
	public ModelAndView test3(ModelAndView view) {
	    view.setViewName("redirect:/index");
	    return view;
	}

	@RequestMapping("/test4")
	public ModelAndView test4(ModelAndView view) {
	    view.setView(new RedirectView("/index", false));
	    return view;
	}

	@RequestMapping("/test5")
	public ModelAndView test5(ModelAndView view) {
	    view.setView(new RedirectView("index", false));
	    return view;
	}

	@RequestMapping("/test6/{id}")
	public ModelAndView test6(ModelAndView view, @PathVariable("id") int id) {
	    view.setViewName("redirect:/index{id}");
	    return view; 
	}
	
	@RequestMapping("/test7/{id}")
	public ModelAndView test7(ModelAndView view, @PathVariable("id") int id) {
	    RedirectView redirectView = new RedirectView("/index{id}");
	    redirectView.setExpandUriTemplateVariables(false);
	    redirectView.setExposeModelAttributes(false);
	    view.setView(redirectView);
	    view.addObject("test", "test");
	    return view;
	}
}
