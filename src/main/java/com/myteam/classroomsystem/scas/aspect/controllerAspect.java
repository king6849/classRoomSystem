package com.myteam.classroomsystem.scas.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class controllerAspect {
    private Logger logger = LoggerFactory.getLogger(controllerAspect.class);


    @Pointcut(value = "execution(* com.myteam.classroomsystem.scas.controller.*.*(..))")
    public void controllerPoint() {
    }


    @Before("controllerPoint()")
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("==================================start======================================");
        //url
        logger.info("url={}", request.getRequestURL());
        //method
        logger.info("method={}", request.getMethod());
        //ip
        logger.info("id={}", request.getRemoteAddr());
        //类方法
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //参数
        logger.info("请求参数args={}", Arrays.asList(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "object", pointcut = "controllerPoint()")
    public void afterReturning(Object object) {
        if (object == null) return;
        logger.info("返回结果response={}", object.toString());
        logger.info("===================================end=======================================");
    }

}
