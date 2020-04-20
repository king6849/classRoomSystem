package com.myteam.classroomsystem.scas.ServiceImpl;

import com.myteam.classroomsystem.scas.Bean.historyApplicationForm;
import com.myteam.classroomsystem.scas.Config.MvcConfig;
import com.myteam.classroomsystem.scas.Entity.*;
import com.myteam.classroomsystem.scas.Executor.AsyncService;
import com.myteam.classroomsystem.scas.ReposityImpl.FormRepositoryImpl;
import com.myteam.classroomsystem.scas.mapper.StudentMapper;
import com.myteam.classroomsystem.scas.service.StudentService;
import com.myteam.classroomsystem.scas.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    @Autowired
    private ConvertData convertData;

    private File imageSavePaht = new File(MvcConfig.repairImageSavePath);

    private SimpleDateFormat format = new SimpleDateFormat("MM/dd HH:mm");

    @Override
    @Transactional
    public ResultVO studentLogin(String name, String password) {

        Auth auth = new Auth();
        Student student = studentMapper.findStudentBySid(name);
        System.out.println(student);
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
    public ResultVO suggestion(Feedback feedback) {
        if (studentMapper.suggestion(feedback) > 0) {
            return ResultVO.getSuccess("已发送该反馈,等待管理员联系");
        }
        return ResultVO.getFailed("发生未知错误,反馈无法发送,请重试");
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

    /*  *
     * @Description: 获取最近的申请单
     * @Author: king
     * @Date: 2020/4/17
     */
    @Override
    public ResultVO applystatus(String sid) {

        //正在申请
        List<Application_form> recentFormBySid = formRepository.studentFindRecentFormBySid(sid);
        if (recentFormBySid.size() != 0) {
            Application_form2 result = convertData.conventToApplication_form2(recentFormBySid.get(0));
            System.out.println("mongo");
            return ResultVO.getSuccessWithData("获取成功", result);
        }
        //已申请完成的
        Application_form2 result = studentMapper.recentApplicationForm(sid);
        if (result == null) return ResultVO.getSuccess("没有申请记录哦");
        System.out.println("mysql");
        return ResultVO.getSuccessWithData("获取成功", result);
    }

    /*  *
     * @Description: 更新个人信息
     * @Author: king
     * @Date: 2020/4/17
     */
    @Override
    public ResultVO updatePersonalInfo(String sid, String phone, String email) {
        int status = studentMapper.updatePersonalInfo(email, phone, sid);
        if (status <= 0) {
            return ResultVO.getFailed("发生未知错误，请稍后再试");
        }
        return ResultVO.getSuccess("更新成功");
    }

    /*  *
     * @Description:查看回执单
     * @Author: king
     * @Date: 2020/4/17
     */
    @Override
    public ResultVO<Receiptform> getReceiptFormInfo(String cid) {
        Receiptform receiptform = studentMapper.getReceiptForm(cid);
        if (receiptform == null) {
            return ResultVO.getFailed("没有回执表记录");
        }
        return ResultVO.getSuccessWithData("获取回执表成功", receiptform);
    }

    /*  *
     * @Description: 报修
     * @Author: king
     * @Date: 2020/4/17
     */
    @Override
    public ResultVO repair(Repair repair, MultipartFile[] images) throws IOException {
        int len = images.length;
        System.out.println("images length" + len);

        String imgPath = "";
        int len_tmp = 0;
        if (len != 0) {
            for (MultipartFile img : images) {
                imgPath += img.getOriginalFilename();
                if (len_tmp != len - 1) {
                    imgPath += ",";
                }
                len_tmp += 1;
                img.transferTo(new File(imageSavePaht, img.getOriginalFilename()));
            }
        }
        repair.setImages(imgPath);
        System.out.println("repair " + repair.toString());
        return ResultVO.getSuccessWithData("已上传报修信息", repair);
    }

    /*  *
     * @Description: 历史申请
     * @Author: king
     * @Date: 2020/4/17
     */
    @Override
    public ResultVO historyForm(String sid) {
        List<historyApplicationForm> listForm = studentMapper.historyForm(sid);
        if (listForm == null) return ResultVO.getFailed("没有历史记录");
        return ResultVO.getSuccessWithData("已获取历史申请记录", listForm);
    }

    @Override
    public ResultVO getReceiptformBySid(String sid) {
        List<Receiptform> list = studentMapper.getReceiptformBySid(sid);
        if (list == null) {
            return ResultVO.getFailed("你没有回执历史记录");
        }
        return ResultVO.getSuccessWithData("已获取回执历史记录", list);
    }

    @Override
    public ResultVO contactUs(String sid, String question) {

        int status = studentMapper.contactUs(sid, question, new Date());
        if (status <= 0) {
            return ResultVO.getFailed("发生未知错误，请稍后重试");
        }
        return ResultVO.getSuccess("提交成功");
    }

    @Override
    public ResultVO contactHistory(String sid) {

        List<ContactUs> contactUses = studentMapper.contactHistory(sid);
        if (contactUses == null) {
            return ResultVO.getSuccess("没有历史记录");
        }
        return ResultVO.getSuccessWithData("取得联系管理员历史数据", contactUses);
    }
}
