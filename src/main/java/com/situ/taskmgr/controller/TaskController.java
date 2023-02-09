package com.situ.taskmgr.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.taskmgr.entity.Result;
import com.situ.taskmgr.entity.Task;
import com.situ.taskmgr.entity.User;
import com.situ.taskmgr.service.TaskService;

@Controller
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService taskService;
	/**	 
	 * 显示任务列表的页面
	 */
	@GetMapping("/list")
	public String list() {
		return "task/list";
	}
	
	/**
	 * 提供任务列表的数据
	 */
	@PostMapping("/list")
	@ResponseBody
	public Result list(Integer page,Integer limit) {
		return Result.success(taskService.getByPage(page, limit));
	}
	
	/**
	 * 显示添加页面
	 */
	@GetMapping("/add")
	public String add() {
		return "task/add";
	}
	
	/**
	 * 添加的操作
	 */
	//@RequestBody解析json格式的参数
	@PostMapping("/add")
	@ResponseBody
	public Result add(@RequestBody Task task,HttpSession session) { 
		//取出当前登录的用户
		User user = (User) session.getAttribute("user");
		task.setCreaterId(user.getId());
		
		int code = taskService.add(task);
		if(code > 0) {
			return Result.success();
		}
		else {
			return Result.error("添加任务失败");
		}
	}
	
	@GetMapping("/edit")
	public String edit() {
		return "task/edit";
	}
	
	/**
	 * 根据ID来获取任务的信息
	 */
	@PostMapping("/get")
	@ResponseBody
	public Result get(Integer id) {
		Task task=taskService.getById(id);
		if(task != null) {
			return Result.success(task);
		}else {
			return Result.error("任务不存在!");
		}
	}
	
	@PostMapping("/edit")
	@ResponseBody
	public Result edit(@RequestBody Task task) {
		
		int code= taskService.update(task);
		if(code > 0) {
			return Result.success();
		} else {
			return Result.error("编辑失败");
		}
	}
	
	@GetMapping("/week")
	public String week() {
		return "task/week";
	}
	
	/**
	 * 返回当周的任务列表
	 */
	@PostMapping("/week")
	@ResponseBody
	public Result getWeek() {
		return Result.success(taskService.getByWeek());
	}
}
