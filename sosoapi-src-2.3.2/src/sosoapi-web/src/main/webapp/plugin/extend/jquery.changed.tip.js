/**
 * 离开未保存时提示
 */
;(function($, window, document,undefined) {
	//保存原值的data属性名称
	var oriValAttrName = '_value';
	
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
	
	//定义ChangedTip的构造函数
    var ChangedTip = function(ele, opt) {
        this.$element = $(ele);
        this.options = $.extend(true, {}, $.fn.changedTip.defaults, opt);
    }
    
    //定义changedTip的方法
    ChangedTip.prototype = {
    	//保存原值
		storeVal:function(){
			$(':text, :password, textarea', this.$element).each(function() {       
		        $(this).attr(oriValAttrName, $(this).val());       
		    });     
		     
		    $(':checkbox, :radio', this.$element).each(function() {       
		        $(this).attr(oriValAttrName, this.checked ? 'on' : 'off');       
		    });     
		     
		    $('select', this.$element).each(function() {    
		    	var selectedOption = this.options[this.selectedIndex];
		    	if(typeof(selectedOption) != 'undefined'){
		    		$(this).attr(oriValAttrName, selectedOption.value);    
		    	}
		    });   
		},
		
		//清除保存缓存
		clearCache:function(){
			$(':text, :password, textarea', this.$element).each(function() {  
				$(this).removeAttr(oriValAttrName);
		    });     
		     
		    $(':checkbox, :radio', this.$element).each(function() {       
		    	$(this).removeAttr(oriValAttrName);   
		    });     
		     
		    $('select', this.$element).each(function() {       
		    	$(this).removeAttr(oriValAttrName);    
		    });   
		},
		
		//恢复原值
		recoverVal:function(){
			$(':text, :password, textarea', this.$element).each(function() {       
				$(this).val($(this).attr(oriValAttrName));       
		    });     
		     
		    $(':checkbox, :radio', this.$element).each(function() {   
		    	var oriVal = $(this).attr(oriValAttrName);
		    	$(this).prop('checked', oriVal == 'on');
		    });     
		     
		    $('select', this.$element).each(function() {       
		    	$(this).val($(this).attr(oriValAttrName));       
		    });   
		},
		
		//判断是否有变更
		isChanged:function(){
			var changed = false;       
		    $(':text, :password, textarea', this.$element).each(function() {       
		        var oriVal = $(this).attr(oriValAttrName);       
		        if(typeof(oriVal) == 'undefined'){
		        	return ;//相当于condinue     
		        }
		        
		        if(oriVal != $(this).val()){
		        	changed = true;       
		        }
		    });       
		     
		    $(':checkbox, :radio', this.$element).each(function() {       
		        var oriVal = $(this).attr(oriValAttrName);       
		        if(typeof(oriVal) == 'undefined'){
		        	return ;//相当于condinue     
		        }
		        
		        var val = this.checked ? 'on' : 'off'; 
		        if(val != oriVal){
		        	changed = true;       
		        }
		    });       
		      
		    $('select', this.$element).each(function() {       
		        var oriVal = $(this).attr(oriValAttrName);       
		        if(typeof(oriVal) == 'undefined'){
		        	return ;//相当于condinue     
		        }

		        var selectedVal = '';
		        var selectedOption = this.options[this.selectedIndex];
		    	if(typeof(selectedOption) != 'undefined'){
		    		selectedVal = selectedOption.value;
		    	}
		        
		        if(oriVal != selectedVal){
		        	changed = true;     
		        }
		    });       
		    
		    return changed;   
		},
		
		//绑定onbeforeunload事件
		bindWindow:function(){
			var plugin = this;
			window.onbeforeunload = function() {  
				if(plugin.isChanged()){
					return plugin.options.tip;
				}
		    }       
		},
		
		//取消绑定onbeforeunload事件
		unbindWindow:function(){
			window.onbeforeunload = null;
		},
		
		//默认初始化
		init:function(){
			this.storeVal();
			this.bindWindow();
		},
		
		//忽略变更
		skip:function(){
			this.unbindWindow();
			this.clearCache();
		}
    }
    
    //初始化
    //支持初始化和方法调用
    $.fn.changedTip = function(params){
        var $element = this;
        var retVal = null;
        var argumentsTemp = arguments;
        var isFunCall = false;
        //插件名称，保存到对象data中
    	var pluginName = "changedTip";
        $element.each(function(){
        	var $this = $(this);
            var plugin = $this.data(pluginName);
            if (!plugin) {
            	$this.data(pluginName, new ChangedTip(this, params));
            	$this.data(pluginName + "-id", new Date().getTime());
            } 
            else {
                if (typeof params === 'string' && typeof plugin[params] === 'function') {
                	isFunCall = true;
                	retVal = plugin[params].apply(plugin, Array.prototype.slice.call(argumentsTemp, 1));
                }
            }
        });

        return isFunCall ? retVal : $element;
    };
    
    //默认配置
    $.fn.changedTip.defaults = {
		//未保存时提示信息
    	tip:'您的修改内容还没有保存,您确定离开吗?'
    }
})(window.jQuery || window.Zepto, window, document);