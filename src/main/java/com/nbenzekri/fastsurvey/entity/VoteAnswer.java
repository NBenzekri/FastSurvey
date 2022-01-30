package com.nbenzekri.fastsurvey.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteAnswer {
    private String questionId;

    private String answerId;
}
