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

import com.dev.base.controller.BaseController;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.Pager;
import com.dev.base.util.WebPaginate;
import com.dev.base.utils.ValidateUtils;
import com.dev.base.vo.SelectInfo;
import com.dev.doc.entity.RespSchema;
import com.dev.doc.service.CopyService;
import com.dev.doc.service.ModuleService;
import com.dev.doc.service.RespSchemaService;
import com.dev.doc.vo.RespSchemaInfo;

/**
 * 
		* <p>Title: 项目数据结构相关</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年7月11日下午5:39:29</p>
 */
@Controller
@RequestMapping("/auth/doc/schema")
public class RespSchemaController extends BaseController{
	@Autowired
	private RespSchemaService respSchemaService;
	
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private CopyService copyService;
	
	/**
	 * 
			*@name 数据结构列表
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/list.htm")
	public String list(HttpServletRequest request,Model model,Long docId,
						Long moduleId,String code,String name,String description,
						Integer pageNumber,Integer pageSize){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		
		Pager pager = new Pager(pageNumber, pageSize);
		List<RespSchemaInfo> list = respSchemaService.listByDocId(docId, moduleId,code,name,description,pager);
		int count = respSchemaService.countByDocId(docId, moduleId,code,name,description);
		pager.setTotalCount(count);
		pager.setList(list);
		
		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		
		List<SelectInfo> refSchemaList = respSchemaService.listRespSchema(docId);
		model.addAttribute("refSchemaList", refSchemaList);
		
		List<SelectInfo> moduleList = moduleService.listModule(docId);
		model.addAttribute("moduleList",moduleList);
		
		return "apidoc/schemaList";
	}
	
	/**
	 * 
			*@name 数据结构列表
			*@Description  用于填充下拉框
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/list.htm")
	public @ResponseBody Map listRespSchema(HttpServletRequest request,Long docId){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		
		List<SelectInfo> result = respSchemaService.listRespSchema(docId);
		return JsonUtils.createSuccess(result);
	}
	
	/**
	 * 
			*@name 新增
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping(value = "/json/add.htm",method = RequestMethod.POST)
	public @ResponseBody Map add(HttpServletRequest request,RespSchema schema){
		ValidateUtils.notNull(schema.getDocId(), ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(schema.getCode(), ErrorCode.SYS_001,"编码不能为空");
		
		respSchemaService.add(schema);
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 编辑
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/update.htm",method = RequestMethod.POST)
	public @ResponseBody Map update(HttpServletRequest request,RespSchema schema,Long schemaId){
		ValidateUtils.notNull(schema.getDocId(), ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(schemaId, ErrorCode.SYS_001,"数据结构id不能为空");
		ValidateUtils.notNull(schema.getCode(), ErrorCode.SYS_001,"编码不能为空");
		
		schema.setId(schemaId);
		respSchemaService.updateByDocId(schema);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 删除
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/del.htm")
	public @ResponseBody Map del(HttpServletRequest request,Long docId,Long schemaId){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(schemaId, ErrorCode.SYS_001,"结构id不能为空");
		
		respSchemaService.deleteByDocId(docId, schemaId);
		
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 查询基本信息
			*@Description  
			*@CreateDate 2015年7月11日下午2:05:24
	 */
	@RequestMapping("/json/info.htm")
	public @ResponseBody Map getInfo(Long docId,Long schemaId){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(schemaId, ErrorCode.SYS_001,"结构id不能为空");
		RespSchema schema = respSchemaService.getByDocId(docId,schemaId);
		
		return JsonUtils.createSuccess(schema);
	}
	
	/**
	 * 
			*@name 复制数据结构
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/copy.htm",method = RequestMethod.POST)
	public @ResponseBody Map copy(HttpServletRequest request,Long docId,Long schemaId){
		ValidateUtils.notNull(docId, ErrorCode.SYS_001,"文档id不能为空");
		ValidateUtils.notNull(schemaId, ErrorCode.SYS_001,"结构id不能为空");
		
		Long userId = getUserId(request);
		copyService.copyRespSchema(userId, docId, schemaId);
		
		return JsonUtils.createSuccess();
	}
}
