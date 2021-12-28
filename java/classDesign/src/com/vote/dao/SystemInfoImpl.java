package com.vote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vote.DBHelper.MySql;
import com.vote.mod.SystemInfoModel;

public class SystemInfoImpl implements ISystemInfoDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	@Override
	public SystemInfoModel getInfo() {
		SystemInfoModel info = new SystemInfoModel();
		try {
			conn = MySql.GetConn();
			ps = conn.prepareStatement("select * from (select count(*)'userCount' from user) a,(select count(*)'voteCount' from votelist)b");
			rs = ps.executeQuery();
			while(rs.next()){
				info.userCount = rs.getInt("userCount");
				info.voteCount = rs.getInt("voteCount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return info;
	}

}
