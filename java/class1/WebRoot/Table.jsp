<%@page import="org.apache.taglibs.standard.tei.ForEachTEI"%>
<%@page import="javafx.scene.input.DataFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.Info.*"%>

<html lang="UTF-8">
<head>
<title>表格</title>
</head>

<body>
	<%
  ArrayList<Object> arr = new ArrayList<Object>();
	arr.add(new Student("001","李白","男","01",723.0));
	arr.add(new Student("002","孟浩然","男","02",789.0));
	arr.add(new Student("003","杨玉环","女","03",600.0));
   %>
	<table border="1">
		<tr>
			<td>学号</td>
			<td>姓名</td>
			<td>性别</td>
			<td>班级</td>
			<td>成绩</td>
		</tr>
		<% for(int i = 0;i< arr.size();i++){
  		Student stu = (Student)arr.get(i);
  	%>
		<tr>
			<td><%= stu.getSon() %></td>
			<td><%= stu.getName() %></td>
			<td><%= stu.getSex() %></td>
			<td><%= stu.getCla() %></td>
			<td><%= stu.getGra() %></td>
		</tr>
		<%}%>
	</table>
</body>
<script>
  </script>
</html>
