<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.info.*"%>

<html>
<head>
<title>获取生日</title>
</head>

<body>
	<table border="1">
		<tr>
			<td>身份证</td>
			<td>生日</td>
		</tr>
		<% String[] arr = {"010020199601026929","010020199711126928"};
    	string str = new string();
    	for(int i = 0;i< arr.length;i++){
    	%>
		<tr>
			<td><%= arr[i] %></td>
			<td><%= str.getBirthday(arr[i]) %></td>
		</tr>
		<% } %>
	</table>
	<br>
</body>
</html>
