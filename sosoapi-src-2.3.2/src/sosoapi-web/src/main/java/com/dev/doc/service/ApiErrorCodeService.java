package com.dev.doc.service;

import java.util.List;
import java.util.Map;

import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;
import com.dev.doc.entity.ApiErrorCode;
import com.dev.doc.vo.ApiErrorCodeInfo;

/**
 * 
		* <p>Title: api错误码</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年4月16日上午8:52:36</p>
 */
public interface ApiErrorCodeService extends BaseMybatisService<ApiErrorCode, Long>{
	/**
	 * 
			*@name 查询文档关联的错误码
			*@Description  
			*@CreateDate 2017年4月16日上午8:57:36
	 */
	List<ApiErrorCode> listByDocId(Long docId,Long moduleId,String condition);
	
	/**
	 * 
			*@name 查询文档关联的错误码
			*@Description  
			*@CreateDate 2017年4月16日上午8:57:36
	 */
	List<ApiErrorCodeInfo> listInfoByDocId(Long docId,Long moduleId,String condition);
	
	/**
	 * 
			*@name 根据id和文档id进行更新
			*@Description  
			*@CreateDate 2015年9月9日上午10:53:49
	 */
	void updateByDocId(ApiErrorCode apiErrorCode);
	
	/**
	 * 
			*@name 获取错误码信息
			*@Description  
			*@CreateDate 2017年3月24日下午4:10:03
	 */
	ApiErrorCode getByDocId(Long docId,Long codeId);
	
	/**
	 * 
			*@name 根据id和文档id进行删除
			*@Description  
			*@CreateDate 2015年9月9日上午11:13:24
	 */
	void deleteByDocId(Long docId,Long codeId);
	
	/**
	 * 
			*@name 指定权重之间全部加上指定步长
			*@Description  
			*@CreateDate 2017年3月23日下午5:09:54
	 */
	void buildSortWeight(Long docId,Long moduleId,
							int minSortWeight,int maxSortWeight,int step);
	
	/**
	 * 
			*@name 获取最大的排序权重
			*@Description  
			*@CreateDate 2017年3月30日下午3:35:26
	 */
	int getMaxSortWeight(Long docId,Long moduleId);
	
	/**
	 * 
			*@name 更新排序权重
			*@Description  
			*@CreateDate 2017年4月5日下午2:39:23
	 */
	void updateSortWeight(Long docId,Long codeId,int sortWeight);
	
	/**
	 * 
			*@name 查询视图关联的错误码信息
			*@Description  
			*@CreateDate 2017年7月19日下午6:17:38
	 */
	List<ApiErrorCodeInfo> listInfoByViewId(Long viewId);
	
	/**
	 * 
			*@name 查询各个模块下错误码总数
			*@Description  key为moduleId，value为该模块下接口总数
			*@CreateDate 2017年4月12日下午5:48:52
	 */
	Map<Long, Integer> countByModule(Long docId);
	
	/**
	 * 
			*@name 查询指定模块下的错误码列表
			*@Description  
			*@CreateDate 2017年4月12日下午6:23:41
	 */
	List<ApiErrorCode> listByModuleId(Long docId,Long moduleId,Pager pager);
	
	/**
	 * 
			*@name 查询指定模块下的错误码总数
			*@Description  
			*@CreateDate 2017年4月12日下午10:53:53
	 */
	int countByModuleId(Long docId,Long moduleId);
}
