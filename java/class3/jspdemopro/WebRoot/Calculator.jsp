<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<base href="<%=basePath%>">
<title>计算器</title>
</head>

<body>
	<h1>我的计算器</h1>
	<hr>
	<form>
		第一个数：<input type="text" class="num1"><br /> 第一个数：<input
			type="text" class="num2"><br /> <select class="flag">
			<option value=0>+</option>
			<option value=1>-</option>
			<option value=2>*</option>
			<option value=3>/</option>
		</select><br /> <input type="button" value="计算" class="sub" onclick="sub()"><br>
		结果：<span id="result"></span>
	</form>
</body>
<script>
  	const getFun = (name)=>{return document.querySelector(name)},
  	num1 = getFun(".num1"),num2 = getFun(".num2"),flag = getFun(".flag"),btn = getFun(".sub"),result=getFun("#result");
  	let xml = new XMLHttpRequest();
  	function sub(){ 		
  	xml.onreadystatechange=handle;
  	let url = "CalculatorAPI?num1="+num1.value+"&num2="+num2.value+"&flag="+flag.value;
  	xml.open("get",url,true);xml.send()}  	
  	function handle(){
  		if(xml.readyState==4)if(xml.status==200)result.innerHTML = xml.responseText;
  	}
  </script>
</html>
