package com.nbenzekri.fastsurvey.repository;

import com.nbenzekri.fastsurvey.entity.PollUserVote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PollUserVoteRepository extends MongoRepository<PollUserVote, String> {
    List<PollUserVote> findByPollId(String pollId);
}
