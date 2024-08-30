package com.codelucky.spzx.common.log.utils;

import com.alibaba.fastjson.JSON;
import com.codelucky.spzx.common.log.annotation.Log;
import com.codelucky.spzx.model.entity.system.SysOperLog;
import com.codelucky.spzx.util.AuthContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Map;

public class LogUtil {
    /**
     操作执行之后调用
     @param sysLog     注解对象--当前方法独有的参数
     @param proceed    目标方法执行后的返回值结果
     @param sysOperLog 封装的日志对象
     @param status     执行方法的状态
     @param errorMsg   执行方法的异常后的异常信息
     */
    public static void afterHandlLog(Log sysLog, Object proceed, SysOperLog sysOperLog, int status, String errorMsg) {
        // 判断是否需要在方法执行之后,保存用户的结果
        if (sysLog.isSaveResponseData()) {
            // 需要保持方法执行后的返回值结果
            sysOperLog.setJsonResult(JSON.toJSONString(proceed));
        }
        // 保存状态 0 或 1
        sysOperLog.setStatus(status);
        // 错误信息
        sysOperLog.setErrorMsg(errorMsg);
    }
    
    /**
     操作执行之前调用
     @param sysLog     注解对象
     @param joinPoint  被代理的目标方法-切点
     @param sysOperLog 需要封装的日志对象
     */
    public static void beforeHandleLog(Log sysLog, ProceedingJoinPoint joinPoint, SysOperLog sysOperLog) {
        // 设置操作模块名称
        sysOperLog.setTitle(sysLog.title());
        // 设置操作人-默认后台用户(MANAGE)
        sysOperLog.setOperatorType(sysLog.operatorType()
                                         .name());
        // 设置业务类型
        sysOperLog.setBusinessType(sysLog.businessType());
        
        // 设置目标方法信息--其中包含方法相关的所有信息,例如注解对象、参数列表、其他注解
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 获取方法对象--反射中表示方法的类型
        Method method = methodSignature.getMethod();
        // 设置目标方法的名称
        sysOperLog.setMethod(method.getDeclaringClass()
                                   .getName());
        
        // 获取请求参数
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // 获取请求对象
        HttpServletRequest request = requestAttributes.getRequest();
        // 获取请求方式 get post
        sysOperLog.setRequestMethod(request.getMethod());
        // 获取请求的URI
        sysOperLog.setOperUrl(request.getRequestURI());
        // 获取IP地址
        sysOperLog.setOperIp(LogUtil.getIpAddress(request));
        
        // 设置请求参数
        // 判断是否要记录请求参数,默认值为true
        if (sysLog.isSaveRequestData()) {
            // 获取请求方式
            String requestMethod = sysOperLog.getRequestMethod();
            // 判断是否为 PUT 或 POST
            if (HttpMethod.PUT.name()
                              .equals(requestMethod) || HttpMethod.POST.name()
                                                                       .equalsIgnoreCase(requestMethod)) {
                // 如果是,有可能是json对象,--讲Json对象转化为字符串
                String params = argsArrayToString(joinPoint.getArgs());
                sysOperLog.setOperParam(params);
            }
        }
        // 从线程变量中获取用户的名称
        sysOperLog.setOperName(AuthContextUtil.get()
                                              .getUserName());
    }
    
    // 参数拼装
    private static String argsArrayToString(Object[] paramsArray) {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0) {
            for (Object o : paramsArray) {
                if (!StringUtils.isEmpty(o) && !isFilterObject(o)) {
                    try {
                        Object jsonObj = JSON.toJSON(o);
                        params += jsonObj.toString() + " ";
                    } catch (Exception e) {
                    }
                }
            }
        }
        return params.trim();
    }
    
    /**
     判断是否需要过滤的对象。
     @param o 对象信息。
     @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    private static boolean isFilterObject(Object o) {
        Class<?> clazz = o.getClass();
        // 判断是否为数组类型
        if (clazz.isArray()) {
            // 如果是数组，判断其元素类型是否为MultipartFile或其子类
            return clazz.getComponentType()
                        .isAssignableFrom(MultipartFile.class);
            // 判断是否为Collection集合类型
        } else if (Collection.class.isAssignableFrom(clazz)) {
            Collection collection = (Collection) o;
            for (Object value : collection) {  // 只要有一个元素的类型为MultipartFile或其子类，则认为该集合对象为过滤对象
                return value instanceof MultipartFile;
            }
        } else if (Map.class.isAssignableFrom(clazz)) {  // 判断是否为Map集合类型
            Map map = (Map) o;
            for (Object value : map.entrySet()) {  // 只要有一个Value的类型是MultipartFile或其子类，则认为该映射对象为过滤对象
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        // 如果以上条件都不满足，最后判断对象本身是否为MultipartFile、HttpServletRequest、HttpServletResponse类的实例
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse;
    }
    
    //获取ip地址
    private static String getIpAddress(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***,***,***,***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        // ipAddress = this.getRequest().getRemoteAddr();
        return ipAddress;
    }
}
