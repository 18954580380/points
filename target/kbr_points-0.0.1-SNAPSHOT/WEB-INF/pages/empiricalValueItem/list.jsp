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
            //声明弹层
            var mylayer;
            //弹出文本框
            function editContent(id){
            var content=$("#itemDescription"+id).val();
                mylayer= layer.open({
                type: 1,
                closeBtn: false,
                shift: 7,
                shadeClose: true,
                content: "<div style='width:350px;'><div style='width:320px;margin-left: 3%;' class='form-group has-feedback'><p></p><textarea id='description' style='height: 150px;padding-right: 10px;margin-left: 1%' class='form-control' type='text' name='description' value='"+content+"'>"+content+"</textarea></div>" +
                "<button style='margin-top:0%;margin-bottom:2%; margin-left: 133px' type='button' onclick='echoData("+id+")'>确定</button></div>"
            });
        }
        //回显赋值
        function echoData(id){
            var content=$("#description").val();
            $("#itemDescription"+id).val(content);
            layer.close(mylayer);
        }
        //修改经验值项目
      function updateEmpiricalValueItem(){
    	  var ids=[];//所有项目ID
    	  var rewardPeriods=[];//所有的奖励周期
    	  var rewardNumbers=[];//所有的奖励次数
    	  var empiricalValues=[];//页面对应所有的积分值
          var itemDescriptions=[];//项目说明
    	  //获取id
    	  $("[name=id]").each(function () {
    		  ids.push($(this).val());
	        });
    	  //页面对应所有的奖励周期值
    	  $("[name=rewardPeriod]").each(function () {
    		  rewardPeriods.push($(this).val());
	        });
    	
    	  //页面对应所有的奖励次数值
    	  $("[name=rewardNumber]").each(function () {
    		  rewardNumbers.push($(this).val()==""?0:$(this).val());
	        });
    	
    	  //经验值xp
    	  $("[name=empiricalValue]").each(function () {
              empiricalValues.push($(this).val()==""?0:$(this).val());
	        });

    	  //项目说明
    	  $("[name=itemDescription]").each(function () {
              itemDescriptions.push($(this).val());
	        });
    	  //提交后台，四个数组，根据下标值一一对应
    	    console.log(ids);
    	  $.ajax({
                  type: "POST",//方法类型
                  dataType: "json",//预期服务器返回的数据类型
                  traditional: true,
                  url: "<%=path%>/empiricalValueItem/updateEmpiricalValueItem",
                  data:{
                	  "ids":ids,
                	  "rewardPeriods":rewardPeriods,
                	  "rewardNumbers":rewardNumbers,
                	  "empiricalValues":empiricalValues,
                	  "itemDescriptions":itemDescriptions},
                  success: function (result) {
                      if (result.code==1) {
                    	  layer.msg("操作成功");
                    	  setTimeout(function(){
                    		    //这里写时间到后执行的代码
                    		   window.location.reload();
                    		}, 1000);
                      }else{
                    	  layer.msg(result.msg);
                      }
                  },
                  error : function() {
                	  layer.msg("操作失败");
                  }
              });
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
                    <h3 style="margin-top: 0px" class="title">经验值项目设置</h3>
                    <!-- Start right -->
                    <div class="col-md-12 col-lg-6" style="padding-left:0px">
                        <a onclick="updateEmpiricalValueItem()" class="btn btn-rounded btn-default">提交</a>
                    </div>
                    <!-- End right -->
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
          <table class="table table-bordered table-striped">
            <thead>
              <tr>
                <td style="display: none"></td>
                <td>序号</td>
                <td>积分项目名称</td>
                <td>奖罚周期</td>
                <td>奖罚次数</td>
                <td>经验值xp</td>
                <td>积分项目来源</td>
                <td>设定/更新日期</td>
                <td>项目说明</td>
              </tr>
            </thead>
            <tbody>
             <c:forEach var="e" items="${empiricalValueItems}" varStatus="index">
              <tr>
                <td style="display: none"><input name="id"  value="${e.id}"/></td>
                <td style="width: 5%">${index.index+1 }</td>
                <td style="width: 15%">${e.pointsItemName }</td>
                <td style="width: 15%">
                <select class="selectpicker"  name="rewardPeriod" id="rewardPeriod">
                        <option ${e.rewardPeriod ==1 ?'selected':''} value="1">每天</option>
                        <option ${e.rewardPeriod ==2 ?'selected':''} value="2">每次</option>
                        <option ${e.rewardPeriod ==3 ?'selected':''} value="3">每小时</option>
                </select> 
                </td>
                <td style="width: 15%"> <input type="text" class="form-control" name="rewardNumber" id="rewardNumber"  value="${e.rewardNumber}"></td>
                <td style="width: 15%"><input  type="text" class="form-control" name="empiricalValue" id="empiricalValue"  value="${e.empiricalValue}"></td>
                 <td style="width: 10%">
                	<c:if test="${e.pointsItemSource==1}">任务模块</c:if>
                	<c:if test="${e.pointsItemSource==2}">圈子模块</c:if>
                 </td>
                <td style="width:10%"><fmt:formatDate value="${e.setDate}" pattern="yyyy-MM-dd"/></td>
                <td style="width: 10%">
                    <a style="cursor:pointer" onclick="editContent('${e.id}')">编辑</a>
                    <textarea style="display: none" value="${e.itemDescription}" name="itemDescription" id="itemDescription${e.id}">${e.itemDescription}</textarea>
                </td>
              </tr>
             </c:forEach> 
            </tbody>
          </table>
        </div>
      </div>
    </div>
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
<%--<script src="<%=path%>/TheHomePageTemplate/js/datatables/jquery.dataTables.js"></script>--%>
<script src="<%=path%>/TheHomePageTemplate/js/jquery.dataTables.js"></script>
<script src="<%=path%>/layer/layer.js"></script>
<script src="<%=path%>/js/page.js"></script>
<script type="text/javascript" src="<%=path%>/TheHomePageTemplate/js/bootstrap-select/bootstrap-select.js"></script>
</body>
</html>