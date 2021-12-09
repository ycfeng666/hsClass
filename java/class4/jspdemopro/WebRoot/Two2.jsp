<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>实验2-1</title>
  </head>  
  <body>
    <jsp:useBean id="car" class="com.sy4.helper.Car"></jsp:useBean>
    <jsp:setProperty property="color" name="car" param="color"/>
    <jsp:setProperty property="isInstall" name="car" param="isInstall"/>
   	 汽车颜色是<jsp:getProperty property="color" name="car"/><br>
   	 汽车安装了空调吗?<jsp:getProperty property="isInstall" name="car"/> 	 
  </body>
</html>
