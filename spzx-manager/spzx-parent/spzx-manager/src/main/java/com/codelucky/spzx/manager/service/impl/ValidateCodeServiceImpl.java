package com.codelucky.spzx.manager.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.codelucky.spzx.manager.service.ValidateCodeService;
import com.codelucky.spzx.model.vo.system.ValidateCodeVo;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.codelucky.spzx.service.config.ConstantConfig.BASE64_IMAGE;
import static com.codelucky.spzx.service.config.ConstantConfig.USER_LOGIN_VALIDATECODE;

@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {
    
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    
    @Override
    public ValidateCodeVo generateValidateCode() {
        // 生成验证码
        // 参数：宽，高，验证码位数，干扰线数量
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150,
                                                                      48,
                                                                      4,
                                                                      20);
        // 验证码内容
        String code = circleCaptcha.getCode();
        // 获取验证码的图案
        String imageBase64 = circleCaptcha.getImageBase64();
        // 生成存储Redis的Key
        String validateKey = UUID.randomUUID()
                                 .toString()
                                 .replaceAll("-",
                                             "");
        // 存储Redis
        redisTemplate.opsForValue()
                     .set(USER_LOGIN_VALIDATECODE + validateKey,
                          code,
                          5,
                          TimeUnit.MINUTES);
        // 封装返回值对象
        ValidateCodeVo validateCodeVo = new ValidateCodeVo();
        validateCodeVo.setCodeKey(validateKey);
        validateCodeVo.setCodeValue(BASE64_IMAGE + imageBase64);
        
        return validateCodeVo;
    }
}
