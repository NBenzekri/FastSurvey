package com.nbenzekri.fastsurvey.dto;

import com.nbenzekri.fastsurvey.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {
    private String id;

    private String questionTitle;

    private boolean multipleAnswers;

    private String description;

    private String pollId;

    private List<AnswerDTO> answers;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
