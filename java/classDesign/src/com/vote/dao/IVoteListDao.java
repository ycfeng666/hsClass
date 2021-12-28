package com.vote.dao;

import java.util.List;

import com.vote.mod.subjectListModel;
import com.vote.mod.voteListModel;

public interface IVoteListDao {
	public List<voteListModel> findList();
	public List<subjectListModel> findSubjectListByVid(int vid);
	public boolean addVote(voteListModel vote,int type);
	public boolean delVote(int id);
	public boolean addSubject(subjectListModel subject,int type);
	public boolean delSubject(int id);
}
