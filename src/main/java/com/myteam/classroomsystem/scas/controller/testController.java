package com.myteam.classroomsystem.scas.controller;

import com.myteam.classroomsystem.scas.Entity.Application_form;
import com.myteam.classroomsystem.scas.Entity.Student;
import com.myteam.classroomsystem.scas.ReposityImpl.FormRepositoryImpl;
import com.myteam.classroomsystem.scas.mapper.StudentMapper;
import com.myteam.classroomsystem.scas.service.StudentService;
import com.myteam.classroomsystem.scas.utils.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Set;

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

    @GetMapping("/test2")
    public void test2() {

        redisTemplate.opsForValue().set("test", "1");
        redisTemplate.opsForValue().set("test", "12");
        redisTemplate.opsForValue().set("test2", "1");
        Object test = redisTemplate.opsForValue().get("test");
        Object test2 = redisTemplate.opsForValue().get("连");
        if (test2 == null) {
            System.out.println("不存在");
        }
        System.out.println(test);
    }

    @GetMapping("/test3")
    public void test3() {
        List<Application_form> forms = mongoTemplate.findAll(Application_form.class);
        System.out.println("forms size is ：" + forms.size());
        System.out.println(forms.get(0));
    }

    @GetMapping("/test4")
    public void test4() {
        List<Application_form> list = formRepository.studentFindFormBySidAndSname("king", "1123");
        System.out.println("list size is :" + list.size());
        System.out.println(list.get(0).toString());
    }

    @PostMapping("/test5")
    public void test4(Student student) {
        System.out.println("studentRegistered :" + student.toString());
        if (studentMapper.addStudent(student) > 0) {
            System.out.println("添加成功");
        }
    }

    @GetMapping("/test6")
    public void tet6() {
        Set sets = redisTemplate.keys("彭*");
        System.out.println(sets.toString());
    }


    public Student tet7() throws IOException {
        Auth auth = new Auth();
        Student student = auth.start("1740129222", "17440981109034");
        System.out.println(student.toString());
        return student;
    }

    @PostMapping("/test8")
    public void test8() {
        String teacherStrings = redisTemplate.opsForValue().get("teachers").toString();
        if (teacherStrings == null || teacherStrings.equals("")) {
            System.out.println("is null");
        }
        System.out.println("test8+++++++++++");
        System.out.println(teacherStrings);
    }
}
