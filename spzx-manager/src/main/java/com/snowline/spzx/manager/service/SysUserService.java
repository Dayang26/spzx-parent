package com.snowline.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.snowline.spzx.model.dto.system.LoginDto;
import com.snowline.spzx.model.dto.system.SysUserDto;
import com.snowline.spzx.model.entity.system.SysUser;
import com.snowline.spzx.model.vo.system.LoginVo;
import org.springframework.stereotype.Service;

/**
 * @author Snow
 * @create 2024-01-13 2:17
 */


public interface SysUserService {
    //登录流程
    LoginVo login(LoginDto loginDto);

    //获取当前登录的用户信息
    SysUser getUserInfo(String token);

    //用户退出
    void logout(String token);


    //用户条件分页查询接口
    PageInfo<SysUser> findByPage(Integer pageNum, Integer pageSize, SysUserDto sysUserDto);


    //用户的添加
    void saveSysUser(SysUser sysUser);


    //用户修改
    void updateSysUser(SysUser sysUser);

    //用户删除
    void deleteById(Integer userId);
}
