(function($) {
    $.fn.bootstrapValidator.validators.phoneExt = {
        /**
         * 验证手机号格式
         */
        validate: function(validator, $field, options) {
        	var value = $field.val();
        	if (value === '') {
                return true;
            }
        	
        	var pattern = /^1[0-9]{10}$/;
        	return value.match(pattern) != null;
        }
    };
    
    $.fn.bootstrapValidator.validators.cardNo = {
        /**
         * 验证身份证格式
         */
        validate: function(validator, $field, options) {
        	var value = $field.val();
        	if (value === '') {
                return true;
            }
        	
        	var pattern = /(^\d{15}$)|(^\d{17}(\d|X|x)$)/;
        	return value.match(pattern) != null;
        }
    };
    
    $.fn.bootstrapValidator.validators.notEmptyCascade = {
        /**
         * 指定对象满足指定值时非空
         */
        validate: function(validator, $field, options) {
        	var cascadeField = validator.getFieldElements(options.field);
            if (cascadeField === null) {
                return true;
            }
            
            return !(cascadeField.val() === options.value && $.trim($field.val()) === '');
        }
    };
    
    $.fn.bootstrapValidator.validators.startWith = {
        /**
         * 指定对象以指定值开头
         */
        validate: function(validator, $field, options) {
        	var value = $field.val();
        	if (value === '') {
                return true;
            }
        	
        	var result = false;
        	var array = new Array();
        	array = options.value.split(",");
        	for(var i = 0; i < array.length; i ++){
        		if(value.indexOf(options.value) == 0){
        			result = true;
        			break;
        		}
        	}
        	
        	return result;
        }
    };
    
    $.fn.bootstrapValidator.validators.url = {
        /**
         * 验证url格式
         */
        validate: function(validator, $field, options) {
        	var value = $field.val();
        	if (value === '') {
                return true;
            }
        	
        	value = value.toLowerCase();
        	return value.indexOf("http:") == 0 || value.indexOf("https:");
        }
    };
}(window.jQuery));