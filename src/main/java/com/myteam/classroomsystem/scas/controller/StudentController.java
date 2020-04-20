package com.myteam.classroomsystem.scas.controller;

import com.myteam.classroomsystem.scas.Entity.*;
import com.myteam.classroomsystem.scas.service.StudentService;
import com.myteam.classroomsystem.scas.service.TeacherService;
import com.myteam.classroomsystem.scas.utils.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @PostMapping("/suggestion")
    public ResultVO contactUs(Feedback feedback) {
        return studentService.suggestion(feedback);
    }


    /*  *
     * @Description: 索取所有的老师
     * @Author: king
     * @Date: 2020/4/12
     */
    @PostMapping("/getTeacher")
    public List<TeacherForSeacher> getTeacher() {
        return teacherService.findAllTeacherByDep();
    }

    /*  *
     * @Description:添加申请表单
     * @Author: king
     * @Date: 2020/4/14
     */
    @PostMapping("/apply")
    public ResultVO addForm(@RequestBody Application_form application_form) {
        return studentService.addForm(application_form);
    }

    /*  *
     * @Description: 更新信息
     * @Author: king
     * @Date: 2020/4/14
     */
    @PostMapping("/studentInfo")
    public ResultVO studentInfo(String phone, String email, String sid) {
        return studentService.studentInfo(phone, email, sid);
    }

    /*  *
     * @Description:最近申请的表单
     * @Author: king
     * @Date: 2020/4/16
     */
    @PostMapping("/applystatus")
    public ResultVO applystatus(String sid) {
        return studentService.applystatus(sid);
    }

    /*  *
     * @Description: 学生更新个人信息
     * @Author: king
     * @Date: 2020/4/17
     */
    @PostMapping("/updateInfo")
    public ResultVO updatePersonalInfo(String sid, String phone, String email) {
        return studentService.updatePersonalInfo(sid, phone, email);
    }

    /*  *
     * @Description:获取回执单
     * @Author: king
     * @Date: 2020/4/17
     */
    @PostMapping("/getReceiptformByCid")
    public ResultVO getReceiptFormByCid(String cid) {
        return studentService.getReceiptFormInfo(cid);
    }


    /*  *
     * @Description: 提交报修信息
     * @Author: king
     * @Date: 2020/4/17
     */
    @PostMapping("/repair")
    public ResultVO repair(String sid, String name, String phone, @RequestParam(required = false) String[] repairs,
                           String description, String classroom, MultipartFile[] images) throws IOException {

        if (repairs == null) {
            return ResultVO.getFailed("你还没有选择报修的物品");
        }
        int len = repairs.length;
        Repair repair = new Repair();
        repair.setSid(sid);
        repair.setName(name);
        repair.setPhone(phone);
        repair.setDescription(description);
        repair.setClassroom(classroom);
        String repairString = "";
        int tmp_len = 0;
        for (String rep : repairs) {
            repairString += rep;
            if (tmp_len != len - 1) {
                repairString += ",";
            }
            tmp_len++;
        }
        repair.setRepairs(repairString);
        return studentService.repair(repair, images);
    }


    /*  *
     * @Description: 历史申请记录
     * @Author: king
     * @Date: 2020/4/17
     */
    @PostMapping("/history")
    public ResultVO historyForm(String sid) {
        return studentService.historyForm(sid);
    }


    /*  *
     * @Description: 回执历史记录
     * @Author: king
     * @Date: 2020/4/18
     */
    @PostMapping("/getReceiptformBySid")
    public ResultVO getReceiptformBySid(String sid) {
        return studentService.getReceiptformBySid(sid);
    }

    /*  *
     * @Description:联系管理员
     * @Author: king
     * @Date: 2020/4/18
     */
    @PostMapping("/contactUs")
    public ResultVO contactUs(String sid, String question) {
        return studentService.contactUs(sid, question);
    }


    /*  *
     * @Description: 学生联系管理员的全部记录
     * @Author: king
     * @Date: 2020/4/18
     */
    @PostMapping("/contactHistory")
    public ResultVO contactHistory(String sid) {
        return studentService.contactHistory(sid);
    }
}
