<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/base.jsp" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description"
          content="Kode is a Premium Bootstrap Admin Template, It's responsive, clean coded and mobile friendly">
    <meta name="keywords" content="bootstrap, admin, dashboard, flat admin template, responsive,"/>
    <title>Kode</title>
    <!-- ========== Css Files ========== -->
    <link href="<%=path%>/TheHomePageTemplate/css/root.css" rel="stylesheet">
    <script type="text/javascript">

    </script>
</head>
<body style="background:#f5f5f5">
<!-- Start Page Loading -->
<div class="loading"><img src="<%=path%>/TheHomePageTemplate/img/loading.gif" alt="loading-img"></div>
<!-- End Page Loading -->
<!-- //////////////////////////////////////////////////////////////////////////// -->
<!-- START TOP -->
<div id="top" class="clearfix">
    <!-- Start Sidebar Show Hide Button -->
    <a href="#" class="sidebar-open-button"><i class="fa fa-bars"></i></a>
    <a href="#" class="sidebar-open-button-mobile"><i class="fa fa-bars"></i></a>
    <!-- End Sidebar Show Hide Button -->

    <!-- Start Searchbox -->
    <form class="searchform">
        <input type="text" class="searchbox" id="searchbox" placeholder="Search">
        <span class="searchbutton"><i class="fa fa-search"></i></span>
    </form>
    <!-- End Searchbox -->

    <!-- Start Top Menu -->
    <ul class="topmenu">
        <li><a href="#">Files</a></li>
        <li><a href="#">Authors</a></li>
        <li class="dropdown">
            <a href="#" data-toggle="dropdown" class="dropdown-toggle">My Files <span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="#">Videos</a></li>
                <li><a href="#">Pictures</a></li>
                <li><a href="#">Blog Posts</a></li>
            </ul>
        </li>
    </ul>
    <!-- End Top Menu -->

    <!-- Start Sidepanel Show-Hide Button -->
    <a href="#sidepanel" class="sidepanel-open-button"><i class="fa fa-outdent"></i></a>
    <!-- End Sidepanel Show-Hide Button -->

    <!-- Start Top Right -->
    <ul class="top-right">

        <li class="dropdown link">
            <a href="#" data-toggle="dropdown" class="dropdown-toggle hdbutton">Create New <span
                    class="caret"></span></a>
            <ul class="dropdown-menu dropdown-menu-list">
                <li><a href="#"><i class="fa falist fa-paper-plane-o"></i>Product or Item</a></li>
                <li><a href="#"><i class="fa falist fa-font"></i>Blog Post</a></li>
                <li><a href="#"><i class="fa falist fa-file-image-o"></i>Image Gallery</a></li>
                <li><a href="#"><i class="fa falist fa-file-video-o"></i>Video Gallery</a></li>
            </ul>
        </li>

        <li class="link">
            <a href="#" class="notifications">6</a>
        </li>

        <li class="dropdown link">
            <a href="#" data-toggle="dropdown" class="dropdown-toggle profilebox"><img
                    src="<%=path%>/TheHomePageTemplate/img/profileimg.png" alt="img"><b>${loginName }</b><span
                    class="caret"></span></a>
            <ul class="dropdown-menu dropdown-menu-list dropdown-menu-right">
                <li><a href="<%=path%>/logout" target="showlayout"><i class="fa falist fa-power-off"></i> 退出登录</a></li>
            </ul>
        </li>

    </ul>
    <!-- End Top Right -->

