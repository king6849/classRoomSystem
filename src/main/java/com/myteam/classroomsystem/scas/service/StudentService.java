package com.myteam.classroomsystem.scas.service;

import com.myteam.classroomsystem.scas.Entity.Application_form;
import com.myteam.classroomsystem.scas.Entity.Feedback;
import com.myteam.classroomsystem.scas.Entity.Student;
import com.myteam.classroomsystem.scas.utils.ResultVO;

import java.io.IOException;

public interface StudentService {

    //    学生微信登录
    public ResultVO studentLogin(String name, String password) throws IOException;

    //学生注册
    public ResultVO addStudent(Student student);

    //学生反馈
    public ResultVO contactUs(Feedback feedback);

    //管理员回答反馈
    public ResultVO contactStu(Feedback feedback);

    //完成反馈
    public ResultVO finishFeedBack(Feedback feedback);

    //学生提交表单
    public ResultVO addForm(Application_form application_form);

    //个人信息
    public ResultVO studentInfo(String phone,String email,String sid);
}
