<#import "/macro.ftl" as m >
<@m.page_header title="新建/编辑模版" selected='setting' subselected="sendTo"  css="sendTo" js="" requirejs='editTemplate' />


<!--右边内容 start-->
<div class="main-detail">
    <div class="main-warp">
        <div class="main-title">
            <h2 class="fl"><#if (template.id)! == ''>添加<#else>编辑</#if>模板</h2>
            <a href="${context.contextPath}/sms/toTemplateList" class="title-btn back-title-btn fr">
                <i>返回</i>
            </a>
        </div>
        <div></div>
        <!--  右侧自适应内容块主体 start  -->
            <div class="detail-wrap">
                <div class="form-body">
                    <form id="updateTemplateForm" class="form-horizontal" role="form">
                    <table>
                    <div class="form-group">
                        <label for="mobile" class="col-sm-2 control-label"><em class="required">*</em>本地模版编号</label>
                        <div class="col-sm-10">
                            <input type="hidden" class="templateId" value="${(template.id)!}">
                            <input type="text" class="form-control templateCode" value="${template.templateCode}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="mobile" class="col-sm-2 control-label">阿里模板编号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control aliyunCode" value="${template.aliyunCode}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="content" class="col-sm-2 control-label"><em class="required">*</em>模版内容</label>
                        <div class="col-sm-10">
                            <textarea  class="form-control template" rows="7">${template.template}</textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button id="updateTemplate" type="button" class="btn btn-primary">确认</button>
                            <button id="reset" type="reset" class="btn">重置</button>
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
    function checkOnly(){
        var templateCode = $('#templateCode').val();
        if (templateCode == ''){
            PopTipsShow("模板编码 必须项..."); return;
        }
        console.info(templateCode);
        $.ajax({
            url:'${context.contextPath}/sms/verifyTemplateCode/'+templateCode,
            success: function(flag){
                if(flag == true){
                    PopTipsShow('模版编号已经存在...',300,500);
                    $('#templateCode').val('');
                }
            }
        });
    }
</script>
