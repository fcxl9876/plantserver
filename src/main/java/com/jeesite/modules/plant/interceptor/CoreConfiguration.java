package com.jeesite.modules.plant.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jeesite.common.config.Global;

@Configuration
@EnableWebMvc
public class CoreConfiguration implements WebMvcConfigurer {

	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(new OpenIntercept()).addPathPatterns("/front/plant/**").excludePathPatterns("/front/plant/login","/front/plant/version");
	
	}
	
	String sourceDiskPath=Global.getConfig("sourceDiskPath");
	
	@Override
	public void  addResourceHandlers(ResourceHandlerRegistry registry){
		

        //和页面有关的静态目录都放在项目的static目录下
		 registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	        //上传的图片在D盘下的OTA目录下，访问路径如：http://localhost:8081/OTA/d3cf0281-bb7f-40e0-ab77-406db95ccf2c.jpg
	        //其中OTA表示访问的前缀。"file:D:/OTA/"是文件真实的存储路径// file:D:/dkyfile/userfiles/fileupload/
	     registry.addResourceHandler("/sources/**").addResourceLocations(sourceDiskPath);	
	        
	}
	

}
