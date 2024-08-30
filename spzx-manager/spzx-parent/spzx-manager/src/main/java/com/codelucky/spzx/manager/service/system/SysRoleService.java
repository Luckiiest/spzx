package com.codelucky.spzx.manager.service.system;

import com.codelucky.spzx.model.dto.system.SysRoleDto;
import com.codelucky.spzx.model.entity.system.SysRole;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface SysRoleService {
    
    /**
     条件分页查询角色列表
     @param sysRoleDto
     @param pageNum
     @param pageSize
     @return
     */
    PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto,
                                 Integer pageNum,
                                 Integer pageSize);
    
    /**
     保护角色
     @param sysRole
     */
    void saveSysRole(SysRole sysRole);
    
    /**
     修改保存角色
     @param sysRole
     */
    void updateSysRole(SysRole sysRole);
    
    /**
     根据id删除角色
     @param id
     */
    void deleteSysRoleById(Long id);
    
    /**
     查询所有的角色以及查询当前用户拥有的角色Id
     @return
     */
    Map<String, Object> findAllRoles(Long userId);
}
