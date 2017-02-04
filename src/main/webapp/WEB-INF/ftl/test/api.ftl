<!DOCTYPE html>
<head>
    <title>测试</title>
</head>
<body>
<input type="hidden" id="path" value="${context.contextPath}">
<input type="hidden" id="webDomain" value="${webDomain}"/>
<div class="Content">
    <div style="text-align:center; padding:10px 0;font-size:30px;">OpenAPI接口测试页</div>
    <hr/>
    <div class="ConLeft Box">
        <div class="ConThree">请求区域：</div>
        <p>接口：</p>
        <p>
            <select id="interface">
                <option value=''>---</option>
                <#list classes as c>
                    <option value='${c}'>${c}</option>
                </#list>
            </select>
        </p>
        <p>方法：</p>
        <p>
            <select id="method">
                <option value=''>---</option>
            </select>
        </p>
        <p>参数列表：</p>
        <input type="hidden" class="hparam" id="typeName" value=""/>
        <input type="hidden" class="hparam" id="requiredNames" value=""/>
        <input type="hidden" class="hparam" id="path" name="path" value=""/>
        <form>
            <input type="hidden" id="clientId" name="clientId" value="1">
            <input type="hidden" class="hparam" id="timestamp" name="timestamp" value=""/>
            <div class="contentDiv">
                <table class="contentTable">
                    <thead>
                        <tr><th>字段名</th><th>值</th></tr>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>
         </form>
    </div>
    <div class="ConRight Box">
        <div class="ConThree">结果区域：</div>
        <p>请求数据：</p>
        <textarea name="" cols="" rows="" class="paramsText"></textarea>
        <p>返回结果：</p>
        <textarea name="" cols="" rows="" class="resultText"></textarea>
    </div>
    <div class="ConTwo Box"><input id="btnSubmit" type="button"  value="测试接口"/></div>
</div>
</body>
<script src="${context.contextPath}/js/lib/jquery-1.8.3.min.js" charset="utf-8"></script>
<script src="${context.contextPath}/js/jquery.form.js" charset="utf-8"></script>
<script src="${context.contextPath}/js/api.js" charset="utf-8"></script>
<script language="javascript">
    var path = document.getElementById("path").value;
</script>
<link rel="stylesheet" href="${context.contextPath}/css/api.css" type="text/css">
</html>