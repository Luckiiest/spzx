package com.codelucky.spzx.common.log.service;

import com.codelucky.spzx.model.entity.system.SysOperLog;
import com.github.pagehelper.PageInfo;

public interface SysOperLogService {
    
    /**
     执行保存日志
     @param sysOperLog
     */
    void saveSysOperLog(SysOperLog sysOperLog);
    
    /**
     分页查询系统操作日志
     @param keyword
     @param pageNum
     @param pageSize
     @return
     */
    PageInfo<SysOperLog> findByPage(String keyword, Integer pageNum, Integer pageSize);
}
