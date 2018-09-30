package com.wuzuqing.springbootdemo.repository.primary;

import com.wuzuqing.springbootdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PrimaryUserRepository extends JpaRepository<User ,Integer> {
    User findByNameAndAge(String name, Integer age);
}
