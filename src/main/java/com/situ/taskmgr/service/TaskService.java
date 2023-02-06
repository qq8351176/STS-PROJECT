package com.situ.taskmgr.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.situ.taskmgr.entity.Task;

public interface TaskService {

	/**
	 * 添加
	 */
	int add(Task task);
	
	/**
	 * 修改任务
	 */
	int update(Task task);
	/**
	 * 根据ID查询
	 */
	
	Task getById(Integer id);
	
	/**
	 * 分页查询
	 */
	PageInfo getByPage(Integer page,Integer limit);
	/**
	 * 查询本周的任务
	 */
	List<Task> getByWeek();
}
