package com.dev.prod.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dev.base.enums.ProdType;
import com.dev.base.mybatis.service.impl.BaseMybatisServiceImpl;
import com.dev.base.util.Pager;
import com.dev.prod.dao.DownloadRecordDao;
import com.dev.prod.entity.DownloadRecord;
import com.dev.prod.entity.Goods;
import com.dev.prod.service.DownloadRecordService;
import com.dev.prod.vo.DownloadRecordInfo;

/**
 * 
		* <p>Title: 下载记录</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年3月3日下午6:06:40</p>
 */
@Service
public class DownloadRecordServiceImpl extends BaseMybatisServiceImpl<DownloadRecord,Long,DownloadRecordDao>
										implements DownloadRecordService{

	@Override
	public List<DownloadRecordInfo> listAll(Date dateStart, Date dateEnd, String fileName, String email, Pager pager) {
		fileName = getLikeExpr(fileName);
		email = getLikeExpr(email);
		List<Map> infoList = getMybatisDao().listAll(dateStart, dateEnd, fileName, email, pager);
		List<DownloadRecordInfo> result = new ArrayList<DownloadRecordInfo>();
		DownloadRecordInfo temp = null;
		for (Map info : infoList) {
			temp = new DownloadRecordInfo();
			temp.setId((Long)info.get("recordId"));
			temp.setUserId((Long)info.get("userId"));
			temp.setUserEmail((String)info.get("userEmail"));
			temp.setGoodsId((Long)info.get("goodsId"));
			temp.setFileName((String)info.get("fileName"));
			temp.setDownloadDate((Date)info.get("createDate"));
			temp.setDownloadIp((String)info.get("downloadIp"));
			temp.setGoodsName((String)info.get("goodsName"));
			temp.setGoodsType(ProdType.valueOf((String)info.get("goodsType")));
			temp.setGoodsDesc((String)info.get("goodsDesc"));
			
			result.add(temp);
		}
		
		return result;
	}

	@Override
	public int countAll(Date dateStart, Date dateEnd, String fileName, String email) {
		fileName = getLikeExpr(fileName);
		email = getLikeExpr(email);
		
		return getMybatisDao().countAll(dateStart, dateEnd, fileName, email);
	}

	@Override
	public void add(Long userId,String ip, Goods goods) {
		DownloadRecord record = new DownloadRecord();
		record.setDownloadIp(ip);
		record.setFileName(goods.getFileName());
		record.setGoodsDesc(goods.getDescription());
		record.setGoodsId(goods.getId());
		record.setGoodsName(goods.getName());
		record.setGoodsType(goods.getType());
		record.setUserId(userId);
		
		getMybatisDao().add(record);
	}
}
