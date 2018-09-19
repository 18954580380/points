<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/base.jsp" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Kode is a Premium Bootstrap Admin Template, It's responsive, clean coded and mobile friendly">
    <meta name="keywords" content="bootstrap, admin, dashboard, flat admin template, responsive," />
    <title>积分管理</title>
    <!-- ========== Css Files ========== -->
    <link href="<%=path%>/TheHomePageTemplate/css/root.css" rel="stylesheet">
    <script type="text/javascript">
        //打开商品弹层
        function  openGoodsView(){
            //先判断是否选择了部分商品适用
            var goodsRewardScope = $("input[name='radio']:checked").val();
            if(goodsRewardScope!=2){
                layer.msg("请正确选择商品奖励范围：部分商品适用!")
                return false;
            }
            var goodsIds=$("#goodsIds").val();
            //打开弹层
            layer.open({
                type:2,
                title:"商品列表",
//    	        fix: false,
                closeBtn:1, //显示关闭按钮
                shade: [0.8, '#393D49'],
                area: ['50%','70%'],
                shadeClose:true,
                scrollbar: false, // 父页面 滚动条 禁止
                offset: 'auto', //右下角弹出
                shift: 2,
                zIndex: 999,
                content: ['<%=path%>/goodsRewardStrategy/goodsView?goodsIds='+goodsIds, 'yes']
            });

        }
        function save(){
            //商品奖励积分比例
            var goodsRewardRatio=$("#goodsRewardRatio").val();
            if(goodsRewardRatio==""){
                layer.msg("商品奖励积分比例不能为空");
                return false
            }
            if(!checkRate(goodsRewardRatio)){
                layer.msg("商品奖励积分比例值为正整数");
                return false
            }
            //获取奖励方式选中的值
            var goodsRewardScope = $("input[name='radio']:checked").val();
            $("#goodsRewardScope").val(goodsRewardScope);
        }
        //判断正整数
        function checkRate(value)
        {
            var re = /^[1-9]+[0-9]*]*$/;

            if (!re.test(value))
            {
                return false;
            }
            return true;
        }
        //删除行商品
        function deleteTr(obj){
            var trData=$(obj).parent().parent();$
            var tdText=trData.find('td').eq(0).text();//tdText
            //会显数据
            var goodIds="";
            var names="";
            var topcatids="";
            var topcatnames="";
            //转数组
            var goodIdsArr=$("#goodsIds").val().split(",");
            var namesArr=$("#names").val().split(",");
            var topcatidsArr=$("#topcatids").val().split(",");
            var topcatnamesArr=$("#topcatnames").val().split(",");
            for ( var i = 0; i <goodIdsArr.length; i++){
                if (goodIdsArr[i]==tdText) {
                }else{
                    //拼接
                    goodIds+=goodIdsArr[i]+","
                    names+=namesArr[i]+","
                    topcatids+=topcatidsArr[i]+","
                    topcatnames+=topcatnamesArr[i]+","
                }
            }
            if(goodIds!=""){
                goodIds = goodIds.substr(0, goodIds.length - 1);
                names = names.substr(0, names.length - 1);
                topcatids = topcatids.substr(0, topcatids.length - 1);
                topcatnames = topcatnames.substr(0, topcatnames.length - 1);
            }
            //从新赋值
            $("#goodsIds").val(goodIds);
            $("#names").val(names);
            $("#topcatids").val(topcatids);
            $("#topcatnames").val(topcatnames);
            var spanText=$("#number").text();
            if(parseInt(spanText)>=1){
                $("#number").text(parseInt(spanText)-1)
            }
            trData.remove();
        }
        //显示商品列表
        function  showTable(obj){
            if(obj.checked){
                $("#describe").show();
                $("#data").show();
            }else{
                $("#describe").hide();
                $("#data").hide();
            }

        }

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
            <a href="#" data-toggle="dropdown" class="dropdown-toggle hdbutton">Create New <span class="caret"></span></a>
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
            <a href="#" data-toggle="dropdown" class="dropdown-toggle profilebox"><img src="<%=path%>/TheHomePageTemplate/img/profileimg.png" alt="img"><b>${loginName }</b><span class="caret"></span></a>
            <ul class="dropdown-menu dropdown-menu-list dropdown-menu-right">
                <li><a href="<%=path%>/logout" target="showlayout"><i class="fa falist fa-power-off"></i> 退出登录</a></li>
            </ul>
        </li>

    </ul>
    <!-- End Top Right -->

