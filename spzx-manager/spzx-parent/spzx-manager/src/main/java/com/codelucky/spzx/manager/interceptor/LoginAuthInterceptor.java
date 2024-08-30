package com.codelucky.spzx.manager.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.codelucky.spzx.model.entity.system.SysUser;
import com.codelucky.spzx.model.vo.common.Result;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import com.codelucky.spzx.util.AuthContextUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import static com.codelucky.spzx.service.config.ConstantConfig.USER_LOGIN;

@Component
public class LoginAuthInterceptor implements HandlerInterceptor {
    
    @Resource
    private RedisTemplate redisTemplate;
    
    // 请求到controller之前进行执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        String servletPath = request.getServletPath();
        System.out.println(servletPath);
        
        // 获取请求方式
        String method = request.getMethod();
        // 如果是遇见请求options
        if (method.equalsIgnoreCase("OPTIONS")) {
            return true;
        }
        // 获取token
        String token = request.getHeader("token");
        // 判断
        if (StrUtil.isEmpty(token)) {
            // 设置响应内容
            responseNoLoginInfo(response);
            return false;
        }
        // 从Redis获取数据
        SysUser strUser = (SysUser) redisTemplate.opsForValue()
                                                 .get(USER_LOGIN + token);
        // 判断
        if (strUser == null) {
            // 设置响应内容
            responseNoLoginInfo(response);
            return false;
        }
        // 转换数据
        //JSONObject sysUser = JSON.parseObject(strUser, SysUser.class);
        // 存储到线程变量中
        AuthContextUtil.set(strUser);
        // 重新设置Redis中token的有效时间
        redisTemplate.expire(USER_LOGIN + token, 30, TimeUnit.MINUTES);
        
        return true;
    }
    
    /**
     构建响应信息
     @param response
     */
    private void responseNoLoginInfo(HttpServletResponse response) {
        // 构建响应内容
        Result result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        // 处理中文乱码
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        // 获取响应流
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            // 响应
            writer.print(JSON.toJSONString(result));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            writer.close();
        }
    }
    
    // 响应之前执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        // 删除线程中的本地变量
        AuthContextUtil.remove();
    }
}
