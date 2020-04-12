package com.myteam.classroomsystem.scas.mapper;

import com.myteam.classroomsystem.scas.Entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TeacherMepper {

    /*  *
     * @Description: 查找所有老师
     * @Param: [department, role]
     * @return: java.util.List<java.lang.String>
     * @Author: king
     * @Date: 2020/4/12
     */
    @Select("select name,tid from teacher")
    public List<Teacher> findAllTeacher();


}
