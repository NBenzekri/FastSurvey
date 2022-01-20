package com.nbenzekri.fastsurvey.dto;

import com.nbenzekri.fastsurvey.entity.VoteAnswer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PollUserVoteDTO {
    private String id;

    private String pollId;

    private List<VoteAnswersDTO> voteAnswers;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

}
