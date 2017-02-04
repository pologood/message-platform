<#import "/macro.ftl" as m >
<@m.page_header title="发送短信" selected='sms' subselected="sendSms"  css="sendTo" js="" requirejs='sendSMS' />

    <!--右边内容 start-->
    <div class="main-detail">
        <div class="main-warp pb100">
            <div class="main-title">
                <h2>发送短信</h2>
            </div>
            <!--  右侧自适应内容块主体 start  -->
                <div class="detail-wrap">
                    <div class="form-body">
                        <form class="form-horizontal" role="form">
                            <#--手机号-->
                            <div class="form-group">
                                <label for="mobile" class="col-sm-2 control-label"><em class="required">*</em>手机号</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control mobile"  placeholder="请输入手机号码，多个号码以“，”隔开">
                                </div>
                            </div>
                            <#--使用模版-->
                            <div class="form-group">
                                <label for="template" class="col-sm-2 control-label">使用模版</label>
                                <div class="col-sm-10">
                                    <div class="checkbox-wrap template">
                                        <span id="template-label" class="option custom-checkbox">
                                            <input type="checkbox" name="template" id="ifTemplate" class="select-template" value="1">
                                            <label for="ifTemplate"><span>启用</span></label>
                                        </span>
                                    </div>
                                    <div class="input-group input-group-sm templateDiv" style="float:left; width:300px;display: none">
                                        <input type="text" class="form-control code-value" placeholder="模版编码,必填" style="width: 100px">
                                        <button class="btn btn-sm template-code" type="button"> Go!</button>
                                        <textarea class="form-control template-value" rows="1" disabled></textarea>
                                        <#--<input type="text" class="form-control template-value" disabled>-->
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="content" class="col-sm-2 control-label"><em class="required">*</em>短信内容</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control content" rows="5" placeholder="短信内容"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="useCustomSign" class="col-sm-2 control-label">自定义签名</label>
                                <div class="col-sm-10">
                                    <div class="checkbox-wrap useCustomSign">
                                    <span id="useCustomSign-label" class="option custom-checkbox">
                                        <input type="checkbox" name="useCustomSign" id="useCustomSign" class="select-sign" value="1">
                                        <label for="useCustomSign"><span>启用</span></label>
                                    </span>
                                    </div>
                                    <div class="input-group input-group-sm signDiv" style="float:left; width:300px;display: none">
                                        <input type="text" class="form-control sign" placeholder="自定义签名,必填" style="width: 150px">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button id="sendSMS" type="button" class="btn btn-primary">发送</button>
                                </div>
                            </div>
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