<#import "/macro.ftl" as m >
<@m.page_header title="修改网关" selected='setting' subselected="gateway"  css="sendTo" js="" requirejs='gatewayInput' />
    <!--右边内容 start-->
    <div class="main-detail">
        <#-- 网关基本信息 -->
        <div class="main-warp">
            <div class="main-title">
                <h2 class="fl">网关基本信息</h2>
                <a href="${context.contextPath}/gateway/toGatewayList" class="title-btn back-title-btn fr">
                    <i>返回</i>
                </a>
            </div>
            <!--  右侧自适应内容块主体 start  -->

            <div class="detail-wrap">
                <div class="form-body">
                    <form id="modifyGateway" class="form-horizontal" role="form"  action="${context.contextPath}/gateway/modify" method="post">
                        <table>
                            <div class="form-group">
                                <label for="mobile" class="col-sm-2 control-label"><em class="required">*</em>网关名称</label>
                                <div class="col-sm-10">
                                    <input type="hidden" class="id" value="${gateway.id}">
                                    <input type="text" class="form-control name" value="${gateway.name}" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="description" class="col-sm-2 control-label"><em class="required">*</em>描述（备注）</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control description" rows="1" >${gateway.description}</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="gatewayInfoData" class="col-sm-2 control-label"><em class="required">*</em>账号数据</label>
                                <div class="col-sm-10">
                                    <textarea  class="form-control gatewayInfoData" rows="3" >${gateway.gatewayInfoData}</textarea>
                                </div>
                            </div>
                            <#--如果是编辑短信网关 则显示签名相关信息-->
                            <#if gateway.type != 'AMS'>
                            <div class="form-group">
                                <label for="supportSign" class="col-sm-2 control-label">固定签名</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control supportSign" value="${gateway.supportSign}" >
                                </div>
                            </div>


                            <div class="form-group">
                                <label for="supportCustomSign" class="col-sm-2 control-label"><em class="required">*</em>签名类型</label>
                                <div class="col-sm-10">
                                    <div class="radio-wrap supportCustomSign">
                                        <#list scsMap?keys as scs>
                                        <#--<#list scsMap as scs>-->
                                            <span class="option custom-radio">
                                                 <input type="radio" name="supportCustomSign" id="supportCustomSign-${scs}"  class="select-scs" value="${scs}" <#if scs==gateway.supportCustomSign>checked="checked"</#if>/>
                                                 <label for="supportCustomSign-${scs}"><span>${scsMap[scs]}</span></label>
                                            </span>
                                        </#list>
                                    </div>
                                </div>
                            </div>


                            <div class="form-group">
                                <label for="signPosition" class="col-sm-2 control-label">签名位置</label>
                                <div class="col-sm-10">
                                    <div class="radio-wrap signPosition">
                                        <#list spMap?keys as sp>
                                            <span class="option custom-radio">
                                                 <input type="radio" name="signPosition" id="signPosition-${sp}"  class="select-sp" value="${sp}" <#if sp==gateway.signPosition>checked="checked"</#if>/>
                                                 <label for="signPosition-${sp}"><span>${spMap[sp]}</span></label>
                                            </span>
                                        </#list>
                                    </div>
                                </div>
                            </div>
                            </#if>
                            <div class="form-group">
                                <label for="gatewayType" class="col-sm-2 control-label"><em class="required">*</em>网关类型</label>
                                <div class="col-sm-10">
                                    <div class="radio-wrap gatewayType">
                                        <span class="option custom-radio">
                                            <input type="radio" name="type" id="gatewayType" class="select-gatewayType" value="${gateway.type}" checked="checked"/>
                                            <label for="gatewayType"><span>${gateway.type}</span></label>
                                        </span>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="gatewayNature" class="col-sm-2 control-label"><em class="required">*</em>名称类型</label>
                                <div class="col-sm-10">
                                    <div class="radio-wrap gatewayNature">
                                        <span class="option custom-radio">
                                             <input type="radio" name="gatewayNature" id="gatewayNature"  class="select-gateway" value="${gateway.gatewayNature}" checked="checked"/>
                                             <label for="gatewayNature"><span>${gateway.gatewayNature}</span></label>
                                         </span>
                                    </div>
                                </div>
                            </div>


                            <div class="form-group">
                                <label for="mobile" class="col-sm-2 control-label"><em class="required">*</em>网关价格</label>
                                <div class="col-sm-10">
                                    <div class="input-group input-group-sm">
                                        <span class="input-group-addon" style="font-size:10px">单位：分</span>
                                        <input type="number" class="form-control price" value="${gateway.price}"  style="width: 100px">

                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="content" class="col-sm-2 control-label"><em class="required">*</em>是否启用</label>
                                <div class="col-sm-10">
                                    <div class="radio-wrap enable">
                                        <span class="option custom-radio">
                                            <input type="radio" name="enable" id="state-a" class="select-state" value="1" <#if gateway.enable==true>checked="checked"</#if>/>
                                            <label for="state-a"><span>启用</span></label>
                                        </span>
                                        <span class="option custom-radio">
                                            <input type="radio" name="enable" id="state-b" class="select-state" value="0"/ <#if gateway.enable==false>checked="checked"</#if>/>
                                            <label for="state-b"><span>停用</span></label>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="button" class="btn btn-primary modifyGateway">确定</button>
                                </div>
                            </div>
                        </table>
                    </form>

                </div>
            </div>
            <!--  右侧自适应内容块主体 end  -->
            <#--<div class="main-title">
                <h2 class="fl">网关Api账户信息</h2>
            </div>
            <div class="form-item">
                <div class="form-label">
                    第三方网关选择
                </div>
                <div class="form-detail pr">
                    <div class="drop-down">
                        <input type="text" value="${(gatewayNatureName)!'请选择'}" data-type="${(gatewayNature)!''}"   class="drop-txt" readonly />
                        <input type="hidden" class="state" value="${(gatewayNature)!''}"  data-value="${(gatewayNatureValue)!''}" id="gatewayNatureHid" name="gatewayNature" />
                        <span class="drop-span">
                            <a href="javascript:void(0)" data-type="YIDA"  data-value="0">易达通道</a>
                            <a href="javascript:void(0)" data-type="XUANWU" data-value="1">玄武网关</a>
                            <a href="javascript:void(0)" data-type="JIANZHOU" data-value="2">建周网关</a>
                            <a href="javascript:void(0)" data-type="ECP" data-value="3">中国电信网关</a>
                            <a href="javascript:void(0)" data-type="EMPP" data-value="4">中国移动网关</a>
                            <a href="javascript:void(0)" data-type="EUCP" data-value="5">亿美软通网关</a>
                            <a href="javascript:void(0)" data-type="UMS" data-value="6">中国联通网关</a>
                            <a href="javascript:void(0)" data-type="YZECP" data-value="7">宇宙之迅网关</a>
                        </span>
                    </div>
                </div>
            </div>-->
            <#--<form name="newGateway"   id="gatewayInfoForm" action="${context.contextPath}/gateway/info/add/" method="post">  </form>-->
        </div>
    </div>
    <!--右边内容 end-->
</div>
<!--主体部分 end-->
<div class="error_msg"></div>
</body>
</html>