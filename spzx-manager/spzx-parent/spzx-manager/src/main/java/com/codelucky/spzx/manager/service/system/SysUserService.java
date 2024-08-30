package com.codelucky.spzx.manager.service.system;

import com.codelucky.spzx.model.dto.system.LoginDto;
import com.codelucky.spzx.model.dto.system.SysUserDto;
import com.codelucky.spzx.model.entity.system.SysUser;
import com.codelucky.spzx.model.vo.system.LoginVo;
import com.github.pagehelper.PageInfo;

public interface SysUserService {
    /**
     用户登录
     @param loginDto
     @return
     */
    LoginVo login(LoginDto loginDto);
    
    /**
     获取用户信息
     @param token
     @return
     */
    SysUser getUserInfo(String token);
    
    /**
     用户退出登录
     @param token
     */
    void loginOut(String token);
    
    /**
     条件用户查询
     @param sysUserDto
     @param pageNum
     @param pageSize
     @return
     */
    PageInfo<SysUser> findByPage(SysUserDto sysUserDto,
                                 Integer pageNum,
                                 Integer pageSize);
    
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
     根据id删除用户信息
     @param userId
     */
    void deleteById(Long userId);
}
