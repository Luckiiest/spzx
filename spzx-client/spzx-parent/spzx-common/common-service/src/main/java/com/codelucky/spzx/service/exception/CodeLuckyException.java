package com.codelucky.spzx.service.exception;


import com.codelucky.spzx.model.vo.common.ResultCodeEnum;
import lombok.Data;

@Data
public class CodeLuckyException extends RuntimeException {

    private Integer code;
    private String message;
    private ResultCodeEnum resultCodeEnum;

    public CodeLuckyException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }
}
