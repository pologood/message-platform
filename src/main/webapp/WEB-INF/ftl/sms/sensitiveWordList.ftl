<#import "/macro.ftl" as m >
<@m.page_header title="模版管理 " selected='setting' subselected="sensitiveWord"  css="msgList" js="" requirejs="sensitiveWordList"/>
<#--<script src="${context.contextPath}/js/require.js" defer async data-main="${context.contextPath}/js/sensitiveWordList"></script>-->
<!--右边内容 start-->
<div class="main-detail">
    <div class="main-warp">
        <div class="main-title">
            <h2 class="fl">敏感字列表</h2>
            <@shiro.hasRole name="admin">
            <button type="button" class="show-sel fr" data-toggle='modal' data-target="#addWord"><i>新增敏感字</i></button>
            </@shiro.hasRole>
            <button type="button" class="show-sel fr" data-toggle="collapse" data-target="#searchWord"><i>展开搜索</i></button>
        </div>
        <!--  右侧自适应内容块主体 start  -->
        <!--消息筛选  start-->
        <div id="searchWord" class="collapse">
            <div class="panel panel-default">
                <div class="panel-body"  style="padding: 10px 10px 10px; border:1px; width:850px; height:60px;">
                    <form class="bs-example bs-example-form" role="form" action="${context.contextPath}/sms/toSensitiveWordList" method="post">
                        <table>
                            <tr>
                                <td>
                                    <div class="input-group input-group-sm" style="padding: 5px 5px 5px;">
                                        <span class="input-group-addon" style="font-size:10px">敏感字</span>
                                        <input id="word" name="word" type="text" class="form-control" placeholder="word">
                                        <span class="input-group-btn">
                                            <button id="searchButton" class="btn btn-default" type="submit" ">搜索!</button>
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
        <table class="table table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th>敏感字</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody style="font-size:11px">
            <#list pager.list as word>
            <tr <#if word_index % 2 != 0>class="success"</#if> data-type="${word.id}">
                <td>${word.id}</td>
                <td>${word.word}</td>
                <td>
                <@shiro.hasRole name="admin">
                    <a href="javascript:void(0)" class="sensitiveWord_delete" data-id="${word.id}">删除</a>
                </@shiro.hasRole>
                <@shiro.hasAnyRoles name="general,finance">
                    无权限...
                </@shiro.hasAnyRoles>
                </td>
            </tr>
            </#list>
            </tbody>
        </table>
        <div class="paging" data-all="${pager.pageCount}" data-url="${context.contextPath}/sms/toSensitiveWordList?<#if pager.pageSize>pageSize=${pager.pageSize}&</#if><#if request.word>word=${request.word}&</#if>" data-now="${pager.currentPage}">
            <p>
            </p>
            <div class="fyds">
                <em>每页显示</em>
                        <span class="fysd_sp1">
                            <input type="text" value="${pager.pageSize}" class="pag_inputs" >
                            <span class="fysd_sp2">
                                <a href="${context.contextPath}/sms/toSensitiveWordList?<#if request.word>word=${request.word}&</#if>pageSize=10" <#if pager.pageSize == 10>class="pagon"</#if>>10</a>
                                <a href="${context.contextPath}/sms/toSensitiveWordList?<#if request.word>word=${request.word}&</#if>pageSize=20" <#if pager.pageSize == 20>class="pagon"</#if>>20</a>
                                <a href="${context.contextPath}/sms/toSensitiveWordList?<#if request.word>word=${request.word}&</#if>pageSize=30" <#if pager.pageSize == 30>class="pagon"</#if>>30</a>
                                <a href="${context.contextPath}/sms/toSensitiveWordList?<#if request.word>word=${request.word}&</#if>pageSize=40" <#if pager.pageSize == 40>class="pagon"</#if>>40</a>
                                <a href="${context.contextPath}/sms/toSensitiveWordList?<#if request.word>word=${request.word}&</#if>pageSize=50" <#if pager.pageSize == 50>class="pagon"</#if>>50</a>
                            </span>
                        </span>
                <em>条</em>
            </div>
        </div>

        <!--通道列表  end-->
        <!--  右侧自适应内容块主体 end  -->
    </div>
</div>
<!--右边内容 end-->
</div>
<!--主体部分 end-->
<div class="mask"></div>
<div class="error_msg"></div>


<!--新增敏感字 start-->
<div id="addWord" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
        <#---->
            <div class="modal-header" style="font-size:10px">
                <h5 class="modal-title" id="exampleModalLabel">新增敏感字</h5>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <div class="input-group">
                            <input id="userId" name="userId" type="hidden"/>
                            <input type="text" class="form-control word_add" placeholder="word" aria-describedby="basic-addon2">
                            <span class="input-group-addon" id="basic-addon2"  style="font-size:10px">逗号隔开</span>
                        </div>
                        <div class="error_msg_recharge"></div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary addWord">确定</button>
            </div>
        <#---->
        </div>
    </div>
</div>
<!--充值 end-->