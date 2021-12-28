<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>实验3</title>
  </head>  
  <body>
    <form action="Three-2.jsp" method="post">
    	请输入要计算的阶数：<input type="number" name="num" min="1" max="9"><br>
    	<input type="submit" value="提交">
    </form>
  </body>
</html>
