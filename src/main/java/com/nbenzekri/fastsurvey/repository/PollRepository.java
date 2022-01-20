package com.nbenzekri.fastsurvey.repository;

import com.nbenzekri.fastsurvey.entity.Poll;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends MongoRepository<Poll, String> {
}
