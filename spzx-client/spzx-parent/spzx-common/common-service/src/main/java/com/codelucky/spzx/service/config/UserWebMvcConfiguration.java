package com.codelucky.spzx.service.config;


import com.codelucky.spzx.service.handler.UserLoginAuthInterceptor;
import jakarta.annotation.Resource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class UserWebMvcConfiguration implements WebMvcConfigurer {

    @Resource
    private UserLoginAuthInterceptor userLoginAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userLoginAuthInterceptor).addPathPatterns("/api/**");
    }
}
