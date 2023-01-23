package com.situ.taskmgr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.taskmgr.entity.User;
import com.situ.taskmgr.mapper.UserMapper;
import com.situ.taskmgr.service.UserService;
import com.situ.taskmgr.util.MD5Util;

/**
 * 
 * @author kwd
 * 1.需要添加一个@service注解
    *  实现service层的接口
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User login(User user) throws Exception {
		// 1. 验证参数  账号和密码
		// 账号
		if (user.getUsername().length() < 3 ||
				user.getUsername().length() > 16) {
			// 帐号的格式不对
			throw new Exception("账号必须是3~16位的字符串！");
		}
		// 密码
		if (user.getPassword().length() < 3 ||
				user.getPassword().length() > 16) {
			// 密码样式不对
			throw new Exception("密码必须是3~16位的字符串！");
		}
		
		// 验证账号是否存在
		User sUser = userMapper.selectByUsername(user.getUsername());
		if (sUser == null) {
			throw new Exception("账号不存在！！！");
		}
		
		// 密码进行MD5加密
		String md5Pwd = MD5Util.getMD5(user.getPassword());
		System.out.println(md5Pwd);
		
		if ( !sUser.getPassword().equals(md5Pwd)) {
			throw new Exception("密码不正确");
		}
		
		// 用户状态
		if (sUser.getStatus() == 1) {
			throw new Exception("用户被禁用，请联系管理员！");
		}
		
		// 返回数据库查询到的用户信息
		return sUser;
	}


	@Override
	public int add(User user) throws Exception {
		// 添加用户： 用户在添加界面中输入账号，密码，邮箱， 电话， 姓名，角色
		// 账号
		if (user.getUsername().length() < 3 ||
				user.getUsername().length() > 16) {
			// 帐号的格式不对
			throw new Exception("账号必须是3~16位的字符串！");
		}
		// 密码
		if (user.getPassword().length() < 3 ||
				user.getPassword().length() > 16) {
			// 密码样式不对
			throw new Exception("密码必须是3~16位的字符串！");
		}
		
		// 电话
		if (user.getPhone().length() != 11) {
			throw new Exception("电话号码必须是11位的！");
		}
		// 邮箱
		if (user.getEmail().length() < 6 ||
				user.getEmail().length() > 64) {
			throw new Exception("邮箱格式不对！！");
		}
		// 姓名
		if (user.getRealname().length() < 2 ||
				user.getRealname().length() > 16) {
			throw new Exception("姓名必须是2~16位的字符串！");
		}
		
		// 用户名是否重复
		User sUser = userMapper.selectByUsername(user.getUsername());
		if ( sUser != null) {
			throw new Exception("账号已经存在！");
		}
		
		// 密码MD5加密
		user.setPassword(MD5Util.getMD5(user.getPassword()));
		
		// 写入数据库
		return userMapper.insert(user);
	}


	@Override
	public int edit(User user) throws Exception {
		// 电话
		if (user.getPhone()!=null && user.getPhone().length() != 11) {
			throw new Exception("电话号码必须是11位的！");
		}
		// 邮箱
		if (user.getEmail()!=null && (user.getEmail().length() < 6 ||
				user.getEmail().length() > 64)) {
			throw new Exception("邮箱格式不对！！");
		}
		// 姓名
		if (user.getRealname()!=null && (user.getRealname().length() < 2 ||
				user.getRealname().length() > 16)) {
			throw new Exception("姓名必须是2~16位的字符串！");
		}
		
		return userMapper.update(user);
	}


	@Override
	public User getById(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.selectById(id);
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userMapper.select();
	}

	@Override
	public PageInfo getByPage(Integer page, Integer limit) {
		//查询的时分页 借助pagehelper
		PageHelper.startPage(page,limit);
		List<User> list = userMapper.select();  //变成分页查询
		
		return new PageInfo<>(list);
	}
	
	
}
