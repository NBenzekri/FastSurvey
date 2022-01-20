package com.nbenzekri.fastsurvey;

import com.nbenzekri.fastsurvey.entity.Poll;
import com.nbenzekri.fastsurvey.repository.PollRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class FastSurveyApplication {

    @Autowired
    PollRepository pollRepository;

    public static void main(String[] args) {
        SpringApplication.run(FastSurveyApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public CommandLineRunner mockData() {

        return args -> {
            Poll poll = new Poll();
            pollRepository.insert(new Poll("title", true));
        };
    }

}
