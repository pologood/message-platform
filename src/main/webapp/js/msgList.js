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

    var Data_max='',//全局开始时间
        Data_min='';//全局结束时间

    /*时间插件 start*/
    $("#startDate").live("click", function () {
        //WdatePicker({ minDate:'1990-01-01', maxDate: '#F{$dp.$D(\'endDate\')||\''+Data_max+'\'}'})
        WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'1990-01-01 11:30:00', maxDate: '#F{$dp.$D(\'endDate\')||\''+Data_max+'\'}'});
    });
    $("#endDate").live("click", function () {
        //WdatePicker({ minDate: '#F{$dp.$D(\'startDate\')||\''+Data_min+'\'}', maxDate:'2050-01-01'})
        WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss', minDate: '#F{$dp.$D(\'startDate\')||\''+Data_min+'\'}', maxDate:'2050-01-01 11:30:00'});
    });
    /*时间插件 end*/

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



})
























