package com.myteam.classroomsystem.scas.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Receiptform {

    private long id;
    private String name;
    @JsonFormat(timezone = "UTC", pattern = "yyyy-MM-dd HH:mm")
    private Date application_time;
    @JsonFormat(timezone = "UTC", pattern = "yyyy-MM-dd HH:mm")
    private Date end_time;
    private String classroom;
    private String note;
    private long cid;
    private int status;

}
