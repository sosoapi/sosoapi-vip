package com.dev.prod.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.base.constant.AppConstants;
import com.dev.base.constant.CfgConstants;
import com.dev.base.controller.BaseController;
import com.dev.base.enums.EnableStatus;
import com.dev.base.enums.ProdType;
import com.dev.base.exception.code.ErrorCode;
import com.dev.base.util.Pager;
import com.dev.base.util.WebPaginate;
import com.dev.base.util.WebUtil;
import com.dev.base.utils.FormatUtils;
import com.dev.base.utils.ValidateUtils;
import com.dev.prod.entity.Goods;
import com.dev.prod.service.DownloadRecordService;
import com.dev.prod.service.GoodsService;
import com.dev.user.service.UserBasicService;

/**
 * 
		* <p>Title: 商品相关</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2016年3月28日上午10:24:02</p>
 */
@Controller
public class GoodsController extends BaseController{
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private UserBasicService userBasicService;
	
	@Autowired
	private DownloadRecordService recordService;
	
	/**
	 * 
			*@name 查看商品列表
			*@Description  
			*@CreateDate 2017年1月23日下午2:35:50
	 */
	@RequestMapping("/pass/goods/list.htm")
	public String list(HttpServletRequest request,Model model,ProdType type,
							String name,Integer pageNumber,Integer pageSize){
		Pager pager = new Pager(pageNumber, pageSize);
		List<Goods> list = goodsService.list(type, name, EnableStatus.on,EnableStatus.on, pager);
		int count = goodsService.count(type, name, EnableStatus.on,EnableStatus.on);
		pager.setTotalCount(count);
		pager.setList(list);

		model.addAttribute("pager", pager);
		model.addAttribute("paginate",WebPaginate.build(request, pageNumber, pageSize, count));
		model.addAttribute("enableDownload", enableDownload(request));
		
		return "prod/goodsList";
	}
	
	//判断是否允许直接下载
	private boolean enableDownload(HttpServletRequest request){
		boolean result = false;
		Long userId = getUserId(request);
		if (userId != null) {
			Long roleId = userBasicService.getById(userId).getRoleId();
			//vip用户或管理员可直接下载
			result = FormatUtils.isEqual(roleId, CfgConstants.ROLE_ID_VIP)
					|| FormatUtils.isEqual(roleId, CfgConstants.ROLE_ID_ADMIN);
		}
		
		return result;
	}
	
	/**
	 * 
			*@name 查看商品详情
			*@Description  
			*@CreateDate 2017年1月23日下午2:35:50
	 */
	@RequestMapping("/pass/goods/info.htm")
	public String getInfo(HttpServletRequest request,Model model,Long goodsId){
		ValidateUtils.notNull(goodsId, ErrorCode.SYS_001,"商品id不能为空");
		
		Goods goods = goodsService.getById(goodsId);
		model.addAttribute("goodsInfo",goods);
		
		return "prod/goodsInfo";
	}
	
	/**
	 * 
	 		*@name 查看商品详情
			*@Description  
			*@CreateDate 2017年1月23日下午2:35:50
	 */
	@RequestMapping("/auth/goods/download.htm")
	public void download(HttpServletRequest request,HttpServletResponse response,Long goodsId) throws IOException{
		ValidateUtils.notNull(goodsId, ErrorCode.SYS_001,"商品id不能为空");
		Goods goods = goodsService.getById(goodsId);
		ValidateUtils.notNull(goods, ErrorCode.SYS_001,"商品不存在");
		ValidateUtils.isTrue(enableDownload(request), ErrorCode.SYS_001,"请先购买后才可以下载");
		
		//添加下载记录
		recordService.add(getUserId(request), WebUtil.getClientIp(request), goods);
		
		OutputStream outputStream = null;
        try {
        	response.setCharacterEncoding(AppConstants.DEF_CHARSET);
            response.setContentType("application/octet-stream;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(goods.getFileName(), AppConstants.DEF_CHARSET));
			
			outputStream = response.getOutputStream();
	        outputStream.write(FileUtils.readFileToByteArray(new File(CfgConstants.GOODS_DOWNLOAD_BASE_URL + goods.getDownloadUrl())));
	        outputStream.flush();
		} 
        catch (Exception e) {
        	e.printStackTrace();
        }
        finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}
}
