package com.snowline.spzx.manager.mapper;

import com.snowline.spzx.model.dto.system.SysUserDto;
import com.snowline.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-13 2:19
 */

@Mapper
public interface SysUserMapper {


    //根据用户名查询数据库sys_user
    SysUser selectUserInfoByUserName(String userName);


    //用户条件分页查询接口
    List<SysUser> findByPage(SysUserDto sysUserDto);

    //用户的添加
    void save(SysUser sysUser);

    //用户修改
    void update(SysUser sysUser);


    //用户删除
    void deleteById(Integer userId);
}
