//接口相关
var interObj = {
	//初始化
	init:function(){
		var thisObj = this;
		
		//初始化基本信息
		interBasicObj.init();
		
		//初始化请求参数
		interParamObj.init();
		
		//初始化响应
		interRespObj.init();
		
		//快捷键设置
		$("#interPanel").keypress(function(e){
			if(e.ctrlKey && (e.which == 13 || e.which == 10)) {
				$("#saveInterBtn").trigger("click");
			} 
		});
		
		//保存
		$("#saveInterBtn").click(function(){
			if(isFormValid("basicInfoFrom")){
				var operType = $("#interOperTypeId").val();
				if(operType == 'add' || operType == 'copy'){
					thisObj.addOper();
				}
				else if(operType == 'update'){
					thisObj.updateOper();
				}
			}
		});
		
		//初始化未保存离开提示
		$('.changed-tip').changedTip();
	},
	
	isChanged:function(callback){
		var flag = $("#interPanel").is(":visible") && $('.changed-tip').changedTip("isChanged");
		if(flag){
			bootbox.confirm("接口信息未保存,您确定离开吗?",function() {
				$('.changed-tip').changedTip("skip");
				callback();
	        });
		}
		else{
			callback();
		}
	},
	
	//初始化新增
	initAddOper:function(moduleId){
		resetValidForm("basicInfoFrom");
		$("#interId").val("");
		if(moduleId != undefined){
			$("#interModuleId").val(moduleId);
		}
		
		$('#reqParamTable').nestable("clear");
		$('#reqRespTable').nestable("clear");
		
		$("#interOperTypeId").val("add");
		$("#interOperNameId").html("【新增】");
		$(".nodeInfo").hide();
		$("#interPanel").show();
		
		//处理未保存离开插件
		$('.changed-tip').changedTip("init");
	},
	
	//组装接口参数
	buildInterParam:function(){
		//表单数据
		var param = $("#basicInfoFrom").find("*").getFieldsValue();
		param.interId = $("#interId").val();
		param.docId = $("#docId").val();
		param.description = $("#interDesc").code();
		
		//请求参数
		var reqParamArray = $('#reqParamTable').nestable("getData");
    	param.reqParam = JSON.stringify(reqParamArray);
		
    	//响应参数
    	var respParamArray = $('#reqRespTable').nestable("getData");
    	param.reqResp = JSON.stringify(respParamArray);
    	
    	return param;
	},
	
	addOper:function(){
		var thisObj = this;
		doPost("auth/doc/inter/json/addDetail.htm",thisObj.buildInterParam(),function(interInfo){
			notice("新增成功",function(){
				$('.changed-tip').changedTip("init");
				
				//添加树节点
				interTreeObj.addNode(interInfo);
				//初始化表单
				interObj.initAddOper();
			});
		});
	},
	
	updateOper:function(){
		var thisObj = this;
		var param = thisObj.buildInterParam();
		doPost("auth/doc/inter/json/updateDetail.htm",param,function(){
			$('.changed-tip').changedTip("init");
			
			notice("修改成功",function(){
				var treeNodeId = $("#basicInfoFrom").data("treeNodeId");
				if(treeNodeId){
					interTreeObj.updateNodeNameByNodeId(treeNodeId,param.name);
				}
			});
		});
	},
	
	//加载接口信息
	loadInfo:function(interId,isCopy,treeNodeId){
		$(".nodeInfo").hide();
		$("#interPanel").show();
		//保存当前节点id
		$("#basicInfoFrom").data("treeNodeId",treeNodeId);
		
		resetValidForm("basicInfoFrom");
		
		var param = new Object();
		param.interId = interId;
		param.docId = $("#docId").val();
		doGet("auth/doc/inter/json/detailInfo.htm",param,function(detailInfo){
			//初始化基本信息
			interBasicObj.loadInfo(detailInfo.basicInfo);
			//初始化请求参数
			interParamObj.loadList(detailInfo.paramList);
			//初始化响应
			interRespObj.loadList(detailInfo.respList);
			
			if(isCopy == true){
				$("#interId").val("");
				$("#interOperTypeId").val("copy");
				$("#interOperNameId").html("【复制】");
			}
			else{
				$("#interId").val(interId);
				$("#interOperTypeId").val("update");
				$("#interOperNameId").html("【编辑】");
			}
			
			$('.changed-tip').changedTip("init");
		});
	}
};

