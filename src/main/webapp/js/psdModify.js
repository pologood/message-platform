/*  @description: 消息推送平台 密码修改页js
 *   @author: jiangfeng
 *   @update: jiangfeng (2015-09-11) */
//配置require文件  start
require.config({
    baseUrl: basePath + "/js/lib",
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
require(["jquery", "common"], function ($) {

    if (typeof JSON == 'undefined') {//判断支不支持JSON对象，不支持的浏览器加载json2.js
        $('head').append($("<script type='text/javascript' src='" + basePath + "/js/json2.js'>"));
    }
    ;

    /*修改密码提交*/
    $('#modifyPassword').on('click',function(){
        postData = {
            oldPassword: $('#oldPassword').val().trim(),
            newPassword: $('#newPassword').val().trim()
        }
        postData = JSON.stringify(postData)
        console.info(postData);

        $.ajax({
            url: basePath + '/user/passwordModification',  //修改密码ajax接口
            type: 'POST',
            data: postData,
            contentType: 'application/json;charset=UTF-8',
            dataType: 'json',
            success: function (re) {
                PopTipsShow(re.description);
                if (re.status == 'success') {
                    window.location.href = basePath+"/home";
                }
            },
            error: function () {
                PopTipsShow('网络错误');
            }
        });


    });



})