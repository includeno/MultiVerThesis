package com.example.utils;

import com.example.enums.CodeInterface;
import com.example.enums.HTTPCode;
import lombok.Data;

import java.io.Serializable;

@Data
public class JsonResult<T> implements Serializable {
    String message;
    Integer code;
    T data;

    public JsonResult() {

    }

    public JsonResult(Integer code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static <T> JsonResult<T> ok() {
        return new JsonResult<>(HTTPCode.OK.getCode(), null, "ok");
    }

    public static <T> JsonResult<T> ok(T data) {
        return new JsonResult<T>(HTTPCode.OK.getCode(), data, "ok");
    }

    public static <T> JsonResult<T> build(CodeInterface<Integer, String> entity, T data) {
        JsonResult<T> jsonResult = new JsonResult<T>();
        jsonResult.setCode(entity.getCode());
        jsonResult.setMessage(entity.getMessage());
        jsonResult.setData(data);
        return jsonResult;
    }

    public static <T> JsonResult<T> build(Integer code, String message, T data) {
        JsonResult<T> jsonResult = new JsonResult<T>();
        jsonResult.setCode(code);
        jsonResult.setMessage(message);
        jsonResult.setData(data);
        return jsonResult;
    }

    public static <T> JsonResult<T> success(String message, T data) {
        JsonResult<T> jsonResult = new JsonResult<T>();
        jsonResult.setCode(HTTPCode.OK.getCode());
        jsonResult.setMessage(message);
        jsonResult.setData(data);
        return jsonResult;
    }

    public static <T> JsonResult<T> error(String message, T data) {
        JsonResult<T> jsonResult = new JsonResult<T>();
        jsonResult.setCode(HTTPCode.ERROR.getCode());
        jsonResult.setMessage(message);
        jsonResult.setData(data);
        return jsonResult;
    }

    public static <T> JsonResult<T> error(String message) {
        JsonResult<T> jsonResult = new JsonResult<T>();
        jsonResult.setCode(HTTPCode.ERROR.getCode());
        jsonResult.setMessage(message);
        jsonResult.setData(null);
        return jsonResult;
    }

    public static <T> JsonResult<T> error(CodeInterface<Integer, String> entity) {
        JsonResult<T> jsonResult = new JsonResult<T>();
        jsonResult.setCode(entity.getCode());
        jsonResult.setMessage(entity.getMessage());
        jsonResult.setData(null);
        return jsonResult;
    }
}
