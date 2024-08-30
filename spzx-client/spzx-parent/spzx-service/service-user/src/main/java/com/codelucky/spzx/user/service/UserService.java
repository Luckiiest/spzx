package com.codelucky.spzx.user.service;

import com.codelucky.spzx.model.dto.h5.UserLoginDto;
import com.codelucky.spzx.model.dto.h5.UserRegisterDto;
import com.codelucky.spzx.model.vo.h5.UserInfoVo;

public interface UserService {

    /**
     * 用户注册
     *
     * @param userRegisterDto
     */
    void register(UserRegisterDto userRegisterDto);

    /**
     * 用户登陆
     *
     * @param userLoginDto
     * @param ip
     * @return
     */
    String login(UserLoginDto userLoginDto, String ip);

    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    UserInfoVo getCurrentUserInfo(String token);
}
