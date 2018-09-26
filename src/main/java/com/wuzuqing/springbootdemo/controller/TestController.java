package com.wuzuqing.springbootdemo.controller;


import com.wuzuqing.springbootdemo.server.AServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @Value("${name}")
    private String name;

    @Autowired
    AServer aServer;

    @RequestMapping("/getEmailAndPhone")
    public String getEmailAndPhone() {
        log.info("1");
        String phone = aServer.getEmailAndPhone();
        log.info("4");
        return name;
    }
}
