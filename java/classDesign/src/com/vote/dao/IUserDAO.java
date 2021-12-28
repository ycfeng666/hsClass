package com.vote.dao;
import java.util.List;

import com.vote.mod.userModel;;

public interface IUserDAO {
	/**
	 * 登录
	 * @param 传入用户名
	 * @return 用户模型 
	 */
	public userModel findLogin(String Account,String PassWord,int type);
	
	/**
	 * 查询用户列表
	 * @param 页码,每页行数
	 * @return 用户模型数组
	 */
	public List<userModel> findUserList();
	
	/**
	 * 查询用户是否存在
	 * @param 用户名
	 * @return 真假
	 */
	public boolean findUserByAccount(String Account);
	/**
	 * 查询用户新增与修改用户
	 * @param 用户模型
	 * @return 真假
	 */
	public boolean saveUser(userModel user,int type);
	/**
	 * 删除用户
	 * @param 用户ID
	 * @return 真假
	 */
	public boolean delUser(int id);
}
