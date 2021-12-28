package com.vote.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vote.dao.CacheManagerImpl;
import com.vote.dao.IVoteListDao;
import com.vote.dao.VoteListImpl;
import com.vote.mod.subjectListModel;

public class SubjectListServlet extends HttpServlet {
	private static final long serialVersionUID = 222L;
	/**
     * @see 默认构造函数
     */
    public SubjectListServlet() {
        super();
    }
    /**
	 * @see Get请求
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String vid = request.getParameter("vid");
		if(vid!=null){
			try {
				response.setContentType("text/html;charset=utf-8");
				VoteListImpl dao = new VoteListImpl();
    			List<subjectListModel> list = dao.findSubjectListByVid(Integer.parseInt(vid));
				String data = JSON.toJSONString(list);
				response.getWriter().print(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			response.getWriter().print("ParamIsEmpty");
		}
    }
    /**
	 * @see Post请求
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	CacheManagerImpl cacheManagerImpl = new CacheManagerImpl();
    	if(cacheManagerImpl.isContains("AdminSession")){
    		BufferedReader bufferedReader = null;
        	StringBuilder stringBuilder = new StringBuilder();
    		InputStream in = request.getInputStream();
    		bufferedReader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
    		char[] charBuffer = new char[128];
    		int bytesRead = -1;
    		while ((bytesRead = bufferedReader.read(charBuffer)) > 0) stringBuilder.append(charBuffer, 0, bytesRead);
			subjectListModel subject = (subjectListModel) JSONObject.parseObject(stringBuilder.toString(), subjectListModel.class);
			IVoteListDao dao = new VoteListImpl();
			boolean result = false;
			if(subject.sid ==0)result = dao.addSubject(subject, 0);
			else result = dao.addSubject(subject, 1);
			if(result)response.getWriter().print("Success");
			else response.getWriter().print("Error");
    	}else response.getWriter().print("NoPermission");
    }
    /**
	 * @see Delete请求
	 */
    protected void doDelete(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
    	CacheManagerImpl cacheManagerImpl = new CacheManagerImpl();
    	if(cacheManagerImpl.isContains("AdminSession")){
    		int id = Integer.parseInt(request.getParameter("id"));
    		if(id!=0){
    			IVoteListDao dao = new VoteListImpl();
				boolean result = dao.delSubject(id);
				if(result)response.getWriter().print("Success");
				else response.getWriter().print("Error");
			}
    	}else response.getWriter().print("NoPermission");
    }
}
