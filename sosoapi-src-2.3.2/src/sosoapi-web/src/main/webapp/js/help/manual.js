var currentUrl = "pass/help/manual.htm";
var treeSetting = {
	view:{
		showIcon:false,
		showLine:false
	}	
};
var treeData = [
	{ name:"1 关于SosoApi",url:currentUrl + "#sub-title-1",target:"_self"},
	{ name:"2 登录注册",url:currentUrl + "#sub-title-2",target:"_self",
		children: [
			{ name:"2.1 注册",url:currentUrl + "#sub-title-2.1",target:"_self"},
			{ name:"2.2 登录",url:currentUrl + "#sub-title-2.2",target:"_self",
				children: [
					{ name:"2.2.1 直接登录",url:currentUrl + "#sub-title-2.2.1",target:"_self"},
					{ name:"2.2.2 未激活",url:currentUrl + "#sub-title-2.2.2",target:"_self"},
					{ name:"2.2.3 忘记密码",url:currentUrl + "#sub-title-2.2.3",target:"_self"}
				]
			}
		]
	},
	{ name:"3 用户后台",url:currentUrl + "#sub-title-3",target:"_self",
		children: [
			{ name:"3.1 首页",url:currentUrl + "#sub-title-3.1",target:"_self"},
			{ name:"3.2 项目管理",url:currentUrl + "#sub-title-3.2",target:"_self",
				children: [
					{ name:"3.2.1 项目列表",url:currentUrl + "#sub-title-3.2.1",target:"_self"},
					{ name:"3.2.2 项目详情",url:currentUrl + "#sub-title-3.2.2",target:"_self",
						children: [
							{ name:"3.2.2.1 项目基本信息",url:currentUrl + "#sub-title-3.2.2.1",target:"_self"},
							{ name:"3.2.2.2 项目成员",url:currentUrl + "#sub-title-3.2.2.2",target:"_self"},
							{ name:"3.2.2.3 变更通知",url:currentUrl + "#sub-title-3.2.2.3",target:"_self"},
							{ name:"3.2.2.4 变更日志",url:currentUrl + "#sub-title-3.2.2.4",target:"_self"},
							{ name:"3.2.2.5 接口管理",url:currentUrl + "#sub-title-3.2.2.5",target:"_self",
								children: [
									{ name:"3.2.2.5.1 Api文档信息",url:currentUrl + "#sub-title-3.2.2.5.1",target:"_self"},
									{ name:"3.2.2.5.2 公共请求参数",url:currentUrl + "#sub-title-3.2.2.5.2",target:"_self"},
									{ name:"3.2.2.5.3  返回码",url:currentUrl + "#sub-title-3.2.2.5.3",target:"_self"},
									{ name:"3.2.2.5.4  创建模块",url:currentUrl + "#sub-title-3.2.2.5.4",target:"_self"},
									{ name:"3.2.2.5.5  接口列表",url:currentUrl + "#sub-title-3.2.2.5.5",target:"_self"},
									{ name:"3.2.2.5.6  创建接口",url:currentUrl + "#sub-title-3.2.2.5.6",target:"_self"},
									{ name:"3.2.2.5.7  快捷工具栏",url:currentUrl + "#sub-title-3.2.2.5.7",target:"_self"},
									{ name:"3.2.2.5.8 Mock服务",url:currentUrl + "#sub-title-3.2.2.5.8",target:"_self"},
								]},
							{ name:"3.2.2.6  数据结构",url:currentUrl + "#sub-title-3.2.2.6",target:"_self"},
							{ name:"3.2.2.7  文档视图",url:currentUrl + "#sub-title-3.2.2.7",target:"_self"},
							{ name:"3.2.2.8  项目角色",url:currentUrl + "#sub-title-3.2.2.8",target:"_self"},
							{ name:"3.2.2.9  接口归档",url:currentUrl + "#sub-title-3.2.2.9",target:"_self"}
						]
					}
				]
			},
			{ name:"3.3 Api文档管理",url:currentUrl + "#sub-title-3.3",target:"_self"},
			{ name:"3.4 个人中心",url:currentUrl + "#sub-title-3.4",target:"_self",
				children: [
					{ name:"3.4.1 账号管理",url:currentUrl + "#sub-title-3.4.1",target:"_self"},
					{ name:"3.4.2 消息管理",url:currentUrl + "#sub-title-3.4.2",target:"_self"}
				]
			},
			{ name:"3.5 意见反馈",url:currentUrl + "#sub-title-3.5",target:"_self"},
			{ name:"3.6 网站监控",url:currentUrl + "#sub-title-3.6",target:"_self",
				children: [
					{ name:"3.6.1 http监控管理",url:currentUrl + "#sub-title-3.6.1",target:"_self"},
					{ name:"3.6.2 监控日志",url:currentUrl + "#sub-title-3.6.2",target:"_self"},
					{ name:"3.6.3 警报日志",url:currentUrl + "#sub-title-3.6.3",target:"_self"},
					{ name:"3.6.4 警报接收者管理",url:currentUrl + "#sub-title-3.6.4",target:"_self"},
					{ name:"3.6.5 警报接收组管理",url:currentUrl + "#sub-title-3.6.5",target:"_self"}
				]
			}
		]
	},
	{ name:"4 管理员后台",url:currentUrl + "#sub-title-4",target:"_self",
		children: [
			{ name:"4.1 系统管理",url:currentUrl + "#sub-title-4.1",target:"_self",
				children: [
					{ name:"4.1.1 用户管理",url:currentUrl + "#sub-title-4.1.1",target:"_self"},
					{ name:"4.1.2 角色管理",url:currentUrl + "#sub-title-4.1.2",target:"_self"},
					{ name:"4.1.3 消息管理",url:currentUrl + "#sub-title-4.1.3",target:"_self"},
					{ name:"4.1.4 系统参数",url:currentUrl + "#sub-title-4.1.4",target:"_self"}
				]
			},
			{ name:"4.2 运营管理",url:currentUrl + "#sub-title-4.2",target:"_self",
				children: [
					{ name:"4.2.1 用户统计",url:currentUrl + "#sub-title-4.2.1",target:"_self"},
					{ name:"4.2.2 项目管理",url:currentUrl + "#sub-title-4.2.2",target:"_self"},
					{ name:"4.2.3 用户反馈",url:currentUrl + "#sub-title-4.2.3",target:"_self"},
					{ name:"4.2.4 系统项目角色",url:currentUrl + "#sub-title-4.2.4",target:"_self"}
				]
			},
			{ name:"4.3 网站监控",url:currentUrl + "#sub-title-4.3",target:"_self",
				children: [
					{ name:"4.3.1 http监控管理",url:currentUrl + "#sub-title-4.3.1",target:"_self"},
					{ name:"4.3.2 监控日志",url:currentUrl + "#sub-title-4.3.2",target:"_self"},
					{ name:"4.3.3 警报日志",url:currentUrl + "#sub-title-4.3.3",target:"_self"}
				]
			},
		]
	}
];

/*{ name:"4.1 用户统计",url:currentUrl + "#sub-title-4.1",target:"_self"},
{ name:"4.2 用户管理",url:currentUrl + "#sub-title-4.2",target:"_self"},
{ name:"4.3 项目管理",url:currentUrl + "#sub-title-4.3",target:"_self"},
{ name:"4.4 用户反馈",url:currentUrl + "#sub-title-4.4",target:"_self"},
{ name:"4.5 消息管理",url:currentUrl + "#sub-title-4.5",target:"_self"}*/

$(function(){
	$.fn.zTree.init($("#manualTree"), treeSetting, treeData);
	
	$("img.lazy").lazyload();
	
	$(".header-section .toggle-btn").unbind("click");
});

