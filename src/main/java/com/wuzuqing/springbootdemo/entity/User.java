package com.wuzuqing.springbootdemo.entity;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
public class User {
    private Integer id;
    private Integer age;
    private String name;
}
