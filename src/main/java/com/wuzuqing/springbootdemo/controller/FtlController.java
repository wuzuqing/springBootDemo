package com.wuzuqing.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class FtlController {

    @RequestMapping("/index")
    public String index(Map<String,Object> map){
        map.put("name","吴祖清");
        return "index";
    }
}
