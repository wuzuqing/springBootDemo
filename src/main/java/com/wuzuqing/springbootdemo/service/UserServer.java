package com.wuzuqing.springbootdemo.service;

import com.wuzuqing.springbootdemo.entity.User;
import com.wuzuqing.springbootdemo.repository.primary.PrimaryUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserServer {

    @Autowired
    private PrimaryUserRepository primaryUserRepository;

    @Transactional
    public User insert(String name, Integer age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        primaryUserRepository.saveAndFlush(user);
        return user;
    }
}
