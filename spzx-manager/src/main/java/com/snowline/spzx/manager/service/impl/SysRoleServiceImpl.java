package com.snowline.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snowline.spzx.manager.mapper.SysRoleMapper;
import com.snowline.spzx.manager.service.SysRoleService;
import com.snowline.spzx.model.dto.system.SysRoleDto;
import com.snowline.spzx.model.entity.system.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-14 16:53
 */

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    //角色列表的方法
    //current：当前页   limit：每页条数 SysRoleDto:条件角色名称对象
    @Override
    public PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit) {
        //设置pageHelper参数
        PageHelper.startPage(current, limit);
        //根据条件查询所有数据
        List<SysRole> list = sysRoleMapper.findByPage(sysRoleDto);
        //封装pageInfo对象
        PageInfo<SysRole> listPageInfo = new PageInfo<>(list);

        return listPageInfo;
    }


    //角色添加方法
    @Override
    public void saveSysRole(SysRole sysRole) {
        sysRoleMapper.save(sysRole);
    }


    //角色修改方法
    @Override
    public void updateSysRole(SysRole sysRole) {
        sysRoleMapper.update(sysRole);
    }


    //角色删除方法
    @Override
    public void deleteById(Long roleId) {
        sysRoleMapper.deleteById(roleId);
    }
}
