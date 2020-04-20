package com.myteam.classroomsystem.scas.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {
    //图片接收保存的位置
    public static final String repairImageSavePath = "C:\\idea\\porjects\\images";

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/repair/img/**")
                .addResourceLocations("file:" + repairImageSavePath);
    }
}
