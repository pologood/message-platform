/*  @description: 消息推送平台 用户管理列表页js
 *  @author: wuxiaoxia
 *	@update: wuxiaoxia 2015-10-10
 *  @update: hupengjun 2026-01-01
 *	*/


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
require(["jquery", "common",'bootbox'], function ($) {

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




    /*---用户搜索函数---*/
    $('#searchButton').on('click', function () {
        $('.bs-example-form').submit();
    })



    /*---用户删除函数--*/
    $('.user_delete').on('click',function(){
        var id = $(this).data('id');
        if(id == null || id == ''){return}
        if (id == 3) {
            PopTipsShow("此用户为管理员用户不能删除！！！");
            return;
        }
        bootbox.setLocale("zh_CN");
        bootbox.confirm({
            size: 'small',
            message: "确定要删除该用户吗？",
            callback: function(result){
                if(result==false){return;}
                var postData = {id: id};
                postData = JSON.stringify(postData);
                $.ajax({
                    url: basePath + '/user/delete' + '?t=' + new Date().getTime(),//删除的接口
                    type: 'post',
                    data: postData,
                    contentType: "application/json; charset=utf-8",
                    dataType: 'json',
                    success: function (data) {
                        PopTipsShow(data.description);
                        if (data.status == 'SUCCESS') {location.reload();}
                    }
                })
            }
        })

    })




    /*---用户充值函数 user_recharge--*/
    $('#user_recharge').on('click',function(){
        var id = $('#userId').val().trim(),num = $('#rechargeNum').val().trim();

        if(num==undefined || num=="" || num==null){PopTipsShow("请填写充值数量...【0~10W】");return;}
        if(num <= 0) {PopTipsShow("充值0？没意义啊...");return;}
        if(num > 100000) {PopTipsShow("充值金额太大...【请小于10W】");return;}

        var postData = {id: id,rechargeAmount: num};
        postData = JSON.stringify(postData);
        /*调用*/
        $.ajax({
            url: basePath + '/user/topUpBalance' + '?t=' + new Date().getTime(),//充值接口
            type: 'post',
            data: postData,
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            success: function (data) {
                $('#rechargeNum').val('');
                PopTipsShow(data.description);
                if (data.status == 'SUCCESS') {
                    location.reload();
                }
            }
        });
    })




    /*---用户充值传递用户ID---*/
    $('.pop_recharge').on('click',function () {
        $('#userId').val($(this).data('id'));
    });



    /*resetPwd 重置密碼*/
    $('.resetPwd').on('click',function () {
        var id = $(this).data('id');
        if(id == null || id == ''){return}
        bootbox.setLocale("zh_CN");
        bootbox.confirm({
            size: 'small',
            message: "确定要重置该用户的密码吗？",
            callback: function(result){
                if(result==false){return;}
                var postData = {id: id};
                postData = JSON.stringify(postData);
                $.ajax({
                    url: basePath + '/user/resetPwd' + '?t=' + new Date().getTime(),//删除的接口
                    type: 'post',
                    data: postData,
                    contentType: "application/json; charset=utf-8",
                    dataType: 'json',
                    success: function (data) {
                        PopTipsShow(data.description);
                    }
                })
            }
        })
    });


    /**用户自行更新回调地址**/
    $('.update_callback').on('click',function () {
        $('.callbackUrl').val($(this).data('id'));
    });


    $('.updateCallback').on('click',function () {
        var url = $('.callbackUrl').val().trim();
        if (url == ''){
            PopTipsShow("回调地址不能为空...");
            return;
        }
        if(!IsURL(url)){
            PopTipsShow("回调地址 格式不正确...");
            return;
        }
        bootbox.setLocale("zh_CN");
        bootbox.confirm({
            size: 'small',
            message: "确定要更新该用户的回调地址吗？",
            callback: function(result){
                if(result==false){return;}
                var postData = {messageCallbackUrl: url};
                postData = JSON.stringify(postData);
                $.ajax({
                    url: basePath + '/user/updateCallbackUrl' + '?t=' + new Date().getTime(),//删除的接口
                    type: 'post',
                    data: postData,
                    contentType: "application/json; charset=utf-8",
                    dataType: 'json',
                    success: function (data) {
                        PopTipsShow(data.description);
                        location.reload();
                    }
                })
            }
        })
    });


    /**迄今见过最简单的url正则表达式**/
    function IsURL(str_url){
        var strRegex = "http://.*?";
        var re=new RegExp(strRegex);
        if (re.test(str_url)){
            return (true);
        }else{
            return (false);
        }
    }

})



















