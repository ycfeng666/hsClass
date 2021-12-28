<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>实验2</title>
  </head>  
  <body>
    <form action="Two-2.jsp" method="post">
  		背景颜色：<input type="color" name="bg_color"><br>
  		字号：<input type="number" name="fony_size" min="12" max="100"><br>
  		表格宽度：<input type="number" name="table_width" min="100" max="100"><br>
  		表格边框大小：<input type="number" name="table_border" min="1" max="100"><br>
  		<input type="submit" value="提交">
  	</form>
  </body>
</html>
