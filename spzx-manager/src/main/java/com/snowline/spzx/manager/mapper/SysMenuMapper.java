package com.snowline.spzx.manager.mapper;

import com.snowline.spzx.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-16 19:05
 */


@Mapper
public interface SysMenuMapper {
    List<SysMenu> findAll();


    //添加
    void save(SysMenu sysMenu);

    //修改
    void update(SysMenu sysMenu);

    //根据菜单id删除
    void removeById(Long id);

    //根据当前菜单id查询，是否包含子菜单
    int selectCountById(Long id);

    //查询用户可以操作的菜单
    List<SysMenu> findMenusByUserId(Long id);

    //获取当前添加菜单的父菜单
    SysMenu getParentMenu(Long parentId);
}
