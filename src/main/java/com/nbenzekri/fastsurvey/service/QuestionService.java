package com.nbenzekri.fastsurvey.service;

import com.nbenzekri.fastsurvey.entity.Question;
import com.nbenzekri.fastsurvey.exception.BadRequestException;
import com.nbenzekri.fastsurvey.exception.NoSuchElementFoundException;
import com.nbenzekri.fastsurvey.repository.QuestionRepository;
import com.nbenzekri.fastsurvey.util.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService implements IGenericService<Question> {

    private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> findAll() {
        return this.questionRepository.findAll();
    }

    @Override
    public Question findById(String id) {
        logger.error("id: {} ", id);
        if (id == null) {
            throw new BadRequestException(CommonConstant.BAD_REQUEST_PARAM + id);
        }
        return this.questionRepository.findById(id).orElseThrow(() -> new NoSuchElementFoundException("Question not found with id: " + id));
    }

    @Override
    public Question save(Question entity) {
        return this.questionRepository.insert(entity);
    }

    @Override
    public Question update(String id, Question entity) {
        Question question = this.findById(id);
        question.setPollId(question.getPollId());
        question.setQuestionTitle(entity.getQuestionTitle());
        question.setMultipleAnswers(entity.isMultipleAnswers());
        question.setDescription(entity.getDescription());
        return this.questionRepository.save(question);
    }

    @Override
    public void deleteById(String id) {
        if (id == null) {
            throw new BadRequestException(CommonConstant.BAD_REQUEST_PARAM + id);
        }
        this.questionRepository.delete(this.findById(id));
        logger.info("Answer oll {} has been deleted!", id);
    }

    public List<Question> findByPollId(String pollId) {
        return this.questionRepository.findByPollId(pollId);
    }
}
