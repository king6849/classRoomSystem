package com.myteam.classroomsystem.scas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ScasApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScasApplication.class, args);
    }

}
