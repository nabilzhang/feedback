<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="panel-heading" id="project_panel_header">
	<button type="button" class="btn btn-primary" id="create_btn">新建</button>
</div>
<div class="panel-body" id="create_project_panel">
	<form>
		<input type="text" name="name" class="form-control" placeholder="项目名称"
			required autofocus /> <input type="email" name="emailList"
			class="form-control" placeholder="邮件列表" required />
		<textarea name="abstractContent" class="form-control"
			placeholder="项目简介" required></textarea>
		<button class="btn btn-primary" id="confirm" type="button">完成</button>
		<button class="btn btn-default" id="cancel" type="button">取消</button>
	</form>
	<div id="warning" class="alert alert-danger"></div>
</div>
<div id="success_tip" class="alert alert-success">操作成功</div>

<script>
	
</script>