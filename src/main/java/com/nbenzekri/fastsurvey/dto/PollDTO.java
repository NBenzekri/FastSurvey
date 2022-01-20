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

    @NotBlank(message = "title is mandatory")
    private String title;

    @NotNull(message = "field not null!")
    private boolean multipleQuestions;

    @NotNull(message = "field not null!")
    private List<QuestionDTO> questionList;

    @NotNull(message = "field not null!")
    private List<PollUserVoteDTO> pollUserVotes;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
