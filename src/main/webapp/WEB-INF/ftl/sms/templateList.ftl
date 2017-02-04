<#import "/macro.ftl" as m >
<@m.page_header title="模版管理 " selected='sms' subselected="template"  css="msgList" requirejs="templateList" />

<!--右边内容 start-->
<div class="main-detail">
    <div class="main-warp">
        <div class="main-title">
            <h2 class="fl">模板列表</h2>
            <a href="${context.contextPath}/sms/addTemplate" class="title-btn add-title-btn fr">
                <i>新增模板</i>
            </a>
            <button type="button" class="show-sel fr" data-toggle="collapse" data-target="#searchSMS">
                <i>展开搜索</i>
            </button>
        </div>
        <!--消息筛选  start-->
        <div id="searchSMS" class="collapse">
            <div class="panel panel-default">
                <div class="panel-body"  style="padding: 10px 10px 10px; border:1px; width:800px; height:60px;">
                    <form class="bs-example bs-example-form" role="form"  action="${context.contextPath}/sms/toTemplateList" method="post">
                        <table>
                            <tr>
                                <td>
                                    <div class="input-group input-group-sm" style="padding: 5px 5px 5px;">
                                        <span class="input-group-addon" style="font-size:10px">用户名</span>
                                        <input id="userName" name="userName" type="text" class="form-control" placeholder="userName">
                                    </div>
                                </td>
                                <td>
                                    <div class="input-group input-group-sm" style="padding: 5px 5px 5px;">
                                        <span class="input-group-addon" style="font-size:10px">本地编码</span>
                                        <input id="userName" name="templateCode" type="text" class="form-control" placeholder="templateCode">
                                    </div>
                                </td>
                                <td>
                                    <div class="input-group input-group-sm" style="padding: 5px 5px 5px;">
                                        <span class="input-group-addon" style="font-size:10px">阿里编码</span>
                                        <input id="userName" name="aliyunCode" type="text" class="form-control" placeholder="aliyunCode">
                                    </div>
                                </td>
                                <td>
                                    <div class="input-group input-group-sm" style="padding: 5px 5px 5px;">
                                        <span class="input-group-addon" style="font-size:10px">模版内容</span>
                                        <input id="template" name="template" type="text" class="form-control" placeholder="template">
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
        <!--  右侧自适应内容块主体 start  -->
        <!--消息列表  start-->
        <table id="receiveSMSTable" class="table table-striped">
            <thead>
            <tr>
                <th>编号</th>
                <th>内容</th>
                <th>本地编码</th>
                <th>阿里编码</th>
                <th>用户名
                    <em class="tipsEm"></em>
                    <div class="tips">公用模板是管理员所建，任何用户都可以使用</div>
                </th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody style="font-size:11px">
            <#list (pager.list)?? as template>
            <tr <#if template_index % 2 != 0>class="success"</#if> data-type="${template.id}">
                <td>${template.id}</td>
                <td data-toggle="tooltip" title="${template.template}">${template.simTemplate}</td>
                <td>${template.templateCode}</td>
                <td>${template.aliyunCode}</td>
                <td>${template.userName}</td>
                <td>
                     <@shiro.hasRole name="admin">
                         <a href="javascript:void(0)" class="editTemplate" data-id="${template.id}" >编辑</a>
                         <a href="javascript:void(0)" class="deleteTemplate" data-id="${template.id}">删除</a>
                     </@shiro.hasRole>
                    <@shiro.hasRole name="general">
                        <#if template.userName != '公用模板'>
                            <a href="javascript:void(0)" class="editTemplate" data-id="${template.id}" >编辑</a>
                            <a href="javascript:void(0)" class="deleteTemplate" data-id="${template.id}">删除</a>
                        </#if>
                    </@shiro.hasRole>
                </td>
            </tr>
            </#list>
            </tbody>
        </table>
        <div class="paging" data-all="${pager.pageCount}" data-url="${context.contextPath}/sms/toTemplateList?<#if pager.pageSize>pageSize=${pager.pageSize}&</#if><#if request.userName>userName=${request.userName}&</#if><#if request.template>template=${request.template}&</#if>" data-now="${pager.currentPage}">
            <p>
            </p>
            <div class="fyds">
                <em>每页显示</em>
                        <span class="fysd_sp1">
                            <input type="text" value="${pager.pageSize}" class="pag_inputs" readonly>
                            <span class="fysd_sp2">
                                <a href="${context.contextPath}/sms/toTemplateList?<#if request.userName>userName=${request.userName}&</#if><#if request.template>template=${request.template}&</#if>pageSize=10" <#if pager.pageSize == 10>class="pagon"</#if>>10</a>
                                <a href="${context.contextPath}/sms/toTemplateList?<#if request.userName>userName=${request.userName}&</#if><#if request.template>template=${request.template}&</#if>pageSize=20" <#if pager.pageSize == 20>class="pagon"</#if>>20</a>
                                <a href="${context.contextPath}/sms/toTemplateList?<#if request.userName>userName=${request.userName}&</#if><#if request.template>template=${request.template}&</#if>pageSize=30" <#if pager.pageSize == 30>class="pagon"</#if>>30</a>
                                <a href="${context.contextPath}/sms/toTemplateList?<#if request.userName>userName=${request.userName}&</#if><#if request.template>template=${request.template}&</#if>pageSize=40" <#if pager.pageSize == 40>class="pagon"</#if>>40</a>
                                <a href="${context.contextPath}/sms/toTemplateList?<#if request.userName>userName=${request.userName}&</#if><#if request.template>template=${request.template}&</#if>pageSize=50" <#if pager.pageSize == 50>class="pagon"</#if>>50</a>
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

<script>
    $(function () {
        //策略小提示
        $('.tipsEm').on('mouseover mouseleave', function () {
            $(this).siblings('.tips').toggle();
        });
    })
</script>