package com.nbenzekri.fastsurvey.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PollDTO {

    private String id;

    private String title;

    private boolean multipleQuestions;

    private List<QuestionDTO> questionList;

    private List<PollUserVoteDTO> pollUserVotes;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
