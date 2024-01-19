package com.snowline.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.snowline.spzx.model.dto.system.SysRoleDto;
import com.snowline.spzx.model.entity.system.SysRole;

/**
 * @author Snow
 * @create 2024-01-14 16:53
 */
public interface SysRoleService {


    //角色列表的方法
    //current：当前页   limit：每页条数 SysRoleDto:条件角色名称对象
    PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit);

    //角色添加方法
    void saveSysRole(SysRole sysRole);


    ////角色修改方法
    void updateSysRole(SysRole sysRole);


    //角色删除方法
    void deleteById(Long roleId);
}
