package com.dev.doc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.base.constant.CfgConstants;
import com.dev.base.controller.BaseController;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.Pager;
import com.dev.base.util.WebPaginate;
import com.dev.base.util.WebUtil;
import com.dev.base.utils.ValidateUtils;
import com.dev.doc.entity.DocArchive;
import com.dev.doc.service.DocArchiveService;

/**
 * 
		* <p>Title: 文档归档</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年9月26日下午2:15:20</p>
 */
@Controller
@RequestMapping("/auth/doc/archive")
public class DocArchiveController extends BaseController {
	@Autowired
	private DocArchiveService docArchiveService;
	
	/**
	 * 
			*@name 查询列表
			*@Description  
			*@CreateDate 2017年7月21日上午10:14:19
	 */
	@RequestMapping("/list.htm")
	public String list(HttpServletRequest request,Model model,Long docId,
							String title,String description,String label,
							Integer pageNumber,Integer pageSize){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		
		Pager pager = new Pager(pageNumber, pageSize);
		List<DocArchive> list = docArchiveService.listByDocId(docId, title, description,label, pager);
		int count = docArchiveService.countByDocId(docId, title, description,label);
		pager.setTotalCount(count);
		pager.setList(list);
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "apidoc/docArchiveList";
	}
	
	/**
	 * 
			*@name 跳转到新增页面
			*@Description  
			*@CreateDate 2015年8月22日下午2:07:39
	 */
	@RequestMapping("/forwardAdd.htm")
	public String forwardAdd(HttpServletRequest request,Model model){
		model.addAttribute("action", "auth/doc/archive/add.htm");
		
		return "apidoc/docArchive";
	}
	
	/**
	 * 
			*@name 新增
			*@Description  
			*@CreateDate 2017年7月21日上午10:21:55
	 */
	@RequestMapping(value = "/add.htm",method = RequestMethod.POST)
	public String add(HttpServletRequest request,Long docId,Long projId,DocArchive docArchive){
		ValidateUtils.notNull(docArchive.getDocId(), ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(docArchive.getProjId(), ErrorCode.SYS_001,"项目id不能为空");
		ValidateUtils.notNull(docArchive.getTitle(), ErrorCode.SYS_001,"标题不能为空");
		docArchive.setUserId(getUserId(request));
		docArchiveService.add(docArchive);
		
		return WebUtil.getRedirectUrl("/auth/doc/archive/list.htm?projId=" 
						+ docArchive.getProjId() + "&docId=" + docArchive.getDocId());
	}
	
	/**
	 * 
			*@name 跳转到更新页面
			*@Description  
			*@CreateDate 2017年10月19日下午12:35:51
	 */
	@RequestMapping(value = "/forwardUpdate.htm",method = RequestMethod.GET)
	public String forwardUpdate(HttpServletRequest request,Model model,Long docId,Long archiveId){
		ValidateUtils.notNull(archiveId, ErrorCode.SYS_001,"归档id不能为空");
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		DocArchive docArchive = docArchiveService.getByDocId(archiveId, docId);
		docArchive.setShareUrl(CfgConstants.WEB_BASE_URL + "pass/doc/archive/share/forward.htm?shareKey=" + docArchive.getShareUrl());
		
		model.addAttribute("archiveInfo", docArchive);
		model.addAttribute("action", "auth/doc/archive/update.htm");
		
		return "apidoc/docArchive";
	}
	
	/**
	 * 
			*@name 更新
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/update.htm",method = RequestMethod.POST)
	public String update(HttpServletRequest request,DocArchive docArchive,Long archiveId){
		ValidateUtils.notNull(archiveId, ErrorCode.SYS_001,"归档id不能为空");
		ValidateUtils.notNull(docArchive.getDocId(), ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(docArchive.getProjId(), ErrorCode.SYS_001,"项目id不能为空");
		ValidateUtils.notNull(docArchive.getTitle(), ErrorCode.SYS_001,"标题不能为空");
		
		docArchive.setId(archiveId);
		docArchiveService.updateByDocId(docArchive);
		
		return WebUtil.getRedirectUrl("/auth/doc/archive/list.htm?projId=" 
				+ docArchive.getProjId() + "&docId=" + docArchive.getDocId());
	}
	
	/**
	 * 
			*@name 删除信息
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/del.htm")
	public @ResponseBody Map del(Long docId,Long archiveId){
		ValidateUtils.notNull(archiveId, ErrorCode.SYS_001,"归档id不能为空");
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		docArchiveService.delByDocId(archiveId, docId);
		return JsonUtils.createSuccess();
	}
}

