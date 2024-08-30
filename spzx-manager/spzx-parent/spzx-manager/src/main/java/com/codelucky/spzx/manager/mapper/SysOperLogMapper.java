package com.codelucky.spzx.manager.mapper;

import com.codelucky.spzx.model.entity.system.SysOperLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysOperLogMapper {
    /**
     保存日志
     @param sysOperLog
     */
    void saveSysOperLog(SysOperLog sysOperLog);
    
    /**
     查询系统操作日志
     @param keyword
     @return
     */
    List<SysOperLog> findByPage(String keyword);
}
