package com.vote.servlet;

import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vote.mod.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vote.dao.*;

public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 233333L;
    /**
     * @see 默认构造函数
     */
    public UserListServlet() {
        super();
    }
	/**
	 * @see Get请求
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CacheManagerImpl cacheManagerImpl = new CacheManagerImpl();
		response.setContentType("text/html;charset=utf-8");
		if(cacheManagerImpl.isContains("AdminSession")){
			try {
				IUserDAO dao = new UserDAOImpI();
				List<userModel> list = dao.findUserList();
				String data = JSON.toJSONString(list);
				response.getWriter().print(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else response.getWriter().print("NoPermission");
	}
	/**
	 * @see Post请求
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader bufferedReader = null;
    	StringBuilder stringBuilder = new StringBuilder();
		InputStream in = request.getInputStream();
		bufferedReader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
		char[] charBuffer = new char[128];
		int bytesRead = -1;
		while ((bytesRead = bufferedReader.read(charBuffer)) > 0) stringBuilder.append(charBuffer, 0, bytesRead);
		userModel user = (userModel) JSONObject.parseObject(stringBuilder.toString(),userModel.class);
		IUserDAO dao = new UserDAOImpI();
		boolean UserIsHave = dao.findUserByAccount(user.account);
		boolean result = false;
		if(user.userid == 0){
			if(!UserIsHave)result= dao.saveUser(user,0);
			else response.getWriter().print("UserIsHave");
		}
		else{
			CacheManagerImpl cacheManagerImpl = new CacheManagerImpl();
			if(cacheManagerImpl.isContains("AdminSession"))result = dao.saveUser(user,1);
			else response.getWriter().print("NoPermission");
		} 
		if(result)response.getWriter().print("Success");
		else response.getWriter().print("Error");
	}
	/**
	 * @see Delete请求
	 */
	protected void doDelete(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		CacheManagerImpl cacheManagerImpl = new CacheManagerImpl();
		if(cacheManagerImpl.isContains("AdminSession")){
			int id = Integer.parseInt(request.getParameter("id"));
			if(id!=0){
				IUserDAO dao = new UserDAOImpI();
				boolean result = dao.delUser(id);
				if(result)response.getWriter().print("Success");
				else response.getWriter().print("Error");
			}
		}else response.getWriter().print("NoPermission");
	}
}
