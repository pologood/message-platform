/*  @description: 消息推送平台 发送app透传消息页js
*   @author: jiangfeng  
*   @update: jiangfeng (2015-09-11) */
//配置require文件  start
require.config({
    baseUrl:  basePath+"/js/lib", 
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
require(["jquery","common","myDate"], function ($){

    if(typeof JSON == 'undefined'){//判断支不支持JSON对象，不支持的浏览器加载json2.js
        $('head').append($("<script type='text/javascript' src='"+basePath+"/js/json2.js'>"));
    };

    $('.select-app').inputChecked({});

    $('.select-push-mode').inputChecked({ //即时 或是 定时
        callback:function(obj){
            var lable = $('#timing').attr('class');
            if (lable == 'checked'){ $('#timer').attr('required','required').prop('disabled',false); }
            else{  $('#timer').removeAttr('required').prop('disabled',true); }
        }
    });


    var datetipsHide = function(){
        $('.date-time').removeClass('onfocus');
    }

    $("#timer").on("focus", function(){ //绑定日期插件
        WdatePicker({
            onpicked:datetipsHide,
            minDate:'%y-%M-%d %H:%m:%s',
            dateFmt:'yyyy-MM-dd HH:mm:ss',
            realDateFmt:"yyyy-MM-dd HH-mm-ss",
            realTimeFmt:"HH:mm:ss HH-mm-ss"
        });
    });


    /*提交表单 发送APP消息*/
    $('#sendApp').on("click", function(){
        var description = $('#description').val().trim();
        if (description == ''){PopTipsShow("描述 必填项..."); return;}

        var content = $('#content').val().trim();
        if (content == ''){PopTipsShow("消息内容 必填项..."); return;}

        var appNames = $('.custom-checkbox .checked').prev().val().trim();
        if (appNames == '' || appNames == undefined){PopTipsShow("选择应用 必填项..."); return;}

        $('#sendToApp').submit();
       /* var executeType =  $('.custom-radio .checked').prev().val();

        var postdata = {description:description,content:content,appName:appNames,executeType:executeType};
        console.info(postdata)
        postdata = JSON.stringify(postdata);*/


    });



    /*提交表单 发送APP消息-----end*/


})