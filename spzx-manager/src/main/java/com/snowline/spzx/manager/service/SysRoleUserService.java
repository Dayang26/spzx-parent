package com.snowline.spzx.manager.service;

import com.snowline.spzx.model.dto.system.AssginRoleDto;

import java.util.Map;

/**
 * @author Snow
 * @create 2024-01-16 14:59
 */
public interface SysRoleUserService {

    /**
     * 查询所有角色
     *
     * @return
     */
    Map<String, Object> findAllRoles(Long userId);


    //用户分配角色，保存分配的数据
    void doAssign(AssginRoleDto assginRoleDto);
}
