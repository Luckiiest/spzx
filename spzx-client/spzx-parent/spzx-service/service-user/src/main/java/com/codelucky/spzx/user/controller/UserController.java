package com.codelucky.spzx.user.controller;

import com.codelucky.spzx.model.dto.h5.UserLoginDto;
import com.codelucky.spzx.model.dto.h5.UserRegisterDto;
import com.codelucky.spzx.model.vo.common.Result;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import com.codelucky.spzx.model.vo.h5.UserInfoVo;
import com.codelucky.spzx.user.service.UserService;
import com.codelucky.spzx.util.IpUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/userInfo")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     *
     * @param userRegisterDto
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody UserRegisterDto userRegisterDto) {
        userService.register(userRegisterDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * 用户登陆
     *
     * @param userLoginDto
     * @param request
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody UserLoginDto userLoginDto, HttpServletRequest request) {
        // 获取当前请求ip
        String ip = IpUtil.getIpAddress(request);
        String token = userService.login(userLoginDto, ip);
        return Result.build(token, ResultCodeEnum.SUCCESS);
    }

    /**
     * 获取用户信息
     *
     * @param request
     * @return
     */
    @GetMapping("/auth/getCurrentUserInfo")
    public Result<UserInfoVo> getCurrentUserInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        UserInfoVo userInfoVo = userService.getCurrentUserInfo(token);
        return Result.build(userInfoVo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 获取用户购物车信息
     *
     * @param userId
     * @return
     */
    @GetMapping("/isCollect/{userId}")
    public Result<Boolean> isCollect(@PathVariable("userId") String userId) {

        return Result.build(null, ResultCodeEnum.SUCCESS);
    }


}
