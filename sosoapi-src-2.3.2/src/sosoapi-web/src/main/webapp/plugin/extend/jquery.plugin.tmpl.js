;(function($, window, document,undefined) {
	//执行指定方法
	function execFun(fun,funParams){
		if(fun != null && typeof fun === 'function'){
			var args = [];
			for(var i = 1; i < arguments.length; i++) {
				args.push(arguments[i]);
			}
			
			fun.apply(this, args);
    	}
	}
	
	//定义Beautifier的构造函数
    var Beautifier = function(ele, opt) {
        this.$element = $(ele);
        this.options = $.extend(true, {}, $.fn.beautifier.defaults, opt);
    }
    
    //定义Beautifier的方法
    Beautifier.prototype = {
		//获取配置信息
        getOptions:function(key){
        	if(key){
        		return this.options[key];
        	}
        	else{
        		return this.options;
        	}
        },
        
        //设置配置信息
        setOptions:function(key,value){
        	this.options[key] = value;
        },
            
        //设置样式
        beautify: function() {
        	//执行前置方法
        	execFun(this.options.beforeBeauty);
        	
            return this.$element.css({
                'color': this.options.color,
                'fontSize': this.options.fontSize,
                'textDecoration': this.options.textDecoration
            });
        }
    }
    
    //初始化
    //支持初始化和方法调用
    $.fn.beautifier = function(params){
        var $element = this;
        var retVal = this;
        var argumentsTemp = arguments;
        //插件名称，保存到对象data中
    	var pluginName = "beautifier";
        $element.each(function(){
        	var $this = $(this);
            var plugin = $this.data(pluginName);
            if (!plugin) {
            	$this.data(pluginName, new Beautifier(this, params));
            	$this.data(pluginName + "-id", new Date().getTime());
            } 
            else {
                if (typeof params === 'string' && typeof plugin[params] === 'function') {
                	retVal = plugin[params].apply(plugin, Array.prototype.slice.call(argumentsTemp, 1));
                }
            }
        });

        return retVal || $element;
    };
    
    //默认配置
    $.fn.beautifier.defaults = {
		'color': 'red',
        'fontSize': '12px',
        'textDecoration': 'none',
        
        //前置方法
        beforeBeauty:null,
    }
})(window.jQuery || window.Zepto, window, document);