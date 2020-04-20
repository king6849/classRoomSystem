package com.myteam.classroomsystem.scas.Bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

@Data
public class historyApplicationForm implements Serializable {

    private String id;
    private String class_room;
    private String organization;
    private int members;
    @JsonFormat(timezone = "GMT-5", pattern = "MM/dd HH:mm")
    @Field("application_time")
    private Date application_time;
    @JsonFormat(timezone = "GMT-5", pattern = "MM/dd HH:mm")
    @Field("end_time")
    private Date end_time;
    private String reason;

    private String counselor;

    private String department_teacher;

    private String note;
    //是否需要多媒体 默认为1，需要 ，0是不需要
    private int multimedia;
    //status 申请单的审核状态，
    // -1 表示申请驳回
    // 0表示等待辅导员或者指导老师批准，
    // 1表示等待系部老师批准，
    // 2表示等待审批员批准
    // 3表示该申请单已完成批准，学生可以看到申请单的申请结果
    private int status;

}
