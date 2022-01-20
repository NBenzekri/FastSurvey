package com.nbenzekri.fastsurvey.service;

import com.nbenzekri.fastsurvey.entity.Poll;
import com.nbenzekri.fastsurvey.exception.NoSuchElementFoundException;
import com.nbenzekri.fastsurvey.repository.PollRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollServiceImpl implements IGenericService<Poll> {

    private static final Logger logger = LoggerFactory.getLogger(PollServiceImpl.class);

    @Autowired
    private PollRepository pollRepository;

    @Override
    public List<Poll> findAll() {
        return this.pollRepository.findAll();
    }

    @Override
    public Poll findById(String id) {
        return this.pollRepository.findById(id).orElseThrow(() -> new NoSuchElementFoundException("Poll Not found!"));
    }

    @Override
    public Poll save(Poll entity) {
        return this.pollRepository.insert(entity);
    }

    @Override
    public Poll update(String id, Poll entity) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }
}
