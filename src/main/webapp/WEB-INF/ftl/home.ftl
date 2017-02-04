<#import "/macro.ftl" as m >
<@m.page_header title="用户列表 " selected='home' subselected=""  css="msgList" requirejs="userList"/>
<script src="${context.contextPath}/js/report/highcharts.js"></script>
<script src="${context.contextPath}/js/report/highcharts-3d.js"></script>
<script src="${context.contextPath}/js/report/exporting.js"></script>
<script src="${context.contextPath}/js/report/sand-signika.js"></script>
<#--<script src="${context.contextPath}/js/report/dark-unica.js"></script>-->

<script type="text/javascript">
    $(function () {
        $('#container-sms').highcharts({
            chart: {
                type: 'column',
                margin: 75,
                options3d: {
                    enabled: true,
                    alpha: 10,
                    beta: 25,
                    depth: 70
                }
            },
            title: { text: 'sms日发送量'},
            subtitle: {text: '10 days'},
            plotOptions: {column: {depth: 25}},
            xAxis: {categories: ${smsDate}},
            yAxis: {title: {text: 'sms数目'}},
            series: [{name: 'sms',data: ${smsCount}}]
        });


        // Build the chart
        $('#container-pie').highcharts({
            chart: {
                type: 'pie',
                options3d: {
                    enabled: true,
                    alpha: 45,
                    beta: 0
                }
            },
            title: { text: '网关使用率'},
            tooltip: {pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'},
            plotOptions: {
                pie: {allowPointSelect: true,
                    cursor: 'pointer',
                    depth: 35,
                    dataLabels: {
                        enabled: true,
                        format: '{point.name}'
                    }
                }
            },
            series: [{
                type: 'pie',
                name: 'Browser share',
                data: ${usedDate}
            }]
        });

        $('#container-sta').highcharts({
            chart: {
                type: 'pie',
                options3d: {
                    enabled: true,
                    alpha: 45,
                    beta: 0
                }
            },
            title: { text: '短信状态图'},
            tooltip: {pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'},
            plotOptions: {
                pie: {allowPointSelect: true,
                    cursor: 'pointer',
                    depth: 35,
                    dataLabels: {
                        enabled: true,
                        format: '{point.name}'
                    }
                }
            },
            series: [{
                type: 'pie',
                name: 'Browser share',
                data: ${statusData}
            }]
        });


        $('#container-app').highcharts({
            chart: {
                type: 'column',
                margin: 75,
                options3d: {
                    enabled: true,
                    alpha: 10,
                    beta: 25,
                    depth: 70
                }
            },
            title: { text: 'app日推送量'},
            subtitle: {text: '10 days'},
            plotOptions: {column: {depth: 25}},
            xAxis: {categories: ${appDate}},
            yAxis: {title: {text: 'app数目'}},
            series: [{name: 'app',data: ${appCount}}]
        });
    });
</script>
<!--右边内容 start-->
<div class="main-detail">
    <div class="main-warp">
        <div class="main-title">
            <#--<input type="text" value="${usedDate}">-->
            <#--<h2 class="fl">首页</h2>-->
        </div>
        <!--  右侧自适应内容块主体 start  -->
        <div id="container-sms" style="height:250px;width: 48%;float:left;margin:5px"></div>
        <div id="container-app" style="height:250px;width: 48%;float:right;margin:5px"></div>
        <div id="container-pie" style="height:250px;width: 48%;float:left;margin:5px"></div>
        <div id="container-sta" style="height:250px;width: 48%;float:right;margin:5px"></div>
    </div>
</div>

