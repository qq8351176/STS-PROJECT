package com.situ.taskmgr.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.situ.taskmgr.entity.User;

/**
 * 
 * @author kwd
 *	如果用户登录了放行可以进入到相应的界面
 */

@Component  //将拦截器放到spring的容器当中
public class UserInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		User user =(User) session.getAttribute("user");
		//判断用户是否登录了
		if(user == null)
		{
			response.sendRedirect("/user/login");
			
			return false;
		}
		
		return true;
	}
}
