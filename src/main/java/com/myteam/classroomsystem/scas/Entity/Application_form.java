package com.myteam.classroomsystem.scas.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

@Data
@Document
public class Application_form implements Serializable {
    @Id
    private String id;
    private String sid;
    private String name;
    private String phone;
    private String organization;
    private int member;
    @Field("application_time")
    private Date applicationTime;
    @Field("end_time")
    private Date endTime;
    private String reason;
    //辅导员工号
    @Field("counselor_tid")
    private Integer counselorTid;
    private String counselor;
    //老师工号
    @Field("department_teacher_tid")
    private Integer departmentTeacherTid;
    @Field("department_teacher")
    private String departmentTeacher;
    private String note;
    // 0,代表不需要多媒体
    // 1,代表需要多媒体 默认
    private int multimedia;
    //status 申请单的审核状态，
    // -1 表示申请驳回
    // 0表示等待辅导员或者指导老师批准，
    // 1表示等待系部老师批准，
    // 2表示等待审批员批准
    // 3表示该申请单已完成批准，学生可以看到申请单的申请结果
    private int status;
    //审批人员给学生安排的教室
    private String _class;
    //驳回原因
    private String rejection;

}
