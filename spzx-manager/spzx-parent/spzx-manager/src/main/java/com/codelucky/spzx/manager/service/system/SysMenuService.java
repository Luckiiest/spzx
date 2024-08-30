package com.codelucky.spzx.manager.service.system;

import com.codelucky.spzx.model.entity.system.SysMenu;
import com.codelucky.spzx.model.vo.system.SysMenuVo;

import java.util.List;

public interface SysMenuService {
    /**
     查询所有菜单数据
     @return
     */
    List<SysMenu> findNodes();
    
    /**
     新增菜单
     @param sysMenu
     */
    void save(SysMenu sysMenu);
    
    /**
     修改菜单
     @param sysMenu
     */
    void updateById(SysMenu sysMenu);
    
    /**
     删除菜单
     @param id
     */
    void removeById(Long id);
    
    /**
     查询用户拥有的菜单列表
     @return
     */
    List<SysMenuVo> menus();
}
