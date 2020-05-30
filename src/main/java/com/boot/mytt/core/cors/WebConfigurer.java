package com.boot.mytt.core.cors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
/**
 * @Author : JCccc
 * @CreateTime : 2019/10/25
 * @Description : 拦截器配置总部
 *   普及：Tomcat（Filter->Servlet->Interceptor->Controller）
 **/
@Configuration
public class WebConfigurer implements WebMvcConfigurer {
 
    @Autowired
    private CORSInterceptor corsInterceptor;
 
 
    // 这个方法是用来配置静态资源的，比如html，js，css，等等
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
 
 
    }
 
    // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
 
         registry.addInterceptor(corsInterceptor);
 
    }
 
 
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedOrigins("*");
    }
 
 
 
}