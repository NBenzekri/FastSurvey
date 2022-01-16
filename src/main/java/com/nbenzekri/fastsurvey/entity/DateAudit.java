package com.nbenzekri.fastsurvey.entity;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Configuration
@EnableMongoAuditing
public abstract class DateAudit implements Serializable {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
