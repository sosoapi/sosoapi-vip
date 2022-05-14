package com.dev.user.service;

import java.util.List;

import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;
import com.dev.user.entity.UserDetail;
import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: 用户详细信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:40:48</p>
 */
public interface UserDetailService extends BaseMybatisService<UserDetail, Long>{
	/**
	 * 
			*@name 查询用户详情信息
			*@Description  
			*@CreateDate 2015年8月8日上午11:16:46
	 */
	UserDetail getByUserId(Long userId);
	
	/**
	 * 
			*@name 查询用户详细信息
			*@Description  
			*@CreateDate 2015年9月12日上午10:59:16
	 */
	UserInfo getDetailByUserId(Long userId);
	
	/**
	 * 
			*@name 查询用户详细信息
			*@Description  
			*@CreateDate 2015年9月12日上午10:59:16
	 */
	UserInfo getDetailByEmail(String email);
	
	/**
	 * 
			*@name 查询所有用户详情
			*@Description  
			*@CreateDate 2015年9月12日上午11:33:40
	 */
	List<UserInfo> listAll(String email,String nickName,Boolean valid,Long roleId,Pager pager);
	
	/**
	 * 
			*@name 查询用户总数
			*@Description  
			*@CreateDate 2015年9月12日上午11:41:56
	 */
	int countAll(String email,String nickName,Boolean valid,Long roleId);
	
	/**
	 * 
			*@name 处理用户付费金额和已开发票金额信息
			*@Description  
			*@CreateDate 2017年11月21日下午5:37:56
	 */
	void updateAmountInfo(Long userId,Integer increaseFeeAmount,Integer increaseInvoiceAmount);
	
	/**
	 * 
			*@name 验证是否可以开指定金额的发票申请
			*@Description  
			*@CreateDate 2017年11月21日下午5:45:12
	 */
	boolean validInvoiceApply(Long userId,int invoiceAmount);
	
	/**
	 * 
			*@name 获取用户可开发票金额
			*@Description  
			*@CreateDate 2017年11月21日下午5:49:04
	 */
	int getAvailableInvoiceAmount(Long userId);
}
