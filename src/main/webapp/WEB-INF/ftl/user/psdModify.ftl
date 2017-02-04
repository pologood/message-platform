<#import "/macro.ftl" as m >
<@m.page_header title="登录密码修改" selected='setting' subselected="password"  css="sendTo" js="" requirejs='psdModify' />
 <!--右边内容 start-->
    <div class="main-detail">
        <div class="main-warp">
            <div class="main-title">
                <h2>登陆密码修改</h2>
            </div>
            <!--  右侧自适应内容块主体 start  -->
            <div class="detail-wrap">
                <div class="form-body">
                    <form class="form-horizontal" role="form">
                        <table>
                            <div class="form-group">
                                <label for="mobile" class="col-sm-2 control-label"><em class="required">*</em>当前密码</label>
                                <div class="col-sm-10">
                                    <input type="password" name="oldPassword" id="oldPassword" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="content" class="col-sm-2 control-label"><em class="required">*</em>新密码</label>
                                <div class="col-sm-10">
                                    <input type="password" name="newPassword" id="newPassword" class="form-control" onblur="verfiypwd(this)" placeholder="密码长度为6-32位字符,需同时包含数字和字母">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="channel" class="col-sm-2 control-label"><em class="required">*</em>确认新密码</label>
                                <div class="col-sm-10">
                                    <input type="password" id="confirmPassword" class="form-control" onblur="confirmPwd(this)" placeholder="密码长度为6-32位字符,需同时包含数字和字母">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button id="modifyPassword" type="button" class="btn btn-primary">确认</button>
                                </div>
                            </div>
                        </table>
                    </form>

                </div>
            </div>
            <!--  右侧自适应内容块主体 end  -->
        </div>

    </div>
    <!--右边内容 end-->
</div>
<!--主体部分 end-->
<div class="error_msg"></div>

<script>

    function verfiypwd(){
        var isPwd = /^(?=.*?[a-zA-Z])(?=.*?[0-6])[!"#$%&'()*+,\-./:;<=>?@\[\\\]^_`{|}~A-Za-z0-9]{6,32}$/;//密码验证正则
        var newPwd =  $('#newPassword').val();

        if (newPwd.match(isPwd) == null){
            PopTipsShow("密码格式不符合...");
            $('#newPassword').val('');
            return;
        }
    }

    function confirmPwd(){
        var newPwd =  $('#newPassword').val();
        var newPwd2 = $('#confirmPassword').val();
        if (newPwd != newPwd2){
            PopTipsShow("确认密码输入不一致...");
            $('#confirmPassword').val('');
            return;
        }
    }

</script>
</body>
</html>
