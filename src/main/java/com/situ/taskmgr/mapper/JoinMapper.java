package com.situ.taskmgr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.situ.taskmgr.entity.Join;

@Mapper
public interface JoinMapper {

	/**
	 * 添加
	 */
	@Insert({
		"insert into `join`", //要用反单引号
		"(type, task_id, user_id)",
		"values(#{type},#{taskId},#{userId})",
	})
	int insert(Join join);
	
	/**
	 * 根据任务ID删除
	 */
	
	@Delete({
		"delete from `join`",
		"where task_id=#{taskId}",
	})
	int deleteByTaskId(Integer taskId);
	
	
	/**
	 * 根据任务ID查询
	 */
	@Select({
		"select * from `join`",
		"where task_id=#{taskId}",
	})
	List<Join> selectByTaskId(Integer taskID);
	
	/**
	 * 根据用户ID查询
	 */
	@Select({
		"select * from `join`",
		"where user_id=#{userId}",
	})
	List<Join> selectByUserId(Integer userId);
}
