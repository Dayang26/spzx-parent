package com.snowline.spzx.manager.service.impl;

import com.snowline.spzx.manager.mapper.SysRoleUserMapper;
import com.snowline.spzx.manager.service.SysRoleUserService;
import com.snowline.spzx.model.dto.system.AssginRoleDto;
import com.snowline.spzx.model.entity.system.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Snow
 * @create 2024-01-16 14:59
 */

@Service
public class SysRoleUserServiceImpl implements SysRoleUserService {

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;


    /**
     * 查询所有角色
     *
     * @return
     */
    @Override
    public Map<String, Object> findAllRoles(Long userId) {
        //  查询所有角色
        List<SysRole> roleList = sysRoleUserMapper.findAllRoles();

        //  分配过的角色列表
        //  根据用户userId查询用户分配过的角色id列表
        List<Long> roleIds = sysRoleUserMapper.selectRoleIdsByUserId(userId);


        HashMap<String, Object> map = new HashMap<>();
        map.put("allRolesList", roleList);
        map.put("sysUserRoles", roleIds);
        return map;
    }


    //用户分配角色，保存分配的数据
    @Override
    public void doAssign(AssginRoleDto assginRoleDto) {
        //删除用户userId之前分配的角色
        sysRoleUserMapper.deleteByUserId(assginRoleDto.getUserId());


        //重新分配新角色
        for (Long roleId : assginRoleDto.getRoleIdList()) {
            sysRoleUserMapper.doAssign(assginRoleDto.getUserId(), roleId);
        }


    }
}
