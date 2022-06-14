package com.ray.collegecompanion;

public class Student {
    String name, dob, dept, gender;

    public Student() {
    }

    public Student(String name, String dob, String dept, String gender) {
        this.name = name;
        this.dob = dob;
        this.dept = dept;
        this.gender = gender;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
