<#import "/macro.ftl" as m >
<@m.page_header title="接收短信列表" selected='sms' subselected="receiveSms"  css="msgList" js="" requirejs='receiveSMSList' />
    <!--右边内容 start-->
    <div class="main-detail">
        <div class="main-warp">
            <div class="main-title">
                <h2 class="fl">接收短信列表</h2>
                <button type="button" class="show-sel fr" data-toggle="collapse"
                        data-target="#searchSMS">
                    <i>展开搜索</i>
                </button>
            </div>
            <!--  右侧自适应内容块主体 start  -->
            <!--消息筛选  start-->
            <div id="searchSMS" class="collapse">
                <div class="panel panel-default">
                    <div class="panel-body"  style="padding: 10px 10px 10px; border:1px; width:600px; height:60px;">
                        <form class="bs-example bs-example-form" role="form" action="" method="get">
                            <table>
                                <tr>
                                    <td>
                                        <div class="input-group input-group-sm" style="padding: 5px 5px 5px;">
                                            <span class="input-group-addon" style="font-size:10px">手机号码</span>
                                            <input id="mobile" name="mobile" type="text" class="form-control" placeholder="phoneNo">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="input-group input-group-sm" style="padding: 5px 5px 5px;">
                                            <span class="input-group-addon" style="font-size:10px">网关</span>
                                            <input name="gatewayName" type="text" class="form-control" placeholder="gatewayName">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="input-group input-group-sm" style="padding: 5px 5px 5px;">
                                            <span class="input-group-addon" style="font-size:10px">运营号码</span>
                                            <input name="spCode" type="text" class="form-control" placeholder="spCode">
                                             <span class="input-group-btn">
                                                <button class="btn btn-default" type="submit" ">搜索!</button>
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
                    <th>号码</th>
                    <th>内容</th>
                    <th>网关</th>
                    <th>运营商号码</th>
                    <th>时间</th>
                </tr>
                </thead>
                <tbody style="font-size:11px">
                <#list pager.list as message>
                <tr <#if message_index % 2 != 0>class="success"</#if> data-type="${message.id}">
                <#--<td>${message_index + 1}</td>-->
                    <td>${message.mobile}</td>
                    <td>${message.content}</td>
                    <td>${message.gatewayName}</td>
                    <td>${message.spCode}</td>
                    <td><#if message.receivingTime>${message.receivingTime}
                        <#else>${message.createDate}</#if>
                    </td>
                </tr>
                </#list>
                </tbody>
            </table>
            <div class="paging" data-all="${pager.pageCount}" data-url="${context.contextPath}/sms/toReceiveSMSList?<#if request.mobile>mobile=${request.mobile}&</#if><#if request.gatewayName>gatewayName=${request.gatewayName}&</#if><#if request.spCode>spCode=${request.spCode}&</#if><#if pager.pageSize>pageSize=${pager.pageSize}&</#if>" data-now="${pager.currentPage}">
                <p>
                </p>
                <div class="fyds">
                    <em>每页显示</em>
                        <span class="fysd_sp1">
                            <input type="text" value="${pager.pageSize}" class="pag_inputs" readonly>
                            <span class="fysd_sp2">
                                <a href="${context.contextPath}/sms/toReceiveSMSList?<#if request.mobile>mobile=${request.mobile}&</#if><#if request.gatewayName>gatewayName=${request.gatewayName}&</#if><#if request.spCode>spCode=${request.spCode}&</#if>pageSize=10" <#if pager.pageSize == 10>class="pagon"</#if>>10</a>
                                <a href="${context.contextPath}/sms/toReceiveSMSList?<#if request.mobile>mobile=${request.mobile}&</#if><#if request.gatewayName>gatewayName=${request.gatewayName}&</#if><#if request.spCode>spCode=${request.spCode}&</#if>pageSize=20" <#if pager.pageSize == 20>class="pagon"</#if>>20</a>
                                <a href="${context.contextPath}/sms/toReceiveSMSList?<#if request.mobile>mobile=${request.mobile}&</#if><#if request.gatewayName>gatewayName=${request.gatewayName}&</#if><#if request.spCode>spCode=${request.spCode}&</#if>pageSize=30" <#if pager.pageSize == 30>class="pagon"</#if>>30</a>
                                <a href="${context.contextPath}/sms/toReceiveSMSList?<#if request.mobile>mobile=${request.mobile}&</#if><#if request.gatewayName>gatewayName=${request.gatewayName}&</#if><#if request.spCode>spCode=${request.spCode}&</#if>pageSize=40" <#if pager.pageSize == 40>class="pagon"</#if>>40</a>
                                <a href="${context.contextPath}/sms/toReceiveSMSList?<#if request.mobile>mobile=${request.mobile}&</#if><#if request.gatewayName>gatewayName=${request.gatewayName}&</#if><#if request.spCode>spCode=${request.spCode}&</#if>pageSize=50" <#if pager.pageSize == 50>class="pagon"</#if>>50</a>
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
