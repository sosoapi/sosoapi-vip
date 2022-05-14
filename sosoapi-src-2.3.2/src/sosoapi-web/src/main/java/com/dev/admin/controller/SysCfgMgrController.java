package com.dev.admin.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.admin.entity.SysCfg;
import com.dev.admin.service.SysCfgService;
import com.dev.base.constant.AppConstants;
import com.dev.base.constant.CfgConstants;
import com.dev.base.controller.BaseController;
import com.dev.base.enums.SysCfgType;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.json.JsonUtils;
import com.dev.base.util.WebUtil;
import com.dev.base.utils.ValidateUtils;
import com.dev.base.vo.SelectInfo;
import com.dev.base.vo.TreeNodeInfo;

/**
 * 
		* <p>Title: 系统参数</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年7月17日上午10:41:04</p>
 */
@Controller
@RequestMapping("/admin/sys/cfg")
public class SysCfgMgrController extends BaseController {
	@Autowired
	private SysCfgService sysCfgService;
	
	/**
	 * 
			*@name 跳转到系统参数页面
			*@Description  
			*@CreateDate 2017年7月17日上午10:51:56
	 */
	@RequestMapping("/list.htm")
	public String list(HttpServletRequest request){
		loadCateList(request);
		return "admin/sysCfgTree";
	}
	
	//加载分组下拉框列表
	private void loadCateList(HttpServletRequest request){
		List<SelectInfo> cateList = sysCfgService.listCate();
		WebUtil.setSessionAttr(request, AppConstants.SESSION_KEY_SYS_CFG_CATE_LIST, cateList);
	}
	
	/**
	 * 
			*@name 加载树结构信息
			*@Description  
			*@CreateDate 2017年7月17日上午10:53:07
	 */
	@RequestMapping(value = "/json/load.htm")
	public @ResponseBody List<TreeNodeInfo> loadTreeData(HttpServletRequest request,
					Long parentDataId,String condition){
		return StringUtils.isEmpty(condition) ? sysCfgService.loadTreeData(parentDataId, condition)
				: sysCfgService.search(condition);
	}
	
	/**
	 * 
			*@name 新增节点
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/add.htm")
	public @ResponseBody Map add(HttpServletRequest request,SysCfg sysCfg){
		ValidateUtils.notNull(sysCfg.getName(), ErrorCode.SYS_001,"名称不能为空");
		if (sysCfg.getType() == SysCfgType.item) {
			ValidateUtils.notNull(sysCfg.getCode(), ErrorCode.SYS_001,"编码不能为空");
			ValidateUtils.isTrue(!sysCfgService.isCodeExist(sysCfg.getCode(), null), ErrorCode.SYS_001,"编码已存在");
		}
		
		sysCfgService.add(sysCfg);
		
		//重新加载分组列表
		if (sysCfg.getType() == SysCfgType.cate) {
			loadCateList(request);
		}
		return JsonUtils.createSuccess(sysCfgService.buildTreeNodeInfo(sysCfg));
	}
	
	/**
	 * 
			*@name 删除节点
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/del.htm")
	public @ResponseBody Map del(HttpServletRequest request,Long cfgId){
		ValidateUtils.notNull(cfgId, ErrorCode.SYS_001,"配置参数id不能为空");
		sysCfgService.deleteById(cfgId);
		
		//重新加载分组列表
		loadCateList(request);
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 获取节点信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/info.htm")
	public @ResponseBody Map getInfo(Long cfgId){
		ValidateUtils.notNull(cfgId, ErrorCode.SYS_001,"配置参数id不能为空");
		
		SysCfg sysCfg = sysCfgService.getById(cfgId);
		return JsonUtils.createSuccess(sysCfg);
	}
	
	/**
	 * 
			*@name 更新节点信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/update.htm")
	public @ResponseBody Map update(HttpServletRequest request,Long cfgId,SysCfg sysCfg){
		ValidateUtils.notNull(cfgId, ErrorCode.SYS_001,"配置参数id不能为空");
		ValidateUtils.notNull(sysCfg.getName(), ErrorCode.SYS_001,"名称不能为空");
		ValidateUtils.notNull(sysCfg.getCode(), ErrorCode.SYS_001,"编码不能为空");
		ValidateUtils.isTrue(!sysCfgService.isCodeExist(sysCfg.getCode(), cfgId), ErrorCode.SYS_001,"编码已存在");
		
		sysCfg.setId(cfgId);
		sysCfgService.update(sysCfg);
		
		//重新加载分组列表
		if (sysCfg.getType() == SysCfgType.cate) {
			loadCateList(request);
		}
		return JsonUtils.createSuccess();
	}
	
	/**
	 * 
			*@name 获取分组列表
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/listCate.htm")
	public @ResponseBody Map listCate(){
		return JsonUtils.createSuccess(sysCfgService.listCate());
	}
	
	/**
	 * 
			*@name 加载系统配置信息
			*@Description  
			*@CreateDate 2015年8月6日下午5:14:18
	 */
	@RequestMapping(value = "/json/reloadAppCfg.htm")
	public @ResponseBody Map reloadAppCfg(HttpServletRequest request){
		CfgConstants.loadDbCfg(sysCfgService,true);
		CfgConstants.updateApplication(request.getSession().getServletContext());
		return JsonUtils.createSuccess();
	}
}
