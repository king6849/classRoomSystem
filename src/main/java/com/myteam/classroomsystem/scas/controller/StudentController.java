package com.myteam.classroomsystem.scas.controller;

import com.myteam.classroomsystem.scas.Entity.Feedback;
import com.myteam.classroomsystem.scas.Entity.Student;
import com.myteam.classroomsystem.scas.Entity.TeacherForSeacher;
import com.myteam.classroomsystem.scas.service.StudentService;
import com.myteam.classroomsystem.scas.service.TeacherService;
import com.myteam.classroomsystem.scas.utils.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    //学生微信端登陆
    @PostMapping("/login")
    public ResultVO studentLogin(String sid, String password) throws IOException {
        if (sid == null || sid.trim().equals("") || password == null || password.trim().equals("")) {
            return ResultVO.getFailed("用户名密码不能为空");
        }
        return studentService.studentLogin(sid, password);
    }

    //学生注册
    @PostMapping("/addStudent")
    public ResultVO studentRegistered(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    //学生反馈信息
    @PostMapping("/contactUs")
    public ResultVO contactUs(@RequestBody Feedback feedback) {
        return studentService.contactUs(feedback);
    }

    @PostMapping("/contactStu")
    public ResultVO contactStu(@RequestBody Feedback feedback) {
        return studentService.contactStu(feedback);
    }

    @PostMapping("/finishFeedBack")
    public ResultVO finishFeedBack(@RequestBody Feedback feedback) {
        return studentService.finishFeedBack(feedback);
    }

    /*  *
     * @Description: 索取所有的老师
     * @Param: []
     * @return: com.myteam.classroomsystem.scas.utils.ResultVO
     * @Author: king
     * @Date: 2020/4/12
     */
    @PostMapping("/getTeacher")
    public List<TeacherForSeacher> getTeacher() {
        return teacherService.findAllTeacherByDep();
    }
}
