package com.dev.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dev.admin.entity.SysMsg;
import com.dev.base.enums.MsgType;
import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.base.util.Pager;

/**
 * 
		* <p>Title: 系统消息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月30日下午3:24:49</p>
 */
public interface SysMsgDao extends BaseMybatisDao<SysMsg,Long> {
	/**
	 * 
			*@name 查询系统消息列表
			*@Description  
			*@CreateDate 2015年9月12日上午11:33:40
	 */
	List<Map> listAll(@Param("title")String title,@Param("msgType")MsgType msgType,
			@Param("roleId")Long roleId,@Param("pager")Pager pager);
	
	/**
	 * 
			*@name 查询系统消息总数
			*@Description  
			*@CreateDate 2015年9月12日上午11:41:56
	 */
	int countAll(@Param("title")String title,@Param("msgType")MsgType msgType,
			@Param("roleId")Long roleId);
}
