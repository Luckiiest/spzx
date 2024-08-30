package com.codelucky.spzx.user.controller;

import com.codelucky.spzx.model.vo.common.Result;
import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import com.codelucky.spzx.user.service.SmsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/sms")
public class SmsController {
    // api/user/sms/sendCode{phone}

    @Resource
    private SmsService smsService;


    /**
     * 发送短信验证码
     *
     * @param phone
     * @return
     */
    @GetMapping("/sendCode/{phone}")
    public Result sendValidateCode(@PathVariable("phone") String phone) {
        smsService.sendValidateCode(phone);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
