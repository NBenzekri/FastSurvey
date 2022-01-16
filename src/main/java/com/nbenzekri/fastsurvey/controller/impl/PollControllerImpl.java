package com.nbenzekri.fastsurvey.controller.impl;

import com.nbenzekri.fastsurvey.controller.PollController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PollControllerImpl implements PollController {

    private static final Logger _logger = LoggerFactory.getLogger(PollControllerImpl.class);


    public String getTest(){
        return "hello world!";
    }
}
