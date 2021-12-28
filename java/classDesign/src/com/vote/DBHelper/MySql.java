package com.vote.DBHelper;
import java.sql.*;

public class MySql {
	//�������ݿ���������
	private static final String DBDRIVER="com.mysql.cj.jdbc.Driver";
	private static final String DBURL="jdbc:mysql://localhost:3306/vote?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=GMT%2B8";
	//���ݿ����ӵ�ַ
	private static final String DBUSER="root";
	private static final String DBPASS="Root@123456";
	
	static{
		try {
			//1.��������
			Class.forName(DBDRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//2.��ȡ����
	public static Connection GetConn(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return conn;
	}
	
	//6.�ر���Դ(���ȹ�)
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
