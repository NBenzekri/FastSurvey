package com.nbenzekri.fastsurvey.controller;

import com.nbenzekri.fastsurvey.dto.ResponseDTO;
import com.nbenzekri.fastsurvey.service.AnswerService;
import com.nbenzekri.fastsurvey.service.QuestionService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Api(value = "QuestionController", tags = "Question controller")
public class QuestionController {

    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @DeleteMapping("/v1/questions/{id}")
    public ResponseEntity<ResponseDTO<Map<String, Boolean>>> deleteQuestion(@PathVariable String id) {
        logger.info("Delete question with id: " + id);

        this.questionService.findById(id);
        this.answerService.findByQuestionId(id).forEach(answer -> this.answerService.deleteById(answer.getId()));
        this.questionService.deleteById(id);
        logger.info("Question and their related info are deleted!");
        return new ResponseDTO<>(Collections.singletonMap("deleted", Boolean.TRUE)).buildOk();
    }
}
