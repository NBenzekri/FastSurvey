package com.nbenzekri.fastsurvey.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;

    private String userName;

    private boolean guestUser;

    private String status;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
