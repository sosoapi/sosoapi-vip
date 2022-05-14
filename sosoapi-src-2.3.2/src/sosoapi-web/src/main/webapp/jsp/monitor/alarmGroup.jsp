<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title>警报接收组详情  ${Cfg.WEB_PAGE_TITLE_POSTFIX}</title>
		
	<!-- PAGE LEVEL STYLES -->
	<link href="plugin/duallistbox/css/bootstrap-duallistbox.min.css" rel="stylesheet">
	<style type="text/css">
		.pannel-tools-fixed{
			position: fixed;
		    z-index: 1;
		    top: 92px;
		    right: 4.1%;
		}
		
		.checkbox-inline, .radio-inline{
			padding-left: 0;
		}
	</style>
	<!-- END PAGE LEVEL  STYLES -->
</head>
<body class="sticky-header">
	<!-- LEFT SECTION -->
   	<jsp:include page="/jsp/user/controlMenu.jsp" />
	<!-- END LEFT SECTION -->
		
	<!-- body content start-->
    <div class="body-content" >
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
			                    <div class="panel-tools pannel-tools-fixed">
									<button id="saveGroupBtn" type="button" class="btn btn-primary">保存</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
								</div>
			                    <span>警报接收组信息</span>
			                </div>
		                	<div class="panel-body">
		                		<div class="row">
									<div class="col-lg-12">
										<h4 class="font-bold">基本信息</h4>
										<hr>
				                		<form id="groupInfoForm" method="post" action="${action}" class="form-horizontal">
				                			<input type="hidden" id="operType" name="operType" value="${operType}" />
		                             		<input type="hidden" name="groupId" value="${groupInfo.id}" />
		                             		<input type="hidden" id="receiverIdList" name="receiverIdList"/>
		                             		<div class="form-group">
							                    <label class="control-label col-lg-offset-2 col-lg-2">名称</label>
							                    <div class="col-lg-4">
							                        <input type="text" name="name" class="form-control" value="${groupInfo.name}">
							                    </div>
							                </div>
							         		
							         		<div class="form-group">
							         			<label class="control-label col-lg-offset-2 col-lg-2">状态</label>
							                    <div class="col-lg-4">
							                        <select name="status" class="form-control" data-initValue="${groupInfo.status}">
					                                	<option value="on">启用</option>
					                                	<option value="off">关闭</option>
					                                </select>
							                    </div>
							         		</div>
							         		
							         		<div class="form-group">
							                	<label class="col-lg-offset-2 col-lg-2 control-label">预警方式</label>

                								<div class="col-lg-4">
                									<label class="checkbox-inline"> 
                										<input class="i-checks" type="checkbox" name="emailAlarm" value="true" ${groupInfo.emailAlarm ? 'checked="checked" ' : ''}> 邮件 
                									</label> 
                									<label class="checkbox-inline">
                    									<input class="i-checks" type="checkbox" name="phoneAlarm" value="true" ${groupInfo.phoneAlarm ? 'checked="checked" ' : ''} disabled="disabled"> 短信 
                    								</label> 
                    							</div>
                    						</div>
                    						
											<div class="form-group">
							                    <label class="control-label col-lg-offset-2 col-lg-2">接收报警时段</label>
							                    <div class="col-lg-4" style="padding-right:0;padding-left:0;">
							                    	<div class="col-lg-5">
							                    		<select name="startTime" class="form-control" data-initValue="${groupInfo.startTime}">
							                    			<option value="0">0点</option>
							                    			<option value="1">1点</option>
							                    			<option value="2">2点</option>
							                    			<option value="3">3点</option>
							                    			<option value="4">4点</option>
							                    			<option value="5">5点</option>
							                    			<option value="6">6点</option>
							                    			<option value="7">7点</option>
							                    			<option value="8">8点</option>
							                    			<option value="9">9点</option>
							                    			<option value="10">10点</option>
							                    			<option value="11">11点</option>
							                    			<option value="12">12点</option>
							                    			<option value="13">13点</option>
							                    			<option value="14">14点</option>
							                    			<option value="15">15点</option>
							                    			<option value="16">16点</option>
							                    			<option value="17">17点</option>
							                    			<option value="18">18点</option>
							                    			<option value="19">19点</option>
							                    			<option value="20">20点</option>
							                    			<option value="21">21点</option>
							                    			<option value="22">22点</option>
							                    			<option value="23">23点</option>
							                    			<option value="24">24点</option>
							                    		</select>
							                    	</div>
							                    	<div class="col-lg-2 text-center">
							                    		<span>至</span>
							                    	</div>
							                    	<div class="col-lg-5">
							                    		<select name="endTime" class="form-control" data-initValue="${groupInfo.endTime}">
							                    			<option value="0">0点</option>
							                    			<option value="1">1点</option>
							                    			<option value="2">2点</option>
							                    			<option value="3">3点</option>
							                    			<option value="4">4点</option>
							                    			<option value="5">5点</option>
							                    			<option value="6">6点</option>
							                    			<option value="7">7点</option>
							                    			<option value="8">8点</option>
							                    			<option value="9">9点</option>
							                    			<option value="10">10点</option>
							                    			<option value="11">11点</option>
							                    			<option value="12">12点</option>
							                    			<option value="13">13点</option>
							                    			<option value="14">14点</option>
							                    			<option value="15">15点</option>
							                    			<option value="16">16点</option>
							                    			<option value="17">17点</option>
							                    			<option value="18">18点</option>
							                    			<option value="19">19点</option>
							                    			<option value="20">20点</option>
							                    			<option value="21">21点</option>
							                    			<option value="22">22点</option>
							                    			<option value="23">23点</option>
							                    			<option value="24" selected="selected">24点</option>
							                    		</select>
							                    	</div>
							                    </div>
							                </div>
							                
							                <div class="form-group">
							                    <label class="control-label col-lg-offset-2 col-lg-2">描述</label>
							                    <div class="col-lg-4">
							                    	<textarea name="description" class="form-control" rows="5">${groupInfo.description}</textarea>
							                    </div>
							                </div>
		                            	</form>
		                            </div>
	                            </div>
	                            
	                            <div class="form-horizontal m-t-lg">
									<div class="row">
										<div class="col-lg-12">
											<h4 class="font-bold">接收者设置</h4>
											<hr>
											<div class="col-lg-8 col-lg-offset-2">
						                    	<select id="receiverDualList" multiple="multiple" size="10">
						                    		<c:if test="${not empty receiverList}">
														<c:forEach items="${receiverList}" var="receiverInfo" varStatus="status">
															<option value="${receiverInfo.code}" ${receiverInfo.selected ? 'selected="selected"' : ''}>${receiverInfo.name}</option>						
														</c:forEach>
													</c:if>
											   	</select>
						                    </div>
										</div>
									</div>
								</div>
		                	</div>
		                </div>
				   	</div>
				</div>
		   	</div>
        </div>
        <!--body wrapper end-->
        
        <!--footer section start-->
	    <footer>
	        ${Cfg.WEB_COPYRIGHT}
	    </footer>
	    <!--footer section end-->
	</div>

    <!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <!-- END FOOTER SECTION -->
    
    <!-- PAGE LEVEL SCRIPTS -->
    <script type="text/javascript" src="plugin/duallistbox/js/jquery.bootstrap-duallistbox.min.js"></script>
    <script type="text/javascript" src="plugin/icheck/js/icheck.min.js"></script>
   	<script type="text/javascript">
	   	$(function(){
	   		$("#groupInfoForm").bootstrapValidator({
	   			fields:{
	   				name:{
	   	                validators: {
	   	                    notEmpty: {
	   	                        message: '名称不能为空'
	   	                    }
	   	                }
	   				}
	   			}
	   		});
	   		
	   		$("#saveGroupBtn").click(function(){
	   			if(isFormValid("groupInfoForm")){
	   				var msg = "";
	   				if($("#operType").val() == "add"){
	   					msg = "确认新增该接收组？";
	   				}
	   				else{
	   					msg = "确认修改该接收组？";
	   				}
	   				
	   				bootbox.confirm(msg,function(){
	   					//设置选中的接收者id列表
	   					$("#receiverIdList").val($('#receiverDualList').val());
	   					//表单提交
	   					$("#groupInfoForm").bootstrapValidator('defaultSubmit');
		   			});
	   			}
	   		});
	   		
	   		$('#receiverDualList').bootstrapDualListbox({
	    		filterTextClear: "全部展示",
	    		filterPlaceHolder: "查询",
	            nonSelectedListLabel: '未加入',
	            selectedListLabel: '已加入',
	            moveSelectedLabel: '移动选中',
	            moveAllLabel: '全部移动',
	            removeSelectedLabel: '移除选中',
	            removeAllLabel: '全部移除',
	            infoTextEmpty: '',
	            
	            preserveSelectionOnMove: 'moved',
	            selectorMinimalHeight: 300,
	            moveOnSelect: false,
	            showFilterInputs: true,
	            nonSelectedFilter: '',
	            selectedFilter: '',
	            infoText:false
	       	});
	   		
	   		$('.i-checks').iCheck({
	   	        checkboxClass: 'icheckbox_square-green',
	   	        radioClass: 'iradio_square-green'
	   	    });
	   	});
   	</script>
	<!-- END PAGE LEVEL  SCRIPTS -->
</body>
</html>
