package com.situ.taskmgr.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.situ.taskmgr.entity.User;
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
		
}
