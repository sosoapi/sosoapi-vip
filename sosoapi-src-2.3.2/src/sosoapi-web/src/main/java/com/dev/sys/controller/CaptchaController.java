package com.dev.sys.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.base.controller.BaseController;
import com.dev.base.util.WebUtil;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.text.impl.RandomTextCreator;
import com.google.code.kaptcha.util.Config;

/**
 * 
		* <p>Title: 验证码</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月25日上午10:13:06</p>
 */
@Controller
@RequestMapping("/captcha")
public class CaptchaController extends BaseController{
	@Autowired
	private Producer captchaProducer;
	
	@Autowired
	private Config captchaConfig;
	
	private static RandomTextCreator randomTextCreator = null;
	
	/**
	 * 
	 		*@name 生成验证码
			*@Description  
			*@CreateDate 2015年8月25日上午10:13:56
	 */
	@RequestMapping("/build.htm")
	public void build(HttpServletRequest request,HttpServletResponse response,String oper) throws Exception{
		String code = captchaProducer.createText();
//		String code = getRandomTextCreator().getText(4, 6);
		WebUtil.setSessionAttr(request, oper, code);
		
		BufferedImage image = captchaProducer.createImage(code);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "JPEG", response.getOutputStream());
		out.flush();
		out.close();
	}
	
	//获取文本渲染
	private RandomTextCreator getRandomTextCreator(){
		if (randomTextCreator == null) {
			randomTextCreator = new RandomTextCreator(captchaConfig);
		}
		return randomTextCreator;
	}
}
