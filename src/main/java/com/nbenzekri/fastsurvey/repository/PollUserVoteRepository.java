package com.nbenzekri.fastsurvey.repository;

import com.nbenzekri.fastsurvey.entity.PollUserVote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollUserVoteRepository extends MongoRepository<PollUserVote, String> {
}
