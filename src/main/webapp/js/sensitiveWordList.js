/*  @description: 消息推送平台 发送短信列表js
 *   @author: wuxiaoxia
 *	@update: wuxiaoxia 2015-09-25*/
//配置require文件  start
require.config({
    baseUrl: basePath + "/js/lib",
    paths: {  //引入js文件路径配置
        'jquery': 'jquery-1.8.3.min',
        'common': 'common',
        'myDate': 'My97DatePicker/WdatePicker'
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
require(["jquery", "common", "myDate"], function ($) {

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


    /*  删除敏感字  start*/
    $('.sensitiveWord_delete').on('click',function () {
        var id = $(this).data('id');
        if(id == null || id == ''){return}
        bootbox.setLocale("zh_CN");
        bootbox.confirm({
            size: 'small',
            message: "确定要删除该敏感字吗？",
            callback: function(result){
                if(result==false){return;}
                var postData = {id: id};
                postData = JSON.stringify(postData);
                $.ajax({
                    url: basePath + '/sms/deleteSensitiveWord' + '?t=' + new Date().getTime(),//删除的接口
                    type: 'post',
                    data: postData,
                    contentType: "application/json; charset=utf-8",
                    dataType: 'json',
                    success: function (data) {
                        PopTipsShow(data.description);
                        if (data.status == 'SUCCESS'){
                            location.reload();
                        }
                    }
                })
            }
        })


    });
    /*删除  end*/


    /*新增敏感字*/
    $('.addWord').on('click',function(){
        var word = $('.word_add').val().trim();
        if (word == ''){
            PopTipsShow("请输入新增的敏感字...");
            return;
        }

        var postData = {word:word};
        postData = JSON.stringify(postData);
        $.ajax({
            url: basePath + "/sms/addSensitiveWord",
            type: 'post',
            data: postData,
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            success: function (data) {
                $('.word_add').val('');
                PopTipsShow(data.description);
                if (data.status == 'SUCCESS'){
                    location.reload();
                }

            }
        })
    });

})
























