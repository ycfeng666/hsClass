package com.vote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vote.DBHelper.MySql;
import com.vote.mod.subjectListModel;
import com.vote.mod.voteListModel;

public class VoteListImpl implements IVoteListDao {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	@Override
	public List<voteListModel> findList() {
		List<voteListModel> list = new ArrayList<voteListModel>();
		try {
			conn = MySql.GetConn();
			ps = conn.prepareStatement("SELECT v.*,u.name FROM votelist v,user u where v.createUser=u.userid");
			rs = ps.executeQuery();
			while(rs.next()){
				voteListModel vote = new voteListModel();
				vote.id = rs.getInt("id");
				vote.title = rs.getString("title");
				vote.releaseTime = rs.getString("releaseTime");
				vote.endTime = rs.getString("endTime");
				vote.createUser = rs.getInt("createUser");
				vote.createUserName = rs.getString("name");
				list.add(vote);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<subjectListModel> findSubjectListByVid(int vid) {
		List<subjectListModel> list = new ArrayList<subjectListModel>();
		try {
			conn = MySql.GetConn();
			ps = conn.prepareStatement("select * from subjectlist where vid =?");
			ps.setInt(1,vid);
			rs = ps.executeQuery();
			while(rs.next()){
				subjectListModel sub = new subjectListModel();
				sub.sid = rs.getInt("sid");
				sub.vid = rs.getInt("vid");
				sub.subject = rs.getString("subject");
				sub.obt1 = rs.getString("obt1");
				sub.obt2 = rs.getString("obt2");
				sub.obt3 = rs.getString("obt3");
				sub.obt4 = rs.getString("obt4");
				sub.remarks = rs.getString("remarks");
				list.add(sub);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public boolean addVote(voteListModel vote,int type) {
		String sqlString = "";
		if(type == 0)sqlString = "insert into votelist(title,releaseTime,endTime,createUser) values (?,?,?,?)";
		else if(type == 1) sqlString="update votelist set title=?,releaseTime=?,endTime=?,createUser=? where id=?";
		try {
			conn = MySql.GetConn();
			ps = conn.prepareStatement(sqlString);
			ps.setString(1, vote.title);
			ps.setString(2, vote.releaseTime);
			ps.setString(3, vote.endTime);
			ps.setInt(4, vote.createUser);
			if (type == 1) ps.setInt(5, vote.id);
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
	public boolean delVote(int id) {
		String sqlString = "delete from votelist where id=?";
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
	@Override
	public boolean addSubject(subjectListModel subject, int type) {
		String sqlString = "";
		if(type == 0)sqlString = "insert into subjectlist(subject,obt1,obt2,obt3,obt4,remarks,vid) values (?,?,?,?,?,?,?)";
		else if(type == 1) sqlString="update subjectlist set subject=?,obt1=?,obt2=?,obt3=?,obt4=?,remarks=? where sid=?";
		try {
			conn = MySql.GetConn();
			ps = conn.prepareStatement(sqlString);
			ps.setString(1, subject.subject);
			ps.setString(2, subject.obt1);
			ps.setString(3, subject.obt2);
			ps.setString(4, subject.obt3);
			ps.setString(5, subject.obt4);
			ps.setString(6, subject.remarks);
			if(type == 0)ps.setInt(7, subject.vid);	
			if(type == 1)ps.setInt(7, subject.sid);
			int i = ps.executeUpdate();
			if(i>0)return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			MySql.Close(null, ps, conn);
		}
		return false;
	}
	@Override
	public boolean delSubject(int id) {
		String sqlString = "delete from subjectlist where sid=?";
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
