package com.demo.competition_manager_backend.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;

import lombok.Data;

@Data
@TableName("user")
public class User {
    @TableId(value = "user_code", type = IdType.INPUT)
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
