<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>答题</title>
</head>

<body>
	<form action="Answer.jsp" method="post">
		<table>
			<tr>
				<td>学号：</td>
				<td><input type="text" name="xuehao"></td>
				<td>姓名：</td>
				<td><input type="text" name="name"></td>
			</tr>
		</table>
		<p>单项选择题（每小题2分,总分10分）</p>
		<p>1.下面那个不是JSP本身已加载的基本类？
		<p>
			<br> <input type="radio" name="one" value="A">
			A.java.lang.*;<br> <input type="radio" name="one" value="B">
			B.java.io.*;<br> <input type="radio" name="one" value="C">
			C.javax.servlet.*;<br> <input type="radio" name="one" value="D">
			D.javax.servlet.jsp.*;<br>
		<p>2.对于预定义说法错误的是</p>
		<input type="radio" name="two" value="A">
		A.一次可声明多个变量和方法，只要以“;”结尾就行<br> <input type="radio" name="two"
			value="B"> B.一个声明仅在一个页面中有效<br> <input type="radio"
			name="two" value="C"> C.声明的变量将作为局部变量<br> <input
			type="radio" name="two" value="D"> D.在预定义中声明的变量将在JSP页面初始化时初始化<br>
		<p>3.不能在不同用户之间共享数据的方法是：</p>
		<input type="radio" name="three" value="A"> A.通过cookie<br>
		<input type="radio" name="three" value="B"> B.利用文件系统<br>
		<input type="radio" name="three" value="C"> C.利用数据库<br> <input
			type="radio" name="three" value="D"> D.通过ServletContext对象<br>
		<p>4.Page指令用于定义JSP文件中的全局属性，下列关于该指令用法的描述不正确的是</p>
		<input type="radio" name="four" value="A"> A.page作用于整个JSP页面<br>
		<input type="radio" name="four" value="B">
		B.可以在一个页面中使用多个page指令。<br> <input type="radio" name="four"
			value="C"> C.为增加程序的可读性，建议将page指令放在jsp文件的开头，但不是必须的.<br> <input
			type="radio" name="four" value="D"> D.page指令中属性只能出现一次<br>
		<p>5.下面关于jsp编译指令说法错误的是：</p>
		<input type="radio" name="five" value="A">
		A.编译指令用于从jsp发送一个信息到容器上<br> <input type="radio" name="five"
			value="B"> B.编译指令用来设置全局变量、声明类要实现的方法和输出内容的类型等<br> <input
			type="radio" name="five" value="C"> C.编译指令向客户产生任何输出<br>
		<input type="radio" name="five" value="D">
		D.编译指令所有的指令都在整个jsp页面内有效<br> <input type="submit" value="考试完成">
		<input type="reset" value="重新答题">
	</form>
</body>
</html>