//接口基本信息
var interBasicObj = {
	//初始化
	init:function(){
		var thisObj = this;
		
		//初始化模块选择下拉框
		thisObj.loadModuleSelect();
		
		$('#interDesc').summernote({
			height: 150
		});
		
		//初始化基本信息表单验证
		$("#basicInfoFrom").bootstrapValidator({
			fields:{
				name:{
	                validators: {
	                    notEmpty: {
	                        message: '接口名称不能为空'
	                    }
	                }
				},
				path:{
	                validators: {
	                    notEmpty: {
	                        message: '请求url不能为空'
	                    }
	                }
				}
			}
		});
	},
	
	//加载模块列表
	loadModuleSelect:function(){
		$("#interModuleId").empty();
		//初始化模块下拉框
		initSelectRemote("auth/doc/module/json/list.htm","docId=" + $("#docId").val(),"#interModuleId");
	},
	
	//加载
	loadInfo:function(basicInfo){
		$("#basicInfoFrom").find("*").setFieldsValue(basicInfo);
		$('#interDesc').code(basicInfo.description);
	}
};

//接口请求信息
var interParamObj = {
	//初始化
	init:function(){
		var thisObj = this;
		//初始化请求参数表
		thisObj.initReqParamTable();
		
		//格式化json操作
		$("#reqFormatSchemaBtn").click(function(){
			var extSchema = $("#reqExtSchemaArea").val();
			try{
				var jqObj = JSON.parse(extSchema);
				$("#reqExtSchemaArea").val(formatJson(jqObj));
			}
			catch(e){
				notice(e.message);
			}
		});
		
		//初始化弹出框的确认btn
		$("#reqConfirmBtn").click(function(){
			if(!validJson($("#reqExtSchemaArea").val())){
				notice("自定义json格式错误!");
				return false;
			}
			
			$("#reqExtSchemaModal").data("row").find("[name='extSchema']").val($("#reqExtSchemaArea").val());
			//清空
			$("#reqExtSchemaModal").data("row",null);
			
			$("#reqExtSchemaModal").modal("hide");
		});
		
		//初始化弹出框的确认btn
		$("#saveMoreBtn").click(function(){
			var $row = $("#moreModal").data("row");
			var moreData = $("#moreForm").find("*").getFieldsValue();
			$row.find("*").setFieldsValue(moreData);
			
			//清空
			$("#moreModal").data("row",null);
			$("#moreModal").modal("hide");
		});
		
		//初始化批量新增
		thisObj.initBatchAdd();
	},
	
	//加载
	loadList:function(paramList){
		$('#reqParamTable').nestable("clear");
		$('#reqParamTable').nestable("loadData",paramList);
	},
	
	//初始化请求数据类型下拉框
	initReqTypeSelect:function(row){
		var reqParamType = $(row).find(".req-param-type");
		//类型为"自定义"下拉框值
		var custJsonOpt = reqParamType.find("option[value='cust_json']");
		//类型为"引用"下拉框值
		var refOpt = reqParamType.find("option[value='sys_ref']");
		
		var reqPositionType = $(row).find(".req-param-position");
		reqPositionType.change(function(){
			var reqParamTypeValue = reqParamType.val();
			if($(this).val() == "body"){
				custJsonOpt.removeAttr("disabled");
				refOpt.removeAttr("disabled");
			}
			else{
				if(reqParamTypeValue == "cust_json" || reqParamTypeValue == "sys_ref"){
					reqParamType.val("sys_string");
					reqParamType.trigger("change");
				}
				custJsonOpt.attr("disabled","disabled");
				refOpt.attr("disabled","disabled");
			}
		});
		
		reqPositionType.trigger("change");
	},

	//初始化自定义扩展结构btn
	initExtSchema:function (row){
		var extSchemaBtn = $(row).find(".ext-schema-btn");
		var custRefSchema = $(row).find(".cust-ref-schema");
		
		extSchemaBtn.bind("click",row,function(e){
			//保存当前选中的行
			$("#reqExtSchemaModal").data("row",$(row));
			
			//填充
			var extSchema = $(row).find("[name='extSchema']").val();
			if(extSchema.length == 0){
				extSchema = '{\n\t"example": "type,description"\n}';
			}
			$("#reqExtSchemaArea").val(extSchema);
			
			$("#reqExtSchemaModal").modal("show");
		});
		
		//设置自定义扩展结构btn可用性
		var typeSelect = $(row).find(".req-param-type");
		$(row).find(".req-param-type").change(function(){
			var reqParamTypeValue = $(this).val();
			if(reqParamTypeValue == "cust_json"){
				extSchemaBtn.removeAttr("disabled");
			}
			else{
				extSchemaBtn.attr("disabled","disabled");
			}
			
			if(reqParamTypeValue == "sys_ref"){
				custRefSchema.removeAttr("disabled");
			}
			else{
				custRefSchema.attr("disabled","disabled");
			}
		});
		
		typeSelect.trigger("change");
	},

	//初始化更多btn
	initMoreBtn:function (row){
		var moreBtn = $(row).find(".more-btn");
		moreBtn.bind("click",row,function(e){
			$("#moreForm").resetForm();
			//保存当前选中的行
			$("#moreModal").data("row",$(row));
			
			//填充数据
			var rowData = $(row).find("*").getFieldsValue();
			$("#moreForm").find("*").setFieldsValue(rowData);
			
			$("#moreModal").modal("show");
		});
	},

	//初始化请求参数表
	initReqParamTable:function (){
		var thisObj = this;
	    $("#reqParamTable").nestable({
	    	group: 1,
	    	enableAddChild:false,
	    	maxDepth: 1,
	    	
	        getItemContentTmpl:function(){
	        	return getTmpl("#reqParamContentTmpl");
	        },
	        
	        afterAddChild:function(li){
	        	//初始化自定义扩展结构btn
	        	thisObj.initExtSchema(li);
	        	
	        	//初始化更多btn
	        	thisObj.initMoreBtn(li);
	        	
	        	//初始化请求数据类型下拉框
	        	thisObj.initReqTypeSelect(li);
	        }
	    });
	    
	    $("#addReqParamBtn").on("click", function () {
	    	$('#reqParamTable').nestable("addChild");
	    });
	    
	    $("#clearReqParamBtn").on("click", function () {
	    	$('#reqParamTable').nestable("clear");
	    });
	},
	
	//初始化批量新增
	initBatchAdd:function(){
		var thisObj = this;
		$("#batchAddReqParamBtn").click(function(){
			resetValidForm("batchAddReqParamForm");
		});
		
		$("#parseReqParamBtn").click(function(){
			var addType = $("input[name='reqParamAddType']:checked").val();
			var reqParamInfo = $.trim($("#batchAddReqParamArea").val());
			
			var paramDataList = thisObj.parseReqParamForBatchAdd(reqParamInfo);
			if(addType == 'append'){//追加
				var currentParamDataList = $('#reqParamTable').nestable("getData");
				if(currentParamDataList.length > 0){
					paramDataList= currentParamDataList.concat(paramDataList);
				}
			}
			$('#reqParamTable').nestable("loadData",paramDataList);
			
			$('#batchAddReqParamFormModal').modal('hide');
		});
	},
	
	//解析批量处理请求参数信息
	parseReqParamForBatchAdd:function(reqParamArrayStr){
		var thisObj = this;
		
		//数据源
		var paramDataList = new Array();
		var paramData = null;
		var paramDataIndex = 0;
		
		if(isNull(reqParamArrayStr)){
			return paramDataList;
		}
		
		var reqParamArray = reqParamArrayStr.split("&");
		var paramInfoStr = "";
		for(var i = 0;i < reqParamArray.length;i ++){
			paramInfoStr = $.trim(reqParamArray[i]);
			if(isNull(paramInfoStr)){//参数信息为空，直接过滤
				continue ;
			}
			
			//解析参数值对
			paramInfo = paramInfoStr.split("=");
			if(paramInfo.length > 0){
				paramData = new Object();
				paramData.code = $.trim(paramInfo[0]);
				paramData.type = "sys_string";
				if(paramInfo.length == 2){
					paramData.defValue = paramInfo[1];
					paramData.type = interUtilObj.parseParamValType(paramInfo[1]);
				}
				
				paramDataList[paramDataIndex ++] = paramData;
			}
		}
		
		return paramDataList;
	}
};

