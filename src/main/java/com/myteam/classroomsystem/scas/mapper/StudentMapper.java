package com.myteam.classroomsystem.scas.mapper;

import com.myteam.classroomsystem.scas.Entity.Feedback;
import com.myteam.classroomsystem.scas.Entity.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

@Mapper
@Component
@Scope(value = "prototype")
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
    @Select("select sid,password from student where sid=#{sid}")
    public Student findStudentBySid(String sid);

    /**
     * @Description: 学生反馈
     * @Param:
     * @return:
     * @Author: king
     * @Date: 2020/4/10
     */
    @Insert("insert into feedback(sid, name, phone, question, application_time) VALUES(#{sid},#{name},#{phone},#{question},#{applicationTime}) ")
    public int contactUs(Feedback feedback);

    /**
     * @Description: 管理员回答学生的反馈
     * @Param:
     * @return:
     * @Author: king
     * @Date: 2020/4/10
     */
    @Update("update feedback set reply=#{reply} where id=#{id}")
    public int contactStu(@Param("id") long id, @Param("reply") String reply);

    /**
     * @Description: 该反馈已完成
     * @Param:
     * @return:
     * @Author: king
     * @Date: 2020/4/10
     */
    @Update("update feedback set  finish_time=#{finishTime},status=1  where id=#{id};")
    public int finishFeedBack(long id, Date finishTime);

}
