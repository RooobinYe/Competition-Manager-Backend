package com.demo.spring_demo.model;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user")
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

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAcademyId() {
        return academyId;
    }

    public void setAcademyId(Long academyId) {
        this.academyId = academyId;
    }

    public Long getComId() {
        return comId;
    }

    public void setComId(Long comId) {
        this.comId = comId;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
