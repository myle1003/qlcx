layer.config({
    extend: 'moon/style.css',
    skin: 'layer-ext-moon'
});

var isMobile = $.common.isMobile() || $(window).width() < 769;
var sidebarHeight = isMobile ? '100%' : '96%';

$(function() {
    $('.sidebar-collapse').slimScroll({
        height: sidebarHeight,
        railOpacity: 0.9,
        alwaysVisible: false
    });

    $('.navbar-minimalize').click(function() {
    	if (isMobile) {
    		$("body").toggleClass("canvas-menu");
    	} else {
    		$("body").toggleClass("mini-navbar");
    	}
        SmoothlyMenu();
    });
    
    $('#side-menu>li').click(function() {
    	if ($('body').hasClass('canvas-menu mini-navbar')) {
            NavToggle();
        }
    	
    });
    $('#side-menu>li li a:not(:has(span))').click(function() {
        if ($(window).width() < 769) {
            NavToggle();
        }
    });

    $('.nav-close').click(NavToggle);
});

$(window).bind("load resize",
function() {
    if ($(this).width() < 769) {
        $('body').addClass('canvas-menu');
        $("nav .logo").addClass("hide");
        $(".slimScrollDiv").css({ "overflow":"hidden" })
    }
});

function NavToggle() {
    $('.navbar-minimalize').trigger('click');
}

function fixedSidebar() {
	$('#side-menu').hide();
	$("nav .logo").addClass("hide");
    setTimeout(function() {
        $('#side-menu').fadeIn(500);
    },
    100);
}

function SmoothlyMenu() {
    if (isMobile && !$('body').hasClass('canvas-menu')) {
    	$('.navbar-static-side').fadeIn();
    	fixedSidebar();
    } else if (!isMobile &&!$('body').hasClass('mini-navbar')) {
    	fixedSidebar();
    	$("nav .logo").removeClass("hide");
    } else if (isMobile && $('body').hasClass('fixed-sidebar')) {
    	$('.navbar-static-side').fadeOut();
    	fixedSidebar();
    } else if (!isMobile && $('body').hasClass('fixed-sidebar')) {
    	fixedSidebar();
    } else {
        $('#side-menu').removeAttr('style');
    }
}

