package com.dev.admin.controller;

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

import com.dev.admin.entity.FilterChain;
import com.dev.admin.service.FilterChainService;
import com.dev.base.controller.BaseController;
import com.dev.base.enums.EnableStatus;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.Pager;
import com.dev.base.util.WebPaginate;
import com.dev.base.utils.ValidateUtils;

/**
 * 
		* <p>Title: shiro过滤链</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月6日下午4:19:22</p>
 */
@Controller
@RequestMapping("/admin/filter/chain")
public class FilterChainMgrController extends BaseController{
	@Autowired
	private FilterChainService filterChainService;
	
	/**
	 * 
			*@name 查询列表
			*@Description  
			*@CreateDate 2017年6月30日上午11:06:17
	 */
	@RequestMapping("/list.htm")
	public String list(HttpServletRequest request,Model model,
							String name,String url,String filter,String status,
							Integer pageNumber,Integer pageSize){
		EnableStatus enableStatus = StringUtils.isEmpty(status) ? null : EnableStatus.valueOf(status);
		Pager pager = new Pager(pageNumber, pageSize);
		List<FilterChain> list = filterChainService.list(name, url, filter, enableStatus, pager);
		int count = filterChainService.count(name, url, filter, enableStatus);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "admin/priv/filterChainList";
	}
	
	/**
	 * 
			*@name 新增
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/add.htm",method = RequestMethod.POST)
	public @ResponseBody Map add(HttpServletRequest request,FilterChain filterChain){
		ValidateUtils.notNull(filterChain.getName(), ErrorCode.SYS_001,"名称不能为空");
		ValidateUtils.notNull(filterChain.getUrl(), ErrorCode.SYS_001,"url规则不能为空");
		ValidateUtils.notNull(filterChain.getFilter(), ErrorCode.SYS_001,"过滤链不能为空");
		
		filterChainService.add(filterChain);
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 获取详情
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/info.htm",method = RequestMethod.GET)
	public @ResponseBody Map getInfo(FilterChain filterChain,Long chainId){
		ValidateUtils.notNull(chainId, ErrorCode.SYS_001,"过滤链id不能为空");
		FilterChain chain = filterChainService.getById(chainId);
		return JsonUtils.createSuccess(chain);
	}
	
	/**
	 * 
			*@name 编辑
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/update.htm",method = RequestMethod.POST)
	public @ResponseBody Map update(FilterChain filterChain,Long chainId){
		ValidateUtils.notNull(chainId, ErrorCode.SYS_001,"过滤链id不能为空");
		ValidateUtils.notNull(filterChain.getName(), ErrorCode.SYS_001,"名称不能为空");
		ValidateUtils.notNull(filterChain.getUrl(), ErrorCode.SYS_001,"url规则不能为空");
		ValidateUtils.notNull(filterChain.getFilter(), ErrorCode.SYS_001,"过滤链不能为空");
		
		filterChain.setId(chainId);
		filterChainService.update(filterChain);
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 删除
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/del.htm",method = RequestMethod.POST)
	public @ResponseBody Map del(Long chainId){
		ValidateUtils.notNull(chainId, ErrorCode.SYS_001,"过滤链id不能为空");
		filterChainService.deleteById(chainId);
		
		return JsonUtils.createSuccess();
	}
}
