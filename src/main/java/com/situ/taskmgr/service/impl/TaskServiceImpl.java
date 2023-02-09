package com.situ.taskmgr.service.impl;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.taskmgr.entity.Join;
import com.situ.taskmgr.entity.Task;
import com.situ.taskmgr.mapper.JoinMapper;
import com.situ.taskmgr.mapper.TaskMapper;
import com.situ.taskmgr.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	private TaskMapper taskmapper;
	@Autowired
	private JoinMapper joinmapper;
	
	@Override
	public int add(Task task) {
		//参数的验证此处省略了 懒.JPG
		//添加任务 任务与用户的关联
		//插入任务task 任务的ID是由数据库自动生成的
		
		//插入用户与用户的关联 join
		
		int code = taskmapper.insert(task);
		
		for(Join join : task.getJoins()) {
			join.setTaskId(task.getId());
			
			//保存到数据库当中去
			code = joinmapper.insert(join);
			
		}
		//插入任务与用户的关联 join 这里需要任务的ID
		
		return code;
	}

	@Override
	public int update(Task task) {
		// 修改任务的信息
		int code = taskmapper.updata(task);
		
		//修改任务的关联人员
		//删除之前的关联
		code = joinmapper.deleteByTaskId(task.getId());
		//重新添加关联
		
		for(Join join : task.getJoins()) {
			code = joinmapper.insert(join);
		}
		return code;
	}

	@Override
	public Task getById(Integer id) {
		
		return taskmapper.seleceById(id);
	}

	@Override
	public PageInfo getByPage(Integer page, Integer limit) {
		//pagehelper
		PageHelper.startPage(page,limit);
		List<Task> list = taskmapper.select();
		return new PageInfo<>(list);
	}

	@Override
	public List<Task> getByWeek() {
		// 获取当天的时间 
		// 周一
		// 下周的周一
		Calendar calendar = Calendar.getInstance();//获取当前的时间
		calendar.set(Calendar.HOUR_OF_DAY, 0);	
		calendar.set(Calendar.MINUTE, 0);	
		calendar.set(Calendar.SECOND, 0);	
		calendar.set(Calendar.MILLISECOND, 0);  
		
		calendar.setWeekDate(calendar.getWeekYear(),
				calendar.get(Calendar.WEEK_OF_YEAR) + (calendar.get(Calendar.DAY_OF_WEEK) == 1?-1:0), 
				2);
		//下周的周一
		Date start = calendar.getTime();
		
		calendar.setWeekDate(calendar.getWeekYear(), calendar.get(Calendar.WEEK_OF_YEAR)+1, 
				2);
		
		Date end = calendar.getTime();
		
		return taskmapper.seleceByDate(start, end);
	}
	
}
