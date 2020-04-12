package com.myteam.classroomsystem.scas.Entity;

import lombok.Data;

@Data
public class Teacher {
    private String tid;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String department;
    private long role;
}
