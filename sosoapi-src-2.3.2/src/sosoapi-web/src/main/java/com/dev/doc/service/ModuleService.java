package com.dev.doc.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;
import com.dev.base.vo.SelectInfo;
import com.dev.doc.entity.Module;

/**
 * 
		* <p>Title: 模块相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:38:27</p>
 */
public interface ModuleService extends BaseMybatisService<Module, Long>{
	/**
	 * 
			*@name 根据api文档id查询所有模块列表
			*@Description  
			*@CreateDate 2015年8月6日下午3:41:18
	 */
	List<Module> listByDocId(Long docId,String code,String name,
								String description,Pager pager);
	
	/**
	 * 查询api文档相关的模块总数
	 * @param docId
	 * @return
	 */
	int countByDocId(Long docId,String code,String name,String description);
	
	/**
	 * 
			*@name 查询所有模块信息
			*@Description  
			*@CreateDate 2015年10月11日上午10:31:41
	 */
	List<Module> listAllByDocId(Long docId);
	
	/**
	 * 
			*@name 根据id和文档id进行更新
			*@Description  
			*@CreateDate 2015年9月9日上午10:53:49
	 */
	void updateByDocId(Module module);
	
	/**
	 * 
			*@name 获取模块信息
			*@Description  
			*@CreateDate 2017年3月24日下午4:10:03
	 */
	Module getByDocId(Long docId,Long moduleId);
	
	/**
	 * 
			*@name 根据id和文档id进行删除
			*@Description  
			*@CreateDate 2015年9月9日上午11:13:24
	 */
	void deleteByDocId(Long docId,Long moduleId);
	
	/**
	 * 
			*@name 指定权重之间全部加上指定步长
			*@Description  
			*@CreateDate 2017年3月23日下午5:09:54
	 */
	void buildSortWeight(Long docId,int minSortWeight,int maxSortWeight,int step);
	
	/**
	 * 
			*@name 更新模块名称
			*@Description  
			*@CreateDate 2017年3月24日下午5:57:35
	 */
	void updateName(Long docId,Long moduleId,String name);
	
	/**
	 * 
			*@name 获取指定id的集合 
			*@Description  
			*@CreateDate 2017年3月26日下午6:03:26
	 */
	List<Module> listByIdSet(Long docId,Set<Long> idSet);
	
	/**
	 * 
			*@name 获取最大的排序权重
			*@Description  
			*@CreateDate 2017年3月30日下午3:35:26
	 */
	int getMaxSortWeight(Long docId);
	
	/**
	 * 
			*@name 更新排序权重
			*@Description  
			*@CreateDate 2017年4月5日下午2:39:23
	 */
	void updateSortWeight(Long docId,Long moduleId,int sortWeight);
	
	/**
	 * 
			*@name 获取模块信息
			*@Description  
			*@CreateDate 2017年4月20日下午7:09:48
	 */
	Map<Long, String> getByDocId(Long docId);
	
	/**
	 * 
			*@name 获取模块下拉框列表
			*@Description  
			*@CreateDate 2017年5月23日上午10:18:54
	 */
	List<SelectInfo> listModule(Long docId);
	
	/**
	 * 
			*@name 查询文档视图关联的模块信息
			*@Description  
			*@CreateDate 2017年7月19日下午6:29:44
	 */
	List<Module> listByViewId(Long viewId);
}
