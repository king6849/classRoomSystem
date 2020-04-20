package com.myteam.classroomsystem.scas.mapper;

import com.myteam.classroomsystem.scas.Entity.Teacher;
import com.myteam.classroomsystem.scas.Entity.names;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TestData {

    @Select("select id,full_name from namelist LIMIT 1,10")
    public List<names> names();


    @Insert("insert into teacher(tid,name,password,email,phone,department,role) values(#{tid},#{name },#{password},#{email},#{phone},#{department},#{role})")
    public int insertTeacher(Teacher teacher);


}
