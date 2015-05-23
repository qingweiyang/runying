package com.runying.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.runying.util.Constants;



public class LoginFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)arg0;   
        HttpServletResponse response = (HttpServletResponse)arg1;   
        HttpSession session = request.getSession();   
        
//      	String url=request.getServletPath();    
        String contextPath=request.getContextPath(); 
        // 用户还未登录
        if(session.getAttribute(Constants.USER)==null){
//        	System.out.println("用户未登录");
        	Constants.username = null;
        	// 访问的URL如果包含jsp但是又不是login.jsp,那么就重定向到login.jsp页面
        	if(request.getRequestURI().indexOf(".jsp") != -1 && request.getRequestURI().indexOf("login.jsp")==-1){
        		System.out.println("重定向到登录界面");
        		response.sendRedirect(contextPath + "/font-desgin/log/login.html");
        	}
        }
        else{
//        	System.out.println("用户已经登录"+session.getAttribute(Constants.USER));
        	Constants.username = session.getAttribute(Constants.USER).toString();
        }
//        System.out.println(arg2);
        arg2.doFilter(arg0, arg1);   
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}
