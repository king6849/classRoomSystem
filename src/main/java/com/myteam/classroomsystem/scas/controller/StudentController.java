package com.myteam.classroomsystem.scas.controller;

import com.myteam.classroomsystem.scas.service.StudentService;
import com.myteam.classroomsystem.scas.utils.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //学生微信端登陆
    @PostMapping("/login")
    public ResultVO studentLogin(String code) {

        return studentService.studentLogin(code);
    }

}
