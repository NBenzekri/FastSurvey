package com.nbenzekri.fastsurvey.entity;

import com.nbenzekri.fastsurvey.configuration.audit.AuditMetadata;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class Poll extends AuditMetadata {

    @Id
    private String id;

    private String title;

    private boolean multipleQuestions;

    public Poll(String title, boolean multipleQuestions) {
        this.title = title;
        this.multipleQuestions = multipleQuestions;
    }
}
