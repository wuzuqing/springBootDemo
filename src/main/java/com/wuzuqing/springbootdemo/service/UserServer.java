package com.wuzuqing.springbootdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserServer {

//    @Autowired
//    private UserMapper userMapper;

    @Transactional
   public int insert(String name, Integer age) {
//        int insert = userMapper.insert(name, age);
//        int i = 1/age;
//        log.info("#######insert{}######:",insert);
//        return insert;
        return 0;
    }
}
