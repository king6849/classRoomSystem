package com.myteam.classroomsystem.scas.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private int members;
    @JsonFormat(timezone = "UTC", pattern = "yyyy-MM-dd HH:mm")
    @Field("application_time")
    private Date applicationtime;
    @JsonFormat(timezone = "UTC", pattern = "yyyy-MM-dd HH:mm")
    @Field("end_time")
    private Date endtime;
    private String reason;
    //辅导员工号
    @Field("counselor_tid")
    private Integer counselortid;
    private String counselor;
    //辅导员审批时间
    @JsonFormat(timezone = "UTC", pattern = "yyyy-MM-dd HH:mm")
    @Field("counselor_deal_time")
    private Date counselordealtime;
    //老师工号
    @Field("department_teacher_tid")
    private Integer departmentteachertid;
    @Field("department_teacher")
    private String departmentteacher;
    //系部老师审批时间
    @JsonFormat(timezone = "UTC", pattern = "yyyy-MM-dd HH:mm")
    @Field("department_teacher_deal_time")
    private Date departmentteacherdealtime;
    //审批员审批时间
    @JsonFormat(timezone = "UTC", pattern = "yyyy-MM-dd HH:mm")
    @Field("approver_time")
    private Date approvertime;
    private String note;
    //是否需要多媒体 默认为1，需要 ，0是不需要
    private int multimedia;
    private int classsize;
    //status 申请单的审核状态，
    // -1 表示申请驳回
    // 0表示等待辅导员或者指导老师批准，
    // 1表示等待系部老师批准，
    // 2表示等待审批员批准
    // 3表示该申请单已完成批准，学生可以看到申请单的申请结果
    private int status;

}