</div>
<!-- END TOP -->
<div class="page-header">
</div>
<!-- End Page Header -->
<!-- //////////////////////////////////////////////////////////////////////////// -->
<!-- START CONTAINER -->
<div class="container-padding" style="margin-top:60px;">
    <!-- Start Row -->
    <div class="row btndiv">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <h3 style="margin-top: 0px" class="title">积分消费策略设置</h3>
                </div>
            </div>
        </div>
    </div>
    <!-- end Row -->
    <!-- Start Row -->
    <div class="row">
        <!-- Start Panel -->
    <div class="col-md-12">
      <div class="panel panel-default">
        <div class="panel-title">

        </div>
        <div class="panel-body">
            <div class="col-md-12 col-lg-12">
                <div class="panel panel-default">

                    <div class="panel-title">
                        Horizontal Form
                        <ul class="panel-tools">
                            <li><a class="icon minimise-tool"><i class="fa fa-minus"></i></a></li>
                            <li><a class="icon expand-tool"><i class="fa fa-expand"></i></a></li>
                            <li><a class="icon closed-tool"><i class="fa fa-times"></i></a></li>
                        </ul>
                    </div>

                    <div class="panel-body">
                        <form class="form-horizontal" action="<%=path%>/consumeRewardPoints/save" type="post">

                            <div class="form-group">
                                <label class="col-sm-2 control-label form-label">消费策略名称:</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control"  name="id" style="width: 300px" value="${p.id}">

                                    <input type="text" class="form-control" id="input11" name="expenseStrategyName" style="width: 300px" value="${p.expenseStrategyName}">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label form-label">兑换方式:</label>
                                <div class="col-sm-10">
                                    <div class="checkbox checkbox-info checkbox-circle">
                                        <input id="checkbox1" type="checkbox" checked>
                                        <label for="checkbox1">
                                           积分抵现(通过积分抵扣部分商品价格,如:每200积分减20元)
                                        </label>
                                    </div>
                                    每一百积分抵扣
                                    <input type="text" class="form-control" style="width: 100px" name="pointsToNow" value="${p.pointsToNow}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label form-label">抵扣上限:</label>
                                <input type="number" value="100" class="form-control" style="width: 100px" name="discountMax" value="${p.discountMax}">
                            </div>


                            <div class="form-group">
                                <label class="col-sm-2 control-label form-label">兑换范围</label>
                                <div class="col-sm-2">
                                    <div class="radio radio-inline">
                                        <input id="radio4" type="radio" name="convertScope" value="0"  <c:if test="${p.convertScope==0||p.convertScope!=1}">checked</c:if>>
                                        <label for="radio4">
                                            全部商品
                                        </label>
                                    </div>
                                    <div class="radio radio-inline">
                                        <input id="radio5" type="radio" name="convertScope" value="1" <c:if test="${p.convertScope==1}">checked</c:if> >
                                        <label for="radio5">
                                            部分商品
                                        </label>
                                    </div>
                                </div>
                                <input type="button" value="从列表中选择商品" onclick="goodSelectOnclick(this)">
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label form-label">兑换策略时效</label>
                                <div class="col-sm-2">
                                    <div class="radio radio-inline">
                                        <input id="radio6" type="radio" name="convertScopeAging" value="0" <c:if test="${p.convertScopeAging==0||p.convertScopeAging!=1}">checked</c:if>>
                                        <label for="radio6">
                                            永久有效
                                        </label>
                                    </div>
                                    <div class="radio radio-inline">
                                        <input id="radio7" type="radio" name="convertScopeAging"  value="1" <c:if test="${p.convertScopeAging==1}">checked</c:if>>
                                        <label for="radio7">
                                            指定时间
                                        </label>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <fieldset>
                                        <div class="control-group">
                                            <div class="controls">
                                                <div class="input-prepend input-group">
                                                    <span class="add-on input-group-addon"><i
                                                            class="fa fa-calendar"></i></span>
                                                    <input type="text"
                                                           class="mydate"
                                                           name="startTime" value="<fmt:formatDate value="${e.startTime}" pattern="yyyy-MM-dd"/>"/>
                                                    <span class="add-on input-group-addon"><i
                                                            class="fa fa-calendar"></i></span>
                                                    <input type="text"
                                                           class="mydate"
                                                           name="endTime" value="<fmt:formatDate value="${e.endTime}" pattern="yyyy-MM-dd"/>"/>
                                                </div>

                                            </div>
                                        </div>
                                    </fieldset>
                                </div>
                            </div>

                            <div id="goodsform"></div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="submit" class="btn btn-default">提交</button>
                                </div>
                            </div>

                        </form>
                    </div>

                </div>
            </div>
        </div>
      </div>
    </div>
    </div>
    <!-- End Row -->
