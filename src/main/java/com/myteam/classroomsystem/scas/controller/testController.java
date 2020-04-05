package com.myteam.classroomsystem.scas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @GetMapping("/hello")
    public String test() {
        System.out.println("hello");
        return "hello world";
    }

}
