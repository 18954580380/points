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
   <script>
       //全选全不选
       function pitchOn(obj) {
           $('input[type=checkbox]').prop('checked', $(obj).prop('checked'));
       }
       function echoData(){
           //商品id
           var ids = "";
           //商品名称
           var names="";
           //分类Id
           var topcatids="";
           //分类名称
           var topcatnames="";
           $.each($('input[name=goodsId]:checked'), function () {
               if ($(this).val() != "") {
                   ids += $(this).val() + ",";
                   names += $(this).data("name")+ ","
                   topcatids += $(this).data("topcatid")+ ","
                   topcatnames += $(this).data("topcatname")+ ","
                   console.log(topcatnames);
               }
           });
           if(ids!=""){
               ids = ids.substr(0, ids.length - 1);
               names = names.substr(0, names.length - 1);
               topcatids = topcatids.substr(0, topcatids.length - 1);
               topcatnames = topcatnames.substr(0, topcatnames.length - 1);
           }
           //写到父页面
           if(ids!==""){
              if(window.parent.document.getElementById("goodsIds").value!=""){
                  //清除页面
                  var tb=$(window.parent.document.getElementById("selectGoodsList"));
                  tb.find('tr').each(function(index){
                      if(index==0){

                      }else{
                          $(this).remove();
                      }
                  })
              }
               window.parent.document.getElementById("goodsIds").value="";
               window.parent.document.getElementById("names").value="";
               window.parent.document.getElementById("topcatids").value="";
               window.parent.document.getElementById("topcatnames").value="";
               var idsArr=ids.split(",");
               var namesArr=names.split(",");
               var topcatidsArr=topcatids.split(",");
               var topcatnamesArr= topcatnames.split(","); //定义一数组
               for (var i=0;i<idsArr.length ;i++ )
               {
                   //向父页面table追加元素
                  var parentTable= window.parent.document.getElementById("selectGoodsList");
                  $(parentTable).append("<tr><td style='display: none'>"+idsArr[i]+"</td><td>"+namesArr[i]+"</td><td>"+topcatnamesArr[i]+"</td><td><a style='cursor:pointer' onclick='deleteTr(this)'>删除</a></td></tr>");
               }
                   window.parent.document.getElementById("goodsIds").value = window.parent.document.getElementById("goodsIds").value=ids;
                   window.parent.document.getElementById("names").value = window.parent.document.getElementById("names").value=names;
                   window.parent.document.getElementById("topcatids").value = window.parent.document.getElementById("topcatids").value=topcatids;
                   window.parent.document.getElementById("topcatnames").value = window.parent.document.getElementById("topcatnames").value=topcatnames;
                   //共选择了几件商品
                   window.parent.document.getElementById("number").innerText=idsArr.length;
           }
           var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
           parent.layer.close(index);
       }

   </script>
</head>
<body>
<div class="content" style="padding-top: 20px">
    <!-- //////////////////////////////////////////////////////////////////////////// -->
    <!-- START CONTAINER -->
    <div class="container-padding">
        <div class="row btndiv">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <!-- Start right -->
                        <div class="col-md-12 col-lg-6" style="padding-left:0px">
                            <a onclick="echoData()" class="btn btn-rounded btn-default">确定</a>
                        </div>
                        <!-- End right -->
                    </div>
                </div>
            </div>
        </div>
        <!-- Start Row -->
        <div class="row">
            <!-- Start Panel -->
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-title">
                    </div>
                    <div class="panel-body table-responsive">
                        <table id="goodsList" class="table table-striped table-bordered">
                            <thead>
                            <tr>
                                <th style="background: none">
                                    <div class="checkbox checkbox-primary"  >
                                    <input id="checkbox102" type="checkbox" onclick="pitchOn(this)" >
                                    <label for="checkbox102">
                                    </label>
                                   </div>
                                </th>
                                <th>商品名称</th>
                                <th>商品所属分类</th>
                            </tr>
                            </thead>

                            <tfoot>

                            </tfoot>

                            <tbody>
                            <c:forEach var="g" items="${goodsData}">
                            <tr>
                                <td>
                                    <div class="checkbox checkbox-primary">
                                        <c:if test="${ empty goodsIds}">
                                            <input id="checkbox${g.goods_id}" type="checkbox" value="${g.goods_id}" data-name="${g.name}" data-topcatid="${g.topcatid}" data-topcatname="${g.topcatname}" name="goodsId">
                                        </c:if>
                                        <c:if test="${!empty goodsIds}">
                                              <c:set var="flag" value="0"></c:set>
                                            <c:forEach var="gid" items="${goodsIds}">
                                                <c:if test="${g.goods_id==gid}">
                                                    <c:set var="flag" value="1"></c:set>
                                                </c:if>
                                            </c:forEach>
                                            <input id="checkbox${g.goods_id}" <c:if test="${flag==1}">checked</c:if> type="checkbox" value="${g.goods_id}" data-name="${g.name}"   data-topcatid="${g.topcatid}" data-topcatname="${g.topcatname}" name="goodsId">
                                        </c:if>
                                    <label for="checkbox${g.goods_id}">
                                    </label>
                                </div>
                                </td>
                                <td>${g.name}</td>
                                <td>${g.topcatname}</td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
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



<%--<script>
    $(document).ready(function() {
        $('#goodsList').DataTable();
    } );
</script>--%>
</body>
</html>