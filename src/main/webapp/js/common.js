function pageTable(url,columns,id) {
    var $table = $("#" + id);
    var _table = $table.dataTable($.extend(true, {}, CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
        ajax: function (data, callback, settings) {
            //封装请求参数
            var param = userManage.getQueryCondition(data);
            $.ajax({
                type: "GET",
                url: rootPath + "/carrier/getCarrierByPage.action",
                cache: false,	//禁用缓存
                data: param,	//传入已封装的参数
                dataType: "json",
                success: function (result) {
                    //异常判断与处理
                    if (result.errorCode) {
                        alert("查询失败");
                        return;
                    }
                    //封装返回数据
                    var returnData = {};
                    returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
                    returnData.recordsTotal = result.total;//总记录数
                    returnData.recordsFiltered = result.total;//后台不实现过滤功能，每次查询均视作全部结果
                    returnData.data = result.pageData;
                    //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                    //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                    callback(returnData);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("查询失败");
                }
            });
        },
        //绑定数据
        columns: columns
    })).api();//此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象
};

columns = [
    {
        data: "carrierId",//字段名
    },
    {
        data: "carrierName",//字段名
    },
    {
        data: "carrierStatus",//字段名
        render: function (data, type, row, meta) {
            return (data == 1 ? "可以查发" : data == 2 ? "可以链接" : data == 3 ? "仅供查询" : "不可用");
        }
    },
    {
        data: "carrierPhone",//字段名
    },
    {
        data: "carrierLink",//字段名
        orderable: false,//禁用排序
        render: CONSTANT.DATA_TABLES.RENDER.ELLIPSIS//alt效果
    },
    {
        data: null,//字段名
        defaultContent: "",//无默认值
        orderable: false//禁用排序
    }
]

var userManage = {
    getQueryCondition: function (data) {
        var param = {};
        //组装分页参数
        param.startIndex = data.start;
        param.pageSize = data.length;
        param.draw = data.draw;
        return param;
    },
};