/*  @description: 消息推送平台  发送客户端透传消息页js
*   @author: jiangfeng  
*   @update: jiangfeng (2015-09-11) */
//配置require文件  start
require.config({
    baseUrl: basePath+"/js/lib", 
    paths: {  //引入js文件路径配置
        'jquery': 'jquery-1.91.min',
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

    $('.select-state,.select-channel,.select-role,.select-getway').inputChecked({});

    /*表单提交*/
    $('#sendClient').on('click',function(){
        /*非空判断-----*/
        var appClientId = $('#appClientId').val().trim();
        if (appClientId == ''){PopTipsShow("客户端号 为必填项..."); return;}

        var appId = $('#appId').val().trim();
        if (appId == ''){PopTipsShow("应用ID 为必填项..."); return;}

        var content = $('#content').val().trim();
        if (content == ''){PopTipsShow("消息内容 为必填项..."); return;}

        var channelName = '';
        var channelNameObj = $('.channelName .checked');
        if (channelNameObj.length > 0 ){
            channelName = $('.channelName .checked').prev('.select-channel').val().trim();
        }
        if (channelName == ''){PopTipsShow("客户端通道 为必填项..."); return;}

        var postData = {appClientId: appClientId,appId: appId,content: content,channelName: channelName}
        postData = JSON.stringify(postData)
        console.info(postData);

        $.ajax({
            url:basePath+'/app/clientSend',
            type:'POST',
            data:postData,
            contentType:'application/json;charset=UTF-8',
            dataType:'json',
            success: function(re){
                console.info(re);
                PopTipsShow(re.description);
                if(re.status == 'SUCCESS'){
                    location.href= basePath+"/app/toAppList";
                }

            },
            error:function(){
                PopTipsShow('网络错误');
            }
        });
    });

})