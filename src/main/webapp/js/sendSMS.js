/*  @description: 消息推送平台 发送短信js
*   @author: jiangfeng  
*   @update: jiangfeng (2015-09-25) */
//配置require文件  start
require.config({
    baseUrl: basePath+"/js/lib", 
    paths: {  //引入js文件路径配置
        'jquery': 'jquery-1.8.3.min',
        'common': 'common'
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
require(["jquery","common"], function ($){

    if(typeof JSON == 'undefined'){//判断支不支持JSON对象，不支持的浏览器加载json2.js
        $('head').append($("<script type='text/javascript' src='"+basePath+"/js/json2.js'>"));
    };

    $('.select-template,.select-sign').inputChecked({});



    /*短信发送 表单提交*/
    var mobileReg = /^1[3578][0-9]{9}(,1[3578][0-9]{9})*$/;
    $('#sendSMS').on('click',function(){
        /*非空判断-----*/
        var mobile = $('.mobile').val().replace("，",",").trim();
        if (mobile.length == 0){PopTipsShow("手机号 为必填项..."); return;}
        if(!mobileReg.test(mobile)){
            PopTipsShow("手机号 格式不正确...");
            return;
        }
        var label = $('.template label').attr('class');
        var templateCode = '';
        if (label == 'checked'){
            templateCode = $('.code-value').val().trim();
            if (templateCode.length == 0) {PopTipsShow("模版编码 为必填项..."); return;}
            var templateValue  = $('.template-value').val().trim();
            if (templateValue.length == 0){
                PopTipsShow("请核实模板编码是否有效...点击Go!");
                return;
            }
        }
        var content = $('.content').val().trim();
        if (content.length == 0){PopTipsShow("短信内容 为必填项..."); return;}

        var sign = null;
        var signLabel = $('.useCustomSign label').attr('class');
        if (signLabel == 'checked'){
            sign = $('.sign').val().trim();
            if(sign.length == 0 ){ PopTipsShow("自定义标签 为必填项..."); return;}
        }
        var postData;
        if (templateCode.length > 0){
            postData = {mobile:mobile, templateCode:templateCode,templateParam:content,sign:sign}
        }else {
            postData = {mobile:mobile, content:content, sign:sign}
        }
        postData = JSON.stringify(postData)
        console.info(postData);

        $.ajax({
            url:basePath+'/smsMessage/send',
            type:'POST',
            data:postData,
            contentType:'application/json;charset=UTF-8',
            dataType:'json',
            success: function(data){
                PopTipsShow(data.description,300,3000);
                if(data.status == 'SUCCESS'){
                    location.href= basePath+"/smsMessage/send";
                }
            },
            error:function(){
                PopTipsShow('网络错误');
            }
        });
    });



    //----判断是否使用模版发送短信
    $('#template-label').on('click',function(){
        var label = $('#template-label label').attr('class');
        if (label == undefined || label == ''){ $('.templateDiv').css('display','block');}
        else{ $('.templateDiv').css('display','none'); }
    });
    //-------end ----------


    //----触发核实模板编码 查询模版 填充到内容
    /*  $('.template-code').on('blur',function(){
     var code = $('.template-code').val();
     if (code == '') {
     PopTipsShow("模版ID 必填项..."); return;
     }
     });*/

    //-----获取模版信息------
    $('.template-code').on('click',function(){
        var code = $('.code-value').val().trim();
        if (code == '') {
            PopTipsShow("模版code 必填项..."); return;
        }
        var postData = {templateCode: code};
        postData = JSON.stringify(postData);
        $.ajax({
            url: basePath + '/sms/getTemplateDetail',
            type: 'post',
            data: postData,
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            success: function (obj) {
                if (obj.id == null) {
                    PopTipsShow("模版code 错误...");
                    $('.code-value').val('');
                }else{
                    $('.template-value').val(obj.template);
                    $('.content').attr('placeholder','{name:"xkeshi",code:"0329"}')
                }

            }
        })
    });
    //-----------end---------------


    //----判断是否使用自定义签名发送短信
    $('#useCustomSign-label').on('click',function(){
        var label = $('#useCustomSign-label label').attr('class');
        if (label == undefined || label == ''){ $('.signDiv').css('display','block');}
        else{ $('.signDiv').css('display','none'); }
    });
    //-------end ----------


})

