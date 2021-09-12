package com.jeesite.modules.plant.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jeesite.common.mapper.JsonMapper;

public class OpenIntercept implements HandlerInterceptor {
	
	private static Map<String, String> illegalMap=new HashMap<String, String>();
	private static Map<String, String> illegalMap2=new HashMap<String, String>();
	static{
		illegalMap.put("status", "400");
		illegalMap.put("msg", "不合法用户登录");
		illegalMap2.put("status", "400");
		illegalMap2.put("msg", "token已经过期,请跳转重新登录!");
	}

	@Autowired
	public  boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			
		String tk=request.getParameter("tk");
		if(tk==null){
			response.setCharacterEncoding("UTF-8");
 
			response.getOutputStream().write(JsonMapper.toJson(illegalMap).getBytes());
			return false;			
		}
		if(ValidateMap.isCacheToken(tk)){			
			return true;
		}else{		
			response.setCharacterEncoding("UTF-8");
	      
			response.getOutputStream().write(JsonMapper.toJson(illegalMap2).getBytes());
			return false;
		}

	}
	
	@Autowired
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
	}
	
	@Autowired
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
	}


}
