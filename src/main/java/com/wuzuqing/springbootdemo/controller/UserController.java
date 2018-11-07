package com.wuzuqing.springbootdemo.controller;

import com.wuzuqing.springbootdemo.baseresp.RespData;
import com.wuzuqing.springbootdemo.entity.User;
import com.wuzuqing.springbootdemo.service.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserServer userServer;

    @PostMapping("/insert")
    public RespData insert(String name, Integer age) {
        return RespData.success(userServer.insert(name, age));
    }
}
