package com.wuzuqing.springbootdemo.entity;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user_1")
public class User  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增
    private Integer id;
    private String name;
    private Integer age;
}
