package com.codelucky.spzx.manager.service.impl.system;

import com.codelucky.spzx.manager.helper.MenuHelper;
import com.codelucky.spzx.manager.mapper.system.SysMenuMapper;
import com.codelucky.spzx.manager.service.system.SysMenuService;
import com.codelucky.spzx.model.entity.system.SysMenu;
import com.codelucky.spzx.model.entity.system.SysUser;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import com.codelucky.spzx.model.vo.system.SysMenuVo;
import com.codelucky.spzx.service.exception.CodeLuckyException;
import com.codelucky.spzx.util.AuthContextUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Resource
    private SysMenuMapper sysMenuMapper;
    
    /**
     查询所有菜单数据
     @return
     */
    @Override
    public List<SysMenu> findNodes() {
        // 查询所有菜单数据
        List<SysMenu> sysMenuList = sysMenuMapper.findNodes();
        // 判断
        if (CollectionUtils.isEmpty(sysMenuList)) return null;
        // 构建树形结构
        List<SysMenu> sysMenuListTree = MenuHelper.buildTree(sysMenuList);
        return sysMenuListTree;
    }
    
    /**
     新增菜单
     @param sysMenu
     */
    @Override
    public void save(SysMenu sysMenu) {
        sysMenuMapper.save(sysMenu);
    }
    
    /**
     修改菜单
     @param sysMenu
     */
    @Override
    public void updateById(SysMenu sysMenu) {
        sysMenuMapper.updateById(sysMenu);
    }
    
    /**
     删除菜单
     @param id
     */
    @Override
    public void removeById(Long id) {
        // 根据当前菜单id查询子菜单的数量
        int count = sysMenuMapper.countByParentId(id);
        // 判断如果有子菜单不删除,抛出异常,前端提示
        if (count > 0) {
            throw new CodeLuckyException(ResultCodeEnum.NODE_ERROR);
        }
        // 不存在子菜单直接删除
        sysMenuMapper.deleteById(id);
    }
    
    /**
     查询用户拥有的菜单列表
     @return
     */
    @Override
    public List<SysMenuVo> menus() {
        
        SysUser sysUser = AuthContextUtil.get();
        Long userId = sysUser.getId();          // 获取当前登录用户的id
        List<SysMenu> sysMenuList = sysMenuMapper.selectByUserId(userId);
        //构建树形数据
        List<SysMenu> sysMenuTreeList = MenuHelper.buildTree(sysMenuList);
        return this.buildMenus(sysMenuTreeList);
    }
    
    // 将List<SysMenu>对象转换成List<SysMenuVo>对象
    private List<SysMenuVo> buildMenus(List<SysMenu> menus) {
        
        List<SysMenuVo> sysMenuVoList = new LinkedList<>();
        for (SysMenu sysMenu : menus) {
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setTitle(sysMenu.getTitle());
            sysMenuVo.setName(sysMenu.getComponent());
            List<SysMenu> children = sysMenu.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                sysMenuVo.setChildren(buildMenus(children));
            }
            sysMenuVoList.add(sysMenuVo);
        }
        return sysMenuVoList;
    }
}
