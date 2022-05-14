package com.dev.prod.service;

import java.util.Date;
import java.util.List;

import com.dev.base.mybatis.service.BaseMybatisService;
import com.dev.base.util.Pager;
import com.dev.prod.entity.DownloadRecord;
import com.dev.prod.entity.Goods;
import com.dev.prod.vo.DownloadRecordInfo;

/**
 * 
		* <p>Title: 下载记录</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年3月3日下午6:06:40</p>
 */
public interface DownloadRecordService extends BaseMybatisService<DownloadRecord, Long>{
	/**
	 * 
			*@name 查询记录列表
			*@Description  
			*@CreateDate 2017年2月13日下午5:10:48
	 */
	List<DownloadRecordInfo> listAll(Date dateStart,Date dateEnd,
						String fileName,String email,Pager pager);
	
	/**
	 * 
			*@name 查询记录总数
			*@Description  
			*@CreateDate 2017年2月13日下午5:11:01
	 */
	int countAll(Date dateStart,Date dateEnd,
			String fileName,String email);
	
	/**
	 * 
			*@name 添加记录
			*@Description  
			*@CreateDate 2017年3月3日下午6:28:36
	 */
	void add(Long userId,String ip,Goods goods);
}
