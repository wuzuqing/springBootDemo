//package com.wuzuqing.springbootdemo.controller;
//
//import com.wuzuqing.springbootdemo.configuration.rabbit.fanout.FanoutSender;
//import com.wuzuqing.springbootdemo.configuration.rabbit.hello.HelloSender;
//import com.wuzuqing.springbootdemo.configuration.rabbit.many.NeoSender;
//import com.wuzuqing.springbootdemo.configuration.rabbit.many.NeoSender2;
//import com.wuzuqing.springbootdemo.configuration.rabbit.object.ObjectSender;
//import com.wuzuqing.springbootdemo.configuration.rabbit.topic.TopicSender;
//import com.wuzuqing.springbootdemo.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class TestController {
//    @Autowired
//    private FanoutSender sender;
//
//    @Autowired
//    private HelloSender helloSender;
//    @Autowired
//    private NeoSender neoSender;
//
//    @Autowired
//    private NeoSender2 neoSender2;
//    @Autowired
//    private ObjectSender objectSender;
//
//    @Autowired
//    private TopicSender topicSender;
//
//    @GetMapping("/fanoutSender")
//    public void fanoutSender()  {
//        sender.send();
//    }
//
//    @GetMapping("/hello")
//    public void hello()  {
//        helloSender.send();
//    }
//
//    @GetMapping("/sendObject")
//    public void sendObject()  {
//        User user = new User();
//        user.setName("neo");
//        user.setAge(12);
//        objectSender.send(user);
//    }
//
//    @GetMapping("/oneToMany")
//    public void oneToMany()  {
//        for (int i = 0; i < 100; i++) {
//            neoSender.send(i);
//        }
//    }
//
//    @GetMapping("/manyToMany")
//    public void manyToMany()  {
//        for (int i = 0; i < 100; i++) {
//            neoSender.send(i);
//            neoSender2.send(i);
//        }
//    }
//
//    @GetMapping("/topic")
//    public void topic()  {
//        topicSender.send();
//    }
//
//    @GetMapping("/topic1")
//    public void topic1()  {
//        topicSender.send1();
//    }
//
//    @GetMapping("/topic2")
//    public void topic2()  {
//        topicSender.send2();
//    }
//}
