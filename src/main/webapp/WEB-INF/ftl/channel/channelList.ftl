<#import "/macro.ftl" as m >
<@m.page_header title="通道管理 " selected='setting' subselected="channel"  css="msgList" requirejs="channelList"/>
<!--右边内容 start-->
<div class="main-detail">
    <div class="main-warp">
        <div class="main-title">
            <h2 class="fl">通道管理</h2>
        <@shiro.hasRole name="admin">
            <a href="${context.contextPath}/channel/add" class="title-btn add-title-btn fr"><i>新增通道</i></a>
        </@shiro.hasRole>
        </div>
        <!--  右侧自适应内容块主体 start  -->
        <!--通道列表  start-->
        <table id="channelTable" class="table table-striped">
            <thead>
            <tr>
                <th>通道</th>
                <th>类型</th>
                <th>描述</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody style="font-size:11px">
            <#list pager.list as channel>
            <tr <#if channel_index % 2 != 0>class="success"</#if> data-type="${channel.id}">
                <td>${channel.channelName}</td>
                <td>${channel.type}</td>
                <td>${channel.description}</td>
                <td><#if channel.enable == true>启用<#else>停用</#if></td>
                <td>
                    <@shiro.hasRole name="admin">
                        <a href="${context.contextPath}/channel/update/${(channel.id)!}" class="channel_update">编辑</a>
                        <a href="javascript:void(0)" class="channel_delete" data-id="${channel.id}">删除</a>
                    </@shiro.hasRole>
                    <@shiro.hasAnyRoles name="general,finance">
                        无权限...
                    </@shiro.hasAnyRoles>
                </td>
            </tr>
            </#list>
            </tbody>
        </table>
        <div class="paging" data-all="${pager.pageCount}" data-url="${context.contextPath}/channel/toChannelList?" data-now="${pager.currentPage}">
            <p>
            </p>
            <div class="fyds">
                <em>每页显示</em>
                        <span class="fysd_sp1">
                            <input type="text" value="${pager.pageSize}" class="pag_inputs" >
                            <span class="fysd_sp2">
                                <a href="${context.contextPath}/channel/toChannelList?pageSize=10" <#if pager.pageSize == 10>class="pagon"</#if>>10</a>
                                <a href="${context.contextPath}/channel/toChannelList?pageSize=20" <#if pager.pageSize == 20>class="pagon"</#if>>20</a>
                                <a href="${context.contextPath}/channel/toChannelList?pageSize=30" <#if pager.pageSize == 30>class="pagon"</#if>>30</a>
                                <a href="${context.contextPath}/channel/toChannelList?pageSize=40" <#if pager.pageSize == 40>class="pagon"</#if>>40</a>
                                <a href="${context.contextPath}/channel/toChannelList?pageSize=50" <#if pager.pageSize == 50>class="pagon"</#if>>50</a>
                            </span>
                        </span>
                <em>条</em>
            </div>
        </div>
    </div>
</div>
<!--右边内容 end-->
</div>
<!--主体部分 end-->
<div class="mask"></div>
<div class="error_msg"></div>

<script>
$(function () {
    //策略小提示
    $('.tipsEm').on('mouseover mouseleave', function () {
    $(this).siblings('.tips').toggle();
    });
})
</script>