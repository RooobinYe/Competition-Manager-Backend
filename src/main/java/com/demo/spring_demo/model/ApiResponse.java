package com.demo.spring_demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.LocalDateTime;

@JsonPropertyOrder({
        "success",
        "errCode",
        "errMsg",
        "data"
})
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
        response.setData(null);
        return response;
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}