package com.nbenzekri.fastsurvey.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerSaveDTO {
    @NotBlank(message = "Answer option is empty")
    @NotNull
    private String answerOption;
}
