/*  @description: 消息推送平台 添加编辑用户js
*   @author: jiangfeng  
*   @update: jiangfeng (2015-10-10) */
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

    $('.select-state,.select-channel,.select-role,.select-env').inputChecked({});


    //新增用户 保存
    $('.addUser').on('click',function(){
        var userName = $('.userName').val().trim();
        if (userName == '') {
            PopTipsShow('用户名 必选项...'); return;
        }
        var contact = $('.contact').val().trim();
        if (contact == ''){
            PopTipsShow('联系人 必选项...'); return;
        }

        var mobile = $('.mobile').val().trim();
        if (mobile == ''){
            PopTipsShow('联系电话 必选项...'); return;
        }

        var messageCallbackUrl = $('.messageCallbackUrl').val().trim();
        if (messageCallbackUrl == ''){
            PopTipsShow('回调地址 必选项...'); return;
        }

        var roles = $('.roles .checked').prev('.select-role').val().trim();
        var enable = $('.enable .checked').prev('.select-state').val().trim();



        var postData = {userName:userName, contact:contact, mobile:mobile, messageCallbackUrl:messageCallbackUrl,
            roles:roles, enable:enable};
        postData = JSON.stringify(postData)

        $.ajax({
            url:basePath+'/user/save',
            type:'POST',
            data:postData,
            contentType:'application/json;charset=UTF-8',
            dataType:'json',
            success: function(re){
                PopTipsShow(re.description);
                if(re.status == 'SUCCESS'){
                    location.href= basePath+"/user/toUserList";
                }
            },
            error:function(){
                PopTipsShow('网络错误');
            }
        });

    });






    //编辑用户 保存
    $('.updateUser').on('click',function(){
        var id = $('.id').val();
        var userName = $('.userName').val().trim();
        if (userName == '') {
            PopTipsShow('用户名 必选项...'); return;
        }
        var contact = $('.contact').val().trim();
        if (contact == ''){
            PopTipsShow('联系人 必选项...'); return;
        }
        var mobile = $('.mobile').val().trim();
        if (mobile == ''){
            PopTipsShow('联系电话 必选项...'); return;
        }
        var messageCallbackUrl = $('.messageCallbackUrl').val().trim();
        if (messageCallbackUrl == ''){
            PopTipsShow('回调地址 必选项...'); return;
        }
        var roles = $('.roles .checked').prev('.select-role').val().trim();
        var enable = $('.enable .checked').prev('.select-state').val().trim();
        var channelList =  $('.channels .checked').prev('.select-channel');
        var channels='';
        for(var i=0; i<channelList.length; i++){
            if (channels.length > 0 ) channels += ',';
            channels+=channelList[i].value; //如果选中，将value添加到变量channels中
        }
        if (channels.length == 0) {
            PopTipsShow('至少关联一个通道 必选项...'); return;
        }

        var postData = {id:id,userName:userName, contact:contact, mobile:mobile, messageCallbackUrl:messageCallbackUrl,
            roles:roles, enable:enable, channels:channels};
        postData = JSON.stringify(postData);
        console.info(postData);
        $.ajax({
            url:basePath+'/user/save',
            type:'POST',
            data:postData,
            contentType:'application/json;charset=UTF-8',
            dataType:'json',
            success: function(re){
                PopTipsShow(re.description);
                if(re.status == 'SUCCESS'){
                    location.href= basePath+"/user/toUserList";
                }
            },
            error:function(){
                PopTipsShow('网络错误');
            }
        });

    });



    /*检验添加用户联系电话格式*/
    $('.mobile').on('blur',function(){
        var mobileReg = /^1[3578][0-9]{9}(,1[3578][0-9]{9})*$/;
        var mobiles = $('.mobile').val();
        if(!mobileReg.test(mobiles)){
            $('.mobile').val('');
        }
    });



})