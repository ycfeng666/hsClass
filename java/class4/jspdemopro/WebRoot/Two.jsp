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
    <form action="Two2.jsp" method="post">
    	汽车的颜色：<input type="color" name="color"><br>
		汽车是否安装了空调：<input type="radio" id="isTure" name="isInstall" value="true">
	    <label for="isTure">以安装空调</label>
	    <input type="radio" id="isFalse" name="isInstall" value="false">
	    <label for="isFalse">未安装空调</label><br>
	    <input type="submit" value="提交">
    </form>
  </body>
</html>
