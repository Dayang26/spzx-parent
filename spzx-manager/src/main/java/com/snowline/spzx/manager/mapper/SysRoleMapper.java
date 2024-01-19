package com.snowline.spzx.manager.mapper;

import com.snowline.spzx.model.dto.system.SysRoleDto;
import com.snowline.spzx.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-14 16:54
 */

@Mapper
public interface SysRoleMapper {
    //角色分页查询
    List<SysRole> findByPage(SysRoleDto sysRoleDto);


    //添加角色
    void save(SysRole sysRole);

    void update(SysRole sysRole);

    //角色删除方法
    void deleteById(Long roleId);
}
