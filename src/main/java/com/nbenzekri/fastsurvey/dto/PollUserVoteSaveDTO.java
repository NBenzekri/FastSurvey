package com.nbenzekri.fastsurvey.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class PollUserVoteSaveDTO {
    @NotNull(message = "Poll id is required!")
    private String pollId;

    private List<VoteAnswersDTO> voteAnswers;
}
