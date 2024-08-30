package com.codelucky.spzx.service.handler;

import com.alibaba.fastjson2.JSON;
import com.codelucky.spzx.model.entity.user.UserInfo;
import com.codelucky.spzx.util.AuthContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

public class UserLoginAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        String userJson = redisTemplate.opsForValue().get("user:login:" + token);
        UserInfo userInfo = JSON.parseObject(String.valueOf(JSON.parse(userJson)), UserInfo.class);
        //放到threadLocal里面
        AuthContextUtil.setUserInfo(userInfo);
        return true;
    }
}
