<%@ page language="java" errorPage="Error.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.info.*"%>
<html>
<head>
<title>计算4/0</title>
</head>
<body>
	<% string str = new string(); %>
	<h3>
		4除0=<%= str.fun(4,0) %></h3>
</body>
</html>