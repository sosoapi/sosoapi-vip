package com.dev.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;
import com.dev.user.dao.UserDetailDao;
import com.dev.user.entity.UserDetail;
import com.dev.user.service.UserDetailService;
import com.dev.user.vo.UserInfo;

/**
 * 
		* <p>Title: 用户详细信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:40:48</p>
 */
@Service
public class UserDetailServiceImpl extends BaseMybatisServiceImpl<UserDetail,Long,UserDetailDao>
										implements UserDetailService{

	@Override
	public UserDetail getByUserId(Long userId) {
		return getMybatisDao().getByUserId(userId);
	}

	@Override
	public UserInfo getDetailByUserId(Long userId) {
		Map<String, Object> info = getMybatisDao().getDetailByUserId(userId);
		return parseDetailInfo(info);
	}
	
	@Override
	public UserInfo getDetailByEmail(String email) {
		Map<String, Object> info = getMybatisDao().getDetailByEmail(email);
		return parseDetailInfo(info);
	}
	
	//组装用户详情
	private UserInfo parseDetailInfo(Map<String, Object> info){
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId((Long)info.get("userId"));
		userInfo.setEmail((String)info.get("email"));
		userInfo.setNickName((String)info.get("nickName"));
		userInfo.setHeadUrl((String)info.get("headUrl"));
		userInfo.setRoleId((Long)info.get("roleId"));
		userInfo.setRoleName((String)info.get("roleName"));
		userInfo.setHomeUrl((String)info.get("homeUrl"));
		userInfo.setValid((Boolean)info.get("valid"));
		userInfo.setLocked((Boolean)info.get("locked"));
		userInfo.setRegistDate((Date)info.get("registDate"));
		if (info.containsKey("feeAmount")) {
			userInfo.setFeeAmount((Integer)info.get("feeAmount"));
		}
		if (info.containsKey("invoiceAmount")) {
			userInfo.setInvoiceAmount((Integer)info.get("invoiceAmount"));
		}
		
		return userInfo;
	}

	@Override
	public List<UserInfo> listAll(String email, String nickName, 
									Boolean valid,Long roleId,Pager pager) {
		email = getLikeExpr(email);
		nickName = getLikeExpr(nickName);
		
		List<UserInfo> result = new ArrayList<UserInfo>();
		List<Map> infoList = getMybatisDao().listAll(email, nickName, valid,roleId, pager);
		for (Map info : infoList) {
			result.add(parseDetailInfo(info));
		}
		
		return result;
	}

	@Override
	public int countAll(String email, String nickName, Boolean valid,Long roleId) {
		email = getLikeExpr(email);
		nickName = getLikeExpr(nickName);
		
		return getMybatisDao().countAll(email, nickName, valid,roleId);
	}

	@Override
	public void updateAmountInfo(Long userId, Integer increaseFeeAmount, Integer increaseInvoiceAmount) {
		if (increaseFeeAmount == null && increaseInvoiceAmount == null) {
			return ;
		}
		
		getMybatisDao().updateAmountInfo(userId, increaseFeeAmount, increaseInvoiceAmount);
	}

	@Override
	public boolean validInvoiceApply(Long userId, int invoiceAmount) {
		return getAvailableInvoiceAmount(userId) >= invoiceAmount;
	}

	@Override
	public int getAvailableInvoiceAmount(Long userId) {
		UserDetail detail = getByUserId(userId);
		return detail.getFeeAmount() - detail.getInvoiceAmount();
	}
}
