<%@ page language="java" import="java.util.*,com.sy5.helper.ScoreBean" pageEncoding="UTF-8"%>
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
  	<jsp:useBean id="score" class="com.sy5.helper.ScoreBean"></jsp:useBean>
  	<jsp:setProperty property="*" name="score"/>
  	学生的总成绩是:${score.CountScore()}
  	学生的平均成绩是:${score.MeanScore()}
  </body>
</html>
