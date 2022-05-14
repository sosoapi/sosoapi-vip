//目录树相关
var bookTreeObj = {
	//初始化树结构
	init:function(){
		var thisObj = this;
		var setting = {
			async: {
				enable: true,
				url:"auth/book/preview/json/load.htm",
				autoParam:["dataId=parentId"],
				otherParam:{"bookId":$("#bookId").val(),"condition":$("#condition").val()},
				dataFilter: ajaxDataFilter,
				headers:{
					"sysReqToken":$("#sysReqToken").val()
				}
			},
			view: {
				expandSpeed:"",
				fontCss: getFont,
				selectedMulti: false,
				showLine:false,
				showIcon:true,
				dblClickExpand: false
			},
			data: {
				keep:{
					parent:true
				},
				
				simpleData: {
					enable: true
				},
				key:{
					title:"title"
				}
			},
			callback: {
				onClick: thisObj.clickCallback
			}
		};
		
		//初始化树
		$.fn.zTree.init($("#bookTree"), setting);
	},

	//点击事件
	clickCallback:function (event, treeId, treeNode) {
		if(treeNode.dataId <= 0 || treeNode.type != 'bookFile'){
			return ;
		}
		
		$(".content-info").show();
		$(".book-info").hide();
		
		$("#chapterTitle").html(treeNode.title);
		
		var param = new Object();
		param.bookId = $("#bookId").val();
		param.contentId = treeNode.otherData;
		doGet("auth/book/preview/json/getContent.htm",param,function(contentInfo){
			$("body").scrollTop(0);
			if(contentInfo.type == "html"){
				$("#richTextContentWrap").html(contentInfo.richTextContent);
				$("#richTextContentWrap").show();
				$("#markdownContentWrap").hide();
			}
			else if(contentInfo.type == "markdown"){
				$("#markdownContentWrap").empty();
				editormd.markdownToHTML("markdownContentWrap", {
                    markdown        : contentInfo.markdownContent,
                    htmlDecode      : "style,script,iframe",  // you can filter tags decode
                    //toc             : false,
                    tocm            : true,    // Using [TOCM]
                    emoji           : true,
                    taskList        : true,
                    tex             : false,  // 默认不解析
                    flowChart       : false,  // 默认不解析
                    sequenceDiagram : false,  // 默认不解析
                    flowChart		: false
                });
				
				$("#richTextContentWrap").hide();
				$("#markdownContentWrap").show();
			}
		});
	}
};

$(function () {
	//初始化树
	bookTreeObj.init();
});
