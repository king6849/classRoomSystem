package com.myteam.classroomsystem.scas.Entity;

import lombok.Data;

import java.util.List;

@Data
public class TeacherForSeacher {
    private String title;
    private List<ItemForteacher> item;
}
