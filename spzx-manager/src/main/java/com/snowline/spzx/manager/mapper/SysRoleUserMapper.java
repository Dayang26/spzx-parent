package com.snowline.spzx.manager.mapper;

import com.snowline.spzx.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-16 15:13
 */

@Mapper
public interface SysRoleUserMapper {

    //查询所有角色
    List<SysRole> findAllRoles();

    //删除用户userId之前分配的角色
    void deleteByUserId(Long userId);


    //重新分配新角色
    void doAssign(Long userId, Long roleId);


    //根据用户userId查询用户分配过的角色id列表
    List<Long> selectRoleIdsByUserId(Long userId);
}
