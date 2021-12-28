package com.vote.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vote.dao.*;

public class FindUserByName extends HttpServlet {
	private static final long serialVersionUID = 66666L;
	public FindUserByName() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("account");
		if(account!=null){
			IUserDAO dao = new UserDAOImpI();
			boolean result = dao.findUserByAccount(account);
			if(result) response.getWriter().print("UserAlreadyExists");
			else response.getWriter().print("UserDoesNotExist");
		}else {
			response.getWriter().print("ParamIsEmpty");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().println("PleaseUseGet");
	}
}
