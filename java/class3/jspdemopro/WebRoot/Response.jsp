<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>使用Response</title>
</head>

<body>
	<%
     session.setAttribute("user", "李明");
     session.setAttribute("pass", "123456");
     response.sendRedirect("Response2.jsp");
      %>
</body>
</html>
