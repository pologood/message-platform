/*  @description: 消息推送平台 消息列表页js
*   @author: wuxiaoxia
*    @update: wuxiaoxia 2015-09-11*/
//配置require文件  start
require.config({
    baseUrl:  basePath+"/js/lib", 
    paths: {  //引入js文件路径配置
        'jquery': 'jquery-1.8.3.min',
        'common': 'common',
        'myDate': 'My97DatePicker/WdatePicker',
        'bootbox': 'bootbox'
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
require(["jquery","common","myDate",'bootbox'], function ($){

    if(typeof JSON == 'undefined'){//判断支不支持JSON对象，不支持的浏览器加载json2.js
        $('head').append($("<script type='text/javascript' src='"+basePath+"/js/json2.js'>"));
    };

    /*每页多少条弹出层 start*/
    $('.pag_inputs').on('click',function(e){
        e.stopPropagation();
        var $this_height=$(document).height(),
            $this=$(this).height();
            $magin_top=$(this).siblings('.fysd_sp2').height(),
            $this_top=$(this).offset().top;
        if($this_height-$this_top-$this>$magin_top){
            $('.fysd_sp2').css({'top':36+'px'});
        }else{
            $('.fysd_sp2').css({'top':-$magin_top-2+'px'});
        }
        $('.fysd_sp2').css({'top':-$magin_top-2+'px'});
        if($('.fysd_sp2').is(':hidden')){
            $('.fysd_sp2').fadeIn(300);
        }else{
            $('.fysd_sp2').fadeOut(300);
        }
    })
    $('.fysd_sp2 a').on('click',function(){
        $('.pag_inputs').val($(this).html());
        $(this).addClass('pagon').siblings('a').removeClass('pagon');
        $('.fysd_sp2').fadeOut(300);
    });
    
    /*每页多少条弹出层 end*/
    var SetMaskHeight = function(){
        var wh = $(window).height(),
            mg = $('.pop_detail_div .pop_detail_tit').outerHeight(true)+40*2;
            $('.popBg').css({'height':document.body.scrollHeight+'px'});
            $('.detail_contBg').css('max-height',wh-mg);
    }
    SetMaskHeight();
    $(window).on('resize',SetMaskHeight);


    /*删除appInfo*/
    $('.deleteAppInfo').on('click',function(){
        var id = $(this).data('id');
        if(id == null || id == ''){return}
        bootbox.setLocale("zh_CN");
        bootbox.confirm({
            size: 'small',
            message: "确定要删除该AppInfo吗？",
            callback: function(result){
                if(result==false){return;}
                $.ajax({
                    url: basePath + '/app/deleteAppInfo/'+ id + '?t=' + new Date().getTime(),//删除的接口
                    success: function (data) {
                        PopTipsShow(data.description);
                        if (data.status == 'success') {location.reload();}
                    }
                })
            }
        })
    })
    /*删除AppInfo end*/


    /*保存AppInfo 新增或编辑AppInfo*/
    $('#saveAppInfo').on('click',function(){
        var id = $('#id').val();

        var appId = $('#appId').val().trim();
        if (appId == '') {PopTipsShow("AppId 为必填项..."); return;}

        var appKey = $('#appKey').val().trim();
        if (appKey == '') {PopTipsShow("appKey 为必填项..."); return;}

        var masterSecret = $('#masterSecret').val().trim();
        if (masterSecret == '') {PopTipsShow("masterSecret 为必填项..."); return;}

        var appCode = $('#appCode').val().trim();
        if (appCode == '') {PopTipsShow("appCode 为必填项..."); return;}

        var appName = $('#appName').val().trim();
        if (appName == '') {PopTipsShow("appName 为必填项..."); return;}

        $.getJSON(basePath+"/app/verifyAppId/"+appId, function(data){
            if(data == true){PopTipsShow("AppId已存在...");$('#appId').val('');}
        });

        $.getJSON(basePath+"/app/verifyAppCode/"+appCode, function(data){
            if(data == true){PopTipsShow("AppCode已存在...");$('#appCode').val('');}
        });

        var postData = {id:id,appId:appId,appKey:appKey,masterSecret:masterSecret,appCode:appCode,appName:appName};
        postData = JSON.stringify(postData);
        $.ajax({
            url: basePath + '/app/saveAppInfo', //保存AppInfo
            type: 'post',
            data: postData,
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            success: function (data) {
                if (data.status == 'success') {
                    location.href =basePath+"/app/toAppInfoList";
                    PopTipsShow(data.description);
                }

            }
        })
    });

    /*保存AppInfo 新增或编辑AppInfo ---end */


})
























