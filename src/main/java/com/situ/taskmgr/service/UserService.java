package com.situ.taskmgr.service;


import java.util.List;

import com.github.pagehelper.PageInfo;
import com.situ.taskmgr.entity.User;

public interface UserService {
	/**
	 * 登录
	 */
	User login(User user) throws Exception;
	
	/**
	 * 添加
	 */
	int add(User user) throws Exception;
	/**
	 * 修改
	 */
	int edit(User user)throws Exception;
	/**
	 * 根据ID查询
	 */
	User getById(Integer id);
	/**
	 * 查询所有
	 */
	List<User> getAll();
	/**
	 * 分页查询 借助pagehelper
	 */
	PageInfo getByPage(Integer page,Integer limit);

	/**
	 * 修改密码的方法
	 * @param oldPassword
	 * @param newPassword
	 * @param rePassword
	 * @param id
	 * @return
	 */
	int modifyPwd(String oldPassword, String newPassword, String rePassword, Integer id)
			throws Exception;
	
}
