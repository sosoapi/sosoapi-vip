$(function() {
	//初始化数据结构类型下拉框
	initSchemaTypeSelect();
	
	//初始化自定义结构表格
	initCustSchemaTable();
	
	//初始化新增操作
	initAddOper();
	
	//初始化保存操作
	initSaveOper();
});

//初始化数据结构类型下拉框
function initSchemaTypeSelect(){
	//已有结构下拉框
	$("#refSchemaSelectId").hide();
	
	//自定义结构初始化的时候隐藏
	$("#custSchemaTable").hide();
	$("#addCustSchemaRootNodeBtn").hide();
	
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
			$("#refSchemaSelectId").show();
		}
		else{
			$("#refSchemaSelectId").hide();
		}
	});
}

//初始化自定义结构表格
function initCustSchemaTable(){
	$("#custSchemaTable").nestable({
    	group: 2,
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
}

//初始化新增操作
function initAddOper(){
	$("#addBtn").click(function(){
		$("#custSchemaTable").nestable("clear");
		$("#schemaTypeSelect").trigger("change");
		
		$("#operTypeId").val("add");
	});
}

//初始化编辑操作
function initUpdateOper(id){
	$("#operTypeId").val("update");
	
	$("#schemaId").val(id);
	
	var param = new Object();
	param.schemaId = id;
	param.docId = $("#docId").val();
	doGet("auth/doc/schema/json/info.htm",param,function(schemaInfo){
		//加载之前先清空数据
		$("#custSchemaTable").nestable("clear");
		
		//填充表单数据
		$("#custSchemaForm").find("*").setFieldsValue(schemaInfo);
		
		//只有自定义结构才需要填充表格数据
		var schemaType = schemaInfo.type;
		if(schemaType == "sys_object" || schemaType == "sys_array"){
			var rowList = JSON.parse(schemaInfo.custSchema);
			$("#custSchemaTable").nestable("loadData",rowList);
		}
		
		$("#schemaTypeSelect").trigger("change");
	});
}

//组装相关的表单信息
function serializeCustSchemaForm(){
	var param = $("#custSchemaForm").find("*").getFieldsValue();
	param.valid = true;
	
	//只有自定义结构才需要序列化表格数据
	var schemaType = $("#schemaTypeSelect").val();
	if(schemaType == "sys_object" || schemaType == "sys_array"){
		var nodeArray = $("#custSchemaTable").nestable("getData");
		param.custSchema = JSON.stringify(nodeArray);
	}
	
	return param;
}

//新增操作
function addOper(){
	var param = serializeCustSchemaForm();
	if(!param.valid){
		notice("编码不能为空");
		return;
	}
	
	doPost("auth/doc/schema/json/add.htm",param,function(data){
		notice("保存成功",function(){
			$("#schemaFormModal").modal("hide");
			location.reload();
		});
	});
}

//初始化删除操作
function initDelOper(id){
	bootbox.confirm("确定执行删除操作？",function(){
		var param = new Object();
		param.schemaId = id;
		param.docId = $("#docId").val();
		doGet("auth/doc/schema/json/del.htm",param,function(){
			location.reload();
		});
	});
}

//编辑操作
function updateOper(){
	var param = serializeCustSchemaForm();
	if(!param.valid){
		notice("编码不能为空");
		return;
	}
	
	doPost("auth/doc/schema/json/update.htm",param,function(data){
		notice("保存成功",function(){
			$('#schemaFormModal').modal('hide');
			location.reload();
		});
	});
}

//初始化保存操作
function initSaveOper(){
	$("#custSchemaForm").bootstrapValidator({
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
	
	$("#saveSchemaBtn").click(function(){
		if(isFormValid("custSchemaForm")){
			var operType = $("#operTypeId").val();
			if(operType == 'add'){
				addOper();
			}
			else if(operType == 'update'){
				updateOper();
			}
		}
	});
}

//初始化复制操作
function initCopyOper(schemaId){
	initUpdateOper(schemaId);
	
	$("#operTypeId").val("add");
	$("#schemaId").val("");
}