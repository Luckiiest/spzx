package com.codelucky.spzx.user.service.impl;

import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.codelucky.spzx.model.dto.h5.UserLoginDto;
import com.codelucky.spzx.model.dto.h5.UserRegisterDto;
import com.codelucky.spzx.model.entity.user.UserInfo;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import com.codelucky.spzx.model.vo.h5.UserInfoVo;
import com.codelucky.spzx.service.exception.CodeLuckyException;
import com.codelucky.spzx.user.mapper.UserInfoMapper;
import com.codelucky.spzx.user.service.UserService;
import com.codelucky.spzx.util.AuthContextUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 用户注册
     * <p>
     * 1. 验证所有信息
     * 2. 验证验证码
     * 3. 验证用户是否存在
     * 4. 封装数据
     * 状态数据
     * 密码加密
     * 5. 保存
     * 6. 删除验证码
     *
     * @param userRegisterDto
     */
    @Override
    public void register(UserRegisterDto userRegisterDto) {
        // 获取数据
        String username = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        String nickName = userRegisterDto.getNickName();
        String code = userRegisterDto.getCode();

        //校验参数
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(nickName) || StringUtils.isEmpty(code)) {
            throw new CodeLuckyException(ResultCodeEnum.DATA_ERROR);
        }

        // 验证验证码
        String validatedCode = redisTemplate.opsForValue().get("phone:code" + username);
        if (StringUtils.equals(validatedCode, code)) {
            throw new CodeLuckyException(ResultCodeEnum.VALIDATECODE_ERROR);
        }

        // 验证用户都是否存在
        UserInfo userInfo = userInfoMapper.getByUsername(username);
        if (userInfo != null) {
            throw new CodeLuckyException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }

        // 封装数据
        userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userInfo.setNickName(nickName);
        userInfo.setPhone(username);
        userInfo.setStatus(1);
        userInfo.setAvatar("http://192.168.116.135:9000/spzx-bucket/default_avator.png");
        // 保存
        userInfoMapper.save(userInfo);
        // 删除验证码
        redisTemplate.delete("phone:code" + username);
    }

    /**
     * 用户登陆
     *
     * @param userLoginDto
     * @param ip
     * @return
     */
    @Override
    public String login(UserLoginDto userLoginDto, String ip) {
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();

        //校验参数
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new CodeLuckyException(ResultCodeEnum.DATA_ERROR);
        }

        UserInfo userInfo = userInfoMapper.getByUsername(username);
        if (null == userInfo) {
            throw new CodeLuckyException(ResultCodeEnum.LOGIN_ERROR);
        }

        //校验密码
        String md5InputPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!md5InputPassword.equals(userInfo.getPassword())) {
            throw new CodeLuckyException(ResultCodeEnum.LOGIN_ERROR);
        }

        //校验是否被禁用
        if (userInfo.getStatus() == 0) {
            throw new CodeLuckyException(ResultCodeEnum.ACCOUNT_STOP);
        }

        //更新登录信息
        userInfo.setLastLoginIp(ip);
        userInfo.setLastLoginTime(new Date());
        userInfoMapper.updateById(userInfo);

        String token = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.opsForValue().set("user:login:" + token, JSON.toJSONString(userInfo), 30, TimeUnit.DAYS);
        return token;
    }

    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    @Override
    public UserInfoVo getCurrentUserInfo(String token) {
        UserInfo userInfo = AuthContextUtil.getUserInfo();
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userInfo, userInfoVo);
        return userInfoVo;
    }
}
