package com.codelucky.spzx.service.handler;

import com.codelucky.spzx.model.vo.common.Result;
import com.codelucky.spzx.service.exception.CodeLuckyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = CodeLuckyException.class)
    @ResponseBody
    public Result error(CodeLuckyException e) {
        e.printStackTrace();
        return Result.build(null,
                            e.getCode(),
                            e.getMessage());
    }
    
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.build(null,
                            201,
                            e.getMessage());
    }
}
