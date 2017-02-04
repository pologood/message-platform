<#import "/macro.ftl" as m >
<@m.page_header title="消息推送列表" selected='app' subselected="appInfo"  css="msgList" js="" requirejs='appInfoList' />
<!--右边内容 start-->
<div class="main-detail">
    <div class="main-warp">
        <div class="main-title">
            <h2 class="fl">消息推送配置</h2>
<@shiro.hasRole name="admin">
            <a href="${context.contextPath}/app/addAppInfo" class="title-btn add-title-btn fr"><i>新增APP</i></a>
</@shiro.hasRole>
            <button type="button" class="show-sel fr" data-toggle="collapse" data-target="#searchAPP"> <i>展开搜索</i></button>
        </div>
        <!--  右侧自适应内容块主体 start  -->
        <!--消息筛选  start-->
        <div id="searchAPP" class="collapse">
            <div class="panel panel-default">
                <div class="panel-body"  style="padding: 10px 10px 10px; border:1px; width:850px; height:60px;">
                    <form class="bs-example bs-example-form" role="form" action="${context.contextPath}/app/toAppInfoList" method="post">
                        <table>
                            <tr>
                                <td>
                                    <div class="input-group input-group-sm" style="padding: 5px 5px 5px;">
                                        <span class="input-group-addon" style="font-size:10px">应用ID</span>
                                        <input id="appId" name="appId" type="text" class="form-control" placeholder="appid">
                                    </div>
                                </td>

                                <td>
                                    <div class="input-group input-group-sm" style="padding: 5px 5px 5px;">
                                        <span class="input-group-addon" style="font-size:10px">应用编码</span>
                                        <input type="text" class="form-control" id="appCode" name="appCode" placeholder="appCode"/>

                                    </div>
                                </td>
                                <td>
                                    <div class="input-group input-group-sm" style="padding: 5px 5px 5px;">
                                        <span class="input-group-addon" style="font-size:10px">应用名称</span>
                                        <input type="text" class="form-control" id="appName" name="appName" placeholder="appName"/>
                                        <span class="input-group-btn">
                                            <button class="btn btn-default" type="submit">搜索!</button>
                                        </span>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>

        <!--消息筛选  end-->
        <!--消息列表  start-->
        <table id="receiveSMSTable" class="table table-striped">
            <thead>
            <tr>
            <#--<th>编号</th>-->

                <th>应用ID</th>
                <th>应用秘钥</th>
                <th>应用密码</th>
                <th>应用编码</th>
                <th>应用名称</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody style="font-size:11px">
            <#list pager.list as appInfo>
            <tr <#if appInfo_index % 2 != 0>class="success"</#if> data-type="${appInfo.id}">
            <#--<td>${message_index + 1}</td>-->
                <td>${appInfo.appId}</td>
                <td>${appInfo.appKey}</td>
                <td>******</td>
                <td>${appInfo.appCode}</td>
                <td>${appInfo.appName}</td>
                <td>
                    <@shiro.hasRole name="admin">
                    <a href="${context.contextPath}/app/updateAppInfo/${appInfo.id!}">修改</a>
                    <a href="javascript:void(0)" class="deleteAppInfo" data-id="${appInfo.id}">删除</a>
                     </@shiro.hasRole>
                    <@shiro.hasAnyRoles name="general,finance">
                        无权限...
                    </@shiro.hasAnyRoles>
                </td>
            </tr>
            </#list>
            </tbody>
        </table>
        <div class="paging" data-all="${pager.pageCount}" data-url="${context.contextPath}/app/toAppInfoList?<#if request.appId>appId=${request.appId}&</#if><#if request.appCode>appCode=${request.appCode}&</#if><#if request.appName>appName=${request.appName}&</#if><#if pager.pageSize>pageSize=${pager.pageSize}&</#if>" data-now="${pager.currentPage}"">
            <p></p>
            <div class="fyds">
                <em>每页显示</em>
                        <span class="fysd_sp1">
                            <input type="text" value="${pager.pageSize}" class="pag_inputs" readonly>
                            <span class="fysd_sp2">
                                <a href="${context.contextPath}/app/toAppInfoList?<#if request.appId>appId=${request.appId}&</#if><#if request.appCode>appCode=${request.appCode}&</#if><#if request.appName>appName=${request.appName}&</#if>pageSize=10" <#if pager.pageSize == 10>class="pagon"</#if>>10</a>
                                <a href="${context.contextPath}/app/toAppInfoList?<#if request.appId>appId=${request.appId}&</#if><#if request.appCode>appCode=${request.appCode}&</#if><#if request.appName>appName=${request.appName}&</#if>pageSize=20" <#if pager.pageSize == 20>class="pagon"</#if>>20</a>
                                <a href="${context.contextPath}/app/toAppInfoList?<#if request.appId>appId=${request.appId}&</#if><#if request.appCode>appCode=${request.appCode}&</#if><#if request.appName>appName=${request.appName}&</#if>pageSize=30" <#if pager.pageSize == 30>class="pagon"</#if>>30</a>
                                <a href="${context.contextPath}/app/toAppInfoList?<#if request.appId>appId=${request.appId}&</#if><#if request.appCode>appCode=${request.appCode}&</#if><#if request.appName>appName=${request.appName}&</#if>pageSize=40" <#if pager.pageSize == 40>class="pagon"</#if>>40</a>
                                <a href="${context.contextPath}/app/toAppInfoList?<#if request.appId>appId=${request.appId}&</#if><#if request.appCode>appCode=${request.appCode}&</#if><#if request.appName>appName=${request.appName}&</#if>pageSize=50" <#if pager.pageSize == 50>class="pagon"</#if>>50</a>
                            </span>
                        </span>
                <em>条</em>
            </div>
        </div>
        <!--消息列表  end-->
        <!--  右侧自适应内容块主体 end  -->
    </div>
</div>
<!--右边内容 end-->
</div>
<!--主体部分 end-->
<div class="mask"></div>
<div class="error_msg"></div>

<!--详情 start-->
<div id="app-detail" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
        <#---->
            <div class="modal-header" style="font-size:10px">
                <h5 class="modal-title" id="exampleModalLabel">APP详情</h5>
            </div>
            <div class="modal-body">
                <table>
                    <tr>
                        <td>
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">客户端ID</span>
                                <input id="clientId-detail" type="text" class="form-control" >
                            </div>
                        </td>
                        <td>
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">APP名称</span>
                                <input id="appName-detail" type="text" class="form-control" >
                            </div>
                        </td>
                        <td>
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">APP状态</span>
                                <input id="status-detail" type="text" class="form-control" >
                            </div>
                        </td>
                    </tr>

                    <tr>
                        <td colspan=3>
                            <div class="input-group input-group-sm">
                                <span class="input-group-addon">APP内容</span>
                                <textarea id="content-detail" class="form-control" rows="7"></textarea>
                            </div>
                        </td>
                    </tr>

                </table>
            </div>
            <div class="modal-footer">
                <button id="detail-back" type="button" class="btn btn-primary" data-dismiss="modal">返回</button>
            </div>
        <#---->
        </div>
    </div>
</div>
<!--详情 end-->


