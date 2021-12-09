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
    <jsp:useBean id="user" class="com.sy4.helper.User"></jsp:useBean>
    <jsp:setProperty property="name" name="user" value="Anna"/>
 	我的名字是:<jsp:getProperty property="name" name="user"/>
  </body>
</html>
