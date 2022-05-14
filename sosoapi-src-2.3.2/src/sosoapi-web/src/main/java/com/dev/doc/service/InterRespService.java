package com.dev.doc.service;

import java.util.List;

import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.doc.entity.InterResp;

/**
 * 
		* <p>Title: 接口响应相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:38:27</p>
 */
public interface InterRespService extends BaseMybatisService<InterResp, Long>{
	/**
	 * 
			*@name 根据接口查询所有响应
			*@Description  
			*@CreateDate 2015年8月6日下午3:39:30
	 */
	List<InterResp> listAllByInterId(Long docId,Long interId);
	
	/**
	 * 
			*@name 根据id和文档id更新
			*@Description  
			*@CreateDate 2015年9月9日下午3:11:23
	 */
	void updateByDocId(InterResp interResp);
	
	/**
	 * 
			*@name 根据id和文档id删除
			*@Description  
			*@CreateDate 2015年9月9日下午3:11:59
	 */
	void deleteByDocId(Long docId,Long respId);
	
	/**
	 * 
			*@name 查询响应信息
			*@Description  
			*@CreateDate 2015年10月12日下午5:34:55
	 */
	InterResp getByDocId(Long docId,Long respId);
	
	/**
	 * 
			*@name 查询文档对应的所有请求响应列表
			*@Description  
			*@CreateDate 2015年10月11日上午12:21:48
	 */
	List<InterResp> listAllByDocId(Long docId);
	
	/**
	 * 
			*@name 删除接口相关的所有响应
			*@Description  
			*@CreateDate 2015年8月6日下午3:37:00
	 */
	void deleteByDocIdAndInterId(Long docId,Long interId);
	
	/**
	 * 
			*@name 查询接口指定响应,code不指定则为查询默认
			*@Description  
			*@CreateDate 2016年10月25日下午4:03:34
	 */
	InterResp getByInterIdAndCode(Long docId,Long interId,String code);
	
	/**
	 * 
			*@name 更新mock信息
			*@Description  
			*@CreateDate 2016年10月26日下午6:10:26
	 */
	void updateMockInfo(Long docId,Long respId,String mockData,String mockRule);
	
	/**
	 * 
			*@name 新增接口响应
			*@Description  
			*@CreateDate 2015年8月6日下午3:37:00
	 */
	void batchAdd(Long docId,Long interId,List<InterResp> list);
	
	/**
	 * 
			*@name 请求json格式转换为list
			*@Description  
			*@CreateDate 2017年4月5日上午11:31:32
	 */
	List<InterResp> buildRespList(Long docId,Long interId,String reqResp);
	
	/**
	 * 
			*@name 更新排序权重
			*@Description  
			*@CreateDate 2017年4月5日下午2:39:23
	 */
	void updateSortWeight(Long docId,Long respId,int sortWeight);
}
