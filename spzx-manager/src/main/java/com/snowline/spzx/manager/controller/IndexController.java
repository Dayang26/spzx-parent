package com.snowline.spzx.manager.controller;

import com.snowline.spzx.manager.service.SysMenuService;
import com.snowline.spzx.manager.service.SysUserService;
import com.snowline.spzx.manager.service.ValidateCodeService;
import com.snowline.spzx.model.dto.system.LoginDto;
import com.snowline.spzx.model.entity.system.SysUser;
import com.snowline.spzx.model.vo.common.Result;
import com.snowline.spzx.model.vo.common.ResultCodeEnum;
import com.snowline.spzx.model.vo.system.LoginVo;
import com.snowline.spzx.model.vo.system.SysMenuVo;
import com.snowline.spzx.model.vo.system.ValidateCodeVo;
import com.snowline.spzx.utils.AuthContextUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-13 2:14
 */

@Tag(name = "用户接口")
@RestController
@RequestMapping(value = "/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ValidateCodeService validateCodeService;

    @Autowired
    private SysMenuService sysMenuService;


    //login
    @Operation(summary = "登录的方法")
    @PostMapping("/login")
    public Result login(@RequestBody LoginDto loginDto) {
        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }

    //生成图片验证码
    @Operation(summary = "生成图片验证码")
    @GetMapping(value = "/generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode() {
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return Result.build(validateCodeVo, ResultCodeEnum.SUCCESS);
    }

//    获取当前登录的用户信息
//    @Operation(summary = "获取当前登录的用户信息")
//    @GetMapping("/getUserInfo")
//    public Result getUserInfo(@RequestHeader(name = "token") String token) {
//        //从请求头里面获取token
////        HttpServletRequest request
////        String token = request.getHeader("token");
//        //根据token去查redis 获取用户信息
//        SysUser userInfo = sysUserService.getUserInfo(token);
//
//        //把用户信息返回
//        return Result.build(userInfo, ResultCodeEnum.SUCCESS);
//    }


    //    获取当前登录的用户信息 from ThreadLocal
    @Operation(summary = "获取当前登录的用户信息")
    @GetMapping("/getUserInfo")
    public Result getUserInfo() {
        return Result.build(AuthContextUtils.get(), ResultCodeEnum.SUCCESS);
    }

    //用户退出
    @GetMapping(value = "/logout")
    public Result logout(@RequestHeader(name = "token") String token) {
        sysUserService.logout(token);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }


    //查询用户可以操作的菜单
    @GetMapping("/menus")
    public Result menus() {
        List<SysMenuVo> list = sysMenuService.findMenusByUserId();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
}
