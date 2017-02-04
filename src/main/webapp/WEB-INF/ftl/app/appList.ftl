<#import "/macro.ftl" as m >
<@m.page_header title="消息推送列表" selected='app' subselected="appMsg"  css="msgList" js="" requirejs='msgList' />
<!--右边内容 start-->
<div class="main-detail">
    <div class="main-warp">
        <div class="main-title">
            <h2 class="fl">消息推送列表</h2>
            <button type="button" class="show-sel fr" data-toggle="collapse" data-target="#searchAPP"><i>展开搜索</i></button>
        </div>
        <!--  右侧自适应内容块主体 start  -->
        <!--消息筛选  start-->
        <div id="searchAPP" class="collapse">
            <div class="panel panel-default">
                <div class="panel-body"  style="padding: 10px 10px 10px; border:1px; width:1250px; height:60px;">
                    <form class="bs-example bs-example-form" role="form" action="${context.contextPath}/app/toAppList" method="post">
                        <table>
                            <tr>
                                <td>
                                    <div class="input-group input-group-sm" style="padding: 5px 5px 5px;">
                                        <span class="input-group-addon" style="font-size:10px">推送内容</span>
                                        <input id="content" name="content" type="text" class="form-control" placeholder="content">
                                    </div>
                                </td>
                                <td>
                                    <div class="input-group input-group-sm" style="padding: 5px 5px 5px;">
                                        <span class="input-group-addon" style="font-size:10px">应用名称</span>
                                        <input id="appName" name="appName" type="text" class="form-control" placeholder="appName">
                                    </div>
                                </td>
                                <td>
                                    <div class="input-group input-group-sm" style="padding: 5px 5px 5px;">
                                        <span class="input-group-addon" style="font-size:10px">选择状态</span>
                                        <select name="status" class="styled-select" style="font-size:10px">
                                            <option value="">全部状态</option>
                                            <option value="SUCCESS">发送成功</option>
                                            <option value="SENDING">发送中</option>
                                            <option value="EMANATE">提交至网关</option>
                                            <option value="FAILED">发送失败</option>
                                        </select>
                                    </div>
                                </td>
                                <td>
                                    <div class="input-group input-group-sm" style="padding: 5px 5px 5px;">
                                        <span class="input-group-addon" style="font-size:10px">推送类型</span>
                                        <select name="sendWay" class="styled-select" style="font-size:10px">
                                            <option value="">全部类型</option>
                                            <option value="BYWEB">WEB推送</option>
                                            <option value="BYAPI">API推送</option>

                                        </select>
                                    </div>
                                </td>
                                <td>
                                    <div class="input-group input-group-sm" style="padding: 5px 5px 5px;">
                                        <span class="input-group-addon" style="font-size:10px">推送时间</span>
                                        <input type="text" class="form-control" id="startDate" name="startTime" placeholder="startTime" readonly  style="font-size:10px"/>
                                        <span class="input-group-addon" style="font-size:10px">-</span>
                                        <input type="text" class="form-control" id="endDate" name="endTime" placeholder="endTime" readonly  style="font-size:10px"/>
                                        <span class="input-group-btn">
                                            <button class="btn btn-default" type="submit">搜索!</button>
                                         </span>
                                    </div>
                                </td>
                            </tr>
                        </table>
                        <#--<div class="input-group input-group-sm" style="padding: 5px 5px 5px;">
                            <span class="input-group-addon" style="font-size:10px">选择应用</span>
                            <#list appInfoList as appInfo>
                                <span class="option custom-radio">
                                    <label class="checkbox-inline" style="font-size:12px">
                                        <input name="appId" id="app-${appInfo.appId}" type="radio" value="${appInfo.appId}">
                                        <span >${appInfo.appName}</span>
                                    </label>
                                </span>
                            </#list>
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="submit">搜索!</button>
                            </span>
                        </div>-->
                    </form>
                </div>
            </div>
        </div>

        <!--消息筛选  end-->
        <!--消息列表  start-->
        <table id="receiveSMSTable" class="table table-striped">
            <thead>
            <tr>
                <th>客户端ID</th>
                <th>内容</th>
                <th>应用名称</th>
                <th>状态</th>
                <th>推送类型</th>
                <th>推送时间</th>
            </tr>
            </thead>
            <tbody style="font-size:11px">
            <#list pager.list as message>
            <tr <#if message_index % 2 != 0>class="success"</#if> data-type="${message.id}">
                <td>${message.appClientId}</td>
                <td data-toggle="tooltip" title="${message.content}">${message.simContent}</td>
                <td>${message.appName}</td>
                <td>${message.status}</td>
                <td>${message.sendWay}</td>
                <td><#if message.receivingTime>${message.receivingTime}
                    <#else>${message.createDate}</#if>
                </td>
            </tr>
            </#list>
            </tbody>
        </table>
        <div class="paging" data-all="${pager.pageCount}" data-url="${context.contextPath}/app/toAppList?<#if request.sendWay>sendWay=${request.sendWay}&</#if><#if request.status>status=${request.status}&</#if><#if request.endTime>endTime=${request.endTime}&</#if><#if request.startTime>startTime=${request.startTime}&</#if><#if request.appName>appName=${request.appName}&</#if><#if request.appInfoId>appInfoId=${request.appInfoId}&</#if><#if request.content>content=${request.content}&</#if><#if request.appId>appId=${request.appId}&</#if><#if pager.pageSize>pageSize=${pager.pageSize}&</#if>" data-now="${pager.currentPage}">
            <p></p>
            <div class="fyds">
                <em>每页显示</em>
                        <span class="fysd_sp1">
                            <input type="text" value="${pager.pageSize}" class="pag_inputs" readonly>
                            <span class="fysd_sp2">
                                <a href="${context.contextPath}/app/toAppList?<#if request.sendWay>sendWay=${request.sendWay}&</#if><#if request.status>status=${request.status}&</#if><#if request.endTime>endTime=${request.endTime}&</#if><#if request.startTime>startTime=${request.startTime}&</#if><#if request.appName>appName=${request.appName}&</#if><#if request.appInfoId>appInfoId=${request.appInfoId}&</#if><#if request.content>content=${request.content}&</#if><#if request.appId>appId=${request.appId}&</#if>pageSize=10" <#if pager.pageSize == 10>class="pagon"</#if>>10</a>
                                <a href="${context.contextPath}/app/toAppList?<#if request.sendWay>sendWay=${request.sendWay}&</#if><#if request.status>status=${request.status}&</#if><#if request.endTime>endTime=${request.endTime}&</#if><#if request.startTime>startTime=${request.startTime}&</#if><#if request.appName>appName=${request.appName}&</#if><#if request.appInfoId>appInfoId=${request.appInfoId}&</#if><#if request.content>content=${request.content}&</#if><#if request.appId>appId=${request.appId}&</#if>pageSize=20" <#if pager.pageSize == 20>class="pagon"</#if>>20</a>
                                <a href="${context.contextPath}/app/toAppList?<#if request.sendWay>sendWay=${request.sendWay}&</#if><#if request.status>status=${request.status}&</#if><#if request.endTime>endTime=${request.endTime}&</#if><#if request.startTime>startTime=${request.startTime}&</#if><#if request.appName>appName=${request.appName}&</#if><#if request.appInfoId>appInfoId=${request.appInfoId}&</#if><#if request.content>content=${request.content}&</#if><#if request.appId>appId=${request.appId}&</#if>pageSize=30" <#if pager.pageSize == 30>class="pagon"</#if>>30</a>
                                <a href="${context.contextPath}/app/toAppList?<#if request.sendWay>sendWay=${request.sendWay}&</#if><#if request.status>status=${request.status}&</#if><#if request.endTime>endTime=${request.endTime}&</#if><#if request.startTime>startTime=${request.startTime}&</#if><#if request.appName>appName=${request.appName}&</#if><#if request.appInfoId>appInfoId=${request.appInfoId}&</#if><#if request.content>content=${request.content}&</#if><#if request.appId>appId=${request.appId}&</#if>pageSize=40" <#if pager.pageSize == 40>class="pagon"</#if>>40</a>
                                <a href="${context.contextPath}/app/toAppList?<#if request.sendWay>sendWay=${request.sendWay}&</#if><#if request.status>status=${request.status}&</#if><#if request.endTime>endTime=${request.endTime}&</#if><#if request.startTime>startTime=${request.startTime}&</#if><#if request.appName>appName=${request.appName}&</#if><#if request.appInfoId>appInfoId=${request.appInfoId}&</#if><#if request.content>content=${request.content}&</#if><#if request.appId>appId=${request.appId}&</#if>pageSize=50" <#if pager.pageSize == 50>class="pagon"</#if>>50</a>
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