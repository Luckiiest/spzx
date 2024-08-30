package com.codelucky.spzx.manager.service;

import com.codelucky.spzx.model.vo.system.ValidateCodeVo;

public interface ValidateCodeService {
    /**
     获取验证码
     @return ValidateCodeVo
     */
    ValidateCodeVo generateValidateCode();
}
