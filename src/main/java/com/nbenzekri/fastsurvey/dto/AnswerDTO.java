package com.nbenzekri.fastsurvey.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {
    private String id;

    private String answerOption;

    private String questionId;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
