package com.nbenzekri.fastsurvey.repository;

import com.nbenzekri.fastsurvey.entity.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
    List<Question> findByPollId(String pollId);
}
