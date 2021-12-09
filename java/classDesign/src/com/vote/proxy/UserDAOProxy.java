package com.vote.proxy;
import com.vote.dao.IUserDAO;
import com.vote.dao.UserDAOImpI;
import com.vote.helper.*;
import com.vote.mod.userModel;


public class UserDAOProxy implements IUserDAO {
	private conn dbc=null;//�������ݿ�����
    private IUserDAO dao=null;//����DAO�ӿ�
    public UserDAOProxy(){
    	try {
			dbc=new conn();//ʵ�������ݿ�����
		} catch (Exception e) {
			e.printStackTrace();
		}
    	dao=new UserDAOImpI(dbc.getConnection());
    	
    }
	@Override
	public boolean findLogin(userModel user) throws Exception {
		boolean flag=false;
		try {
			flag=dao.findLogin(user);//������ʵ����
		} catch (Exception e) {
			throw e;
		}finally{
			dbc.close();
		}
		return flag;
	}
}
