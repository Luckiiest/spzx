package com.codelucky.spzx.manager.service.system;

import com.codelucky.spzx.model.dto.system.AssginMenuDto;

import java.util.Map;

public interface SysRoleMenuService {
    /**
     查询所有菜单和当前角色拥有的菜单Id
     @param roleId
     @return
     */
    Map<String, Object> findSysRoleMenuByRoleId(Long roleId);
    
    /**
     给指定角色分配菜单
     @param assginMenuDto
     */
    void doAssign(AssginMenuDto assginMenuDto);
}
