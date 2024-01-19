package com.snowline.spzx.manager.mapper;

import com.snowline.spzx.model.dto.system.AssginMenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-16 23:37
 */

@Mapper
public interface SysRoleMenuMapper {


    //    查询角色配分过的菜单id列表
    List<Long> findSysRoleMenuByRoleId(Long roleId);


    //删除角色分配的菜单数据
    void deleteByRoleId(Long roleId);


    //分配角色菜单数据
    void doAssign(AssginMenuDto assignMenuDto);


//    把isHalf值改为1
    void updateSysRoleMenuIsHalf(Long menuId);
}
