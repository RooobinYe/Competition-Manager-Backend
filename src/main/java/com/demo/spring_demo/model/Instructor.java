package com.demo.spring_demo.model;

public class Instructor {
    private Long id;
    private String name;
    private String workCode; // 工号
    private Long teamID;
    private Long academyID;
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkCode() {
        return workCode;
    }

    public void setWorkCode(String workCode) {
        this.workCode = workCode;
    }

    public Long getTeamID() {
        return teamID;
    }

    public void setTeamID(Long teamID) {
        this.teamID = teamID;
    }

    public Long getAcademyID() {
        return academyID;
    }

    public void setAcademyID(Long academyID) {
        this.academyID = academyID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
