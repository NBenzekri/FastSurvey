package com.nbenzekri.fastsurvey.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Document
public class Poll extends DateAudit{

    @Id
    private UUID id;

    private String title;

    private boolean multipleQuestions;

}
