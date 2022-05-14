package com.dev.book.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dev.base.mybatis.dao.BaseMybatisDao;
import com.dev.book.entity.BookChapter;

/**
 * 
		* <p>Title: 书籍章节</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年11月20日下午12:32:20</p>
 */
public interface BookChapterDao extends BaseMybatisDao<BookChapter,Long> {
	/**
	 * 
			*@name 查询指定书籍所有目录
			*@Description  
			*@CreateDate 2017年11月23日下午5:38:35
	 */
	List<BookChapter> listByBookId(@Param("bookId")Long bookId,@Param("parentId")Long parentId,@Param("condition")String condition);
	
	/**
	 * 
			*@name 指定权重之间全部加上指定步长
			*@Description  
			*@CreateDate 2017年3月23日下午5:09:54
	 */
	void buildSortWeight(@Param("bookId")Long bookId,@Param("parentId")Long parentId,@Param("minSortWeight")int minSortWeight,
								@Param("maxSortWeight")int maxSortWeight,@Param("step")int step);
	
	/**
	 * 
			*@name 获取最大的排序权重
			*@Description  
			*@CreateDate 2017年3月30日下午3:35:26
	 */
	int getMaxSortWeight(@Param("bookId")Long bookId,@Param("parentId")Long parentId);
	
	/**
	 * 
			*@name 更新排序权重
			*@Description  
			*@CreateDate 2017年4月5日下午2:39:23
	 */
	void updateSortWeight(@Param("bookId")Long bookId,@Param("chapterId")Long chapterId,@Param("sortWeight")int sortWeight);
	
	/**
	 * 
			*@name 获取章节信息
			*@Description  
			*@CreateDate 2017年11月24日上午11:03:35
	 */
	BookChapter getByBookId(@Param("bookId")Long bookId,@Param("chapterId")Long chapterId);
	
	/**
	 * 
			*@name 删除章节
			*@Description  
			*@CreateDate 2017年11月24日下午5:02:18
	 */
	void delByBookId(@Param("bookId")Long bookId,@Param("chapterId")Long chapterId);
	
	/**
	 * 
			*@name 重置父章节
			*@Description  
			*@CreateDate 2017年11月24日下午5:06:18
	 */
	void updateParentId(@Param("bookId")Long bookId,@Param("oldParentId")Long oldParentId,@Param("newParentId")Long newParentId);
	
	/**
	 * 
			*@name 重命名章节标题
			*@Description  
			*@CreateDate 2017年11月24日下午5:02:18
	 */
	void renameByBookId(@Param("bookId")Long bookId,@Param("chapterId")Long chapterId,@Param("title")String title);
}
