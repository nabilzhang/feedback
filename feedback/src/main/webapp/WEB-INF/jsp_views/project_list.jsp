<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html lang="zh-cn">
<head>
<jsp:include page="elements/common/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="elements/common/top_nav.jsp"></jsp:include>
	<div class="container-fluid">
		<jsp:include page="elements/common/left_nav.jsp"></jsp:include>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h1 class="page-header">项目管理</h1>
			<!-- table panel start -->
			<div class="panel panel-default">
				<jsp:include page="elements/project/create_project_panel.jsp"></jsp:include>
				<div class="table-responsive" id="table-wrap">
					<table class="table table-hover">
						<thead style="background: #f5f5f5">
							<tr>
								<th>名称</th>
								<th>简介</th>
								<th>创建时间</th>
								<th>代码</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${projects.content}" var="project">
								<tr>
									<td><a
										href="/feedbacks?projectId=<c:out value="${project.id}"/>">
											<c:out value="${project.name}" />
									</a></td>
									<td><c:out value="${project.abstractContent}" /></td>
									<td><c:out value="${project.createdTime}" /></td>
									<td><a href="#" class="btn btn-info codeshow"
										data-token="<c:out value="${project.token}"/>"
										data-toggle="modal" data-target="#scriptModel">查看代码</a></td>
									<td><a href="javascript:void(0);"
										onclick="delete_project(<c:out value="${project.id}"/>)"><span
											class="glyphicon glyphicon-remove"></span></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- table-responsive  end -->
			</div>
			<!-- panel end -->
		</div>
	</div>
	<div id="scriptModel" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">拷贝脚本到页面</h4>
				</div>
				<div class="modal-body">
					<pre>
<code>

&lt;link href=&quot;<span class="host">token</span>css/feedback.css&quot; rel=&quot;stylesheet&quot; type=&quot;text/css&quot; /&gt;
&lt;script src=&quot;<span class="host">token</span>js/fabric.min.js&quot;&gt;&lt;/script&gt;
&lt;script src=&quot;<span class="host">token</span>js/html2canvas.js&quot;&gt;&lt;/script&gt;
&lt;script src=&quot;<span class="host">token</span>js/feedback.js&quot;&gt;&lt;/script&gt;
&lt;script type=&quot;text/javascript&quot;&gt;
    Feedback({
        url: &#39;<span class="host">token</span>api/feedback&#39;,
        apikey: &#39;<span class="project-token">token</span>&#39;
    });
&lt;/script&gt;
</code>
</pre>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- Bootstrap core JavaScript
         ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="./js/jquery.1.11.0.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
	<script src="./js/docs.min.js"></script>
	<script>
		$(document).ready(function() {
	        //初始化
	        $("#create_project_panel").hide();
	        $("#warning").hide();

	        $("#project_panel_header").delegate('#create_btn', 'click', function() {
	            $("#create_project_panel").show();
	        });
	        //点击完成按钮
	        $("#create_project_panel").delegate("#confirm", 'click', function() {
	            create_project();
	        });

	        ////点击取消按钮
	        $("#create_project_panel").delegate("#cancel", 'click', function() {
	            $("#create_project_panel form")[0].reset();
	            $("#create_project_panel").hide();
	        });

	        //成功
	        $("#success_tip").hide();
	        //if(getQuery("success") == "true"){
	        //  $("#success_tip").show();
	        //}
	        //等一会儿要消失提示
	        //setTimeout(function(){
	        //  window.location.href="/project";
	        //  clearTimeout();
	        // },3000);

	    });
	    

	    //创建项目
	    function create_project(){
	        $("#warning").hide();
	        $.post( "/project",  $("#create_project_panel form").serialize(), function(data){
	            if(data.success){
	                window.location.href="?success=true";
	            } else {
	                $("#warning").text(data.errMsg).show();
	            }
	        });
	    }
      
      //删除项目
      function delete_project(id){
    	  $.ajax({
    		  url: "/project/"+id,
    		  type:"delete",
    	  }).done(function(){
    		  window.location.href="?success=true";		  
    	  });
      }
      //获取一个QueryString
      function getQuery(name) 
      {
         var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
         var r = window.location.search.substr(1).match(reg);
         if (r != null)
             return unescape(r[2]);
         return null;
      }
      //显示代码
      $('#scriptModel').on('shown.bs.modal', function (e) {
    	  e.preventDefault();
    	  var host = location.protocol + '//' + location.host + '/';
    	  $('span.host').text(host);
    	  $('span.project-token').text($(e.relatedTarget).data('token'));
      });
      </script>
</body>
</html>