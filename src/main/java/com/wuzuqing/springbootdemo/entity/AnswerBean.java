package com.wuzuqing.springbootdemo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "android_answer")
public class AnswerBean {
    @Id
    private int id;
    private String content;
    private String url;
    private Integer questionId;
}
