package com.snowline.spzx.manager.service;

import com.snowline.spzx.model.entity.system.SysMenu;
import com.snowline.spzx.model.vo.system.SysMenuVo;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-16 18:12
 */
public interface SysMenuService {

    //菜单列表
    List<SysMenu> findNodes();


    //菜单添加
    void save(SysMenu sysMenu);


    //菜单修改
    void update(SysMenu sysMenu);


    //根据菜单id删除
    void removeById(Long id);


    //查询用户可以操作的菜单
    List<SysMenuVo> findMenusByUserId();
}
