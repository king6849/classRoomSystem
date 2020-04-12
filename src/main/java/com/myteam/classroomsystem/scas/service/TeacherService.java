package com.myteam.classroomsystem.scas.service;

import com.myteam.classroomsystem.scas.Entity.TeacherForSeacher;

import java.util.List;

public interface TeacherService {

    public List<TeacherForSeacher> findAllTeacherByDep();

}
