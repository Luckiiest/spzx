package com.codelucky.spzx.manager.controller.system;

import com.codelucky.spzx.common.log.annotation.Log;
import com.codelucky.spzx.manager.service.system.SysRoleMenuService;
import com.codelucky.spzx.model.dto.system.AssginMenuDto;
import com.codelucky.spzx.model.vo.common.Result;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/system/sysRoleMenu")
@Tag(name = "角色菜单接口")
public class SysRoleMenuController {
    
    @Resource
    private SysRoleMenuService sysRoleMenuService;
    
    /**
     给指定角色分配菜单
     @param assginMenuDto
     @return
     */
    @PostMapping("/doAssign")
    @Log(title = "系统角色菜单模块-给角色分配菜单",
         businessType = 1)
    public Result doAssign(@RequestBody AssginMenuDto assginMenuDto) {
        sysRoleMenuService.doAssign(assginMenuDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    
    /**
     查询所有菜单和当前角色拥有的菜单Id
     @param roleId
     @return
     */
    @GetMapping("/findSysRoleMenuByRoleId/{roleId}")
    @Log(title = "系统角色菜单模块-查询所有菜单和当前角色拥有的菜单Id",
         businessType = 0,
         isSaveResponseData = false)
    public Result<Map<String, Object>> findSysRoleMenuByRoleId(@PathVariable Long roleId) {
        Map<String, Object> resultMap = sysRoleMenuService.findSysRoleMenuByRoleId(roleId);
        
        return Result.build(resultMap, ResultCodeEnum.SUCCESS);
    }
}
