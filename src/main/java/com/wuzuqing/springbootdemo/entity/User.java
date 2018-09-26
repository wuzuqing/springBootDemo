package com.wuzuqing.springbootdemo.entity;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class User {
    private String name;
    private int age;


    public static void main(String[] args){
        User user  = new   User();
        user.setAge(20);
        user.setName("wuzuqing");
        log.info("user = "+user);
    }
}
