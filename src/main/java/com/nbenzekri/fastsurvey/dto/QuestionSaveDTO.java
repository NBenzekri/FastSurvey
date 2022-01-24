package com.nbenzekri.fastsurvey.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class QuestionSaveDTO {
    @NotBlank(message = "Question title is empty")
    private String questionTitle;

    private boolean multipleAnswers;

    private String description;

    private List<AnswerSaveDTO> answers;
}
