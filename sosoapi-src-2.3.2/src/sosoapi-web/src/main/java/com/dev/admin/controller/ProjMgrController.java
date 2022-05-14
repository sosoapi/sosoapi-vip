package com.dev.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.base.controller.BaseController;
import com.dev.base.enums.ProjectStatus;
import com.dev.base.util.Pager;
import com.dev.base.util.WebPaginate;
import com.dev.proj.service.ProjectService;
import com.dev.proj.vo.ProjectInfo;

/**
 * 
		* <p>Title: 管理员首页</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月6日下午4:19:22</p>
 */
@Controller
@RequestMapping("/admin/proj")
public class ProjMgrController extends BaseController{
	@Autowired
	private ProjectService projectService;
	
	/**
	 * 
			*@name 项目列表
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/list.htm")
	public String listProj(HttpServletRequest request,Model model,String name,String code,
							String email,String status,
							Integer memMinCount,Integer memMaxCount,
							Integer pageNumber,Integer pageSize){
		ProjectStatus projectStatus = null;
		if (!StringUtils.isEmpty(status)) {
			projectStatus = ProjectStatus.valueOf(status);
		}
		
		Pager pager = new Pager(pageNumber, pageSize);
		List<ProjectInfo> list = projectService.listAll(name,code, email, projectStatus,memMinCount,memMaxCount, pager);
		int count = projectService.countAll(name,code, email, projectStatus,memMinCount,memMaxCount);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "admin/projList";
	}
	
}
