package com.vote.dao;
import java.util.List;

import com.vote.mod.userModel;;

public interface IUserDAO {
	/**
	 * ��¼
	 * @param �����û���
	 * @return �û�ģ�� 
	 */
	public userModel findLogin(String Account,String PassWord,int type);
	
	/**
	 * ��ѯ�û��б�
	 * @param ҳ��,ÿҳ����
	 * @return �û�ģ������
	 */
	public List<userModel> findUserList();
	
	/**
	 * ��ѯ�û��Ƿ����
	 * @param �û���
	 * @return ���
	 */
	public boolean findUserByAccount(String Account);
	/**
	 * ��ѯ�û��������޸��û�
	 * @param �û�ģ��
	 * @return ���
	 */
	public boolean saveUser(userModel user,int type);
	/**
	 * ɾ���û�
	 * @param �û�ID
	 * @return ���
	 */
	public boolean delUser(int id);
}
