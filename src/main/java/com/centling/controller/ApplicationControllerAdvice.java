package com.centling.controller;
import com.alibaba.fastjson.JSONObject;
import com.centling.utils.Result;
import com.centling.utils.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    private Logger logger = LoggerFactory.getLogger(ApplicationControllerAdvice.class);


    @ExceptionHandler(BusinessException.class)
    public Result robotExceptionDeal(BusinessException re) {
        if(logger.isErrorEnabled()){
            logger.error("业务错误信息:{}",re);
        }
        Result response = new Result();
        response.setReturnCode(400);
        response.setReturnMsg(re.getErrorMsg());
        response.setReturnData(new HashMap<>());
        if(logger.isInfoEnabled()) {
            logger.info("请求返回结果为【{}】", JSONObject.toJSONString(response));
        }
        return  response;

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result argumentValidHandler(MethodArgumentNotValidException re) {
        if(logger.isErrorEnabled()){
            logger.error("业务错误信息:{}",re);
        }

        Result response = new Result();
        response.setReturnCode(403);

        BindingResult result = re.getBindingResult();
        List<String> errList ;

        if(result!=null){
            errList = result.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList());
            response.setReturnMsg(String.join("\n", errList));
        }else {
            response.setReturnMsg(new String("argument error"));
        }

        response.setReturnData(new HashMap<>());
        if(logger.isInfoEnabled()) {
            logger.info("请求返回结果为【{}】", JSONObject.toJSONString(response));
        }
        return  response;

    }

    @ExceptionHandler(RuntimeException.class)
    public Result runtimeExceptionDeal(RuntimeException re) {
        if(logger.isErrorEnabled()){
            logger.error("其他运行时错误信息:{}",re);
        }
        Result response = new Result();
        response.setReturnCode(400);
        response.setReturnMsg("系统维护中");
        response.setReturnData(new HashMap<>());
        if(logger.isInfoEnabled()) {
            logger.info("请求返回结果为【{}】", JSONObject.toJSONString(response));
        }
        return  response;
    }
}
