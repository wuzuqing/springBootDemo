package com.wuzuqing.springbootdemo;


import com.wuzuqing.springbootdemo.config.datasource.dynamic.DynamicDataSourceRegister;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//注册动态多数据源

@Import({DynamicDataSourceRegister.class})
//@MapperScan("com.wuzuqing.springbootdemo.mapper")
@EnableAsync
public class MyApp {

    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }
}
