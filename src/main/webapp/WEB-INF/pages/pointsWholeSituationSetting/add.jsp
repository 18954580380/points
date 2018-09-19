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
  <title>Kode</title>
  <!-- ========== Css Files ========== -->
  <link href="<%=path%>/TheHomePageTemplate/css/root.css" rel="stylesheet">
  <script type="text/javascript">
  function save(){
  	var ratioPoints=$("#ratioPoints").val();
  	var newUserInitPoints=$("#newUserInitPoints").val();
  	var alarmPoints=$("#alarmPoints").val();
     if(!checkRate(ratioPoints)){
           layer.msg("积分货币折算比应为正整数！");
           return false;
     }
     if(newUserInitPoints==""){
         layer.msg("新用户初始积分不能为空！");
         return false;
     }
     if(alarmPoints==""){
         layer.msg("积分报警不能为空！");
         return false;
     }
     //获取处理方式选中的值
      var processingMode = $("input[name='radio']:checked").val();
      $("#processingMode").val(processingMode);
      if(processingMode==1){
          $("#addPoints").val("");
      }
      if(processingMode==1){
          //判断自动增加积分数量的值
          var email=$("#email").val();
          if(email==""){
              layer.msg("邮箱不能为空！");
              return false;
          }
          if(!checkEmail(email)){
              layer.msg("邮箱地址不合法！");
              return false;
          }
      }
      if(processingMode==2){
          //判断自动增加积分数量的值
          var addPoints=$("#addPoints").val();
          console.log(addPoints);
          console.log(checkRate(addPoints));
           if(addPoints==""){
               layer.msg("自动增加积分数量不能为空！");
               return false;
           }
           if(!checkRate(addPoints)){
               layer.msg("自动增加积分数量应为正整数！");
               return false;
           }
      }
      var  clearingDate=$("#clearingDate").val();
      if(clearingDate==""){
          layer.msg("清算日期不能为空！");
          return false;
      }
      //清算方式
      var strategy = $("input[name='strategy']:checked").val();
      $("#clearingStrategy").val(strategy);
      if(strategy==3){
          var clearingRatio=$("#clearingRatio").val();
          if(clearingRatio==""){
              layer.msg("清算比例不能为空！");
              return false;
          }
          if(!checkRate(clearingRatio)){
              layer.msg("清算比例应为正整数！");
              return false;
          }
      }else{
          $("#clearingRatio").val("");
      }
        return true;
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
  //校验邮箱
  function checkEmail(value)
  {
      var re = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;

      if (!re.test(value))
      {
          return false;
      }
      return true;
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
              <h3 style="margin-top: 0px" class="title">新建积分池策略</h3>
            </div>
      </div>
    </div>
  </div>
  <div class="row">
    <!-- Start Panel -->
    <div class="col-md-12">
      <div class="panel panel-default">
        <div class="panel-title">
        </div>
        <div class="panel-body table-responsive">
          <table id="" class="table table-striped table-bordered">
            <thead>
            <tr>
              <th>积分池当前剩余可用/可发放积分总数</th>
              <th>积分池累计投放积分总数</th>
              <th>积分池累计赠送积分总数</th>
              <th>用户已领取积分总数</th>
              <th>用户已消费积分总数</th>
              <th>用户留存积分总数</th>
              <th>用户积分回收总数</th>
              <th>当前积分货币折算比</th>
            </tr>
            </thead>
            <tfoot>
            </tfoot>
            <tbody>
            <c:forEach var="d" items="${listData}">
            <tr>
              <td>${d.points}</td>
              <td>${d.putTotalPoints}</td>
              <td>${d.totalPresentedPoints}</td>
              <td>${d.userGetTotalPoints}</td>
              <td>${d.userExpenseTotalPoints}</td>
              <td>${d.userKeepTotalPoints}</td>
              <td>${d.totalLosePoints}</td>
              <td>${d.ratioPoints}</td>
            </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
    <!-- End Panel -->
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
              <form class="form-horizontal"  action="<%=path%>/pointsWholeSituationSetting/save"  onsubmit="return save()" method="post">
                <div class="form-group">
                  <label for="ratioPoints" class="col-sm-1 control-label form-label" style="width: 145px">积分/货币折算比<span style="color:red;margin-left: 10px">*</span></label>
                  <div class="col-sm-8">
                   <input style="width: 220px" type="text" class="form-control" name="ratioPoints" id="ratioPoints" value="">
                  </div>
                </div>
                <div class="form-group">
                  <label for="newUserInitPoints" class="col-sm-1 control-label form-label" style="width: 145px">新用户初始积分<span style="color: red;margin-left: 10px">*</span></label>
                  <div class="col-sm-8">
                   <input style="width: 220px" type="text" class="form-control" value="0" name="newUserInitPoints" id="newUserInitPoints">
                  </div>
                </div>
                <div class="form-group">
                  <label for="alarmPoints" class="col-sm-1 control-label form-label" style="width: 145px">积分报警<span style="color: red;margin-left: 10px">*</span></label>
                  <div class="col-sm-8">
                   <input style="width: 220px" type="text" class="form-control" value="100000" name="alarmPoints" id="alarmPoints">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-1 control-label form-label">处理方式<span style="color: red;margin-left: 10px">*</span></label>
                  <div class="col-sm-8">
                    <div class="radio">
                      <input type="radio" name="radio" id="radio1" value="1" checked>
                      <label for="radio1">
                        邮箱报警,人工处理
                      </label>
                      <input name="email" type="email" value="" id="email"/>
                    </div>
                    <div class="radio radio-warning">
                      <input type="radio" name="radio" id="radio2" value="2">
                      <label for="radio2">
                       自动增加积分
                      </label>
                      <input name="addPoints" value="" id="addPoints"/>
                    </div>
                    <!--积分报警处理方式-->
                    <input name="processingMode" value="1" id="processingMode" type="hidden">
                  </div>
                </div>
                <div class="form-group">
                  <label for="clearingDate" class="col-sm-1 control-label form-label">清算日期</label>
                  <div class="col-sm-8">
                    <input style="width: 220px" type="text" class="selectdate" name="clearingDate" id="clearingDate">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-1 control-label form-label">清算策略<span style="color: red;margin-left: 10px">*</span></label>
                  <div class="col-sm-8">
                    <div class="radio">
                      <input type="radio" name="strategy" id="strategy"  value="1" checked>
                      <label for="strategy">
                       用户积分不变
                      </label>
                    </div>
                    <div class="radio radio-warning">
                      <input type="radio"  name="strategy" id="strategy2" value="2">
                      <label for="strategy2">
                        完全归零
                      </label>
                    </div>
                    <div class="radio radio-warning">
                      <input type="radio"  name="strategy" id="strategy3" value="3">
                      <label for="strategy3">
                        按比例清算
                      </label>
                      <input name="clearingRatio" value="" id="clearingRatio"/>
                    </div>
                    <!--清算策略-->
                    <input name="clearingStrategy" value="1" id="clearingStrategy" type="hidden">
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
<script src="<%=path%>/TheHomePageTemplate/js/datatables/jquery.dataTables.js"></script>

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
</body>
</html>