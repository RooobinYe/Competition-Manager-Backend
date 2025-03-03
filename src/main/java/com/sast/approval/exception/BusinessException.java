package com.sast.approval.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = errorCode.getHttpStatus();
    }

    public BusinessException(ErrorCode errorCode) {
        this(errorCode, errorCode.getMessage());
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public static BusinessException teamNotFound(Integer teamId) {
        return new BusinessException(ErrorCode.TEAM_NOT_FOUND, "Team not found with id: " + teamId);
    }

    public static BusinessException memberAlreadyExists(Integer teamId, Integer memberId) {
        return new BusinessException(ErrorCode.MEMBER_ALREADY_EXISTS, 
            "Member " + memberId + " already exists in team " + teamId);
    }
} 