package com.nbenzekri.fastsurvey.entity;

import com.nbenzekri.fastsurvey.configuration.audit.AuditMetadata;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Question extends AuditMetadata {
    @Id
    private String id;

    private String questionTitle;

    private boolean multipleAnswers;

    private String description;

    private String pollId;
}
