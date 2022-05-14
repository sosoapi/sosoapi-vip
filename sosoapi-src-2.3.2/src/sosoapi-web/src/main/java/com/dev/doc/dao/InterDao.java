package com.dev.doc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dev.base.enums.ReqMethod;
import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;
import com.dev.doc.entity.Inter;

/**
 * 
		* <p>Title: 接口信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:27:56</p>
 */
public interface InterDao extends BaseMybatisDao<Inter,Long> {
	/**
	 * 
			*@name 获取所有接口
			*@Description  
			*@CreateDate 2015年8月15日下午2:23:41
	 */
	List<Inter> listAllByDocId(@Param("docId")Long docId,@Param("moduleId")Long moduleId,
								@Param("deprecated")Boolean deprecated,@Param("condition")String condition);
	
	/**
	 * 
			*@name 查询指定模块下的接口列表
			*@Description  
			*@CreateDate 2017年4月12日下午6:23:41
	 */
	List<Inter> listByModuleId(@Param("docId")Long docId,@Param("moduleId")Long moduleId,@Param("pager")Pager pager);
	
	/**
	 * 
			*@name 查询指定模块下的接口总数
			*@Description  
			*@CreateDate 2017年4月12日下午10:53:53
	 */
	int countByModuleId(@Param("docId")Long docId,@Param("moduleId")Long moduleId);
	
	/**
	 * 
			*@name 获取文档对应的接口
			*@Description  
			*@CreateDate 2016年10月26日下午4:37:50
	 */
	List<Inter> listForMockByDocId(@Param("docId")Long docId,@Param("method")ReqMethod method);
	
	/**
	 * 
			*@name 查询api文档相关接口列表
			*@Description  
			*@CreateDate 2015年8月6日下午3:05:46
	 */
	List<Inter> listByDocId(@Param("docId")Long docId,@Param("moduleId")Long moduleId,
			@Param("name")String name,@Param("path")String path,
			@Param("description")String description,@Param("pager")Pager pager);
	
	/**
	 * 查询api文档相关接口总数
	 * @param docId
	 * @param moduleId
	 * @return
	 */
	int countByDocId(@Param("docId")Long docId,@Param("moduleId")Long moduleId,
			@Param("name")String name,@Param("path")String path,@Param("description")String description);
	
	/**
	 * 获取接口详细信息
	 * @param interId
	 * @return
	 */
	Map getInfoByDocId(@Param("docId")Long docId,@Param("interId")Long interId);
	
	/**
	 * 
			*@name 获取接口信息
			*@Description  
			*@CreateDate 2016年5月15日上午10:37:18
	 */
	Inter getByDocId(@Param("docId")Long docId,@Param("interId")Long interId);
	
	/**
	 * 
			*@name 根据id和文档id更新
			*@Description  
			*@CreateDate 2015年9月9日上午11:36:54
	 */
	void updateByDocId(Inter inter);
	
	/**
	 * 
			*@name 根据id和文档id删除
			*@Description  
			*@CreateDate 2015年9月9日上午11:37:21
	 */
	void deleteByDocId(@Param("docId")Long docId,@Param("interId")Long interId);
	
	/**
	 * 
			*@name 根据请求url和请求方式查询接口信息
			*@Description  
			*@CreateDate 2015年10月10日下午8:51:50
	 */
	Inter getByPathAndMethod(@Param("docId")Long docId,@Param("path")String path,@Param("method")ReqMethod method);
	
	/**
	 * 
			*@name 查询未初始化路径正则表达式列表
			*@Description  
			*@CreateDate 2016年10月28日下午3:28:34
	 */
	List<Inter> listForPathRegex(@Param("pageSize")int pageSize);
	
	/**
	 * 
			*@name 更新路径正则表达式
			*@Description  
			*@CreateDate 2016年10月28日下午3:38:12
	 */
	void updateForPathRegex(@Param("interId")Long interId,@Param("pathRegex")String pathRegex);
	
	/**
	 * 
			*@name 指定权重之间全部加上指定步长
			*@Description  
			*@CreateDate 2017年3月23日下午5:09:54
	 */
	void buildSortWeight(@Param("docId")Long docId,@Param("moduleId")Long moduleId,
							@Param("minSortWeight")int minSortWeight,@Param("maxSortWeight")int maxSortWeight,@Param("step")int step);
	
	/**
	 * 
			*@name 获取最大权重值
			*@Description  
			*@CreateDate 2017年3月24日下午4:49:22
	 */
	int getMaxSortWeight(@Param("docId")Long docId,@Param("moduleId")Long moduleId);
	
	/**
	 * 
			*@name 更新接口名称
			*@Description  
			*@CreateDate 2017年3月26日下午4:27:40
	 */
	void updateName(@Param("docId")Long docId,@Param("interId")Long interId,@Param("name")String name);
	
	/**
	 * 
			*@name 更新排序权重
			*@Description  
			*@CreateDate 2017年4月5日下午2:39:23
	 */
	void updateSortWeight(@Param("docId")Long docId,@Param("interId")Long interId,@Param("sortWeight")int sortWeight);
	
	/**
	 * 
			*@name 删除接口模块
			*@Description  
			*@CreateDate 2017年3月26日下午4:39:15
	 */
	void updateForDelModule(@Param("docId")Long docId,@Param("moduleId")Long moduleId);
	
	/**
	 * 
			*@name 查询各个模块下接口总数
			*@Description  
			*@CreateDate 2017年4月12日下午5:48:52
	 */
	List<Map> countByModule(@Param("docId")Long docId);
	
	/**
	 * 
			*@name 查询文档视图关联的接口列表
			*@Description  
			*@CreateDate 2017年7月19日下午6:35:03
	 */
	List<Inter> listByViewId(@Param("viewId")Long viewId,@Param("condition")String condition);
}
