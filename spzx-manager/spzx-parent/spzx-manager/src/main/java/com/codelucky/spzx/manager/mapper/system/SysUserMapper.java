package com.codelucky.spzx.manager.mapper.system;

import com.codelucky.spzx.model.dto.system.SysUserDto;
import com.codelucky.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {
    /**
     根据用户名查询用户
     @param userName
     @return SysUser
     */
    SysUser selectSysUserByUserName(String userName);
    
    /**
     条件分页查询
     @param sysUserDto
     @return List<SysUser>
     */
    List<SysUser> findByPage(SysUserDto sysUserDto);
    
    /**
     新增用户
     @param sysUser
     */
    void saveSysUser(SysUser sysUser);
    
    /**
     修改用户信息
     @param sysUser
     */
    void updateSysUser(SysUser sysUser);
    
    /**
     根据id查询用户
     @param id
     @return
     */
    SysUser selectSysUserById(Long id);
    
    /**
     根据id删除用户信息
     @param userId
     */
    void deleteById(Long userId);
}
