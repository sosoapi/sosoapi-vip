package com.dev.base.qiniu.market.service;

import com.dev.base.qiniu.market.resp.NropResp;

/**
 * 
		* <p>Title: 鉴黄服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年12月31日下午5:03:00</p>
 */
public interface NropService {
	/**
	 * 
			*@name 图片鉴黄
			*@Description  
			*@CreateDate 2015年12月31日下午5:03:08
	 */
	NropResp nrop(String imgUrl);
}
