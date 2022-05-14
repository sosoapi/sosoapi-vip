package com.dev.order.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.base.enums.DealStatus;
import com.dev.base.enums.InvoiceType;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;
import com.dev.base.utils.DateUtil;
import com.dev.base.utils.ValidateUtils;
import com.dev.order.dao.InvoiceApplyDao;
import com.dev.order.entity.InvoiceApply;
import com.dev.order.service.InvoiceApplyService;
import com.dev.order.vo.InvoiceApplyInfo;
import com.dev.user.service.UserDetailService;

/**
 * 
		* <p>Title: 发票申请服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年11月20日上午11:22:14</p>
 */
@Service
public class InvoiceApplyServiceImpl extends BaseMybatisServiceImpl<InvoiceApply,Long,InvoiceApplyDao>
										implements InvoiceApplyService{
	@Autowired
	private UserDetailService userDetailService;
	
	@Override
	public List<InvoiceApplyInfo> listByUserId(Long userId, String email, 
									String companyName, DealStatus dealStatus,
										InvoiceType type, Pager pager) {
		email = getLikeExpr(email);
		companyName = getLikeExpr(companyName);
		
		List<Map> applyList = getMybatisDao().listByUserId(userId, email, companyName, dealStatus, type, pager);
		InvoiceApplyInfo applyInfo = null;
		List<InvoiceApplyInfo> result = new ArrayList<InvoiceApplyInfo>();
		for (Map info : applyList) {
			applyInfo = new InvoiceApplyInfo();
			applyInfo.setId((Long)info.get("applyId"));
			applyInfo.setUserId((Long)info.get("userId"));
			applyInfo.setUserEmail((String)info.get("userEmail"));
			applyInfo.setType(InvoiceType.valueOf((String)info.get("type")));
			applyInfo.setProductName((String)info.get("productName"));
			applyInfo.setTotalAmount((Integer)info.get("totalAmount"));
			applyInfo.setCompanyName((String)info.get("companyName"));
			applyInfo.setDealStatus(DealStatus.valueOf((String)info.get("dealStatus")));
			applyInfo.setDealDate((Date)info.get("dealDate"));
			applyInfo.setApplyDate((Date)info.get("modifyDate"));
			
			result.add(applyInfo);
		}
		
		return result;
	}

	@Override
	public int countByUserId(Long userId, String email, String companyName, 
			DealStatus dealStatus, InvoiceType type) {
		email = getLikeExpr(email);
		companyName = getLikeExpr(companyName);
		
		return getMybatisDao().countByUserId(userId, email, companyName, dealStatus, type);
	}

	@Override
	public void delByUserId(Long applyId, Long userId) {
		InvoiceApply oldApply = getByUserId(applyId, userId);
		ValidateUtils.notNull(oldApply, ErrorCode.SYS_007);
		ValidateUtils.isTrue(isValidChange(oldApply), ErrorCode.INVOICE_001);
		
		getMybatisDao().delByUserId(applyId, userId);
	}

	@Override
	public InvoiceApply getByUserId(Long applyId, Long userId) {
		return getMybatisDao().getByUserId(applyId, userId);
	}

	@Override
	public InvoiceApply addByUserId(Long userId, InvoiceApply apply) {
		ValidateUtils.isTrue(!isExistDealing(userId), ErrorCode.INVOICE_003);
		
		apply.setUserId(userId);
		if (apply.getDealStatus() == null) {
			apply.setDealStatus(DealStatus.undeal);
		}
		getMybatisDao().add(apply);
		
		return apply;
	}

	//超管编辑
	@Override
	public InvoiceApply update(InvoiceApply apply) {
		InvoiceApply oldApply = getById(apply.getId());
		ValidateUtils.notNull(oldApply, ErrorCode.SYS_007);
		ValidateUtils.isTrue(isValidChange(oldApply), ErrorCode.INVOICE_001);
		getMybatisDao().update(apply);
		
		return apply;
	}

	@Override
	public void updateByUserId(Long userId, InvoiceApply apply) {
		InvoiceApply oldApply = getByUserId(apply.getId(), userId);
		ValidateUtils.notNull(oldApply, ErrorCode.SYS_007);
		ValidateUtils.isTrue(isValidChange(oldApply), ErrorCode.INVOICE_001);
		
		getMybatisDao().update(apply);
	}
	
	@Override
	public void submitByUserId(Long applyId, Long userId) {
		InvoiceApply oldApply = getByUserId(applyId, userId);
		ValidateUtils.notNull(oldApply, ErrorCode.SYS_007);
		ValidateUtils.isTrue(isValidChange(oldApply), ErrorCode.INVOICE_001);
		ValidateUtils.isTrue(userDetailService.validInvoiceApply(oldApply.getUserId(), oldApply.getTotalAmount()), ErrorCode.INVOICE_004);
		
		getMybatisDao().submitByUserId(applyId, userId);
	}

	@Override
	public void approve(Long dealUserId, InvoiceApply apply) {
		InvoiceApply oldApply = getMybatisDao().getByUserId(apply.getId(),null);
		ValidateUtils.notNull(oldApply, ErrorCode.SYS_007);
		
		apply.setDealUserId(dealUserId);
		apply.setDealDate(DateUtil.getNow());
		getMybatisDao().approve(apply);
		
		//处理审批通过更新已开发票金额
		if (apply.getDealStatus() == DealStatus.dealed) {
			ValidateUtils.isTrue(userDetailService.validInvoiceApply(oldApply.getUserId(), oldApply.getTotalAmount()), ErrorCode.INVOICE_004);
			userDetailService.updateAmountInfo(oldApply.getUserId(), null, oldApply.getTotalAmount());
		}
	}
	
	//判断是否允许变更
	private boolean isValidChange(InvoiceApply apply){
		if (apply == null) {
			return false;
		}
		
		DealStatus status = apply.getDealStatus();
		return status == DealStatus.undeal 
				|| status == DealStatus.reject;
	}

	@Override
	public boolean isExistDealing(Long userId) {
		return getMybatisDao().countForDealing(userId) > 0;
	}
}
