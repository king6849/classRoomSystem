package com.myteam.classroomsystem.scas.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document
public class Application_form2 implements Serializable {
    @Id
    private String id;
    private String sid;
    private String name;
    private String phone;
    private String organization;
    private int member;
    @JsonFormat(timezone = "UTC", pattern = "yyyy-MM-dd HH:mm")
    private Date application_time;
    @JsonFormat(timezone = "UTC", pattern = "yyyy-MM-dd HH:mm")
    private Date end_time;
    private String reason;
    //辅导员工号
    private Integer counselor_id;
    private String counselor;
    //辅导员审批时间
    @JsonFormat(timezone = "UTC", pattern = "yyyy-MM-dd HH:mm")
    private Date counselor_deal_time;
    //老师工号
    private Integer department_teacher_id;
    private String department_teacher;
    //系部老师审批时间
    @JsonFormat(timezone = "UTC", pattern = "yyyy-MM-dd HH:mm")
    private Date department_teacher_deal_time;
    //审批员审批时间
    @JsonFormat(timezone = "UTC", pattern = "yyyy-MM-dd HH:mm")
    private Date approver_time;
    private String note;
    //是否需要多媒体 默认为1，需要 ，0是不需要
    private int multimedia;
    private int class_size;
    //status 申请单的审核状态，
    // -1 表示申请驳回
    // 0表示等待辅导员或者指导老师批准，
    // 1表示等待系部老师批准，
    // 2表示等待审批员批准
    // 3表示该申请单已完成批准，学生可以看到申请单的申请结果
    private int status;



}
