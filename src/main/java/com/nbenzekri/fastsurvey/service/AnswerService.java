package com.nbenzekri.fastsurvey.service;

import com.nbenzekri.fastsurvey.entity.Answer;
import com.nbenzekri.fastsurvey.exception.BadRequestException;
import com.nbenzekri.fastsurvey.exception.NoSuchElementFoundException;
import com.nbenzekri.fastsurvey.repository.AnswerRepository;
import com.nbenzekri.fastsurvey.util.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerService implements IGenericService<Answer> {

    private static final Logger logger = LoggerFactory.getLogger(AnswerService.class);

    @Autowired
    private AnswerRepository answerRepository;


    @Override
    public List<Answer> findAll() {
        return this.answerRepository.findAll();
    }

    @Override
    public Answer findById(String id) {
        if (id == null) {
            throw new BadRequestException(CommonConstant.BAD_REQUEST_PARAM + id);
        }
        return this.answerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementFoundException("Answer not found with id: " + id));
    }

    @Override
    public Answer save(Answer entity) {
        return this.answerRepository.insert(entity);
    }

    public List<Answer> save(String questionId, List<Answer> answers) {
        answers.forEach(answer -> answer.setQuestionId(questionId));
        return answers.stream()
                .map(answer -> this.answerRepository.insert(answer))
                .collect(Collectors.toList());
    }

    @Override
    public Answer update(String id, Answer entity) {
        Answer answer = this.findById(id);
        answer.setAnswerOption(entity.getAnswerOption());
        return this.answerRepository.save(answer);
    }

    @Override
    public void deleteById(String id) {
        if (id == null) {
            throw new BadRequestException(CommonConstant.BAD_REQUEST_PARAM + id);
        }
        this.answerRepository.delete(this.findById(id));
        logger.info("Answer oll {} has been deleted!", id);
    }

    public List<Answer> findByQuestionId(String questionId) {
        return this.answerRepository.findByQuestionId(questionId);
    }
}
