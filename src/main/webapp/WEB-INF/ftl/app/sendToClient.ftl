<#import "/macro.ftl" as m >
<@m.page_header title="发送客户端透传消息" selected='app' subselected="appClientSend"  css="sendTo" js="" requirejs='sendToClient' />

    <!--右边内容 start-->
    <div class="main-detail">
        <div class="main-warp">
            <div class="main-title">
                <h2>消息推送</h2>
            </div>
            <!--  右侧自适应内容块主体 start  -->
            <div class="detail-wrap">
                <div class="form-body">
                    <form class="form-horizontal" role="form">
                        <table>
                            <div class="form-group">
                                <label for="appClientId" class="col-sm-2 control-label"><em class="required">*</em>客户端ID</label>
                                <div class="col-sm-10">
                                    <input type="text" name="appClientId" id="appClientId" class="form-control" placeholder="请输入客户端，多个ID以“,”隔开。">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="mobile" class="col-sm-2 control-label"><em class="required">*</em>应用ID</label>
                                <div class="col-sm-10">
                                    <input type="text" name="appId" id="appId" class="form-control" placeholder="请输入应用ID">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="content" class="col-sm-2 control-label"><em class="required">*</em>消息内容</label>
                                <div class="col-sm-10">
                                    <textarea name="content" id="content" class="form-control" rows="7"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="channel" class="col-sm-2 control-label"><em class="required">*</em>客户端通道</label>
                                <div class="col-sm-10">
                                    <div class="radio-wrap channelName">
                                        <#if channelList?size gt 0>
                                            <#list channelList as channel>
                                                <span class="option custom-radio">
                                                    <input type="radio" name="channelName" id="role-${channel_index}" class="select-channel" value="${channel.channelName}" checked>
                                                    <label for="role-${channel_index}"><span>${channel.channelName}</span></label>
                                                </span>
                                            </#list>
                                        <#else>
                                            <span><font color="red">该环境下没有可用通道，请先添加通道!</font></span>
                                        </#if>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button id="sendClient" type="button" class="btn btn-primary">发送</button>
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
<!--错误提示-->
<div class="error_msg"></div>

