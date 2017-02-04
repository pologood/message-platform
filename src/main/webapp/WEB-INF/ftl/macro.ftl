<#macro page_header selected='' subselected='' css='' js='' title='' model='' model_css ='' model_js='' requirejs=''>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta version="tag 1.0.5">
    <title>${title} - 消息推送平台</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <#if selected=='coupon' || subselected='about'>
        <meta name="format-detection" content="telephone=no" />
    </#if>
    <link type="text/css" rel="stylesheet" href="${context.contextPath}/css/reset.css" />
    <link href="${context.contextPath}/css/common.css" type="text/css" rel="stylesheet" />
    <link href="${context.contextPath}/css/sendTo.css" type="text/css" rel="stylesheet" />
    <link href="${context.contextPath}/css/bootstrap.min.css" type="text/css" rel="stylesheet" /><!-- 新 Bootstrap 核心 CSS 文件 -->
    <#if css?length gt 0>
        <#list css?split("|") as c>
            <link rel="stylesheet" type="text/css" href="${base}/css/${c}.css?${version}">
        </#list>
    </#if>
    <#if model == ''>
        <script src="${base}/js/lib/jquery-1.91.min.js"></script><!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
        <script src="${base}/js/lib/bootstrap.min.js"></script><!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
        <script src="${base}/js/lib/bootbox.js"></script>
        <script src="${base}/js/lib/common.js"></script>
        <script src="${base}/js/lib/respond.js"></script>
        <script src="${base}/js/lib/My97DatePicker/WdatePicker.js"></script>
    </#if>
    <#if js?length gt 0>
        <#list js?split("|") as j>
            <script src="${base}/js/${j}.js?${version}"></script>
        </#list>
    </#if>
    <#if model ??>
        <#if model_js?length gt 0>
            <#list model_js?split("|") as j>
                <script src="${base}/js/require.js" defer async  data-main="${base}/js/${j}"></script>
            </#list>
        </#if>
    </#if>

    <!--[if IE 8]>
    <script src="${context.contextPath}/js/lib/respond.js" language="javascript" type="text/javascript"></script>
    <![endif]-->
   <script type="text/javascript">
        var basePath = "${context.contextPath}";
    </script>
    <#if requirejs != ''>
        <script src="${context.contextPath}/js/require.js" defer async data-main="${context.contextPath}/js/${requirejs}"></script>
    </#if>
</head>

<body>
<#--头部 start-->
<div class="header clearFix pr">
    <a class="logo"><span>消息推送平台</span></a>
    <div class="user userSelect">
        <span href="javascript:" class="UserName"><@shiro.principal/></span>
        <a href="${context.contextPath}/logout" class="exit">[退出]</a>
    </div>
</div>
<#--头部 end-->
<#--主体部分 start-->
<div class="main">
    <div class="nav">
        <ul class="nav-wrap">
            <li class="nav-item  <#if selected=='home' >current</#if>">
                <a href="${context.contextPath}/home" class="icon index">首页</a>
            </li>

            <li class="nav-item  <#if selected=='sms' >on</#if>">
                <a href="javascript:;" class="icon sms">短信管理</a>
                <ul>
                <@shiro.hasRole name="admin">
                    <li class="nav-item phone <#if subselected=='sendSms' >current</#if>">
                        <a href="${context.contextPath}/smsMessage/send">发送短信</a>
                    </li>
                </@shiro.hasRole>

                <li class="nav-item phone <#if subselected=='sendSmsList' >current</#if>">
                    <a href="${context.contextPath}/smsMessage/sendSmsList">发送短信列表</a>
                </li>

                <li class="nav-item <#if subselected=='template' >current</#if>">
                    <a href="${context.contextPath}/smsMessage/templateList">模板管理</a>
                </li>
                </ul>
            </li>

            <#--<li class="nav-item <#if selected=='app' >on</#if>">
                <a href="javascript:;" class="icon msg">消息推送</a>
                <ul>
                <@shiro.hasRole name="admin">
                    <li class="nav-item <#if subselected=='appClientSend' >current</#if>">
                        <a href="${context.contextPath}/app/toClientSend">消息推送</a>
                    </li>
                </@shiro.hasRole>
                    <li class="nav-item <#if subselected=='appInfo' >current</#if>">
                        <a href="${context.contextPath}/app/toAppInfoList">消息推送配置</a>
                    </li>
                    <li class="nav-item <#if subselected=='appMsg' >current</#if>">
                        <a href="${context.contextPath}/app/toAppList">消息推送列表</a>
                    </li>
                </ul>
            </li>-->
            <li class="nav-item <#if selected=='count' >on</#if>">
                <a href="javascript:;" class="icon count">数据统计</a>
                <ul>
                    <li class="nav-item <#if subselected=='deposit' >current</#if>">
                        <a href="${context.contextPath}/statistics/toDepositList">充值记录</a>
                    </li>
                    <li class="nav-item <#if subselected=='consumption' >current</#if>">
                        <a href="${context.contextPath}/statistics/toConsumptionRecordList">消费记录</a>
                    </li>
                </ul>
            </li>
            <li class="nav-item <#if selected=='account' >on</#if>">
                <a href="javascript:;" class="icon user-management">用户管理</a>
                <ul>
                    <li class="nav-item <#if subselected=='list' >current</#if>">
                        <a href="${context.contextPath}/user/list">用户列表</a>
                    </li>
                </ul>
            </li>
            <li class="nav-item <#if selected=='setting' >on</#if>">
                <a href="javascript:;" class="icon set">设置</a>
                <ul>
                    <li class="nav-item <#if subselected=='channel' >current</#if>">
                        <a href="${context.contextPath}/channel/list">通道管理</a>
                    </li>
                    <li class="nav-item <#if subselected=='sensitiveWord' >current</#if>">
                        <a href="${context.contextPath}/smsMessage/sensitiveWordList">敏感字管理</a>
                    </li>
                    <li class="nav-item <#if subselected=='password' >current</#if>">
                        <a href="${context.contextPath}/user/toPsdModify">登录密码修改</a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</#macro>
