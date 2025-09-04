package com.tliasservice.exception;

import com.tliasservice.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    //处理业务异常
    @ExceptionHandler(BusinessException.class)
    public Result handleBusinessException(BusinessException e){
        e.printStackTrace();
        return Result.error(e.getMessage());
    }

    //处理其他异常
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        e.printStackTrace();
        return Result.error("对不起，操作失败，请联系管理员");
    }
}
