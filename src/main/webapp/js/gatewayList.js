/*  @description: 消息推送平台 网关列表js
 *  @author: wuxiaoxia
 *	@update: wuxiaoxia 2015-10-10
 *  @update: hupengjun 2016-01-01	*/

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
require(["jquery", "common","bootbox"], function ($) {

    if (typeof JSON == 'undefined') {//判断支不支持JSON对象，不支持的浏览器加载json2.js
        $('head').append($("<script type='text/javascript' src='" + basePath + "/js/json2.js'>"));
    }
    ;


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


    /*---网关删除函数--*/
    $('.gateway_delete').on('click',function(){
        var id = $(this).data('id');
        if(id == null || id == ''){return}
        bootbox.setLocale("zh_CN");
        bootbox.confirm({
            size: 'small',
            message: "确定要删除该网关吗？",
            callback: function(result){
                if(result==false){return;}
                var postData = {id: id};
                postData = JSON.stringify(postData);
                $.ajax({
                    url: basePath + '/gateway/delete' + '?t=' + new Date().getTime(),//删除的接口
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


    /*---网关充值函数 user_recharge--*/
    $('#gateway_recharge').on('click',function(){
        /*取值*/
        var id = $('#gatewayId').val().trim(),num = $('#rechargeNum').val().trim();
        /*判断*/
        if(num==""){PopTipsShow("请填写充值数量...");return;}
        if(num > 100000 ){PopTipsShow("充值数量不能大于10W...");return;}
        var postData = {id: id,rechargeAmount: num};
        postData = JSON.stringify(postData);
        /*调用*/
        $.ajax({
            url: basePath + '/gateway/topUpRemain' + '?t=' + new Date().getTime(),//充值接口
            type: 'post',
            data: postData,
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            success: function (data) {
                console.info(data);
                $('#rechargeNum').val('');
                PopTipsShow(data.description);
                console.info(data.status);
                if (data.status == 'SUCCESS') {
                    location.reload();
                }
            }
        });
    })

    /*---网关充值传递用户ID---*/
    $('.pop_recharge').on('click',function () {
        $('#gatewayId').val($(this).data('id'));
    });



})

