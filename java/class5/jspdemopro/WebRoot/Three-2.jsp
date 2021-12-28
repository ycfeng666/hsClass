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
    <%
    int num = Integer.parseInt(request.getParameter("num"));
    for(int i=1;i<num+1;i++){
    	pageContext.setAttribute("i", i);
    	for(int j=1;j<=i;j++){
    		pageContext.setAttribute("j", j);
    %>
    		${j}*${i}=${j*i}
    <%} %>
    <br>
    <%} %>
  </body>
</html>
