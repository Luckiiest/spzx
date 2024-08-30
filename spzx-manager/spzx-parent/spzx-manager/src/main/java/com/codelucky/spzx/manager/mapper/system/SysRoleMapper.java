package com.codelucky.spzx.manager.mapper.system;

import com.codelucky.spzx.model.dto.system.SysRoleDto;
import com.codelucky.spzx.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper {
    
    /**
     查询角色列表
     @param sysRoleDto
     @return
     */
    List<SysRole> findByPage(SysRoleDto sysRoleDto);
    
    /**
     保存角色
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
     查询所有角色
     @return
     */
    List<SysRole> findAllRoles();
}
