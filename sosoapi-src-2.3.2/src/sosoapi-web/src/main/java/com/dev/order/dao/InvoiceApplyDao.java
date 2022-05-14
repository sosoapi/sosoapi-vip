package com.dev.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dev.base.enums.DealStatus;
import com.dev.base.enums.InvoiceType;
import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;
import com.dev.order.entity.InvoiceApply;

/**
 * 
		* <p>Title: 发票申请服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年11月20日上午11:22:54</p>
 */
public interface InvoiceApplyDao extends BaseMybatisDao<InvoiceApply,Long> {
	/**
	 * 
			*@name 查询列表
			*@Description  
			*@CreateDate 2015年9月12日上午11:33:40
	 */
	List<Map> listByUserId(@Param("userId")Long userId,@Param("email")String email,@Param("companyName")String companyName,
							@Param("dealStatus")DealStatus dealStatus,@Param("type")InvoiceType type,
							@Param("pager")Pager pager);
	
	/**
	 * 
			*@name 查询总数
			*@Description  
			*@CreateDate 2015年9月12日上午11:41:56
	 */
	int countByUserId(@Param("userId")Long userId,@Param("email")String email,@Param("companyName")String companyName,
						@Param("dealStatus")DealStatus dealStatus,@Param("type")InvoiceType type);
	
	/**
	 * 
			*@name 删除
			*@Description  
			*@CreateDate 2017年11月20日下午4:31:57
	 */
	void delByUserId(@Param("applyId")Long applyId,@Param("userId")Long userId);
	
	/**
	 * 
			*@name 获取详情
			*@Description  
			*@CreateDate 2017年11月20日下午4:31:57
	 */
	InvoiceApply getByUserId(@Param("applyId")Long applyId,@Param("userId")Long userId);
	
	/**
	 * 
			*@name 提交申请
			*@Description  
			*@CreateDate 2017年11月21日上午11:32:44
	 */
	void submitByUserId(@Param("applyId")Long applyId,@Param("userId")Long userId);
	
	/**
	 * 
			*@name 审批
			*@Description  
			*@CreateDate 2017年11月21日上午11:36:14
	 */
	void approve(InvoiceApply apply);
	
	/**
	 * 
			*@name 更新
			*@Description  
			*@CreateDate 2017年11月21日下午4:39:22
	 */
	void updateByUserId(InvoiceApply apply);
	
	/**
	 * 
			*@name 查询处理中的记录数
			*@Description  
			*@CreateDate 2017年11月21日下午5:26:55
	 */
	int countForDealing(@Param("userId")Long userId);
}
