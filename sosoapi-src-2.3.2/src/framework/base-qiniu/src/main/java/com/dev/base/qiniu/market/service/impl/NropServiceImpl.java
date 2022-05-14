package com.dev.base.qiniu.market.service.impl;

import org.springframework.stereotype.Service;

import com.dev.base.json.JsonUtils;
import com.dev.base.qiniu.market.resp.NropResp;
import com.dev.base.qiniu.market.service.NropService;
import com.dev.base.utils.HttpClientUtils;

/**
 * 
		* <p>Title: 鉴黄服务</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年12月31日下午5:03:18</p>
 */
@Service
public class NropServiceImpl implements NropService{
	@Override
	public NropResp nrop(String imgUrl){
		String result = HttpClientUtils.doGet(imgUrl + "?nrop", null);
		return JsonUtils.toObject(result, NropResp.class);
	}
}