</div>

<!-- END TOP -->
<!-- //////////////////////////////////////////////////////////////////////////// -->

<!-- //////////////////////////////////////////////////////////////////////////// -->
<!-- Start Page Header -->
<div class="page-header">
</div>
<!-- End Page Header -->
<!-- //////////////////////////////////////////////////////////////////////////// -->
<!-- START CONTAINER -->
<div class="container-padding" style="margin-top: 60px">
    <!-- Start Row -->
    <div class="row btndiv">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <h3 style="margin-top: 0px" class="title">新建商品奖励策略</h3>
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
                    <form class="form-horizontal"  action="<%=path%>/goodsRewardStrategy/update" id="addActivityRewardStrategy" onsubmit="return save()" method="post">
                        <input value="${goodsRewardStrategy.id}" name="id" type="hidden">
                        <div class="form-group">
                            <label class="col-sm-1 control-label form-label">商品奖励范围<span style="color: red;margin-left: 10px">*</span></label>
                            <div class="col-sm-8">
                                <div class="radio">
                                    <input type="radio" name="radio" id="radio1" value="1" ${goodsRewardStrategy.goodsRewardScope==1?"checked":""}>
                                    <label for="radio1">
                                        全部商品适用
                                    </label>
                                </div>
                                <div class="radio radio-warning">
                                    <input type="radio" name="radio" id="radio2" value="2" ${goodsRewardStrategy.goodsRewardScope==2?"checked":""}>
                                    <label for="radio2">
                                        部分商品适用
                                    </label>
                                    &nbsp;&nbsp;&nbsp;&nbsp;共选择了<span id="number">${fn:length(goodsRewardStrategyGoodsRelations)}</span>件商品
                                    <a style="cursor:pointer;margin-left: 30px" onclick="openGoodsView()">从列表中选择商品</a>&nbsp;&nbsp;
                                    <input type="checkbox" name="checkbox5" id="checkbox5" checked   value=""  onclick="showTable(this)"><span>显示商品列表</span>
                                </div>
                                <!--商品奖励范围-->
                                <input name="goodsRewardScope" value="1" id="goodsRewardScope" type="hidden">
                            </div>
                        </div>
                        <!--商品列表 -->
                        <div class="form-group">
                            <label id="describe"  class="col-sm-1 control-label form-label">已选商品列表<span style="color: #ccffc4;margin-left: 10px"></span></label>
                            <div class="col-sm-8"  id="data">
                                <table id="selectGoodsList"  class="table table-striped table-bordered">
                                    <thead>
                                    <tr>
                                        <th style="display: none">Id</th>
                                        <th>商品名称</th>
                                        <th>商品所属分类</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tfoot>
                                    </tfoot>
                                    <tbody>
                                     <c:forEach var="good" items="${goodsRewardStrategyGoodsRelations}">
                                         <tr>
                                             <td style="display: none">${good.goodsId}</td>
                                             <td>${good.goodsName}</td>
                                             <td>${good.goodsCategory}</td>
                                             <td><a style='cursor:pointer' onclick='deleteTr(this)'>删除</a></td>
                                         </tr>

                                     </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <!--存放关联的商品Id-->
                            <input name="goodsIds" id="goodsIds" value="${ids}" type="hidden">
                            <input name="names" id="names" value="${names}" type="hidden">
                            <input name="topcatids" id="topcatids" value="${topcatids}" type="hidden">
                            <input name="topcatnames" id="topcatnames" value="${topcatnames}" type="hidden">
                        </div>
                        <div class="form-group">
                            <label for="goodsRewardRatio" class="col-sm-1 control-label form-label" style="width: 10%">商品奖励积分比例<span style="color: red;margin-left: 10px">*</span></label>
                            <div class="col-sm-8">
                                <input style="width: 220px" type="text" class="form-control" value="${goodsRewardStrategy.goodsRewardRatio}" name="goodsRewardRatio" id="goodsRewardRatio">
                            </div>
                        </div>
                        <input type="submit" value="提交" style="width: 76px;margin: 10px"/>
                        <a href="<%=path%>/activityRewardStrategy/listView" class="btn" style="margin-bottom: 3px">返回</a>
                    </form>
                </div>
            </div>
        </div>
        <!-- End Panel -->
    </div>
    <!-- End Row -->
