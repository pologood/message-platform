<#import "/macro.ftl" as m >
<@m.page_header title="用户列表 " selected='account' subselected="list"  css="msgList" requirejs="userList"/>
    <!--右边内容 start-->
    <div class="main-detail">
        <div class="main-warp">
            <div class="main-title">
                <h2 class="fl">用户列表</h2>
                <@shiro.hasRole name="admin">
                    <a href="${context.contextPath}/user/add" class="title-btn add-title-btn fr">
                        <i>新增用户</i>
                    </a>
                </@shiro.hasRole>
                <button type="button" class="show-sel fr" data-toggle="collapse" data-target="#searchUser">
                    <i>展开搜索</i>
                </button>
            </div>
            <!--  右侧自适应内容块主体 start  -->
            <!--消息筛选  start-->
            <div id="searchUser" class="collapse">
                <div class="panel panel-default">
                    <div class="panel-body"  style="padding: 10px 10px 10px; border:1px; width:850px; height:60px;">
                        <form class="bs-example bs-example-form" role="form" action="${context.contextPath}/user/toUserList" method="post">
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
                                            <span class="input-group-addon" style="font-size:10px">用户状态</span>
                                            <select name="enable" class="styled-select" style="font-size:10px">
                                                <option value="">全部状态</option>
                                                <option value="1">启用</option>
                                                <option value="0">停用</option>
                                            </select>
                                             <span class="input-group-btn">
                                                <button id="searchButton" class="btn btn-default" type="button" ">搜索!</button>
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
                    <th>用户名</th>
                    <th>角色</th>
                    <th>余额</th>
                    <th>联系方式</th>
                    <th>关联通道</th>
                    <th>回调地址</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody style="font-size:11px">
                <#list pager.list as user>
                <tr <#if user_index % 2 != 0>class="success"</#if> data-type="${user.id}">
                    <td>${user.userName}</td>
                    <td>${user.roles}</td>
                    <td>${user.balance}</td>
                    <td>${user.info}</td>
                    <td>${user.channels}</td>
                    <td>${user.callbackUrl}</td>
                    <td><#if user.enable == true>已启用<#else>已停用</#if></td>
                    <td>
                        <@shiro.hasRole name="admin">
                            <a href="javascript:void(0)" class="pop pop_recharge" data-toggle='modal' data-target="#user-recharge" data-id="${user.id}">充值</a>
                            <a href="${context.contextPath}/user/update?id=${(user.id)!}" class="pop pop_modify">编辑</a>
                            <a href="javascript:void(0)" class="resetPwd"    data-id="${user.id}">重置密码</a>
                            <a href="javascript:void(0)" class="user_delete" data-id="${user.id}">删除</a>
                        </@shiro.hasRole>
                        <@shiro.hasRole name="general">
                            <a href="javascript:void(0)" class="pop update_callback" data-toggle='modal' data-target="#user_callback" data-id="${user.messageCallbackUrl}">回调地址</a>
                        </@shiro.hasRole>
                    </td>
                </tr>
                </#list>
                </tbody>
            </table>
            <div class="paging" data-all="${pager.pageCount}" data-url="${context.contextPath}/user/toUserList?<#if request.userName>userName=${request.userName}&</#if><#if request.enable>enable=${request.enable}&</#if>" data-now="${pager.currentPage}">
                <p>
                </p>
                <div class="fyds">
                    <em>每页显示</em>
                        <span class="fysd_sp1">
                            <input type="text" value="${pager.pageSize}" class="pag_inputs" >
                            <span class="fysd_sp2">
                                <a href="${context.contextPath}/user/toUserList?<#if request.userName>userName=${request.userName}&</#if><#if request.enable>enable=${request.enable}&</#if>pageSize=10" <#if pager.pageSize == 10>class="pagon"</#if>>10</a>
                                <a href="${context.contextPath}/user/toUserList?<#if request.userName>userName=${request.userName}&</#if><#if request.enable>enable=${request.enable}&</#if>pageSize=20" <#if pager.pageSize == 20>class="pagon"</#if>>20</a>
                                <a href="${context.contextPath}/user/toUserList?<#if request.userName>userName=${request.userName}&</#if><#if request.enable>enable=${request.enable}&</#if>pageSize=30" <#if pager.pageSize == 30>class="pagon"</#if>>30</a>
                                <a href="${context.contextPath}/user/toUserList?<#if request.userName>userName=${request.userName}&</#if><#if request.enable>enable=${request.enable}&</#if>pageSize=40" <#if pager.pageSize == 40>class="pagon"</#if>>40</a>
                                <a href="${context.contextPath}/user/toUserList?<#if request.userName>userName=${request.userName}&</#if><#if request.enable>enable=${request.enable}&</#if>pageSize=50" <#if pager.pageSize == 50>class="pagon"</#if>>50</a>
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

<!--充值 start-->
<div id="user-recharge" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <#---->
            <div class="modal-header" style="font-size:10px">
                <h5 class="modal-title" id="exampleModalLabel">用户充值</h5>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <div class="input-group">
                            <input id="userId" name="userId" type="hidden"/>
                            <input id="rechargeNum" name="rechargeNum" type="text" class="form-control" placeholder="请输入正整数...[1~10W]"
                                   aria-describedby="basic-addon2" onkeyup="value=this.value.replace(/\D+/g,'')">
                            <span class="input-group-addon" id="basic-addon2"  style="font-size:10px">单位：元[￥]</span>
                        </div>
                        <div class="error_msg_recharge"></div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="user_recharge">确定</button>
            </div>
            <#---->
        </div>
    </div>
</div>
<!--充值 end-->



<!--充值 start-->
<div id="user_callback" class="modal fade bs-example-modal-middle" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-middle">
        <div class="modal-content">
        <#---->
            <div class="modal-header" style="font-size:10px">
                <h5 class="modal-title" id="exampleModalLabel">回调地址</h5>
            </div>
            <div class="modal-body">
                <input type="text" class="form-control callbackUrl" placeholder="http://...">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary updateCallback" >确定</button>
            </div>
        <#---->
        </div>
    </div>
</div>
<!--充值 end-->
