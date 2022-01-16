package com.nbenzekri.fastsurvey.controller;

import org.springframework.web.bind.annotation.GetMapping;

public interface PollController {
    @GetMapping("/test")
    public String getTest();
}
