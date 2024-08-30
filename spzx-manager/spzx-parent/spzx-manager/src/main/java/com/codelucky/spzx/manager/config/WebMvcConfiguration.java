package com.codelucky.spzx.manager.config;

import com.codelucky.spzx.manager.interceptor.LoginAuthInterceptor;
import com.codelucky.spzx.manager.properties.UserAuthProperties;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebMvcConfiguration implements WebMvcConfigurer {
    
    @Resource
    private LoginAuthInterceptor loginAuthInterceptor;
    @Resource
    private UserAuthProperties userAuthProperties;
    
    // 跨域处理和设置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 添加路径规则
                .allowCredentials(true)       // 是否允许在跨域的情况下传递Cookie
                .allowedOriginPatterns("*")   // 允许请求来源的域规则
                .allowedMethods("*")          // 允许的请求方法
                .allowedHeaders("*");         // 允许所有的请求头
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册登录拦截器
        registry.addInterceptor(loginAuthInterceptor)
                .excludePathPatterns(userAuthProperties.getNoAuthUrls())
                //.excludePathPatterns("/**")
                .addPathPatterns("/**");
    }
}
