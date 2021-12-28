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
  <body bgColor="${param.bg_color}">
    <table width="${param.table_width}" border="${param.table_border}">
    	<tr><td><font size="${param.fony_size}">Hello World!!</font></td></tr>
    </table>
  </body>
</html>
