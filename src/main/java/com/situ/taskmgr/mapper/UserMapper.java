package com.situ.taskmgr.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.situ.taskmgr.entity.User;

/**
 * 
 * @author kwd
 *	添加一个注解 @maper
 *	添加访问数据库的方法 CRUD
 */
@Mapper
public interface UserMapper {
	/**
	 * 插入用户
	*/
	@Insert({
		"insert into user",
		"(username, password, phone, email, realname, role)",
		"value(#{username}, #{password}, #{phone}, #{email}, #{realname}, #{role})",
	})
	int insert(User user);
	
	/**
	 * 修改用户
	*/
	@Update({
		"<script>",
		"update user",
		"<set>",
		"<if test='password!=null and password.length>0'>password=#{password},</if>",
		"<if test='phone!=null and phone.length>0'>phone=#{phone},</if>",
		"<if test='email!=null and email.length>0'>email=#{email},</if>",
		"<if test='realname!=null and realname.length>0'>realname=#{realname},</if>",
		"<if test='role!=null'>role=#{role},</if>",
		"<if test='status!=null'>status=#{status},</if>",
		"</set>",
		"where id=#{id}",
		"</script>",
	})
	int update(User user);
	
	/**
	 * 查询用户 根据用户名
 	*/
	@Select({
		"select * from user",
		"where username = #{username}",
	})
	User selectByUsername(String username);
	
	@Select({
		"select * from user",
		"where id = #{id}",
	})
	User selectById(Integer id);
	
	@Select({
		"select * from user",
	})
	List<User> select();
	
}
