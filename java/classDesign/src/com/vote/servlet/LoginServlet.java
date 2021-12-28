package com.vote.servlet;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.util.Streams;

import com.vote.mod.*;
import com.vote.dao.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vote.MyHelper.*;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 31415926L;
	/**
     * @see 默认构造函数
     */
	public LoginServlet() {
        super();
    }
	/**
	 * @see Get请求
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CacheManagerImpl cacheManagerImpl = new CacheManagerImpl();
		String type = request.getParameter("type");
		response.setContentType("text/html;charset=utf-8");
		userModel user = null;
		if(type != null){
			if(type.equals("0")){
				if (cacheManagerImpl.isContains("AdminSession"))user = (userModel) cacheManagerImpl.getCacheDataByKey("AdminSession");
				response.getWriter().print(JSON.toJSONString(user));
			}else if(type.equals("1")){
				if(cacheManagerImpl.isContains("AdminSession"))user = (userModel) cacheManagerImpl.getCacheDataByKey("AdminSession");
				else if(cacheManagerImpl.isContains("UserSession"))user = (userModel) cacheManagerImpl.getCacheDataByKey("UserSession");
				response.getWriter().print(JSON.toJSONString(user));
			}
		}
	}

	/**
	 * @see Post请求
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputStream in = request.getInputStream();
		String Json = Streams.asString(in);
		loginParams login = (loginParams) JSONObject.parseObject(Json,loginParams.class);
		CacheManagerImpl cacheManagerImpl = new CacheManagerImpl();
		IUserDAO dao = new UserDAOImpI();
		PrintWriter out = response.getWriter();
		if(login == null){
		 out.print("ParamIsEmpty");
		}else {
			if(login.type == 0){
				int adminOrUser = 1;
				if (login.role =="admin") adminOrUser = 1;
				else adminOrUser = 2;
				userModel user = dao.findLogin(login.name,MD5Util.getMD5Str(login.password),adminOrUser);
				if(user != null){
	        	  if(user.isAdmin == 1)cacheManagerImpl.putCache("AdminSession", user, 10 * 1000L);// 写入管理员缓存
	    		  cacheManagerImpl.putCache("UserSession", user, 10 * 1000L);
	    		  out.print("Success");
				}else out.print("UserIsEmpty");}
		    else if(login.type == 1){
		    	if(login.role =="admin")cacheManagerImpl.clearByKey("AdminSession");
		    	else {
		    		cacheManagerImpl.clearByKey("AdminSession");
		    		cacheManagerImpl.clearByKey("UserSession");
		    	}
	    	  out.print("LoginOutSuccess");
		    }
		    else {
		    	out.print("Unknown");
		    }
		}
		out.flush();out.close();
	}
}
