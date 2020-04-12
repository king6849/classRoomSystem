package com.myteam.classroomsystem.scas.Entity;

import lombok.Data;

@Data
public class Classroom {

    private long id;
    private String code;
    private long status;
    private long type;
    private long maxcapacity;

}
