package com.wuzuqing.springbootdemo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "android_answer")
public class AnswerBean {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增
    private int id;
    private String content;
    private String url;
    private Integer questionId;
}
