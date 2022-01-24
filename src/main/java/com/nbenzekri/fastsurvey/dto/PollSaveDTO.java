package com.nbenzekri.fastsurvey.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PollSaveDTO {

    @NotBlank(message = "title is mandatory")
    private String title;

    @NotNull(message = "field not null!")
    private boolean multipleQuestions;

    @NotNull(message = "Questions are mandatory!")
    private List<QuestionSaveDTO> questionList;
}