package com.codelucky.spzx.manager.service.impl.system;

import com.codelucky.spzx.manager.mapper.system.SysRoleUserMapper;
import com.codelucky.spzx.manager.service.system.SysRoleUserService;
import com.codelucky.spzx.model.dto.system.AssginRoleDto;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleUserServiceImpl implements SysRoleUserService {
    
    @Resource
    private SysRoleUserMapper sysRoleUserMapper;
    
    /**
     保存分配角色
     @param assginRoleDto
     */
    @Override
    public void doAssign(AssginRoleDto assginRoleDto) {
        // 删除当前用户原来的角色
        sysRoleUserMapper.deleteByUserId(assginRoleDto.getUserId());
        // 保存
        List<Long> roleIdList = assginRoleDto.getRoleIdList();
        // 遍历
        roleIdList.forEach(roleId -> {
            sysRoleUserMapper.dodoAssign(roleId, assginRoleDto.getUserId());
        });
        
    }
}
