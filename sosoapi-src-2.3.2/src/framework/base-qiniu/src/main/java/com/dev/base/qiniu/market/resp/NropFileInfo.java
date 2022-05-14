package com.dev.base.qiniu.market.resp;

/**
 * 
		* <p>Title: 文件相关信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年12月31日下午5:02:34</p>
 */
public class NropFileInfo {
	/** 介于0-1间的浮点数，表示该图像被识别为某个分类的概率值，概率越高、机器越肯定；您可以根据您的需求确定需要人工复审的界限。*/
	private float rate;
	
	/** 介于0-2间的整数，表示该图像被机器判定为哪个分类，分别对应： 0：色情； 1：性感； 2：正常*/
	private int label;
	
	/** 鉴别图片文件的文件名*/
	private String name;
	
	/** 是否需要人工复审该图片*/
	private String review;

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public int getLabel() {
		return label;
	}

	public void setLabel(int label) {
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
}
