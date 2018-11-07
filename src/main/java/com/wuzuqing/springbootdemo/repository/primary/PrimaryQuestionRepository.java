package com.wuzuqing.springbootdemo.repository.primary;

import com.wuzuqing.springbootdemo.entity.QuestionBean;
import com.wuzuqing.springbootdemo.entity.QuestionTagBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PrimaryQuestionRepository extends JpaRepository<QuestionBean, Integer> {

    List<QuestionBean> findByQuestionTag(QuestionTagBean tagBean);

}
