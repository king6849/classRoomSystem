package com.myteam.classroomsystem.scas.controller;

import com.myteam.classroomsystem.scas.Entity.Teacher;
import com.myteam.classroomsystem.scas.Entity.names;
import com.myteam.classroomsystem.scas.ReposityImpl.FormRepositoryImpl;
import com.myteam.classroomsystem.scas.mapper.StudentMapper;
import com.myteam.classroomsystem.scas.mapper.TestData;
import com.myteam.classroomsystem.scas.service.StudentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@RestController
public class testController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentMapper studentMapper;

    @Resource
    private MongoTemplate mongoTemplate;
    @Autowired
    private FormRepositoryImpl formRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/hello")
    public String test() {
        System.out.println("hello");
        return "hello world";
    }

    @Autowired
    private FormRepositoryImpl repository;

    @PostMapping("/test2")
    public String test2(String sid) {
        repository.studentFindRecentFormBySid(sid);

        return "hello world";
    }


    @Autowired
    TestData testData;

    public static String getRandomNickname(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        return val;
    }

    @GetMapping("/test9")
    public void test9() {
        List<names> names = testData.names();
        for (names names1 : names) {
            Teacher teacher = new Teacher();
            teacher.setTid(String.valueOf(names1.getId()));
            teacher.setName(names1.getFull_name());
            String encodePassword = encoder.encode(String.valueOf(names1.getId()));
            teacher.setPassword(encodePassword);
            teacher.setEmail(String.valueOf(names1.getId()) + "@scse.com.cn");
            int role = (int) Math.floor(Math.random() * Math.floor(3));
            teacher.setRole(role);
            String department = "";
            if (role == 0) {
                department = "软件系";
            } else if (role == 1) {
                department = "计算机系";
            } else {
                department = "电子系";
            }
            teacher.setDepartment(department);
            teacher.setPhone(getRandomNickname(11));
            System.out.println(teacher);
            testData.insertTeacher(teacher);
        }

    }

    @Test
    public void test4() {
        System.out.println((int) Math.floor(Math.random() * Math.floor(3)));
    }
}
