package com.wuzuqing.springbootdemo.controller;


import com.wuzuqing.springbootdemo.error.MyException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

    @GetMapping("/errorIndex")
    public String errorIndex(int i){
        int j = 1/i;
        return "suscess" + j;
    }

    @GetMapping("/hello")
    public String hello() throws Exception {
        throw new MyException("发生错误");
    }

}
