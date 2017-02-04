/*  @description: 消息推送平台 短信模板添加页js
*   @author: jiangfeng  
*   @update: jiangfeng (2015-11-27) */
//配置require文件  start
require.config({
    baseUrl:  basePath+"/js/lib", 
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




    //短信模版修改提交
    $('#updateTemplate').on('click',function(){
        var id = $('.templateId').val().trim();
        //var userId = $('.userId').val().trim();
        var template = $('.template').val().trim();
        var templateCode = $('.templateCode').val().trim();
        var aliyunCode = $('.aliyunCode').val().trim();
        if (template == ''){PopTipsShow("短信模版 必填项..."); return}
        if (templateCode == ''){PopTipsShow("模版编号 必填项..."); return}

        var postData = {id:id, template:template, templateCode:templateCode, aliyunCode:aliyunCode};
        postData = JSON.stringify(postData)
        console.info(postData);
        $.ajax({
            url:basePath+'/sms/saveTemplate',
            type:'POST',
            data:postData,
            contentType:'application/json;charset=UTF-8',
            dataType:'json',
            success: function(re){
                console.info(re);
                PopTipsShow(re.description);
                if(re.status == 'SUCCESS'){
                    location.href= basePath+"/sms/toTemplateList";
                }
            },
            error:function(){
                PopTipsShow('网络错误');
            }
        });
    });






})