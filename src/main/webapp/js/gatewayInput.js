/*  @description: 消息推送平台 新建编辑网关js
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

    $('.select-gatewayType,.select-state,.select-gateway,.select-scs,.select-sp').inputChecked({});



    //新增网关 保存
    $('.addGateway').on('click',function(){
        var name = $('.name').val().trim();
        if (name == '') {
            PopTipsShow('网关名称 必选项...'); return;
        }
        var description = $('.description').val().trim();
        if (description == ''){
            PopTipsShow('网关备注 必选项...'); return;
        }

        var price = $('.price').val().trim();
        if (price == ''){
            PopTipsShow('网关价格 必选项...'); return;
        }
        if(price <= 0){
            PopTipsShow('网关价格需要大于0...'); return;
        }

        var type = $('.type .checked').prev('.select-gatewayType').val().trim();
        var gatewayNature = $('.gatewayNature .checked').prev('.select-gateway').val().trim();
        var enable = $('.enable .checked').prev('.select-state').val().trim();



        var postData = {name:name, description:description, type:type, price:price, gatewayNature:gatewayNature, enable:enable };
        postData = JSON.stringify(postData)
        console.info(postData);

        $.ajax({
            url:basePath+'/gateway/save',
            type:'POST',
            data:postData,
            contentType:'application/json;charset=UTF-8',
            dataType:'json',
            success: function(re){
                console.info(re);
                if(re.status == 'SUCCESS'){
                    location.href= basePath+"/gateway/toGatewayList";
                }
                PopTipsShow(re.description);
            },
            error:function(){
                PopTipsShow('网络错误');
            }
        });

    });


    //编辑网关 保存
    $('.modifyGateway').on('click',function(){
        var id = $('.id').val();
        var name = $('.name').val().trim();
        if (name == '') {
            PopTipsShow('网关名称 必选项...'); return;
        }
        var description = $('.description').val().trim();
        if (description == ''){
            PopTipsShow('网关备注 必选项...'); return;
        }
        var price = $('.price').val().trim();
        if (price == ''){
            PopTipsShow('网关价格 必选项...'); return;
        }
        if(price <= 0){
            PopTipsShow('网关价格需要大于0...'); return;
        }
        //类型
        var type = $('.gatewayType .checked').prev('.select-gatewayType').val().trim();
        //网关类型
        var gatewayNature = $('.gatewayNature .checked').prev('.select-gateway').val().trim();
        //是否启用
        var enable = $('.enable .checked').prev('.select-state').val().trim();
        //账号信息
        var gatewayInfoData = $('.gatewayInfoData').val().trim();
        if (gatewayInfoData.length == 0){
            PopTipsShow('账号数据不能为空...'); return;
        }

        //如果短信编辑则需要编辑签名信息
        if (type != 'AMS'){
            //签名类型
            var scsObject = $('.supportCustomSign .checked');
            if (scsObject.length == 0){
                PopTipsShow('签名类型不能为空...'); return;
            }
            var supportCustomSign = $('.supportCustomSign .checked').prev('.select-scs').val().trim();

            if (supportCustomSign == 0) {//固定签名类型
                //固定签名内容
                var supportSign = $('.supportSign').val().trim();
                if (supportSign.length == 0){
                    PopTipsShow('固定签名类型，固定签名值不能为空...'); return;
                }
                //签名位置
                var spObject = $('.signPosition .checked');
                if (spObject.length == 0){
                    PopTipsShow('固定签名类型，签名位置不能为空...'); return;
                }
                var signPosition = $('.signPosition .checked').prev('.select-sp').val().trim();
            }

            if (supportCustomSign == 1) {//自定义签名类型
                //签名位置
                var spObject = $('.signPosition .checked');
                if (spObject.length == 0){
                    PopTipsShow('自定义签名类型，签名位置不能为空...'); return;
                }
                var signPosition = $('.signPosition .checked').prev('.select-sp').val().trim();
            }
        }

        var postData = {id:id, name:name, description:description, type:type, price:price, gatewayNature:gatewayNature,
            enable:enable, gatewayInfoData:gatewayInfoData,supportSign:supportSign,supportCustomSign:supportCustomSign,
            signPosition:signPosition};
        postData = JSON.stringify(postData);

        $.ajax({
            url:basePath+'/gateway/save',
            type:'POST',
            data:postData,
            contentType:'application/json;charset=UTF-8',
            dataType:'json',
            success: function(re){
                console.info(re);
                if(re.status == 'SUCCESS'){
                    location.href= basePath+"/gateway/toGatewayList";
                }
                PopTipsShow(re.description);
            },
            error:function(){
                PopTipsShow('网络错误');
            }
        });

    });
})