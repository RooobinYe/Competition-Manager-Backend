package com.demo.spring_demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class Team implements Serializable {
    private Long id;
    private Long comId;
    private String name;

    private Long captainId;
    private String captainName;
    // 审核通过标识
    private Integer status;

    /**
     * 队员ID列表，包括队长
     * 可以考虑使用typeHandler
     * 可以参考：https://mybatis.org/mybatis-3/configuration.html#typeHandlers
     * https://juejin.cn/post/7196490153135751224
     */
    private List<Long> memberIds;
    private List<Long> instructorIds;

    private LocalDateTime createTime;
}
