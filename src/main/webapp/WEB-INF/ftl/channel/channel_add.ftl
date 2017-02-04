<#import "/macro.ftl" as m >
<@m.page_header title="新建通道 " selected='setting' subselected="channel"  css="" requirejs="channelInput" />
    <!--右边内容 start-->
    <div class="main-detail">
        <div class="main-warp">
            <div class="main-title">
                <h2 class="fl">新建通道</h2>
                <a href="${context.contextPath}/channel/toChannelList" class="title-btn back-title-btn fr">
                    <i>返回</i>
                </a>
            </div>
            <!--  右侧自适应内容块主体 start  -->
            <div class="detail-wrap">
                <div class="form-body">
                    <form id="newChannel" class="form-horizontal" role="form">
                        <table>
                            <div class="form-group">
                                <label for="channelName" class="col-sm-2 control-label">通道名称</label>
                                <div class="col-sm-10">
                                    <input type="text"  class="form-control channelName">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="description" class="col-sm-2 control-label">描述（备注）</label>
                                <div class="col-sm-10">
                                    <textarea  class="form-control description" rows="3"></textarea>
                                </div>
                            </div>


                            <div class="form-group">
                                <label for="type" class="col-sm-2 control-label">通道类型</label>
                                <div class="col-sm-10">
                                    <div class="radio-wrap type">
                                        <#list typeList as type>
                                            <span class="option custom-radio">
                                                <input type="radio" name="type" id="${type}" class="select-channelType" value="${type}" checked="checked"/>
                                                <label for="${type}"><span>${type}</span></label>
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
                                    <button type="button" class="btn btn-primary addChannel">确定</button>
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
