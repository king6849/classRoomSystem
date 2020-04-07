package com.myteam.classroomsystem.scas.controller;

import com.myteam.classroomsystem.scas.service.StudentService;
import com.myteam.classroomsystem.scas.utils.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //学生微信端登陆
    @PostMapping("/login")
    public ResultVO studentLogin(String code) {
        System.out.println("code :" + code);
        return studentService.studentLogin(code);
    }

    @PostMapping("/addStudent")
    public ResultVO studentRegistered(@RequestBody HashMap<String, Object> param) {
        System.out.println("studentRegistered :" + param.toString());
        return studentService.addStudent(param);
    }

}
