<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${Cfg.WEB_BASE_URL}"/>
	<%@include file="/jsp/common/head.jsp"%>
	<title></title>
		
	<!-- PAGE LEVEL STYLES -->
	<link href="plugin/nestable/ext/css/nestable.ext.css" rel="stylesheet">
	<style type="text/css">
		.dd-handle-drag{
			height:47px;
		}
		
		.dd-handle-drag:before{
			top: 14px;
		}
		
		.form-control{
			width:120px;
		}
		
		.dd-item > button{
			margin-top: 15px;
		}
		
		#nestable-output{
			width:100%;
		}
	</style>
	<!-- END PAGE LEVEL  STYLES -->
</head>
<body class="blank">
	<div class="content animate-panel">
		<div class="row">
		    <div class="col-lg-8">
		        <div id="nestable-menu">
		        	<button type="button" data-action="load" class="btn btn-default btn-sm">Load</button>
		        	<button type="button" data-action="clear" class="btn btn-default btn-sm">Clear</button>
		        	<button type="button" data-action="addRoot" class="btn btn-default btn-sm">Add Root</button>
		            <button type="button" data-action="expand-all" class="btn btn-default btn-sm">Expand All</button>
		            <button type="button" data-action="collapse-all" class="btn btn-default btn-sm">Collapse All</button>
		        </div>
		    </div>
		</div>

		<div class="row">
		    <div class="col-lg-8">
		        <div class="hpanel">
		            <div class="panel-heading">
		                <div class="panel-tools">
		                    <a class="showhide"><i class="fa fa-chevron-up"></i></a>
		                    <a class="closebox"><i class="fa fa-times"></i></a>
		                </div>
		                Nestable basic list
		            </div>
		            <div class="panel-body">
		                <p class="m-b-lg">
		                    <strong>Nestable</strong> is an interactive hierarchical list. You can drag and drop to rearrange the order. It works well on touch-screens.
		                </p>
           	
		                <div class="dd" id="nestable">
		                	<div class="dd-title">
				           		<ul>
									<li>操作</li>
				                	<li>字段</li>
				                	<li>描述</li>
				                    <li>类型</li>
				                    <li>引用</li>
								</ul>
				           	</div>
			           	
		                    <ol class="dd-list">
		                        <li class="dd-item dd-item-drag">
		                            <div class="dd-handle dd-handle-drag"></div>
		                            <div class="dd-content">
		                            	<ul class="list-inline">
											<li>
												<input name="code" class="form-control" value="1"/>
											</li>
											
											<li>
												<input name="description" class="form-control" />
											</li>
											
											<li>
												<select name="type" class="form-control cust-schema-type">
									           		<option value="sys_string">string</option>
									           		<option value="sys_array">array</option>
									                <option value="sys_object">object</option>
									                <option value="sys_boolean">boolean</option>
													<option value="sys_integer_int32">int</option>
													<option value="sys_integer_int64">long</option>
													<option value="sys_number_float">float</option>
													<option value="sys_number_double">double</option>
													<option value="sys_number_decimal">decimal</option>
													<option value="sys_ref">ref</option>
									           	</select>
											</li>
											
											<li>
												<select name="refSchemaId" class="form-control cust-ref-schema">
													<option value="1">1</option>
													<option value="2">2</option>
										        </select>
											</li>
										</ul>
		                            </div>
		                        </li>
		                        <li class="dd-item dd-item-drag">
		                        	<div class="dd-handle dd-handle-drag"></div>
		                            <div class="dd-content">
		                            	<ul class="list-inline">
											<li>
												<input name="code" class="form-control" value="2"/>
											</li>
											
											<li>
												<input name="description" class="form-control" />
											</li>
											
											<li>
												<select name="type" class="form-control cust-schema-type">
									           		<option value="sys_string">string</option>
									           		<option value="sys_array">array</option>
									                <option value="sys_object">object</option>
									                <option value="sys_boolean">boolean</option>
													<option value="sys_integer_int32">int</option>
													<option value="sys_integer_int64">long</option>
													<option value="sys_number_float">float</option>
													<option value="sys_number_double">double</option>
													<option value="sys_number_decimal">decimal</option>
													<option value="sys_ref">ref</option>
									           	</select>
											</li>
											
											<li>
												<select name="refSchemaId" class="form-control cust-ref-schema">
													<option value="1">1</option>
													<option value="2">2</option>
										        </select>
											</li>
										</ul>
		                            </div>
		                            <ol class="dd-list">
		                                <li class="dd-item dd-item-drag">
		                                	<div class="dd-handle dd-handle-drag"></div>
		                                    <div class="dd-content">
				                            	<ul class="list-inline">
													<li>
														<input name="code" class="form-control" value="3"/>
													</li>
													
													<li>
														<input name="description" class="form-control" />
													</li>
													
													<li>
														<select name="type" class="form-control cust-schema-type">
											           		<option value="sys_string">string</option>
											           		<option value="sys_array">array</option>
											                <option value="sys_object">object</option>
											                <option value="sys_boolean">boolean</option>
															<option value="sys_integer_int32">int</option>
															<option value="sys_integer_int64">long</option>
															<option value="sys_number_float">float</option>
															<option value="sys_number_double">double</option>
															<option value="sys_number_decimal">decimal</option>
															<option value="sys_ref">ref</option>
											           	</select>
													</li>
													
													<li>
														<select name="refSchemaId" class="form-control cust-ref-schema">
															<option value="1">1</option>
															<option value="2">2</option>
												        </select>
													</li>
												</ul>
				                            </div>
		                                </li>
		                                <li class="dd-item dd-item-drag">
		                                	<div class="dd-handle dd-handle-drag"></div>
		                                    <div class="dd-content">
				                            	<ul class="list-inline">
													<li>
														<input name="code" class="form-control" value="4"/>
													</li>
													
													<li>
														<input name="description" class="form-control" />
													</li>
													
													<li>
														<select name="type" class="form-control cust-schema-type">
											           		<option value="sys_string">string</option>
											           		<option value="sys_array">array</option>
											                <option value="sys_object">object</option>
											                <option value="sys_boolean">boolean</option>
															<option value="sys_integer_int32">int</option>
															<option value="sys_integer_int64">long</option>
															<option value="sys_number_float">float</option>
															<option value="sys_number_double">double</option>
															<option value="sys_number_decimal">decimal</option>
															<option value="sys_ref">ref</option>
											           	</select>
													</li>
													
													<li>
														<select name="refSchemaId" class="form-control cust-ref-schema">
															<option value="1">1</option>
															<option value="2">2</option>
												        </select>
													</li>
												</ul>
				                            </div>
		                                </li>
		                            </ol>
		                        </li>
		                        <li class="dd-item dd-item-drag">
		                        	<div class="dd-handle dd-handle-drag"></div>
		                            <div class="dd-content">
		                            	<ul class="list-inline">
											<li>
												<input name="code" class="form-control" value="5"/>
											</li>
											
											<li>
												<input name="description" class="form-control" />
											</li>
											
											<li>
												<select name="type" class="form-control cust-schema-type">
									           		<option value="sys_string">string</option>
									           		<option value="sys_array">array</option>
									                <option value="sys_object">object</option>
									                <option value="sys_boolean">boolean</option>
													<option value="sys_integer_int32">int</option>
													<option value="sys_integer_int64">long</option>
													<option value="sys_number_float">float</option>
													<option value="sys_number_double">double</option>
													<option value="sys_number_decimal">decimal</option>
													<option value="sys_ref">ref</option>
									           	</select>
											</li>
											
											<li>
												<select name="refSchemaId" class="form-control cust-ref-schema">
													<option value="1">1</option>
													<option value="2">2</option>
										        </select>
											</li>
										</ul>
		                            </div>
		                            <ol class="dd-list">
		                                <li class="dd-item dd-item-drag">
		                                	<div class="dd-handle dd-handle-drag"></div>
		                                    <div class="dd-content">
				                            	<ul class="list-inline">
													<li>
														<input name="code" class="form-control" value="6"/>
													</li>
													
													<li>
														<input name="description" class="form-control" />
													</li>
													
													<li>
														<select name="type" class="form-control cust-schema-type">
											           		<option value="sys_string">string</option>
											           		<option value="sys_array">array</option>
											                <option value="sys_object">object</option>
											                <option value="sys_boolean">boolean</option>
															<option value="sys_integer_int32">int</option>
															<option value="sys_integer_int64">long</option>
															<option value="sys_number_float">float</option>
															<option value="sys_number_double">double</option>
															<option value="sys_number_decimal">decimal</option>
															<option value="sys_ref">ref</option>
											           	</select>
													</li>
													
													<li>
														<select name="refSchemaId" class="form-control cust-ref-schema">
															<option value="1">1</option>
															<option value="2">2</option>
												        </select>
													</li>
												</ul>
				                            </div>
		                                </li>
		                                <li class="dd-item dd-item-drag">
		                                	<div class="dd-handle dd-handle-drag"></div>
		                                    <div class="dd-content">
				                            	<ul class="list-inline">
													<li>
														<input name="code" class="form-control" value="7"/>
													</li>
													
													<li>
														<input name="description" class="form-control" />
													</li>
													
													<li>
														<select name="type" class="form-control cust-schema-type">
											           		<option value="sys_string">string</option>
											           		<option value="sys_array">array</option>
											                <option value="sys_object">object</option>
											                <option value="sys_boolean">boolean</option>
															<option value="sys_integer_int32">int</option>
															<option value="sys_integer_int64">long</option>
															<option value="sys_number_float">float</option>
															<option value="sys_number_double">double</option>
															<option value="sys_number_decimal">decimal</option>
															<option value="sys_ref">ref</option>
											           	</select>
													</li>
													
													<li>
														<select name="refSchemaId" class="form-control cust-ref-schema">
															<option value="1">1</option>
															<option value="2">2</option>
												        </select>
													</li>
												</ul>
				                            </div>
		                                </li>
		                            </ol>
		                        </li>
		                        <li class="dd-item dd-item-drag">
		                        	<div class="dd-handle dd-handle-drag"></div>
		                            <div class="dd-content">
		                            	<ul class="list-inline">
											<li>
												<input name="code" class="form-control" value="8"/>
											</li>
											
											<li>
												<input name="description" class="form-control" />
											</li>
											
											<li>
												<select name="type" class="form-control cust-schema-type">
									           		<option value="sys_string">string</option>
									           		<option value="sys_array">array</option>
									                <option value="sys_object">object</option>
									                <option value="sys_boolean">boolean</option>
													<option value="sys_integer_int32">int</option>
													<option value="sys_integer_int64">long</option>
													<option value="sys_number_float">float</option>
													<option value="sys_number_double">double</option>
													<option value="sys_number_decimal">decimal</option>
													<option value="sys_ref">ref</option>
									           	</select>
											</li>
											
											<li>
												<select name="refSchemaId" class="form-control cust-ref-schema">
													<option value="1">1</option>
													<option value="2">2</option>
										        </select>
											</li>
										</ul>
		                            </div>
		                        </li>
		                        <li class="dd-item dd-item-drag">
		                        	<div class="dd-handle dd-handle-drag"></div>
		                            <div class="dd-content">
		                            	<ul class="list-inline">
											<li>
												<input name="code" class="form-control" value="9"/>
											</li>
											
											<li>
												<input name="description" class="form-control" />
											</li>
											
											<li>
												<select name="type" class="form-control cust-schema-type">
									           		<option value="sys_string">string</option>
									           		<option value="sys_array">array</option>
									                <option value="sys_object">object</option>
									                <option value="sys_boolean">boolean</option>
													<option value="sys_integer_int32">int</option>
													<option value="sys_integer_int64">long</option>
													<option value="sys_number_float">float</option>
													<option value="sys_number_double">double</option>
													<option value="sys_number_decimal">decimal</option>
													<option value="sys_ref">ref</option>
									           	</select>
											</li>
											
											<li>
												<select name="refSchemaId" class="form-control cust-ref-schema">
													<option value="1">1</option>
													<option value="2">2</option>
										        </select>
											</li>
										</ul>
		                            </div>
		                        </li>
		                    </ol>
		                </div>
		            </div>
		        </div>
		    </div>
		
			<div class="col-lg-4">
				<div class="hpanel">
					<div class="panel-heading">
						<div class="panel-tools">
		                    <a class="showhide"><i class="fa fa-chevron-up"></i></a>
		                    <a class="closebox"><i class="fa fa-times"></i></a>
		                </div>
						Serialised Output
					</div>
					
					<div class="panel-body">
						<textarea id="nestable-output" class="form-control" rows="40"></textarea>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script id="itemContentTmpl" type="text/html">  
		<ul class="list-inline">
			<li>
				<input name="code" class="form-control"/>
			</li>
			
			<li>
				<input name="description" class="form-control" />
			</li>
			
			<li>
				<select name="type" class="form-control cust-schema-type">
	           		<option value="sys_string">string</option>
	           		<option value="sys_array">array</option>
	                <option value="sys_object">object</option>
	                <option value="sys_boolean">boolean</option>
					<option value="sys_integer_int32">int</option>
					<option value="sys_integer_int64">long</option>
					<option value="sys_number_float">float</option>
					<option value="sys_number_double">double</option>
					<option value="sys_number_decimal">decimal</option>
					<option value="sys_ref">ref</option>
	           	</select>
			</li>
			
			<li>
				<select name="refSchemaId" class="form-control cust-ref-schema">
					<option value="1">1</option>
					<option value="2">2</option>
		        </select>
			</li>
		</ul>
	</script>

	<!-- FOOTER SECTION -->
    <jsp:include page="/jsp/common/footer.jsp" />
    <script type="text/javascript" src="plugin/nestable/ext/js/jquery.nestable.ext.js"></script>
    <script type="text/javascript">
    var itemListData = [
                        {
                            "code": "1",
                            "description": "",
                            "type": "sys_string",
                            "refSchemaId": "1",
                            "nodeId": "10",
                            "parentId": null
                          },
                          {
                            "code": "2",
                            "description": "",
                            "type": "sys_string",
                            "refSchemaId": "1",
                            "nodeId": "11",
                            "parentId": null
                          },
                          {
                            "code": "3",
                            "description": "",
                            "type": "sys_string",
                            "refSchemaId": "1",
                            "nodeId": "1110",
                            "parentId": "11"
                          },
                          {
                            "code": "4",
                            "description": "",
                            "type": "sys_string",
                            "refSchemaId": "1",
                            "nodeId": "1111",
                            "parentId": "11"
                          },
                          {
                            "code": "5",
                            "description": "",
                            "type": "sys_string",
                            "refSchemaId": "1",
                            "nodeId": "12",
                            "parentId": null
                          },
                          {
                            "code": "6",
                            "description": "",
                            "type": "sys_string",
                            "refSchemaId": "1",
                            "nodeId": "1210",
                            "parentId": "12"
                          },
                          {
                            "code": "7",
                            "description": "",
                            "type": "sys_string",
                            "refSchemaId": "1",
                            "nodeId": "1211",
                            "parentId": "12"
                          },
                          {
                            "code": "8",
                            "description": "",
                            "type": "sys_string",
                            "refSchemaId": "1",
                            "nodeId": "13",
                            "parentId": null
                          },
                          {
                            "code": "9",
                            "description": "",
                            "type": "sys_string",
                            "refSchemaId": "1",
                            "nodeId": "14",
                            "parentId": null
                          }
                        ];
    
    //初始化操作菜单
    function initMenu(){
    	$('#nestable-menu').on('click', function (e) {
            var target = $(e.target);
            var action = target.data('action');
            if (action === 'expand-all') {
                $('.dd').nestable('expandAll');
            }
            else if (action === 'collapse-all') {
                $('.dd').nestable('collapseAll');
            }
            else if (action === 'load'){
            	$('#nestable').nestable("loadData",itemListData);
            }
            else if (action === 'clear'){
            	$('#nestable').nestable("clear");
            }
            else if (action === 'addRoot'){
            	$('#nestable').nestable("addChild");
            }
        });
    }
    
    //初始化nestable
	function initNestable(){
        $('#nestable').nestable({
            group: 1,
            
            getItemContentTmpl:function(){
            	return getTmpl("#itemContentTmpl");
            },
            
            afterAddChild:function(li){
            },
            
            afterRemoveItem:function(li){
            }
        });
        
 		$('#nestable').nestable("hideAction",$($('#nestable').find('li[data-id="4"]')[0]),'addChild');
	}
    
    //初始化输出
    function initOutput(){
        $('#nestable').on('change', function(){
        	var output = $('#nestable-output');
            if (window.JSON) {
                //output.val(window.JSON.stringify($('#nestable').nestable('serialize'), null, 2));
            	output.val(window.JSON.stringify($('#nestable').nestable('getData'), null, 2));
            } else {
                output.val('JSON browser support required for this demo.');
            }
        });
        
        $('#nestable').trigger('change');
        
        //内容变更，输出即时展示
        $('#nestable').find(".form-control").each(function(){
            $(this).change(function(){
            	$('#nestable').trigger("change");
            })
        });
    }
    
    $(function () {
      	//初始化操作菜单
        initMenu();

      	//初始化nestable
    	initNestable();
      	
    	//初始化输出
        initOutput();
    });
    </script>
    <!-- END FOOTER SECTION -->
</body>
</html>
