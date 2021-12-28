<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>实验1</title>
  </head>  
  <body>
  	<form action="One-2.jsp" method="post">
  		语文：<input type="number" name="chinses" min="0" max="150"><br>
  		数学：<input type="number" name="math" min="0" max="150"><br>
  		英语：<input type="number" name="english" min="0" max="150"><br>
  		政治：<input type="number" name="political" min="0" max="100"><br>
  		物理：<input type="number" name="physical" min="0" max="100"><br>
  		体育：<input type="number" name="sports" min="0" max="100"><br>
  		<input type="submit" value="提交">
  	</form>
  </body>
</html>
