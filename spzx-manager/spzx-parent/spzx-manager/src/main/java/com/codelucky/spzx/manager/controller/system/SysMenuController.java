package com.codelucky.spzx.manager.controller.system;

import com.codelucky.spzx.common.log.annotation.Log;
import com.codelucky.spzx.manager.service.system.SysMenuService;
import com.codelucky.spzx.model.entity.system.SysMenu;
import com.codelucky.spzx.model.vo.common.Result;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/system/sysMenu")
@Tag(name = "菜单接口")
public class SysMenuController {
    
    @Resource
    private SysMenuService sysMenuService;
    
    /**
     删除菜单
     @param id
     @return
     */
    @DeleteMapping("/removeById/{id}")
    @Log(title = "系统菜单模块-删除菜单",
         businessType = 3)
    public Result removeById(@PathVariable Long id) {
        sysMenuService.removeById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    
    /**
     修改菜单
     @param sysMenu
     @return
     */
    @PutMapping("/updateById")
    @Log(title = "系统菜单模块-修改菜单",
         businessType = 2)
    public Result updateById(@RequestBody SysMenu sysMenu) {
        sysMenuService.updateById(sysMenu);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    
    /**
     新增菜单
     @param sysMenu
     @return
     */
    @PostMapping("/save")
    @Log(title = "系统菜单模块-新增菜单",
         businessType = 1)
    public Result save(@RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    
    /**
     查询所有菜单数据
     @return
     */
    @GetMapping("/findNodes")
    @Log(title = "系统菜单模块-查询所有菜单",
         businessType = 0,
         isSaveResponseData = false)
    public Result<List<SysMenu>> findNodes() {
        List<SysMenu> sysMenuList = sysMenuService.findNodes();
        return Result.build(sysMenuList, ResultCodeEnum.SUCCESS);
    }
}
