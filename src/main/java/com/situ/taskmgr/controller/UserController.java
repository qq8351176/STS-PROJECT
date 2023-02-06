package com.situ.taskmgr.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.taskmgr.entity.*;
import com.situ.taskmgr.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	    @Autowired
	    private UserService userService;
	    
		@GetMapping("/login")
		public String login(){
			return "user/login";
			//在templates下应该有一个login.html
		}
		
		/**
		 * 定义一个方法 返回是string类型
		 * 方法里面要做三件事 处理参数
		 * 调用service层
		 * 根据处理结果来跳转页面
		 * 
		 */
		
		@PostMapping("/login")
		public String login(User user,HttpSession session,Model model)
		{
			try {
				user = userService.login(user);
				//登录成功的时候保存登录信息
				session.setAttribute("user", user);
				return "redirect:/";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				model.addAttribute("error",e.getMessage());
				return "user/login";
			}
		}
		
		@GetMapping("/logout")
		public String logout(HttpSession session)
		{
			session.invalidate();//清楚session对象
			
			return "redirect:/user/login";
			
		}
		
		@GetMapping("edit")
		public String edit()
		{
			return "user/edit";
		}
		
		/**
		 * 修改个人信息的处理 返回一个json格式的数据
		 */
		@PostMapping("/edit")
		@ResponseBody //返回json格式
		public Result edit(User user,HttpSession session) {
			//取出当前登录的用户
			if(user.getId() == null)
			{
				User loginUser = (User) session.getAttribute("user");
				user.setId(loginUser.getId());
			}
			try {
				userService.edit(user);
				return Result.success();
			} catch (Exception e) {
				e.printStackTrace();
				return Result.error(e.getMessage());
			}
		}
		
		/**
		 * 修改密码的页面
		 */
		@GetMapping("/modifyPwd")
		public String modifyPwd()
		{
			return "user/modifyPwd";
		}
		
		@PostMapping("/modifyPwd")
		@ResponseBody
		public Result modifyPwd(String oldPassword,String newPassword,
				String rePassword,HttpSession session)
		{
			User user = (User) session.getAttribute("user");
			
			try {
				userService.modifyPwd(oldPassword,newPassword,rePassword,user.getId());
				//清除登录信息
				session.invalidate();
				
				return Result.success();
			} catch (Exception e) {
				e.printStackTrace();
				return Result.error(e.getMessage());
			}
					
		}
		
		/**
		 * 员工列表的页面
		 */
		@GetMapping("/list")
		public String list()
		{
			return "user/list";
		}
		
		@PostMapping("/list")
		@ResponseBody
		public Result list(Integer page,Integer limit) {
			if(page == null) {
			  //不分页
				return Result.success(userService.getAll());
			}
			else {
				return Result.success(userService.getByPage(page, limit));
			}
		}
		
		@GetMapping("/add")
		public String add() {
			return "user/add";
		}
		@PostMapping("/add")
		@ResponseBody
		public Result add(User user) {
			try {
				userService.add(user);
				//添加成功
				return Result.success();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Result.error(e.getMessage());
			}
		}
}
