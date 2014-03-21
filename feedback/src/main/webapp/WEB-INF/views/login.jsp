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
      <link href="../css/bootstrap.min.css" rel="stylesheet">
      <link href="../css/bootstrap-theme.min.css" rel="stylesheet">
      <!-- Custom styles for this template -->
      <link href="../css/login.css" rel="stylesheet">
   </head>
   <body>
      <div class="container form-signin-container">
         <h2 class="form-signin-heading center">集智-集万千智慧</h2>
         <!-- Nav tabs -->
         <ul class="nav nav-tabs" id="login_tab">
            <li class="active"><a href="#login" data-toggle="tab">登录</a></li>
            <li><a href="#signup" data-toggle="tab">注册</a></li>
         </ul>
         <div class="tab-content">
            <div class="tab-pane fade in active" id="login">
                <form class="form-signin" role="form">
                    <input type="email" name="email" class="form-control" placeholder="邮箱" required autofocus />
                    <input type="password" name="password" class="form-control" placeholder="密码" required>
                    <label class="checkbox"><input type="checkbox" value="remember-me"> 记住我</label>
                    <button class="btn btn-lg btn-primary btn-block" type="button" onclick="login()">登陆</button>
                 </form>
            </div>
            <div class="tab-pane fade" id="signup">
                <form class="form-signup" role="form">
                    <input type="email" name = "email" class="form-control" placeholder="邮箱" required autofocus />
                    <input type="password" name = "password" class="form-control" placeholder="密码" required/>
                    <input type="password" name = "rePassword" class="form-control" placeholder="重复密码" required>
                    <button class="btn btn-lg btn-primary btn-block" type="button" onclick="register()">注册</button>
                 </form>
            </div>
            <div id="warning" class="alert alert-danger">
                
            </div>
         </div>
      </div>
      <!-- /container -->
      <!-- Bootstrap core JavaScript
         ================================================== -->
      <!-- Placed at the end of the document so the pages load faster -->
      <script src="../js/jquery.1.11.0.min.js"></script>
      <script src="../js/bootstrap.min.js"></script>
      <script>
          $(document).ready(function () {
            $('#login_tab a:last').tab('show');
            $("#warning").hide();
          });
          //注册
          function register(){
        	  $("#warning").hide();
              $.post( "/user/register", $(".form-signup" ).serialize(), function(data){
                  if(data.success){
                      window.location.href="";
                  } else {
                	  $("#warning").text(data.errMsg).show();
                  }
              } );
          }
          
          //登录
          function login(){
        	  $("#warning").hide();
              $.post( "/user/logon", $( ".form-signin" ).serialize(), function(data){
                  if(data.success){
                      window.location.href="/index";
                  } else {
                	  $("#warning").text(data.errMsg).show();
                  }
              } );
          }
        </script>
   </body>
</html>