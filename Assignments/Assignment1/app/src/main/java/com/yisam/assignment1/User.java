package com.yisam.assignment1;

public class User {
    private String name;
    private String studentID;

    public User (String name, String studentID) {
        this.name = name;
        this.studentID = studentID;
    }

    public String getName() {
        return this.name;
    }
    public String getStudentID() {
        return this.studentID;
    }
}
