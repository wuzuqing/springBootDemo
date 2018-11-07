package com.wuzuqing.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping("/ftl")
public class FtlController {

    @RequestMapping(path = "/index", method = {RequestMethod.GET})
    public String index(Map<String, Object> map) {
        map.put("name", "吴祖清");
        return "ftlIndex";
    }
}
