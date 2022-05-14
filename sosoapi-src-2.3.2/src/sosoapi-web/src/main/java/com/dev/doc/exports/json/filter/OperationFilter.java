package com.dev.doc.exports.json.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
		* <p>Title: 导出swagger官方格式</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2017年9月13日下午4:46:04</p>
 */
@JsonIgnoreProperties({"sortWeight","devStatus","devStatusName","showDevStatus",
						"label","developer","modifyDate"})
public interface OperationFilter {

}
