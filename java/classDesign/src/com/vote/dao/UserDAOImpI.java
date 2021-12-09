package com.vote.dao;
import java.sql.*;

import com.vote.mod.userModel;;

public class UserDAOImpI implements IUserDAO {
	private Connection conn=null;//�������ݿ����Ӷ��� 
	private PreparedStatement pstmt=null;//�������ݿ��������
	public UserDAOImpI(Connection conn){ //�������ݿ�����
		this.conn=conn;
	}
	@Override
	public boolean findLogin(userModel user) throws Exception {
		boolean flag=false;
		try {
			String sql="select name from user where name='"+user.getName()+"' and password='"+user.getPassword()+"'";
			pstmt=conn.prepareStatement(sql);//ʵ��������
			pstmt.setString(1,user.getName());
		    pstmt.setString(2, user.getPassword());
		    ResultSet rSet=pstmt.executeQuery();//ȡ�ý�� 
		    if(rSet.next()){
		    	user.setName(rSet.getString(1));//ȡ���û���
		    	flag=true; 
		    } 
		} catch (Exception e) {
			throw e;
		}finally{
			//�رղ���
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (Exception e) {
					throw e;				
			}		
		}
			
		}
		return flag;
	}
}
