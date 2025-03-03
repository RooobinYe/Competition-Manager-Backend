package com.sast.approval.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("member")
public class Member {
    @TableId(type = IdType.AUTO)
    private Integer id; // 不是学号
    private String name;
    private String studentId; // 学号
    private Integer teamId;
    private Integer academyId;
    private String phone;
    private Integer isCaptain;
    private String email;
    
    @TableField(exist = false)
    private Team team;
}
