<#import "/macro.ftl" as m >
<@m.page_header title="编辑通道 " selected='setting' subselected="channel"  css="" requirejs="channelInput" />
    <!--右边内容 start-->
    <div class="main-detail">
        <div class="main-warp">
            <div class="main-title">
                <h2 class="fl">编辑通道</h2>
                <a href="${context.contextPath}/channel/toChannelList" class="title-btn back-title-btn fr">
                    <i>返回</i>
                </a>
            </div>
            <!--  右侧自适应内容块主体 start  -->
            <div class="detail-wrap">
                <div class="form-body">
                    <form id="updateChannel" class="form-horizontal" role="form">
                        <table>
                            <div class="form-group">
                                <label for="channelName" class="col-sm-2 control-label">通道名称</label>
                                <div class="col-sm-10">
                                    <input type="hidden" class="id" value="${channel.id}">
                                    <input type="text" class="form-control channelName" value="${channel.channelName}" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="description" class="col-sm-2 control-label">描述（备注）</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control description" rows="3" >${channel.description}</textarea>
                                </div>
                            </div>


                            <div class="form-group">
                                <label for="type" class="col-sm-2 control-label">通道类型</label>
                                <div class="col-sm-10">
                                    <div class="radio-wrap type">
                                        <span class="option custom-radio">
                                            <input type="radio" name="type" id="type" class="select-channelType" value="${channel.type}" checked="checked"/>
                                            <label for="type"><span>${channel.type}</span></label>
                                        </span>
                                    </div>
                                </div>
                            </div>


                            <div class="form-group">
                                <label for="enable" class="col-sm-2 control-label">是否启用</label>
                                <div class="col-sm-10">
                                    <div class="radio-wrap enable">
                                        <span class="option custom-radio">
                                            <input type="radio" name="enable" id="state-a" class="select-state" value="1" <#if channel.enable == true>checked="checked"</#if>/>
                                            <label for="state-a"><span>启用</span></label>
                                        </span>
                                        <span class="option custom-radio">
                                            <input type="radio" name="enable" id="state-b" class="select-state" value="0" <#if channel.enable == false>checked="checked"</#if>/>
                                            <label for="state-b"><span>停用</span></label>
                                        </span>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="gateways" class="col-sm-2 control-label">关联网关</label>
                                <div class="col-sm-10">
                                    <div class="checkbox-wrap gateways">
                                        <#if gateways?size gt 0>
                                            <#list gateways as gateway>
                                                <span class="option custom-checkbox">
                                                    <input id="${gateway.name}" type="checkbox"  class="select-gateway" value="${gateway.id}" <#if gateway.selected == true>checked="true"</#if>">
                                                    <label for="${gateway.name}"><span>${gateway.name}</span></label>
                                                </span>
                                            </#list>
                                        </#if>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="strategy" class="col-sm-2 control-label">网关策略</label>
                                <div class="col-sm-10">
                                    <div class="radio-wrap strategy">
                                        <#list strategyMap?keys as strategy>
                                            <span class="option custom-radio">
                                            <input id="${strategy}" type="radio" name="strategy" class="select-strategy" value="${strategy}" <#if strategy == channel.strategy>checked="checked"</#if>>
                                            <label for="${strategy}"><span>${strategyMap[strategy]}</span></label>
                                        </span>
                                        </#list>
                                        <input type="text" class="form-control strategy-input" value="${channel.priority}" style="width: 300px;font-size:10px;<#if channel.strategy != 'PRIORITY'>display:none</#if>">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="button" class="btn btn-primary updateChannel">确定</button>
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
