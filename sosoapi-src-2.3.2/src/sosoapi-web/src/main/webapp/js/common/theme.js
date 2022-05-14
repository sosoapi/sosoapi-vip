$(function(){
	//初始化主题
	initTheme();
	
	//初始化控件
	initComponent();
});

//初始化
function initTheme(){
	// Sidebar toggle
	$('.menu-list > a').click(function() {
		var parent = $(this).parent();
		var sub = parent.find('> ul');

		if (!$('body').hasClass('sidebar-collapsed')) {
			if (sub.is(':visible')) {
				sub.slideUp(300, function() {
					/*parent.removeClass('nav-active');*/
					$('.body-content').css({
						height : ''
					});
					adjustMainContentHeight();
				});
			} else {
				visibleSubMenuClose();
				parent.addClass('nav-active');
				sub.slideDown(300, function() {
					adjustMainContentHeight();
				});
			}
		}
		return false;
	});

	// add class mouse hover
	$('.side-navigation > li').hover(function() {
		$(this).addClass('nav-hover');
	}, function() {
		$(this).removeClass('nav-hover');
	});

	// Toggle Menu
	$('.toggle-btn').click(function() {
		if($(".body-content.no-sidebar-left").length > 0){
			return false;
		}
		
		var body = $('body');
		var bodyPosition = body.css('position');

		if (bodyPosition != 'relative') {
			if (!body.hasClass('sidebar-collapsed')) {
				body.addClass('sidebar-collapsed');
				$('.side-navigation ul').attr('style', '');
			} else {
				body.removeClass('sidebar-collapsed');
				$('.side-navigation li.active ul').css({
					display : 'block'
				});
			}
		} else {
			if (body.hasClass('sidebar-open')) {
				body.removeClass('sidebar-open');
			} else {
				body.addClass('sidebar-open');
			}

			adjustMainContentHeight();
		}
	});

	$(window).resize(function() {
		if ($('body').css('position') == 'relative') {
			$('body').removeClass('sidebar-collapsed');
		} else {
			$('body').css({
				left : '',
				marginRight : ''
			});
		}

		searchform_reposition();
	});
	
	searchform_reposition();
}

function searchform_reposition() {
	if ($('.search-content').css('position') == 'relative') {
		$('.search-content').insertBefore(
				'.sidebar-left-info .search-field');
	} else {
		$('.search-content').insertAfter('.right-notification');
	}
}

function visibleSubMenuClose() {
	$('.menu-list').each(function() {
		var t = $(this);
		if (t.hasClass('nav-active')) {
			t.find('> ul').slideUp(300, function() {
				t.removeClass('nav-active');
			});
		}
	});
}

function adjustMainContentHeight() {
	var docHeight = $(document).height();
	if (docHeight > $('.body-content').height()) {
		$('.body-content').height(docHeight);
	}
}

