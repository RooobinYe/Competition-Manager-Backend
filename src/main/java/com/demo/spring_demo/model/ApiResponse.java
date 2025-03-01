package com.demo.spring_demo.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

import java.util.Collections;

@JsonPropertyOrder({
        "success",
        "errCode",
        "errMsg",
        "data"
})
@Data
public class ApiResponse<T> {
    private boolean success;
    private int errCode;
    private String errMsg;
    private T data;

    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setErrCode(0);
        response.setErrMsg("");
        response.setData(data);
        return response;
    }

    public static <T> ApiResponse<T> error(int errCode, String errMsg) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(false);
        response.setErrCode(errCode);
        response.setErrMsg(errMsg);
        response.setData((T) Collections.emptyMap());
        return response;
    }
}
