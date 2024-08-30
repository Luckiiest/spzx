package com.codelucky.spzx.manager.controller.system;

import com.codelucky.spzx.common.log.service.SysOperLogService;
import com.codelucky.spzx.model.entity.system.SysOperLog;
import com.codelucky.spzx.model.vo.common.Result;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/system/sysOperLog")
@Tag(name = "操作日志接口")
public class SysOperLogController {
    
    @Resource
    private SysOperLogService sysOperLogService;
    
    /**
     条件分页查询
     @param keyword
     @param pageNum
     @param pageSize
     @return
     */
    @GetMapping("/findByPage/{pageNum}/{pageSize}")
    public Result<PageInfo<SysOperLog>> findByPage(String keyword, @PathVariable Integer pageNum,
                                                   @PathVariable Integer pageSize) {
        PageInfo<SysOperLog> sysOperLogPageInfo = sysOperLogService.findByPage(keyword, pageNum, pageSize);
        
        return Result.build(sysOperLogPageInfo, ResultCodeEnum.SUCCESS);
    }
}
