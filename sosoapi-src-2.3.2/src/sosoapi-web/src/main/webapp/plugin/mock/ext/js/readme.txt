mock-java.js由mock.js(v1.0.1-beta3)改造而来，用于后端java调用js
改造点如下：
1、将原mock.js中的方法名为关键字的，如boolean,int改成字符串，不影响使用
2、mock.js原文件末尾添加json2.js源文件(https://github.com/douglascrockford/JSON-js/blob/master/json2.js)
3、代码改造

Mock.mock = function(rurl, rtype, template) {
	    // Mock.mock(template)
	    if (arguments.length === 1) {
	        return Handler.gen(rurl);
	    }
	    
变更为

Mock.mock = function(rurl, rtype, template) {
	    // Mock.mock(template)
	    if (arguments.length === 1) {
	    	//扩展，解决java调用js返回值int全部被转换为double问题
	        /*return Handler.gen(rurl);*/
	    	return JSON.stringify(Handler.gen(rurl),null,2); 
	    }
