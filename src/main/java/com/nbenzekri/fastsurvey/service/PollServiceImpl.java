package com.nbenzekri.fastsurvey.service;

import com.nbenzekri.fastsurvey.entity.Poll;
import com.nbenzekri.fastsurvey.exception.BadRequestException;
import com.nbenzekri.fastsurvey.exception.NoSuchElementFoundException;
import com.nbenzekri.fastsurvey.repository.AnswerRepository;
import com.nbenzekri.fastsurvey.repository.PollRepository;
import com.nbenzekri.fastsurvey.repository.QuestionRepository;
import com.nbenzekri.fastsurvey.util.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollServiceImpl implements IGenericService<Poll> {

    private static final Logger logger = LoggerFactory.getLogger(PollServiceImpl.class);

    @Autowired
    private PollRepository pollRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public List<Poll> findAll() {
        return this.pollRepository.findAll();
    }

    @Override
    public Poll findById(String id) {
        if (id == null) {
            throw new BadRequestException(CommonConstant.BAD_REQUEST_PARAM + id);
        }
        return this.pollRepository.findById(id).orElseThrow(() -> new NoSuchElementFoundException("Poll Not found with id: " + id));
    }

    /**
     * Save new Poll to the database
     * a Poll has a title and a list of question in the dto
     *
     * @param entity
     * @return
     */
    @Override
    public Poll save(Poll entity) {
        try {
            return this.pollRepository.insert(entity);
        } catch (DuplicateKeyException ex) {
            logger.error(ex.toString());
            throw new DuplicateKeyException(CommonConstant.DUPLICATE_KEY_EXCEPTION);
        }
    }

    @Override
    public Poll update(String id, Poll entity) {
        Poll poll = this.findById(id);
        poll.setTitle(entity.getTitle());
        poll.setMultipleQuestions(entity.isMultipleQuestions());
        return this.pollRepository.save(poll);
    }

    @Override
    public void deleteById(String id) {
        if (id == null) {
            throw new BadRequestException(CommonConstant.BAD_REQUEST_PARAM + id);
        }
        this.pollRepository.delete(this.findById(id));
        logger.info("Poll {} has been deleted!", id);
    }
}
