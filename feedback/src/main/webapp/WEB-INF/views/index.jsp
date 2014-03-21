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
                  <li class="active"><a href="#">项目</a></li>
                  <li><a href="#">开放平台</a></li>
                  <li><a href="#">设置</a></li>
               </ul>
            </div>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
               <h1 class="page-header">项目管理</h1>
               <div class="btn-group">
                  <button type="button" class="btn">操作</button>
                  <button type="button" class="btn dropdown-toggle" data-toggle="dropdown">
                  <span class="caret"></span>
                  </button>
                  <ul class="dropdown-menu" role="menu">
                     <li><a href="#"><span class="glyphicon glyphicon-plus"></span> 新建</a></li>
                  </ul>
               </div>
               <div class="table-responsive">
                  <table class="table table-striped">
                     <thead>
                        <tr>
                           <th>编号</th>
                           <th>名称</th>
                           <th>描述</th>
                           <th>token</th>
                        </tr>
                     </thead>
                     <tbody>
                        <c:forEach items="${projects.content}" var="project">
                            <tr>
                           <td><c:out value="${project.id}"/></td>
                           <td><c:out value="${project.name}"/></td>
                           <td><c:out value="${project.abstractContent}"/></td>
                           <td><c:out value="${project.createdTime}"/></td>
                        </tr>
                        </c:forEach>
                     </tbody>
                  </table>
                  <ul class="pagination">
                        <li><a href="?pageNo=<c:out value="{projects.getNumber()}"/>">&laquo;</a></li>
                        <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
                  </ul>
               </div>
            </div>
         </div>
      </div>
      <!-- Bootstrap core JavaScript
         ================================================== -->
      <!-- Placed at the end of the document so the pages load faster -->
      <script src="./js/jquery.1.11.0.min.js"></script>
      <script src="./js/bootstrap.min.js"></script>
      <script src="./js/docs.min.js"></script>
   </body>
</html>