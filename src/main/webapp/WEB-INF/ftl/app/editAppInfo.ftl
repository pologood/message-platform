<#import "/macro.ftl" as m >
<@m.page_header title="新建通道 " selected='setting' subselected="appInfo"  css="" requirejs="appInfoList" />
<!--右边内容 start-->
<div class="main-detail">
    <div class="main-warp">
        <div class="main-title">
            <h2 class="fl"><#if appInfo==null>新建<#else>编辑</#if>AppInfo</h2>
            <a href="${context.contextPath}/app/toAppInfoList" class="title-btn back-title-btn fr">
                <i>返回</i>
            </a>
        </div>
        <!--  右侧自适应内容块主体 start  -->
        <div class="detail-wrap">
            <div class="form-body">
                <form id="newAppInfo" class="form-horizontal" role="form"  action="${context.contextPath}/app/saveAppInfo" method="post">
                    <table>
                        <div class="form-group">
                            <label for="AppID" class="col-sm-2 control-label"><em class="required">*</em></em> AppID </label>
                            <div class="col-sm-10">
                                <input type="hidden" name="id" id="id" class="form-control" <#if appInfo!=null>value="${appInfo.id}"</#if> >
                                <input type="text" name="appId" id="appId" class="form-control" <#if appInfo!=null>value="${appInfo.appId}"</#if> >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="AppKey" class="col-sm-2 control-label"><em class="required">*</em> AppKey </label>
                            <div class="col-sm-10">
                                <input type="text" name="appKey" id="appKey" class="form-control" <#if appInfo!=null>value="${appInfo.appKey}"</#if> >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="MasterSecret" class="col-sm-2 control-label"><em class="required">*</em>MasterSecret</label>
                            <div class="col-sm-10">
                                <input type="text" name="masterSecret" id="masterSecret" class="form-control" <#if appInfo!=null>value="${appInfo.masterSecret}"</#if> >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="AppCode" class="col-sm-2 control-label"><em class="required">*</em>AppCode</label>
                            <div class="col-sm-10">
                                <input type="text" name="appCode" id="appCode" class="form-control" <#if appInfo!=null>value="${appInfo.appCode}"</#if> >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="AppName" class="col-sm-2 control-label"><em class="required">*</em>AppName</label>
                            <div class="col-sm-10">
                                <input type="text" name="appName" id="appName" class="form-control" <#if appInfo!=null>value="${appInfo.appName}"</#if> >
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button id="saveAppInfo" type="button" class="btn btn-primary">确定</button>
                            </div>
                        </div>
                    </table>
                </form>

            </div>
        </div>
        <!--  右侧自适应内容块主体 end  -->
    </div>
</div>
<!--右边内容 end-->
</div>
<!--主体部分 end-->
<div class="error_msg"></div>

