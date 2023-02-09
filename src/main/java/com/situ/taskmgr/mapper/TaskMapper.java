package com.situ.taskmgr.mapper;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.situ.taskmgr.entity.Task;

@Mapper
public interface TaskMapper {
	/**
	 * 添加
	 */
	@Insert({
		"insert into task",
		"(title, content, target, result, start, end, creater_id)",
		"value",
		"(#{title}, #{content}, #{target}, #{result}, #{start}, #{end}, #{createrId})",
	})
	@Options(useGeneratedKeys = true, keyColumn = "id",keyProperty = "id")
	//获取数据库自动生成的主键         获取ID                 保存到task的ID属性上
	
	int insert(Task task);
	
	/**
	 * 更新
	 */
	@Update({
		"update task",
		"set title=#{title},",
		"content=#{content},",
		"target=#{target},",
		"result=#{result},",
		"start=#{start},",
		"end=#{end},",
		"status=#{status}",
		"where id=#{id}",
	})
	int updata(Task task);
	/**
	 * 根据ID查询
	 */
	@Select({
		"select * from task",
		"where id=#{id}"
	})
	@Results({
		@Result(column = "id", property = "id",id = true),
		@Result(column = "id",property = "joins",
		many = @Many(select = "com.situ.taskmgr.mapper.JoinMapper.selectByTaskId")),
	})
	Task seleceById(Integer id);
	/**
	 * 查询所有
	 */
	@Select({
		"select * from task",
	})
	@Results({
		@Result(column = "creater_id",property = "createrId"),//结果的关联映射
		@Result(column = "creater_id",property = "creater", 
		one = @One(select = "com.situ.taskmgr.mapper.UserMapper.selectById")),
	})
	List<Task> select();
	/**
	 * 根据时间查询
	 */
	@Select({
		"select * from task",
		"where",
		"not (end < #{start} or start > #{end})"
	})
	List<Task> seleceByDate(@Param("start")Date start,@Param("end")Date end);
}
