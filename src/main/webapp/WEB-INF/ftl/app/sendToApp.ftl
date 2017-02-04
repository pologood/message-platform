<#import "/macro.ftl" as m >
<@m.page_header title="发送app透传消息" selected='app' subselected="appSend"  css="sendTo" js="" requirejs='sendToApp' />

    <!--右边内容 start-->
    <div class="main-detail">
        <div class="main-warp">
            <div class="main-title"><h2>发送APP透传消息</h2></div>
            <!--  右侧自适应内容块主体 start  -->
            <div class="detail-wrap">
                <div class="form-body">
                    <form class="form-horizontal" role="form" id="sendToApp" action="${context.contextPath}/app/appSend" method="post">
                        <table>
                            <div class="form-group">
                                <label for="mobile" class="col-sm-2 control-label"><em class="required">*</em>描述</label>
                                <div class="col-sm-10">
                                    <input type="text" name="description" id="description" class="form-control"  placeholder="只用于后续查找识别类似备注">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="content" class="col-sm-2 control-label"><em class="required">*</em>消息内容</label>
                                <div class="col-sm-10">
                                    <textarea name="content" id="content" class="form-control" rows="7"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="channel" class="col-sm-2 control-label"><em class="required">*</em>选择应用</label>
                                <div class="col-sm-10">
                                    <div class="checkbox-wrap">
                                    <#list appNameList as appName>
                                        <span class="option custom-checkbox">
                                            <input type="checkbox" name="appName" id="${appName}" class="select-app" value="${appName}">
                                            <label for="${appName}"><span>${appName}</span></label>
                                        </span>
                                    </#list>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="channel" class="col-sm-2 control-label"><em class="required">*</em>推送方式</label>
                                <div class="col-sm-10">
                                    <div class="radio-wrap push-mode">
                                        <span class="option custom-radio">
                                            <input type="radio" name="executeType" id="time-a" class="select-push-mode" value="IMMEDIATE" checked="checked" />
                                            <label for="time-a"><span>即时</span></label>
                                        </span>
                                        <span class="option custom-radio">
                                            <input type="radio" name="executeType" id="time-b" class="select-push-mode" value="TIME" >
                                            <label id="timing" for="time-b"><span>定时</span></label>
                                            <input type="text" class="date-time verification" id="timer" name="executeTime" value="" disabled="disabled"/>
                                        </span>
                                    </div>
                                </div>
                            </div>


                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button id="sendApp" type="button" class="btn btn-primary">发送</button>
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

