<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html lang="zh-cn">
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="description" content="">
      <meta name="author" content="">
      <link rel="shortcut icon" href="">
      <title>集智-集万千智慧</title>
      <!-- Bootstrap core CSS -->
      <link href="./css/bootstrap.min.css" rel="stylesheet">
      <!-- Custom styles for this template -->
      <link href="./css/dashboard.css" rel="stylesheet">
      <!-- Just for debugging purposes. Don't actually copy this line! -->
      <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
      <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
      <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
      <![endif]-->
   </head>
   <body>
      <div class="navbar navbar-inverse navbar-fixed-top" style="background:#3f4145" role="navigation">
         <div class="container-fluid">
            <div class="navbar-header" >
                <img alt="" src="../img/logo.png">
            </div>
            <div class="navbar-collapse collapse">
               <ul class="nav navbar-nav navbar-right">
                  <li><a href="#">用户</a></li>
                  <li><a href="#">帮助</a></li>
               </ul>
               <form class="navbar-form navbar-right">
                  <input type="text" class="form-control" placeholder="搜索">
               </form>
            </div>
         </div>
      </div>
      <div class="container-fluid">
         <div class="row">
            <div class="col-sm-3 col-md-2 sidebar">
               <ul class="nav nav-sidebar nav-pills nav-stacked">
                  <li class="active"><a href="">项目</a></li>
                  <li><a href="#">开放平台</a></li>
                  <li><a href="#">设置</a></li>
               </ul>
            </div>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
               <h1 class="page-header">项目管理</h1>
               <div class="panel panel-default">
               
               <div class="panel-heading" id="project_panel_header">
                    <button type="button" class="btn btn-primary" id="create_btn">新建</button>
               </div>
                <div class="panel-body" id="create_project_panel">
                    <form>
                        <input type="text" name="name" class="form-control" placeholder="项目名称" required autofocus />
                        <input type="email" name="emailList" class="form-control" placeholder="邮件列表" required />
                        <textarea name="abstractContent" class="form-control" placeholder="项目简介" required ></textarea>
                        <button class="btn btn-primary" id="confirm" type="button">完成</button>
                        <button class="btn btn-default" id="cancel" type="button">取消</button>
                    </form>
                    <div id="warning" class="alert alert-danger"></div>
                </div>
                <div id="success_tip" class="alert alert-success">操作成功</div>
                
               <div class="table-responsive" id="table-wrap">
                  <table class="table table-hover">
                     <thead style="background:#f5f5f5">
                        <tr>
                           <th>名称</th>
                           <th>简介</th>
                           <th>创建时间</th>
                           <th>token</th>
                           <th>操作</th>
                        </tr>
                     </thead>
                     <tbody>
                        <c:forEach items="${projects.content}" var="project">
                           <tr>
                           <td><a href="/feedbacks?projectId=<c:out value="${project.id}"/>"><c:out value="${project.name}"/></a></td>
                           <td><c:out value="${project.abstractContent}"/></td>
                           <td><c:out value="${project.createdTime}"/></td>
                           <td><c:out value="${project.token}"/></td>
                           <td><a href="javascript:void(0);" onclick="delete_project(<c:out value="${project.id}"/>)"><span class="glyphicon glyphicon-remove"></span></a></td>
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
      <!-- Bootstrap core JavaScript
         ================================================== -->
      <!-- Placed at the end of the document so the pages load faster -->
      <script src="./js/jquery.1.11.0.min.js"></script>
      <script src="./js/bootstrap.min.js"></script>
      <script src="./js/docs.min.js"></script>
      
      <script>
      $(document).ready(function(){
    	  //初始化
    	  $("#create_project_panel").hide();
    	  $("#warning").hide();
    	  
    	  $("#project_panel_header").delegate('#create_btn', 'click', function(){
    		  $("#create_project_panel").show();
    	  });
    	  //点击完成按钮
    	  $("#create_project_panel").delegate("#confirm", 'click', function(){
    		  create_project();
    	  });
    	  
    	  ////点击取消按钮
          $("#create_project_panel").delegate("#cancel", 'click', function(){
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
      
      
      </script>
   </body>
</html>