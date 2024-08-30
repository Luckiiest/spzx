package com.codelucky.spzx.manager.service.impl.system;

import cn.hutool.core.collection.CollectionUtil;
import com.codelucky.spzx.manager.mapper.system.SysRoleMenuMapper;
import com.codelucky.spzx.manager.service.system.SysMenuService;
import com.codelucky.spzx.manager.service.system.SysRoleMenuService;
import com.codelucky.spzx.model.dto.system.AssginMenuDto;
import com.codelucky.spzx.model.entity.system.SysMenu;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
    
    @Resource
    private SysMenuService sysMenuService;
    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;
    
    /**
     查询所有菜单和当前角色拥有的菜单Id
     @param roleId
     @return
     */
    @Override
    public Map<String, Object> findSysRoleMenuByRoleId(Long roleId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        // 查询所有菜单的列表
        List<SysMenu> sysMenusTree = sysMenuService.findNodes();
        // 查询当前角色拥有的菜单Id列表
        List<Long> menuIds = sysRoleMenuMapper.findSysRoleMenuByRoleId(roleId);
        
        resultMap.put("sysMenusTree", sysMenusTree);
        resultMap.put("menuIds", menuIds);
        
        return resultMap;
    }
    
    /**
     给指定角色分配菜单
     @param assginMenuDto
     */
    @Override
    public void doAssign(AssginMenuDto assginMenuDto) {
        // 根据角色的id删除其所对应的菜单数据
        sysRoleMenuMapper.deleteByRoleId(assginMenuDto.getRoleId());
        // 获取菜单的id
        List<Map<String, Number>> menuIdList = assginMenuDto.getMenuIdList();
        if (!CollectionUtil.isEmpty(menuIdList)) {
            // 保存用户选择的菜单
            sysRoleMenuMapper.doAssign(assginMenuDto);
        }
    }
}
