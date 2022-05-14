package com.dev.doc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.base.constant.CfgConstants;
import com.dev.base.controller.BaseController;
import com.dev.base.enums.InterNodeType;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.Pager;
import com.dev.base.util.WebPaginate;
import com.dev.base.utils.FormatUtils;
import com.dev.base.utils.ValidateUtils;
import com.dev.base.vo.TreeNodeInfo;
import com.dev.doc.entity.DocView;
import com.dev.doc.entity.DocViewRes;
import com.dev.doc.service.DocViewResService;
import com.dev.doc.service.DocViewService;
import com.dev.doc.vo.DocViewInfo;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * 
		* <p>Title: 文档视图</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年7月19日上午10:30:57</p>
 */
@Controller
@RequestMapping("/auth/doc/view")
public class DocViewController extends BaseController{
	@Autowired
	private DocViewService docViewService;
	
	@Autowired
	private DocViewResService docViewResService;
	
	/**
	 * 
			*@name 查询列表
			*@Description  
			*@CreateDate 2017年7月21日上午10:14:19
	 */
	@RequestMapping("/list.htm")
	public String list(HttpServletRequest request,Model model,Long docId,
							String title,String description,
							Integer pageNumber,Integer pageSize){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		
		Pager pager = new Pager(pageNumber, pageSize);
		Long userId = getUserId(request);
		List<DocViewInfo> list = docViewService.listByDocId(docId, title, description, pager);
		int count = docViewService.countByDocId(docId, title, description);
		pager.setTotalCount(count);
		pager.setList(list);
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "apidoc/docViewList";
	}
	
	/**
	 * 
			*@name 编辑
			*@Description  
			*@CreateDate 2017年7月21日上午10:16:41
	 */
	@RequestMapping(value = "/json/update.htm",method = RequestMethod.POST)
	public @ResponseBody Map update(HttpServletRequest request,DocView docView,Long viewId){
		ValidateUtils.notNull(viewId, ErrorCode.SYS_001,"视图id不能为空");
		ValidateUtils.notNull(docView.getDocId(), ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(docView.getTitle(), ErrorCode.SYS_001,"标题不能为空");
		
		docView.setId(viewId);
		docViewService.updateByDocId(docView);
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 新增
			*@Description  
			*@CreateDate 2017年7月21日上午10:21:55
	 */
	@RequestMapping(value = "/json/add.htm",method = RequestMethod.POST)
	public @ResponseBody Map add(DocView docView){
		ValidateUtils.notNull(docView.getDocId(), ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(docView.getTitle(), ErrorCode.SYS_001,"视图标题不能为空");
		docViewService.add(docView);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 查询信息
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/info.htm")
	public @ResponseBody Map getInfo(Long docId,Long viewId){
		ValidateUtils.notNull(viewId, ErrorCode.SYS_001,"视图id不能为空");
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		DocView docView = docViewService.getByDocId(viewId, docId);
		docView.setShareUrl(CfgConstants.WEB_BASE_URL + "pass/doc/view/share/forward.htm?shareKey=" + docView.getShareUrl());
		return JsonUtils.createSuccess(docView);
	}
	
	/**
	 * 
			*@name 删除信息
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/del.htm")
	public @ResponseBody Map del(Long docId,Long viewId){
		ValidateUtils.notNull(viewId, ErrorCode.SYS_001,"视图id不能为空");
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		docViewService.delByDocId(viewId, docId);
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 资源设置
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/forwardResSet.htm",method = RequestMethod.GET)
	public String forwardResSet(HttpServletRequest request,Model model,Long viewId,Long docId){
		ValidateUtils.notNull(viewId, ErrorCode.SYS_001,"视图id不能为空");
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		return "apidoc/docViewResSet";
	}
	
	/**
	 * 
			*@name 获取树结构
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/load.htm")
	public @ResponseBody List<TreeNodeInfo> loadTreeData(HttpServletRequest request,Long docId,Long viewId){
		ValidateUtils.notNull(viewId, ErrorCode.SYS_001,"视图id不能为空");
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		return docViewResService.loadTreeData(docId, viewId);
	}
	
	/**
	 * 
			*@name 更新资源
			*@Description  
			*@CreateDate 2017年7月3日下午2:39:11
	 */
	@RequestMapping(value = "/json/updateRes.htm",method = RequestMethod.POST)
	public @ResponseBody Map updateRes(Long viewId,Long docId,String resInfo){
		ValidateUtils.notNull(viewId, ErrorCode.SYS_001,"视图id不能为空");
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		List<DocViewRes> resList = parseViewResList(viewId, docId, resInfo);
		docViewResService.updateViewRes(viewId, docId, resList);
		return JsonUtils.createSuccess();
	} 
	
	//解析视图资源列表
	private List<DocViewRes> parseViewResList(Long viewId,Long docId,String resInfo){
		List<DocViewRes> result = new ArrayList<DocViewRes>();
		if (StringUtils.isEmpty(resInfo)) {
			return result;
		}
				
		List<Map<String,String>> resList = JsonUtils.toObject(resInfo, new TypeReference<List<Map<String,String>>>(){});
		DocViewRes viewRes = null;
		for (Map<String, String> res : resList) {
			viewRes = new DocViewRes();
			viewRes.setViewId(viewId);
			viewRes.setDocId(docId);
			viewRes.setType(InterNodeType.valueOf((String)res.get("type")));
			viewRes.setResourceId(FormatUtils.parseLong(res.get("dataId")));
			result.add(viewRes);
		}
		
		return result;
	}
}

