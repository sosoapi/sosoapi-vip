package com.dev.prod.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;
import com.dev.prod.entity.DownloadRecord;

/**
 * 
		* <p>Title: 下载记录</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年3月3日下午6:06:40</p>
 */
public interface DownloadRecordDao extends BaseMybatisDao<DownloadRecord,Long> {
	/**
	 * 
			*@name 查询记录列表
			*@Description  
			*@CreateDate 2017年2月13日下午5:10:48
	 */
	List<Map> listAll(@Param("dateStart")Date dateStart,@Param("dateEnd")Date dateEnd,
						@Param("fileName")String fileName,@Param("email")String email,
						@Param("pager")Pager pager);
	
	/**
	 * 
			*@name 查询记录总数
			*@Description  
			*@CreateDate 2017年2月13日下午5:11:01
	 */
	int countAll(@Param("dateStart")Date dateStart,@Param("dateEnd")Date dateEnd,
			@Param("fileName")String fileName,@Param("email")String email);
	

}
