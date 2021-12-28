package com.vote.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.vote.DBHelper.MySql;
import com.vote.MyHelper.MD5Util;
import com.vote.mod.userModel;;

public class UserDAOImpI implements IUserDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	// 管理员与用户登录
	@Override
	public userModel findLogin(String account,String password,int type){
		userModel user = null;
		try {
			String sqlString ="";
			if(type == 1)sqlString = "select * from user where account=? and password =? and isAdmin = 1";
			else sqlString = "select * from user where account=? and password =?";
			conn = MySql.GetConn();
			ps = conn.prepareStatement(sqlString);
			ps.setString(1, account);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while(rs.next()){
				user = new userModel();
				user.userid = rs.getInt("userid");
				user.account = rs.getString("account");
				user.name = rs.getString("name");
				user.isAdmin = rs.getInt("isAdmin");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySql.Close(rs, ps, conn);
		}
		return user;
	}
	// 查询用户列表
	// 需鉴权
	@Override
	public List<userModel> findUserList() {
		List<userModel> list = new ArrayList<userModel>();
		try {
			conn = MySql.GetConn();
			ps = conn.prepareStatement("select * from user");
			rs = ps.executeQuery();
			while(rs.next()){
				userModel user = new userModel();
				user.userid = rs.getInt("userid");
				user.account = rs.getString("account");
				user.name = rs.getString("name");
				user.isAdmin = rs.getInt("isAdmin");
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public boolean findUserByAccount(String Account) {
		String sqlString ="select count(*) 'count' from user where account=?";
		boolean result = false;
		try {
			conn = MySql.GetConn();
			ps = conn.prepareStatement(sqlString);
			ps.setString(1, Account);
			rs = ps.executeQuery();
			while (rs.next()) {
				if(rs.getInt("count")>0) result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			MySql.Close(rs, ps, conn);
		}	
		return result;
	}
	@Override
	public boolean saveUser(userModel user,int type) {
		String sqlString = "";
		if(type == 0)sqlString = "insert into user(account,password,isAdmin,name) values (?,?,0,?)";
		else if(type == 1) sqlString="update user set account=?,password=?,name=? where userid=?";
		try {
			conn = MySql.GetConn();
			ps = conn.prepareStatement(sqlString);
			ps.setString(1, user.account);
			ps.setString(2, MD5Util.getMD5Str(user.password));
			ps.setString(3, user.name);
			if(type == 1) ps.setInt(4, user.userid);
			int i = ps.executeUpdate();
			if(i>0)return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			MySql.Close(null, ps, conn);
		}
		return false;
	}
	@Override
	public boolean delUser(int id) {
		String sqlString = "delete from user where userid=?";
		try {
			conn = MySql.GetConn();
			ps = conn.prepareStatement(sqlString);
			ps.setInt(1, id);
			int i = ps.executeUpdate();
			if(i>0)return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			MySql.Close(null, ps, conn);
		}
		return false;
	}
}