</div>
<!-- END CONTAINER -->
<!-- //////////////////////////////////////////////////////////////////////////// -->
</div>
<!--  弹框div -->
<div class="goodDivId"
     style="display:none;position: fixed;z-index:9999;left:50%;top:50%;margin-left:-200px;margin-top:-200px;width: 450px;padding: 30px;border:solid 2px #000000;border-radius:2px;background: white;">
    <center>
        <div class="mb25 pr" style="height: 400px;overflow-y: scroll">
            <table class="table table-hover table-striped" id="koboroDoctorRadioId">
                <thead>
                <tr>
                    <td>选择</td>
                    <td>序号</td>
                    <td>商品ID</td>
                    <td>商品名称</td>
                    <td>商品类型</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="e" items="${goods}" varStatus="index">
                    <tr>
                            <%--<td style="display: none"><input name="id"  value="${e.id}"/></td>--%>
                        <td style="width: 5%">
                       <input type="checkbox" name="goods" value="${e.get("goods_id")}" valueName="${e.get("name")}" valueType="${e.get("goods_type")}"></td>
                        <td style="width: 5%">${index.index+1 }</td>
                        <td style="width: 15%">${e.get("goods_id")}</td>
                        <td style="width: 15%">${e.get("name")}</td>
                        <td style="width: 15%">${e.get("goods_type")}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="mb20">
            <button class="btn btn-primary " id="goodsDivDuttonId" type="button" title="确认">确&nbsp;认</button>
            &nbsp;&nbsp;
            <button class="btn btn-default fl mr32" id="mr32" type="button" title="取消">取&nbsp;消</button>
        </div>
    </center>
</div>
<!-- End Content -->
<!-- //////////////////////////////////////////////////////////////////////////// -->

