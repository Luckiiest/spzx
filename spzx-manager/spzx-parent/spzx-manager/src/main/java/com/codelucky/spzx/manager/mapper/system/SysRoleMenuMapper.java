package com.codelucky.spzx.manager.mapper.system;

import com.codelucky.spzx.model.dto.system.AssginMenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMenuMapper {
    /**
     查询当前角色拥有的菜单Id列表
     @param roleId
     @return List<Long>
     */
    List<Long> findSysRoleMenuByRoleId(Long roleId);
    
    /**
     删除之前拥有的菜单
     @param roleId
     */
    void deleteByRoleId(Long roleId);
    
    /**
     保存用户选择的菜单
     @param assginMenuDto
     */
    void doAssign(AssginMenuDto assginMenuDto);
}
