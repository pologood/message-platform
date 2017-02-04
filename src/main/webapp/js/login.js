/*  @description: 消息推送平台 消息列表页js
 *   @author: wuxiaoxia
 *	@update: wuxiaoxia 2015-09-11*/
//配置require文件  start
require.config({
    baseUrl: basePath + "/js/lib",
    paths: {  //引入js文件路径配置
        'jquery': 'jquery-1.8.3.min'
    },
//  enforceDefine:true,
    urlArgs: "t=" + (new Date()).getTime()
});
require(["jquery"], function ($) {

    if (typeof JSON == 'undefined') {//判断支不支持JSON对象，不支持的浏览器加载json2.js
        $('head').append($("<script type='text/javascript' src='" + basePath + "/js/json2.js'>"));
    }
    ;

    $(".inputStyle").each(function () {
        var self = $(this);
        self.bind('paste cut keydown keyup focus blur', function () {
            Keyup();
        })
    });
    function Keyup() {
        var name = $('#name').val().trim(),
            pwd = $('#pwd').val().trim(),
            getCode = $("#getCode").val().trim();
        if (name != '' && pwd != '' && getCode != '') {
            $(".loginBtn").removeClass('unloginBtn');
        } else {
            $(".loginBtn").addClass('unloginBtn');
        }
        $('.red').removeClass('on');
    }


    $(".loginBtn").click(function () {
        var name = $("#name").val().trim(),
            pwd = $("#pwd").val().trim(),
            getCode = $("#getCode").val().trim();

        /*账号密码前端验证start*/
        if ($(this).hasClass('unloginBtn')) {
            return false;
        }
        if (name === '' && pwd === '') {
            $('.red').addClass('on').text("账号与密码不能为空重新输入");
            return false;
        }
        ;
        if (name === '') {
            $('.red').addClass('on').text("账号不能为空重新输入");
            return false;
        }
        ;
        if (pwd === '') {
            $('.red').addClass('on').text("密码不能为空重新输入");
            return false;
        }
        ;
        if (!$("#getCode").is(":hidden")) {
            if (getCode == "") {
                $('.red').addClass('on').text("验证码不能为空重新输入");
                return false;
            }
            if (getCode.length != 4) {
                $('.red').addClass('on').text("验证码输入有误重新输入");
                return false;
            }
        }
        /*账号密码前端验证end*/
        $('#loginform').submit();
    });

    $('#getCodeBtn').click(function (event) {//生成验证码
        var self = $(this),
            src = self.data('url');
        self.hide().attr('src', src + '?t=' + new Date().getTime()).fadeIn();
        event.cancelBubble = true;
    });
    $(document).keypress(function (e) {
        // 回车键事件
        if (e.which == 13) {
            jQuery(".loginBtn").click();
        }
    });


})