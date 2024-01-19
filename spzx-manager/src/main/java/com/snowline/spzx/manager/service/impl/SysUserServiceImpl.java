package com.snowline.spzx.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snowline.spzx.common.exception.SnowLineException;
import com.snowline.spzx.manager.mapper.SysUserMapper;
import com.snowline.spzx.manager.service.SysUserService;
import com.snowline.spzx.model.dto.system.LoginDto;
import com.snowline.spzx.model.dto.system.SysUserDto;
import com.snowline.spzx.model.entity.system.SysUser;
import com.snowline.spzx.model.vo.common.ResultCodeEnum;
import com.snowline.spzx.model.vo.system.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Snow
 * @create 2024-01-13 2:18
 */

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //用户登录
    @Override
    public LoginVo login(LoginDto loginDto) {

        //获取输入的验证码
        String captcha = loginDto.getCaptcha();
        //获取存储到redis的key名称
        String key = loginDto.getCodeKey();

        //根据redis里面的key 查询reids 获取验证码
        String redisCode = redisTemplate.opsForValue().get("user:validate" + key);

        //比较验证码和输入的验证码
        if (StrUtil.isEmpty(redisCode) || !StrUtil.equalsIgnoreCase(redisCode, captcha)) {
            //如果不一致，提示用户 校验失败
            throw new SnowLineException(ResultCodeEnum.VALIDATECODE_ERROR);
        }

        //如果一致 删除redis里面的验证码
        redisTemplate.delete("user:validate" + key);

        // 获取提交用户名from LoginDto
        String userName = loginDto.getUserName();

        //根据用户名查询数据库sys_user
        SysUser sysUser = sysUserMapper.selectUserInfoByUserName(userName);

        //如果根据用户名查询不到信息，用户不存在，返回错误信息
        if (sysUser == null) {
            throw new SnowLineException(ResultCodeEnum.LOGIN_ERROR);
        }

        //如果根据用户名查询到用户信息，用户存在
        //获取输入的密码，校验密码
        String database_password = sysUser.getPassword();
        String input_password = DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes());

        //比较两个密码
        if (!input_password.equals(database_password)) {
            throw new SnowLineException(ResultCodeEnum.LOGIN_ERROR);
        }

        //密码一致,登录成功，否则失败
        //登录成功，生成用户唯一标识token
        String token = UUID.randomUUID().toString().replaceAll("-", "");

        //把登录用户信息放到redis   key:token value:userInfo

        redisTemplate.opsForValue().set("user:login" + token, JSON.toJSONString(sysUser), 7, TimeUnit.DAYS);

        //返回loginvo对象
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);

        return loginVo;
    }


    //获取当前登录的用户信息
    @Override
    public SysUser getUserInfo(String token) {
        String userJson = redisTemplate.opsForValue().get("user:login" + token);
        //把Json字符串转成对象并且返回
        return JSON.parseObject(userJson, SysUser.class);
    }

    //用户退出
    @Override
    public void logout(String token) {
        redisTemplate.delete("user:login" + token);
    }


    //用户条件分页查询接口
    @Override
    public PageInfo<SysUser> findByPage(Integer pageNum, Integer pageSize, SysUserDto sysUserDto) {


        PageHelper.startPage(pageNum, pageSize);
        List<SysUser> list = sysUserMapper.findByPage(sysUserDto);
        return new PageInfo<>(list);
    }

    //用户的添加
    @Override
    public void saveSysUser(SysUser sysUser) {
        //判断用户名不能重复
        String input_userName = sysUser.getUserName();
        SysUser db_user = sysUserMapper.selectUserInfoByUserName(input_userName);
        if (db_user != null) {
            throw new SnowLineException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }

        //密码加密
        String md5_password = DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes());
        sysUser.setPassword(md5_password);

        //设置status值 1:可用 2:不可用
        sysUser.setStatus(1);

        sysUserMapper.save(sysUser);
    }

    //用户修改
    @Override
    public void updateSysUser(SysUser sysUser) {
        //判断用户名不能重复
        String input_userName = sysUser.getUserName();
        SysUser db_user = sysUserMapper.selectUserInfoByUserName(input_userName);
        if (db_user != null) {
            if (!Objects.equals(sysUser.getId(), db_user.getId())) {
                throw new SnowLineException(ResultCodeEnum.USER_NAME_IS_EXISTS);
            }
        }

        sysUserMapper.update(sysUser);
    }

    //用户删除
    @Override
    public void deleteById(Integer userId) {
        sysUserMapper.deleteById(userId);
    }


}

