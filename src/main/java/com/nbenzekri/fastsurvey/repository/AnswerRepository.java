package com.nbenzekri.fastsurvey.repository;

import com.nbenzekri.fastsurvey.entity.Answer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends MongoRepository<Answer, String> {
}
