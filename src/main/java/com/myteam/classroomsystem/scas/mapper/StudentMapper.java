package com.myteam.classroomsystem.scas.mapper;

import com.myteam.classroomsystem.scas.Bean.historyApplicationForm;
import com.myteam.classroomsystem.scas.Entity.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Mapper
@Component
public interface StudentMapper {

    /*
     * addStudent
     * */
    @Insert("insert into student(sid, name,password, my_class,department) values(#{sid},#{name},#{password},#{myClass},#{department}) ")
    public int addStudent(Student student);

    /**
     * @Description:根据学号查找学生
     * @Param:
     * @return:
     * @Author: king
     * @Date: 2020/4/10
     */
    @Select("select sid,name,password,email,phone,my_class , department  from student where sid=#{sid}")
    public Student findStudentBySid(String sid);

    /**
     * @Description: 学生反馈
     * @Param:
     * @return:
     * @Author: king
     * @Date: 2020/4/10
     */
    @Insert("insert into feedback(name, phone, ui,function,convenient,note) VALUES(#{name},#{phone},#{ui},#{function},#{convenient} ,#{note} ) ")
    public int suggestion(Feedback feedback);


    /*  *
     * @Description: 个人信息
     * @Author: king
     * @Date: 2020/4/16
     */
    @Update("update student set phone=#{phoen} ,email=#{email} where sid=#{sid}")
    public int studentInfo(@Param("phone") String phone, @Param("email") String email, @Param("sid") String sid);


    /*  *
     * @Description: 最近申请单
     * @Author: king
     * @Date: 2020/4/16
     */
    @Select("select * from classroomapplicationform where sid=#{sid} order by application_time desc limit 1")
    public Application_form2 recentApplicationForm(String sid);

    /*  *
     * @Description: 根据学号更新学生邮箱和手机号
     * @Author: king
     * @Date: 2020/4/16
     */
    @Update("update student set email=#{email},phone=#{phone} where sid=#{sid} ")
    public int updatePersonalInfo(@Param("email") String email, @Param("phone") String phone, @Param("sid") String sid);


    /*  *
     * @Description: 据cid获取receiptform表的信息
     * @Author: king
     * @Date: 2020/4/17
     */
    @Select("select * from receiptform where cid=#{cid} ")
    public Receiptform getReceiptForm(String cid);

    /*  *
     * @Description: 报修
     * @Author: king
     * @Date: 2020/4/17
     */
    @Insert("insert into repare(sid,name,phone,repairs,description,images) values (#{sid},#{name}  ,#{phone},#{repairs}  ,#{description},#{images} )")
    public int repair(Repair repair);


    /*  *
     * @Description: 历史申请单
     * @Author: king
     * @Date: 2020/4/17
     */
    @Select("select id,class_room,organization,application_time,end_time,members,reason,counselor,department_teacher,multimedia,note,status from historyForm where sid=#{sid} ")
    public List<historyApplicationForm> historyForm(String sid);

    /*  *获取历史回执记录
     * @Description:
     * @Author: king
     * @Date: 2020/4/18
     */
    @Select("select r.id,r.name,r.application_time,r.end_time,r.classroom,r.note,r.cid,r.status from receiptform as r join classroomapplicationform as a on r.cid=a.id where a.sid=#{sid}")
    public List<Receiptform> getReceiptformBySid(String sid);


    /*  *
     * @Description: 查找学生的姓名
     * @Author: king
     * @Date: 2020/4/18
     */
    @Select("select name from student where sid=#{sid} ")
    public String findStudentNamBySid(String sid);

    /*  *
     * @Description: 联系管理员
     * @Author: king
     * @Date: 2020/4/18
     */
    @Insert("insert into contactus(sid,question,application_time) values(#{sid},#{question} ,#{application_time})")
    public int contactUs(@Param("sid") String sid, @Param("question") String question, @Param("application_time") Date application_time);


    @Select("select s.sid as sid, s.name as name ,question,application_time,reply,finish_time,status from contactus as c  join student as s on c.sid=s.sid where c.sid=#{sid} ")
    public List<ContactUs> contactHistory(String sid);

}
