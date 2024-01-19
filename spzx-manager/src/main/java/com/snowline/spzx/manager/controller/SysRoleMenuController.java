package com.snowline.spzx.manager.controller;

import com.snowline.spzx.manager.service.SysRoleMenuService;
import com.snowline.spzx.model.dto.system.AssginMenuDto;
import com.snowline.spzx.model.vo.common.Result;
import com.snowline.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Snow
 * @create 2024-01-16 23:34
 */

@RestController
@RequestMapping("/admin/system/sysRoleMenu")
public class SysRoleMenuController {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    //查询所有才散 和查询角色分配过的菜单id列表
    @GetMapping("/findSysRoleMenuByRoleId/{roleId}")
    public Result findSysRoleMenuByRoleId(@PathVariable Long roleId) {

        Map<String, Object> map = sysRoleMenuService.findSysRoleMenuByRoleId(roleId);

        return Result.build(map, ResultCodeEnum.SUCCESS);
    }


    //保存角色分配菜单数据

    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginMenuDto assignMenuDto) {
        sysRoleMenuService.doAssign(assignMenuDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
