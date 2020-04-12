package com.myteam.classroomsystem.scas.Entity;

import lombok.Data;

@Data
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
}
