<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html lang="zh-cn">
<head>
<jsp:include page="elements/common/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="elements/common/top_nav.jsp"></jsp:include>
	<div class="container-fluid">
		<jsp:include page="elements/common/left_nav.jsp"></jsp:include>
		<!-- table panel start -->
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<a type="button" class="btn btn-link pull-right" href="/project">返回项目</a>
			<h1 class="page-header">反馈列表</h1>
			<!-- table-responsive  start -->
			<div class="table-responsive" id="table-wrap">
				<table class="table table-hover table-striped">
					<thead>
						<tr>
							<th>标题</th>
							<th>截图</th>
							<th>用户</th>
							<th>邮箱</th>
							<th>评论内容</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="feedback">
							<tr>
								<td><c:out value="${feedback.title}" /></td>
								<td><a data-lightbox="roadtrip"
									href="<c:out value="${feedback.image}"/>"> <img
										style="width: 10em; heigth: 8em;"
										src="<c:out value="${feedback.image}"/>" /></a></td>
								<td><c:out value="${feedback.nick}" /></td>
								<td><c:out value="${feedback.email}" /></td>
								<td><c:out value="${feedback.content}" /></td>
								<!-- <td><fmt:formatDate value="${feedback.createdTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td> -->
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<ul class="pagination">
				</ul>
			</div>
			<!-- table-responsive  end -->
		</div>
		<!-- table panel end -->
	</div>
	<!-- Bootstrap core JavaScript
         ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="./js/jquery.1.11.0.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/lightbox-2.6.min.js"></script>

	<script>
		$(document).ready(
				function() {
					//初始化
					$("#create_project_panel").hide();
					$("#warning").hide();

					$("#project_panel_header").delegate('#create_btn', 'click',
							function() {
								$("#create_project_panel").show();
							});
					//点击完成按钮
					$("#create_project_panel").delegate("#confirm", 'click',
							function() {
								create_project();
							});

					////点击取消按钮
					$("#create_project_panel").delegate("#cancel", 'click',
							function() {
								$("#create_project_panel form")[0].reset();
								$("#create_project_panel").hide();
							});
				});
	</script>
</body>
</html>