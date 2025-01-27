package com.demo.spring_demo.model.dto.captain;

import java.io.Serializable;

public class UpdateTeamDTO implements Serializable {
    private Integer id;
    private Integer comId;
    private String name;
    private Integer captainId;
    private String captainName;
    private Integer status;
    private String memberNames;
    private String instructorNames;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCaptainId() {
        return captainId;
    }

    public void setCaptainId(Integer captainId) {
        this.captainId = captainId;
    }

    public String getCaptainName() {
        return captainName;
    }

    public void setCaptainName(String captainName) {
        this.captainName = captainName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMemberNames() {
        return memberNames;
    }

    public void setMemberNames(String memberNames) {
        this.memberNames = memberNames;
    }

    public String getInstructorNames() {
        return instructorNames;
    }

    public void setInstructorNames(String instructorNames) {
        this.instructorNames = instructorNames;
    }
}
