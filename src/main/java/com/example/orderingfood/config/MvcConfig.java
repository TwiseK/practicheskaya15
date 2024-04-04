package com.example.orderingfood.config;

import com.example.orderingfood.Repo.LogRepo;
import com.example.orderingfood.model.LogModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    public LogRepo logRepo;

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        LogModel log = new LogModel();
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        log.setData(currentDateTime);
        log.setLevel("INFO");
        log.setMessage("Сайт запущен");
        logRepo.save(log);
    }

}
