package com.snowline.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.snowline.spzx.manager.service.SysRoleService;
import com.snowline.spzx.manager.service.SysRoleUserService;
import com.snowline.spzx.model.dto.system.SysRoleDto;
import com.snowline.spzx.model.entity.system.SysRole;
import com.snowline.spzx.model.vo.common.Result;
import com.snowline.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

/**
 * @author Snow
 * @create 2024-01-14 16:52
 */

@RestController
@RequestMapping(value = "/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleUserService sysRoleUserService;

    @Autowired
    private SysRoleService sysRoleService;

    //角色列表的方法
    //current：当前页   limit：每页条数 SysRoleDto:条件角色名称对象
    @PostMapping("/findByPage/{current}/{limit}")
    public Result findByPage(@PathVariable("current") Integer current,
                             @PathVariable("limit") Integer limit,
                             @RequestBody SysRoleDto sysRoleDto) {

        //pageHelper插件实现分页
        PageInfo<SysRole> pageInfo = sysRoleService.findByPage(sysRoleDto, current, limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }


    //角色添加方法
    @PostMapping("/saveSysRole")
    public Result saveSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.saveSysRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }


    //角色修改方法
    @PutMapping("/updateSysRole")
    public Result updateSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.updateSysRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }


    //角色删除方法
    @DeleteMapping("/deleteById/{roleId}")
    public Result deleteById(@PathVariable("roleId") Long roleId) {
        sysRoleService.deleteById(roleId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }


    /**
     * 查询所有角色
     *
     * @return
     */
    @GetMapping("/findAllRoles/{userId}")
    public Result findAllRoles(@PathVariable("userId") Long userId) {
        Map<String, Object> map = sysRoleUserService.findAllRoles(userId);
        return Result.build(map, ResultCodeEnum.SUCCESS);
    }
}
