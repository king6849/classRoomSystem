package com.myteam.classroomsystem.scas.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Mapper
@Component
@Scope(value = "prototype")
public interface StudentMapper {

    /*
     * addStudent
     * */
    @Insert("insert into student(sid, name, email, phone, myclass, department,openid) values(#{sid},#{name},#{email},#{phone},#{myclass},#{department},#{openid}) ")
    public int addStudent(HashMap<String, Object> param);

}
