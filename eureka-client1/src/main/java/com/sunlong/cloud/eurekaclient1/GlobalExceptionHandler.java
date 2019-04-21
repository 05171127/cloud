package com.sunlong.cloud.eurekaclient1;

import com.sulong.cloud.common.model.GeneralResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * @author : shipp
 * @description :
 * @data : 2018/12/27 16:05
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public GeneralResponse methodArgumentNotValidHandler(HttpServletRequest request, Exception exception) {
        GeneralResponse res = new GeneralResponse();
        res.setCode((byte)0);
        res.setMsg("请求失败");

        return res;
    }
}
