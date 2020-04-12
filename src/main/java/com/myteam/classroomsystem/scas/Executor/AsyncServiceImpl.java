package com.myteam.classroomsystem.scas.Executor;

import com.myteam.classroomsystem.scas.Entity.Student;
import com.myteam.classroomsystem.scas.mapper.StudentMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AsyncServiceImpl implements AsyncService {
    private Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

    @Test
    public void test1() {
        logger.info("king-->", "kingdasljgkldashglkadsj");
        logger.info(String.format("%s %s %s", "username", "feature", "1111"));
    }

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private StudentMapper studentMapper;

    @Async("serviceExecutor")
    @Override
    public void saveStudentLoginInfo(Student student) {
        //密码加密
        String encodePassword = encoder.encode(student.getPassword());
        student.setPassword(encodePassword);
        if (studentMapper.addStudent(student) > 0) {
            logger.info("新添加学生成功");
        }
    }
}
