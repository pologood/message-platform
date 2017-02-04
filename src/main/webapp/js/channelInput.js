/*  @description: 消息推送平台 新建编辑通道js
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



    $('.select-channelType,.select-state,.select-gateway,.select-strategy,.select-env').inputChecked({});


    //新增通道 保存
    $('.addChannel').on('click',function(){
        var channelName = $('.channelName').val().trim();
        if (channelName == '') {
            PopTipsShow('通道名称 必选项...'); return;
        }
        var description = $('.description').val().trim();
        if (description == ''){
            PopTipsShow('网关备注 必选项...'); return;
        }
        var type = $('.type .checked').prev('.select-channelType').val().trim();
        var enable = $('.enable .checked').prev('.select-state').val().trim();

        var postData = {channelName:channelName, description:description, type:type, enable:enable };
        postData = JSON.stringify(postData)
        console.info(postData);

        $.ajax({
            url:basePath+'/channel/save',
            type:'POST',
            data:postData,
            contentType:'application/json;charset=UTF-8',
            dataType:'json',
            success: function(re){
                console.info(re);
                PopTipsShow(re.description);
                if(re.status == 'SUCCESS'){
                    location.href= basePath+"/channel/toChannelList";
                }
            },
            error:function(){
                PopTipsShow('网络错误');
            }
        });

    });





    //编辑通道 保存
    $('.updateChannel').on('click',function(){
        var id = $('.id').val();
        var channelName = $('.channelName').val().trim();
        if (channelName == '') {
            PopTipsShow('通道名称 必选项...'); return;
        }
        var description = $('.description').val().trim();
        if (description == ''){
            PopTipsShow('网关备注 必选项...'); return;
        }

        var type = $('.type .checked').prev('.select-channelType').val().trim();
        var enable = $('.enable .checked').prev('.select-state').val().trim();

        var gatewayList = $('.gateways .checked').prev('.select-gateway');
        var gateways='';
        for(var i=0; i<gatewayList.length; i++){
            if(gateways.length > 0) gateways += ',';
            gateways += gatewayList[i].value; //如果选中，将value添加到变量s中
        }
        if (gateways.length == 0){
            PopTipsShow('至少关联一个网关 必选项...'); return;
        }

        var strategyObj = $('.strategy .checked');
        if (strategyObj.length == 0){
            PopTipsShow('网关策略 必选项...'); return;
        }
        var strategy = strategyObj.prev('.select-strategy').val().trim();
        console.info(strategy);


        var priority = $('.strategy-input').val().trim();
        priority = priority.replace('，',',');


        var postData = {id:id, channelName:channelName, description:description, type:type, enable:enable, gateways:gateways,
            strategy:strategy, priority:priority};
        postData = JSON.stringify(postData);
        console.info(postData);
        $.ajax({
            url:basePath+'/channel/save',
            type:'POST',
            data:postData,
            contentType:'application/json;charset=UTF-8',
            dataType:'json',
            success: function(re){
                console.info(re);
                PopTipsShow(re.description);
                if(re.status == 'SUCCESS'){
                    location.href= basePath+"/channel/toChannelList";
                }
            },
            error:function(){
                PopTipsShow('网络错误');
            }
        });

    });



    //如果选中优先级则需要填写优先级
    $('#RANDOM').on('click',function(){
        $('.strategy-input').css('display','none'); //block
        $('.strategy-input').val('');
    });
    $('#ACTIVITY').on('click',function(){
        $('.strategy-input').val('');
        $('.strategy-input').css('display','none'); //block
    });
    $('#PRIORITY').on('click',function(){
        var gatewayList = $('.gateways .checked').prev('.select-gateway');
        var gateways='';
        for(var i=0; i<gatewayList.length; i++){
            if(gateways.length > 0) gateways += ',';
            gateways+=gatewayList[i].id; //如果选中，将value添加到变量s中
        }
        $('.strategy-input').val(gateways);
        $('.strategy-input').css('display','block'); //block

    });




})