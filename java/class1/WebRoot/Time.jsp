<%@page import="org.apache.taglibs.standard.tei.ForEachTEI"%>
<%@page import="javafx.scene.input.DataFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html lang="UTF-8">
<head>
<title>颜色</title>
<meta http-equiv="refresh" content="1">
</head>

<body>
	<% 
  	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String time = sdf.format(new Date());
  %>
	<%= time %>
</body>
<script>
  </script>
</html>
