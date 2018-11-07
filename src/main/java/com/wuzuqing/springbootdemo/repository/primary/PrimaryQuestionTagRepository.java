package com.wuzuqing.springbootdemo.repository.primary;

import com.wuzuqing.springbootdemo.entity.QuestionTagBean;
import com.wuzuqing.springbootdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PrimaryQuestionTagRepository extends JpaRepository<QuestionTagBean,Integer> {

    QuestionTagBean findQuestionTagBeanByTag(String tag);

}
