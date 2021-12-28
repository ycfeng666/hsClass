package com.vote.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.vote.dao.CacheManagerImpl;
import com.vote.dao.ISystemInfoDAO;
import com.vote.dao.SystemInfoImpl;
import com.vote.mod.SystemInfoModel;

public class SystemInfoServlet  extends HttpServlet {
	private static final long serialVersionUID = 999L;
	/**
     * @see 默认构造函数
     */
	public SystemInfoServlet() {
        super();
    }
    /**
	 * @see Get请求
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		CacheManagerImpl cacheManagerImpl = new CacheManagerImpl();
    	response.setContentType("text/html;charset=utf-8");
    	if(cacheManagerImpl.isContains("AdminSession")){
    		try {
    			ISystemInfoDAO dao = new SystemInfoImpl();
				SystemInfoModel info = dao.getInfo();
				String data = JSON.toJSONString(info);
				response.getWriter().print(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}else {
			response.getWriter().print("ParamIsEmpty");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	response.getWriter().println("PleaseUseGet");
    }
}
