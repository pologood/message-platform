/*  @description: 消息推送平台 通道列表js
 *   @author: jiangfeng
 *	@update: jiangfeng 2015-10-09*/
//配置require文件  start
require.config({
    baseUrl: basePath + "/js/lib",
    paths: {  //引入js文件路径配置
        'jquery': 'jquery-1.91.min',
        'common': 'common',
        'bootbox':'bootbox'
    },
//  enforceDefine:true,
    shim: {   //非amd标准js文件引入模块定义与依赖配置
        'common': {
            deps: ['jquery'],
            exports: 'common'
        }

    },
    urlArgs: "t=" + (new Date()).getTime()
});
require(["jquery", "common"], function ($) {

    if (typeof JSON == 'undefined') {//判断支不支持JSON对象，不支持的浏览器加载json2.js
        $('head').append($("<script type='text/javascript' src='" + basePath + "/js/json2.js'>"));
    }
    ;

    $('.select-getway').inputChecked({});

    /*每页多少条弹出层 start*/
    $('.pag_inputs').on('click', function (e) {
        e.stopPropagation();
        var $this_height = $(document).height(),
            $this = $(this).height();
        $magin_top = $(this).siblings('.fysd_sp2').height(),
            $this_top = $(this).offset().top;
        if ($this_height - $this_top - $this > $magin_top) {
            $('.fysd_sp2').css({'top': 36 + 'px'});
        } else {
            $('.fysd_sp2').css({'top': -$magin_top - 2 + 'px'});
        }
        $('.fysd_sp2').css({'top': -$magin_top - 2 + 'px'});
        if ($('.fysd_sp2').is(':hidden')) {
            $('.fysd_sp2').fadeIn(300);
        } else {
            $('.fysd_sp2').fadeOut(300);
        }
    })
    $('.fysd_sp2 a').on('click', function () {
        $('.pag_inputs').val($(this).html());
        $(this).addClass('pagon').siblings('a').removeClass('pagon');
        $('.fysd_sp2').fadeOut(300);
    });
    /*每页多少条弹出层 end*/

    var SetMaskHeight = function () {
        var wh = $(window).height(),
            mg = $('.pop_detail_div .pop_detail_tit').outerHeight(true) + 40 * 2;
        $('.popBg').css({'height': document.body.scrollHeight + 'px'});
        $('.detail_contBg').css('max-height', wh - mg);
    }
    SetMaskHeight();
    $(window).on('resize', SetMaskHeight);

    /*---用户删除函数--*/
    $('.channel_delete').on('click',function(){
        var id = $(this).data('id');
        if(id == null || id == ''){return}
        bootbox.setLocale("zh_CN");
        bootbox.confirm({
            size: 'small',
            message: "确定要删除该通道吗？",
            callback: function(result){
                if(result==false){return;}
                var postData = {id: id};
                postData = JSON.stringify(postData);
                $.ajax({
                    url: basePath + '/channel/delete' + '?t=' + new Date().getTime(),//删除的接口
                    type: 'post',
                    data: postData,
                    contentType: "application/json; charset=utf-8",
                    dataType: 'json',
                    success: function (data) {
                        PopTipsShow(data.description);
                        if (data.status == '0') {location.reload();}
                    }
                })
            }
        })

    })

})
