package com.nbenzekri.fastsurvey.entity;

import com.nbenzekri.fastsurvey.configuration.audit.AuditMetadata;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class VoteAnswer extends AuditMetadata {
    @Id
    private String id;

    private String answerId;

    private String pollUserVoteId;
}
