package com.situ.taskmgr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task")
public class TaskController {

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
	
	
}
