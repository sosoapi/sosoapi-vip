package com.dev.admin.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.base.controller.BaseController;
import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.ProdType;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.Pager;
import com.dev.base.util.WebPaginate;
import com.dev.base.util.WebUtil;
import com.dev.base.utils.DateUtil;
import com.dev.base.utils.ValidateUtils;
import com.dev.prod.entity.Goods;
import com.dev.prod.service.DownloadRecordService;
import com.dev.prod.service.GoodsService;
import com.dev.prod.vo.DownloadRecordInfo;

/**
 * 
		* <p>Title: 商品管理</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2016年3月28日上午10:24:02</p>
 */
@Controller
@RequestMapping("/admin/goods")
public class GoodsMgrController extends BaseController{
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private DownloadRecordService recordService;
	
	/**
	 * 
			*@name 查看商品列表
			*@Description  
			*@CreateDate 2017年1月23日下午2:35:50
	 */
	@RequestMapping("/list.htm")
	public String list(HttpServletRequest request,Model model,String type,String status,String pubStatus,
							String name,Integer pageNumber,Integer pageSize){
		ProdType prodType = StringUtils.isEmpty(type) ? null : ProdType.valueOf(type);
		EnableStatus statusObj = StringUtils.isEmpty(status) ? null : EnableStatus.valueOf(status);
		EnableStatus pubStatusObj = StringUtils.isEmpty(pubStatus) ? null : EnableStatus.valueOf(pubStatus);
		
		Pager pager = new Pager(pageNumber, pageSize);
		List<Goods> list = goodsService.list(prodType, name, statusObj,pubStatusObj, pager);
		int count = goodsService.count(prodType, name, statusObj,pubStatusObj);
		pager.setTotalCount(count);
		pager.setList(list);

		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "admin/goods/goodsList";
	}
	
	/**
	 * 
			*@name 删除
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/del.htm")
	public @ResponseBody Map del(Long goodsId){
		ValidateUtils.notNull(goodsId, ErrorCode.SYS_001,"商品id不能为空");
		goodsService.deleteById(goodsId);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 新增
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/forwardAdd.htm",method = RequestMethod.GET)
	public String forwardAdd(HttpServletRequest request,Model model){
		model.addAttribute("action", "admin/goods/add.htm");
		model.addAttribute("operType", "add");
		
		return "admin/goods/goodsInfo";
	}
	
	/**
	 * 
			*@name 跳转到更新页面
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/forwardUpdate.htm",method = RequestMethod.GET)
	public String forwardUpdate(HttpServletRequest request,Model model,Long goodsId){
		ValidateUtils.notNull(goodsId, ErrorCode.SYS_001,"商品id不能为空");
		
		Goods goods = goodsService.getById(goodsId);
		model.addAttribute("goodsInfo", goods);
		model.addAttribute("action", "admin/goods/update.htm");
		model.addAttribute("operType", "update");
		
		return "admin/goods/goodsInfo";
	}
	
	/**
	 * 
			*@name 更新
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/update.htm",method = RequestMethod.POST)
	public String update(HttpServletRequest request,Goods goods,Long goodsId){
		ValidateUtils.notNull(goodsId, ErrorCode.SYS_001,"商品id不能为空");
		ValidateUtils.notNull(goods.getName(), ErrorCode.SYS_001,"名称不能为空");
		
		goods.setId(goodsId);
		goodsService.update(goods);
		return WebUtil.getRedirectUrl("/admin/goods/list.htm");
	}
	
	/**
	 * 
			*@name 新增
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/add.htm",method = RequestMethod.POST)
	public String add(HttpServletRequest request,Goods goods){
		ValidateUtils.notNull(goods.getName(), ErrorCode.SYS_001,"名称不能为空");
		goodsService.add(goods);
		
		return WebUtil.getRedirectUrl("/admin/goods/list.htm");
	}
	
	/**
	 * 
			*@name 查看商品下载列表
			*@Description  
			*@CreateDate 2017年1月23日下午2:35:50
	 */
	@RequestMapping("/listDownloadRecord.htm")
	public String listDownloadRecord(HttpServletRequest request,Model model,
			String dateStart,String dateEnd,String email,String fileName,
			Integer pageNumber,Integer pageSize){
		Date start = StringUtils.isEmpty(dateStart) ? null : DateUtil.parseByLong(dateStart + " 00:00:00");
		Date end = StringUtils.isEmpty(dateEnd) ? null : DateUtil.parseByLong(dateEnd + " 23:59:59");
		
		Pager pager = new Pager(pageNumber, pageSize);
		List<DownloadRecordInfo> list = recordService.listAll(start, end, fileName, email, pager);
		int count = recordService.countAll(start, end, fileName, email);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		return "admin/goods/downloadRecordList";
	}
	
	/**
	 * 
			*@name 复制
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping("/json/copy.htm")
	public @ResponseBody Map copy(HttpServletRequest request,Long goodsId){
		ValidateUtils.notNull(goodsId, ErrorCode.SYS_001,"版本id不能为空");
		goodsService.copy(goodsId);
		
		return JsonUtils.createSuccess();
	}
}
