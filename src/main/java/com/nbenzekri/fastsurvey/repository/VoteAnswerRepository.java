package com.nbenzekri.fastsurvey.repository;

import com.nbenzekri.fastsurvey.entity.VoteAnswer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteAnswerRepository extends MongoRepository<VoteAnswer, String> {
}
