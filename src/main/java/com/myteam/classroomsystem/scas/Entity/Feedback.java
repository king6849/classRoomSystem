package com.myteam.classroomsystem.scas.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Feedback {

    private long id;
    private String sid;
    private String name;
    private String phone;
    private String question;
    private String reply;
    @JsonFormat(timezone = "GMT-5",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applicationTime;
    @JsonFormat(timezone = "GMT-5",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishTime;
    private long status;


}