$(function() {
    function calSumWidth(elements) {
        var width = 0;
        $(elements).each(function() {
            width += $(this).outerWidth(true);
        });
        return width;
    }
    
    function setActiveTab(element) {
        if (!$(element).hasClass('active')) {
            var currentId = $(element).data('id');
            $('.qlcx_iframe').each(function() {
                if ($(this).data('id') == currentId) {
                    $(this).show().siblings('.qlcx_iframe').hide();
                }
            });
            $(element).addClass('active').siblings('.menuTab').removeClass('active');
            scrollToTab(element);
        }
    }

    function scrollToTab(element) {
        var marginLeftVal = calSumWidth($(element).prevAll()),
        marginRightVal = calSumWidth($(element).nextAll());
        var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".menuTabs"));
        var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
        var scrollVal = 0;
        if ($(".page-tabs-content").outerWidth() < visibleWidth) {
            scrollVal = 0;
        } else if (marginRightVal <= (visibleWidth - $(element).outerWidth(true) - $(element).next().outerWidth(true))) {
            if ((visibleWidth - $(element).next().outerWidth(true)) > marginRightVal) {
                scrollVal = marginLeftVal;
                var tabElement = element;
                while ((scrollVal - $(tabElement).outerWidth()) > ($(".page-tabs-content").outerWidth() - visibleWidth)) {
                    scrollVal -= $(tabElement).prev().outerWidth();
                    tabElement = $(tabElement).prev();
                }
            }
        } else if (marginLeftVal > (visibleWidth - $(element).outerWidth(true) - $(element).prev().outerWidth(true))) {
            scrollVal = marginLeftVal - $(element).prev().outerWidth(true);
        }
        $('.page-tabs-content').animate({
            marginLeft: 0 - scrollVal + 'px'
        },
        "fast");
    }

    function scrollTabLeft() {
        var marginLeftVal = Math.abs(parseInt($('.page-tabs-content').css('margin-left')));
        var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".menuTabs"));
        var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
        var scrollVal = 0;
        if (($(".page-tabs-content").width()) < visibleWidth) {
            return false;
        } else {
            var tabElement = $(".menuTab:first");
            var offsetVal = 0;
            while ((offsetVal + $(tabElement).outerWidth(true)) <= marginLeftVal) { 
                offsetVal += $(tabElement).outerWidth(true);
                tabElement = $(tabElement).next();
            }
            offsetVal = 0;
            if (calSumWidth($(tabElement).prevAll()) > visibleWidth) {
                while ((offsetVal + $(tabElement).outerWidth(true)) < (visibleWidth) && tabElement.length > 0) {
                    offsetVal += $(tabElement).outerWidth(true);
                    tabElement = $(tabElement).prev();
                }
                scrollVal = calSumWidth($(tabElement).prevAll());
            }
        }
        $('.page-tabs-content').animate({
            marginLeft: 0 - scrollVal + 'px'
        },
        "fast");
    }

    function scrollTabRight() {
        var marginLeftVal = Math.abs(parseInt($('.page-tabs-content').css('margin-left')));
        var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".menuTabs"));
        var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
        var scrollVal = 0;
        if ($(".page-tabs-content").width() < visibleWidth) {
            return false;
        } else {
            var tabElement = $(".menuTab:first");
            var offsetVal = 0;
            while ((offsetVal + $(tabElement).outerWidth(true)) <= marginLeftVal) { 
                offsetVal += $(tabElement).outerWidth(true);
                tabElement = $(tabElement).next();
            }
            offsetVal = 0;
            while ((offsetVal + $(tabElement).outerWidth(true)) < (visibleWidth) && tabElement.length > 0) {
                offsetVal += $(tabElement).outerWidth(true);
                tabElement = $(tabElement).next();
            }
            scrollVal = calSumWidth($(tabElement).prevAll());
            if (scrollVal > 0) {
                $('.page-tabs-content').animate({
                    marginLeft: 0 - scrollVal + 'px'
                },
                "fast");
            }
        }
    }

    $(".menuItem").each(function(index) {
        if (!$(this).attr('data-index')) {
            $(this).attr('data-index', index);
        }
    });

    function menuItem() {
        var dataUrl = $(this).attr('href'),
        dataIndex = $(this).data('index'),
        menuName = $.trim($(this).text()),
        flag = true;
        $(".nav ul li, .nav li").removeClass("selected");
        $(this).parent("li").addClass("selected");
        setIframeUrl($(this).attr("href"));
        if (dataUrl == undefined || $.trim(dataUrl).length == 0) return false;

        $('.menuTab').each(function() {
            if ($(this).data('id') == dataUrl) {
                if (!$(this).hasClass('active')) {
                    $(this).addClass('active').siblings('.menuTab').removeClass('active');
                    scrollToTab(this);
                    $('.mainContent .qlcx_iframe').each(function() {
                        if ($(this).data('id') == dataUrl) {
                            $(this).show().siblings('.qlcx_iframe').hide();
                            return false;
                        }
                    });
                }
                flag = false;
                return false;
            }
        });
        if (flag) {
            var str = '<a href="javascript:;" class="active menuTab" data-id="' + dataUrl + '">' + menuName + ' <i class="fa fa-times-circle"></i></a>';
            $('.menuTab').removeClass('active');

            var str1 = '<iframe class="qlcx_iframe" name="iframe' + dataIndex + '" width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';
            $('.mainContent').find('iframe.qlcx_iframe').hide().parents('.mainContent').append(str1);
            
            $.modal.loading("Đang tải dữ liệu, vui lòng đợi...");
            
            $('.mainContent iframe:visible').load(function () {
            	$.modal.closeLoading();
            });
            
            $('.menuTabs .page-tabs-content').append(str);
            scrollToTab($('.menuTab.active'));
        }
        return false;
    }
    
    function menuBlank() {
    	var dataUrl = $(this).attr('href');
    	window.open(dataUrl);
    	return false;
    }

    $('.menuItem').on('click', menuItem);
    
    $('.menuBlank').on('click', menuBlank);

    function closeTab() {
        var closeTabId = $(this).parents('.menuTab').data('id');
        var currentWidth = $(this).parents('.menuTab').width();
        var panelUrl = $(this).parents('.menuTab').data('panel');
        if ($(this).parents('.menuTab').hasClass('active')) {

            if ($(this).parents('.menuTab').next('.menuTab').size()) {

                var activeId = $(this).parents('.menuTab').next('.menuTab:eq(0)').data('id');
                $(this).parents('.menuTab').next('.menuTab:eq(0)').addClass('active');

                $('.mainContent .qlcx_iframe').each(function() {
                    if ($(this).data('id') == activeId) {
                        $(this).show().siblings('.qlcx_iframe').hide();
                        return false;
                    }
                });

                var marginLeftVal = parseInt($('.page-tabs-content').css('margin-left'));
                if (marginLeftVal < 0) {
                    $('.page-tabs-content').animate({
                        marginLeft: (marginLeftVal + currentWidth) + 'px'
                    },
                    "fast");
                }

                $(this).parents('.menuTab').remove();

                $('.mainContent .qlcx_iframe').each(function() {
                    if ($(this).data('id') == closeTabId) {
                        $(this).remove();
                        return false;
                    }
                });
            }

            if ($(this).parents('.menuTab').prev('.menuTab').size()) {
                var activeId = $(this).parents('.menuTab').prev('.menuTab:last').data('id');
                $(this).parents('.menuTab').prev('.menuTab:last').addClass('active');
                $('.mainContent .qlcx_iframe').each(function() {
                    if ($(this).data('id') == activeId) {
                        $(this).show().siblings('.qlcx_iframe').hide();
                        return false;
                    }
                });

                $(this).parents('.menuTab').remove();

                $('.mainContent .qlcx_iframe').each(function() {
                    if ($(this).data('id') == closeTabId) {
                        $(this).remove();
                        return false;
                    }
                });
                
                if($.common.isNotEmpty(panelUrl)){
            		$('.menuTab[data-id="' + panelUrl + '"]').addClass('active').siblings('.menuTab').removeClass('active');
            		$('.mainContent .qlcx_iframe').each(function() {
                        if ($(this).data('id') == panelUrl) {
                            $(this).show().siblings('.qlcx_iframe').hide();
                            return false;
                        }
            		});
            	}
            }
        }
        else {
            $(this).parents('.menuTab').remove();

            $('.mainContent .qlcx_iframe').each(function() {
                if ($(this).data('id') == closeTabId) {
                    $(this).remove();
                    return false;
                }
            });
        }
        scrollToTab($('.menuTab.active'));
        setIframeUrl($('.page-tabs-content').find('.active').attr('data-id'));
        return false;
    }
    
    $('.menuTabs').on('click', '.menuTab i', closeTab);

    function showActiveTab() {
        scrollToTab($('.menuTab.active'));
    }
    $('.tabShowActive').on('click', showActiveTab);

    function activeTab() {
        if (!$(this).hasClass('active')) {
            var currentId = $(this).data('id');
            $('.mainContent .qlcx_iframe').each(function() {
                if ($(this).data('id') == currentId) {
                    $(this).show().siblings('.qlcx_iframe').hide();
                    return false;
                }
            });
            $(this).addClass('active').siblings('.menuTab').removeClass('active');
            scrollToTab(this);
        }
    }

    $('.menuTabs').on('click', '.menuTab', activeTab);

    function refreshTab() {
    	var currentId = $('.page-tabs-content').find('.active').attr('data-id');
    	var target = $('.qlcx_iframe[data-id="' + currentId + '"]');
        var url = target.attr('src');
        target.attr('src', url).ready();
    }
    
    function fullScreenTab() {
    	var currentId = $('.page-tabs-content').find('.active').attr('data-id');
    	var target = $('.qlcx_iframe[data-id="' + currentId + '"]');
	    target.fullScreen(true);
    }
    
    function tabCloseCurrent() {
    	$('.page-tabs-content').find('.active i').trigger("click");
    }
    
    function tabCloseOther() {
        $('.page-tabs-content').children("[data-id]").not(":first").not(".active").each(function() {
            $('.qlcx_iframe[data-id="' + $(this).data('id') + '"]').remove();
            $(this).remove();
        });
        $('.page-tabs-content').css("margin-left", "0");
        setIframeUrl($('.page-tabs-content').find('.active').attr('data-id'));
    }
    
    function tabCloseAll() {
    	$('.page-tabs-content').children("[data-id]").not(":first").each(function() {
            $('.qlcx_iframe[data-id="' + $(this).data('id') + '"]').remove();
            $(this).remove();
        });
        $('.page-tabs-content').children("[data-id]:first").each(function() {
            $('.qlcx_iframe[data-id="' + $(this).data('id') + '"]').show();
            $(this).addClass("active");
        });
        $('.page-tabs-content').css("margin-left", "0");
        setIframeUrl($('.page-tabs-content').find('.active').attr('data-id'));
    }
    
    
    $('#fullScreen').on('click', function () {
    	$(document).toggleFullScreen();
    });
    
    $('.tabReload').on('click', refreshTab);
    
    $('.tabFullScreen').on('click', fullScreenTab);

    $('.menuTabs').on('dblclick', '.menuTab', activeTabMax);

    // 左移按扭
    $('.tabLeft').on('click', scrollTabLeft);

    // // 右移按扭
    // $('.tabRight').on('click', scrollTabRight);
    
    // Nhật ký
    $('.tabCloseCurrent').on('click', tabCloseCurrent);
    
    // 关闭其他
    $('.tabCloseOther').on('click', tabCloseOther);

    // 关闭全部
    $('.tabCloseAll').on('click', tabCloseAll);
    
    // tab全屏显示
    $('.tabMaxCurrent').on('click', function () {
        $('.page-tabs-content').find('.active').trigger("dblclick");
    });
    
    // 关闭全屏
    $('#ax_close_max').click(function(){
    	$('#content-main').toggleClass('max');
    	$('#ax_close_max').hide();
    })
    
    // 双击选项卡全屏显示
    function activeTabMax() {
        $('#content-main').toggleClass('max');
        $('#ax_close_max').show();
    }
    
    // 设置锚点
    function setIframeUrl(href) {
    	if($.common.equals("history", mode)) {
    		storage.set('publicPath', href);
    	} else {
    		var nowUrl = window.location.href;
            var newUrl = nowUrl.substring(0, nowUrl.indexOf("#"));
            window.location.href = newUrl + "#" + href;
    	}
    }
    
    $(window).keydown(function(event) {
        if (event.keyCode == 27) {
            $('#content-main').removeClass('max');
            $('#ax_close_max').hide();
        }
    });
    
    window.onhashchange = function() {
        var hash = location.hash;
        var url = hash.substring(1, hash.length);
        $('a[href$="' + url + '"]').click();
    };
    
    // 右键菜单实现
    $.contextMenu({
        selector: ".menuTab",
        trigger: 'right',
        autoHide: true,
        items: {
            "close_current": {
                name: "Nhật ký",
                icon: "fa-close",
                callback: function(key, opt) {
                	opt.$trigger.find('i').trigger("click");
                }
            },
            "close_other": {
                name: "Đóng khác",
                icon: "fa-window-close-o",
                callback: function(key, opt) {
                	setActiveTab(this);
                    tabCloseOther();
                }
            },
            "close_left": {
                name: "Đóng bên trái",
                icon: "fa-reply",
                callback: function(key, opt) {
                	setActiveTab(this);
                	this.prevAll('.menuTab').not(":last").each(function() {
                	    if ($(this).hasClass('active')) {
                	        setActiveTab(this);
                	    }
                	    $('.qlcx_iframe[data-id="' + $(this).data('id') + '"]').remove();
                	    $(this).remove();
                	});
                	$('.page-tabs-content').css("margin-left", "0");
                }
            },
            "close_right": {
                name: "Đóng bên phải",
                icon: "fa-share",
                callback: function(key, opt) {
                	setActiveTab(this);
                	this.nextAll('.menuTab').each(function() {
                        $('.menuTab[data-id="' + $(this).data('id') + '"]').remove();
                        $(this).remove();
                    });
                }
            },
            "close_all": {
                name: "Đóng tất cả",
                icon: "fa-window-close",
                callback: function(key, opt) {
                    tabCloseAll();
                }
            },
            "step": "---------",
            "full": {
                name: "hiển thị toàn màn hình",
                icon: "fa-arrows-alt",
                callback: function(key, opt) {
                	setActiveTab(this);
                    var target = $('.qlcx_iframe[data-id="' + this.data('id') + '"]');
                	target.fullScreen(true);
                }
            },
            "refresh": {
                name: "làm mới trang",
                icon: "fa-refresh",
                callback: function(key, opt) {
                	setActiveTab(this);
                	var target = $('.qlcx_iframe[data-id="' + this.data('id') + '"]');
                	var url = target.attr('src');
                    $.modal.loading("Đang tải dữ liệu, vui lòng đợi...");
                    target.attr('src', url).load(function () {
                    	$.modal.closeLoading();
                    });
                }
            },
            "open": {
                name: "mở trong một cửa sổ mới",
                icon: "fa-link",
                callback: function(key, opt) {
                	var target = $('.qlcx_iframe[data-id="' + this.data('id') + '"]');
                    window.open(target.attr('src'));
                }
            },
        }
    })
});
