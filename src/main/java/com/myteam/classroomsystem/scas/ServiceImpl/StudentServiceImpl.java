package com.myteam.classroomsystem.scas.ServiceImpl;

import com.myteam.classroomsystem.scas.Entity.Application_form;
import com.myteam.classroomsystem.scas.Entity.Feedback;
import com.myteam.classroomsystem.scas.Entity.Student;
import com.myteam.classroomsystem.scas.Executor.AsyncService;
import com.myteam.classroomsystem.scas.ReposityImpl.FormRepositoryImpl;
import com.myteam.classroomsystem.scas.mapper.StudentMapper;
import com.myteam.classroomsystem.scas.service.StudentService;
import com.myteam.classroomsystem.scas.utils.Auth;
import com.myteam.classroomsystem.scas.utils.RestTemplateConfig;
import com.myteam.classroomsystem.scas.utils.ResultVO;
import com.myteam.classroomsystem.scas.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private Token mytoken;

    @Resource
    private RestTemplateConfig restTemplateConfig;

    @Resource
    private MongoTemplate mongoTemplate;

    @Autowired
    private FormRepositoryImpl formRepository;

    @Resource
    private StudentMapper studentMapper;

    @Autowired
    private AsyncService asyncService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    @Transactional
    public ResultVO studentLogin(String name, String password) {
        Auth auth = new Auth();
        Student student = studentMapper.findStudentBySid(name);
        //第一次登录
        if (student == null) {
            try {
                student = auth.start(name, password);
                student.setPassword(password);
            } catch (IOException e) {
                return ResultVO.getFailed("服务超时，请稍后再试");
            }
            //密码，账号不对的情况
            if (!auth.isStatusCode()) {
                return ResultVO.getFailed(auth.getStatus());
            }
            //保存学生登录信息
            asyncService.saveStudentLoginInfo(student);
            return ResultVO.getSuccessWithData("登录成功", student);
        }
        //第二次登录
        else if (student != null) {
            boolean pwd = encoder.matches(password, student.getPassword());
            if (pwd) {
                return ResultVO.getSuccessWithData("登录成功", student);
            } else {
                return ResultVO.getFailed("密码错误");
            }
        }
        return null;
    }


    /*
     * 学生注册
     * */
    @Override
    public ResultVO addStudent(Student student) {
        Student student1 = studentMapper.findStudentBySid(student.getSid());
        if (student != null) {
            return ResultVO.getFailed("该账号已被注册了");
        }
        if (studentMapper.addStudent(student) > 0) {
            return ResultVO.getSuccess("注册成功");
        }
        return ResultVO.getFailed("注册失败");
    }

    //学生反馈
    @Override
    @Transactional
    public ResultVO contactUs(Feedback feedback) {
        if (studentMapper.contactUs(feedback) > 0) {
            return ResultVO.getSuccess("已发送该反馈,等待管理员联系");
        }
        return ResultVO.getFailed("发生未知错误,反馈无法发送,请重试");
    }

    //管理员回答反馈
    @Override
    @Transactional
    public ResultVO contactStu(Feedback feedback) {
        if (studentMapper.contactStu(feedback.getId(), feedback.getReply()) > 0) {
            return ResultVO.getSuccess("已发反馈的回答,等待申请人联系");
        }
        return ResultVO.getFailed("发生未知错误,回答无法发送,请重试");
    }

    /**
     * @Description:
     * @Param:
     * @return:
     * @Author: king
     * @Date: 2020/4/11
     */
    @Override
    @Transactional
    public ResultVO finishFeedBack(Feedback feedback) {
        if (studentMapper.finishFeedBack(feedback.getId(), feedback.getFinishTime()) > 0) {
            return ResultVO.getSuccess("已完成反馈状态更新:此条反馈信息已完成");
        }
        return ResultVO.getFailed("发生未知错误,无法发送反馈信息状态,请重试");
    }

    /*  *
     * @Description: 提交表单
     * @Param: [application_form]
     * @return: com.myteam.classroomsystem.scas.utils.ResultVO
     * @Author: king
     * @Date: 2020/4/14
     */
    @Override
    public ResultVO addForm(Application_form application_form) {
        Application_form status = mongoTemplate.save(application_form, "application_form");
        if (status.getId() == null) {
            return ResultVO.getFailed("提交失败");
        }
        System.out.println("status" + status);
        return ResultVO.getSuccess("提交成功");
    }

    /*  *
     * @Description: 更新信息
     * @Param: [phone, email, sid]
     * @return: com.myteam.classroomsystem.scas.utils.ResultVO
     * @Author: king
     * @Date: 2020/4/14
     */
    @Override
    public ResultVO studentInfo(String phone, String email, String sid) {
        int status = studentMapper.studentInfo(phone, email, sid);
        if (status == 0) {
            return ResultVO.getFailed("发生未知错误，重稍后重试");
        }
        return ResultVO.getSuccess("更新信息成功");
    }
}
