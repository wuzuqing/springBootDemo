package com.wuzuqing.springbootdemo.controller;


import com.wuzuqing.springbootdemo.baseresp.RespData;
import com.wuzuqing.springbootdemo.entity.User;
import com.wuzuqing.springbootdemo.server.JpaMultipleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jpa")
public class JpaMultipleController {
    @Autowired
    private JpaMultipleService jpaMultipleService;


    @GetMapping("/all")
    public RespData all() {
        return RespData.success(jpaMultipleService.findAll());
    }


    @GetMapping("/all1")
    public RespData all1() {
        return RespData.success(jpaMultipleService.findAll1());
    }

    @GetMapping("/all2")
    public RespData all2() {
        return RespData.success(jpaMultipleService.findAll2());
    }


    @GetMapping("/findOne1")
    public RespData findOne1(Integer id) {
        return RespData.success(jpaMultipleService.findOne1(id));
    }

    @GetMapping("/findOne2")
    public RespData findOne2(Integer id) {
        return RespData.success(jpaMultipleService.findOne2(id));
    }


    @GetMapping("/addOne1")
    public RespData addOne1(User user) {
        return RespData.success(jpaMultipleService.addOne1(user));
    }

    @GetMapping("/addOne2")
    public RespData addOne2(User user) {
        return RespData.success(jpaMultipleService.addOne2(user));
    }

}