function initComponent(){
	// Add special class to minimalize page elements when screen is less than 768px
    setBodySmall();

    // Initialize animate panel function
//    $('.animate-panel').animatePanel();
    
    // Function for collapse hpanel
    $('.showhide').on('click', function (event) {
        event.preventDefault();
        var hpanel = $(this).closest('div.hpanel');
        var icon = $(this).find('i:first');
        var body = hpanel.find('div.panel-body');
        var footer = hpanel.find('div.panel-footer');
        body.slideToggle(300);
        footer.slideToggle(200);

        // Toggle icon from up to down
        icon.toggleClass('fa-chevron-up').toggleClass('fa-chevron-down');
        hpanel.toggleClass('').toggleClass('panel-collapse');
        setTimeout(function () {
            hpanel.resize();
            hpanel.find('[id^=map-]').resize();
        }, 50);
    });

    // Function for close hpanel
    $('.closebox').on('click', function (event) {
        event.preventDefault();
        var hpanel = $(this).closest('div.hpanel');
        hpanel.remove();
        if($('body').hasClass('fullscreen-panel-mode')) { $('body').removeClass('fullscreen-panel-mode');}
    });

    // Fullscreen for fullscreen hpanel
    $('.fullscreen').on('click', function() {
        var hpanel = $(this).closest('div.hpanel');
        var icon = $(this).find('i:first');
        $('body').toggleClass('fullscreen-panel-mode');
        icon.toggleClass('fa-expand').toggleClass('fa-compress');
        hpanel.toggleClass('fullscreen');
        setTimeout(function() {
            $(window).trigger('resize');
        }, 100);
    });

    // Function for small header
    $('.small-header-action').on('click', function(event){
        event.preventDefault();
        var icon = $(this).find('i:first');
        var breadcrumb  = $(this).parent().find('#hbreadcrumb');
        $(this).parent().parent().parent().toggleClass('small-header');
        breadcrumb.toggleClass('m-t-lg');
        icon.toggleClass('fa-arrow-up').toggleClass('fa-arrow-down');
    });

    // Set minimal height of #wrapper to fit the window
    setTimeout(function () {
        fixWrapperHeight();
    });
    
    $(window).bind("resize click", function () {

        // Add special class to minimalize page elements when screen is less than 768px
        setBodySmall();

        // Waint until metsiMenu, collapse and other effect finish and set wrapper height
        setTimeout(function () {
            fixWrapperHeight();
        }, 300);
    });
    
    // Initialize popover
    $("[data-toggle=popover]").popover();

    // Move modal to body
    // Fix Bootstrap backdrop issu with animation.css
    $('.modal').appendTo("body");
}

function fixWrapperHeight() {
    /*// Get and set current height
    var headerH = 62;
    var navigationH = $("#navigation").height();
    var contentH = $(".content").height();

    // Set new height when contnet height is less then navigation
    if (contentH < navigationH) {
        $("#wrapper").css("min-height", navigationH + 'px');
    }

    // Set new height when contnet height is less then navigation and navigation is less then window
    if (contentH < navigationH && navigationH < $(window).height()) {
        $("#wrapper").css("min-height", $(window).height() - headerH  + 'px');
    }

    // Set new height when contnet is higher then navigation but less then window
    if (contentH > navigationH && contentH < $(window).height()) {
        $("#wrapper").css("min-height", $(window).height() - headerH + 'px');
    }*/
}

function setBodySmall() {
    if ($(this).width() < 769) {
        $('body').addClass('page-small');
        $('body').addClass('sidebar-collapsed');
    } 
    else {
        $('body').removeClass('page-small');
        $('body').removeClass('show-sidebar');
    }
    
    //隐藏头部
    if($('body').hasClass('page-small')){
    	$('.media-small-hide').hide();
    }
    else{
    	$('.media-small-hide').show();
    }
}

// Animate panel function
$.fn['animatePanel'] = function() {

    var element = $(this);
    var effect = $(this).data('effect');
    var delay = $(this).data('delay');
    var child = $(this).data('child');

    // Set default values for attrs
    if(!effect) { effect = 'zoomIn';}
    if(!delay) { delay = 0.06; } else { delay = delay / 10; };
    if(!child) { child = '.row > div';} else {child = "." + child;};

    //Set defaul values for start animation and delay
    var startAnimation = 0;
    var start = Math.abs(delay) + startAnimation;

    // Get all visible element and set opacity to 0
    var panel = element.find(child);
    panel.addClass('opacity-0');

    // Get all elements and add effect class
    panel = element.find(child);
    panel.addClass('stagger').addClass('animated-panel').addClass(effect);

    var panelsCount = panel.length + 10;
    var animateTime = (panelsCount * delay * 10000) / 10;

    // Add delay for each child elements
    panel.each(function (i, elm) {
        start += delay;
        var rounded = Math.round(start * 10) / 10;
        $(elm).css('animation-delay', rounded + 's');
        // Remove opacity 0 after finish
        $(elm).removeClass('opacity-0');
    });

    // Clear animation after finish
    setTimeout(function(){
        $('.stagger').css('animation', '');
        $('.stagger').removeClass(effect).removeClass('animated-panel').removeClass('stagger');
    }, animateTime);
};