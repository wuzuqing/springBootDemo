package com.wuzuqing.springbootdemo.repository.primary;

import com.wuzuqing.springbootdemo.entity.AnswerBean;
import com.wuzuqing.springbootdemo.entity.QuestionBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PrimaryAnswerRepository extends JpaRepository<AnswerBean, Integer> {

    List<AnswerBean> getAnswerBeansByQuestionId(Integer questionId);
}
