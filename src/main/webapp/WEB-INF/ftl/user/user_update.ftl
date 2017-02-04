<#import "/macro.ftl" as m >
<@m.page_header title="编辑用户 " selected='' subselected=""  css="msgList" js="" requirejs="userModify"/>
    <!--右边内容 start-->
    <div class="main-detail">
        <div class="main-warp">
            <div class="main-title">
                <h2 class="fl">编辑用户</h2>
                <a href="${context.contextPath}/user/toUserList" class="title-btn back-title-btn fr">
                    <i>返回</i>
                </a>
            </div>
            <!--  右侧自适应内容块主体 start  -->
            <div class="detail-wrap">
                <div class="form-body">
                    <form id="updateUser" class="form-horizontal" role="form"  action="${context.contextPath}/user/save" method="post">
                        <table>
                            <div class="form-group">
                                <label for="userName" class="col-sm-2 control-label">用户名</label>
                                <div class="col-sm-10">
                                    <input type="hidden" class="id" value="${user.id}">
                                    <input type="text" class="form-control userName" value="${user.userName}" >
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="contact" class="col-sm-2 control-label">联系人</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control contact" value="${user.contact}"  >
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="mobile" class="col-sm-2 control-label">联系电话</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control mobile" value="${user.mobile}" >
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="messageCallbackUrl" class="col-sm-2 control-label">回调地址</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control messageCallbackUrl" value="${user.messageCallbackUrl}"  >
                                </div>
                            </div>


                            <div class="form-group">
                                <label for="roles" class="col-sm-2 control-label">角色选择</label>
                                <div class="col-sm-10">
                                    <div class="radio-wrap roles">
                                        <#list roles as role>
                                            <span class="option custom-radio">
                                                <input type="radio" name="roles" id="role-${role_index}" class="select-role" value="${role.name}" <#if role.name == '${user.roles}'> checked="true"</#if>/>
                                                <label for="role-${role_index}"><span>${role.description}</span></label>
                                            </span>
                                        </#list>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="enable" class="col-sm-2 control-label">是否启用</label>
                                <div class="col-sm-10">
                                    <div class="radio-wrap enable">
                                        <span class="option custom-radio">
                                            <input type="radio" name="enable" id="state-a" class="select-state" value="1" <#if user.enable = '1'> checked="true"</#if>/>
                                            <label for="state-a"><span>启用</span></label>
                                        </span>
                                        <span class="option custom-radio">
                                            <input type="radio" name="enable" id="state-b" class="select-state" value="0" <#if user.enable = '0'> checked="true"</#if>/>
                                            <label for="state-b"><span>停用</span></label>
                                        </span>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="content" class="col-sm-2 control-label">关联通道</label>
                                <div class="col-sm-10 ">
                                    <div class="checkbox-wrap channels">
                                        <#if channels?size gt 0>
                                            <#list channels as channel>
                                                <span class="option custom-checkbox">
                                                    <input id="${channel.id}" type="checkbox" class="select-channel" value="${channel.id}" <#if channel.selected == true>checked="true"</#if> />
                                                        <label for="${channel.id}"><span>${channel.channelName}</span></label>
                                                </span>
                                            </#list>
                                        </#if>
                                    </div>
                                </div>
                            </div>


                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="button" class="btn btn-primary updateUser">确定</button>
                                </div>
                            </div>
                        </table>
                    </form>

                </div>
            </div>
    </div>
</div>
<div class="error_msg"></div>