package com.teamapp.model;


public class TeamMember {
    private Long id;
    private String name;
    private String studentId;

    public TeamMember() {
    }


    public TeamMember(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
    }

    public TeamMember(Long id, String name, String studentId) {
        this.id = id;
        this.name = name;
        this.studentId = studentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
}
