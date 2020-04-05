package com.myteam.classroomsystem.scas.Entity;

public class Student {

    private long sid;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String myClass;
    private String department;


    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
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


    public String getMyClass() {
        return myClass;
    }

    public void setMyClass(String myClass) {
        this.myClass = myClass;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}
