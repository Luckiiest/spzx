package com.codelucky.spzx.manager.controller.system;

import com.codelucky.spzx.common.log.annotation.Log;
import com.codelucky.spzx.manager.service.system.SysRoleUserService;
import com.codelucky.spzx.model.dto.system.AssginRoleDto;
import com.codelucky.spzx.model.vo.common.Result;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/system/sysRoleUser")
@Tag(name = "角色用户接口")
public class SysRoleUserController {
    @Resource
    private SysRoleUserService sysRoleUserService;
    
    /**
     保存分配角色
     @param assginRoleDto
     @return
     */
    @PostMapping("/doAssign")
    @Log(title = "系统角色用户模块-给用户分配角色",
         businessType = 1)
    public Result doAssign(@RequestBody AssginRoleDto assginRoleDto) {
        sysRoleUserService.doAssign(assginRoleDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
