package com.wuzuqing.springbootdemo.controller;


import com.wuzuqing.springbootdemo.baseresp.RespData;
import com.wuzuqing.springbootdemo.entity.QuestionBean;
import com.wuzuqing.springbootdemo.entity.QuestionTagBean;
import com.wuzuqing.springbootdemo.repository.primary.PrimaryAnswerRepository;
import com.wuzuqing.springbootdemo.repository.primary.PrimaryQuestionRepository;
import com.wuzuqing.springbootdemo.repository.primary.PrimaryQuestionTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController("/android")
public class AndroidQuestAdd {

    @Autowired
    PrimaryQuestionTagRepository questionTagRepository;
    @Autowired
    PrimaryQuestionRepository questionRepository;
    @Autowired
    PrimaryAnswerRepository answerRepository;


    @GetMapping("/getAllTag")
    public RespData getAllTag() {
        return RespData.success(questionTagRepository.findAll());
    }


    @GetMapping("/getQuestionByTag/{tag}")
    public RespData getQuestionByTag(@PathVariable("tag") String tag) {
        QuestionTagBean questionTagBean = questionTagRepository.findQuestionTagBeanByTag(tag);
        return RespData.success(questionRepository.findByQuestionTag(questionTagBean));
    }

    @GetMapping("/getQuestionByTag")
    public RespData getQuestionByTag(@RequestParam(name = "tag", required = false) String tag,
                                     @RequestParam(name = "tagId", required = false) Integer tagId, @RequestParam("lastId") Integer lastId) {
        if (tagId == null) {
            if (StringUtils.isEmpty(tag)) {
                return RespData.filed(-1, "无效参数");
            }
            QuestionTagBean questionTagBean = questionTagRepository.findQuestionTagBeanByTag(tag);
            tagId = questionTagBean.getId();
        }
        return RespData.success(questionRepository.findByTagAndLastId(tagId, lastId));
    }

    @PostMapping("/addQuestion")
    public RespData addQuest(@RequestParam("tag") String tag, @RequestParam("question") String question) {
        QuestionTagBean questionTagBean = questionTagRepository.findQuestionTagBeanByTag(tag);
        if (questionTagBean == null) {
            questionTagBean = new QuestionTagBean();
            questionTagBean.setTag(tag);
            questionTagBean.setEnable(1);
            questionTagRepository.saveAndFlush(questionTagBean);
        }
        QuestionBean questionBean = new QuestionBean();
        questionBean.setQuestion(question);
        questionBean.setQuestionTag(questionTagBean);
        questionBean.setCreateDate(new Date(System.currentTimeMillis()));
        questionRepository.saveAndFlush(questionBean);

//        AnswerBean answerBean = new AnswerBean();
//        answerBean.setId(questionBean.getId());
//        int lastIndexOf = answer.lastIndexOf("作者：");
//        if (lastIndexOf != -1) {
//            answer = answer.substring(0, lastIndexOf);
//        }
//        answer = answer.replaceAll(" ", "\n");
//        answerBean.setContent(answer);
//        answerBean.setUrl(url);
//
//        answerRepository.saveAndFlush(answerBean);
//        questionBean.setAnswer(answerBean);

        questionRepository.save(questionBean);

        return RespData.success(questionBean);
    }
}
