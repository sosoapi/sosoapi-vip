package com.dev.doc.service;

import java.util.List;
import java.util.Map;

import com.dev.base.enums.ReqMethod;
import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;
import com.dev.doc.entity.Inter;
import com.dev.doc.vo.InterInfo;

/**
 * 
		* <p>Title: 接口相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:38:27</p>
 */
public interface InterService extends BaseMybatisService<Inter, Long>{
	/**
	 * 
			*@name 获取所有接口
			*@Description  
			*@CreateDate 2015年8月15日下午2:23:41
	 */
	List<Inter> listAllByDocId(Long docId,Long moduleId,Boolean deprecated,String condition);
	
	/**
	 * 
			*@name 查询api文档相关接口列表
			*@Description  
			*@CreateDate 2015年8月6日下午3:05:46
	 */
	List<Inter> listByDocId(Long docId,Long moduleId,String name,
								String path,String description,Pager pager);
	
	/**
	 * 查询api文档相关接口总数
	 * @param docId
	 * @param moduleId
	 * @return
	 */
	int countByDocId(Long docId,Long moduleId,String name,String path,String description);
	
	/**
	 * 获取接口详细信息
	 * @param interId
	 * @return
	 */
	InterInfo getInfoByDocId(Long docId,Long interId);
	
	/**
	 * 
			*@name 新增接口详情，包括接口信息，请求参数，请求响应
			*@Description  
			*@CreateDate 2017年4月5日上午11:35:59
	 */
	Inter addDetailByDocId(Long docId,InterInfo interInfo);
	
	/**
	 * 
			*@name 更新接口详情，包括接口信息，请求参数，请求响应
			*@Description  
			*@CreateDate 2017年4月5日上午11:35:59
	 */
	void updateDetailByDocId(Long docId,Long interId,InterInfo interInfo);
	
	/**
	 * 
			*@name 获取接口信息
			*@Description  
			*@CreateDate 2016年5月15日上午10:37:18
	 */
	Inter getByDocId(Long docId,Long interId);
	
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
	void deleteByDocId(Long docId,Long interId);
	
	/**
	 * 
			*@name 根据请求url和请求方式查询接口信息
			*@Description  
			*@CreateDate 2015年10月10日下午8:51:50
	 */
	Inter getByPathAndMethod(Long docId,String path,ReqMethod method);
	
	/**
	 * 
			*@name 获取文档对应的接口
			*@Description  
			*@CreateDate 2016年10月26日下午4:37:50
	 */
	List<Inter> listForMockByDocId(Long docId,ReqMethod method);
	
	/**
	 * 
			*@name 指定权重之间全部加上指定步长
			*@Description  
			*@CreateDate 2017年3月23日下午5:09:54
	 */
	void buildSortWeight(Long docId,Long moduleId,int minSortWeight,int maxSortWeight,int step);
	
	/**
	 * 
			*@name 获取最大权重值
			*@Description  
			*@CreateDate 2017年3月24日下午4:49:22
	 */
	int getMaxSortWeight(Long docId,Long moduleId);
	
	/**
	 * 
			*@name 更新接口名称
			*@Description  
			*@CreateDate 2017年3月24日下午5:57:35
	 */
	void updateName(Long docId,Long interId,String name);
	
	/**
	 * 
			*@name 删除接口模块
			*@Description  
			*@CreateDate 2017年3月26日下午4:39:15
	 */
	void updateForDelModule(Long docId,Long moduleId);
	
	/**
	 * 
			*@name 更新排序权重
			*@Description  
			*@CreateDate 2017年4月5日下午2:39:23
	 */
	void updateSortWeight(Long docId,Long interId,int sortWeight);
	
	/**
	 * 
			*@name 查询各个模块下接口总数
			*@Description  key为moduleId，value为该模块下接口总数
			*@CreateDate 2017年4月12日下午5:48:52
	 */
	Map<Long, Integer> countByModule(Long docId);
	
	/**
	 * 
			*@name 查询指定模块下的接口列表
			*@Description  
			*@CreateDate 2017年4月12日下午6:23:41
	 */
	List<Inter> listByModuleId(Long docId,Long moduleId,Pager pager);
	
	/**
	 * 
			*@name 查询指定模块下的接口总数
			*@Description  
			*@CreateDate 2017年4月12日下午10:53:53
	 */
	int countByModuleId(Long docId,Long moduleId);
	
	/**
	 * 
			*@name 查询文档视图关联的接口列表
			*@Description  
			*@CreateDate 2017年7月19日下午6:35:03
	 */
	List<Inter> listByViewId(Long viewId,String condition);
}
