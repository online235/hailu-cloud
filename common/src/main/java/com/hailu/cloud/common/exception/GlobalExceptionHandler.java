package com.hailu.cloud.common.exception;

import com.hailu.cloud.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理
 * 所有异常均向上抛, 统一在controller层进行处理
 *
 * @author zhijie
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有未识别的异常
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResponse handleException(Throwable ex) {
        log.error(ex.getMessage(), ex);
        return ApiResponse.serverError();
    }

    /**
     * 处理404
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiResponse handleHttp404Exception(NoHandlerFoundException ex) {
        log.error(ex.getMessage(), ex);
        return ApiResponse.notFound();
    }

    /**
     * 处理Http method使用不正确的错误, 例如: 新增应该使用POST, 但实际使用了GET
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    public ApiResponse handleHttp405Exception(HttpRequestMethodNotSupportedException ex) {
        log.error(ex.getMessage(), ex);
        return ApiResponse.methodNotAllowed();
    }

    /**
     * 处理validation框架参数校验异常(bean验证)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);
        return ApiResponse.requestError(ex.getBindingResult());
    }

    /**
     * 处理validation框架参数校验异常(属性认证)
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse handleBindException(BindException ex) {
        log.error(ex.getMessage(), ex);
        return ApiResponse.requestError(ex.getBindingResult());
    }

    /**
     * 处理validation框架参数校验异常(方法验证)
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse handleConstraintViolationException(ConstraintViolationException ex) {
        log.error(ex.getMessage(), ex);
        return ApiResponse.requestError(ex.getConstraintViolations());
    }

    /**
     * 接口参数反序列化异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error(ex.getMessage(), ex);
        return ApiResponse.abnormalParameter(ex.getMessage());
    }

}