<!-- //////////////////////////////////////////////////////////////////////////// -->
<!-- START SIDEPANEL -->
<div role="tabpanel" class="sidepanel">

    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#today" aria-controls="today" role="tab"
                                                  data-toggle="tab">TODAY</a></li>
        <li role="presentation"><a href="#tasks" aria-controls="tasks" role="tab" data-toggle="tab">TASKS</a></li>
        <li role="presentation"><a href="#chat" aria-controls="chat" role="tab" data-toggle="tab">CHAT</a></li>
    </ul>
    <!-- Tab panes -->
    <div class="tab-content">
        <!-- Start Today -->
        <div role="tabpanel" class="tab-pane active" id="today">

            <div class="sidepanel-m-title">
                Today
                <span class="left-icon"><a href="#"><i class="fa fa-refresh"></i></a></span>
                <span class="right-icon"><a href="#"><i class="fa fa-file-o"></i></a></span>
            </div>

            <div class="gn-title">NEW</div>

            <ul class="list-w-title">
                <li>
                    <a href="#">
                        <span class="label label-danger">ORDER</span>
                        <span class="date">9 hours ago</span>
                        <h4>New Jacket 2.0</h4>
                        Etiam auctor porta augue sit amet facilisis. Sed libero nisi, scelerisque.
                    </a>
                </li>
                <li>
                    <a href="#">
                        <span class="label label-success">COMMENT</span>
                        <span class="date">14 hours ago</span>
                        <h4>Bill Jackson</h4>
                        Etiam auctor porta augue sit amet facilisis. Sed libero nisi, scelerisque.
                    </a>
                </li>
                <li>
                    <a href="#">
                        <span class="label label-info">MEETING</span>
                        <span class="date">at 2:30 PM</span>
                        <h4>Developer Team</h4>
                        Etiam auctor porta augue sit amet facilisis. Sed libero nisi, scelerisque.
                    </a>
                </li>
                <li>
                    <a href="#">
                        <span class="label label-warning">EVENT</span>
                        <span class="date">3 days left</span>
                        <h4>Birthday Party</h4>
                        Etiam auctor porta augue sit amet facilisis. Sed libero nisi, scelerisque.
                    </a>
                </li>
            </ul>

        </div>
        <!-- End Today -->

        <!-- Start Tasks -->
        <div role="tabpanel" class="tab-pane" id="tasks">

            <div class="sidepanel-m-title">
                To-do List
                <span class="left-icon"><a href="#"><i class="fa fa-pencil"></i></a></span>
                <span class="right-icon"><a href="#"><i class="fa fa-trash"></i></a></span>
            </div>

            <div class="gn-title">TODAY</div>

            <ul class="todo-list">
                <li class="checkbox checkbox-primary">
                    <input id="checkboxside1" type="checkbox"><label for="checkboxside1">Add new products</label>
                </li>

                <li class="checkbox checkbox-primary">
                    <input id="checkboxside2" type="checkbox"><label for="checkboxside2"><b>May 12, 6:30 pm</b> Meeting
                    with Team</label>
                </li>

                <li class="checkbox checkbox-warning">
                    <input id="checkboxside3" type="checkbox"><label for="checkboxside3">Design Facebook page</label>
                </li>

                <li class="checkbox checkbox-info">
                    <input id="checkboxside4" type="checkbox"><label for="checkboxside4">Send Invoice to
                    customers</label>
                </li>

                <li class="checkbox checkbox-danger">
                    <input id="checkboxside5" type="checkbox"><label for="checkboxside5">Meeting with developer
                    team</label>
                </li>
            </ul>
            <div class="gn-title">TOMORROW</div>
            <ul class="todo-list">
                <li class="checkbox checkbox-warning">
                    <input id="checkboxside6" type="checkbox"><label for="checkboxside6">Redesign our company
                    blog</label>
                </li>

                <li class="checkbox checkbox-success">
                    <input id="checkboxside7" type="checkbox"><label for="checkboxside7">Finish client work</label>
                </li>

                <li class="checkbox checkbox-info">
                    <input id="checkboxside8" type="checkbox"><label for="checkboxside8">Call Johnny from Developer
                    Team</label>
                </li>

            </ul>
        </div>
        <!-- End Tasks -->

        <!-- Start Chat -->
        <div role="tabpanel" class="tab-pane" id="chat">

            <div class="sidepanel-m-title">
                Friend List
                <span class="left-icon"><a href="#"><i class="fa fa-pencil"></i></a></span>
                <span class="right-icon"><a href="#"><i class="fa fa-trash"></i></a></span>
            </div>

            <div class="gn-title">ONLINE MEMBERS (3)</div>
            <ul class="group">
                <li class="member"><a href="#"><img src="img/profileimg.png" alt="img"><b>Allice Mingham</b>Los Angeles</a><span
                        class="status online"></span></li>
                <li class="member"><a href="#"><img src="img/profileimg2.png" alt="img"><b>James Throwing</b>Las
                    Vegas</a><span class="status busy"></span></li>
                <li class="member"><a href="#"><img src="img/profileimg3.png" alt="img"><b>Fred Stonefield</b>New
                    York</a><span class="status away"></span></li>
                <li class="member"><a href="#"><img src="img/profileimg4.png" alt="img"><b>Chris M. Johnson</b>California</a><span
                        class="status online"></span></li>
            </ul>

            <div class="gn-title">OFFLINE MEMBERS (8)</div>
            <ul class="group">
                <li class="member"><a href="#"><img src="img/profileimg5.png" alt="img"><b>Allice Mingham</b>Los Angeles</a><span
                        class="status offline"></span></li>
                <li class="member"><a href="#"><img src="img/profileimg6.png" alt="img"><b>James Throwing</b>Las
                    Vegas</a><span class="status offline"></span></li>
            </ul>

            <form class="search">
                <input type="text" class="form-control" placeholder="Search a Friend...">
            </form>
        </div>
        <!-- End Chat -->

    </div>

</div>

<!-- ================================================
jQuery Library
================================================ -->
<script type="text/javascript" src="<%=path%>/TheHomePageTemplate/js/jquery.min.js"></script>

<!-- ================================================
Bootstrap Core JavaScript File
================================================ -->
<script src="<%=path%>/TheHomePageTemplate/js/bootstrap/bootstrap.min.js"></script>

<!-- ================================================
Plugin.js - Some Specific JS codes for Plugin Settings
================================================ -->
<script type="text/javascript" src="<%=path%>/TheHomePageTemplate/js/plugins.js"></script>

<!-- ================================================
Bootstrap Select
================================================ -->
<script type="text/javascript" src="<%=path%>/TheHomePageTemplate/js/bootstrap-select/bootstrap-select.js"></script>

<!-- ================================================
Bootstrap Toggle
================================================ -->
<script type="text/javascript" src="<%=path%>/TheHomePageTemplate/js/bootstrap-toggle/bootstrap-toggle.min.js"></script>

