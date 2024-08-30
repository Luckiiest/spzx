package com.codelucky.spzx.common.log.aspect;

import com.codelucky.spzx.common.log.annotation.Log;
import com.codelucky.spzx.common.log.service.SysOperLogService;
import com.codelucky.spzx.common.log.utils.LogUtil;
import com.codelucky.spzx.model.entity.system.SysOperLog;
import jakarta.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect implements Ordered {
    
    @Resource
    private SysOperLogService sysOperLogService;
    
    /**
     通知类型:
     Around: 环绕通知
     参数部分, 切点表达式
     切点表达式:
     1. 直接配置全路径的方式
     2. 通过在参数列表中声明注解类型,表达始终配置指定注解类型的便利
     区别: 本质上没有区别,只是形式上不同,第二种方式更加方便,可以直接获取增强方法种的独有数据
     @param
     @return
     @throws Throwable
     */
    //@Around("@annotation(com.codelucky.spzx.common.log.annotation.Log)")
    @Around("@annotation(sysLog)")
    public Object doConcurrentOperation(ProceedingJoinPoint joinPoint, Log sysLog) throws Throwable {
        
        // 不需要sysLog变量时,通过joinPoint获取注解属性
        //MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //Log annotation = signature.getMethod()
        //                          .getAnnotation(Log.class);
        //String title = annotation.title();
        // 有sysLog变量时,直接通过sysLog获取
        //String title = sysLog.title();
        
        // 创建日志对象
        SysOperLog sysOperLog = new SysOperLog();
        // 方法执行之前收集数据
        LogUtil.beforeHandleLog(sysLog, joinPoint, sysOperLog);
        Object proceed = null;
        
        try {
            // 执行目标方法
            proceed = joinPoint.proceed();
            // 收集目标方法正确的结果
            LogUtil.afterHandlLog(sysLog, proceed, sysOperLog, 0, null);
        } catch (Throwable e) {
            // 方法执行之后异常信息收集
            LogUtil.afterHandlLog(sysLog, proceed, sysOperLog, 1, e.toString());
            e.printStackTrace();
            throw new RuntimeException();
        }
        
        sysOperLogService.saveSysOperLog(sysOperLog);
        
        return proceed;
    }
    
    // 设置切面类优先级
    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }
}
