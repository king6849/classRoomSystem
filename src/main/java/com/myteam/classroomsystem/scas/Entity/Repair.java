package com.myteam.classroomsystem.scas.Entity;


import lombok.Data;

@Data
public class Repair {

    private long id;
    private String sid;
    private String name;
    private String phone;
    private String repairs;
    private String description;
    private String images;
    private String classroom;

}
