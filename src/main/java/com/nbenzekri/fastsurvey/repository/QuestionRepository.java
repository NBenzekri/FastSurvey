package com.nbenzekri.fastsurvey.repository;

import com.nbenzekri.fastsurvey.entity.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
}
