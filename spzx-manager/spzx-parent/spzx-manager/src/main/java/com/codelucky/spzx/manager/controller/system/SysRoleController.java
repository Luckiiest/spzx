package com.codelucky.spzx.manager.controller.system;

import com.codelucky.spzx.common.log.annotation.Log;
import com.codelucky.spzx.manager.service.system.SysRoleService;
import com.codelucky.spzx.model.dto.system.SysRoleDto;
import com.codelucky.spzx.model.entity.system.SysRole;
import com.codelucky.spzx.model.vo.common.Result;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/system/sysRole")
@Tag(name = "角色接口")
public class SysRoleController {
    @Resource
    private SysRoleService sysRoleService;
    
    
    /**
     查询所有的角色以及查询当前用户拥有的角色Id
     @return
     */
    @GetMapping("/findAllRoles/{userId}")
    @Log(title = "系统角色模块-查询所有的角色以及查询当前用户拥有的角色",
         businessType = 0)
    public Result<Map<String, Object>> findAllRoles(@PathVariable Long userId) {
        Map<String, Object> resultMap = sysRoleService.findAllRoles(userId);
        return Result.build(resultMap, ResultCodeEnum.SUCCESS);
    }
    
    /**
     删除角色
     @param id
     @return
     */
    @DeleteMapping("/deleteSysRoleById/{id}")
    @Log(title = "系统角色模块-删除角色",
         businessType = 3)
    public Result deleteSysRoleById(@PathVariable Long id) {
        sysRoleService.deleteSysRoleById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    
    /**
     修改保存角色
     @param sysRole
     @return
     */
    @PutMapping("/updateSysRole")
    @Log(title = "系统角色模块-修改角色",
         businessType = 2)
    public Result updateSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.updateSysRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    
    /**
     保存角色
     @param sysRole
     @return
     */
    @PostMapping("/saveSysRole")
    @Log(title = "系统角色模块-保存角色",
         businessType = 1)
    public Result saveSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.saveSysRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    
    /**
     条件分页查询
     @param sysRoleDto
     @param pageNum
     @param pageSize
     @return
     */
    @GetMapping("/findByPage/{pageNum}/{pageSize}")
    @Log(title = "系统角色模块-分页查询",
         businessType = 0,
         isSaveResponseData = false)
    public Result<PageInfo<SysRole>> findByPage(SysRoleDto sysRoleDto, @PathVariable Integer pageNum,
                                                @PathVariable Integer pageSize) {
        PageInfo<SysRole> rolePageInfo = sysRoleService.findByPage(sysRoleDto, pageNum, pageSize);
        return Result.build(rolePageInfo, ResultCodeEnum.SUCCESS);
    }
}
