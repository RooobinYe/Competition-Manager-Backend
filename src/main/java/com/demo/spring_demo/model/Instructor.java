package com.demo.spring_demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("instructor")
public class Instructor {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String workCode; // 工号
    private Long teamId;
    private Long academyId;
    private String phone;

    @TableField(exist = false)
    private Team team;
}
