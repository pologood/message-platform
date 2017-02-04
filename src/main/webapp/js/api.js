$(function () {
    $(window).bind("beforeunload", function () {
        $('#interface').val('');
    });

    $('#interface').change(function () {
        trimData();
        $('#method').empty();

        var interface = $(this).val();
        if (interface.length > 0) {
            $.ajax({
                type: "GET",
                url: path + "/test/getMethodList",
                data: "interfaceName=" + interface,
                success: function (response) {
                    var data = response;
                    if (typeof(data) == "undefined") {
                        alert('获取方法列表失败');
                        return false;
                    }
                    $("#method").append("<option value=''>---</option>");
                    for (var key in data) {
                        $("#method").append("<option value='" + key + "'>" + data[key] + "</option>");
                    }
                }, error: function (data, e) {
                    alert('error:' + JSON.stringify(data));
                    alert(e);
                }
            });
        }
    });

    $('#method').on('change', function () {
        trimData();
        trimtable();
        var interface = $('#interface').val();
        var method = $('#method').val();
        if (method == '') {
            return false;
        }
        $.ajax({
            type: "GET",
            url: path + "/test/content",
            data: "interfaceName=" + interface + "&methodName=" + method,
            success: function (response) {
                var data = response;
                data = eval('(' + data + ')');
                if (typeof(data) == "undefined" || typeof(data.url) == "undefined") {
                    alert('获取参数列表失败');
                } else {
                    var url = data.url;
                    $('#path').val(url);
                    var typeName = data.typeName;
                    var requiredNames = data.requiredNames;
                    $('#typeName').val(typeName);
                    $('#requiredNames').val(requiredNames);
                    drawTable(data.fields);
                }
            }, error: function (data) {
            }
        });
    });

    $('#btnSubmit').on('click', function () {
        trimData();
        if ($('#method').val() == '') {
            alert('请先选择接口/方法');
            return false;
        }
        var domain = $('#path').val();
        //var requireds = $('#requiredNames').val().split(',')
        //var isReturn = false;
        //$(requireds).each(function(i,field){
        //    if(field==''){
        //        return true;
        //    }
        //    var value = $.trim($('#'+field).val());
        //    if(value==''){
        //        alert(field+" is required.");
        //        isReturn = true;
        //        return false;
        //    }
        //});
        //if(isReturn){
        //    return false;
        //}
        setStorage();
        var typeName = $('#typeName').val();
        var urls = $('#webDomain').val() + domain;
        $('#timestamp').val("1447921566686");
        //$('#timestamp').val(new Date().getTime());
        var params = $('form').serialize();
        if (typeName != 'GET') {
            params = {data: JSON.stringify($("form").serializeObject())};
        }

        /**获取签名 */
        $("form").ajaxSubmit({
            type: 'get', // 提交方式 get/post
            url: path + '/test/sign?path=' + domain,
            success: function (response) {
                $('.resultText').val(JSON.stringify(data));
                var data = response;
                if (!data) {
                    alert('生成签名失败');
                    return false;
                } else {
                    $('.paramsText').val(JSON.stringify($("form").serializeObject()));
                    if (typeName == 'GET') {
                        params = $("form").serialize();
                    } else {
                        //params = JSON.stringify($("form").serializeObject());
                        contentType : "application/x-www-form-urlencoded"
                    }
                    $("form").ajaxSubmit({
                        type: 'post', // 提交方式 get/post
                        url: urls + "?sign=" + data,
                        success: function (data) {
                            $('.resultText').val(JSON.stringify(data));
                        }, error: function (data) {
                            $('.resultText').val(JSON.stringify(data));
                        }
                    });
                }
            }
        });
    });
});

function trimtable() {
    $('.hparam').val('');
    $(".contentTable tr:not(:first)").empty();
}

function drawTable(fields) {
    $(".contentTable  tr:not(:first)").remove();
    $.each(fields, function (key, value) {
        if (value == 'java.util.List' || value.charAt(0) == '[') {
            $(".contentTable>tbody").append('<tr><td>' + key + '</td><td><textarea name="' + key + '" id="' + key + '" class="area1"/>&nbsp;' + value + '</td></tr>');
        } else {
            $(".contentTable>tbody").append('<tr><td>' + key + '</td><td><input type="input" name="' + key + '" id="' + key + '"/>&nbsp;' + value + '</td></tr>');
        }
    });
    //init storage value
    getStorage();
}

function trimData() {
    $('.paramsText').empty();
    $('.resultText').empty();
    $('#sign').val('');

}

function setStorage() {
    var deviceNumber = $('#deviceNumber').val();
    if (typeof(deviceNumber) != "undefined") {
        localStorage["deviceNumber"] = deviceNumber;
    }
    var shopId = $('#shopId').val();
    if (typeof(shopId) != "undefined") {
        localStorage["shopId"] = shopId;
    }
    var operatorId = $('#operatorId').val();
    if (typeof(operatorId) != "undefined") {
        localStorage["operatorId"] = operatorId;
    }
    var secret = $('#secret').val();
    if (typeof(secret) != "undefined") {
        localStorage["secret"] = secret;
    }
}

function getStorage() {
    $('#deviceNumber').val(localStorage["deviceNumber"]);
    $('#shopId').val(localStorage["shopId"]);
    $('#operatorId').val(localStorage["operatorId"]);
    $('#secret').val(localStorage["secret"]);
}

$.fn.serializeObject = function () {
    var serializeObj = {};
    var array = this.serializeArray();
    var str = this.serialize();
    $(array).each(function () {
        if (serializeObj[this.name]) {
            if ($.isArray(serializeObj[this.name])) {
                serializeObj[this.name].push(this.value);
            } else {
                serializeObj[this.name] = [serializeObj[this.name], this.value];
            }
        } else {
            if ($('.contentTable textarea[id=' + this.name + ']').length > 0) {
                if ($.trim(this.value) != '') {
                    try {
                        serializeObj[this.name] = $.parseJSON(this.value);
                    } catch (e) {
                        alert('参数（' + this.name + '）不是一个标准的JSON格式数据')
                    }
                }
            } else {
                serializeObj[this.name] = this.value;
            }
        }
    });
    return serializeObj;
};