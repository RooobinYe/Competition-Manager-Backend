package com.demo.spring_demo.model;

public class User {
    private String userCode;
    private String password;
    private String name;
    private Long academyId;
    /**
     * 超级管理员可以设置为null或者默认值
     */
    private Long comId;
    /**
     * 0- Captain
     * 1- Judge
     * 2- AcademyAdmin
     * 3- SuperAdmin
     */
    private Integer role;
}
