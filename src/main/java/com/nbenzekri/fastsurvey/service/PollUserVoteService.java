package com.nbenzekri.fastsurvey.service;

import com.nbenzekri.fastsurvey.entity.PollUserVote;
import com.nbenzekri.fastsurvey.exception.BadRequestException;
import com.nbenzekri.fastsurvey.repository.PollUserVoteRepository;
import com.nbenzekri.fastsurvey.util.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollUserVoteService implements IGenericService<PollUserVote> {
    private static final Logger logger = LoggerFactory.getLogger(PollUserVoteService.class);

    @Autowired
    PollUserVoteRepository pollUserVoteRepository;
    @Autowired
    PollServiceImpl pollService;

    @Override
    public List<PollUserVote> findAll() {
        return this.pollUserVoteRepository.findAll();
    }

    @Override
    public PollUserVote findById(String id) {
        return null;
    }

    public List<PollUserVote> findByPollId(String pollId) {
        return this.pollUserVoteRepository.findByPollId(pollId);
    }

    @Override
    public PollUserVote save(PollUserVote entity) {
        this.pollService.findById(entity.getPollId());
        try {
            return this.pollUserVoteRepository.insert(entity);
        } catch (DuplicateKeyException ex) {
            logger.error(ex.toString());
            throw new DuplicateKeyException(CommonConstant.DUPLICATE_KEY_EXCEPTION);
        }
    }

    @Override
    public PollUserVote update(String id, PollUserVote entity) {
        return null;
    }

    @Override
    public void deleteById(String id) {
        if (id == null) {
            throw new BadRequestException(CommonConstant.BAD_REQUEST_PARAM + id);
        }
        this.pollUserVoteRepository.delete(this.findById(id));
        logger.info("PollUserVote {} has been deleted!", id);
    }
}
