package com.demo.competition_manager_backend.model.dto.captain;

import java.io.Serializable;

public class AddMemberDTO implements Serializable {
    private Integer id;
    private String name;
    private Integer studentId;
    private Integer teamId;
    private Integer academyId;
    private String phone;
    private Integer isCaptain;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getAcademyId() {
        return academyId;
    }

    public void setAcademyId(Integer academyId) {
        this.academyId = academyId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIsCaptain() {
        return isCaptain;
    }

    public void setIsCaptain(Integer isCaptain) {
        this.isCaptain = isCaptain;
    }
}
