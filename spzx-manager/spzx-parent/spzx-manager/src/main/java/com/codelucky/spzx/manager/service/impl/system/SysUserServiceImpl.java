package com.codelucky.spzx.manager.service.impl.system;

import cn.hutool.core.util.StrUtil;
import com.codelucky.spzx.manager.mapper.system.SysUserMapper;
import com.codelucky.spzx.manager.service.system.SysUserService;
import com.codelucky.spzx.model.dto.system.LoginDto;
import com.codelucky.spzx.model.dto.system.SysUserDto;
import com.codelucky.spzx.model.entity.system.SysUser;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import com.codelucky.spzx.model.vo.system.LoginVo;
import com.codelucky.spzx.service.exception.CodeLuckyException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.codelucky.spzx.service.config.ConstantConfig.USER_LOGIN;
import static com.codelucky.spzx.service.config.ConstantConfig.USER_LOGIN_VALIDATECODE;


@Service
public class SysUserServiceImpl implements SysUserService {
    
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private RedisTemplate redisTemplate;
    
    /**
     用户登录
     @param loginDto
     @return
     */
    @Override
    public LoginVo login(LoginDto loginDto) {
        if (loginDto == null) {
            throw new CodeLuckyException(ResultCodeEnum.LOGIN_ERROR);
        }
        
        // 校验验证码
        // 获取redis存储key
        String codeKey = loginDto.getCodeKey();
        // 获取用户输入的验证码
        String inputCaptcha = loginDto.getCaptcha();
        // 从redis获取当前验证码
        String redisCaptcha = (String) redisTemplate.opsForValue()
                                                    .get(USER_LOGIN_VALIDATECODE + codeKey);
        // 比较
        if (StrUtil.isEmpty(redisCaptcha) || !StrUtil.equalsIgnoreCase(inputCaptcha, redisCaptcha)) {
            throw new CodeLuckyException(ResultCodeEnum.VALIDATECODE_ERROR);
        }
        
        redisTemplate.delete(USER_LOGIN_VALIDATECODE + codeKey);
        
        //1. 根据用户名查询
        SysUser user = sysUserMapper.selectSysUserByUserName(loginDto.getUserName());
        if (user == null) {
            throw new CodeLuckyException(ResultCodeEnum.LOGIN_ERROR);
        }
        //2. 校验密码
        // 获取用户输入明文密码
        String password = loginDto.getPassword();
        // 加密
        String md5InputPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        // 比较
        if (!md5InputPassword.equals(user.getPassword())) {
            throw new CodeLuckyException(ResultCodeEnum.LOGIN_ERROR);
        }
        //3，生成Token
        String token = UUID.randomUUID()
                           .toString()
                           .replaceAll("-", "");
        //4. 存储到Redis 结构key value：string
        redisTemplate.opsForValue()
                     .set(USER_LOGIN + token, user, 30, TimeUnit.MINUTES);
        //5. 封装数据，响应
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        loginVo.setRefresh_token("");
        return loginVo;
    }
    
    /**
     获取用户信息
     @param token
     @return
     */
    @Override
    public SysUser getUserInfo(String token) {
        SysUser sysUser = (SysUser) redisTemplate.opsForValue()
                                                 .get(USER_LOGIN + token);
        //SysUser sysUser = JSON.parseObject(strUser, SysUser.class);
        return sysUser;
    }
    
    /**
     用户退出登录
     @param token
     */
    @Override
    public void loginOut(String token) {
        redisTemplate.delete(USER_LOGIN + token);
    }
    
    /**
     条件分页查询
     @param sysUserDto
     @param pageNum
     @param pageSize
     @return PageInfo<SysUser>
     */
    @Override
    public PageInfo<SysUser> findByPage(SysUserDto sysUserDto,
                                        Integer pageNum,
                                        Integer pageSize) {
        //设置分页条件
        PageHelper.startPage(pageNum, pageSize);
        //查询用户列表
        List<SysUser> sysUserList = sysUserMapper.findByPage(sysUserDto);
        //封装分页对象
        return new PageInfo<>(sysUserList);
    }
    
    /**
     新增用户
     @param sysUser
     */
    @Override
    public void saveSysUser(SysUser sysUser) {
        // 判断是否存在新增的用户
        SysUser user = sysUserMapper.selectSysUserByUserName(sysUser.getUserName());
        if (user != null) {
            throw new CodeLuckyException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }
        // 密码加密
        String userPassword = sysUser.getPassword();
        // 加密
        String password = DigestUtils.md5DigestAsHex(userPassword.getBytes());
        sysUser.setPassword(password);
        // 状态赋值
        sysUser.setStatus(0);
        // 新增
        sysUserMapper.saveSysUser(sysUser);
    }
    
    /**
     修改用户信息
     @param sysUser
     */
    @Override
    public void updateSysUser(SysUser sysUser) {
        // 根据用户名id查询用户名
        //SysUser userById = sysUserMapper.selectSysUserById(sysUser.getId());
        // 根据用户名查询用户
        SysUser userByUserName = sysUserMapper.selectSysUserByUserName(sysUser.getUserName());
        // 判断本用户名id用户名是否存在
        // 判断本用户的用户名是否和数据库存在用户名是否相同，若不相同则代表其他用户名已存在
        if (userByUserName != null && !userByUserName.getId()
                                                     .equals(sysUser.getId())) {
            throw new CodeLuckyException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }
        //修改用户信息
        sysUserMapper.updateSysUser(sysUser);
    }
    
    /**
     根据id删除用户信息
     @param userId
     */
    @Override
    public void deleteById(Long userId) {
        sysUserMapper.deleteById(userId);
    }
}
