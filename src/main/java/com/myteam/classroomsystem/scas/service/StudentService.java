package com.myteam.classroomsystem.scas.service;

import com.myteam.classroomsystem.scas.Entity.Application_form;
import com.myteam.classroomsystem.scas.Entity.Feedback;
import com.myteam.classroomsystem.scas.Entity.Repair;
import com.myteam.classroomsystem.scas.Entity.Student;
import com.myteam.classroomsystem.scas.utils.ResultVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StudentService {

    //    学生微信登录
    public ResultVO studentLogin(String name, String password) throws IOException;

    //学生注册
    public ResultVO addStudent(Student student);

    //学生反馈
    public ResultVO suggestion(Feedback feedback);

    //学生提交表单
    public ResultVO addForm(Application_form application_form);

    //个人信息
    public ResultVO studentInfo(String phone, String email, String sid);

    //最近申请
    public ResultVO applystatus(String sid);

    //更新个人信息
    public ResultVO updatePersonalInfo(String sid, String phone, String email);

    //据cid获取receiptform表的信息
    public ResultVO getReceiptFormInfo(String cid);

    //报修
    public ResultVO repair(Repair repair, MultipartFile[] image) throws IOException;

    //历史申请单
    public ResultVO historyForm(String sid);

    //获取历史回执单
    public ResultVO getReceiptformBySid(String sid);

    //联系管理员
    public ResultVO contactUs(String sid, String question);

    //历史帮助记录
    public ResultVO contactHistory(String sid);
}
