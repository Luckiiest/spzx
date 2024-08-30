package com.codelucky.spzx.manager.service.impl.system;

import com.codelucky.spzx.manager.mapper.system.SysRoleMapper;
import com.codelucky.spzx.manager.mapper.system.SysRoleUserMapper;
import com.codelucky.spzx.manager.service.system.SysRoleService;
import com.codelucky.spzx.model.dto.system.SysRoleDto;
import com.codelucky.spzx.model.entity.system.SysRole;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysRoleUserMapper sysRoleUserMapper;
    
    /**
     条件分页查询角色列表
     @param sysRoleDto
     @param pageNum
     @param pageSize
     @return
     */
    @Override
    public PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer pageNum, Integer pageSize) {
        // 封装分页查询条件
        PageHelper.startPage(pageNum, pageSize);
        // 查询角色列表
        List<SysRole> sysRoleList = sysRoleMapper.findByPage(sysRoleDto);
        // 转换数据类型
        return new PageInfo<>(sysRoleList);
    }
    
    /**
     保护角色
     @param sysRole
     */
    @Override
    public void saveSysRole(SysRole sysRole) {
        sysRoleMapper.saveSysRole(sysRole);
    }
    
    /**
     修改保存角色
     @param sysRole
     */
    @Override
    public void updateSysRole(SysRole sysRole) {
        sysRoleMapper.updateSysRole(sysRole);
    }
    
    /**
     根据id删除角色
     @param id
     */
    @Override
    public void deleteSysRoleById(Long id) {
        sysRoleMapper.deleteSysRoleById(id);
    }
    
    /**
     查询所有的角色以及查询当前用户拥有的角色Id
     @return
     */
    @Override
    public Map<String, Object> findAllRoles(Long userId) {
        // 创建对象封装数据
        Map<String, Object> resultMap = new HashMap<>();
        // 查询所有的角色
        List<SysRole> allRoles = sysRoleMapper.findAllRoles();
        // 查询当前用户拥有的角色Id
        List<Long> roleIds = sysRoleUserMapper.selectRolesIdsByUserId(userId);
        resultMap.put("allRoles", allRoles);
        resultMap.put("roleIds", roleIds);
        return resultMap;
    }
}
