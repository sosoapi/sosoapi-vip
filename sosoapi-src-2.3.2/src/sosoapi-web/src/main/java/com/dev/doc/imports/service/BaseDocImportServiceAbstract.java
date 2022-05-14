package com.dev.doc.imports.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.dev.base.constant.CfgConstants;
import com.dev.base.enums.ProjectStatus;
import com.dev.base.utils.MapUtils;
import com.dev.doc.entity.ApiDoc;
import com.dev.doc.entity.Module;
import com.dev.doc.entity.RespSchema;
import com.dev.doc.service.ApiDocService;
import com.dev.doc.service.ModuleService;
import com.dev.doc.service.RespSchemaService;
import com.dev.proj.entity.Project;
import com.dev.proj.entity.ProjectMember;
import com.dev.proj.service.ProjectMemberService;
import com.dev.proj.service.ProjectService;

/**
 * 
		* <p>Title: 导入基类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年8月15日下午2:49:27</p>
 */
public abstract class BaseDocImportServiceAbstract<T> implements BaseDocImportService<T>{
	@Autowired
	private ApiDocService apiDocService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ProjectMemberService projectMemberService;
	
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private RespSchemaService respSchemaService;
	
	/**
	 * 
			*@name 保存文档信息
			*@Description  
			*@CreateDate 2017年8月15日下午3:30:49
	 */
	protected ApiDoc saveApiDoc(Long userId,ApiDoc apiDoc) {
		Long projId = saveProj(userId, apiDoc);
		apiDoc.setProjId(projId);
		apiDocService.add(apiDoc);
		
		return apiDoc;
	}
	
	/**
	 * 
			*@name 保存项目信息
			*@Description  
			*@CreateDate 2017年8月15日下午3:24:34
	 */
	protected Long saveProj(Long userId,ApiDoc apiDoc){
		//添加项目
		Project project = new Project();
		project.setCode(apiDoc.getTitle());
		project.setName(apiDoc.getTitle());
		project.setDescription(apiDoc.getDescription());
		project.setStatus(ProjectStatus.open);
		project.setUserId(userId);
		projectService.simpleAdd(project);
		
		//绑定项目成员
		ProjectMember member = new ProjectMember();
		member.setProjId(project.getId());
		member.setUserId(userId);
		member.setProjRoleId(CfgConstants.PROJ_ROLE_ID_ADMIN);
		projectMemberService.add(member);
		
		return project.getId();
	}
	
	/**
	 * 
			*@name 获取模块信息
			*@Description  {name:id}
			*@CreateDate 2017年8月15日下午3:41:12
	 */
	protected Map<String, Long> getModuleInfo(Long docId) {
		List<Module> list = moduleService.listAllByDocId(docId);
		Map<String,Long> result = MapUtils.newMap();
		for (Module module : list) {
			result.put(module.getName(), module.getId());
		}
		
		return result;
	}
	
	/**
	 * 
			*@name 获取公共数据结构信息
			*@Description  {code:id}
			*@CreateDate 2017年8月15日下午3:41:12
	 */
	protected Map<String, Long> getRespSchemaInfo(Long docId) {
		Map<String,Long> result = MapUtils.newMap();
		List<RespSchema> schemaList = respSchemaService.listAllByDocId(docId);
		for (RespSchema respSchema : schemaList) {
			result.put(respSchema.getCode(), respSchema.getId());
		}
		
		return result;
	}
	
	/**
	 * 
			*@name 将列表转换为字符串
			*@Description  
			*@CreateDate 2017年8月15日下午3:26:57
	 */
	protected String parseList(List<String> paramList,boolean upperCase){
		StringBuilder listBuilder = new StringBuilder();
		if (CollectionUtils.isEmpty(paramList)) {
			return listBuilder.toString();
		}
		
		int size = paramList.size();
		String param = null;
		for (int i = 0; i < size; i++) {
			param = paramList.get(i);
			if (StringUtils.isEmpty(param)) {
				continue ;
			}
			
			if (upperCase) {
				param = param.toUpperCase();
			}
			
			listBuilder.append(param);
			if (i != (size - 1)) {
				listBuilder.append(",");
			}
		}
		
		return listBuilder.toString();
	}
}
