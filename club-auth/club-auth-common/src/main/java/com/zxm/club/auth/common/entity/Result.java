package com.zxm.club.auth.common.entity;

import com.zxm.club.auth.common.enums.ResultCodeEnums;
import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;
    private boolean success;

    public static Result ok() {
        Result<Object> result = new Result<>();
        result.setCode(ResultCodeEnums.SUCCESS.code);
        result.setMessage(ResultCodeEnums.SUCCESS.message);
        result.setSuccess(true);
        return result;
    }

    public static <T> Result ok(T object) {
        Result<Object> result = new Result<>();
        result.setCode(ResultCodeEnums.SUCCESS.code);
        result.setMessage(ResultCodeEnums.SUCCESS.message);
        result.setData(object);
        result.setSuccess(true);
        return result;
    }

    public static Result fail() {
        Result<Object> result = new Result<>();
        result.setCode(ResultCodeEnums.FAIL.code);
        result.setMessage(ResultCodeEnums.FAIL.message);
        result.setSuccess(false);
        return result;
    }

    public static <T> Result fail(T o) {
        Result<Object> result = new Result<>();
        result.setCode(ResultCodeEnums.FAIL.code);
        result.setMessage(ResultCodeEnums.FAIL.message);
        result.setData(o);
        result.setSuccess(false);
        return result;
    }
}
