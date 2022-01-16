package com.nbenzekri.fastsurvey.repository;

import com.nbenzekri.fastsurvey.entity.Poll;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PollRepository extends MongoRepository<Poll, UUID> {
}
