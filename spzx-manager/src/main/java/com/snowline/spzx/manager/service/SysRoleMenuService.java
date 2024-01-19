package com.snowline.spzx.manager.service;

import com.snowline.spzx.model.dto.system.AssginMenuDto;

import java.util.Map;

/**
 * @author Snow
 * @create 2024-01-16 23:36
 */
public interface SysRoleMenuService {


    //查询所有才散 和查询角色分配过的菜单id列表
    Map<String, Object> findSysRoleMenuByRoleId(Long roleId);



//    保存分配的菜单数据
    void doAssign(AssginMenuDto assignMenuDto);
}