//接口响应
var interRespObj = {
	//初始化
	init:function(){
		var thisObj = this;
		
		//初始化响应信息表
		thisObj.initReqRespTable();
		
		//初始化数据结构类型下拉框
		thisObj.initSchemaTypeSelect();
		
		//初始化自定义结构表格
		thisObj.initCustSchemaTable();
		
		//初始化新增操作
		thisObj.initAddOper();
		
		//初始化保存操作
		thisObj.initSaveOper();
		
		//格式化json操作
		thisObj.initRespFormat();
		
		//初始化mock相关
		thisObj.initMock();
		
		//初始化json导入
		thisObj.initImportJson();
	},
	
	//初始化请求响应表
	initReqRespTable:function (){
		var thisObj = this;
	    $("#reqRespTable").nestable({
	    	group: 2,
	    	enableAddChild:false,
	    	maxDepth: 1,
	    	
	        getItemContentTmpl:function(){
	        	return getTmpl("#reqRespContentTmpl");
	        },
	        
	        afterAddChild:function(li){
	        	var $row = $(li);
	        	//初始化更新操作
				$row.find(".resp-oper-update").bind("click",$row,function(e){
					thisObj.loadInfo(e.data);
				});
				
				//初始化mock设置
				$row.find(".resp-oper-update-mock").bind("click",$row,function(e){
					thisObj.loadMockInfo(e.data);
				});
	        }
	    });
	    
	    $("#clearReqRespBtn").on("click", function () {
	    	$('#reqRespTable').nestable("clear");
	    });
	},
	
	//格式化json操作
	initRespFormat:function (){
		var thisObj = this;
		$("#respFormatSchemaBtn").click(function(){
			thisObj.formatJsonArea($("#respExtSchemaArea"));
		});
	},

	//初始化数据结构类型下拉框
	initSchemaTypeSelect:function (){
		$("#schemaTypeSelect").change(function(){
			var type = $("#schemaTypeSelect").val();
			//处理自定义结构
			if(type == "sys_object" || type == "sys_array"){
				$("#custSchemaTable").show();
				$("#addCustSchemaRootNodeBtn").show();
			}
			else{
				$("#custSchemaTable").hide();
				$("#addCustSchemaRootNodeBtn").hide();
			}
			
			//处理选择已有结构
			if(type == 'sys_ref'){
				$("#refSchemaDiv").show();
			}
			else{
				$("#refSchemaDiv").hide();
			}
			
			//处理扩展结构
			if(type == "cust_json"){
				$("#respExtSchemaDiv").show();
				$("#respFormatSchemaBtn").show();
			}
			else{
				$("#respExtSchemaDiv").hide();
				$("#respFormatSchemaBtn").hide();
			}
		});
	},

	//初始化自定义结构表格
	initCustSchemaTable:function (){
		$("#custSchemaTable").nestable({
	    	group: 3,
	    	maxDepth: 10,
	    	
	        getItemContentTmpl:function(){
	        	return getTmpl("#custSchemaContentTmpl");
	        },
	        
	        afterAddChild:function(li){
				//初始化引用的schema下拉框
	        	var schemaType = li.find(".cust-schema-type").first();
	        	schemaType.change(function(){
					var refSchema = li.find(".cust-ref-schema").first().parent();
					var selectVal = $(this).val();
					//处理引用类型
					if(selectVal == "sys_ref"){
						refSchema.show();
					}
					else{
						refSchema.hide();
					}
					
					//处理复合类型
					if (selectVal == "sys_object" || selectVal == "sys_array"){
						li.children("[data-action='addChild']").show();
					}
					else{
						li.children("[data-action='addChild']").hide();
					}
				});
				
				//初始化引用下拉框
	        	schemaType.trigger("change");
	        }
	    });
		
		//新增根节点
		$("#addCustSchemaRootNodeBtn").click(function(){
			$('#custSchemaTable').nestable("addChild");
		});
	},

	//初始化新增操作
	initAddOper:function (){
		$("#addRespBtn").click(function(){
			resetValidForm("respCustSchemaForm");
			$("#custSchemaTable").nestable("clear");
			$("#schemaTypeSelect").trigger("change");
			
			$("#interRespOperTypeId").val("add");
			
			//事例
			var example = '{\n\t"example": "type,description"\n}';
			$("#respExtSchemaArea").val(example);
		});
	},

	//初始化json导入
	initImportJson:function(){
		var thisObj = this;
		$("#importRespBtn").click(function(){
			resetValidForm("importRespForm");
		});
		
		$("#parseRespBtn").click(function(){
			var respInfo = $.trim($("#importRespArea").val());
			var jsonObj = thisObj.convertJsonObj(respInfo);
			var respDataList = thisObj.parseRespByJson(jsonObj);
			
			var param = new Object();
			param.code = "new resp";
			param.valid = true;
			param.type = interUtilObj.parseParamValType(jsonObj);
			param.custSchema = JSON.stringify(respDataList);
			var row = $('#reqRespTable').nestable("getOptions").getItemTmpl();
			row.find("*").setFieldsValue(param);
			$('#reqRespTable').nestable("addChild",null,row);
			
			$('#importRespFormModal').modal('hide');
		});
	},
	
	//初始化编辑操作
	loadInfo:function (jqRow){
		var interResp = jqRow.find("*").getFieldsValue();
		$("#respCustSchemaForm").data("row",jqRow);
		
		$("#interRespOperTypeId").val("update");
		
		//加载之前先清空数据
		$("#custSchemaTable").nestable("clear");
		//填充表单数据
		$("#respCustSchemaForm").find("*").setFieldsValue(interResp);
		//只有自定义结构才需要填充表格数据
		var schemaType = interResp.type;
		if(schemaType == "sys_object" || schemaType == "sys_array"){
			var rowList = JSON.parse(interResp.custSchema);
			$("#custSchemaTable").nestable("loadData",rowList);
		}
		
		$("#schemaTypeSelect").trigger("change");
	},

	//组装相关的表单信息
	serializeCustSchemaForm:function (){
		var param = $("#respCustSchemaForm").find("*").getFieldsValue();
		param.valid = true;
		
		//只有自定义结构才需要序列化表格数据
		var schemaType = $("#schemaTypeSelect").val();
		if(schemaType == "sys_object" || schemaType == "sys_array"){
			var nodeArray = $("#custSchemaTable").nestable("getData");
			param.custSchema = JSON.stringify(nodeArray);
		}
		
		return param;
	},

	//新增操作
	addOper:function (){
		var param = this.serializeCustSchemaForm();
		if(!param.valid){
			notice("编码不能为空");
			return;
		}
		
		var row = $('#reqRespTable').nestable("getOptions").getItemTmpl();
		row.find("*").setFieldsValue(param);
		$('#reqRespTable').nestable("addChild",null,row);
	},

	//编辑操作
	updateOper:function (){
		var param = this.serializeCustSchemaForm();
		if(!param.valid){
			notice("编码不能为空");
			return;
		}
		
		$("#respCustSchemaForm").data("row").find("*").setFieldsValue(param);
	},

	//初始化保存操作
	initSaveOper:function (){
		var thisObj = this;
		$("#respCustSchemaForm").bootstrapValidator({
			fields:{
				code:{
	                validators: {
	                    notEmpty: {
	                        message: '编码不能为空'
	                    }
	                }
				}
			}
		});
		
		$("#saveRespBtn").click(function(){
			if(isFormValid("respCustSchemaForm")){
				if($("#schemaTypeSelect").val() == "cust_json" && !validJson($("#respExtSchemaArea").val())){
					notice("自定义json格式错误!");
					return false;
				}
				
				var operType = $("#interRespOperTypeId").val();
				if(operType == 'add'){
					thisObj.addOper();
				}
				else if(operType == 'update'){
					thisObj.updateOper();
				}
				
				$("#respCustSchemaForm").data("row",null);
				$('#respSchemaFormModal').modal('hide');
			}
		});
	},

	//加载响应信息
	loadList:function (respList){
		$('#reqRespTable').nestable("clear");
		$('#reqRespTable').nestable("loadData",respList);
	},

	//初始化mock编辑
	loadMockInfo:function (jqRow){
		var mockData = jqRow.find("[name='mockData']").val();
		var mockRule = jqRow.find("[name='mockRule']").val();
		$("#mockForm").data("row",jqRow);
		$("#mockPreviewArea").val("");
		$("#mockDataArea").val(mockData);
		$("#mockRuleArea").val(mockRule);
	},

	//初始化mock相关操作
	initMock:function (){
		var thisObj = this;
		$("#mockFormatBtn").click(function(){
			thisObj.formatJsonArea($("#mockDataArea"));
			thisObj.formatJsonArea($("#mockRuleArea"));
		});
		
		$("#mockPreviewBtn").click(function(){
			thisObj.previewMockData();
		});
		
		$("#saveMockBtn").click(function(){
			var jqRow = $("#mockForm").data("row");
			jqRow.find("[name='mockData']").val($("#mockDataArea").val());
			jqRow.find("[name='mockRule']").val($("#mockRuleArea").val());
			$("#mockForm").data("row",null);
			$('#mockFormModal').modal('hide');
		});
	},

	//格式化json内容区域
	formatJsonArea:function (jqObj){
		var content = jqObj.val();
		if(isNull(content)){
			return ;
		}
		
		try{
			var jsonObj = JSON.parse(content);
			jqObj.val(formatJson(jsonObj));
		}
		catch(e){
			notice(e.message);
		}
	},

	//预览mock数据
	previewMockData:function (){
		var param = new Object();
		param.mockRule = $("#mockRuleArea").val();
		doPost("pass/mock/dynamic/json/preview.htm",param,function(mockData){
			$("#mockPreviewArea").val(mockData);
		});
	},
	
	//将json字符串转换为json对象
	convertJsonObj:function(jsonInfoStr){
		var jsonObj = new Object;
		try{
			jsonObj = JSON.parse(jsonInfoStr);
		}
		catch(e){
			notice(e.message);
		}
		
		return jsonObj;
	},
	
	//解析json导入结构
	parseRespByJson:function(jsonObj){
		var thisObj = this;
		//数据源
		var respDataList = new Array();
		if(jsonObj == undefined || jsonObj == null){
			return respDataList;
		}
		
		//解析数据格式
		thisObj.parseJsonSchema(respDataList,null,jsonObj);
		return respDataList;
	},
	
	//解析json对象结构
	parseJsonSchema:function(respDataList,parentId,jsonObj){
		var thisObj = this;
		//解析数据格式
		if(jsonObj instanceof Array){//数组对象
			thisObj.parseJsonArraySchema(respDataList,null,jsonObj);
		}
		else {//普通对象
			thisObj.parseJsonObjectSchema(respDataList,null,jsonObj);
		}
	},
	
	//解析json数组对象结构
	parseJsonArraySchema:function(respDataList,parentId,jsonObj){
		var thisObj = this;
		var subId = parentId == null ? 10 : parentId * 100 + 10;
		/* var respData = new Object();
		respData.code = "def_array_name";
		respData.type = "sys_array";
		respData.nodeId = subId;
		respData.parentId = parentId;
		respDataList.push(respData); */
		
		thisObj.parseJsonSchema(respDataList,subId,jsonObj[0]);
	},
	
	//解析json对象结构
	parseJsonObjectSchema:function(respDataList,parentId,jsonObj){
		var thisObj = this;
		var attrType = "";
		var attrVal = null;
		var respData = null;
		
		var subId = parentId == null ? 10 : parentId * 100 + 10;
		var respData = new Object();
		
		/* if(parentId != null){//顶层结构无需设置名称
			respData.code = "def_obj_name";
			respData.type = "sys_object";
			respData.nodeId = subId;
			respData.parentId = parentId;
			respDataList.push(respData);
		} */
		
		for(var attr in jsonObj){
			attrVal = jsonObj[attr];
			
			respData = new Object();
			respData.code = attr;
			respData.type = interUtilObj.parseParamValType(attrVal);
			respData.nodeId = subId;
			respData.parentId = parentId;
			respDataList.push(respData);

			attrType = Object.prototype.toString.call(attrVal);
			if(attrType == "[object Array]"){
				thisObj.parseJsonObjectSchema(respDataList,subId,attrVal[0]);
			}
			else if(attrType == "[object Object]"){
				thisObj.parseJsonObjectSchema(respDataList,subId,attrVal);
			}
			
			subId ++;
		}
	}
};

//工具类
var interUtilObj = {
	//根据值解析对应的类型
	parseParamValType:function(val){
		//空值，默认为字符串
		if(isNull(val)){
			return "sys_string";
		}
		
		var valType = Object.prototype.toString.call(val);
		//数组
		if(valType == "[object Array]"){
			return "sys_array";
		}
		//对象
		else if(valType == "[object Object]"){
			return "sys_object";
		}
		else {//简单类型
			if(val.toString().toLowerCase() == 'true' || val.toString().toLowerCase() == 'false'){
				return "sys_boolean";
			}
			else if(/(^-?\d+$)/.test(val)){
				return "sys_integer_int32";
			}
			else if(/(^(-?\d+)(\.\d+)?$)/.test(val)){
				return "sys_number_double";
			}
			else {
				return "sys_string";
			}
		}
	}
};

$(function() {
	interObj.init();
});