<!-- ================================================
Moment.js
================================================ -->
<script type="text/javascript" src="<%=path%>/TheHomePageTemplate/js/moment/moment.min.js"></script>

<!-- ================================================
Bootstrap Date Range Picker
================================================ -->
<script type="text/javascript" src="<%=path%>/TheHomePageTemplate/js/date-range-picker/daterangepicker.js"></script>
<!-- ================================================
kindeditor富文本编译器
================================================ -->
<script charset="utf-8" src="<%=path%>/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="<%=path%>/kindeditor/lang/zh-CN.js"></script>
<script charset="utf-8" src="<%=path%>/layer/layer.js"></script>
<script src="<%=path%>/js/page.js"></script>

<script>
    var locale1 = {
        format: "YYYY/MM/DD",
        separator: " - ",
        applyLabel: "确认",
        cancelLabel: "清空",
        fromLabel: "开始时间",
        toLabel: "结束时间",
        customRangeLabel: "自定义",
        daysOfWeek: ["日", "一", "二", "三", "四", "五", "六"],
        monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"]
    };
    $(document).ready(function () {
        $('.mydate').daterangepicker({
            'locale': locale1,
            language: 'zh-CN',
            // timePicker: true,
            timePickerIncrement: 10,
            timePicker24Hour: true,
            singleDatePicker: true,
            autoUpdateInput: true,//1.当设置为false的时候,不给与默认值(当前时间)2.选择时间时,失去鼠标焦点,不会给与默认值 默认true
        }, function (start, end, label) {
            console.log(start.toISOString(), end.toISOString(), label);
        });
    });
    //定义locale汉化插件
    var locale = {
        format: "YYYY/MM/DD HH:mm:SS",
        separator: " - ",
        applyLabel: "确认",
        cancelLabel: "清空",
        fromLabel: "开始时间",
        toLabel: "结束时间",
        customRangeLabel: "自定义",
        daysOfWeek: ["日", "一", "二", "三", "四", "五", "六"],
        monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"]
    };
    // 活动时间 -----时间区间
    $(document).ready(function () {
        $('#date-range-and-time-picker').daterangepicker({
            'locale': locale,
            //汉化按钮部分
            ranges: {
                '今日': [moment(), moment()],
                '最近7日': [moment(), moment().subtract(-6, 'days')],
                '最近30日': [moment(), moment().subtract(-29, 'days')],
                '本月': [moment().startOf('month'), moment().endOf('month')],
                '下一个月': [moment().subtract(-1, 'month').startOf('month'), moment().subtract(-1, 'month').endOf('month')]
            },
            timePicker: true,
            timePickerIncrement: 10,
            timePicker24Hour: true,
            showDropdowns: true,//当设置值为true的时候，允许年份和月份通过下拉框的形式选择 默认false
            autoUpdateInput: true,//1.当设置为false的时候,不给与默认值(当前时间)2.选择时间时,失去鼠标焦点,不会给与默认值 默认true
        }, function (start, end, label) {
            console.log(start.toISOString(), end.toISOString(), label);
        });
    });
    //医生选择
    function goodSelectOnclick(node) {
        $('.goodDivId').show();

        $("#goodsDivDuttonId").click(function () {
            var names = document.getElementsByName("goods");
            var i = 0;
            var goodsid="";
            var goodsname="";
            var goodstype="";
            for (i; i < names.length; i++) {
                if (names[i].checked) {
                    alert(names[i].getAttribute('valueName'));
                    goodsid='<input  type="text" name="goodsId" ' +
                        'value="' + names[i].value  + '" >';
                    goodsname='<input  type="text" name="goodsName" ' +
                        'value="' + names[i].getAttribute('valueName')  + '" >';
                    goodstype='<input  type="text" name="goodsCategory" ' +
                        'value="' + names[i].getAttribute('valueType')  + '" >';
                    $('#goodsform').append(goodsid);
                    $('#goodsform').append(goodsname);
                    $('#goodsform').append(goodstype);
                }
            }

            $('.goodDivId').hide();
            $("#goodsDivDuttonId").unbind();
        })

        $(".mr32").click(function () {
            $('.goodDivId').hide();
            $("#goodsDivDuttonId").unbind();
        });
    }
</script>
</body>
</html>