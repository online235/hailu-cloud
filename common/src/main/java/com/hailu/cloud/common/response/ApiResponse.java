package com.hailu.cloud.common.response;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author zhijie
 */
@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {

    private int code;

    private String message;

    private Object data;

    public ApiResponse(ApiResponseEnum responseEnum, Object data) {
        this.code = responseEnum.getResponseCode();
        this.data = data;
    }

    public ApiResponse(ApiResponseEnum responseEnum) {
        this(responseEnum, null);
    }

    public static ApiResponse result(Object data) {
        return new ApiResponse(ApiResponseEnum.SUCCESS, data);
    }

    public static ApiResponse result() {
        return new ApiResponse(ApiResponseEnum.SUCCESS);
    }

    public static ApiResponse methodNotAllowed() {
        return new ApiResponse(ApiResponseEnum.METHOD_NOT_ALLOWED);
    }

    public static ApiResponse notFound() {
        return new ApiResponse(ApiResponseEnum.NOT_FOUND);
    }

    public static ApiResponse serverError() {
        return new ApiResponse(ApiResponseEnum.SERVER_ERROR);
    }

    public static ApiResponse unAuthorized() {
        return new ApiResponse(ApiResponseEnum.UN_AUTHORIZED);
    }

    public static ApiResponse requestTimeout() {
        return new ApiResponse(ApiResponseEnum.REQUEST_TIMEOUT);
    }

    public static ApiResponse abnormalParameter(String message) {
        ApiResponse response = new ApiResponse(ApiResponseEnum.ABNORMAL_PARAMETER);
        response.setMessage(message);
        return response;
    }

    public static ApiResponse requestError(BindingResult bindingResult) {
        Map<String, String> errorMap = new HashMap<>(bindingResult.getErrorCount());
        bindingResult.getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        ApiResponse response = new ApiResponse(ApiResponseEnum.REQUEST_ERROR);
        response.setMessage(JSON.toJSONString(errorMap));
        return response;
    }

    public static ApiResponse requestError(Set<? extends ConstraintViolation> constraintViolations) {
        StringBuilder builder = new StringBuilder();
        for (ConstraintViolation violation : constraintViolations) {
            builder.append(violation.getMessage()).append(",").append(violation.getInvalidValue()).append(";");
        }
        ApiResponse response = new ApiResponse(ApiResponseEnum.REQUEST_ERROR);
        response.setMessage(builder.toString());
        return response;
    }

}
