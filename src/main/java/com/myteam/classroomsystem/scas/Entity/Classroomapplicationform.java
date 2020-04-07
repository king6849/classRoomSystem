package com.myteam.classroomsystem.scas.Entity;


public class Classroomapplicationform {

    private long id;
    private String name;
    private String phone;
    private String organization;
    private java.sql.Timestamp applicationTime;
    private java.sql.Timestamp endtime;
    private String reason;
    private String counselor;
    private String departmentTeacher;
    private String note;
    private int status;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }


    public java.sql.Timestamp getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(java.sql.Timestamp applicationTime) {
        this.applicationTime = applicationTime;
    }


    public java.sql.Timestamp getEndtime() {
        return endtime;
    }

    public void setEndtime(java.sql.Timestamp endtime) {
        this.endtime = endtime;
    }


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    public String getCounselor() {
        return counselor;
    }

    public void setCounselor(String counselor) {
        this.counselor = counselor;
    }


    public String getDepartmentTeacher() {
        return departmentTeacher;
    }

    public void setDepartmentTeacher(String departmentTeacher) {
        this.departmentTeacher = departmentTeacher;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
