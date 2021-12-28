package com.vote.DBHelper;
import java.sql.*;

public class MySql {
	//定义数据库驱动程序
	private static final String DBDRIVER="com.mysql.cj.jdbc.Driver";
	private static final String DBURL="jdbc:mysql://localhost:3306/vote?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=GMT%2B8";
	//数据库连接地址
	private static final String DBUSER="root";
	private static final String DBPASS="Root@123456";
	
	static{
		try {
			//1.加载驱动
			Class.forName(DBDRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//2.获取连接
	public static Connection GetConn(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return conn;
	}
	
	//6.关闭资源(后开先关)
	public static void Close(ResultSet rs,PreparedStatement ps,Connection conn){
		try {
			if(rs != null)rs.close();
			if(ps != null)ps.close();
			if(conn != null)conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
