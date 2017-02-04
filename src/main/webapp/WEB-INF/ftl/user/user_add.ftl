<#import "/macro.ftl" as m >
<@m.page_header title="新建用户 " selected='' subselected=""  css="msgList" js="" requirejs="userModify"/>
    <!--右边内容 start-->
    <div class="main-detail">
        <div class="main-warp">
            <div class="main-title">
                <h2 class="fl">新建用户</h2>
                <a href="${context.contextPath}/user/toUserList" class="title-btn back-title-btn fr">
                    <i>返回</i>
                </a>
            </div>
            <!--  右侧自适应内容块主体 start  -->
            <div class="detail-wrap">
                <div class="form-body">
                    <form id="newUser" class="form-horizontal" role="form"  action="${context.contextPath}/user/save" method="post">
                        <table>
                            <div class="form-group">
                                <label for="userName" class="col-sm-2 control-label"><em class="required">*</em>用户名</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control userName"  >
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="contact" class="col-sm-2 control-label"><em class="required">*</em>联系人</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control contact"  >
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="mobile" class="col-sm-2 control-label"><em class="required">*</em>联系电话</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control mobile"  >
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="messageCallbackUrl" class="col-sm-2 control-label"><em class="required">*</em>回调地址</label>
                                <div class="col-sm-10">
                                    <input type="text"  class="form-control messageCallbackUrl"  >
                                </div>
                            </div>


                            <div class="form-group">
                                <label for="roles" class="col-sm-2 control-label"><em class="required">*</em>角色选择</label>
                                <div class="col-sm-10">
                                    <div class="radio-wrap roles">
                                        <#list roles as role>
                                            <span class="option custom-radio">
                                                <input type="radio" name="roles" id="role-${role_index}" class="select-role" value="${role.name}" <#if role.name == 'general'> checked="true"</#if>/>
                                                <label for="role-${role_index}"><span>${role.description}</span></label>
                                            </span>
                                        </#list>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="enable" class="col-sm-2 control-label"><em class="required">*</em>是否启用</label>
                                <div class="col-sm-10">
                                    <div class="radio-wrap enable">
                                        <span class="option custom-radio">
                                            <input type="radio" name="enable" id="state-a" class="select-state" value="1" checked="checked"/>
                                            <label for="state-a"><span>启用</span></label>
                                        </span>
                                        <span class="option custom-radio">
                                            <input type="radio" name="enable" id="state-b" class="select-state" value="0"/>
                                            <label for="state-b"><span>停用</span></label>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="button" class="btn btn-primary addUser">确定</button>
                                </div>
                            </div>
                        </table>
                    </form>

                </div>
            </div>
    </div>
</div>
<div class="error_msg"></div>
