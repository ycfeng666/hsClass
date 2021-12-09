package com.sy3.utils;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class CalculatorAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			String _num1 = request.getParameter("num1");
			String _num2 = request.getParameter("num2");
			String flag=request.getParameter("flag");		
			int num1=0;int num2=0;int result = 0;	
			num1=Integer.parseInt(_num1);
			num2=Integer.parseInt(_num2);			
			switch (flag) {
			case "0":result = num1+num2;break;
			case "1":result = num1-num2;break;
			case "2":result = num1*num2;break;
			case "3":if (num1>0) result = num1/num2;break;}
			PrintWriter out = response.getWriter();
			out.print(result);out.flush();out.close();
		} catch (Exception e) {response.getWriter().println(e.toString());}		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   doGet(request,response);
	}
}
