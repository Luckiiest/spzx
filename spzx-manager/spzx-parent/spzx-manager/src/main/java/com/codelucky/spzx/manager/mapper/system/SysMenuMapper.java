package com.codelucky.spzx.manager.mapper.system;

import com.codelucky.spzx.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuMapper {
    /**
     查询所有的菜单
     @return
     */
    List<SysMenu> findNodes();
    
    /**
     新增菜单
     @param sysMenu
     */
    void save(SysMenu sysMenu);
    
    /**
     根据id修改菜单
     @param sysMenu
     */
    void updateById(SysMenu sysMenu);
    
    /**
     统计一级菜单是否有菜单数据
     @param id
     @return
     */
    int countByParentId(Long id);
    
    /**
     删除菜单
     @param id
     */
    void deleteById(Long id);
    
    /**
     查询用户拥有的菜单列表
     @param userId
     @return
     */
    List<SysMenu> selectByUserId(Long userId);
}
