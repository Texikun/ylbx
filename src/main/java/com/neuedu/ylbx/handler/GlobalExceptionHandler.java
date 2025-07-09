package com.neuedu.ylbx.handler;

import com.neuedu.ylbx.utils.ResultVo;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: wudagai
 */
@ControllerAdvice //监听controller处理器方法是否发生异常
public class GlobalExceptionHandler {
    //定义异常处理器方法


    //SignatureException   token的非法操作
    //MalformedJwtException  token解析异常
    //ExpiredJwtException 过时

    @ExceptionHandler(SignatureException.class)
    @ResponseBody
    public ResultVo SignatureExceptionHandler(SignatureException e){
        return ResultVo.fail("token的非法操作,请自重","token_error" );
    }

    @ExceptionHandler(MalformedJwtException.class)
    @ResponseBody
    public ResultVo SignatureExceptionHandler(MalformedJwtException e){
        return ResultVo.fail("token解析异常","token_error");
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseBody
    public ResultVo SignatureExceptionHandler(ExpiredJwtException e){
        return ResultVo.fail("登录过时，请重新登录","token_error");
    }
    /**
     * jdk异常api提供的异常可以统一交个Exception处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVo ExceptionHandler(Exception e){
        if(e.getMessage().contains("token")){
            return ResultVo.fail(e.getMessage(),"token_error");
        }
        return ResultVo.fail(e.getMessage());
    }
}
