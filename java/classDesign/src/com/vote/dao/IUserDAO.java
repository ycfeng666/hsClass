package com.vote.dao;
import com.vote.mod.userModel;;

public interface IUserDAO {
	/**
	 * �û���¼��֤
	 * @param user����vo����
	 * @return ��֤�Ĳ������ 
	 * @throws Exception
	 */
	public boolean findLogin(userModel user) throws Exception;
}
