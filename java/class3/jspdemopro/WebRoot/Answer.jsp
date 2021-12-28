<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>结果</title>
</head>

<body>
	<%
    String[] quesList = {"one","two","three","four","five"};
    String[] anList = {"B","C","A","D","C"};
    List<String> reList = new ArrayList<>();
    int isTrue = 0;
    for(String a : quesList){reList.add(request.getParameter(a));}
    for(int i=0;i<reList.size();i++){
    	String c = reList.get(i);
    %>
	第<%=i+1 %>题：
	<%
    	if(c.equals(anList[i])){
    	isTrue++;
    %>
	正确
	<% }else{%>
	错误
	<% } %>
	<br>
	<% } %>
	正确率:<%=isTrue+"/"+quesList.length %>
</body>
</html>
