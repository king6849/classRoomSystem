package com.myteam.classroomsystem.scas.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ContactUs {

    private long id;
    private String sid;
    private String name;
    private String question;
    @JsonFormat(pattern = "MM/dd HH:mm" ,timezone = "UTC")
    private Date application_time;
    private String reply;
    @JsonFormat(pattern = "MM/dd HH:mm",timezone = "UTC")
    private Date finish_time;
    private int status;

}
