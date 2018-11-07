package com.wuzuqing.springbootdemo.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "android_question")
public class QuestionBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增
    private Integer id;
    @OneToOne
    private QuestionTagBean questionTag;
    @OneToOne
    private AnswerBean answer;

    private String question;

    private Date createDate;
}
