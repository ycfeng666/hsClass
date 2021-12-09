<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page language="java" import="com.vote.mod.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>${staticModel.adminWebName}-登录</title>
<link rel="stylesheet" type="text/css" href="css/adminLogin.css" />
<link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.css">
</head>
<body>
	<div id="bigBox">
		<h1>${staticModel.adminLoginSpan}</h1>
		<div class="inputBox">
			<form action="<%=basePath %>LoginServlet" method="post" onSubmit="return validate(this)">
				<div class="inputText">
					<i class="fa fa-user-circle"></i>
					<input type="text" placeholder="${staticModel.PleaseMailOrPhone}" name="name"/>
				</div>
				<div class="inputText">
					<i class="fa fa-key"></i>
					<input type="password" placeholder="${staticModel.PleasePassWord}" name="password"/>
				</div>
				<input type="submit" class="inputButton" value="LOGIN" />
			</form>
		</div>
	</div>
</body>
</html>