package com.myteam.classroomsystem.scas.Entity;


public class Teacher {

    private long tid;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String department;
    private long role;


    public long getTid() {
        return tid;
    }

    public void setTid(long tid) {
        this.tid = tid;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    public long getRole() {
        return role;
    }

    public void setRole(long role) {
        this.role = role;
    }

}
