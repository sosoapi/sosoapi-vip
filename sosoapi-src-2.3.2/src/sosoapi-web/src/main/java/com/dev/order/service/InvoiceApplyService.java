package com.dev.order.service;

import java.util.List;

import com.dev.base.enums.DealStatus;
import com.dev.base.enums.InvoiceType;
import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;
import com.dev.order.entity.InvoiceApply;
import com.dev.order.vo.InvoiceApplyInfo;

/**
 * 
		* <p>Title: 发票申请服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年11月20日上午11:22:31</p>
 */
public interface InvoiceApplyService extends BaseMybatisService<InvoiceApply, Long>{
	/**
	 * 
			*@name 查询列表
			*@Description  
			*@CreateDate 2015年9月12日上午11:33:40
	 */
	List<InvoiceApplyInfo> listByUserId(Long userId,String email,String companyName,
							DealStatus dealStatus,InvoiceType type,Pager pager);
	
	/**
	 * 
			*@name 查询总数
			*@Description  
			*@CreateDate 2015年9月12日上午11:41:56
	 */
	int countByUserId(Long userId,String email,String companyName,
						DealStatus dealStatus,InvoiceType type);
	
	/**
	 * 
			*@name 删除
			*@Description  
			*@CreateDate 2017年11月20日下午4:31:57
	 */
	void delByUserId(Long applyId,Long userId);
	
	/**
	 * 
			*@name 获取详情
			*@Description  
			*@CreateDate 2017年11月20日下午4:31:57
	 */
	InvoiceApply getByUserId(Long applyId,Long userId);
	
	/**
	 * 
			*@name 新增发票申请
			*@Description  
			*@CreateDate 2017年11月21日上午11:05:19
	 */
	InvoiceApply addByUserId(Long userId,InvoiceApply apply);
	
	/**
	 * 
			*@name 修改发票申请
			*@Description  
			*@CreateDate 2017年11月21日上午11:20:27
	 */
	void updateByUserId(Long userId,InvoiceApply apply);
	
	/**
	 * 
			*@name 提交申请
			*@Description  
			*@CreateDate 2017年11月21日上午11:32:44
	 */
	void submitByUserId(Long applyId,Long userId);
	
	/**
	 * 
			*@name 
			*@Description  
			*@CreateDate 2017年11月21日上午11:36:14
	 */
	void approve(Long dealUserId,InvoiceApply apply);
	
	/**
	 * 
			*@name 判断是否存在处理中的记录
			*@Description  
			*@CreateDate 2017年11月21日下午5:28:31
	 */
	boolean isExistDealing(Long userId);
}
