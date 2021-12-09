package com.vote.proxy;
import com.vote.dao.IUserDAO;
import com.vote.dao.UserDAOImpI;
import com.vote.helper.*;
import com.vote.mod.userModel;


public class UserDAOProxy implements IUserDAO {
	private conn dbc=null;//定义数据库连接
    private IUserDAO dao=null;//定义DAO接口
    public UserDAOProxy(){
    	try {
			dbc=new conn();//实例化数据库连接
		} catch (Exception e) {
			e.printStackTrace();
		}
    	dao=new UserDAOImpI(dbc.getConnection());
    	
    }
	@Override
	public boolean findLogin(userModel user) throws Exception {
		boolean flag=false;
		try {
			flag=dao.findLogin(user);//调用真实主题
		} catch (Exception e) {
			throw e;
		}finally{
			dbc.close();
		}
		return flag;
	}
}
