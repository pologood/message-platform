<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>登录页面 - 消息推送平台</title>
    <link type="text/css" rel="stylesheet" href="${context.contextPath}/css/reset.css" />
    <link type="text/css" rel="stylesheet" href="${context.contextPath}/css/login.css" />
    <!--[if IE 8]>
    <script src="${context.contextPath}/js/lib/respond.js" language="javascript" type="text/javascript"></script>
    <![endif]-->
    <script type="text/javascript">
        var basePath = "${context.contextPath}";
    </script>
    <script src="${context.contextPath}/js/require.js" defer async data-main="${context.contextPath}/js/login"></script>
</head>

<body>
<div class="logCont">
    <div class="main">
        <span class="logo">爱客仕</span>
        <div class="login">
            <form name="loginform" id="loginform" action="${context.contextPath}/login" method="post">
                <h2>消息推送平台${context.contextPath}<i>Sign In</i></h2>
                <div class="inputDiv">
                    <span class="inputSpanB"><input type="text" class="inputStyle" name="username" id="name" value="" /><label class="show">账号</label></span>
                    <span class="inputSpan"><input type="password" class="inputStyle" name="password" id="pwd" value="" /><label class="show">密码</label></span>
                    <span class="inputSpan inputCode">
                    	<input type="text" class="inputStyle inputStyle-code" name="captcha" id="getCode" value="" />
                        <img  class="getCodeBtn" id="getCodeBtn" data-url="/getCaptcha" src="/getCaptcha" />
                        <label class="show">验证码</label>
                    </span>
                </div>
                <span class="red <#if errorCode??>on</#if>">
                                 <#if errorCode == "SHIRO_1001">
                                     验证码输入有误重新输入
                                 <#else >
                                     用户名密码输入错误
                                 </#if>
                    </span>
                <input type="button" value="确定" class="loginBtn unloginBtn" />
            </form>
        </div>
    </div>
    <p class="p_copyright">&copy;爱客仕 2015</p>
</div>
</body>
</html>
