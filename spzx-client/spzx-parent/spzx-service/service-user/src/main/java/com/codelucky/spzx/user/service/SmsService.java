package com.codelucky.spzx.user.service;

public interface SmsService {
    /**
     * 发送短信验证码
     *
     * @param phone
     */
    void sendValidateCode(String phone);
}
