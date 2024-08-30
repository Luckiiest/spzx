package com.codelucky.spzx.manager.mapper.system;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleUserMapper {
    
    /**
     删除指定用户原来的角色
     @param userId
     */
    void deleteByUserId(Long userId);
    
    /**
     保存用户选择分配的角色
     @param roleId
     @param userId
     */
    void dodoAssign(@Param("roleId") Long roleId, @Param("userId") Long userId);
    
    /**
     查询指定用户所拥有的角色Id
     @param userId
     @return
     */
    List<Long> selectRolesIdsByUserId(Long userId);
}
