package com.wuzuqing.springbootdemo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "android_tag")
public class QuestionTagBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增
    private Integer id;
    private String tag;
    private Integer enable;
}
