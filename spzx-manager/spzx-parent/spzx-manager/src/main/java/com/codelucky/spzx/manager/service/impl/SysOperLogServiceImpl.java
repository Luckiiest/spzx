package com.codelucky.spzx.manager.service.impl;

import com.codelucky.spzx.common.log.service.SysOperLogService;
import com.codelucky.spzx.manager.mapper.SysOperLogMapper;
import com.codelucky.spzx.model.entity.system.SysOperLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysOperLogServiceImpl implements SysOperLogService {
    
    @Resource
    private SysOperLogMapper sysOperLogMapper;
    
    /**
     执行保存日志
     @param sysOperLog
     */
    @Override
    @Async
    public void saveSysOperLog(SysOperLog sysOperLog) {
        sysOperLogMapper.saveSysOperLog(sysOperLog);
    }
    
    /**
     分页查询系统操作日志
     @param keyword
     @param pageNum
     @param pageSize
     @return
     */
    @Override
    public PageInfo<SysOperLog> findByPage(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysOperLog> sysOperLogList = sysOperLogMapper.findByPage(keyword);
        PageInfo<SysOperLog> pageInfo = new PageInfo<>(sysOperLogList);
        return pageInfo;
    }
    
}
