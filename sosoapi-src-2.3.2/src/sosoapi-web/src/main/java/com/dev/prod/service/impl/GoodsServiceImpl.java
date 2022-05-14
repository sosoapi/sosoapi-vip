package com.dev.prod.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.dev.base.constant.CfgConstants;
import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.ProdType;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;
import com.dev.base.utils.DateUtil;
import com.dev.base.utils.ValidateUtils;
import com.dev.prod.dao.GoodsDao;
import com.dev.prod.entity.Goods;
import com.dev.prod.service.GoodsService;

/**
 * 
		* <p>Title: 商品相关服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年1月23日上午10:45:40</p>
 */
@Service
public class GoodsServiceImpl extends BaseMybatisServiceImpl<Goods,Long,GoodsDao>
										implements GoodsService{

	@Override
	public List<Goods> list(ProdType type, String name, EnableStatus status,EnableStatus pubStatus, Pager pager) {
		return getMybatisDao().list(type, name, status,pubStatus, pager);
	}

	@Override
	public int count(ProdType type, String name, EnableStatus status,EnableStatus pubStatus) {
		return getMybatisDao().count(type, name, status,pubStatus);
	}

	@Override
	public void copy(Long goodsId) {
		Goods goods = getMybatisDao().getById(goodsId);
		ValidateUtils.notNull(goods, ErrorCode.SYS_007);
		Date now = DateUtil.getNow();
		Goods copy = (Goods)cloneBean(goods);
		copy.setId(null);
		copy.setCreateDate(now);
		copy.setModifyDate(now);
		copy.setPubDate(now);
		copy.setStatus(EnableStatus.off);
		copy.setPubStatus(EnableStatus.off);
		copy.setName(copy.getName() + CfgConstants.COPY_FLAG);
		getMybatisDao().add(copy);
	}
	
	//复制对象
	private Object cloneBean(Object object){
		Object result = null;
		try {
			result = BeanUtils.cloneBean(object);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
