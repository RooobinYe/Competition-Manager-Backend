package com.demo.spring_demo.model.dto.captain;

import java.io.Serializable;

public class GetMemberDTO implements Serializable {
    private String studentId;
    private String name;
    private String academy;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }
} 