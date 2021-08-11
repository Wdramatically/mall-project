package com.imooc.mall.exception;


import com.imooc.mall.common.ApiRestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @ControllerAdvice 用于拦截
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception e){
        logger.error("Default Exception:", e);
        return ApiRestResponse.error(ImoocMallExceptionEnum.SYSTEM_ERROR);
    }

    @ExceptionHandler(ImoocMallException.class)
    @ResponseBody
    public Object handleImoocMallException(ImoocMallException exception){
        logger.error("imoocMallException", exception);
        return ApiRestResponse.error(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ApiRestResponse handleArgumentNotValidException(MethodArgumentNotValidException e){
        logger.error("MethodArgumentNotValidException:", e);
        return handleBindingResult(e.getBindingResult());
    }

    /**
     * 处理绑定异常
     * @param bindingResult
     * @return
     */
    private ApiRestResponse handleBindingResult(BindingResult bindingResult) {
        List<String> list = new ArrayList<>();
        if(bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError error : allErrors){
                list.add(error.getDefaultMessage());
            }
        }
        if (list.size() == 0){
            throw new ImoocMallException(ImoocMallExceptionEnum.REQUEST_PARAM_ERROR);
        }
        return ApiRestResponse.error(ImoocMallExceptionEnum.REQUEST_PARAM_ERROR.getCode(), list.toString());
    }
}
