package com.nbenzekri.fastsurvey.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteAnswersDTO {
    private String id;

    private String answerId;

    private String questionId;

    private String pollUserVoteId;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
