package com.myteam.classroomsystem.scas.Entity;

import lombok.Data;

@Data
public class Receiptform {

    private long id;
    private String name;
    private java.sql.Timestamp applicationTime;
    private java.sql.Timestamp endtime;
    private String classroom;
    private String note;
    private long cid;

}
