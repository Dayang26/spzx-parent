package com.snowline.spzx.utils;

import com.snowline.spzx.model.entity.system.SysUser;

/**
 * @author Snow
 * @create 2024-01-14 1:27
 */
public class AuthContextUtils {
    //创建threadLocal对象
    private static final ThreadLocal<SysUser> threadLocal = new ThreadLocal<>();

    //添加数据
    public static void set(SysUser sysUser) {
        threadLocal.set(sysUser);
    }

    //获取数据
    public static SysUser get(){
        return threadLocal.get();
    }

    //删除数据
    public static void remove(){
        threadLocal.remove();
    }
}
