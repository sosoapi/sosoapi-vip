/*!
 * Nestable jQuery Plugin - Copyright (c) 2012 David Bushell - http://dbushell.com/
 * Dual-licensed under the BSD or MIT licenses
 */
;(function($, window, document, undefined){
    var hasTouch = 'ontouchstart' in document;

    /**
     * Detect CSS pointer-events property
     * events are normally disabled on the dragging element to avoid conflicts
     * https://github.com/ausi/Feature-detection-technique-for-pointer-events/blob/master/modernizr-pointerevents.js
     */
    var hasPointerEvents = (function(){
        var el = document.createElement('div');
        var docEl = document.documentElement;
        if (!('pointerEvents' in el.style)) {
            return false;
        }
        el.style.pointerEvents = 'auto';
        el.style.pointerEvents = 'x';
        docEl.appendChild(el);
        var supports = window.getComputedStyle && window.getComputedStyle(el, '').pointerEvents === 'auto';
        docEl.removeChild(el);
        return !!supports;
    })();

    function Plugin(element, options){
        this.w  = $(document);
        this.el = $(element);
        this.options = $.extend({}, $.fn.nestable.defaults, options);
        this.init();
    }

    Plugin.prototype = {
        init: function(){
            var list = this;
            list.reset();
            list.el.data('nestable-group', list.options.group);
            list.placeEl = $('<div class="' + list.options.placeClass + '"/>');

            $.each(this.el.find(list.options.itemNodeName + "." + list.options.itemClass), function(k, el) {
                list.setParent($(el));
            });

            list.el.on('click', 'button', function(e) {
                if (list.dragEl) {
                    return;
                }
                var target = $(e.currentTarget);
                var action = target.data('action');
                var item = target.parent(list.options.itemNodeName);
                if (action === 'collapse') {
                    list.collapseItem(item);
                }
                else if (action === 'expand') {
                    list.expandItem(item);
                }
                else if (action === 'addChild') {
                	if(list.options.getItemTmpl != null 
                			&& typeof list.options.getItemTmpl === 'function'){
                		list.addChild(item,list.options.getItemTmpl());
                	}
                }
                else if (action === 'removeItem') {
                    list.removeItem(item);
                }
            });

            var onStartEvent = function(e){
                var handle = $(e.target);
                if (!handle.hasClass(list.options.handleClass)) {
                    if (handle.closest('.' + list.options.noDragClass).length) {
                        return;
                    }
                    handle = handle.closest('.' + list.options.handleClass);
                }

                if (!handle.length || list.dragEl) {
                    return;
                }

                list.isTouch = /^touch/.test(e.type);
                if (list.isTouch && e.touches.length !== 1) {
                    return;
                }

                e.preventDefault();
                list.dragStart(e.touches ? e.touches[0] : e);
            };

            var onMoveEvent = function(e){
                if (list.dragEl) {
                    e.preventDefault();
                    list.dragMove(e.touches ? e.touches[0] : e);
                }
            };

            var onEndEvent = function(e){
                if (list.dragEl) {
                    e.preventDefault();
                    list.dragStop(e.touches ? e.touches[0] : e);
                }
            };

            if (hasTouch) {
                list.el[0].addEventListener('touchstart', onStartEvent, false);
                window.addEventListener('touchmove', onMoveEvent, false);
                window.addEventListener('touchend', onEndEvent, false);
                window.addEventListener('touchcancel', onEndEvent, false);
            }

            list.el.on('mousedown', onStartEvent);
            list.w.on('mousemove', onMoveEvent);
            list.w.on('mouseup', onEndEvent);
        },

        serialize: function(){
        	var data;
            var depth = 0;
            var list  = this;
            var step = function(level, depth){
                var array = [ ];
                var items = level.children(list.options.itemNodeName);
                items.each(function(){
                    var li = $(this);
                    var item = $.extend({},list.options.getItemContent(li));
                    var sub  = li.children(list.options.listNodeName);
                    if (sub.length) {
                        item.children = step(sub, depth + 1);
                    }
                    array.push(item);
                });
                return array;
            };
            data = step(list.el.find(list.options.listNodeName).first(), depth);
            return data;
        },

        reset: function(){
            this.mouse = {
                offsetX   : 0,
                offsetY   : 0,
                startX    : 0,
                startY    : 0,
                lastX     : 0,
                lastY     : 0,
                nowX      : 0,
                nowY      : 0,
                distX     : 0,
                distY     : 0,
                dirAx     : 0,
                dirX      : 0,
                dirY      : 0,
                lastDirX  : 0,
                lastDirY  : 0,
                distAxX   : 0,
                distAxY   : 0
            };
            this.isTouch    = false;
            this.moving     = false;
            this.dragEl     = null;
            this.dragRootEl = null;
            this.dragDepth  = 0;
            this.hasNewRoot = false;
            this.pointEl    = null;
        },

        //隐藏操作
        hideAction:function(li,action){
        	li.children('[data-action="' + action + '"]').hide();
    	},
        
    	//新增子节点
        addChild: function(li,jqTmpl){
        	var opt = this.options;
        	if(!jqTmpl){
        		jqTmpl = opt.getItemTmpl();
        	}
        	
        	if(li){//给指定节点添加子节点
        		var list = li.find(opt.listNodeName).first();
                var depth = li.parents(opt.listNodeName).length;
                if (depth + 1 <= opt.maxDepth) {
                    if (!list.length) {
                        list = $('<' + opt.listNodeName + '/>').addClass(opt.listClass);
                        li.append(list);
                    } 
                    
                    list.append(jqTmpl);
                    this.setParent(jqTmpl);
                    this.setParent(li);
                    
                    this.el.trigger('change');
                }
        	}
        	else{//添加根节点
        		var rootList = this.el.children(opt.listNodeName);
        		if(rootList.length == 0){
        			rootList = $(opt.itemListStructHTML);
        			this.el.append(rootList);
        		}
        		
        		rootList.append(jqTmpl);
                this.setParent(jqTmpl);
                this.el.trigger('change');
        	}
        	
        	//回调函数
        	if(opt.afterAddChild != null 
        			&& typeof opt.afterAddChild === 'function'){
        		opt.afterAddChild(jqTmpl);
        	}
        },

        //删除节点
        removeItem: function(li){
        	var temp = li;
        	var opt = this.options;
        	var parent = li.parents(opt.listNodeName).first();
            li.remove();
            
            if (parent.children().length == 0) {
            	//渲染父节点
            	var tempItem = parent.parent(opt.itemNodeName).first();
            	if(tempItem){
            		this.unsetParent(tempItem);
            	}
                
            	parent.remove();
            }
            
            this.el.trigger('change');
            
            //回调函数
        	if(opt.afterRemoveItem != null 
        			&& typeof opt.afterRemoveItem === 'function'){
        		opt.afterRemoveItem(temp);
        	}
        },
        
        expandItem: function(li){
            li.removeClass(this.options.collapsedClass);
            li.children('[data-action="expand"]').hide();
            li.children('[data-action="collapse"]').show();
            li.children(this.options.listNodeName).show();
        },

        collapseItem: function(li){
            var lists = li.children(this.options.listNodeName);
            if (lists.length) {
                li.addClass(this.options.collapsedClass);
                li.children('[data-action="collapse"]').hide();
                li.children('[data-action="expand"]').show();
                li.children(this.options.listNodeName).hide();
            }
        },

        expandAll: function(){
            var list = this;
            list.el.find(list.options.itemNodeName).each(function() {
                list.expandItem($(this));
            });
        },

        collapseAll: function(){
            var list = this;
            list.el.find(list.options.itemNodeName).each(function() {
                list.collapseItem($(this));
            });
        },

        setParent: function(li){
        	li.children('[data-action]').remove();
        	
        	var opt = this.options;
        	if(opt.enableAddChild){
        		li.prepend($(opt.addChildBtnHTML));
        	}
        	if(opt.enableRemoveItem){
        		li.prepend($(opt.removeBtnHTML));
        	}
        	
        	if (li.children(opt.listNodeName).length > 0) {
                li.prepend($(opt.expandBtnHTML));
                li.prepend($(opt.collapseBtnHTML));
            }
        	
            li.children('[data-action="expand"]').hide();
        },

        unsetParent: function(li){
        	var opt = this.options;
        	
            li.removeClass(opt.collapsedClass);
            li.children('[data-action]').remove();
            if(opt.enableAddChild){
        		li.prepend($(opt.addChildBtnHTML));
        	}
        	if(opt.enableRemoveItem){
        		li.prepend($(opt.removeBtnHTML));
        	}
            
            li.children(opt.listNodeName).remove();
        },
        
        //加载数据
        loadData:function(itemList){
        	this.options.loadData(this.el,itemList);
        },
        
        //获取数据
        getData:function(){
        	return this.options.getData(this);
        },
        
        //清空
        clear:function(){
        	$(this.el).children(this.options.listNodeName).first().remove();
        },
        
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
        
        dragStart: function(e){
            var mouse = this.mouse;
            var target = $(e.target);
            var dragItem = target.closest(this.options.itemNodeName);

            this.placeEl.css('height', dragItem.height());

            mouse.offsetX = e.offsetX !== undefined ? e.offsetX : e.pageX - target.offset().left;
            mouse.offsetY = e.offsetY !== undefined ? e.offsetY : e.pageY - target.offset().top;
            mouse.startX = mouse.lastX = e.pageX;
            mouse.startY = mouse.lastY = e.pageY;

            this.dragRootEl = this.el;

            this.dragEl = $(document.createElement(this.options.listNodeName)).addClass(this.options.listClass + ' ' + this.options.dragClass);
            this.dragEl.css('width', dragItem.width());

            dragItem.after(this.placeEl);
            dragItem[0].parentNode.removeChild(dragItem[0]);
            dragItem.appendTo(this.dragEl);

            $(document.body).append(this.dragEl);
            this.dragEl.css({
                'left' : e.pageX - mouse.offsetX,
                'top'  : e.pageY - mouse.offsetY
            });
            // total depth of dragging item
            var i;
            var depth;
            var items = this.dragEl.find(this.options.itemNodeName);
            for (i = 0; i < items.length; i++) {
                depth = $(items[i]).parents(this.options.listNodeName).length;
                if (depth > this.dragDepth) {
                    this.dragDepth = depth;
                }
            }
        },

        dragStop: function(e){
            var el = this.dragEl.children(this.options.itemNodeName).first();
            el[0].parentNode.removeChild(el[0]);
            this.placeEl.replaceWith(el);

            this.dragEl.remove();
            this.el.trigger('change');
            if (this.hasNewRoot) {
                this.dragRootEl.trigger('change');
            }
            this.reset();
        },

        dragMove: function(e){
            var list;
            var parent;
            var prev;
            var next;
            var depth;
            var opt = this.options;
            var mouse = this.mouse;

            this.dragEl.css({
                'left' : e.pageX - mouse.offsetX,
                'top'  : e.pageY - mouse.offsetY
            });

            // mouse position last events
            mouse.lastX = mouse.nowX;
            mouse.lastY = mouse.nowY;
            // mouse position this events
            mouse.nowX  = e.pageX;
            mouse.nowY  = e.pageY;
            // distance mouse moved between events
            mouse.distX = mouse.nowX - mouse.lastX;
            mouse.distY = mouse.nowY - mouse.lastY;
            // direction mouse was moving
            mouse.lastDirX = mouse.dirX;
            mouse.lastDirY = mouse.dirY;
            // direction mouse is now moving (on both axis)
            mouse.dirX = mouse.distX === 0 ? 0 : mouse.distX > 0 ? 1 : -1;
            mouse.dirY = mouse.distY === 0 ? 0 : mouse.distY > 0 ? 1 : -1;
            // axis mouse is now moving on
            var newAx   = Math.abs(mouse.distX) > Math.abs(mouse.distY) ? 1 : 0;

            // do nothing on first move
            if (!mouse.moving) {
                mouse.dirAx  = newAx;
                mouse.moving = true;
                return;
            }

            // calc distance moved on this axis (and direction)
            if (mouse.dirAx !== newAx) {
                mouse.distAxX = 0;
                mouse.distAxY = 0;
            } else {
                mouse.distAxX += Math.abs(mouse.distX);
                if (mouse.dirX !== 0 && mouse.dirX !== mouse.lastDirX) {
                    mouse.distAxX = 0;
                }
                mouse.distAxY += Math.abs(mouse.distY);
                if (mouse.dirY !== 0 && mouse.dirY !== mouse.lastDirY) {
                    mouse.distAxY = 0;
                }
            }
            mouse.dirAx = newAx;

            /**
             * move horizontal
             */
            if (opt.enableHorizontalMove && mouse.dirAx && mouse.distAxX >= opt.threshold) {
                // reset move distance on x-axis for new phase
                mouse.distAxX = 0;
                prev = this.placeEl.prev(opt.itemNodeName);
                // increase horizontal level if previous sibling exists and is not collapsed
                if (mouse.distX > 0 && prev.length && !prev.hasClass(opt.collapsedClass)) {
                    // cannot increase level when item above is collapsed
                    list = prev.find(opt.listNodeName).last();
                    // check if depth limit has reached
                    depth = this.placeEl.parents(opt.listNodeName).length;
                    if (depth + this.dragDepth <= opt.maxDepth) {
                        // create new sub-level if one doesn't exist
                        if (!list.length) {
                            list = $('<' + opt.listNodeName + '/>').addClass(opt.listClass);
                            list.append(this.placeEl);
                            prev.append(list);
                            this.setParent(prev);
                        } else {
                            // else append to next level up
                            list = prev.children(opt.listNodeName).last();
                            list.append(this.placeEl);
                        }
                    }
                }
                // decrease horizontal level
                if (mouse.distX < 0) {
                    // we can't decrease a level if an item preceeds the current one
                    next = this.placeEl.next(opt.itemNodeName);
                    if (!next.length) {
                        parent = this.placeEl.parent();
                        this.placeEl.closest(opt.itemNodeName).after(this.placeEl);
                        if (!parent.children().length) {
                            this.unsetParent(parent.parent());
                        }
                    }
                }
            }

            var isEmpty = false;

            // find list item under cursor
            if (!hasPointerEvents) {
                this.dragEl[0].style.visibility = 'hidden';
            }
            this.pointEl = $(document.elementFromPoint(e.pageX - document.body.scrollLeft, e.pageY - (window.pageYOffset || document.documentElement.scrollTop)));
            if (!hasPointerEvents) {
                this.dragEl[0].style.visibility = 'visible';
            }
            if (this.pointEl.hasClass(opt.handleClass)) {
                this.pointEl = this.pointEl.parent(opt.itemNodeName);
            }
            if (this.pointEl.hasClass(opt.emptyClass)) {
                isEmpty = true;
            }
            else if (!this.pointEl.length || !this.pointEl.hasClass(opt.itemClass)) {
                return;
            }

            // find parent list of item under cursor
            var pointElRoot = this.pointEl.closest('.' + opt.rootClass),
                isNewRoot   = this.dragRootEl.data('nestable-id') !== pointElRoot.data('nestable-id');

            /**
             * move vertical
             */
            if (opt.enableVerticalMove && (!mouse.dirAx || isNewRoot || isEmpty)) {
                // check if groups match if dragging over new root
                if (isNewRoot && opt.group !== pointElRoot.data('nestable-group')) {
                    return;
                }
                // check depth limit
                depth = this.dragDepth - 1 + this.pointEl.parents(opt.listNodeName).length;
                if (depth > opt.maxDepth) {
                    return;
                }
                var before = e.pageY < (this.pointEl.offset().top + this.pointEl.height() / 2);
                parent = this.placeEl.parent();
                // if empty create new list to replace empty placeholder
                if (isEmpty) {
                    list = $(document.createElement(opt.listNodeName)).addClass(opt.listClass);
                    list.append(this.placeEl);
                    this.pointEl.replaceWith(list);
                }
                else if (before) {
                    this.pointEl.before(this.placeEl);
                }
                else {
                    this.pointEl.after(this.placeEl);
                }
                if (!parent.children().length) {
                    this.unsetParent(parent.parent());
                }
                if (!this.dragRootEl.find(opt.itemNodeName).length) {
                    this.dragRootEl.append('<div class="' + opt.emptyClass + '"/>');
                }
                // parent root list has changed
                if (isNewRoot) {
                    this.dragRootEl = pointElRoot;
                    this.hasNewRoot = this.el[0] !== this.dragRootEl[0];
                }
            }
        }
    };

    $.fn.nestable = function(params){
        var lists  = this;
        var retval = this;
        var argumentsTemp = arguments;
        lists.each(function(){
            var plugin = $(this).data("nestable");

            if (!plugin) {
                $(this).data("nestable", new Plugin(this, params));
                $(this).data("nestable-id", new Date().getTime());
            } 
            else {
                if (typeof params === 'string' && typeof plugin[params] === 'function') {
                	retval = plugin[params].apply(plugin, Array.prototype.slice.call(argumentsTemp, 1));
                }
            }
        });

        return retval || lists;
    };
    
    $.fn.nestable.defaults = {
        listNodeName    : 'ol',
        itemNodeName    : 'li',
        rootClass       : 'dd',
        listClass       : 'dd-list',
        itemClass       : 'dd-item',
        dragClass       : 'dd-dragel',
        handleClass     : 'dd-handle',
        collapsedClass  : 'dd-collapsed',
        placeClass      : 'dd-placeholder',
        noDragClass     : 'dd-nodrag',
        emptyClass      : 'dd-empty',
        expandBtnHTML   : '<button data-action="expand" type="button">Expand</button>',
        collapseBtnHTML : '<button data-action="collapse" type="button">Collapse</button>',
        addChildBtnHTML : '<button data-action="addChild" type="button">AddChild</button>',
        removeBtnHTML 	: '<button data-action="removeItem" type="button">RemoveItem</button>',
        group           : 0,
        maxDepth        : 5,
        threshold       : 20,
        
        //操作控制
        enableAddChild:true,
        enableRemoveItem:true,
        enableVerticalMove:true,
        enableHorizontalMove:true,
        
        //节点结构html
        itemStructHTML: '<li class="dd-item dd-item-drag">' +
							'<div class="dd-handle dd-handle-drag"></div>' +
							'<div class="dd-content"></div>' +
					    '</li',
		
		//节点列表结构html
		itemListStructHTML:'<ol class="dd-list"></ol>',
        
		//新增节点后的回调函数
		afterAddChild:function(li){
			
		},
		
		//删除节点后的回调函数
		afterRemoveItem:function(li){
			
		},
		
        //获取item整体结构模板,jquery对象
        //无需重载
        getItemTmpl:function(){
        	var itemContentTmpl = this.getItemContentTmpl();
        	var itemTmpl = $(this.itemStructHTML);
        	if(itemContentTmpl){
        		itemTmpl.find(".dd-content")
    	        		.append(itemContentTmpl);
        	}
        	
        	return itemTmpl;
        },
        
        //获取item中的展示内容模板,jquery对象
        //按需要重载
        getItemContentTmpl:function(){
        	return $('<input class="form-control"/>');
        },
        
        //获取item序列化内容
        //按需要重载
        getItemContent:function(li){
        	return li.children(".dd-content").find("*").getFieldsValue();
        },
        
        //获取数据
        getData:function(list){
        	var result = [];
        	var step = function(id,itemList){
        		var subId = id * 100 + 10;
            	$.each(itemList, function(index, item){
            		item["nodeId"] = "" + subId;
            		item["parentId"] = id == 0 ? null : "" + id;
            		
            		var children = item.children;
            		if(children){
            			delete item.children;
            			result.push(item);
            			step(subId,children);
            		}
            		else{
            			result.push(item);
            		}
            		
            		subId ++;
            	});
        	};
        	
        	step(0,list.serialize());
        	return result;
        },
        
        //加载数据
        loadData:function(element,itemDataList){
        	if(!itemDataList || itemDataList.length == 0){
        		return ;
        	}
        	
        	var opt = this;
        	var rootItem = $(opt.itemListStructHTML);
        	$.each(itemDataList,function(index,itemData){
        		var item = $(opt.itemStructHTML);
        		item.attr("data-id",itemData.nodeId);
        		item.find(".dd-content")
        			.append(opt.getItemContentTmpl());
        		item.find('*').setFieldsValue(itemData);
        		item.data("itemData",itemData);
        		
        		if(!itemData.parentId || itemData.parentId == null){
        			rootItem.append(item);
        		}
        		else{
        			var parentItem = rootItem.find("[data-id=" + itemData.parentId + "]").first();
        			var children = parentItem.find(opt.listNodeName).first();
        			if(!children || children.length == 0){
        				children = $(opt.itemListStructHTML);
        				parentItem.append(children);
        			}
        			
        			children.append(item);
        		}
        	});
        	
        	//渲染
        	var nestable = $(element);
        	//先清空再加载
        	nestable.nestable("clear");
        	nestable.append(rootItem);
        	$.each(element.find(opt.itemNodeName + "." + opt.itemClass), function(k, el) {
        		nestable.nestable("setParent",$(el));
        		opt.afterAddChild($(el));
            });
        }
    };
})(window.jQuery || window.Zepto, window, document);
