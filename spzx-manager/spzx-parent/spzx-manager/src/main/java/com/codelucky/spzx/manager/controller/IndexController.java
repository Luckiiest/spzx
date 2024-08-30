package com.codelucky.spzx.manager.controller;

import com.codelucky.spzx.manager.service.ValidateCodeService;
import com.codelucky.spzx.manager.service.system.SysMenuService;
import com.codelucky.spzx.manager.service.system.SysUserService;
import com.codelucky.spzx.model.dto.system.LoginDto;
import com.codelucky.spzx.model.entity.system.SysUser;
import com.codelucky.spzx.model.vo.common.Result;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import com.codelucky.spzx.model.vo.system.LoginVo;
import com.codelucky.spzx.model.vo.system.SysMenuVo;
import com.codelucky.spzx.model.vo.system.ValidateCodeVo;
import com.codelucky.spzx.util.AuthContextUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/system/index")
@Tag(name = "首页接口")
public class IndexController {
    
    @Resource
    private SysUserService sysUserService;
    @Resource
    private ValidateCodeService validateCodeService;
    @Resource
    private SysMenuService sysMenuService;
    
    /**
     查询用户拥有的菜单列表
     @return
     */
    @GetMapping("/menus")
    public Result<List<SysMenuVo>> menus() {
        List<SysMenuVo> sysMenuVoList = sysMenuService.menus();
        return Result.build(sysMenuVoList, ResultCodeEnum.SUCCESS);
    }
    
    /**
     用户退出登录
     @param token
     @return
     */
    @GetMapping("logout")
    @Operation(summary = "退出登录")
    public Result logout(@RequestHeader(name = "token") String token) {
        sysUserService.loginOut(token);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    
    /**
     获取用户信息
     @param token
     @return
     */
    @GetMapping("/userinfo")
    @Operation(summary = "获取用户信息")
    public Result<SysUser> getUserInfo(@RequestHeader(name = "token") String token) {
        //SysUser sysUser = sysUserService.getUserInfo(token);
        SysUser sysUser = AuthContextUtil.get();
        return Result.build(sysUser, ResultCodeEnum.SUCCESS);
    }
    
    /**
     获取验证码
     @return
     */
    @GetMapping("/generateValidateCode")
    @Operation(summary = "获取验证码")
    public Result<ValidateCodeVo> generateValidateCode() {
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return Result.build(validateCodeVo, ResultCodeEnum.SUCCESS);
    }
    
    /**
     用户登录
     @param loginDto
     @return
     */
    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public Result<LoginVo> login(@RequestBody LoginDto loginDto) {
        System.out.println("loginDto: " + loginDto);
        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }
}
