<#import "/macro.ftl" as m >
<@m.page_header title="发送短信列表" selected='sms' subselected="sendSmsList"  css="msgList" js="" requirejs='sendSMSList' />
    <!--右边内容 start-->
    <div class="main-detail">
        <div class="main-warp">
            <div class="main-title">
                <h2 class="fl">发送短信列表</h2>
                <button type="button" class="show-sel fr" data-toggle="collapse"  data-target="#searchSMS"> <i>展开搜索</i> </button>
            </div>
            <!--  右侧自适应内容块主体 start  -->
            <!--消息筛选  start-->

            <div id="searchSMS" class="collapse">
                <div class="panel panel-default">
                    <div class="panel-body"  style="padding: 10px 10px 10px; border:1px; width:1400px; height:60px;">
                            <form class="bs-example bs-example-form" role="form" action="${context.contextPath}/sms/toSendSMSList" method="get">
                                <table>
                                <tr>
                                    <td>
                                        <div class="input-group input-group-sm" style="padding: 5px 5px 5px;">
                                            <span class="input-group-addon" style="font-size:10px">手机号码</span>
                                            <input id="mobile" name="mobile" type="text" class="form-control" placeholder="phoneNo">
                                        </div>
                                    </td>
                                    <#--<td>
                                        <div class="input-group input-group-sm" style="padding: 5px 5px 5px;">
                                            <span class="input-group-addon" style="font-size:10px">发送通道</span>
                                            <input name="channelName" type="text" class="form-control" placeholder="channel">
                                        </div>
                                    </td>-->

                                    <td>
                                        <div class="input-group input-group-sm" style="padding: 5px 5px 5px;">
                                            <span class="input-group-addon" style="font-size:10px">创建时间</span>
                                            <input type="text" class="form-control" id="startDate" name="startDate" placeholder="开始时间" readonly   style="font-size:10px"/>
                                            <span class="input-group-addon" style="font-size:10px">-</span>
                                            <input type="text" class="form-control" id="endDate" name="endDate" placeholder="截止时间" readonly  style="font-size:10px"/>
                                        </div>
                                    </td>

                                    <td>
                                        <div class="input-group input-group-sm" style="padding: 5px 5px 5px;">
                                            <span class="input-group-addon" style="font-size:10px">发送状态</span>
                                            <select name="status" class="styled-select" style="font-size:10px">
                                                <option value="">全部状态</option>
                                                <option value="PENDING">待发送</option>
                                                <option value="SENDING">推送至MQ</option>
                                                <option value="EMANATE">提交至网关</option>
                                                <#--<option value="DELIVRD">通道接受成功,但还未投递给用户</option>-->
                                                <option value="SUCCESS">发送成功</option>
                                                <option value="FAILED">发送失败</option>
                                            </select>
                                           <#-- <span class="input-group-addon" style="font-size:10px">发送状态</span>
                                            <input name="status" type="text" class="form-control" placeholder="status">-->
                                             <span class="input-group-btn">
                                                <button class="btn btn-default" type="button" ">搜索!</button>
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
            <table id="sendSMSTable" class="table table-striped">
                <thead>
                <tr>
                    <th>号码</th>
                    <th>内容</th>
                    <th>模板编码</th>
                    <th>用户</th>
                    <#--<th>通道</th>-->
                    <th>状态</th>
                    <th>反馈</th>
                    <th>时间</th>
                </tr>
                </thead>
                <tbody style="font-size:11px">
                    <#list pager.list as message>
                        <tr <#if message_index % 2 != 0>class="success"</#if> data-type="${message.id}">
                            <td>${message.mobile}</td>
                            <td data-toggle="tooltip" title="${message.content}">${message.simContent}</td>
                            <td>${message.templateCode}</td>
                            <td>${message.userName}</td>
                            <#--<td>${message.channelName}</td>-->
                            <td>${message.status}</td>
                            <td data-toggle="tooltip" title="${message.comment}">${message.simComment}</td>
                            <td><#if message.executeTime>${message.executeTime?string("yyyy-MM-dd HH:mm:ss")}
                                <#else>${message.createDate?string("yyyy-MM-dd HH:mm:ss")}
                                </#if>
                            </td>
                        </tr>
                    </#list>
                </tbody>
            </table>
            <div class="paging" data-all="${pager.pageCount}" data-url="${context.contextPath}/sms/toSendSMSList?<#if request.mobile>mobile=${request.mobile}&</#if><#if request.channelName>channelName=${request.channelName}&</#if><#if request.status>status=${request.status}&</#if><#if request.startDate>startDate=${request.startDate}&</#if><#if request.endDate>endDate=${request.endDate}&</#if><#if pager.pageSize>pageSize=${pager.pageSize}&</#if>" data-now="${pager.currentPage}">
                <p>
                </p>
                <div class="fyds">
                    <em>每页显示</em>
                        <span class="fysd_sp1">
                            <input type="text" value="${pager.pageSize}" class="pag_inputs" readonly>
                            <span class="fysd_sp2">
                                <a href="${context.contextPath}/sms/toSendSMSList?<#if request.mobile>mobile=${request.mobile}&</#if><#if request.channelName>channelName=${request.channelName}&</#if><#if request.status>status=${request.status}&</#if><#if request.startDate>startDate=${request.startDate}&</#if><#if request.endDate>endDate=${request.endDate}&</#if>pageSize=10" <#if pager.pageSize == 10>class="pagon"</#if>>10</a>
                                <a href="${context.contextPath}/sms/toSendSMSList?<#if request.mobile>mobile=${request.mobile}&</#if><#if request.channelName>channelName=${request.channelName}&</#if><#if request.status>status=${request.status}&</#if><#if request.startDate>startDate=${request.startDate}&</#if><#if request.endDate>endDate=${request.endDate}&</#if>pageSize=20" <#if pager.pageSize == 20>class="pagon"</#if>>20</a>
                                <a href="${context.contextPath}/sms/toSendSMSList?<#if request.mobile>mobile=${request.mobile}&</#if><#if request.channelName>channelName=${request.channelName}&</#if><#if request.status>status=${request.status}&</#if><#if request.startDate>startDate=${request.startDate}&</#if><#if request.endDate>endDate=${request.endDate}&</#if>pageSize=30" <#if pager.pageSize == 30>class="pagon"</#if>>30</a>
                                <a href="${context.contextPath}/sms/toSendSMSList?<#if request.mobile>mobile=${request.mobile}&</#if><#if request.channelName>channelName=${request.channelName}&</#if><#if request.status>status=${request.status}&</#if><#if request.startDate>startDate=${request.startDate}&</#if><#if request.endDate>endDate=${request.endDate}&</#if>pageSize=40" <#if pager.pageSize == 40>class="pagon"</#if>>40</a>
                                <a href="${context.contextPath}/sms/toSendSMSList?<#if request.mobile>mobile=${request.mobile}&</#if><#if request.channelName>channelName=${request.channelName}&</#if><#if request.status>status=${request.status}&</#if><#if request.startDate>startDate=${request.startDate}&</#if><#if request.endDate>endDate=${request.endDate}&</#if>pageSize=50" <#if pager.pageSize == 50>class="pagon"</#if>>50</a>
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
