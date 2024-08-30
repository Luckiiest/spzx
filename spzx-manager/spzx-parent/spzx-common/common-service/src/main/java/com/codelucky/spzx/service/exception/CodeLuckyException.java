package com.codelucky.spzx.service.exception;

import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import lombok.Data;

@Data
public class CodeLuckyException extends RuntimeException {
    private Integer code;          // 错误状态码
    private String message;        // 错误消息
    
    private ResultCodeEnum resultCodeEnum;     // 封装错误状态码和错误消息
    
    public CodeLuckyException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }
    
    public CodeLuckyException(Integer code,
                              String message) {
        this.code = code;
        this.message = message;
    }
}
