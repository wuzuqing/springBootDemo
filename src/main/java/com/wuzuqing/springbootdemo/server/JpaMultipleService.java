package com.wuzuqing.springbootdemo.server;

import com.wuzuqing.springbootdemo.entity.User;
import com.wuzuqing.springbootdemo.repository.primary.PrimaryUserRepository;
import com.wuzuqing.springbootdemo.repository.secondary.SecondaryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaMultipleService {
    @Autowired
    private PrimaryUserRepository primaryUserRepository;


    @Autowired
    private SecondaryUserRepository secondaryUserRepository;


    public List<User> findAll() {
        List<User> users = primaryUserRepository.findAll();
        List<User> list = secondaryUserRepository.findAll();
        users.addAll(list);
        return users;
    }



    public User findOne1(Integer id) {
        return primaryUserRepository.findById(id).get();
    }

    public User addOne1(User user) {
        return primaryUserRepository.saveAndFlush(user);
    }

    public List<User> findAll1() {
        return primaryUserRepository.findAll();
    }



    public User findOne2(Integer id) {
        return secondaryUserRepository.findById(id).get();
    }

    public User addOne2(User user) {
        return secondaryUserRepository.saveAndFlush(user);
    }

    public List<User> findAll2() {
        return secondaryUserRepository.findAll();
    }


}
