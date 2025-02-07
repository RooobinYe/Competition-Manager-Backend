package com.demo.spring_demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
@TableName("team")
public class Team implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer comId;
    private Integer captainId;
    private String captainName;
    private Integer status;
    
    @TableField(exist = false)
    private List<Integer> memberIds;
    @TableField(exist = false)
    private List<Integer> instructorIds;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    
    @TableField(exist = false)
    private List<Member> members;
    private String memberNames;
    private String instructorNames;
}
