package com.dev.doc.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;
import com.dev.doc.entity.Module;

/**
 * 
		* <p>Title: 模块消息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:28:32</p>
 */
public interface ModuleDao extends BaseMybatisDao<Module,Long> {
	/**
	 * 
			*@name 根据api文档id查询所有模块列表
			*@Description  
			*@CreateDate 2015年8月6日下午3:41:18
	 */
	List<Module> listByDocId(@Param("docId")Long docId,@Param("code")String code,
									@Param("name")String name,@Param("description")String description,@Param("pager")Pager pager);
	
	/**
	 * 查询api文档相关的模块总数
	 * @param docId
	 * @return
	 */
	int countByDocId(@Param("docId")Long docId,@Param("code")String code,
							@Param("name")String name,@Param("description")String description);
	
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
	Module getByDocId(@Param("docId")Long docId,@Param("moduleId")Long moduleId);
	
	/**
	 * 
			*@name 根据id和文档id进行删除
			*@Description  
			*@CreateDate 2015年9月9日上午11:13:24
	 */
	void deleteByDocId(@Param("docId")Long docId,@Param("moduleId")Long moduleId);
	
	/**
	 * 
			*@name 指定权重之间全部加上指定步长
			*@Description  
			*@CreateDate 2017年3月23日下午5:09:54
	 */
	void buildSortWeight(@Param("docId")Long docId,@Param("minSortWeight")int minSortWeight,
								@Param("maxSortWeight")int maxSortWeight,@Param("step")int step);
	
	/**
	 * 
			*@name 更新模块名称
			*@Description  
			*@CreateDate 2017年3月24日下午5:57:35
	 */
	void updateName(@Param("docId")Long docId,@Param("moduleId")Long moduleId,@Param("name")String name);
	
	/**
	 * 
			*@name 获取指定id的集合 
			*@Description  
			*@CreateDate 2017年3月26日下午6:03:26
	 */
	List<Module> listByIdSet(@Param("docId")Long docId,@Param("idSet")Set<Long> idSet);
	
	/**
	 * 
			*@name 获取最大的排序权重
			*@Description  
			*@CreateDate 2017年3月30日下午3:35:26
	 */
	int getMaxSortWeight(@Param("docId")Long docId);
	
	/**
	 * 
			*@name 更新排序权重
			*@Description  
			*@CreateDate 2017年4月5日下午2:39:23
	 */
	void updateSortWeight(@Param("docId")Long docId,@Param("moduleId")Long moduleId,@Param("sortWeight")int sortWeight);
	
	/**
	 * 
			*@name 查询文档视图关联的模块信息
			*@Description  
			*@CreateDate 2017年7月19日下午6:29:44
	 */
	List<Module> listByViewId(@Param("viewId")Long viewId);
}
