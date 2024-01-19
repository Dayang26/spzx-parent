package com.snowline.spzx.manager.service.impl;

import com.snowline.spzx.manager.mapper.SysRoleMenuMapper;
import com.snowline.spzx.manager.service.SysMenuService;
import com.snowline.spzx.manager.service.SysRoleMenuService;
import com.snowline.spzx.model.dto.system.AssginMenuDto;
import com.snowline.spzx.model.entity.system.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Snow
 * @create 2024-01-16 23:36
 */

@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {


    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;


    @Autowired
    private SysMenuService sysMenuService;

    //查询所有才散 和查询角色分配过的菜单id列表
    @Override
    public Map<String, Object> findSysRoleMenuByRoleId(Long roleId) {

        //查询所有菜单
        List<SysMenu> sysMenuList = sysMenuService.findNodes();

        //查询角色配分过的菜单id列表
        List<Long> roleMenuIds = sysRoleMenuMapper.findSysRoleMenuByRoleId(roleId);

        Map<String, Object> map = new HashMap<>();
        map.put("sysMenuList", sysMenuList);
        map.put("roleMenuIds", roleMenuIds);

        return map;
    }





    //    保存分配的菜单数据
    @Override
    public void doAssign(AssginMenuDto assignMenuDto) {

        sysRoleMenuMapper.deleteByRoleId(assignMenuDto.getRoleId());

        List<Map<String, Number>> menuInfo = assignMenuDto.getMenuIdList();
        if (menuInfo != null && !menuInfo.isEmpty()) {
            sysRoleMenuMapper.doAssign(assignMenuDto);
        }

    }
}
