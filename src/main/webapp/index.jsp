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
</head>
 <frameset cols="260px,*" border="0" name="showlayout">
	<frame src="<%=path%>/menu/iframe" border="0">
	<frameset rows="" frameborder="yes" framespacing="1">
		<frame src="<%=path%>/pointsItem/selectAll"  name="showframe" >
	</frameset>
</frameset>
<body style="background: #3d464d">
<script type="text/javascript" src="<%=path%>/TheHomePageTemplate/js/jquery.min.js"></script>
<script src="<%=path%>/TheHomePageTemplate/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/TheHomePageTemplate/js/plugins.js"></script>
<script type="text/javascript" src="<%=path%>/TheHomePageTemplate/js/bootstrap-select/bootstrap-select.js"></script>
<script type="text/javascript" src="<%=path%>/TheHomePageTemplate/js/bootstrap-toggle/bootstrap-toggle.min.js"></script>
<script type="text/javascript" src="<%=path%>/TheHomePageTemplate/js/flot-chart/flot-chart.js"></script>
<script type="text/javascript" src="<%=path%>/TheHomePageTemplate/js/flot-chart/flot-chart-time.js"></script>
<script type="text/javascript" src="<%=path%>/TheHomePageTemplate/js/flot-chart/flot-chart-stack.js"></script>
<script type="text/javascript" src="<%=path%>/TheHomePageTemplate/js/flot-chart/flot-chart-pie.js"></script>
<script type="text/javascript" src="<%=path%>/TheHomePageTemplate/js/flot-chart/flot-chart-plugin.js"></script>
<script type="text/javascript" src="<%=path%>/TheHomePageTemplate/js/easypiechart/easypiechart.js"></script>
<script type="text/javascript" src="<%=path%>/TheHomePageTemplate/js/easypiechart/easypiechart-plugin.js"></script>
<script type="text/javascript" src="<%=path%>/TheHomePageTemplate/js/sparkline/sparkline.js"></script>
<script type="text/javascript" src="<%=path%>/TheHomePageTemplate/js/sparkline/sparkline-plugin.js"></script>
<script src="<%=path%>/TheHomePageTemplate/js/rickshaw/d3.v3.js"></script>
<script src="<%=path%>/TheHomePageTemplate/js/rickshaw/rickshaw.js"></script>
<script src="<%=path%>/TheHomePageTemplate/js/rickshaw/rickshaw-plugin.js"></script>
<script type="text/javascript" src="<%=path%>/TheHomePageTemplate/js/moment/moment.min.js"></script>

</body>
</html>
