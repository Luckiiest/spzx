package com.codelucky.spzx.manager.controller.system;

import com.codelucky.spzx.common.log.annotation.Log;
import com.codelucky.spzx.manager.service.system.SysUserService;
import com.codelucky.spzx.model.dto.system.SysUserDto;
import com.codelucky.spzx.model.entity.system.SysUser;
import com.codelucky.spzx.model.vo.common.Result;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/system/sysUser")
@Tag(name = "用户接口")
public class SysUserController {
    
    @Resource
    private SysUserService sysUserService;
    
    /**
     删除用户
     @param userId
     @return
     */
    @DeleteMapping("/deleteById/{userId}")
    @Log(title = "系统用户模块-删除用户",
         businessType = 3)
    public Result deleteById(@PathVariable Long userId) {
        sysUserService.deleteById(userId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    
    /**
     修改用户
     @param sysUser
     @return
     */
    @PutMapping("/updateSysUser")
    @Log(title = "系统用户模块-修改用户",
         businessType = 2)
    public Result updateSysUser(@RequestBody SysUser sysUser) {
        sysUserService.updateSysUser(sysUser);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    
    /**
     新增用户
     @param sysUser
     @return
     */
    @PostMapping("/saveSysUser")
    @Log(title = "系统用户模块-新增用户",
         businessType = 1)
    public Result saveSysUser(@RequestBody SysUser sysUser) {
        sysUserService.saveSysUser(sysUser);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    
    /**
     分页查询用户
     @param sysUserDto
     @param pageNum
     @param pageSize
     @return
     */
    @GetMapping("/findByPage/{pageNum}/{pageSize}")
    @Log(title = "系统用户模块-分页查询",
         businessType = 0,
         isSaveResponseData = false)
    public Result<PageInfo<SysUser>> findByPage(SysUserDto sysUserDto, @PathVariable Integer pageNum,
                                                @PathVariable Integer pageSize) {
        PageInfo<SysUser> pageInfo = sysUserService.findByPage(sysUserDto, pageNum, pageSize);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }
}
