package com.wuzuqing.springbootdemo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//注册动态多数据源
//@MapperScan("com.wuzuqing.springbootdemo.mapper")
@EnableAsync
//@EnableScheduling
public class MyApp {

    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }
}
