<#import "/macro.ftl" as m >
<@m.page_header title="网关列表" selected='setting' subselected="gateway"  css="msgList" requirejs='gatewayList' />
    <!--右边内容 start-->
    <div class="main-detail">
        <div class="main-warp">
            <div class="main-title">
                <h2 class="fl">网关管理</h2>
                <@shiro.hasRole name="admin">
                    <a href="${context.contextPath}/gateway/add" class="title-btn add-title-btn fr"><i>新增网关</i></a>
                </@shiro.hasRole>
            </div>
            <!--  右侧自适应内容块主体 start  -->
            <!--通道列表  start-->

            <table id="gatewayTable" class="table table-striped">
                <thead>
                    <tr>
                        <th>名称</th>
                        <th>统称</th>
                        <th>类型</th>
                        <th>描述</th>
                        <th>价格</th>
                        <th>短信剩余数</th>
                        <th>签名</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody style="font-size:11px">
                    <#list pager.list as gateway>
                    <tr <#if gateway_index % 2 != 0>class="success"</#if> data-type="${gateway.id}">
                        <td>${gateway.name}</td>
                        <td>${gateway.gatewayNature}</td>
                        <td>${gateway.type}</td>
                        <td>${gateway.description}</td>
                        <td><#if gateway.type == 'AMS'>--<#else>${gateway.price}</#if></td>
                        <td><#if gateway.type == 'AMS'>--<#else>${gateway.remain}</#if></td>
                        <td>
                            <#if gateway.supportCustomSign == 0>固定签名,<#elseif gateway.supportCustomSign == 1>自定义签名,<#elseif gateway.supportCustomSign == 2>不需要签名,</#if>
                            <#if gateway.signPosition == 'PREFIX'>签名前缀,<#elseif gateway.signPosition == 'SUFFIX'>签名后缀,</#if>
                            ${gateway.supportSign}
                        </td>
                        <td><#if gateway.enable == true>启用<#else>停用</#if></td>
                        <td>
                            <@shiro.hasRole name="admin">
                                <a href="${context.contextPath}/gateway/modify/${(gateway.id)!}" class="pop pop_modify">编辑</a>
                                <a href="javascript:void(0)"  class="gateway_delete" data-id="${gateway.id}">删除</a>
                                <a href="javascript:void(0)" class="pop pop_recharge"  <#if gateway.type != 'AMS'>data-toggle='modal' data-target=".bs-example-modal-sm" data-id="${gateway.id}">充值<#else>></#if></a>
                            </@shiro.hasRole>
                            <@shiro.hasAnyRoles name="general,finance">
                                无权限...
                            </@shiro.hasAnyRoles>
                        </td>
                    </tr>
                    </#list>
                </tbody>
            </table>
            <div class="paging" data-all="${pager.pageCount}" data-url="${context.contextPath}/gateway/toGatewayList?" data-now="${pager.currentPage}">
                <p>
                </p>
                <div class="fyds">
                    <em>每页显示</em>
                        <span class="fysd_sp1">
                            <input type="text" value="${pager.pageSize}" class="pag_inputs" >
                            <span class="fysd_sp2">
                                <a href="${context.contextPath}/gateway/toGatewayList?pageSize=10" <#if pager.pageSize == 10>class="pagon"</#if>>10</a>
                                <a href="${context.contextPath}/gateway/toGatewayList?pageSize=20" <#if pager.pageSize == 20>class="pagon"</#if>>20</a>
                                <a href="${context.contextPath}/gateway/toGatewayList?pageSize=30" <#if pager.pageSize == 30>class="pagon"</#if>>30</a>
                                <a href="${context.contextPath}/gateway/toGatewayList?pageSize=40" <#if pager.pageSize == 40>class="pagon"</#if>>40</a>
                                <a href="${context.contextPath}/gateway/toGatewayList?pageSize=50" <#if pager.pageSize == 50>class="pagon"</#if>>50</a>
                            </span>
                        </span>
                    <em>条</em>
                </div>
            </div>

            <div class="mask"></div>
            <div class="error_msg"></div>


            <!--充值 start-->
            <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
                <div class="modal-dialog modal-sm">
                    <div class="modal-content">
                        <#---->
                        <div class="modal-header" style="font-size:10px">
                            <h5 class="modal-title" id="exampleModalLabel">短信充值</h5>
                        </div>
                        <div class="modal-body">
                            <form>
                                <div class="form-group">
                                    <div class="input-group">
                                        <input id="gatewayId" name="gatewayId" type="hidden"/>
                                        <input id="rechargeNum" name="rechargeNum" type="text" class="form-control" placeholder="正整数 [1-10W]"
                                               aria-describedby="basic-addon2" onkeyup="value=this.value.replace(/\D+/g,'')">
                                        <span class="input-group-addon" id="basic-addon2" style="font-size:10px">单位：条</span>
                                    </div>
                                    <div class="error_msg_recharge"></div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary" id="gateway_recharge">确定</button>
                        </div>
                         <#---->
                    </div>
                </div>
            </div>
            <!--充值 end-->
        </div>
    </div>
</body>
</html>