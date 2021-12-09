package com.vote.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vote.mod.userModel;
import com.vote.proxy.*;



public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
     * @see HttpServlet#HttpServlet()
     */
	public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		if(name==null||"".equals(name)){
			System.out.println("�û�������Ϊ��");
			return;
		}	 
		else if(password==null||"".equals(password)){
			System.out.println("���벻��Ϊ��");
			return;
		}
		else {
			userModel user=new userModel();
			user.setName(name);
			user.setPassword(password);
			UserDAOProxy proxy = new UserDAOProxy();		
			try {		
				if(proxy.findLogin(user)){
					System.out.println("�û���¼�ɹ�����ӭ"+user.getName()+"���٣�");	
				}else {
					System.out.println("�û���¼ʧ�ܣ�������û���������");
				}						
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("/Admin/index.jsp").forward(request,response);//��ת
	}		
}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
	}
}
