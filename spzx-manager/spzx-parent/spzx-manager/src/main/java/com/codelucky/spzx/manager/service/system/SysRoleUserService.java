package com.codelucky.spzx.manager.service.system;

import com.codelucky.spzx.model.dto.system.AssginRoleDto;

public interface SysRoleUserService {
    
    /**
     保存分配角色
     @param assginRoleDto
     */
    void doAssign(AssginRoleDto assginRoleDto);
}
