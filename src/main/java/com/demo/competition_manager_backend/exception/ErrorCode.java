package com.demo.competition_manager_backend.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    // Team related errors (1000-1999)
    TEAM_NOT_FOUND(1001, HttpStatus.NOT_FOUND, "Team not found"),
    TEAM_CREATE_FAILED(1002, HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create team"),
    TEAM_UPDATE_FAILED(1003, HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update team"),
    
    // Member related errors (2000-2999)
    MEMBER_ALREADY_EXISTS(2001, HttpStatus.CONFLICT, "Member already exists in team"),
    MEMBER_NOT_FOUND(2002, HttpStatus.NOT_FOUND, "Member not found"),
    MEMBER_UPDATE_FAILED(2003, HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update member"),
    MEMBER_CREATE_FAILED(2004, HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create member"),

    // Instructor related errors (3000-3999)
    INSTRUCTOR_UPDATE_FAILED(3001, HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update instructor");

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(int code, HttpStatus httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
} 