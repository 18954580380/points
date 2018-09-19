$(function () {
    var $table = $("#table");
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
        columns: [
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
        ],
        "createdRow": function (row, data, index) {
            //不使用render，改用jquery文档操作呈现单元格
            var $btnEdit = $('<button type="button" class="btn-edit">修改</button>');
            var $btnDel = $('<button type="button" class="btn-del">删除</button>');
            $('td', row).eq(5).append($btnEdit).append($btnDel);
        },
        "drawCallback": function (settings) {
            //渲染完毕后的回调
            //默认选中第一行
            //$("tbody tr",$table).eq(0).click();
        }
    })).api();//此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象
    // //查询
    // $("#btn_search").click(function () {
    //     _table.draw();
    // });
    // //行点击事件
    // $("tbody", $table).on("click", "tr", function (event) {
    //     $(this).addClass("active").siblings().removeClass("active");
    //     //获取该行对应的数据
    //     var item = _table.row($(this).closest('tr')).data();
    //     userManage.showItemDetail(item);
    // });
    // //按钮点击事件
    // $table.on("click", ".btn-edit", function () {
    //     //点击编辑按钮
    //     var item = _table.row($(this).closest('tr')).data();
    //     userManage.editItemInit(item);
    // }).on("click", ".btn-del", function () {
    //     //点击删除按钮
    //     var item = _table.row($(this).closest('tr')).data();
    //     userManage.deleteItem(item);
    // });
    // //影藏列
    // $('a').on('click', function (e) {
    //     var cut = $(this).text()
    //     if (cut.indexOf("显示") > -1) {
    //         $(this).text("影藏" + cut.split("示")[1])
    //     } else {
    //         $(this).text("显示" + cut.split("藏")[1])
    //     }
    //     e.preventDefault();
    //     var column = _table.column($(this).attr('data-column'));
    //     column.visible(!column.visible());
    // });

});
var userManage = {
    getQueryCondition: function (data) {
        var param = {};
        //组装排序参数
        if (data.order && data.order.length && data.order[0]) {
            switch (data.order[0].column) {
                case 0:
                    param.orderColumn = "carrier_id";//数据库列名称
                    break;
                case 1:
                    param.orderColumn = "carrier_name";//数据库列名称
                    break;
                case 2:
                    param.orderColumn = "carrier_status";//数据库列名称
                    break;
                case 3:
                    param.orderColumn = "carrier_phone";//数据库列名称
                    break;
                default:
                    param.orderColumn = "carrier_id";//数据库列名称
                    break;
            }
            //排序方式asc或者desc
            param.orderDir = data.order[0].dir;
        }
        param.id = $("#id-search").val();//查询条件
        param.name = $("#name-search").val();//查询条件
        param.status = $("#status-search").val();//查询条件
        //组装分页参数
        param.startIndex = data.start;
        param.pageSize = data.length;
        param.draw = data.draw;
        return param;
    },
    editItemInit: function (item) {
        //编辑方法
        alert("编辑" + item.carrierId + "  " + item.carrierName);
    },
    deleteItem: function (item) {
        //删除
        alert("删除" + item.carrierId + "  " + item.carrierName);
    },
    showItemDetail: function (item) {
        //点击行
        alert("点击" + item.carrierId + "  " + item.carrierName);
    }
};