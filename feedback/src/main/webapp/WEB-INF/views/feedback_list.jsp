<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
      <link href="./css/lightbox.css" rel="stylesheet" />
      <!-- Just for debugging purposes. Don't actually copy this line! -->
      <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
      <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
      <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
      <![endif]-->
   </head>
   <body>
      <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
         <div class="container-fluid">
            <div class="navbar-header">
               <a class="navbar-brand" href="/index.html">集智-集万千智慧</a>
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
                  <li class="active"><a href="/project">项目</a></li>
                  <li><a href="#">开放平台</a></li>
                  <li><a href="#">设置</a></li>
               </ul>
            </div>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
               <a type="button" class="btn btn-link pull-right" href="/project">返回项目</a>
               <h1 class="page-header">反馈列表</h1>
               
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
                           <td><c:out value="${feedback.title}"/></td>
                           <td><a data-lightbox="roadtrip"  href="<c:out value="${feedback.image}"/>">
                            <img style="width:10em;heigth:8em;" src="<c:out value="${feedback.image}"/>"/></a>
                            </td>
                           <td><c:out value="${feedback.nick}"/></td>
                           <td><c:out value="${feedback.email}"/></td>
                           <td><c:out value="${feedback.content}"/></td>
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
            <!-- panel end -->
         </div>
      </div>
      <!-- Bootstrap core JavaScript
         ================================================== -->
      <!-- Placed at the end of the document so the pages load faster -->
      <script src="./js/jquery.1.11.0.min.js"></script>
      <script src="./js/bootstrap.min.js"></script>
      <script src="./js/docs.min.js"></script>
      <script src="./js/lightbox-2.6.min.js"></script>
      
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
      });
      
      //创建项目
      function create_project(){
    	  $("#warning").hide();
    	  $.post( "/project",  $("#create_project_panel form").serialize(), function(data){
    		  if(data.success){
    			  window.location.href="";
    		  } else {
    			  $("#warning").text(data.errMsg).show();
    		  }
    	  });
      }
      
      
      </script>
   </body>
</html>