<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>使用Response2</title>
  </head>
  
  <body>
    用户名:<%=session.getAttribute("user") %><br>
    密码:<%=session.getAttribute("pass") %>
  </body>
</html>
