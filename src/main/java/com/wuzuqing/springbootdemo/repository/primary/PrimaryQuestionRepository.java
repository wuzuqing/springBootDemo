package com.wuzuqing.springbootdemo.repository.primary;

import com.wuzuqing.springbootdemo.entity.QuestionBean;
import com.wuzuqing.springbootdemo.entity.QuestionTagBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PrimaryQuestionRepository extends JpaRepository<QuestionBean, Integer> {

    List<QuestionBean> findByQuestionTag(QuestionTagBean tagBean);


    @Query(value = "select * from android_question where question_tag_id=?1 and id>?2  limit 10 " ,nativeQuery = true)
    List<QuestionBean> findByTagAndLastId(Integer tagId,Integer lastId);


}
