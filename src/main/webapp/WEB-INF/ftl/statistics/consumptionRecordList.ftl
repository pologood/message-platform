<#import "/macro.ftl" as m >
<@m.page_header title="消费记录" selected='count' subselected="consumption"  css="msgList" js="" requirejs='msgList' />

    <!--右边内容 start-->
    <div class="main-detail">
        <div class="main-warp">
            <div class="main-title">
                <h2 class="fl">消费记录</h2>
                    <form id="exportDeposit" action="${context.contextPath}/statistics/deposit/consumptionDownload" method="post">
                        <input  name="userName" type="hidden" value="${request.userName}"/>
                        <input  name="startDate" type="hidden" value="${request.startDate}"/>
                        <input  name="endDate" type="hidden" value="${request.endDate}"/>
                        <button type="submit" class="show-sel fr"> <i>记录导出</i> </button>

                    </form>
                <button type="button" class="show-sel fr" data-toggle="collapse" data-target="#searchConsumption"> <i>展开搜索</i> </button>
            </div>
            <!--  右侧自适应内容块主体 start  -->
            <!--消息筛选  start-->
            <div id="searchConsumption" class="collapse">
                <div class="panel panel-default">
                    <div class="panel-body"  style="padding: 10px 10px 10px; border:1px; width:850px; height:60px;">
                        <form class="bs-example bs-example-form" role="form" action="" method="post">
                            <table>
                                <tr>
                                    <td>
                                        <div class="input-group input-group-sm" style="padding: 5px 5px 5px;">
                                        <@shiro.hasAnyRoles name="admin,finance">
                                            <span class="input-group-addon" style="font-size:10px">用户名</span>
                                            <input id="userName" name="userName" type="text" class="form-control" placeholder="user">
                                        </@shiro.hasAnyRoles>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="input-group input-group-sm" style="padding: 5px 5px 5px;">
                                            <span class="input-group-addon" style="font-size:10px">消费时间</span>
                                            <input type="text" class="form-control" id="startDate" name="startDate" placeholder="startDate" readonly  style="font-size:10px"/>
                                            <span class="input-group-addon" style="font-size:10px">-</span>
                                            <input type="text" class="form-control" id="endDate" name="endDate" placeholder="endDate" readonly  style="font-size:10px"/>
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
            <!--通道列表  start-->
            <table id="receiveSMSTable" class="table table-striped">
                <thead>
                <tr>
                <#--<th>编号</th>-->
                    <th>用户名</th>
                    <th>发送扣款</th>
                    <th>失败退款</th>
                    <th>时间</th>
                </tr>
                </thead>
                <tbody style="font-size:11px">
                <#list pager.list as record>
                <tr <#if record_index % 2 != 0>class="success"</#if> data-type="${record.id}">
                <#--<td>${message_index + 1}</td>-->
                    <td>${record.userName}</td>
                    <td>${record.cost}</td>
                    <td>
                        <#if record.refund == "0.00">
                            --
                        <#else>
                        ${record.refund}
                        </#if>
                    </td>
                    <td>${record.createDate}</td>
                </tr>
                </#list>
                </tbody>
            </table>
            <div class="paging" data-all="${pager.pageCount}" data-url="${context.contextPath}/statistics/toConsumptionRecordList?<#if request.endDate>endDate=${request.endDate}&</#if><#if request.startDate>startDate=${request.startDate}&</#if><#if request.userName>userName=${request.userName}&</#if><#if pager.pageSize>pageSize=${pager.pageSize}&</#if>" data-now="${pager.currentPage}">
                <p>
                </p>
                <div class="fyds">
                    <em>每页显示</em>
                        <span class="fysd_sp1">
                            <input type="text" value="${pager.pageSize}" class="pag_inputs" readonly>
                            <span class="fysd_sp2">
                                <a href="${context.contextPath}/statistics/toConsumptionRecordList?<#if request.endDate>endDate=${request.endDate}&</#if><#if request.startDate>startDate=${request.startDate}&</#if><#if request.userName>userName=${request.userName}&</#if>pageSize=10" <#if pager.pageSize == 10>class="pagon"</#if>>10</a>
                                <a href="${context.contextPath}/statistics/toConsumptionRecordList?<#if request.endDate>endDate=${request.endDate}&</#if><#if request.startDate>startDate=${request.startDate}&</#if><#if request.userName>userName=${request.userName}&</#if>pageSize=20" <#if pager.pageSize == 20>class="pagon"</#if>>20</a>
                                <a href="${context.contextPath}/statistics/toConsumptionRecordList?<#if request.endDate>endDate=${request.endDate}&</#if><#if request.startDate>startDate=${request.startDate}&</#if><#if request.userName>userName=${request.userName}&</#if>pageSize=30" <#if pager.pageSize == 30>class="pagon"</#if>>30</a>
                                <a href="${context.contextPath}/statistics/toConsumptionRecordList?<#if request.endDate>endDate=${request.endDate}&</#if><#if request.startDate>startDate=${request.startDate}&</#if><#if request.userName>userName=${request.userName}&</#if>pageSize=40" <#if pager.pageSize == 40>class="pagon"</#if>>40</a>
                                <a href="${context.contextPath}/statistics/toConsumptionRecordList?<#if request.endDate>endDate=${request.endDate}&</#if><#if request.startDate>startDate=${request.startDate}&</#if><#if request.userName>userName=${request.userName}&</#if>pageSize=50" <#if pager.pageSize == 50>class="pagon"</#if>>50</a>
                            </span>
                        </span>
                    <em>条</em>
                </div>
            </div>




            <#--<div class="msgListBg">
                <div class="list_tit list_bg">
                    <span class="list_w18">编号</span>
                    <span class="list_w18">用户名</span>
                    <span class="list_w18">发送扣款</span>
                    <span class="list_w18">失败退款</span>
                    <span class="list_w18">时间</span>
                </div>
                <ul class="list_Ul">
                    <#list pager.list as record>
                        <li <#if record_index %2 == 0>class="tr_bg"</#if> data-type="${record.id}">
                            <span class="list_w18">${record_index + 1}</span>
                            <span class="list_w18">${record.userName}</span>
                            <span class="list_w18">${record.cost}</span>
                            <span class="list_w18">
                                <#if record.refund == "0.00">
                                    --
                                <#else>
                                    ${record.refund}
                                </#if>
                            </span>
                            <span class="list_w18">${record.createDate}</span>
                        </li>
                    </#list>
                </ul>
                <div class="paging" data-all="${pager.pageCount}" data-url="${context.contextPath}/statistics/toConsumptionRecordList?<#if pager.pageSize>pageSize=${pager.pageSize}&</#if><#if request.showSearch>showSearch=${request.showSearch}&</#if>" data-now="${pager.currentPage}">
                    <p>
                    </p>
                    <div class="fyds">
                        <em>每页显示</em>
                        <span class="fysd_sp1">
                            <input type="text" value="${pager.pageSize}" class="pag_inputs" readonly>
                            <span class="fysd_sp2">
                                <a href="${context.contextPath}/statistics/toConsumptionRecordList?pageSize=10" <#if pager.pageSize == 10>class="pagon"</#if>>10</a>
                                <a href="${context.contextPath}/statistics/toConsumptionRecordList?pageSize=20" <#if pager.pageSize == 20>class="pagon"</#if>>20</a>
                                <a href="${context.contextPath}/statistics/toConsumptionRecordList?pageSize=30" <#if pager.pageSize == 30>class="pagon"</#if>>30</a>
                                <a href="${context.contextPath}/statistics/toConsumptionRecordList?pageSize=40" <#if pager.pageSize == 40>class="pagon"</#if>>40</a>
                                <a href="${context.contextPath}/statistics/toConsumptionRecordList?pageSize=50" <#if pager.pageSize == 50>class="pagon"</#if>>50</a>
                            </span>
                        </span>
                        <em>条</em>
                    </div>
                </div>
            </div>-->
            <!--通道列表  end-->
            <!--  右侧自适应内容块主体 end  -->
        </div>
    </div>
    <!--右边内容 end-->
</div>
<!--主体部分 end-->
