package com.myteam.classroomsystem.scas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;

@EnableScheduling
@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.myteam.classroomsystem.scas.Repository")
public class ScasApplication {
    @Autowired
    private RedisTemplate redisTemplate = null;


    @PostConstruct
    public void init() {
        initRedisTemplate();

    }

    //    初始化redis存储序列化器，防止乱码
    private void initRedisTemplate() {
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
    }

    //    spring 安全加密
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(ScasApplication.class, args);
    }

}