</div>
<!-- END CONTAINER -->
<!-- //////////////////////////////////////////////////////////////////////////// -->
</div>
<!-- End Content -->
<!-- //////////////////////////////////////////////////////////////////////////// -->

<!-- //////////////////////////////////////////////////////////////////////////// -->
<!-- START SIDEPANEL -->
<div role="tabpanel" class="sidepanel">
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#today" aria-controls="today" role="tab" data-toggle="tab">TODAY</a></li>
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
                    <input id="checkboxside2" type="checkbox"><label for="checkboxside2"><b>May 12, 6:30 pm</b> Meeting with Team</label>
                </li>

                <li class="checkbox checkbox-warning">
                    <input id="checkboxside3" type="checkbox"><label for="checkboxside3">Design Facebook page</label>
                </li>

                <li class="checkbox checkbox-info">
                    <input id="checkboxside4" type="checkbox"><label for="checkboxside4">Send Invoice to customers</label>
                </li>

                <li class="checkbox checkbox-danger">
                    <input id="checkboxside5" type="checkbox"><label for="checkboxside5">Meeting with developer team</label>
                </li>
            </ul>
            <div class="gn-title">TOMORROW</div>
            <ul class="todo-list">
                <li class="checkbox checkbox-warning">
                    <input id="checkboxside6" type="checkbox"><label for="checkboxside6">Redesign our company blog</label>
                </li>

                <li class="checkbox checkbox-success">
                    <input id="checkboxside7" type="checkbox"><label for="checkboxside7">Finish client work</label>
                </li>

                <li class="checkbox checkbox-info">
                    <input id="checkboxside8" type="checkbox"><label for="checkboxside8">Call Johnny from Developer Team</label>
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
                <li class="member"><a href="#"><img src="img/profileimg.png" alt="img"><b>Allice Mingham</b>Los Angeles</a><span class="status online"></span></li>
                <li class="member"><a href="#"><img src="img/profileimg2.png" alt="img"><b>James Throwing</b>Las Vegas</a><span class="status busy"></span></li>
                <li class="member"><a href="#"><img src="img/profileimg3.png" alt="img"><b>Fred Stonefield</b>New York</a><span class="status away"></span></li>
                <li class="member"><a href="#"><img src="img/profileimg4.png" alt="img"><b>Chris M. Johnson</b>California</a><span class="status online"></span></li>
            </ul>

            <div class="gn-title">OFFLINE MEMBERS (8)</div>
            <ul class="group">
                <li class="member"><a href="#"><img src="img/profileimg5.png" alt="img"><b>Allice Mingham</b>Los Angeles</a><span class="status offline"></span></li>
                <li class="member"><a href="#"><img src="img/profileimg6.png" alt="img"><b>James Throwing</b>Las Vegas</a><span class="status offline"></span></li>
            </ul>

            <form class="search">
                <input type="text" class="form-control" placeholder="Search a Friend...">
            </form>
        </div>
        <!-- End Chat -->
    </div>
</div>
<!-- END SIDEPANEL -->
<!-- //////////////////////////////////////////////////////////////////////////// -->
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
Data Tables
================================================ -->
<script src="<%=path%>/js/jquery.dataTables.js"></script>

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
<script src="<%=path%>/layer/layer.js"></script>
<script src="<%=path%>/TheHomePageTemplate/js/bootstrap-datetimepicker/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script src="<%=path%>/TheHomePageTemplate/js/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="<%=path%>/js/custom/timeFormatting.js"></script>
<!-- Basic Date Range Picker -->
<script type="text/javascript">
    $(function () {
        $('.selectdate').datetimepicker({
            clearBtn:false,
            language: 'zh-CN',
            weekStart: 1,
            autoclose: 1,
            format: 'yyyy-mm-dd',
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0
        });
    });
</script>
<%--  <script>
      $(document).ready(function() {
          $('#selectGoodsList').DataTable();
      } );
  </script>--%>
</body>
</html>