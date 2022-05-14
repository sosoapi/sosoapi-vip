<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>版本列表  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
	<!-- <link href="" rel="stylesheet" /> -->
	<!-- END PAGE LEVEL  STYLES -->
</head>
<body class="sticky-header">
	<!-- LEFT SECTION -->
	<shiro:user>
   		<jsp:include page="/jsp/user/controlMenu.jsp"/>
   	</shiro:user>
   	
   	<shiro:guest>
   		<jsp:include page="/jsp/common/leftEmptyMenu.jsp"/>
   	</shiro:guest>
	<!-- END LEFT SECTION -->
	
	<div class="body-content">
		<!-- TOP SECTION -->
		<jsp:include page="/jsp/common/top.jsp" />
	    <!-- END TOP SECTION -->
    
  		<!--body wrapper start-->
        <div id="wrapper">
        	<div class="content animate-panel">
        		<div class="row">
        			<div class="col-lg-12">
            			<div class="hpanel">
                			<div class="panel-heading">
			                    <div class="panel-tools">
			                        <a class="showhide"><i class="fa fa-chevron-up"></i></a>
			                        <a class="closebox"><i class="fa fa-times"></i></a>
			                    </div>
			                    <span>购买说明</span>
			                </div>
		                	<div class="panel-body">
		                		<ol>
		                			<li class="text-danger">无论是开源版本或专业版，只能部署公司内部或个人使用。请不要私自用于商业用途或传播，否则我们将追究你的法律责任！</li>
		                			<li>购买后即成为会员，可直接下载会员价为0的相关源码包，同时可加入vip用户群:
		                				<a href="https://jq.qq.com/?_wv=1027&k=45R5xqM" target="_blank" class="text-muted text-center">452964138</a>，
		                				该群会优先处理部署和使用过程中碰到的相关问题。
		                			</li>
		                			<li>任一源码包购买后不支持退款。</li>
		                			<li>部署前请先查看相关部署文档，位于sosoapi-web/doc目录下，同时包含数据库设计pdm，方便二次开发。</li>
		                			<li>源码环境：基于spring mvc + mybatis框架，jdk1.7，tomcat1.7，数据库为mysql5.5+，maven管理。</li>
		                			<li>源码包包含framework和sosoapi-web两个项目，其中framework为基础工具包，被sosoapi-web所依赖。</li>
		                			<li>数据库编码采用的是utf8mb4，数据库脚本位于sosoapi-web/db/version目录下，所有数据库脚本为增量脚本，初次部署需全部执行，升级请先备份数据库。</li>
		                			<li>注意：当前导出的swagger json为我们扩展过的格式，暂时无法与swagger editor兼容。</li>
		                			<li>购买后如果需要发票请直接联系运营人员，QQ号:3259073638</li>
		                			<!-- <li class="text-danger">因运营需要，专业版版本从5月1日开始购买费用将上调至￥2000，原vip用户服务不变。感谢理解和支持，谢谢。</li> -->
		                		</ol>
		                	</div>
		                </div>
				   	</div>
				</div>
				
        		<div class="row">
        			<div class="col-lg-12">
            			<div class="hpanel">
                			<div class="panel-heading">
			                   	<span>版本列表</span>
			                </div>
		                	<div class="panel-body">
		                		<div class="row">
        	    					<div class="col-lg-12">
										<table class="table table-hover table-bordered table-fixed">
			                        		<thead>
				                            	<tr>
				                                	<th class="table-index">#</th>
				                                	<th class="col-lg-3">名称</th>
				                                    <th class="col-lg-1">版本号</th>
				                                	<th class="col-lg-1">价格</th>
				                                	<th class="col-lg-1">会员价</th>
				                                    <th class="col-lg-1">版本说明</th>
				                                    <th class="col-lg-2">发布时间</th>
				                                    <th class="col-lg-3">操作</th>
				                               </tr>
				                            </thead>
			                            
		                                 	<tbody>
		                                 		<c:if test="${not empty pager.list}">
		                                 			<c:forEach items="${pager.list}" var="goodsInfo" varStatus="status">
		                                 				<tr>
				                                        	<td>${status.index + 1}</td>
				                                        	<td>${goodsInfo.name}</td>
				                                        	<td>${goodsInfo.description}</td>
				                                        	<td>
				                                        		<fmt:formatNumber value="${goodsInfo.price/100}" type="currency" pattern="￥0"/>
				                                        	</td>
				                                        	<td>
				                                        		<fmt:formatNumber value="${goodsInfo.discount/100}" type="currency" pattern="￥0"/>
				                                        	</td>
				                                        	<td>
				                                        		<div class="hidden">${goodsInfo.detailInfo}</div>
				                                         		<a class="goods-detail-btn" data-toggle="modal" href="#goodsDetailModal">
                                									查看详情
                            									</a>
				                                        	</td>
				                                         	<td>
				                                         		<fmt:formatDate value="${goodsInfo.pubDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss "/>
				                                         	</td>
				                                         	<td class="actions">
				                                         		<c:choose>
				                                         			<c:when test="${enableDownload}">
				                                         				<a href="auth/goods/download.htm?goodsId=${goodsInfo.id}" class="btn btn-sm btn-success">
					                                                   		<i class="fa fa-cloud-download"></i> 下载
					                                                   	</a>
				                                         			</c:when>
				                                         			
				                                         			<c:otherwise>
				                                         				<a href="javascript:void(0);" class="btn btn-sm btn-success" disabled>
					                                                   		<i class="fa fa-cloud-download"></i> 下载
					                                                   	</a>
					                                                   	
					                                                   	<a ${not empty userInfo ? 'target="_blank"' : ''} href="auth/order/add.htm?goodsId=${goodsInfo.id}&redirectUrl=pass/goods/list.htm" class="btn btn-sm btn-danger">
					                                                   		<i class="fa fa-credit-card"></i> 购买(支付宝)
				                                                   		</a>
				                                         			</c:otherwise>
				                                         		</c:choose>
				                                           </td>
				                                     	</tr>
		                                 			</c:forEach>
		                                 		</c:if>
		                                 	</tbody>
			                            </table>
			                            
			                            <jsp:include page="/jsp/common/paginate.jsp"/>
	                            	</div>
	                            </div>
		                	</div>
		                </div>
				   	</div>
				</div>
				
				<!-- MODAL SECTION -->
				<div class="row">
        			<div class="col-lg-12">
						<div class="modal fade" id="goodsDetailModal" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
			                <div class="modal-dialog">
			                    <div class="modal-content">
			                        <div class="modal-header">
			                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			                            <h4 class="modal-title">版本说明</h4>
			                        </div>
			                        <div id="goodsDetailModalBody" class="modal-body">
			                        </div>
			                        <div class="modal-footer">
			                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			                        </div>
			                    </div>
			                </div>
			        	</div>
	            	</div>
	            </div>
				<!-- END MODAL SECTION -->
        	</div>
        </div>
        
        <footer>
	        ${Cfg.WEB_COPYRIGHT}
	    </footer>
    </div>
    
    <!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <script type="text/javascript" src="js/prod/goodsList.js"></script>
    <!-- END FOOTER SECTION -->
</body>
</html>
